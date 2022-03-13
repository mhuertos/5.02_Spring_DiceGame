package com.itAcademy.diceGame.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.stereotype.Repository;

import com.itAcademy.diceGame.entity.Player;

@Repository
public interface PlayerRepository extends MongoRepository<Player, String> {
	
	boolean existsByName(String name);
	
}
