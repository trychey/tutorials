package com.baeldung.hibernate_parameters;

import junit.framework.TestCase;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

public class NamedParameterUnitTest extends TestCase {
    private SessionFactory sessionFactory;

    @Override
    protected void setUp() throws Exception {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(new Event("Event 1"));
            session.save(new Event("Event 2"));
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    @Override
    protected void tearDown() throws Exception {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

    public void testNamedParameter() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
            Query<Event> query = session.createQuery("from Event E WHERE E.title = :eventTitle", Event.class);

            // This binds the value "Event1" to the parameter :eventTitle
            query.setParameter("eventTitle", "Event 1");

            assertEquals(1, query.list().size());
        session.getTransaction().commit();
        session.close();
    }
}
