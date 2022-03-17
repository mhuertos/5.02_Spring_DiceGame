package com.itAcademy.diceGame_jwt.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.itAcademy.diceGame_jwt.entity.Player;
import com.itAcademy.diceGame_jwt.entity.Thrown;

@Repository
public interface ThrownRepository extends JpaRepository<Thrown, Long> {
	
	void deleteByPlayer(Player player);
	
	boolean existsByPlayer(Player player);
	
	List<Thrown> findAllByPlayer(Player player);
	
	double countByPlayer(Player player);
	
	@Query(value="SELECT count(*) FROM Thrown t WHERE t.winning=true AND t.player=?1")
	double countGanadas(Player player);
	

	


	
	
	
	
	
}
