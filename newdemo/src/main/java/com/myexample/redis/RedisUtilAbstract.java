package com.myexample.redis;

import org.springframework.data.redis.core.RedisTemplate;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public abstract class RedisUtilAbstract {
	/* 二进制 RedisTemplate */
	public abstract RedisTemplate<String, Object> getBinRedisTemplate();

	/* String RedisTemplate */
	public abstract RedisTemplate<String, String> getStrRedisTemplate();

	public void setString(String key, String value) {
		this.getStrRedisTemplate().opsForValue().set(key, value);
	}

	public void setString(String key, String value, long expireTime) {
		this.getStrRedisTemplate().opsForValue().set(key, value, expireTime, TimeUnit.SECONDS);
	}

	public String getString(String key) {
		return (String) this.getStrRedisTemplate().opsForValue().get(key);
	}

	public void setObject(String key, Object value) {
		this.getBinRedisTemplate().opsForValue().set(key, value);
	}

	public void setObject(String key, Object value, long expireTime) {
		this.getBinRedisTemplate().opsForValue().set(key, value, expireTime, TimeUnit.SECONDS);
	}

	public <T> T getObject(String key, Class<T> cls) {
		return cls.cast(this.getBinRedisTemplate().opsForValue().get(key));
	}

	public void hashPutKVStr(String key, String hashKey, String value) {
		this.getStrRedisTemplate().opsForHash().put(key, hashKey, value);
	}

	public void hashPutWithMapStr(String key, Map<String, String> map) {
		this.getStrRedisTemplate().opsForHash().putAll(key, map);
	}

	public String hashGetKVStr(String key, String hashKey) {
		return (String) this.getStrRedisTemplate().opsForHash().get(key, hashKey);
	}

	public Map<Object, Object> hashGetWithMapStr(String key) {
		return this.getStrRedisTemplate().opsForHash().entries(key);
	}

	public void hashPutKVObj(String key, String hashKey, Object value) {
		this.getBinRedisTemplate().opsForHash().put(key, hashKey, value);
	}

	public void hashPutWithMapObj(String key, Map<Object, Object> map) {
		this.getBinRedisTemplate().opsForHash().putAll(key, map);
	}

	public <T> T hashGetKVObj(String key, String hashKey, Class<T> cls) {
		return cls.cast(this.getBinRedisTemplate().opsForHash().get(key, hashKey));
	}

	public Map<Object, Object> hashGetWithMapObj(String key) {
		return this.getBinRedisTemplate().opsForHash().entries(key);
	}
	
	public Long hashLength(String key) {
		return this.getBinRedisTemplate().opsForHash().size(key);
	}

	public Long listLeftPush(String key, String value) {
		return this.getStrRedisTemplate().opsForList().leftPush(key, value);
	}
	public Long listLeftPushAll(String key, List<String> value) {
		return this.getStrRedisTemplate().opsForList().leftPushAll(key, value);
	}
	public Long listRightPush(String key, String value) {
		return this.getStrRedisTemplate().opsForList().rightPush(key, value);
	}
	public Long listRightPushAll(String key, List<String> value) {
		return this.getStrRedisTemplate().opsForList().rightPushAll(key, value);
	}
	public String listLeftPop(String key) {
		return this.getStrRedisTemplate().opsForList().leftPop(key);
	}

	public String listRightPop(String key) {
		return this.getStrRedisTemplate().opsForList().rightPop(key);
	}

	public List<String> listRange(String key, long start, long end) {
		return this.getStrRedisTemplate().opsForList().range(key, start, end);
	}

	/*public Long listLeftPush(String key, Object value) {
		return this.getBinRedisTemplate().opsForList().leftPush(key, value);
	}
	public Long listLeftPushAll(String key, List<Object> value) {
		return this.getBinRedisTemplate().opsForList().leftPushAll(key, value);
	}
	public Long listRightPush(String key, Object value) {
		return this.getBinRedisTemplate().opsForList().rightPush(key, value);
	}
	public Long listRightPushAll(String key, List<Object> value) {
		return this.getBinRedisTemplate().opsForList().rightPushAll(key, value);
	}

	public <T> T listLeftPop(String key, Class<T> cls) {
		return cls.cast(this.getBinRedisTemplate().opsForList().leftPop(key));
	}

	public <T> T listRightPop(String key, Class<T> cls) {
		return cls.cast(this.getBinRedisTemplate().opsForList().rightPop(key));
	}

	public List<Object> listRange(String key, long start, long end) {
		return this.getBinRedisTemplate().opsForList().range(key, start, end);
	}*/
	
	public Long listLength(String key){
		return this.getBinRedisTemplate().opsForList().size(key);
	}

	public Long increment(String key, long delta) {
		return this.getBinRedisTemplate().opsForValue().increment(key, delta);
	}

	public Long increment(String key) {
		return increment(key, 1);
	}

	public boolean hasKey(String key) {
		return this.getBinRedisTemplate().hasKey(key);
	}

	public boolean hasHashKV(String key, String hashKey) {
		return this.getBinRedisTemplate().opsForHash().hasKey(key, hashKey);
	}

	public void delete(String key) {
		this.getBinRedisTemplate().delete(key);
	}

	public void deleteHashKV(String key, String hashKey) {
		this.getBinRedisTemplate().opsForHash().delete(key, hashKey);
	}

	public boolean expire(String key, long expireTime) {
		return this.getBinRedisTemplate().expire(key, expireTime, TimeUnit.SECONDS);
	}

	public boolean expireAT(String key, Date date) {
		return this.getBinRedisTemplate().expireAt(key, date);
	}

	public long getExpire(String key) {
		return this.getBinRedisTemplate().getExpire(key, TimeUnit.SECONDS);
	}
	
	/*protected String getCacheKey(String prefix){
		StringBuilder key = new StringBuilder(prefix);
		return key.toString();
	}*/
}
