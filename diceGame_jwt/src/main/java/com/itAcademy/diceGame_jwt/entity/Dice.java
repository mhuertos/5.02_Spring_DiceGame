package com.itAcademy.diceGame_jwt.entity;

//This class has been created to change the dice's amount of sides 
public class Dice {
	
	private static int sides = 6;
	
	public static int diceRoll() {
		int result = (int) Math.floor(Math.random() * sides + 1);
		return result;
	}

}
