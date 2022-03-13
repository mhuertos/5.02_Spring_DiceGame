package com.it.diceGame_MongoDB_V2.entity;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import lombok.Data;

@Data
@Document(collection="rolls")
public class Roll {
	
	@MongoId(targetType = FieldType.OBJECT_ID)
	private String id;
	private int result;
	private int diceOneResult;
	private int diceTwoResult;
	private boolean winning;
	private Date roll_date;
	@DBRef
	private Player player;

	
	public Roll(Player player) {
		this.roll_date = new Date();
		this.diceOneResult = Dice.diceRoll();
		this.diceTwoResult = Dice.diceRoll();
		this.result = this.diceOneResult + this.diceTwoResult;
		this.player = player;
		if(this.result == 7) {
			this.winning = true;
		}else {
			this.winning = false;
		}
	}


	public int getResult() {
		return result;
	}

	public int getDiceOneResult() {
		return diceOneResult;
	}

	public int getDiceTwoResult() {
		return diceTwoResult;
	}

	public boolean isWinning() {
		return winning;
	}

	public Date getRoll_date() {
		return roll_date;
	}
}
