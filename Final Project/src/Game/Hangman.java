package Game;

import java.util.Scanner;

public class Hangman {
	
	private static boolean mainloop;		//creates variable to use in other methods
	private static String word;				//word chosen from topic array
	private static String blank;			//variable that changes the chosen word into blanks
	private static int Stage = 0;			//stage counter to count the number of wrong guesses the user made
	
	public static void main(String[] args) {	//main method 
		mainloop = true;		//loops the game so that the user can play again
		System.out.println("Hi there, Welcome to Hangman! To begin please select a topic by entering the corresponding letter:");
		
		while(mainloop == true) {
			run(); 	//calls the run method
		}
	}
	
	public static void run() {		//game method
		String[] topics = {"colours", "animals", "fruits"};		//Array list of topics
		
		String[] colours = {"orange", "red", "purple"};			//Array list of words for the topics
		String[] animals = {"giraffe", "gorilla", "elephant"};
		String[] fruits = {"apricot", "mango", "grapefruit"};
		
		Scanner input = new Scanner(System.in);		//scanner
		String choose = "";						//user input for topic selection				
		Boolean loop = true;				//boolean to loop topic selection if user enters invalid character
		
		System.out.println("\nA. Colours \nB. Animals \nC. Fruits");		
		while (loop == true) {								//topic selection
			System.out.print("Enter your your topic: ");
			choose = input.nextLine();
			choose = choose.toLowerCase();
		if (choose.equals("a") ||choose.equals("b") ||choose.equals("c")) {		//stops the loop if user chooses a topic
			loop = false;
			}else{									//prompts the user to input a valid character if input is invalid
				System.out.println("Please enter a valid character");
			}
		}
		
		String[] topic;				//creates a new array called topic that replaces the chosen topic 
		
		if (choose.equals("a")) {				//Sets the array as the user chosen topic 
			topic = colours;
			System.out.println("Your topic: Colours");
		}else if (choose.equals("b")) {
			topic = animals;
			System.out.println("Your topic: Animals");
		}else {
			topic = fruits;
			System.out.println("Your topic: Fruits");
		}
		word = topic[(int) (Math.random() * topic.length)];	//Chooses random word from array
		blank = new String(new char[word.length()]).replace("\0", "_");	//Takes chosen word and replaces each letter with a blank or underscore
		
		System.out.println("\n   |");			//prints out base stage of hangman
		System.out.println("   |");
		System.out.println("   |");
		System.out.println("   |");
		System.out.println("   |");
		System.out.println("   |");
		System.out.println("   | ");
		System.out.println("___|___");
		
		while (Stage < 7 && blank.contains("_")) {					//for loop that keeps the game going as long as there aren't 7 mistakes and there are still blanks
			System.out.println(blank);		
			boolean letterloop = true;			//boolean to keep asking the user for an input if they input something invalid
			
			System.out.print("Enter a letter: ");
			while (letterloop == true) {
			String guess = input.nextLine();
			guess = guess.toLowerCase();					//sets user input as lower case so that it will count if user enters character in upper case
			if (guess.length() != 1) {			//asks for user input if they enter something thats more than 1 character
				System.out.print("Please enter only 1 character: ");
			} else {
				check(guess);				//calls the checking system
				letterloop = false;
				}
			}
		}
			
		Stage = 0;				//resets the stage counter after the game comes to an end
		Boolean replayloop = true;			//boolean to loop play again if user enters invalid input
		String playagain = "";
		
		System.out.println("Would you like to play again?");
		while (replayloop == true) {							//asks the user if they would like to play again
			System.out.print("Enter Y for yes and N for no: ");
			playagain = input.nextLine();
			playagain = playagain.toLowerCase();
			if (playagain.equals("n")){						//if user inputs n or N, mainloop will be false and the game will end
				System.out.println("Thank you for playing Hangman!");
				mainloop = false;
				replayloop = false;
			}else if (playagain.equals("y")){				//if user inputs y or Y, replay loop will equals false and the game will start again
				replayloop = false;
			}else {											//if user inputs something thats not y or n, will prompt the user to reenter 
				System.out.println("Please enter a valid character");
			}
		}
	}
	
	public static void check (String guess) {			//Checks if the user input matches with any letters of the word
		String letters = "";		//letters variable is used to check if the user input is correct or incorrect and used to update blank variable as the user guesses
		for (int i = 0; i < word.length(); i++) {		//for loop the same length as the chosen word to check every single letter
			if (word.charAt(i) == guess.charAt(0)){		//if user input is the same as the character in the word...
				letters += guess.charAt(0);				//adds character to the letters variable
			} else if (blank.charAt(i) != '_') {		//if a letter is already present at the character, add the letter
				letters += word.charAt(i);
			} else {									//if its wrong leave it as a blank and add it to the letters variable
				letters+= "_";			
			}
		}
		
		if (blank.equals(letters)) {		//if statement to check if the variable letters is equals to blank(from previous guess) 	
			Stage++;		//Adds to the stage counter if the new word is equals to the old word , meaning that the user input was incorrect
			Draw();			//calls the hangman drawings
		} else {
			blank = letters;		//Sets Blank as letters if they are different ; if letters is different from blank, that means a word was entered correctly
	}
		if (blank.equals(word)) {
			System.out.println(letters);
			System.out.println("Correct! You got the word!");		//if blank equals the word, there are no more blanks spaces and the user correctly guessed the word
		}
	}
	
	public static void Draw() {			//Hangman drawings according to the stage
		
		if (Stage == 0) {
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("   | ");
			System.out.println("___|___");
		}
		if (Stage == 1) {
			System.out.println("Wrong guess, try again");
			System.out.println("   ____________");
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("   | ");
			System.out.println("___|___");
		}
		if (Stage == 2) {
			System.out.println("Wrong guess, try again");
			System.out.println("   ____________");
			System.out.println("   |          _|_");
			System.out.println("   |         /   \\");
			System.out.println("   |        |     |");
			System.out.println("   |         \\_ _/");
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("___|___");
		}
		if (Stage == 3) {
			System.out.println("Wrong guess, try again");
			System.out.println("   ____________");
			System.out.println("   |          _|_");
			System.out.println("   |         /   \\");
			System.out.println("   |        |     |");
			System.out.println("   |         \\_ _/");
			System.out.println("   |           |");
			System.out.println("   |           |");
			System.out.println("   |");
			System.out.println("___|___");
		}
		if (Stage == 4) {
			System.out.println("Wrong guess, try again");
			System.out.println("   ____________");
			System.out.println("   |          _|_");
			System.out.println("   |         /   \\");
			System.out.println("   |        |     |");
			System.out.println("   |         \\_ _/");
			System.out.println("   |           |");
			System.out.println("   |           |");
			System.out.println("   |          /  ");
			System.out.println("___|___      /   ");
		}
		if (Stage == 5) {
			System.out.println("Wrong guess, try again");
			System.out.println("   ____________");
			System.out.println("   |          _|_");
			System.out.println("   |         /   \\");
			System.out.println("   |        |     |");
			System.out.println("   |         \\_ _/");
			System.out.println("   |           |");
			System.out.println("   |           |");
			System.out.println("   |          / \\ ");
			System.out.println("___|___      /   \\");
		}
		if (Stage == 6) {
			System.out.println("Wrong guess, try again");
			System.out.println("   ____________");
			System.out.println("   |          _|_");
			System.out.println("   |         /   \\");
			System.out.println("   |        |     |");
			System.out.println("   |         \\_ _/");
			System.out.println("   |        ___|");
			System.out.println("   |           | ");
			System.out.println("   |          / \\ ");
			System.out.println("___|___      /   \\");
		}
		if (Stage == 7) {
			System.out.println("Game Over! The Hangman has been drawn!");
			System.out.println("   ____________");
			System.out.println("   |          _|_");
			System.out.println("   |         /   \\");
			System.out.println("   |        |     |");
			System.out.println("   |         \\_ _/");
			System.out.println("   |        ___|___");
			System.out.println("   |           | ");
			System.out.println("   |          / \\ ");
			System.out.println("___|___      /   \\");
			System.out.println("The word was " + word);
		}
	}
}