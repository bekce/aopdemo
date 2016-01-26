package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.context.annotation.EnableLoadTimeWeaving.AspectJWeaving;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * I have tried out the aspectj weaver with async annotation, to make a POC for
 * aspectj weaving support of spring. it failed. the aspectj support does not
 * work and @Async annotation is completely useless in this case.
 * 
 * It says @EnableAsync(mode = AdviceMode.ASPECTJ) should work with aspectj but
 * it simply does not. VM parameters: -Duser.language=en -Duser.country=us
 * -javaagent:aspectjweaver.jar -javaagent:spring-instrument.jar
 * 
 * mode = AdviceMode.PROXY works (only with external invocation, self invocation
 * does not go through proxy)
 */
@SpringBootApplication
@EnableAsync(mode = AdviceMode.ASPECTJ)
@EnableAspectJAutoProxy
// @EnableAutoConfiguration(exclude=AopAutoConfiguration.class)
// @EnableSpringConfigured
@EnableLoadTimeWeaving(aspectjWeaving = AspectJWeaving.ENABLED)
public class AopdemoApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(AopdemoApplication.class, args);

		SomeService someService = context.getBean(SomeService.class);
		someService.t1();
		someService.t2();
		someService.t3();

	}
}
