package edu.northeastern.cs5200.repos;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import edu.northeastern.cs5200.models.Buyer;

@Repository
public interface BuyerRepo extends CrudRepository<Buyer, Integer> {

	@Query("SELECT b FROM Buyer b WHERE b.email=:email")
    public Buyer findBuyerByEmail (@Param("email") String email);
	
	@Query("SELECT b FROM Buyer b WHERE b.email=:email and b.password=:password")
    public Buyer findBuyerByEmailPassword (@Param("email") String email, @Param("password") String password);
	
}
