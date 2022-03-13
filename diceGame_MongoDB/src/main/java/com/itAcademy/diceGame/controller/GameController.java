package com.itAcademy.diceGame.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itAcademy.diceGame.entity.Player;
import com.itAcademy.diceGame.entity.Roll;
import com.itAcademy.diceGame.service.PlayerService;
import com.itAcademy.diceGame.service.RollService;


@RestController("/")
public class GameController {
	
	@Autowired
	PlayerService playerService;
	
	@Autowired
	RollService rollService;
	
	
	@PostMapping("players")
	public void addPlayer(@RequestParam(name="name", defaultValue = "ANONIM") String name) {
		playerService.addPlayer(name);
	}
	
	@PutMapping("players")
	public void modifyPlayerName(
			@RequestParam(name="id", required=true) String id,
			@RequestParam(name="newName", required=false, defaultValue="ANONIM") String newName) {
		playerService.modifyName(newName, id);
	}
	
	@GetMapping("players/{idPlayer}/games")
	public List<Roll> getOnePlayer(@PathVariable("idPlayer") String idPlayer) {
		return playerService.getRolls(idPlayer);
	}
	
	/*FUNCIONA ASOCIANDO ENTIDADES
	@PostMapping("players/{idPlayer}/games")
	public void rollDice(@PathVariable("idPlayer") String idPlayer) {
		Roll rollDice = rollService.getNewRollDice();
		playerService.addRollDice(rollDice, idPlayer);
	}*/
	
	//ALTERNATIVA 2: Anulamos la Collection 'Roll' y la annotation @DBRef de 'Players';
	@PostMapping("players/{idPlayer}/games")
	public void rollDice(@PathVariable("idPlayer") String idPlayer) {
		Roll rollDice = new Roll();
		playerService.addRollDice(rollDice, idPlayer);
	}
	
	@DeleteMapping("players/{idPlayer}/games")
	public void deleteRollDice(@PathVariable("idPlayer") String idPlayer) {
		playerService.deleteRolls(idPlayer);
	}
	
	@GetMapping("players/ranking")
	public List<Player> getRanking(){
		return playerService.getRanking();
	}
	
	@GetMapping("players/ranking/loser")
	public Player getLoser() {
		return playerService.getLoser();
	}
	
	@GetMapping("players/ranking/winner")
	public Player getWiner() {
		return playerService.getWiner();
	}
	
	
	
	
	
	
	
	
	
	
}
