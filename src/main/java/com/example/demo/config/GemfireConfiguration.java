package com.example.demo.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.gemfire.CacheFactoryBean;
import org.springframework.data.gemfire.repository.config.EnableGemfireRepositories;

@Configuration
@EnableGemfireRepositories(basePackages = "com.example.demo")
public class GemfireConfiguration {
	
	@Bean
	Properties gemfireProperties() {
		Properties gemfireProperties = new Properties();
		gemfireProperties.setProperty("name", "SpringDataGemFireApplication");
		gemfireProperties.setProperty("name2", "SpringDataGemFir2222n");
		gemfireProperties.setProperty("mcast-port", "0");
        gemfireProperties.setProperty("log-level", "config");
        gemfireProperties.setProperty("log-level1", "config");
        gemfireProperties.setProperty("log-level2", "config");
        return gemfireProperties;
	}
	
	@Bean
    CacheFactoryBean gemfireCache() {
        CacheFactoryBean gemfireCache = new CacheFactoryBean();
        gemfireCache.setClose(true);
        gemfireCache.setProperties(gemfireProperties());
        return gemfireCache;
    }


}
