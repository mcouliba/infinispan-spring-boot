package org.infinispan.springboot.service;

import org.infinispan.springboot.model.Product;
import org.infinispan.springboot.repository.DefaultCacheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private DefaultCacheRepository defaultCacheRepository;

    public Product find(String key){
        return defaultCacheRepository.find(key);
    }

    public Product insert(String key, Product value) {return defaultCacheRepository.insert(key, value); }
}
