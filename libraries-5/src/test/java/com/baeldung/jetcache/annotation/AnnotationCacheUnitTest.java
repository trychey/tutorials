package com.baeldung.jetcache.annotation;

import com.alicp.jetcache.anno.CacheInvalidate;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.CacheUpdate;
import com.alicp.jetcache.anno.Cached;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@SpringBootApplication
@EnableMethodCache(basePackages = "com.baeldung.jetcache")
public class AnnotationCacheUnitTest {
    @Component
    public static class TestService {
        private int callCount = 0;

        @Cached(name = "doSomething", cacheType = CacheType.LOCAL, key = "args[0]")
        public String doSomething(int i) {
            this.callCount = this.callCount + 1;

            return "Hello: " + i;
        }

        @CacheInvalidate(name = "doSomething", key = "args[0]")
        public void invalidateSomething(int i) {
        }

        @CacheUpdate(name = "doSomething", key = "args[0]", value = "args[1]")
        public void updateSomething(int i, String value) {
        }

        public int getCallCount() {
            return callCount;
        }

        void reset() {
            callCount = 0;
        }
    }

    @Configuration
    public static class TestConfiguration {
        @Bean
        public TestService testService() {
            return new TestService();
        }
    }

    @Autowired
    private TestService testService;

    @BeforeEach
    void setup() {
        testService.reset();
    }

    @Test
    void testMethodCache() {
        assertEquals("Hello: 1", testService.doSomething(1));
        assertEquals(1, testService.getCallCount());

        assertEquals("Hello: 1", testService.doSomething(1));
        // Cache hit - call count didn't increase.
        assertEquals(1, testService.getCallCount());

        assertEquals("Hello: 2", testService.doSomething(2));
        // Cache miss - call count did increase.
        assertEquals(2, testService.getCallCount());
    }

    @Test
    void testCacheInvalidation() {
        assertEquals("Hello: 10", testService.doSomething(10));
        assertEquals(1, testService.getCallCount());

        testService.invalidateSomething(10);

        assertEquals("Hello: 10", testService.doSomething(10));
        // Cache miss - entry was invalidated.
        assertEquals(2, testService.getCallCount());
    }

    @Test
    void testCacheUpdate() {
        assertEquals("Hello: 20", testService.doSomething(20));
        assertEquals(1, testService.getCallCount());

        testService.updateSomething(20, "Other");

        // Value returned wasn't generated by the method but provided by us.
        assertEquals("Other", testService.doSomething(20));
        // Cache hit - call count didn't increase.
        assertEquals(1, testService.getCallCount());
    }
}
