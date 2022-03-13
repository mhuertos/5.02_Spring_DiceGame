package com.itAcademy.diceGame.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itAcademy.diceGame.entity.Player;
import com.itAcademy.diceGame.entity.Roll;
import com.itAcademy.diceGame.repository.PlayerRepository;

@Service
public class PlayerService {
	
	@Autowired
	PlayerRepository playerRepository;
	
	@Autowired
	private MongoOperations mongoOperations;
	

	public void addPlayer(String playerName) {
		Player player = new Player(playerName);
		if(checkPlayerExists(playerName)) {
			throw new IllegalStateException("This name is not available. Please introduce another name.");
		}else {
			playerRepository.save(player);
		}
		
	}
	
	private boolean checkPlayerExists(String name) {
		boolean exists= false;
		if(name.equalsIgnoreCase("ANONIM")) {
			exists = false;
		}else if(playerRepository.existsByName(name)) {
			exists = true;
		}else {
			exists= false;
		}
		return exists;
	}

	public Player getOnePlayer(String id) {
		Player player = playerRepository.findById(id)
				.orElseThrow(() -> new IllegalStateException("There is no player with id: "+ id));
		return player;
	}

	@Transactional
	public void modifyName(String newName, String id) {
		
		if(checkPlayerExists(newName)) {
			throw new IllegalStateException("There is another player "
					+ "named: "+newName+". Please chose another one");
		}else {
			Query query = new Query();
			query.addCriteria(Criteria.where("idPlayer").is(id));
			Player player = mongoOperations.findOne(query, Player.class);
			
			player.setName(newName);
			playerRepository.save(player);
		}
	}
	
	@Transactional
	public void addRollDice(Roll rollDice, String idPlayer) {
		Query query = new Query();
		query.addCriteria(Criteria.where("idPlayer").is(idPlayer));
		Player player = mongoOperations.findOne(query, Player.class);
		player.addToRoll(rollDice);
		playerRepository.save(player);
	}

	
	public void deleteRolls(String idPlayer) {
		Query query = new Query();
		query.addCriteria(Criteria.where("idPlayer").is(idPlayer));
		Player player = mongoOperations.findOne(query, Player.class);
		player.deleteRolls();
		playerRepository.save(player);
	}

	public List<Roll> getRolls(String idPlayer) {
		Query query = new Query();
		query.addCriteria(Criteria.where("idPlayer").is(idPlayer));
		Player player = mongoOperations.findOne(query, Player.class);
		return player.getRoll();
	}

	public List<Player> getRanking() {
		UpdatePlayersWinAvg();
		Query query = new Query();
		query.with(Sort.by(Sort.Direction.DESC, "winRate"));
		return mongoOperations.find(query, Player.class);
	}
	
	@Transactional
	private void UpdatePlayersWinAvg() {
		List<Player> players = playerRepository.findAll();
		for(int i = 0; i < players.size(); i++) {
			Player player = players.get(i);
			float games = calcNumGames(player);
			float winGames = calcWinGames(player);
			if(winGames != 0) {
				float avg = (winGames/games) * 100;
				player.setWinRate(avg);
				playerRepository.save(player);
			}
		}
	}
	
	public int calcNumGames(Player player) {
		return player.getRoll().size();
	}
	
	public int calcWinGames(Player player) {
		List<Roll> rolls = player.getRoll();
		int counter = 0;
		for(int i = 0; i < rolls.size(); i++) {
			if(rolls.get(i).getResult() == 7) {
				counter++;
			}
		}
		return counter;
	}

	public Player getLoser() {
		Query query = new Query();
		query.with(Sort.by(Sort.Direction.ASC, "winRate"));
		return mongoOperations.findOne(query, Player.class);
	}

	public Player getWiner() {
		Query query = new Query();
		query.with(Sort.by(Sort.Direction.DESC, "winRate"));
		return mongoOperations.findOne(query, Player.class);
	}
	
	
}
