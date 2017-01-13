package com.baeldung.server;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;

import com.baeldung.server.model.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JerseyApiLiveTest {

    private static final String SERVICE_URL = "http://localhost:8082/spring-jersey/resources/employees";

    @Test
    public void givenGetAllEmployees_whenCorrectRequest_thenResponseCodeSuccess() throws ClientProtocolException, IOException {
        final HttpUriRequest request = new HttpGet(SERVICE_URL);

        final HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

        assert(httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK);
    }

    @Test
    public void givenGetEmployee_whenEmployeeExists_thenResponseCodeSuccess() throws ClientProtocolException, IOException {
        final HttpUriRequest request = new HttpGet(SERVICE_URL + "/1");

        final HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

        assert(httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK);
    }

    @Test
    public void givenGetEmployee_whenEmployeeDoesNotExist_thenResponseCodeNotFound() throws ClientProtocolException, IOException {
        final HttpUriRequest request = new HttpGet(SERVICE_URL + "/1000");

        final HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

        assert(httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_NOT_FOUND);
    }

    @Test
    public void givenGetEmployee_whenJsonRequested_thenCorrectDataRetrieved() throws ClientProtocolException, IOException {
        final HttpUriRequest request = new HttpGet(SERVICE_URL + "/1");

        request.setHeader("Accept", "application/json");
        final HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        ObjectMapper mapper = new ObjectMapper();
        Employee emp = mapper.readValue(httpResponse.getEntity().getContent(), Employee.class);

        assert(emp.getFirstName().equals("Jane"));
    }

    @Test
    public void givenAddEmployee_whenJsonRequestSent_thenResponseCodeCreated() throws ClientProtocolException, IOException {
        final HttpPost request = new HttpPost(SERVICE_URL);

        Employee emp = new Employee(5, "Johny");
        ObjectMapper mapper = new ObjectMapper();
        String empJson = mapper.writeValueAsString(emp);
        StringEntity input = new StringEntity(empJson);
        input.setContentType("application/json");
        request.setEntity(input);
        final HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

        assert(httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_CREATED);
    }

    @Test
    public void givenAddEmployee_whenRequestForExistingObjectSent_thenResponseCodeConflict() throws ClientProtocolException, IOException {
        final HttpPost request = new HttpPost(SERVICE_URL);

        Employee emp = new Employee(1, "Johny");
        ObjectMapper mapper = new ObjectMapper();
        String empJson = mapper.writeValueAsString(emp);
        StringEntity input = new StringEntity(empJson);
        input.setContentType("application/json");
        request.setEntity(input);
        final HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

        assert(httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_CONFLICT);
    }

}