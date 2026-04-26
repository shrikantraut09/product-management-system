package com.test.product.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	// Custome Exception
//	@ExceptionHandler(ProductNotFoundException.class)
//	public String handleProductNotFound(ProductNotFoundException e, Model model) {
//
//		model.addAttribute("message", e.getMessage());
//
//		return "error";
//
//	}

	@ExceptionHandler(Exception.class)
	public String handleProductNotFound(ProductNotFoundException e, Model model) {

		model.addAttribute("errorMessage", e.getMessage());

		return "error";
	}

}
