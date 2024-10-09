package com.baeldung.dbteardown;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("hsql")
@Transactional
public class TransactionalLiveTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        String insertSql = "INSERT INTO customers (name, email) VALUES (?, ?)";
        Customer customer = new Customer("John", "john@domain.com");
        jdbcTemplate.update(insertSql, customer.getName(), customer.getEmail());
    }

    @Test
    void whenPostHttpRequestCustomers_thenStatusOK() throws Exception {
        Customer customer = new Customer("John", "john@domain.com");
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter objectWriter = mapper.writer()
            .withDefaultPrettyPrinter();
        String requestJson = objectWriter.writeValueAsString(customer);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
            .andExpect(MockMvcResultMatchers.status()
                .isOk());
    }

    @Test
    void whenGetHttpRequestToCustomers_thenStatusOk() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/customers"))
            .andExpect(MockMvcResultMatchers.content()
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status()
                .isOk());
    }

    @Test
    void whenGetCustomerByName_thenStatusOK() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/customers/John"))
            .andExpect(MockMvcResultMatchers.status()
                .isOk())
            .andExpect(MockMvcResultMatchers.content()
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.jsonPath("$.name")
                .value("John"));
    }

    @Test
    void whenGetCustomerByName_notFound_thenStatus404() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/customers/Doe")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status()
                .isNotFound());
    }
}
