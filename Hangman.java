import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Hangman {

//		String head = " O";
//		String body = "|";
//		String rArm = "/";
//		String rLeg = "/";
//		String hips = " _";
//		String lArm = "\\";
//		String lLeg = "\\";
//	
//		System.out.print(head + "\n" + rArm + body + lArm + "\n" + rLeg +" " +  lLeg );
		
		//Java Keywords
		public static final String[] WORDS = {
				"abstract", "question", "computer", "javascript", "canteen", "forest", "dessert", "panda",
				"coronavirus", "ebola" , "html", "water", "anime", "soda", "water", "food", "software", 
				"videogames", "lights", "wifisfuneral", "candle", "bike", "pasta", "chef", "beyonce", "kobe",
				"cooking", "crazy", "bathroom", "college", "professor"
	};
		
		public static final Random RANDOM = new Random();
		// Max errors before user lose
		public static final int MAX_ERRORS = 10;
		// Word To Find
		private String wordToFind;
		// Word found stored in a char array to show aggression  of user
		private char[] wordFound;
		private int noErrors;
		// letters already entered by user
		private ArrayList <String> letters = new ArrayList<>();
		
		// Method returning randomly next word to find
		private String nextWordToFind() {
			return WORDS[RANDOM.nextInt(WORDS.length)];	
		}
		
		// Method for starting a new game
		private void newCase() {
			noErrors = 0;
			letters.clear();
			wordToFind = nextWordToFind();
			
			//word found initialization 
			wordFound = new char[wordToFind.length()];
			
			for (int i = 0; i < wordFound.length; i++) {
				wordFound[i] = '*';	
			}
		}
		
		//Method returning true if word is found by user
		public boolean wordFound() {
			return wordToFind.contentEquals(new String(wordFound));
		}
		
		//Method updating the word found after user entered a character 
		private void enter(String c) {
			//we update only if c has not already been entered
			if (!letters.contains(c)) {
				// WE CHECK IF WORD TO FIND CONTAINS C 
				if (wordToFind.contains(c)) {
					// if so, we replace _ by the character c
					int index = wordToFind.indexOf(c);
					
					while (index >= 0) {
						wordFound[index] = c.charAt(0);
						index = wordToFind.indexOf(c, index + 1);
					}
				} else { 
					// c not in the word => error
					noErrors++;
				}
				
				// c is now a letter entered 
				letters.add(c);
			}
		}
		// method returning the state of the word found by the user until by now
		private String wordFoundContent() {
			StringBuilder builder = new StringBuilder();
			
			for (int i = 0; i < wordFound.length; i++) {
				builder.append(wordFound[i]); 
				
				if (i < wordFound.length - 1) {
					builder.append(" "); 
				}
			}
			return builder.toString();
		}
		// play method for our hang man Game
		public void play() {
			try  (Scanner input = new Scanner(System.in)) {
				// we play while noErrors is lower than max errors or user has found the word 
				while (noErrors < MAX_ERRORS) {
					System.out.println("\nEnter a letter : ");
					// get next input
					String str = input.next();
					
					//we keep just first letter 
					if (str.length() > 1) {
						str = str.substring(0, 1);
					}
					
					//update word found 
					enter(str);
					
					//display current state 
					System.out.println("\n" + wordFoundContent());
					
					//Check if word is found 
					if (wordFound()) {
						System.out.println("\nYou Win!");
						break;
					} else {
						//we display how many tries remaining for the user
						System.out.println("\n Numbers of Tries Remaining : " + (MAX_ERRORS - noErrors));
					}
				}
				
				if (noErrors == MAX_ERRORS) {
					//user loses
					System.out.println("\nYou Lose! ");
					System.out.println("\nWord To Find was : " + wordToFind);
				}
			}
		}
		
		public static void main(String[] args) {
			System.out.println("Hang Man game made as a class project.. \n"
					+ "By : Danny Madrida ");
			Hangman hangmanGame = new Hangman();
			hangmanGame.newCase();
			hangmanGame.play();
			
			//Time to Test haha 
		}
}
