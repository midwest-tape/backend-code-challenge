package com.midwesttape.project.challengeapplication;

import com.midwesttape.project.challengeapplication.facade.UserFacade;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;


@SpringBootTest
@AutoConfigureMockMvc
public class GraphqlControllerIntegrationTest {

    private static final String GRAPHQL_PATH = "/graphql";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    UserFacade userFacade;

    @Test
    public void givenUserId_whenRead_thenStatusIsOk() throws Exception {

        String getUserWithAddrQuery = "{ getUserWithAddress (userId: 1) {id firstName address {id address1} } }";

        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.post(GRAPHQL_PATH).content(toJSON(getUserWithAddrQuery))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(request().asyncStarted())
                .andReturn();
        this.mockMvc.perform(asyncDispatch(result))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.getUserWithAddress").isNotEmpty());
    }
    
    @Test
    public void whenUpdateUser_thenStatusIsOk() throws Exception {
        String addBookMutation = "mutation { updateUser(modifiedUser: {id: 1, firstName: \"jimmy\","
                + "lastName: \"johns\","
                + "username: \"johnyjohns\","
                + "password: \"password\","
                + "address: {"
                + "id: 1,"
                + "address1: \"2nd main st\","
                + "address2: \"thedugout\","
                + "city: \"hudson\","
                + "state: \"FL\","
                + "postal: \"33759\" }"
                + "}) { id firstName address {id address1}}}";
        
        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.post(GRAPHQL_PATH).content(toJSON(addBookMutation))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(request().asyncStarted())
            .andReturn();
                
        this.mockMvc.perform(asyncDispatch(result))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data.updateUser.id").value("1"))
            .andExpect(jsonPath("$.data.updateUser.firstName").value("jimmy"))
            .andExpect(jsonPath("$.data.updateUser.address.address1").value("2nd main st"));
    }

    private String toJSON(String query) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("query", query);
        return jsonObject.toString();
    }
}
