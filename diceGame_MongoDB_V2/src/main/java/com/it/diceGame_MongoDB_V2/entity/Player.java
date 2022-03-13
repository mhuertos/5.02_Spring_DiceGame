package com.it.diceGame_MongoDB_V2.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import lombok.Data;

@Data
@Document(collection = "players")
public class Player {
		
	@MongoId(targetType = FieldType.OBJECT_ID)
	private String idPlayer;
	private String name;
	private double winRate;
	private Date create;
	@DBRef
	private List<Roll> roll;
	
	public Player() {
	}
	
	public String getIdPlayer() {
		return idPlayer;
	}

	public Player(String name) {
		this.name = name;
		this.create = new Date();
		this.roll = new ArrayList<Roll>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getWinRate() {
		return winRate;
	}

	public void setWinRate(double winRate) {
		this.winRate = winRate;
	}

	public Date getCreate() {
		return create;
	}

	public void setCreate(Date create) {
		this.create = create;
	}

	public List<Roll> getRoll() {
		return roll;
	}

	public void setRoll(List<Roll> roll) {
		this.roll = roll;
	}
	
	public void addToRoll(Roll roll) {
		this.roll.add(roll);
	}
	
	public void deleteRolls() {
		this.roll = new ArrayList<Roll>();
	}
}
