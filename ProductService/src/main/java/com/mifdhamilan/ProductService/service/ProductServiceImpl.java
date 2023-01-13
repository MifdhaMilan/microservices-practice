package com.mifdhamilan.ProductService.service;

import com.mifdhamilan.ProductService.entity.Product;
import com.mifdhamilan.ProductService.exception.ProductServiceCustomException;
import com.mifdhamilan.ProductService.model.ProductRequest;
import com.mifdhamilan.ProductService.model.ProductResponse;
import com.mifdhamilan.ProductService.repository.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;
    @Override
    public long addProduct(ProductRequest productRequest) {
        log.info("Adding product...");

        Product product
                =Product.builder()
                .productName(productRequest.getProductName())
                .quantity(productRequest.getQuantity())
                .price(productRequest.getPrice())
                .build();
        productRepository.save(product);

        log.info("Product Created");

        return product.getProductId();
    }

    @Override
    public ProductResponse getProductById(long productId) {
        log.info("Get the product for the product Id: {}"+productId);

        Product product
                =productRepository.findById(productId)
                .orElseThrow(() -> new ProductServiceCustomException("product with the given Id not found", "PRODUCT_NOT_FOUND"));

        ProductResponse productResponse
                =new ProductResponse();
        BeanUtils.copyProperties(product, productResponse);

        return productResponse;
    }
}
