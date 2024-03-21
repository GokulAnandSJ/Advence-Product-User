package com.example.demo.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.demo.DAO.ProductRequestDto;
import com.example.demo.entity.Product;
import com.example.demo.utility.ResponseStructure;

public interface ProductService {
	

	public ResponseEntity<ResponseStructure<Product>> svae(ProductRequestDto productRequestDto);
	
	public abstract ResponseEntity<ResponseStructure<Product>> addProduct(Product product);
	
	public ResponseEntity<ResponseStructure<Product>> findProductById(int productId);

	public ResponseEntity<ResponseStructure<Product>> updateByProductId(int productId , Product updatedProduct);
	
	public ResponseEntity<ResponseStructure<Product>> deleteProductById(int productId);
	
	public ResponseEntity<ResponseStructure<List<Product>>> findAllProduct();
	
	public ResponseEntity<ResponseStructure<Product>> update(int productId , ProductRequestDto productRequestDto);
//	ResponseEntity<ResponseStructure<ProductEntity>> update(int productId, ProductEntityRequestDto productRequestDto);
	
}
