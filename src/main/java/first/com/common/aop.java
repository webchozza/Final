package first.com.common;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class aop {

	Logger log = LoggerFactory.getLogger(getClass());

	public Object log(ProceedingJoinPoint joinPoint) throws Throwable {

		String signatureString = joinPoint.getSignature().toShortString();

		if(log.isDebugEnabled()){ log.debug(signatureString + "   before");	}
		if(log.isInfoEnabled()){ log.info(signatureString + "   before"); }
		if(log.isErrorEnabled()){ log.error(signatureString + "   before"); }
		
//		If the log does not appear, uncomment and run the code below.(The trace level records all logs.)
		if(log.isTraceEnabled()){ log.trace(signatureString + "   before"); }
			
		try {
			Object result = joinPoint.proceed();
			return result;

		} finally {
			
			if(log.isDebugEnabled()){ log.debug(signatureString + "   after");	}
			if(log.isInfoEnabled()){ log.info(signatureString + "   after"); }
			if(log.isErrorEnabled()){ log.error(signatureString + "   after"); }
			if(log.isTraceEnabled()){ log.trace(signatureString + "   after"); }
		}
	}
}