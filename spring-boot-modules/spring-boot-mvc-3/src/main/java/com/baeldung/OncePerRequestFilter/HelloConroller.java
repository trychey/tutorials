package com.baeldung.OncePerRequestFilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.async.DeferredResult;

import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Controller
public class HelloConroller implements AutoCloseable {

    private final ExecutorService executorService = Executors
            .newCachedThreadPool();

    private Logger logger = LoggerFactory.getLogger(HelloConroller.class);

    @GetMapping(path = "/greeting1")
    public DeferredResult<String> hello1(HttpServletResponse response) throws Exception{
        DeferredResult<String> deferredResult = new DeferredResult<>();
        executorService.submit(() -> perform(deferredResult));
        return deferredResult;
    }

    private void perform(DeferredResult<String> dr) {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        dr.setResult("OK");
    }

    @Override
    public void close() throws Exception {
        executorService.shutdownNow();
    }
}
