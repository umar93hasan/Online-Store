package edu.northeastern.cs5200.repos;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.northeastern.cs5200.models.Product;

@Repository
public interface ProductRepo extends CrudRepository<Product, Integer> {
	@Query("SELECT p FROM Product p WHERE p.name LIKE %:keyword% AND dtype = :keyword2")
    public List<Product> findProductByKeyword (@Param("keyword") String keyword, @Param("keyword2") String keyword2);
	
	@Query("SELECT p FROM Product p WHERE p.id=:id")
    public Product findProductById (@Param("id") int keyword);
}
