package com.bcdigger.common.utils.cache.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


@Component
@Configuration
@ConfigurationProperties(prefix="spring.redis")
public class RedisClusterProperties {
	
	@Value("${spring.redis.expireSeconds}")
	private int   expireSeconds;
	@Value("${spring.redis.cluster.nodes}")
    private String clusterNodes;
	@Value("${spring.redis.password}")
    private String password;
	@Value("${spring.redis.commandTimeout}")
    private int    commandTimeout;
	
	@Value("${spring.redis.pool.max-active}")
    private int  maxActive;
	
	@Value("${spring.redis.timeout}")
    private int timeout;

    @Value("${spring.redis.pool.max-idle}")
    private int maxIdle;
    
    @Value("${spring.redis.pool.min-idle}")
    private int minIdle;

    @Value("${spring.redis.pool.max-wait}")
    private long maxWaitMillis;
	
	public int getExpireSeconds() {
		return expireSeconds;
	}
	public String getClusterNodes() {
		return clusterNodes;
	}
	public String getPassword() {
		return password;
	}
	public int getCommandTimeout() {
		return commandTimeout;
	}
	public int getMaxActive() {
		return maxActive;
	}
	public int getTimeout() {
		return timeout;
	}
	public int getMaxIdle() {
		return maxIdle;
	}
	public int getMinIdle() {
		return minIdle;
	}
	public long getMaxWaitMillis() {
		return maxWaitMillis;
	}
	
	
	
	
    
}
