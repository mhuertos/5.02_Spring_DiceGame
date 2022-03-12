package com.itAcademy.DiceGame.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.itAcademy.DiceGame.entity.Player;
import com.itAcademy.DiceGame.entity.Thrown;

@Repository
public interface ThrownRepository extends JpaRepository<Thrown, Long> {
	
	void deleteByPlayer(Player player);
	
	boolean existsByPlayer(Player player);
	
	List<Thrown> findAllByPlayer(Player player);
	
	double countByPlayer(Player player);
	
	@Query(value="SELECT count(*) FROM Thrown t WHERE t.winning=true AND t.player=?1")
	double countGanadas(Player player);
	
	
	//Funciona para darme la media!!
	@Query(value="SELECT t.id_player, avg(t.result) FROM throwings t GROUP BY t.id_player", nativeQuery = true)
	List<Object> avg();

	


	
	
	
	
	
}
