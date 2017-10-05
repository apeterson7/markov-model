import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.TreeMap;


public class SuffixCounter {

    public static void main(String[] args) throws FileNotFoundException {

	int k = 2;
	String text = args[1];
	
	MyHashMap<String, Markov> map = new MyHashMap<String, Markov>();
	boolean end = false;
	for (int i = 0; i < text.length() - (k + 1); i++) {
	    //If the map contains the key
	    if (map.containsKey(text.substring(i, i + k))) {
		if (text.length() - i < k + 1) {
		    map.get(text.substring(i, i + k)).add(null);
		} else {
		    map.get(text.substring(i, i + k)).add(text.charAt(i + k));
		}
	    // If the map doesn't already contain the substring
	    } else {
		Markov newMarkov = new Markov(text.substring(i, i + k));
		if (text.length() - i < k + 3) {
		    newMarkov.add(null);
		} else {
		    newMarkov.add(text.charAt(i + k));
		}
		map.put(text.substring(i, i + k), newMarkov);
	    }
	}
	System.out.println(map.size() + " distinct keys");
	Iterator itr = map.values();
	for (int i = 0; i < map.size(); i++) {
	    System.out.println(itr.next());
	}

    }
}
