import java.io.*;
import java.util.*;

public class TextGenerator {

    public static void main(String[] args) throws FileNotFoundException {
	int k = Integer.parseInt(args[0]);
	int M = Integer.parseInt(args[1]);
	String file = args[2];
	
	Scanner text = new Scanner(new File(file));
	text.useDelimiter("");
	
	MyHashMap<String, Markov> map = new MyHashMap<String, Markov>();
	String substring = "";
	
	for(int i = 0; i < k; i++) {
	    substring = substring + text.next();
	}
	String genText = substring;
	
	/**
	 * read the text file into a treemap with a scanner
	 */
	while (text.hasNext()) {
	    //take off the next suffix as a character
	    Character suffix = text.next().charAt(0);
	    //create the markov object out of the k-length substring
	    Markov mv = new Markov(substring);
	    //add the suffix char to the new markov object
	    mv.add(suffix);
	    //if the map has the k-length substring, add the suffix to the existing markov model
	    if(map.containsKey(substring)) {
		map.get(substring).add(suffix);
	    //otherwise add the new substring and markov to the map
	    }else {
		map.put(substring, mv);
	    }
	    //add the suffix to the end of the substring and delete the first character
	    substring = substring + String.valueOf(suffix);
	    substring = substring.substring(1);
	}
	
	
	/**
	 * create a string by determining a statistically accurate, random 
	 * character based on the input text
	 */
	for (int i = 0; i < M-k; i++) {
	    
	    try {
		genText = genText.concat(String.valueOf(map.get(genText.substring(genText.length() - k, genText.length())).random()));
	    }catch(NullPointerException e) {
		
	    }	
	}

	
	System.out.println();
	System.out.println(genText);
	System.out.println();
	

    }
}
