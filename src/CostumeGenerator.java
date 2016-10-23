import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.io.*;

public class CostumeGenerator {
	/* This program randomly chooses three words from three different 
	 * lists to present an idea for a Halloween costume.
	 * It checks that it does not pick the same word from two lists,
	 * and that it does not give the same answer twice.  */
	
	public static void main(String[] args) {
		
		String doagain = "";  //used to decide how many times to run the do loop
		String costume = ""; //randomly generated idea
		
		System.out.println("This program will help you to choose a Halloween costume!"); //initial text displayed
		
		ArrayList < String> previousSuggestions = new ArrayList <String>();  //create array
		previousSuggestions.add(""); 
		
		do {
			
			costume = RandomCostume();  //generates a random costume idea
			while (previousSuggestions.contains(costume)) {  //checks if idea has already been used
				costume = RandomCostume();
				int count = 0;
				count++;
				if (count > 10000) 
					continue;
				/* in theory, the user could eventually test and reject all 125,000 possible
				 * combinations.  This is unlikely, but a continue function here will prevent
				 * determined users from getting stuck in an infinite loop.*/
			}
		
			char vowel = costume.charAt(0);  //test if first letter of costume is a vowel
			if ((vowel == 'a') || (vowel == 'e') || (vowel == 'i') || (vowel == 'o') || 
					(vowel == 'u')) {
				System.out.print("How about an "); //first part of answer with vowel
			}
			else 
				System.out.print("How about a ");  //first part of answer with consonant
		
			System.out.println(costume + "? (Type y/n)"); //second part of answer
		
			doagain = IO.readString();  //decide if we need to generate another idea or not
			while (!doagain.equals("y") && !doagain.equals("n")) {
				System.out.println("You have to enter y or n!");
				doagain = IO.readString(); 
			}
			if (doagain.equals("y"))
				System.out.println("Yay! We found you a costume!"); //final output
			
			previousSuggestions.add(costume); //add used suggestion to array
		}
		
		while (doagain.equals("n"));  //generate a new idea if they don't like the first one

		}

	
	public static String RandomCostume() {
		
		int numOfOptions = 50; //number of words I wrote in each text file
		String adj1 = "";  //an adjective
		String adj2 = "";  //a different adjective
		String noun = "";  //a noun
		String line = "";  //line of text document on which each word will be found
		String costume = "";  //whole costume idea
			
			Random con = new Random();  //creates instance of random
			
			//choose first adjective
			int x = (con.nextInt(numOfOptions) + 1);   //generate random number
			try {
				File adjfile = new File("Adjective1.txt");  //accesses text file
				Scanner adjscan = new Scanner(adjfile);  //scans text file
				for (int i = 0; i < x; i++) {  //find line that matches random number
					line = adjscan.next(); 
				}  
				adj1 = line; 
				adjscan.close(); //ends scanner
			} 
			catch(FileNotFoundException e) { //prevents crashing if text file can't be found
				System.out.print("File not Found");	
			}
			
			//choose second adjective
			do { 
				int y = (con.nextInt(numOfOptions) + 1);  
				try { 
					File adjfile2 = new File("Adjective2.txt");
					Scanner adjscan2 = new Scanner(adjfile2);
					for (int i = 0; i < y; i++) {
						line = adjscan2.next(); 
					}
					adj2 = line; 
					adjscan2.close(); 
				} 
				catch (FileNotFoundException e) {
					System.out.print("File not Found"); 
				} 
			}
			while (adj1.equals(adj2)); //checks that same word is not used twice
			
			//choose noun
			do { 
				int z = (con.nextInt(numOfOptions) + 1);  
				try { 
					File nounfile = new File("Noun1.txt");
					Scanner nounscan = new Scanner(nounfile);
					for (int i = 0; i < z; i++) {
						line = nounscan.next(); 
					}
					noun = line; 
					nounscan.close(); 
				}
			catch (FileNotFoundException e) {
				System.out.print("File not Found"); 
				} 
			}
			while (adj2.equals(noun));
			
			costume = (adj1 + " " + adj2 + " " + noun);  //put words together for cleaner output
			
		return costume;
		
	}

} 
