package edu.northeastern.cs5200.repos;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import edu.northeastern.cs5200.models.Seller;

@Repository
public interface SellerRepo extends CrudRepository<Seller, Integer> {
	
	@Query("SELECT s FROM Seller s WHERE s.email=:email")
    public Seller findSellerByEmail (@Param("email") String email);
	
	@Query("SELECT s FROM Seller s WHERE s.email=:email and s.password=:password")
    public Seller findSellerByEmailPassword (@Param("email") String email, @Param("password") String password);
	
}
