package com.itAcademy.diceGame_jwt.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itAcademy.diceGame_jwt.entity.Player;
import com.itAcademy.diceGame_jwt.entity.Thrown;
import com.itAcademy.diceGame_jwt.repository.ThrownRepository;

@Service
public class ThrownService {
	
	@Autowired
	ThrownRepository thrownRepository;

	//listo
	public void addNewThrowing(Player player) {
		Thrown thrown = new Thrown(player);
		thrownRepository.save(thrown);		
	}
	
	//listo
	@Transactional
	public void deleteThrowings(Player player) {
		if(thrownRepository.existsByPlayer(player)) {
			thrownRepository.deleteByPlayer(player);
		}else {
			throw new IllegalStateException("This player has no throwings yet");
		}
	}
	
	//listo
	public List<Thrown> getThrowns(Player player) {
		return thrownRepository.findAllByPlayer(player);
	}

	public double calcWinRate(Player player) {
		double numPartidas = thrownRepository.countByPlayer(player);
		double numPartidasGanadas = thrownRepository.countGanadas(player);
		return (numPartidasGanadas/numPartidas) * 100;
	}
	
	


	
	
	
	
	
	
	
	
}
