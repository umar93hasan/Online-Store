package edu.northeastern.cs5200.repos;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import edu.northeastern.cs5200.models.Order;
import edu.northeastern.cs5200.models.OrderItem;

@Repository
public interface OrderItemRepo extends CrudRepository<OrderItem, Integer> {

	@Query("SELECT o FROM OrderItem o WHERE o.order=:order")
    public List<OrderItem> findOrdersItemByOrder (@Param("order") Order order);
	
}
