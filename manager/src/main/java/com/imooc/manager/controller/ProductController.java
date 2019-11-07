package com.imooc.manager.controller;

import com.imooc.entity.Product;
import com.imooc.manager.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 产品
 */
@RestController
@RequestMapping("/products")
public class ProductController {

    private static Logger logger = LoggerFactory.getLogger(ProductController.class);
    @Autowired
    private ProductService productService;

    @PostMapping("/add")
    public Product addProduct(@RequestBody Product product) {
        logger.info("创建产品，参数{}", product);
        Product result = productService.addProduct(product);
        logger.info("创建产品，结果{}", result);
        return result;

    }


    @GetMapping("/{id}")
    public Product findOne(@PathVariable(name = "id") String id) {
        logger.info("查询单个产品，id={}", id);
        Product product = productService.findOne(id);
        logger.info("查询单个产品，product={}", product);
        return product;
    }

}
