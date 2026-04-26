package com.test.product.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
public class Products {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long productId;

	@NotBlank(message = "Product name is required")
	@Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
	private String productName;

	@NotNull(message = "Product price is required")
	@Positive(message = "Price must be greater than 0")
	private Double productPrice;

	@NotNull(message = "Product quantity is required")
	@Min(value = 1, message = "Quantity must be at least 1")
	@Max(value = 1000, message = "Quantity cannot exceed 1000")
	private Integer productQuantity;

	public Products() {
		super();
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}

	public Integer getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(Integer productQuantity) {
		this.productQuantity = productQuantity;
	}

}
