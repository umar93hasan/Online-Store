package edu.northeastern.cs5200.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.northeastern.cs5200.models.Book;

@Repository
public interface BookRepo extends CrudRepository<Book, Integer> {

}
