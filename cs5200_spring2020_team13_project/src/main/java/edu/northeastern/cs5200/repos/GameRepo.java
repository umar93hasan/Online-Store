package edu.northeastern.cs5200.repos;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import edu.northeastern.cs5200.models.Game;

@Repository
public interface GameRepo extends CrudRepository<Game, Integer> {

}
