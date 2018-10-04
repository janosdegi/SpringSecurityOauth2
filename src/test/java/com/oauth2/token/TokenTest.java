package com.oauth2.token;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import com.oauth2.config.MvcConfig;
import com.oauth2.config.RootConfig;
import com.oauth2.config.SecurityConfig;
import org.junit.Assert;
import org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.util.JacksonJsonParser;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.*;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by Dégi János on 2018.03.16..
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={RootConfig.class, SecurityConfig.class, MvcConfig.class})
@WebAppConfiguration
public class TokenTest {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private FilterChainProxy springSecurityFilterChain;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac)
                .addFilter(springSecurityFilterChain).build();
    }

    @Test
    public void givenDBUser_whenRevokeToken_thenAuthorized() {
        String accessToken = null;
        try {
            accessToken = obtainAccessToken("tdj", "1234");
        } catch (Exception e) {
            e.printStackTrace();
        }
//        A65DG9322K
        System.out.println("accessToken: " + accessToken);

        Assert.assertNotNull(accessToken);
    }

    private String obtainAccessToken(String username, String password) throws Exception {

        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("grant_type", "password");
//        params.add("client_id", "jdbctestuser");
        params.add("username", username);
        params.add("password", password);

        System.out.println(" START --------------------------------------------------  ");

        ResultActions result
                = mockMvc.perform(post("/oauth/token")
                .params(params)
                .with(httpBasic("jdbctestuser","jdbctestusersecret"))
                .accept("application/json;charset=UTF-8"))
//                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"));



        String resultString = result.andReturn().getResponse().getContentAsString();

        System.out.println(resultString);

        System.out.println(" END --------------------------------------------------  ");

        JacksonJsonParser jsonParser = new JacksonJsonParser();
        return jsonParser.parseMap(resultString).get("access_token").toString();
    }


    private String obtainAccessToken(String clientId, String username, String password) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("grant_type", "password");
        params.put("client_id", clientId);
        params.put("username", username);
        params.put("password", password);
        Response response = RestAssured.given().auth().preemptive()
                .basic(clientId, "secret").and().with().params(params).when()
                .post("http://localhost:8080/oauth/token");

        System.out.println(response.getBody().asString());

        return response.jsonPath().getString("access_token");
    }
}
