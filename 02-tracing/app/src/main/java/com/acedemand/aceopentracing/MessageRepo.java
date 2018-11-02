package com.acedemand.aceopentracing;

import org.springframework.cloud.sleuth.annotation.NewSpan;
import org.springframework.stereotype.Component;

@Component
public class MessageRepo {
    @NewSpan(name = "DoSth")
    public void doSth() throws  InterruptedException{
        Thread.sleep(1000);
    }

}
