package com.baeldung.client.messagebodywriter;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.JAXB;

import com.baeldung.server.model.Employee;

@Provider
@Produces(MediaType.APPLICATION_XML)
public class XmlEmployeeWriter implements MessageBodyWriter<Employee> {

    @Override
    public long getSize(Employee post, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return -1;
    }

    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return type.equals(Employee.class) && mediaType.toString().startsWith(MediaType.APPLICATION_XML.toString());
    }

    @Override
    public void writeTo(Employee post, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream) throws IOException, WebApplicationException {
        JAXB.marshal(post, entityStream);
    }
}
