package com.bcdigger.core.mybatis.cache.redis;

import org.apache.ibatis.cache.decorators.LoggingCache;
/**
 * ClassName: MybatiesRedisCache <br/>
 * Function:  详细说明 请看  com.bcdigger.utils.cache.redis.mybatisImpl.RedisCache <br/>
 * date: 2014-8-13 下午5:20:22 <br/>
 * 
 * @author yookien
 */
public class MybatiesRedisCache  extends LoggingCache  {

	public MybatiesRedisCache(String id) {  
        super(new RedisCache(id));  
	}
}
