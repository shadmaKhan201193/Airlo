package com.products.aspect;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Aspect
@Service
public class TestClassForJaar {
	
	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;
	
	String TOPIC_NAME_TEST="test1234";
	
	@Pointcut("execution(* com.itl.controller.*.create*(..))")
	public void loggingPointCut() {
		System.out.println("before aspects aspects");
	
	}

	@After("loggingPointCut()")
	public void around(JoinPoint joinPoint) throws Throwable {
		System.out.println("inside around method");
		Object obj = joinPoint.getArgs()[0];
		  Class<?> c = obj.getClass();
		  Method methods =c.getDeclaredMethod("getPrimaryKey",null);
		  methods.setAccessible(true);
		  Object object=methods.invoke(obj, null);
		
		if (null!=object) {
			
			this.kafkaTemplate.send(OmniConstants.TOPIC_NAME_TEST, object);

		}

	}
}

