package com.baeldung.opentelemetry.repository;

import com.baeldung.opentelemetry.exception.ProductNotFoundException;
import com.baeldung.opentelemetry.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class ProductRepository {

    private final Logger logger = LoggerFactory.getLogger(ProductRepository.class);

    private final Map<Long, Product> productMap = new HashMap<>();

    @PostConstruct
    private void postConstruct() {
        this.setupRepo();
    }

    public Product getProduct(Long productId){
        logger.info("Getting Product from Product Repo With Product Id {}", productId);

        if(!productMap.containsKey(productId)){
            logger.error("Product Not Found for Product Id {}", productId);
            throw new ProductNotFoundException("Product Not Found");
        }

        return productMap.get(productId);
    }

    private void setupRepo() {
        Product product1 = getProduct(100001, "apple");
        productMap.put(100001L, product1);

        Product product2 = getProduct(100002, "pears");
        productMap.put(100002L, product2);

        Product product3 = getProduct(100003, "banana");
        productMap.put(100003L, product3);

        Product product4 = getProduct(100004, "mango");
        productMap.put(100004L, product4);

        Product product5 = getProduct(100005, "test");
        productMap.put(100005L, product5);
    }

    private static Product getProduct(int id, String name) {
        Product product1 = new Product();
        product1.setId(id);
        product1.setName(name);
        return product1;
    }
}
