package com.zhcnnet.zenglish;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration.JedisPoolingClientConfigurationBuilder;

import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisConfig 
{
	@Value("${custom.redis.host}")
	private String host;
	
	@Value("${custom.redis.port}")
	private int port;
	
	@Value("${custom.redis.database}")
	private int database;
	
	@Value("${custom.redis.password}")
	private String password;
	
	@Value("${custom.redis.max-idle}")
	private int maxIdle;
	
	@Value("${custom.redis.max-total}")
	private int maxTotal;
	
	@Value("${custom.redis.max-wait-millis}")
	private long maxWaitMillis;
	
	
	private JedisPoolConfig getJedisPoolConfig()
	{
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxIdle(this.maxIdle);
		config.setMaxTotal(this.maxTotal);
		config.setMaxWaitMillis(this.maxWaitMillis);
		return config;
	}
	
	private RedisStandaloneConfiguration getRedisStandaloneConfiguration()
	{
		RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
		redisStandaloneConfiguration.setHostName(this.host);
		redisStandaloneConfiguration.setPort(this.port);
		redisStandaloneConfiguration.setDatabase(this.database);
		redisStandaloneConfiguration.setPassword(this.password);
		
		return redisStandaloneConfiguration;
	}
	
	@Bean
	public JedisConnectionFactory getJedisConnectionFactory()
	{
		JedisPoolingClientConfigurationBuilder jedisClientConfigurationBuilder = (JedisPoolingClientConfigurationBuilder) JedisClientConfiguration.builder();
		jedisClientConfigurationBuilder.poolConfig(getJedisPoolConfig());
		
		return new JedisConnectionFactory(getRedisStandaloneConfiguration(),jedisClientConfigurationBuilder.build());
	}
	
	@Bean
    public RedisTemplate<String,Object> redisTemplate(JedisConnectionFactory factory)
	{
        RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        template.setConnectionFactory(factory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new StringRedisSerializer());

        return template;
    }
}
