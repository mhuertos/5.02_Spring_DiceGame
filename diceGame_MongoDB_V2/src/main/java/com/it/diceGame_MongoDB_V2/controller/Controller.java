package com.it.diceGame_MongoDB_V2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.it.diceGame_MongoDB_V2.entity.Player;
import com.it.diceGame_MongoDB_V2.entity.Roll;
import com.it.diceGame_MongoDB_V2.service.PlayerService;
import com.it.diceGame_MongoDB_V2.service.RollService;

@RestController
@RequestMapping("/v2")
public class Controller {
	
	@Autowired
	RollService rollService;
	
	@Autowired
	PlayerService playerService;
	
	@PostMapping("/players")
	public void createPlayer(
			@RequestParam(name = "name", 
			required = false, 
			defaultValue = "ANONIM") String name) {
		
		playerService.addPlayer(name);
	}
	
	@PutMapping("players")
	public void modifyPlayerName(
			@RequestParam(name="id", required = true) String idPlayer,
			@RequestParam(name="newName", required = false, defaultValue = "ANONIM") String newName) {
		playerService.modifyName(idPlayer, newName);
	}
	
	@PostMapping("players/{idPlayer}/games")
	public void rollDice(@PathVariable("idPlayer") String idPlayer) {
		Player player = playerService.getOnePlayer(idPlayer);
		rollService.addRollDice(player);
	}
	
	@DeleteMapping("players/{idPlayer}/games")
	public void deleteAllRollsByPlayer(@PathVariable("idPlayer") String idPlayer) {
		Player player = playerService.getOnePlayer(idPlayer);
		rollService.deleteAll(player);
	}
	
	@GetMapping("players/{idPlayer}/games")
	public List<Roll> getRollsByPlayer(@PathVariable("idPlayer") String idPlayer) {
		Player player = playerService.getOnePlayer(idPlayer);
		return rollService.getRollsByPlayer(player);
	}
	
	@GetMapping("players/ranking")
	public List<Player> getRanking(){
		return rollService.getRanking();
	}
	
	@GetMapping("players/ranking/winner")
	public Player getWinner() {
		rollService.updatedRanking();
		return playerService.getWinner();
	}
	
	@GetMapping("players/ranking/loser")
	public Player getLoser() {
		rollService.updatedRanking();
		return playerService.getLoser();
	}
}
