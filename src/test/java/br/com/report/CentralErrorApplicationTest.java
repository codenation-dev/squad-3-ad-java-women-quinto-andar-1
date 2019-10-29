package br.com.report;

import br.com.report.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CentralErrorApplicationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port = 8080;

    private String getRootUrl(){
        return "http://localhost:" + port;
    }

    @Test
    public void testCreateUser(){
        User user = new User();
        user.setLogin("tainamedeiros");
        user.setPassword("123456");
        user.setEmail("tainajmedeiros@gmail.com");
        user.setLastActivity("10/10/10");

        ResponseEntity<User> postResponse = restTemplate.postForEntity(getRootUrl() +
                "/api/user", user, User.class);
        Assert.assertNotNull(postResponse);
        Assert.assertNotNull(postResponse.getBody());
    }

    @Test
    public void testGetAllUsers(){
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange((getRootUrl()) + "/api/users",
                HttpMethod.GET, entity, String.class);
        Assert.assertNotNull(response.getBody());
    }

    @Test
    public void testGetUserById(){
        User user = restTemplate.getForObject(getRootUrl() + "/api/user/1", User.class);
        System.out.println(user.getLogin());
        Assert.assertNotNull(user);
    }

    @Test
    public void testUpdatePost(){

    }

}
