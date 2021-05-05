package com.lucianoortizsilva.gateway.config;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Configuration;

import com.google.common.cache.CacheBuilder;

import lombok.RequiredArgsConstructor;



@Configuration
@EnableCaching
@RequiredArgsConstructor
public class CachingConfig extends CachingConfigurerSupport {

	@Override
	public CacheManager cacheManager() {
	  ConcurrentMapCacheManager cacheManager = new ConcurrentMapCacheManager() {
		   @Override
		   protected Cache createConcurrentMapCache(final String name) {
		    return new ConcurrentMapCache(name, CacheBuilder.newBuilder().expireAfterAccess(1, TimeUnit.MINUTES).maximumSize(999).build().asMap(), false);
		   }
	  };
	  cacheManager.setCacheNames(Arrays.asList(CacheConstant.CACHE_ALUNO, CacheConstant.CACHE_PROFESSOR, CacheConstant.CACHE_AVALIACOES));
	  return cacheManager;
	}
	
}