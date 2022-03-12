package com.itAcademy.DiceGame.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itAcademy.DiceGame.entity.Player;
import com.itAcademy.DiceGame.entity.Thrown;
import com.itAcademy.DiceGame.repository.PlayerRepository;

@Service
public class PlayerService {
	
	@Autowired
	PlayerRepository playerRepository;

	public List<Player> getPlayers() {
		List<Player> players = playerRepository.findAll();
		if(players.size() == 0) {
			throw new IllegalStateException("There is no registered player yet");
		}
		return players;
	}

	
	public void createPlayer(String name) {
		if(checkExistence(name)) {
			throw new IllegalStateException("This name is not available. Please introduce another name.");
		}else {
			Player player = new Player(name);
			playerRepository.save(player);
		}
		
	}
	
	
	public Player getOnePlayer(Long idPlayer) {
		Player player = playerRepository.findById(idPlayer)
				.orElseThrow(() -> new IllegalStateException("There is no registered player with ID: "+idPlayer));
		return player;
	}
	
	
	public boolean checkExistence(String name) {
		boolean exists;
		if(name.equalsIgnoreCase("anonim")) {
			exists = false;
		}else if(playerRepository.existsByName(name)){
			exists = true;
		}else {
			exists = false;
		}
		return exists;
	}
	
	
	@Transactional
	public void modifyPlayer(Long idPlayer, String newName) {
		Player player = playerRepository.findById(idPlayer)
				.orElseThrow(() -> new IllegalStateException("There is no registered player with ID: "+idPlayer));
		
		if(checkExistence(newName)) {
			throw new IllegalStateException("This name is not available. Please introduce another name.");
		}else {
			player.setName(newName);
		}
	}


	public Player getLoser(List<Player> players) {
		double lessScore = players.get(0).getWinningRate();
		int loserPosition = 0;
		for(int i = 0; i < players.size(); i++) {
			if(players.get(i).getWinningRate() < lessScore) {
				loserPosition = i;
			}
		}
		return players.get(loserPosition);
	}
	
	public Player getWinner(List<Player> players) {
		double maxScore = players.get(0).getWinningRate();
		int winnerPosition = 0;
		for(int i = 0; i < players.size(); i++) {
			if(players.get(i).getWinningRate() > maxScore) {
				winnerPosition = i;
			}
		}
		return players.get(winnerPosition);
	}


	


	
	
}
