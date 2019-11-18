package br.com.report;

import br.com.report.entity.User;
import br.com.report.payload.LogRequest;
import br.com.report.payload.LoginRequest;
import br.com.report.payload.SignUpRequest;
import br.com.report.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@PropertySource("classpath:application-test.properties")
public class LogControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void generaterUser(String login, String email, String password) throws Exception {
        this.mvc.perform( MockMvcRequestBuilders
                .post("/api/auth/cad")
                .content(asJsonString(
                        new SignUpRequest(login, email, password)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
    }

    private User getUser(Long id) throws Exception {
        return userRepository.findById(id).get();
    }


    private String obtainAccessToken(String username, String password) throws Exception {
        ResultActions result
                = this.mvc.perform( MockMvcRequestBuilders
                .post("/api/auth/login")
                .content(asJsonString(
                        new LoginRequest(username, password)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("tokenType").value("Bearer"))
                .andExpect(MockMvcResultMatchers.jsonPath("accessToken").exists());

        String resultString = result.andReturn().getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> map = mapper.readValue(resultString, Map.class);
        return map.get("accessToken");
    }

    private void generaterLog(String usertoken, String accessToken) throws Exception {
        this.mvc.perform( MockMvcRequestBuilders
                .post("/api/log")
                .header("Authorization", "Bearer " + accessToken)
                .content(asJsonString(
                        new LogRequest("error","classX.Controller","descrição do erro", "controller", "ativo", "dev", 100, usertoken )))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
    }

    @Test
    public void successFindByIdLogTest() throws Exception {
        generaterUser("taina", "tainajmedeiros@gmail.com", "TM@123");
        String acessToken = obtainAccessToken("taina","TM@123");

        User user = getUser(1L);
        generaterLog(user.getToken(), acessToken);

        ResultActions result
                = this.mvc.perform( MockMvcRequestBuilders
                .get("/api/log/id/1")
                .header("Authorization", "Bearer " + acessToken)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.level").value("error"));
    }

    @Test
    public void notLogFindByIdLogTest() throws Exception{
        generaterUser("taina", "tainajmedeiros@gmail.com", "TM@123");
        String acessToken = obtainAccessToken("taina","TM@123");

        User user = getUser(1L);
        generaterLog(user.getToken(), acessToken);

        ResultActions result
                = this.mvc.perform( MockMvcRequestBuilders
                .get("/api/log/id/10")
                .header("Authorization", "Bearer " + acessToken)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }

    @Test
    public void notUserLoginFindByIdLogTest() throws Exception{
        ResultActions result
                = this.mvc.perform( MockMvcRequestBuilders
                .get("/api/log/id/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());

    }

    @Test
    public void successGetAllLog() throws Exception {
        generaterUser("taina", "tainajmedeiros@gmail.com", "TM@123");
        String acessToken = obtainAccessToken("taina","TM@123");

        User user = getUser(1L);
        generaterLog(user.getToken(), acessToken);

        this.mvc.perform( MockMvcRequestBuilders
                .get("/api/log")
                .header("Authorization", "Bearer " + acessToken)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void unauthorizedGetAllLog() throws Exception {
        ResultActions result
                = this.mvc.perform( MockMvcRequestBuilders
                .get("/api/log")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void successFindLogByEnvironment() throws Exception{
        generaterUser("taina", "tainajmedeiros@gmail.com", "TM@123");
        String acessToken = obtainAccessToken("taina","TM@123");

        User user = getUser(1L);
        generaterLog(user.getToken(), acessToken);

        this.mvc.perform( MockMvcRequestBuilders
                .get("/api/log/dev")
                .header("Authorization", "Bearer " + acessToken)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void successfindLogByEnvironmentAndOrderByLevel() throws Exception{
        generaterUser("taina", "tainajmedeiros@gmail.com", "TM@123");
        String acessToken = obtainAccessToken("taina","TM@123");

        User user = getUser(1L);
        generaterLog(user.getToken(), acessToken);

        this.mvc.perform( MockMvcRequestBuilders
                .get("/api/log/envOrdLev/dev")
                .header("Authorization", "Bearer " + acessToken)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void successfindLogByEnvironmentAndOrderByEvent() throws Exception{
        generaterUser("taina", "tainajmedeiros@gmail.com", "TM@123");
        String acessToken = obtainAccessToken("taina","TM@123");

        User user = getUser(1L);
        generaterLog(user.getToken(), acessToken);

        this.mvc.perform( MockMvcRequestBuilders
                .get("/api/log/envOrdEve/dev")
                .header("Authorization", "Bearer " + acessToken)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void successffindLogByEnvironmentAndSearchByLevel() throws Exception{
        generaterUser("taina", "tainajmedeiros@gmail.com", "TM@123");
        String acessToken = obtainAccessToken("taina","TM@123");

        User user = getUser(1L);
        generaterLog(user.getToken(), acessToken);

        this.mvc.perform( MockMvcRequestBuilders
                .get("/api/log/dev/error")
                .header("Authorization", "Bearer " + acessToken)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void successffindLogByEnvironmentAndSearchByOrigin() throws Exception{
        generaterUser("taina", "tainajmedeiros@gmail.com", "TM@123");
        String acessToken = obtainAccessToken("taina","TM@123");

        User user = getUser(1L);
        generaterLog(user.getToken(), acessToken);

        this.mvc.perform( MockMvcRequestBuilders
                .get("/api/log/dev/classX.Controller")
                .header("Authorization", "Bearer " + acessToken)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

//    @Test
//    public void successffindLogByEnvironmentAndSearchByDescription() throws Exception{
//        generaterUser("taina", "tainajmedeiros@gmail.com", "TM@123");
//        String acessToken = obtainAccessToken("taina","TM@123");
//
//        User user = getUser(1L);
//        generaterLog(user.getToken(), acessToken);
//
//        this.mvc.perform( MockMvcRequestBuilders
//                .get("/api/log/dev/classX.Controller")
//                .header("Authorization", "Bearer " + acessToken)
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//    }

}
