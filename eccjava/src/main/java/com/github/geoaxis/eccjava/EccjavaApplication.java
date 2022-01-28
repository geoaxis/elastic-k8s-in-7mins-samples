package com.github.geoaxis.eccjava;

import co.elastic.apm.attach.ElasticApmAttacher;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import java.time.Duration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@SpringBootApplication
@EnableCaching
public class EccjavaApplication {

  public static void main(String[] args) {
    if(
        System.getenv("ELASTIC_APM_SECRET_TOKEN") != null &&
        System.getenv("ELASTIC_APM_SERVER_URL") != null &&
        System.getenv("ELASTIC_APM_SERVICE_NAME") != null
    ) {
      ElasticApmAttacher.attach();
    }

    SpringApplication.run(EccjavaApplication.class, args);
  }

  @Bean
  public CommonsRequestLoggingFilter requestLoggingFilter() {
    CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter();
    loggingFilter.setIncludeClientInfo(true);
    loggingFilter.setIncludeQueryString(true);
    loggingFilter.setIncludePayload(true);
    loggingFilter.setIncludeHeaders(true);
    return loggingFilter;
  }

  @Bean
  public OpenAPI springShopOpenAPI() {
    return new OpenAPI()
        .info(new Info().title("ECCJava")
            .description("A demo api using Java/Spring")
            .version("v0.0.1"));
  }

  @Bean
  public RestTemplate getRestTemplate() {
    return new RestTemplate();
  }

  @Bean
  public RedisCacheConfiguration cacheConfiguration() {
    return RedisCacheConfiguration.defaultCacheConfig()
        .entryTtl(Duration.ofSeconds(10))
        .disableCachingNullValues()
        .serializeValuesWith(SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
  }

  @Bean
  public RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer() {
    return (builder) -> builder
        .withCacheConfiguration("stocks",
            RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(10)));
  }

}
