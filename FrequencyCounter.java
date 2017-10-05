import java.io.*;
import java.util.*;


public class FrequencyCounter {

    public static void main(String[] args) throws FileNotFoundException {
	
	int k = Integer.parseInt(args[0]);
	String text = args[1];
	MyHashMap<String,Markov> map = new MyHashMap<String, Markov>(); 
	
	for(int i = 0; i < text.length()-(k+1); i++) {
	    Markov newMarkov = new Markov(text.substring(i, i+k));
	    if(map.containsKey(text.substring(i, i+k))){
		map.get(text.substring(i, i+k)).add('c');
	    }else {
		newMarkov.add();
		map.put(text.substring(i, i+k),newMarkov);
	    }
	}
	System.out.println(map.size() + " distinct keys");
	Iterator itr = map.values();
	for(int i = 0; i < map.size(); i++) {
	    System.out.println(itr.next().toString().substring(0,4));
	}
	
    }
}
