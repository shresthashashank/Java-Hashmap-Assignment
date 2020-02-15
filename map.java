import java.util.*;
import java.io.*;

public class map
{
	private static Scanner infile;
	private static Scanner infile1;

	public static void main(String[] args) throws IOException
	{
		File file = new File("words.txt");			//Reading the text file.
		infile1 = new Scanner(file);				//Using  Scanner to read the numbers from a text file.
		infile = new Scanner(file);					//Using  Scanner to read the words from a text file.
		int newlinecount = 0;				
		int charcount = 0;
		int wordcount = 0;
		
		Map < String , Integer > identifier_counter = new HashMap<>(); 			//Creating hash map for storing key , value pairs of all the identifiers.
		Map < Integer , Integer > number_counter = new HashMap<>();				//Creating hash map for storing key , value pairs of all the numbers.	
		Map < Character , Integer > character_counter = new HashMap<>();		//Creating hash map for storing key , value pairs of all the characters.
		
		while(infile1.hasNext())					// Loop to continue until it runs out of tokens to read
		{
			String numbers = infile1.next();		//Storing all the consecutive tokens from the file.
			
			try 									//Implementing try catch to handle number format exception.
			{
				int num = Integer.parseInt(numbers); 	//Parsing the string into integers and storing it in num.
				
				if(number_counter.containsKey(num))					//Checks if the parsed string in in our hashmap.
				{
					int counttt = number_counter.get(num) + 1;		
					number_counter.put(num , counttt);				//If string is in the hash map , incrementing the value by 1 and storing it.
				}
				else
				{
					number_counter.put(num , 1);				//If string is not present in the hash map , adding it . 
				}
			}
			catch(NumberFormatException e)
			{
				
			}
			
		}
		
		while(infile.hasNext())						//Loop continues to run until it reaches end of file.
		{
			
			String line = infile.nextLine();		//Using another Scanner to read from the file.
			char [] strArr = line.toCharArray();    //Converting the scanned string into a series of characters.
			String[] wlist = line.split("\\s+");    //Space delimeter used to split the words after each space and storing it in an array.
			
			
			
			newlinecount++;					//Adds up total no of lines in each iteration.
			charcount += line.length();     //Counts the total number of characters.
			wordcount += wlist.length;		//Counts the total number of words.
			
			for(char character : strArr)								//Looping over the character array.
			{		
				if(character_counter.containsKey(character))			//Checking if hash map contains the character. 
				{
					int countt = character_counter.get(character) + 1;  
					character_counter.put(character, countt);			//If the hash map contains the characters , it increments its value by 1.
								
				}	
				else
				{
					character_counter.put(character,1);				//Adds the string if not present in the hash map.
				}
				
			}

			
			if(!line.trim().equals(""))             //If the line after removing all whitespace is not an empty string goes through.
			{
				String [] identifiers = line.split(" ");  //If space character is found , each word is separated.
				
				
				for(String identifier : identifiers)
				{					
					String lowered = identifier.toLowerCase();  //Converting every identifiers to lower case.
					
					try 										//If the string is not an integer , it moves on to store the value in the hashmap
					{
						int a = Integer.parseInt(lowered);			//Captures all the integer from the string.
					
					}
					catch(NumberFormatException e)
					{
						if(identifier_counter.containsKey(lowered))		//Checking if hash map contains the the identifier
							{
								int count = identifier_counter.get(lowered) + 1; //Increase count to 1 if it already exists.
								identifier_counter.put(lowered , count);	
							}
							else
							{
								identifier_counter.put(lowered , 1);	// If identifier not found , it adds it.
							}
					}
				}
			
			}
		}
		
		System.out.println("No of lines = "+newlinecount);			//Prints number of lines in the file.
		System.out.println("No of characters = "+charcount);		//Prints number of characters in the file.
		System.out.println("No of words = "+wordcount);				//Prints number of words in the file.
	
		LinkedHashMap< String, Integer> reverse_sorted_int = new LinkedHashMap<>();	//Using Linked Hash Map to preserve the order of the elements.

		identifier_counter.entrySet()			//Using comparator to sort the hash map in descending order.
			.stream()
			.sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
			.forEachOrdered(x -> reverse_sorted_int.put(x.getKey(), x.getValue()));
			
		System.out.println("\nThe top 5 used identifiers are : \n"); //Printing the top 5 identifiers.
		
		int counter = 0;
		
		for(Map.Entry<String, Integer> entry : reverse_sorted_int.entrySet())	//Iterating over the hash map.

		{
			System.out.println(entry.getKey()+" = "+entry.getValue()+" times.");	//Printing out the reverse sorted values from the hashmap(Top 5).
			counter++;			//Incrementing counter in each iteration.
			if(counter == 5)	//Counter stops after reaching 5 and prints out top 5 elements.
			break;
		}
	
		
		LinkedHashMap<Character, Integer> reverse_sorted_char = new LinkedHashMap<>();		//Using Linked Hash Map to preserve the order of the elements.
			character_counter.entrySet()			//Using comparator to sort the hashmap in descending order.
			    .stream()
			    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
		    	.forEachOrdered(x -> reverse_sorted_char.put(x.getKey(), x.getValue()));
		
		System.out.println("\nThe top 5 used characters are: \n");
			
		int counter2 = 0;
		for(Map.Entry<Character, Integer> entry : reverse_sorted_char.entrySet())  //Iterating over the hash map.
		{
			System.out.println(entry.getKey()+" = "+entry.getValue()+" times.");	//Printing out the reverse sorted values from the hashmap(Top 5).
			counter2++;				//Incrementing counter2 in each iteration.
			if(counter2 == 5)		//Printing out top 5 characters in the file.
				break;
		}
		
		LinkedHashMap<Integer, Integer> reverse_sorted_Integer = new LinkedHashMap<>();		//Using Linked Hash Map to preserve the order of the elements.

			number_counter.entrySet()			//Using comparator to sort the hashmap in descending order.
				.stream()
				.sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
				.forEachOrdered(x -> reverse_sorted_Integer.put(x.getKey(), x.getValue()));
		
		System.out.println("\nThe top 5 numbers are: \n");	//Printing top 5 numbers.
		
		int counter3 = 0;
		for(Map.Entry<Integer, Integer> entry : reverse_sorted_Integer.entrySet())	//Iterating over the hash map.

		{
			System.out.println(entry.getKey()+" = "+entry.getValue()+" times.");	//Printing out the reverse sorted values from the hashmap(Top 5).
			counter3++;			//Incrementing counter3 in each iteration.

			if(counter3 == 5)		//Counter stops after reaching 5 and prints out top 5 elements
				break;
		}
		

		int wordss = identifier_counter.size();
		System.out.println("\nNo of unique words: "+wordss);	//Prints out the number of unique words.

	}

}