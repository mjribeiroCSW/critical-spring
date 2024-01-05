package IntegrationTests;

import com.boar.lil.criticalspring.CriticalSpringApplication;
import com.boar.lil.h2entity.CreditCard;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = CriticalSpringApplication.class)
@AutoConfigureMockMvc
public class IntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    // Used for JSON serialization/deserialization
    private ObjectMapper objectMapper;

    @Test
    public void testGetCreditCardById() throws Exception {
        // Perform a GET request to the endpoint
        MvcResult result = mockMvc.perform(get("/api/creditcard/1"))
                .andExpect(status().isOk())
                .andReturn();

        // Extract the response content as a String
        var jsonResponse = result.getResponse().getContentAsString();
        var creditCard = objectMapper.readValue(jsonResponse, CreditCard.class);

        assertEquals(1, creditCard.id);
        assertEquals("John", creditCard.FirstName);
    }
}