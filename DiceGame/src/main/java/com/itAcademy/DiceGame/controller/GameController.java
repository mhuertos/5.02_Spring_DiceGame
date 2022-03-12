package com.itAcademy.DiceGame.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itAcademy.DiceGame.entity.Player;
import com.itAcademy.DiceGame.entity.Thrown;
import com.itAcademy.DiceGame.service.PlayerService;
import com.itAcademy.DiceGame.service.ThrownService;

@RestController("/")
public class GameController {
	
	@Autowired
	PlayerService playerService;
	
	@Autowired
	ThrownService thrownService;

	
	@GetMapping("players")
	public List<Player> getPlayers(){
		return playerService.getPlayers();
	}
	
	//listo
	@PostMapping("players")
	public void createPlayer(@RequestParam(required=false, defaultValue = "ANONIM") String name) {
		playerService.createPlayer(name);
	}
	
	//listo
	@PutMapping("players")
	public void modifyPlayer(@RequestParam(required=true) Long idPlayer,
			@RequestParam(required=true, defaultValue = "ANONIM") String newName) {
		playerService.modifyPlayer(idPlayer, newName);
	}
	
	//listo
	@PostMapping("players/{idPlayer}/games")
	public void throwDice(@PathVariable("idPlayer") Long idPlayer) {
		Player player = playerService.getOnePlayer(idPlayer);
		thrownService.addNewThrowing(player);
	}
	
	//listo
	@DeleteMapping("players/{idPlayer}/games")
	public void deleteThrowings(@PathVariable("idPlayer") Long idPlayer) {
		Player player = playerService.getOnePlayer(idPlayer);
		thrownService.deleteThrowings(player);
	}
	
	
	//listo ALTERNATIVA 1 AL DE ARRIBA (desde la tabla Thrown)
	@GetMapping("/players/{idPlayer}/games")
	public List<Thrown> getThrowings2(@PathVariable("idPlayer") Long idPlayer){
		Player player = playerService.getOnePlayer(idPlayer);
		return thrownService.getThrowns(player);
	}
	
	
	//listo
	@GetMapping("/players/")
	public List<Player> getRanking() {
		List<Player> players = playerService.getPlayers();
		for(int i = 0; i < players.size(); i++) {
			players.get(i).setWinningRate(thrownService.calcWinRate(players.get(i)));
		}
		return players;
	}
	

	@GetMapping("players/ranking/loser")
	public Player getLoser(){
		List<Player> players = getRanking();
		return playerService.getLoser(players);
	}
	
	@GetMapping("players/ranking/winner")
	public Player getWinner(){
		List<Player> players = getRanking();
		return playerService.getWinner(players);
	}
}
