package com.olasharing.trc.leaf.repository;

import com.olasharing.trc.leaf.SequenceRepository;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * redis can support increment and so fast
 *
 * @author stone
 */
public class RedisSequenceRepository implements SequenceRepository {

    private StringRedisTemplate redisTemplate;

    public RedisSequenceRepository(RedisConnectionFactory redisConnectionFactory) {
        redisTemplate = new StringRedisTemplate(redisConnectionFactory);
    }

    static String rawKey(long timestamp) {
        return Constants.REDIS_KEY_PREFIX + timestamp;
    }

    @Override
    public long next(String bizCode, long timestamp, int delta) {
        String key = rawKey(timestamp);
        String hashKey = bizCode;
        Long next = redisTemplate.opsForHash().increment(key, hashKey, delta);
        redisTemplate.expire(key, Constants.REDIS_KEY_EXPIRE_HOURS, TimeUnit.HOURS);
        return next.longValue();
    }
}
