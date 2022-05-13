package br.com.phelto.readme.postagens.infrastructure.cache;

import io.lettuce.core.ReadFrom;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStaticMasterReplicaConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.Duration;

@Configuration
public class RedisCustomConfiguration extends CachingConfigurerSupport {

    @Bean
    public LettuceConnectionFactory lettuceConnectionFactory(){
        RedisStaticMasterReplicaConfiguration cache = new RedisStaticMasterReplicaConfiguration("localhost",6379);
        cache.addNode("localhost",6379);
        LettuceClientConfiguration client = LettuceClientConfiguration
                                            .builder()
                .commandTimeout(Duration.ofMinutes(1))
                .readFrom(ReadFrom.MASTER_PREFERRED)
                .build();

        return new LettuceConnectionFactory(cache,client);

    }

    @Bean(name="redisTemplate")
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }

    @Bean(name = "cacheManager3Horas")
    public CacheManager cacheManager3Horas(RedisConnectionFactory redisConnectionFactory){
        return RedisCacheManager.builder(redisConnectionFactory)
                .cacheDefaults(RedisCacheConfiguration.defaultCacheConfig()
                        .entryTtl(Duration.ofHours(3)))
                .build();
    }

}
