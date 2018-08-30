package org.infinispan.springboot.controller;

import org.infinispan.springboot.model.Product;
import org.infinispan.springboot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * Created by mcouliba on 29/05/2018.
 */
@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    /**
     the getAll method retrieves all food items in the database. This is mapped to hte GET rest action
     @return A List() of Reservation items
     **/
    @RequestMapping(method= RequestMethod.GET)
    public Product findByKey(@RequestParam("key") String key) throws IOException {
        return productService.find(key);
    }

    @RequestMapping(method= RequestMethod.POST)
    public Product insertByKey(@RequestParam("key") String key,@RequestBody Product value) throws IOException {
        return productService.insert(key, value);
    }
}
