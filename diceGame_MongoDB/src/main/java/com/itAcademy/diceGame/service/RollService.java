package com.itAcademy.diceGame.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itAcademy.diceGame.entity.Roll;
import com.itAcademy.diceGame.repository.RollRepository;

@Service
public class RollService {
	
	@Autowired
	RollRepository rollRepository;

	public Roll getNewRollDice() {
		Roll rollDice = new Roll();
		rollRepository.save(rollDice);
		return rollDice;
	}
	
	

}
