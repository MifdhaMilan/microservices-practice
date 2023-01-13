package com.mifdhamilan.ProductService.service;

import com.mifdhamilan.ProductService.model.ProductRequest;
import com.mifdhamilan.ProductService.model.ProductResponse;

public interface ProductService {
    long addProduct(ProductRequest productRequest);

    ProductResponse getProductById(long productId);
}
