package th.co.the1.echo.adapter.cache;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import th.co.the1.echo.application.port.out.EchoCachePort;

import java.util.concurrent.TimeUnit;

import static th.co.the1.echo.adapter.cache.EchoCacheConfiguration.KEY;

@Component
@RequiredArgsConstructor
public class EchoCacheService implements EchoCachePort {

    private final RedisTemplate<String, String> redisTemplate;

    private final CacheConfigurationProperties configurationProperties;

    @Override
    public String get(String key) {
        return (String) redisTemplate.opsForHash().get(KEY, key);
    }

    @Override
    public void put(String key, String value) {
        redisTemplate.opsForHash().put(KEY, key, value);
        redisTemplate.expire(KEY, configurationProperties.getCacheTimeout(), TimeUnit.MINUTES);
    }
}
