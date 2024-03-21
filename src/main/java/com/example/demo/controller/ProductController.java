package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.DAO.ProductRequestDto;
import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;
import com.example.demo.utility.ErrorStructure;
import com.example.demo.utility.ResponseStructure;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
public class ProductController {

//	@Autowired
	private ProductService productService;

	public ProductController(ProductService productService) {
		super();
		this.productService = productService;
	}

	@Operation(description = "The endPoint is used to the add a new product to the database ", responses = {
			@ApiResponse(responseCode = "200", description = "Product Saved Sucessfully"),
			@ApiResponse(responseCode = "400", description = "Invalid Input") })

	@PostMapping("/products")
	public ResponseEntity<ResponseStructure<Product>> save(@RequestBody ProductRequestDto productRequestDto) {
		return productService.svae(productRequestDto);
	}

//	@PostMapping("/products")
//	public ResponseEntity<ResponseStructure<Product>> addProduct(@RequestBody Product product){
//
//		return productService.addProduct(product);
//	}

	@PutMapping("/product/{productId}")
	public ResponseEntity<ResponseStructure<Product>> update(@PathVariable int productId,
			@RequestBody ProductRequestDto productRequestDto) {
		return productService.update(productId, productRequestDto);
	}

//	@PutMapping("/product/{productId}")
//
//	public ResponseEntity<ResponseStructure<Product>> updateProductByItId(@PathVariable int productId , @RequestBody Product product ) {
//		return productService.updateByProductId(productId, product);
//	}

	@Operation(description = "The endPoint is used to the add a new product to the database ", responses = {
			@ApiResponse(responseCode = "200", description = "Product DELETE Sucessfully"),
			@ApiResponse(responseCode = "400", description = "Invalid Input") })

	@DeleteMapping("/product/deleteProductById/{productId}")

	public ResponseEntity<ResponseStructure<Product>> deleteProductById(@PathVariable int productId) {
		return productService.deleteProductById(productId);
	}

	@Operation(description = "The endPoint is used to the add a new product to the database ", responses = {
			@ApiResponse(responseCode = "200", description = "Product Found"),
			@ApiResponse(responseCode = "400", description = "Product not Found By Given Id", content = {
					@Content(schema = @Schema(implementation = ErrorStructure.class)) }) })
	@GetMapping("products/{productId}")
	public ResponseEntity<ResponseStructure<Product>> findProductById(@PathVariable int productId) {
		return productService.findProductById(productId);
	}

	@GetMapping("/products")

	public ResponseEntity<ResponseStructure<List<Product>>> findAllProducts() {
		return productService.findAllProduct();

	}

}
