package com.itAcademy.diceGame_jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itAcademy.diceGame_jwt.entity.Player;


@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

	Player findByName(String name);
	
	boolean existsByName(String name);
}
