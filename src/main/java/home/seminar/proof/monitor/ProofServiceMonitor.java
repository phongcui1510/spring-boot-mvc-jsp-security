package home.seminar.proof.monitor;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ProofServiceMonitor {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProofServiceMonitor.class);
	
	@Before("execution(* home.seminar.proof.service.proof.ProofService.findAll(..))")
	public void logBefore(JoinPoint joinPoint) {
		
		LOGGER.info("logBefore() ProofService.findAll is running!");
		LOGGER.info("hijacked : " + joinPoint.getSignature().getName());
		LOGGER.info("******");

	}
}
