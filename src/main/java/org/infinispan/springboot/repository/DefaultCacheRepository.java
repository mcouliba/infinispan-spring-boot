package org.infinispan.springboot.repository;

import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.springboot.cache.RemoteCacheManagerFactory;
import org.infinispan.springboot.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class DefaultCacheRepository {

	private static final String CACHE_NAME = "default";

	private RemoteCache<String, Product> remoteCache;

	@Autowired
	public DefaultCacheRepository(RemoteCacheManagerFactory rcm) throws IOException {
	    List<Class> classes = new ArrayList<Class>();
        classes.add(Product.class);

        rcm.setProtobufConfig("product", classes);
		this.remoteCache = rcm.getcache(CACHE_NAME);
	}

	public Product find(String key){
		return remoteCache.get(key);
	}

	public Product insert(String key, Product value){
		return remoteCache.put(key, value);
	}

    public Product insertWithTTL(String key, Product value, Long ttl){
        return remoteCache.put(key, value, ttl, TimeUnit.SECONDS);
    }
}
