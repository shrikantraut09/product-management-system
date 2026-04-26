package com.test.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.test.product.entity.Products;
import com.test.product.exception.ProductNotFoundException;
import com.test.product.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public void saveProductsData(Products products) {

		productRepository.save(products);

	}


	public Products getProduct(long id) {

		return productRepository.findById(id)
				.orElseThrow(() -> new ProductNotFoundException("Product Not Found this Id: " + id));
	}

	public void deleteProduct(long id) {

		productRepository.deleteById(id);
	}

	public Page<Products> getPaginatedPages(int page, int size) {
		return productRepository.findAll(PageRequest.of(page, size));
	}

	public Page<Products> searchProducts(String keyword, int page, int size) {
	    return productRepository
	        .findByProductNameContainingIgnoreCase(keyword, PageRequest.of(page, size));
	}
}
