package com.it.diceGame_MongoDB_V2.entity;

public class Dice {
	private static int sides = 6;
		
	public static int diceRoll() {
		int result = (int) Math.floor(Math.random() * sides + 1);
		return result;
	}
}
