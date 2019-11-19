package br.com.report;

import br.com.report.entity.Log;
import br.com.report.entity.User;
import br.com.report.payload.LogRequest;
import br.com.report.payload.LoginRequest;
import br.com.report.payload.SignUpRequest;
import br.com.report.repository.LogRepository;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@PropertySource("classpath:application-test.properties")
@Transactional
public class LogControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LogRepository logRepository;

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
        this.mvc.perform( MockMvcRequestBuilders
                .post("/api/auth/cad")
                .content(asJsonString(
                        new SignUpRequest("tequila", "tequila@email.com", "TQ@123")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
    }

    private User getUser(Long id) throws Exception {
        Optional<User> user = userRepository.findFirstByOrderById();
        return user.orElse(null);

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
                .andExpect(MockMvcResultMatchers.jsonPath("jwtAuthenticationResponse.tokenType").value("Bearer"))
                .andExpect(MockMvcResultMatchers.jsonPath("jwtAuthenticationResponse.accessToken").exists());

        String resultString = result.andReturn().getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Map> map = mapper.readValue(resultString, Map.class);
        return map.get("jwtAuthenticationResponse").get("accessToken").toString();

    }

    private Log generaterLog(String usertoken, String accessToken) throws Exception {
        Optional<User> user = userRepository.findByToken(usertoken);
        Log log = logRepository.save(new Log("error","classX.Controller","descrição do erro",
                "controller", "ativo", "dev", 100, user.get()));
        return log;
    }

    @Test
    public void successFindByIdLogTest() throws Exception {
        generaterUser("taina", "tainajmedeiros@gmail.com", "TM@123");
        String acessToken = obtainAccessToken("taina","TM@123");

        User user = getUser(1L);
        Log log = generaterLog(user.getToken(), acessToken);

        ResultActions result
                = this.mvc.perform( MockMvcRequestBuilders
                .get("/api/logs/id/"+ log.getId())
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
                .get("/api/logs/id/10")
                .header("Authorization", "Bearer " + acessToken)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }

    @Test
    public void notUserLoginFindByIdLogTest() throws Exception{
        ResultActions result
                = this.mvc.perform( MockMvcRequestBuilders
                .get("/api/logs/id/1")
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
                .get("/api/logs")
                .header("Authorization", "Bearer " + acessToken)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void unauthorizedGetAllLog() throws Exception {
        ResultActions result
                = this.mvc.perform( MockMvcRequestBuilders
                .get("/api/logs")
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
                .get("/api/logs/dev")
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
                .get("/api/logs/envOrdLev/dev")
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
                .get("/api/logs/envOrdEve/dev")
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
                .get("/api/logs/dev/error")
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
                .get("/api/logs/dev/classX.Controller")
                .header("Authorization", "Bearer " + acessToken)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void successffindLogByEnvironmentAndSearchByDescription() throws Exception{
        generaterUser("taina", "tainajmedeiros@gmail.com", "TM@123");
        String acessToken = obtainAccessToken("taina","TM@123");

        User user = getUser(1L);
        generaterLog(user.getToken(), acessToken);

        this.mvc.perform( MockMvcRequestBuilders
                .get("/api/logs/dev/classX.Controller")
                .header("Authorization", "Bearer " + acessToken)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
