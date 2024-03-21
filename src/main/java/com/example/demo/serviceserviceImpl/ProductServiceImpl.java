package com.example.demo.serviceserviceImpl;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.DAO.ProductRequestDto;
import com.example.demo.entity.Product;
import com.example.demo.exception.ProductIdNotFoundException;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;
import com.example.demo.utility.ResponseStructure;

@Service
public class ProductServiceImpl implements ProductService {


	
	//	ResponseStructure<Product> reponseStructure = new ResponseStructure<>();

	//	@Autowired
	private ProductRepository pr;
	private ResponseStructure<Product> responseStructure;
	private ResponseStructure<List<Product>> listOfProduct;

	public ProductServiceImpl(ProductRepository pr, ResponseStructure<Product> responseStructure,ResponseStructure<List<Product>> listOfProduct) {
		super();
		this.pr = pr;
		this.responseStructure = responseStructure;
		this.listOfProduct=listOfProduct;
	}
	
	
	@Override
	public ResponseEntity<ResponseStructure<Product>> svae(ProductRequestDto productRequestDto) {
		Product product = pr.save(mapToProduct(productRequestDto , new Product()));
		
		return ResponseEntity.ok(responseStructure.setStatusCode(HttpStatus.OK.value()).setMessage("Product DATA Saved Sucessfully").setData(product));
	}	
	
	
	private Product mapToProduct(ProductRequestDto productRequestDto, Product product) {
		product.setProductName(productRequestDto.getProductName());
		product.setProductBrand(productRequestDto.getProductBrand());
		product.setProductPrice(productRequestDto.getProductPrice());
		return product;
	}

	
	@Override
	public ResponseEntity<ResponseStructure<Product>> update(int productId, ProductRequestDto productRequestDto) {

		Product product = mapToProduct(productRequestDto , new Product());
		
		return pr.findById(productId).map(existingProduct ->{ product.setProductId(existingProduct.getProductId());
		existingProduct = pr.save(product);
		
		return ResponseEntity.ok(responseStructure.setStatusCode(HttpStatus.OK.value()).setMessage("Product Data Updated Sucessfully").setData(existingProduct));
		
		
		}).orElseThrow(()-> new ProductIdNotFoundException("Entered Product Id is Not Found"));
	}
	

	@Override
	public ResponseEntity<ResponseStructure<Product>> addProduct(Product product) {

		Product products = pr.save(product);

		ResponseStructure<Product> responseStructure = new ResponseStructure<>();
		responseStructure.setStatusCode(HttpStatus.CREATED.value());
		responseStructure.setMessage("Product Details Added Sucessfully");
		responseStructure.setData(products);

		return new ResponseEntity<ResponseStructure<Product>>(responseStructure, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<ResponseStructure<Product>> findProductById(int productId) {

		return pr.findById(productId).map(p -> {
			//			ResponseStructure<Product> reponseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.FOUND.value())
			.setMessage("Product Object Found Sucessfully").setData(p);
			return ResponseEntity.ok(responseStructure);
		}).orElseThrow(() -> new ProductIdNotFoundException("Product Id is Not Foud In DataBase"));

	}

	@Override
	public ResponseEntity<ResponseStructure<Product>> updateByProductId(int productId, Product updatedProduct) {



		return  pr.findById(productId).map(p->{
			updatedProduct.setProductId(p.getProductId());
			pr.save(updatedProduct);
			return  ResponseEntity.ok(responseStructure.setStatusCode(HttpStatus.OK.value()).setMessage("Product is UPDATED Sucessfully").
					setData(updatedProduct));
		}).orElseThrow(()-> new ProductIdNotFoundException("Entered Id is Not Found in DataBase"));

	}

	//		Optional<Product> opt = pr.findById(productId);
	//		if (opt.isPresent()) {
	//			Product existingProduct = opt.get();
	//			updatedProduct.setProductId(existingProduct.getProductId());
	//			pr.save(updatedProduct);
	//
	//			ResponseStructure<Product> reponseStructure = new ResponseStructure<>();
	//			reponseStructure.setStatusCode(HttpStatus.OK.value());
	//			reponseStructure.setMessage("Product Object UPDATED Sucessfully");
	//			reponseStructure.setData(updatedProduct);
	//
	//			return new ResponseEntity<ResponseStructure<Product>>(reponseStructure, HttpStatus.OK);
	//		} else {
	////			return null;
	//			throw new ProductIdNotFoundException("Entered Product Id Is Wrong Please Check");
	//		}
	//	}

	@Override
	public ResponseEntity<ResponseStructure<Product>> deleteProductById(int productId) {



		return pr.findById(productId).map(product-> {
			pr.delete(product);
			responseStructure.setStatusCode(HttpStatus.OK.value()).setMessage("Product DELETE Sucessfully").setData(product);
			return ResponseEntity.ok(responseStructure);
		}).orElseThrow(() -> new ProductIdNotFoundException("Product Id is Not Foud In DataBase"));



		//		Optional<Product> opt = pr.findById(productId);
		//		if (opt.isPresent()) {
		//			Product deleteProduct = opt.get();
		//			pr.delete(deleteProduct);
		//
		////			ResponseStructure<Product> reponseStructure = new ResponseStructure<>();
		//			reponseStructure.setStatusCode(HttpStatus.OK.value());
		//			reponseStructure.setMessage("Product Object DELETE Sucessfully");
		//			reponseStructure.setData(deleteProduct);
		//
		//			return new ResponseEntity<ResponseStructure<Product>>(reponseStructure, HttpStatus.OK);
		//		} else {
		////			return null;
		//			throw new ProductIdNotFoundException("Entered Product Id Is Wrong Please Check");
		//
		//		}
	}

	@Override
	public ResponseEntity<ResponseStructure<List<Product>>> findAllProduct() {

		return ResponseEntity.ok(listOfProduct.setStatusCode(HttpStatus.OK.value()).setMessage("All the PRODUCT FOUND sucessfully").setData(pr.findAll()));

	}

	//				return ResponseEntity.ok(listStructure.setMessage("Products found successfully").setData(productRepository.findAll()).setStatusCode(HttpStatus.OK.value()));

	//		List<Product> products = pr.findAll();
	//
	//		ResponseStructure<List<Product>> reponseStructure = new ResponseStructure<>();
	//		reponseStructure.setStatusCode(HttpStatus.FOUND.value());
	//		reponseStructure.setMessage("List OF Product SHOWS Sucessfully");
	//		reponseStructure.setData(products);
	//
	//		return new ResponseEntity<ResponseStructure<List<Product>>>(reponseStructure, HttpStatus.FOUND);
	//	}
}
