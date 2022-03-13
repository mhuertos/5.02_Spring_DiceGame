package com.it.diceGame_MongoDB_V2.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.it.diceGame_MongoDB_V2.entity.Player;
import com.it.diceGame_MongoDB_V2.entity.Roll;

@Repository
public interface RollRepository extends MongoRepository<Roll, String> {
	
	void deleteByPlayer(Player player);
	
	List<Roll> findAllByPlayer(Player player);
	
	double countByPlayer(Player player);
	
	double countByWinningAndPlayer(boolean winning, Player player);

}
