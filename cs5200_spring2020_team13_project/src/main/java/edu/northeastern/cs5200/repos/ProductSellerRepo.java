package edu.northeastern.cs5200.repos;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.northeastern.cs5200.models.Product;
import edu.northeastern.cs5200.models.ProductSeller;
import edu.northeastern.cs5200.models.Seller;

@Repository
public interface ProductSellerRepo extends CrudRepository<ProductSeller, Integer> {

	@Query("SELECT sp FROM ProductSeller sp WHERE sp.seller = :seller")
    public List<ProductSeller> findProductsSellerByEmail (@Param("seller") Seller seller);
	
	@Query("SELECT sp FROM ProductSeller sp WHERE sp.product IN :allProducts")
    public List<ProductSeller> findProductsListedSold (@Param("allProducts") List<Product> allProducts);
	
	/*
	 * @Query("SELECT sp FROM ProductSeller sp WHERE Type(sp.product)=:dtype")
	 * public List<ProductSeller> findListedProductsByCategory(@Param("dtype")String
	 * dtype);
	 */
	
	
}
