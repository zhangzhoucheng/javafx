
package com.zz.test.javafxmvn.commontool.redis;

import java.lang.reflect.Method;
import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;



/**
 * 
 * <note>
 * Desc： redis配置
 * 配置CacheManager,配置使用哪种cache，一个应用可以有多个CacheManager，而一个CacheManager下又可以有多个Cache
 * 配置RedisTemplate,访问redis,主要用于初始化redis
 * @author jld.zhangzhou
 * @email idiot_jillidan@163.com;
 * @re be willing to communicate
 * @refactor for jld
 * @datetime 2020-04-17 10:19:06
 * @location mobile base 3th,BeiJing 
 * version  1.0
 *  
 * @REVISIONS: 
 * Version 	        Date 		         Author             Location                   Description          
 * ------------------------------------------------------------------------------------------------------  
 * 1.0 		  2020-04-17 10:19:06    jld.zhangzhou     mobile base 3th,BeiJing      1.create the class            
 * </note>
 */

@Configuration
//开启 Spring Cache
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport{
	
	@Value("${cache.expireTime}")
    // 缓存超时时间
    private int cacheExpireTime;
	
	@Autowired
	private RedisCacheErrorHandler redisCacheErrorHandler;
	
	/**
     * 配置@Cacheable、@CacheEvict等注解在没有指定Key的情况下，key生成策略
     * 该配置作用于缓存管理器管理的所有缓存
     * 最终生成的key 为 cache类注解指定的cacheNames::类名:方法名#参数值1,参数值2...
     *
     * @return
     */
    @Bean
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuffer sb = new StringBuffer();
                sb.append(target.getClass().getName());
                sb.append(":");
                sb.append(method.getName());
                sb.append("#");
                for (Object obj : params) {
                    sb.append(obj.toString());
                    sb.append(",");
                }
                return sb.substring(0, sb.length() - 1);
            }
        };
    }
    
    /**
     * 配置缓存管理器
     */
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory factory) {
        // 配置 json 序列化器 - Jackson2JsonRedisSerializer
        Jackson2JsonRedisSerializer<Object> jacksonSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jacksonSerializer.setObjectMapper(objectMapper);

        //关键点，spring cache 的注解使用的序列化都从这来，没有这个配置的话使用的jdk自己的序列化，实际上不影响使用，只是打印出来不适合人眼识别
        RedisCacheConfiguration cacheConfig = RedisCacheConfiguration.defaultCacheConfig()
                // 将 key 序列化成字符串
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                // 将 value 序列化成 json
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jacksonSerializer))//value序列化方式
                // 设置缓存过期时间，单位秒
                .entryTtl(Duration.ofSeconds(cacheExpireTime))
                // 不缓存空值
                .disableCachingNullValues();

        return RedisCacheManager.builder(factory)
                .cacheDefaults(cacheConfig)
                .build();
    }

	
	 /**
     * 自定义 redisTemplate （方法名一定要叫 redisTemplate 因为 @Bean 是根据方法名配置这个bean的name的）
     * 默认的 RedisTemplate<K,V> 为泛型，使用时不太方便，自定义为 <String, Object>
     * 默认序列化方式为 JdkSerializationRedisSerializer 序列化后的内容不方便阅读，改为序列化成 json
     *
     * @param redisConnectionFactory
     * @return
     */
   /* @Bean
    RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        // 配置 json 序列化器 - Jackson2JsonRedisSerializer
        Jackson2JsonRedisSerializer jacksonSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jacksonSerializer.setObjectMapper(objectMapper);

        // 创建并配置自定义 RedisTemplateRedisOperator
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        // 将 key 序列化成字符串
        template.setKeySerializer(new StringRedisSerializer());
        // 将 hash 的 key 序列化成字符串
        template.setHashKeySerializer(new StringRedisSerializer());
        // 将 value 序列化成 json
        template.setValueSerializer(jacksonSerializer);
        // 将 hash 的 value 序列化成 json
        template.setHashValueSerializer(jacksonSerializer);
        template.afterPropertiesSet();
        return template;
    }*/

    /**
	 * 
	 * <pre>
	 * Desc  异常回调处理配置
	 * @return
	 * @author gaoyang
	 * @date   2017年7月27日 下午9:29:49
	 * </pre>
	 */
	@Bean
	public CacheErrorHandler cacheErrorHandler(){
		return redisCacheErrorHandler;
	}
}
