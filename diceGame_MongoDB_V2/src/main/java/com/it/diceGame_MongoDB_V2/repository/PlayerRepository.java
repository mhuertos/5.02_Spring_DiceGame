package com.it.diceGame_MongoDB_V2.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.it.diceGame_MongoDB_V2.entity.Player;

@Repository
public interface PlayerRepository extends MongoRepository<Player, String> {
	
	boolean existsByName(String name);
	
	Player findTopByOrderByWinRateDesc();
	
	Player findTopByOrderByWinRateAsc();

}
