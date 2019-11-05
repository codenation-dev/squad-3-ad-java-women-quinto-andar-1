package br.com.report;

import br.com.report.payload.SignUpRequest;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@PropertySource("classpath:application-test.properties")
public class AutorizationTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void sucessSignUpUserTest() throws Exception
    {
        this.mvc.perform( MockMvcRequestBuilders
                .post("/api/auth/cad")
                .content(asJsonString(
                        new SignUpRequest("tata", "tata@email.com", "TM@123")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true));
    }

    @Test
    public void existsByLoginSignUpUserTest() throws Exception
    {
        generaterUser();
        this.mvc.perform( MockMvcRequestBuilders
                .post("/api/auth/cad")
                .content(asJsonString(
                        new SignUpRequest("taina", "tai@email.com", "tai@123")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(false));

    }

    @Test
    public void existsByEmailSignUpUserTest() throws Exception
    {
        generaterUser();
        this.mvc.perform( MockMvcRequestBuilders
                .post("/api/auth/cad")
                .content(asJsonString(
                        new SignUpRequest("tai", "taina@email.com", "TM@123")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
               // .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(false));

    }

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
    }



}