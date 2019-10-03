package com.yanfei.springFrame.Config;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class RequestLimitContract {
	private static final Logger logger = LoggerFactory.getLogger("requestLimitLogger");
	private Map<String, Integer> redisTemplate = new HashMap<>();
	
	@Before("within(@org.springframework.web.bind.annotation.RestController *) && @annotation(limit)")
	public void requestLimit(final JoinPoint joinPoint, RequestLimit limit) throws RequestLimitException {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String ip = request.getLocalAddr();
		String url = request.getRequestURL().toString();
		String key = "req_limit_".concat(url).concat(ip);
		if (redisTemplate.get(key) == null || redisTemplate.get(key) == 0) {
			redisTemplate.put(key, 1);
		} else {
			redisTemplate.put(key, redisTemplate.get(key) + 1);
		}
		int count = redisTemplate.get(key);
		if (count > 0) {
			// 创建一个定时器
			Timer timer = new Timer();
			TimerTask timerTask = new TimerTask() {
				@Override
				public void run() {
					redisTemplate.remove(key);
				}
			};
			// 这个定时器设定在time规定的时间之后会执行上面的remove方法，也就是说在这个时间后它可以重新访问
			timer.schedule(timerTask, limit.time());
		}
		if (count > limit.count()) {
			logger.info("用户IP[" + ip + "]访问地址[" + url + "]超过了限定的次数[" + limit.count() + "]");
			throw new RequestLimitException();
		}
	}
}
