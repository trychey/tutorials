package com.baeldung.disetter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.baeldung.disetter.model.Bike;
import com.baeldung.disetter.model.Engine;
import com.baeldung.disetter.model.Tyre;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:disetter.xml" })
public class SetterDIXMLIntegrationTest implements ApplicationContextAware {
    private ApplicationContext applicationContext;
    
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
        
    @Test
    public void whenC_ThenDependencyResolved() {
        Engine engine = (Engine) applicationContext.getBean("engine");
        Tyre tyre = (Tyre) applicationContext.getBean("tyre");
        Bike bike = (Bike) applicationContext.getBean("Bike");
        
        assertNotNull(engine);
        assertNotNull(tyre);
        assertNotNull(bike);
        
        assertEquals("Engine of type Internal Combustion Engine and displacement 150 cc.", engine.toString());
        assertEquals("Tyres of brand Bridgestone and size 167/82", tyre.toString());
        assertEquals("Bike characteristics:\nEngine of type Internal Combustion Engine and displacement 150 cc.\nTyres of brand Bridgestone and size 167/82", bike.toString());
    }
}
