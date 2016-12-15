package home.seminar.proof.monitor;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UserServiceMonitor {

private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceMonitor.class);
	
	@Before("execution(* home.seminar.proof.service.user.UserService.getAllUsers(..))")
	public void logBefore(JoinPoint joinPoint) {
		
		LOGGER.info("logBefore() UserService.getAllUsers is running!");
		LOGGER.info("hijacked : " + joinPoint.getSignature().getName());
		LOGGER.info("******");

	}
}
