package com.itAcademy.DiceGame.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Throwings")
public class Thrown {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition = "tinyint")
	private int result;
	
	@Column
	private int diceOneResult;
	
	@Column
	private int diceTwoResult;
	
	@Column
	private boolean winning;
	
	@Column
	private Date throwing_date = new Date();
	
	@ManyToOne
	@JoinColumn(name = "idPlayer", nullable = false)
	private Player player;

	public Thrown() {
	}
	
	public Thrown(Player player) {
		this.player = player;
		diceOneResult = Dice.diceRoll();
		diceTwoResult = Dice.diceRoll();
		result = diceOneResult + diceTwoResult;
		if(result == 7) {
			winning = true;
		}else {
			winning = false;
		}
	}


	public int getDiceOneResult() {
		return diceOneResult;
	}

	public void setDiceOneResult(int diceOneResult) {
		this.diceOneResult = diceOneResult;
	}

	public int getDiceTwoResult() {
		return diceTwoResult;
	}

	public void setDiceTwoResult(int diceTwoResult) {
		this.diceTwoResult = diceTwoResult;
	}

	public boolean isWinning() {
		return winning;
	}

	public void setWinning(boolean winning) {
		this.winning = winning;
	}

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public int getResult() {
		return result;
	}


	public void setResult(int result) {
		this.result = result;
	}


	public Date getThrowing_date() {
		return throwing_date;
	}


	public void setThrowing_date(Date throwing_date) {
		this.throwing_date = throwing_date;
	}

	/*
	public Player getPlayer() {
		return player;
	}*/

	public void setPlayer(Player player) {
		this.player = player;
	}
	
	

}
