package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class SomeService /*implements ApplicationContextAware */{

	final static Logger logger = LoggerFactory.getLogger("demo");
	private SomeService service = this;

	public void t1() {
		logger.info("t1 current thread id: {}", Thread.currentThread().getId());
	}

	@Async
	public void t2() {
		logger.info("t2 current thread id: {}", Thread.currentThread().getId());
	}

	public void t3() {
		logger.info("t3 current thread id: {}", Thread.currentThread().getId());
		service.t2();
	}

//	@Override
//	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//		service = applicationContext.getBean(SomeService.class);
//	}
}
