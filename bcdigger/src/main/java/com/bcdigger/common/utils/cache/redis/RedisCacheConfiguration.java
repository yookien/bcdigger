package com.bcdigger.common.utils.cache.redis;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

/**
 * Redis缓存配置类
 * @author yookien
 *
 */
//@Configuration
@EnableCaching
public class RedisCacheConfiguration extends CachingConfigurerSupport{
	
	Logger logger = Logger.getLogger(RedisCacheConfiguration.class);

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.timeout}")
    private int timeout;

    @Value("${spring.redis.pool.max-idle}")
    private int maxIdle;
    
    @Value("${spring.redis.pool.min-idle}")
    private int minIdle;

    @Value("${spring.redis.pool.max-wait}")
    private long maxWaitMillis;

    /*@Value("${spring.redis.password}")
    private String password;*/
    
	/*@Autowired
    private JedisPoolConfig jedisPoolConfig;*/
	@Override
	public CacheManager cacheManager() {
		// TODO Auto-generated method stub
		return super.cacheManager();
	}

	@Override
	public KeyGenerator keyGenerator() {
		 return new KeyGenerator() {
			 @Override
	         public Object generate(Object target, Method method, Object... params) {
	         	StringBuilder sb = new StringBuilder();
	            sb.append(target.getClass().getName());
	            sb.append(method.getName());
	            for (Object obj : params) {
	            	sb.append(obj.toString());
	            }
	            return sb.toString();
	         }
	     };
	}

	@Override
	public CacheResolver cacheResolver() {
		// TODO Auto-generated method stub
		return super.cacheResolver();
	}

	@Override
	public CacheErrorHandler errorHandler() {
		// TODO Auto-generated method stub
		return super.errorHandler();
	}

	

    @Bean
    public JedisPool redisPoolFactory() {
        logger.info("JedisPool注入成功！！");
        logger.info("redis地址：" + host + ":" + port);
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMinIdle(minIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);

        //JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout, password);
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout);

        return jedisPool;
    }
}
