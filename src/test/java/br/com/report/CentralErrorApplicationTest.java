package br.com.report;

import com.google.common.net.HttpHeaders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CentralErrorApplicationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl(){
        return "http://localhost:" + port;
    }




    @Test
    public void testGetAllUsers(){
    /*    HttpHeaders headers = new  HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

     */


    }

    @Test
    public void testGetAllLogs(){

    }

    @Test
    public void testGetAllTokens(){

    }
}
