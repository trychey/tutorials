package com.baeldung.client.rest;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.baeldung.client.messagebodyreader.JsonEmployeeReader;
import com.baeldung.client.messagebodyreader.XmlEmployeeReader;
import com.baeldung.client.messagebodywriter.JsonEmployeeWriter;
import com.baeldung.client.messagebodywriter.XmlEmployeeWriter;
import com.baeldung.server.model.Employee;

public class RestClient {

    private static final String REST_URI = "http://localhost:8082/spring-jersey/resources/employees";
    private Client client = ClientBuilder.newClient();

    public Response createJsonEmployee(Employee emp) {
        return client.target(REST_URI).register(JsonEmployeeWriter.class).request(MediaType.APPLICATION_JSON).post(Entity.entity(emp, MediaType.APPLICATION_JSON));
    }

    public Employee getJsonEmployee(int id) {
        return client.target(REST_URI).register(JsonEmployeeReader.class).path(new Integer(id).toString()).request(MediaType.APPLICATION_JSON).get(new GenericType<Employee>() {
        });
    }

    public Response createXmlEmployee(Employee emp) {
        return client.target(REST_URI).register(XmlEmployeeWriter.class).request(MediaType.APPLICATION_XML).post(Entity.entity(emp, MediaType.APPLICATION_XML));
    }

    public Employee getXmlEmployee(int id) {
        return client.target(REST_URI).register(XmlEmployeeReader.class).path(new Integer(id).toString()).request(MediaType.APPLICATION_XML).get(new GenericType<Employee>() {
        });
    }
}
