package com.bcdigger.common.utils.cache.redis;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
@ConditionalOnClass({JedisCluster.class})
@EnableConfigurationProperties(RedisClusterProperties.class)
public class JedisClusterConfig {
 
	@Autowired
    private RedisClusterProperties redisClusterProperties;
 
    /**
     * 注意：
     * 这里返回的JedisCluster是单例的，并且可以直接注入到其他类中去使用
     * @return
     */
	@Bean
    public JedisCluster getJedisCluster() {
		
		JedisPoolConfig config = new JedisPoolConfig();
		config =new JedisPoolConfig();
		config.setMaxTotal(redisClusterProperties.getMaxActive());//设置最大连接数  
		config.setMaxIdle(redisClusterProperties.getMaxIdle()); //设置最大空闲数 
		config.setMaxWaitMillis(redisClusterProperties.getMaxWaitMillis());//设置超时时间  
		config.setMinIdle(redisClusterProperties.getMinIdle());
	
		config.setTestOnBorrow(true);
		       
        String[] serverArray = redisClusterProperties.getClusterNodes().split(",");//获取服务器数组(这里要相信自己的输入，所以没有考虑空指针问题)
        Set<HostAndPort> nodes = new HashSet<>();
 
        for (String ipPort : serverArray) {
            String[] ipPortPair = ipPort.split(":");
            nodes.add(new HostAndPort(ipPortPair[0].trim(), Integer.valueOf(ipPortPair[1].trim())));
        }
 
        return new JedisCluster(nodes,config);//需要密码连接的创建对象方式
    }
 
}