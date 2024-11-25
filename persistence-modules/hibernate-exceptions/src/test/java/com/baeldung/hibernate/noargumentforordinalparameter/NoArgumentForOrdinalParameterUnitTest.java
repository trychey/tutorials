package com.baeldung.hibernate.noargumentforordinalparameter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.hibernate.QueryParameterException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.baeldung.hibernate.exception.persistentobject.HibernateUtil;
import com.baeldung.hibernate.noargumentforordinalparameter.Employee;

class NoArgumentForOrdinalParameterUnitTest {

    private static Session session;

    @BeforeAll
    static void init() {
        session = HibernateUtil.getSessionFactory()
            .openSession();
        session.beginTransaction();
    }

    @AfterAll
    static void clear() {
        session.close();
    }

    @Test
    void whenMissingParameter_thenThrowQueryParameterException() {
        assertThatThrownBy(() -> {
            Query<Employee> query = session.createQuery("FROM Employee emp WHERE firstName = ?1 AND lastName = ?2", Employee.class);
            query.setParameter(1, "Jean");

            query.list();
        }).isInstanceOf(QueryParameterException.class)
            .hasMessageContaining("No argument for ordinal parameter");
    }

    @Test
    void whenDefiningAllParameters_thenCorrect() {
        Query<Employee> query = session.createQuery("FROM Employee emp WHERE firstName = ?1 AND lastName = ?2", Employee.class);
        query.setParameter(1, "Jean")
            .setParameter(2, "Smith");

        assertThat(query.list()).isNotNull();
    }

}
