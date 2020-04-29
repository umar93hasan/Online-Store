package edu.northeastern.cs5200.repos;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.northeastern.cs5200.models.Address;
import edu.northeastern.cs5200.models.User;

@Repository
public interface AddressRepo extends CrudRepository<Address, Integer> {	
	
	@Query("SELECT a FROM Address a WHERE a.resident=:resident")
    public List<Address> findAddressesByEmail (@Param("resident") User resident);
	
}
