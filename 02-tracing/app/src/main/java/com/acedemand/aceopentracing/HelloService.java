package com.acedemand.aceopentracing;


import brave.Span;
import brave.Tracer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by XTL on 8/14/2014.
 */
@RequestMapping("/api/**")
@RestController
public class HelloService {

    @Autowired
    private MessageRepo messageRepo;

    @Autowired
    private Tracer tracer;


    private void enrichMessage(){
        Span span = tracer.nextSpan().name("enrichMessage").start();
        try(Tracer.SpanInScope spanInScope = tracer.withSpanInScope(span)){
            System.out.println(" Enriching Message "  );
        }finally {
            span.finish();
        }
        System.out.println("Message enriched");
    }

    @RequestMapping(method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Message get() throws InterruptedException {
        messageRepo.doSth();
        enrichMessage();
        return new Message("green", 10, 7);
    }
}