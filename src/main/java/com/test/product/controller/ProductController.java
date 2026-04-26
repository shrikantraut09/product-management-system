package com.test.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.test.product.entity.Products;
import com.test.product.service.ProductService;

import jakarta.validation.Valid;

@Controller
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping("/")
	public String getProducts(Model model) {

		model.addAttribute("products", new Products());

		return "index";
	}

	@GetMapping("/view")
	public String getAllProducts(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "2") int size,
			@RequestParam(defaultValue = "") String keyword, Model model) {

		Page<Products> productPage;

		if (keyword.isEmpty()) {
			productPage = productService.getPaginatedPages(page, size);
		} else {
			productPage = productService.searchProducts(keyword, page, size);
		}

		model.addAttribute("productList", productPage.getContent());
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", productPage.getTotalPages());
		model.addAttribute("keyword", keyword);

		return "view";
	}

	@PostMapping("/save")
	public String saveProducts(@Valid @ModelAttribute("products") Products products, BindingResult result) {

		if (result.hasErrors()) {
			return "index";
		}

		productService.saveProductsData(products);

		return "redirect:/view";
	}

	@GetMapping("/edit")
	public String editProductById(@RequestParam("pid") long id, Model model) {

		model.addAttribute("products", productService.getProduct(id));

		return "index";

	}

	@GetMapping("/delete")
	public String deleteProductBtId(@RequestParam("pid") long id) {

		productService.deleteProduct(id);

		return "redirect:/view";
	}

}
