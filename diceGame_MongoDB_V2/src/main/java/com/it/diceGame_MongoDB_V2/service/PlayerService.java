package com.it.diceGame_MongoDB_V2.service;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.mongodb.core.MongoOperations;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.it.diceGame_MongoDB_V2.entity.Player;
import com.it.diceGame_MongoDB_V2.repository.PlayerRepository;

@Service
public class PlayerService {
	
	@Autowired
	PlayerRepository playerRepository;
	
	@Autowired
	MongoOperations mongoOperations;

	public void addPlayer(String name) {
		if(checkPlayerNameExistance(name)) {
			throw new IllegalStateException("There is another player using this name yet. "
					+ "Please chose another one.");
		}else {
			playerRepository.save(new Player(name));
		}
	}
	
	public Player getOnePlayer(String idPlayer) {
		return playerRepository.findById(idPlayer)
			.orElseThrow(() -> new IllegalStateException("There is no players with id: "+idPlayer));
	}
	
	private boolean checkPlayerNameExistance(String name) {
		boolean exists = false;
		if(name.equalsIgnoreCase("anonim")) {
			exists = false;
		}else if(playerRepository.existsByName(name)) {
			exists = true;
		}
		return exists;
	}

	@Transactional
	public void modifyName(String idPlayer, String newName) {
		if(checkPlayerNameExistance(newName)) {
			throw new IllegalStateException("There is another player using this name yet. "
					+ "Please chose another one.");
		}else {
			Player player = getOnePlayer(idPlayer);
			player.setName(newName);
			playerRepository.save(player);
		}
	}

	public Player getWinner() {
		return playerRepository.findTopByOrderByWinRateDesc();
	}
	
	public Player getLoser() {
		return playerRepository.findTopByOrderByWinRateAsc();
	}

}
