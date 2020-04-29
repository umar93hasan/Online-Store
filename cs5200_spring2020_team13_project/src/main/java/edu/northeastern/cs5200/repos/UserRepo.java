package edu.northeastern.cs5200.repos;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import edu.northeastern.cs5200.models.User;

@Repository
public interface UserRepo extends CrudRepository<User, Integer> {

	@Query("SELECT u FROM User u WHERE u.email=:email and u.password=:password")
    public User findUserByEmailPassword (@Param("email") String email, @Param("password") String password);
	
	@Query("SELECT u FROM User u WHERE u.email=:email")
    public User findUserByEmail (@Param("email") String email);
	
	@Transactional
	@Modifying
	@Query("DELETE From User u WHERE u.email=:email")
	public void deleteUserByEmail(@Param("email") String email);
	
}
