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
	private int    expireSeconds;
	@Value("${spring.redis.cluster.nodes}")
    private String clusterNodes;
	@Value("${spring.redis.password}")
    private String password;
	@Value("${spring.redis.commandTimeout}")
    private int    commandTimeout;
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
	
	
	
	
    
}
