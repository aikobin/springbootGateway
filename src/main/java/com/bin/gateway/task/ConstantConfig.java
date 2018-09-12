package com.bin.gateway.task;

import com.google.common.util.concurrent.RateLimiter;


public class ConstantConfig {
	

	 //控制qps  2000次/s
	public static RateLimiter LIMITER =RateLimiter.create(2000);
	
}
