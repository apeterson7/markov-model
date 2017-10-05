import java.util.*;
import java.util.Map.Entry;



public class Markov {

    /**
     * markov object represents a substring and a treemap of the associate suffixes
     * 
     */
    private String substring;
    private int frequency = 0;
    private TreeMap<Character, Integer> suffixes = new TreeMap<Character, Integer>();
    
    
    /**
     * 
     * @param Substring is the string the markov object represents
     */
    public Markov(String Substring) {
	substring = Substring;
    }
    
    /**
     * 
     * @param c the suffix of the markov object
     * increments frequency
     * puts the suffix into suffixes if not previously there
     * if not:
     * increments the int that the suffix is mapped to in the treemap
     * 
     */
    public void add(Character c) {
	frequency++;

	if(c == null) {
	    //do nothing
	}else if(suffixes.containsKey(c)) {
	    int sufFreq = suffixes.get(c);
	    suffixes.put(c,sufFreq+1);
	}else {
	    suffixes.put(c,1);
	}
	
    }
    /**
     * calling add without an argument will simply increment the frequency counter
     */
    public void add() {
	frequency++;
    }
    
    /**
     * 
     * @return string representation of markov object for testing purposes
     */
    public String toString() {
	return frequency + " " + substring + " " + suffixes.toString();
    }
    
    /**
     * compares the substring of a markov object to the substring of
     * another markov object
     * @param mark, the markov to be compared
     * @return true or false based on the comparison
     */
    public boolean equals(Markov mark) {
	if(this.substring == mark.substring) {
	    return true;
	}else {
	    return false;
	}
    }
    /**
     * method to return a pseudo random suffix in the treemap 
     * accurately attending to the probabilities associated with each
     * suffix
     * 
     * @return the pseudo-randomly selected suffix
     */
    public Character random() {
	Random randint = new Random();
	int random = randint.nextInt(frequency);
	for(Map.Entry<Character,Integer> entry : suffixes.entrySet()) {
	    random = random - entry.getValue();
	    if(random < 0) {
		return entry.getKey();
	    }
	}
	return null;
	
	
    }
    
}
