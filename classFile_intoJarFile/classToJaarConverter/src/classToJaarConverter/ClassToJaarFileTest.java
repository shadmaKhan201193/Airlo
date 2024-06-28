package classToJaarConverter;

import java.lang.reflect.Method;

@Aspect
@Component
@Service
public class ClassToJaarFileTest {
	
	
	
	@Pointcut("execution(* com.itl.controller.*.create*(..))")
	public void loggingPointCut() {
		logger.info("before aspects aspects");
	}

	@After("loggingPointCut()")
	public void around(JoinPoint joinPoint) throws Throwable {

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
