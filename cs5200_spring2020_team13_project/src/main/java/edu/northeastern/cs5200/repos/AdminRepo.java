package edu.northeastern.cs5200.repos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.northeastern.cs5200.models.*;

@Repository
public interface AdminRepo extends CrudRepository<Admin, Integer> {

	@Query("SELECT a FROM Admin a WHERE a.email=:email and a.password=:password")
    public Admin findAdminByEmailPassword (@Param("email") String email, @Param("password") String password);
	
}
