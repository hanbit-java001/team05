package com.hanbit.team05.core.application;

import java.util.Random;

public class Test {

	public static void main(String[] args) {

	String[] stringArray = {"a","b","c","d","e","f","g","h","i","j","k","l","n","m","o","p","q","r","s","t","u","v","w","x","y","z"};

		for (int i = 0; i < 4; i++) {
			int randomNumber = new Random().nextInt(stringArray.length)+0;
			System.out.println(stringArray[randomNumber]);
		}
	}
}
