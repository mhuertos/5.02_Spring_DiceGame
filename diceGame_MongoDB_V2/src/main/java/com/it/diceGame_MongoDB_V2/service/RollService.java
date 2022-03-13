package com.it.diceGame_MongoDB_V2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.it.diceGame_MongoDB_V2.entity.Player;
import com.it.diceGame_MongoDB_V2.entity.Roll;
import com.it.diceGame_MongoDB_V2.repository.PlayerRepository;
import com.it.diceGame_MongoDB_V2.repository.RollRepository;

@Service
public class RollService {
	
	@Autowired
	RollRepository rollRepository;
	
	@Autowired
	PlayerRepository playerRepository;
	
	@Autowired
	MongoOperations mongoOperations;

	public void addRollDice(Player player) {
		rollRepository.save(new Roll(player));
	}

	public void deleteAll(Player player) {
		rollRepository.deleteByPlayer(player);
	}

	public List<Roll> getRollsByPlayer(Player player) {
		return rollRepository.findAllByPlayer(player);
	}

	public List<Player> getRanking() {
		updatedRanking();
		Query query = new Query();
		query.with(Sort.by(Sort.Direction.DESC, "winRate"));
		return mongoOperations.find(query, Player.class);
	}
	
	@Transactional
	public void updatedRanking() {
		List<Player> players = playerRepository.findAll();
		for(int i = 0; i < players.size(); i++) {
			Player player = players.get(i);
			double games = rollRepository.countByPlayer(player);
			double wins = rollRepository.countByWinningAndPlayer(true, player);
			double avg = (wins/games) * 100;
			player.setWinRate(avg);
			playerRepository.save(player);
		}
	}
	
	

}
