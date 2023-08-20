package org.example.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.example.transformer.StringTransformer;

@Aspect
public class LoggingAspect {
    @Before("execution(void StringTransformer.run()) && target(transformer)")
    public void beforeRun(StringTransformer transformer) {
        System.out.println("Running StringTransformer...");
    }

    @After("execution(* StringTransformer.log*(...)) && target(transformer)")
    public void afterLogMethod(StringTransformer transformer) {
        System.out.println("Logging aspect: Log method was called");
    }
}
