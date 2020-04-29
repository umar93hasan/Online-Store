package edu.northeastern.cs5200.controllers;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.northeastern.cs5200.models.Book;
import edu.northeastern.cs5200.models.Game;
import edu.northeastern.cs5200.models.Product;
import edu.northeastern.cs5200.models.ProductSeller;
import edu.northeastern.cs5200.models.Seller;
import edu.northeastern.cs5200.repos.ProductRepo;
import edu.northeastern.cs5200.repos.ProductSellerRepo;
import edu.northeastern.cs5200.repos.SellerRepo;

@RestController
public class ProductSellerController {
	
	
	@Autowired(required = true)
	private ProductSellerRepo productSellerRepo;
	
	@Autowired(required = true)
	private SellerRepo sellerRepo;
	
	@Autowired(required = true)
	private ProductRepo productRepo;
	
	@GetMapping("/api/productseller/insert/{keyword}/{keyword2}")
	public ProductSeller insertProductSeller(@PathVariable int keyword, @PathVariable String keyword2) {
		Seller newSeller = sellerRepo.findSellerByEmail(keyword2);
		Product newProduct = productRepo.findProductById(keyword);

		ProductSeller objProdSeller = new ProductSeller(newSeller, newProduct);
		//newSeller.addSellingProduct(objProdSeller);
		//newProduct.addProductSeller(objProdSeller);
		return productSellerRepo.save(objProdSeller);
		
	}
	
	/*@GetMapping("/api/sellers")
	public List<Seller> selectAllSellers() {
		return (List<Seller>) sellerRepo.findAll();
	}*/

	@GetMapping("/api/productseller/{email}")
	public List<Product> findProductsSellerByEmail(@PathVariable String email) {
		
		Seller seller = sellerRepo.findSellerByEmail(email);
		List<Product> lstProducts =  new ArrayList<>();
		
		for(ProductSeller objSP: productSellerRepo.findProductsSellerByEmail(seller)) {	
			lstProducts.add(objSP.getProduct());
		}			
		return lstProducts ;
	}
	

	@GetMapping("/api/productseller/{keyword}/{keyword2}")
	public List<ProductSeller> findProductByKeyword(@PathVariable String keyword, @PathVariable String keyword2) {
		
		List<ProductSeller> ps = (List<ProductSeller>) productSellerRepo.findAll();;
		List<ProductSeller> searchProduct = new ArrayList<>();
		Set<Integer> added = new HashSet<>();
		for(ProductSeller x:ps) {
			if(x.getProduct().getName().toLowerCase().contains(keyword.toLowerCase()) && !added.contains(x.getProduct().getId())) {
				if(keyword2.equalsIgnoreCase("game") && x.getProduct() instanceof Game || keyword2.equalsIgnoreCase("book") && x.getProduct() instanceof Book) {
						searchProduct.add(x);
						added.add(x.getProduct().getId());
					}
			}				
		}
		return searchProduct;
		
		/*List<ProductSeller> ps = (List<ProductSeller>) productSellerRepo.findAll();;
		List<Product> searchProduct = new ArrayList<>();
		for(ProductSeller x:ps) {
			if(x.getProduct().getName().toLowerCase().contains(keyword.toLowerCase())) {
				if(keyword2.equalsIgnoreCase("game") && x.getProduct() instanceof Game || keyword2.equalsIgnoreCase("book") && x.getProduct() instanceof Book)
					searchProduct.add(x.getProduct());
			}				
		}
		return searchProduct;*/
		
		
		
		
		/*List<Product> allProducts = productRepo.findProductByKeyword(keyword, keyword2);
		
		
		List<Product> lstProducts =  new ArrayList<>();
		
		for(ProductSeller objSP: productSellerRepo.findProductsListedSold(allProducts)) {	
			lstProducts.add(objSP.getProduct());
		}			
		return lstProducts ;*/
		
		
	}

}


