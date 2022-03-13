package com.itAcademy.diceGame.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Component
//@Data
//@Document(collection="rolls")
public class Roll {
	
	//@MongoId(targetType = FieldType.OBJECT_ID)
	//private String id;
	private int result;
	private int diceOneResult;
	private int diceTwoResult;
	private boolean winning;
	private Date roll_date;

	
	public Roll() {
		this.roll_date = new Date();
		this.diceOneResult = Dice.diceRoll();
		this.diceTwoResult = Dice.diceRoll();
		this.result = this.diceOneResult + this.diceTwoResult;
		
		if(this.result == 7) {
			this.winning = true;
		}else {
			this.winning = false;
		}
	}


	public int getResult() {
		return result;
	}


	public void setResult(int result) {
		this.result = result;
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


	public Date getRoll_date() {
		return roll_date;
	}


	public void setRoll_date(Date roll_date) {
		this.roll_date = roll_date;
	}
	
	
}
