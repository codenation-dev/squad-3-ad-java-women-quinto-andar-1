package br.com.report;

import br.com.report.entity.User;
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
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;


import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@PropertySource("classpath:application-test.properties")
@Transactional
public class UserControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private UserRepository userRepository;

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void generaterUser() throws Exception {
        this.mvc.perform( MockMvcRequestBuilders
                .post("/api/auth/cad")
                .content(asJsonString(
                        new SignUpRequest("taina", "taina@email.com", "TM@123")))
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
                    .andExpect(MockMvcResultMatchers.jsonPath("tokenType").value("Bearer"))
                    .andExpect(MockMvcResultMatchers.jsonPath("accessToken").exists());

        String resultString = result.andReturn().getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> map = mapper.readValue(resultString, Map.class);
        return map.get("accessToken");
    }

    @Test
    public void successFindByIdTest() throws Exception {
        generaterUser();
        User user = getUser(1L);

        String token = obtainAccessToken("taina","TM@123");
        ResultActions result
                = this.mvc.perform( MockMvcRequestBuilders
                .get("/api/user/"+user.getId())
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.login").value("taina"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("taina@email.com"));
    }

    @Test
    public void successGetAllUse() throws Exception {
        generaterUser();
        String token = obtainAccessToken("taina","TM@123");
        this.mvc.perform( MockMvcRequestBuilders
                .get("/api/user")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void nonexistentIdFindByIdTest() throws Exception {
        generaterUser();
        String token = obtainAccessToken("taina","TM@123");
        ResultActions result
                = this.mvc.perform( MockMvcRequestBuilders
                .get("/api/user/4")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        int resultString = result.andReturn().getResponse().getContentLength();
        assertEquals(0,resultString);

    }

    @Test
    public void unauthorizedGetAllUse() throws Exception {
        this.mvc.perform( MockMvcRequestBuilders
                .get("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void unauthorizedFindByIdTest() throws Exception {
        ResultActions result
                = this.mvc.perform( MockMvcRequestBuilders
                .get("/api/user/4")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }
}
