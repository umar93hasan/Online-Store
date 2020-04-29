package edu.northeastern.cs5200.repos;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.northeastern.cs5200.models.Buyer;
import edu.northeastern.cs5200.models.Order;

@Repository
public interface OrderRepo extends CrudRepository<Order, Integer> {
	
	@Query("SELECT o FROM Order o WHERE o.orderBuyer=:orderBuyer")
    public List<Order> findOrdersByBuyer (@Param("orderBuyer") Buyer orderBuyer);

	@Query("SELECT o FROM Order o WHERE o.id=:id")
	public Order findOrderById(@Param("id") int id);
	
}
