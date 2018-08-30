package org.infinispan.springboot.service;

import org.infinispan.springboot.model.Product;

public interface ProductService {

    public Product find(String key);

    public Product insert(String key, Product value);

    public Product insertWithTTL(String key, Product value, Long ttl);
}