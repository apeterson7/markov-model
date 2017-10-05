import java.util.*;




public class MyHashMap<K,V> {


    protected static final Exception NoSuchElementException = null;

    private LinkedList<MyEntry>[] table;
    
    private int size;
    
    private float loadFactor;
  
    /**
     * 
     * @author apeterso
     *an entry class to store the key and value pairs of the hashmap along with methods to access these class methods
     *for various tasks
     *
     */
    public class MyEntry {
	
	private K key;
	
	private V value;
	
	public MyEntry(K key,V value) {
	    this.key = key;
	    this.value = value;
	}
	
	public boolean equals(MyEntry entry) {
	    if(this.key.equals(entry.key)) {
		return true;
	    }else {
		return false;
	    }
	}
	
	public int hashCode() {
	    return this.key.hashCode();
	    
	}
    }
    
    /**
     * 
     * @param capacity = the starting capacity of the hashmap
     * @param loadFactor = the tablesize to realsize ratio at which the hashmap restructures
     */
    public MyHashMap(int capacity, float loadFactor) {
	this.loadFactor = loadFactor;
	
	table = (LinkedList<MyEntry>[]) new LinkedList[capacity];
	for(int i = 0; i < capacity; i++) {
	    table[i] = new LinkedList<MyEntry>();
	}
	size = 0;
    }
    
    public MyHashMap() {
	this(11,(float).75);
	
    }
 
    /**
     * 
     * @return the amount of items in the hashmap
     */
    public int size() {
	return size;
    }
    /**
     * 
     * @return true if the list is empty false if not
     */
    public boolean isEmpty() {
	if(size == 0) {
	    return true;
	}else {
	    return false;
	}
    }
    
    /**
     * clears each bucket in the table and sets size class member to 0
     */
    public void clear() {
	for(int i = 0; i < table.length; i++) {
	    table[i].clear();
	}
	size = 0;
    }
    
    /**
     * 
     * @return string representation of hashmap for testing purposes
     */
    public String toString() {
	String s = "";
	for(int i = 0; i < table.length; i++) {
	    s = s.concat("Bucket " + i + ": ");
	    if(table[i].size() == 0) {
		s = s.concat("empty");
	    }
	    for(int j = 0; j < table[i].size(); j++) {
		s = s.concat("(" + table[i].get(j).key + ", " + table[i].get(j).value + ")");
	    }
	    s = s.concat("\n");
	}
	return s;
    }
    
    /**
     * 
     * @param key of the entry to be added
     * @param value of the entry to be added
     * if the key is contained in the hashmap, update the value with the new value
     * if not, add the key to the hashmap based on the hashcode of the key
     * 
     * resize if loadfactor is reached
     * 
     * @return the previous value if there was one, null if not
     */
    public V put(K key, V value) {
	MyEntry entry = new MyEntry(key, value);
	int bucket = key.hashCode() % table.length;
	if(bucket < 0) {
	    bucket = bucket + table.length;
	}
	//check to see if the key is in the bucket that the hash code produced
	for(int i = 0; i < table[bucket].size(); i++) {
	    if(table[bucket].get(i).equals(entry)) {
		V temp = table[bucket].get(i).value;
		table[bucket].get(i).value = value;
		return temp;
	    }
	}
	//was not found-- add
	table[bucket].add(entry);
	size++;
	float loadFactorCompare = ((float) size)/(float)(table.length);
	if(loadFactorCompare > loadFactor) {
	    resize();
	}
	return null;
    }

    /**
     * 
     * @param key to search for
     * @return the value
     */
    public V get(K key) {
	int bucket = key.hashCode() % table.length;
	if(bucket < 0) {
	    bucket = bucket + table.length;
	}	
	try {
	    for (int i = 0; i < table[bucket].size(); i++) {
		if (table[bucket].get(i).key.equals(key)) {
		    return table[bucket].get(i).value;
		}

	    }
	} catch (ArrayIndexOutOfBoundsException e) {
	    

	}

	return null;
	
    }
    
    /**
     * 
     * @param key to remove from hashmap
     * @return the value of the removed key or null if it didnt exists
     * update size
     */
    public V remove(K key) {
	int bucket = key.hashCode() % table.length;
	if(containsKey(key) == false) {
	    return null;
	}
	if(bucket < 0) {
	    bucket = bucket + table.length;
	}
	for(int i = 0; i < table[bucket].size(); i++) {
	    if(table[bucket].get(i).key.equals(key)) {
		V temp = table[bucket].get(i).value;
		table[bucket].remove(i);
		size--;
		return temp;
	    }
	}
	return null;
	
    }
    /**
     * 
     * @param key to search for
     * @return true if key exists in hashmap, false if not
     */
    public boolean containsKey(K key) {
	if(get(key) == null) {
	    return false;
	}else{
	    return true;
	}
	
    }
    
    /**
     * 
     * @param value to search for
     * @return true if value exists in hashmap, false if not
     */
    public boolean containsValue(V value) {
	for(int i = 0; i < table.length; i++) {
	    for(int j = 0; j < table[i].size(); j++) {
		if(table[i].get(j).value.equals(value)) {
		    return true;
		}
	    }
	}
	return false;
	
    }
    
    /**
     * 
     * @return an iterator for the keys in the hashmap
     */
    public Iterator<K> keys() {
        return new Iterator<K>() {
            int bucket = 0;
            Iterator<MyEntry> itr = table[bucket].iterator();
            int nextCount = 0;

            public boolean hasNext() {
                if(nextCount < size) {
                    return true;
                }else {
                    return false;
                }
            }

            public K next() {
                // if my hasNext() is false, I should throw a NoSuchElementException
        	if(hasNext() == false) {
        	    throw new NoSuchElementException();
        	}

                // while itr.hasNext() is false, increment bucket and get the next iterator
        	while(itr.hasNext() == false) {
        	    bucket++;
        	    itr = table[bucket].iterator();
        	}

        	nextCount++;
        	return itr.next().key;
                // now increment nextCount and return the key from the item itr.next() returns
            }

            public void remove() {
        	itr.remove();
        	size--;
        	nextCount++;
                // just ask itr to remove, but I need to update my size and nextCount
            }
        };
    }
    
    /**
     * 
     * @return an iterator for the values in the hashmap
     */
    public Iterator<V> values(){
	return new Iterator<V>() {
            int bucket = 0;
            Iterator<MyEntry> itr = table[bucket].iterator();
            int nextCount = 0;

            public boolean hasNext() {
                if(nextCount < size) {
                    return true;
                }else {
                    return false;
                }
            }

            public V next() {
                // if my hasNext() is false, I should throw a NoSuchElementException
        	if(hasNext() == false) {
        	    throw new NoSuchElementException();
        	}

                // while itr.hasNext() is false, increment bucket and get the next iterator
        	while(itr.hasNext() == false) {
        	    bucket++;
        	    itr = table[bucket].iterator();
        	}

        	nextCount++;
        	return itr.next().value;
                // now increment nextCount and return the key from the item itr.next() returns
            }

            public void remove() {
        	itr.remove();/**
        	     * 
        	     * @return an iterator for the keys in the hashmap
        	     */
        	size--;
        	nextCount++;
                // just ask itr to remove, but I need to update my size and nextCount
            }
        };
	
    }


    /**
     * 
     * helper method for the resize method
     * @param prev 
     * @return the next prime after prev*2
     */
    private int nextPrime(int prev) {
	boolean isPrime = false;
	int nextPrime = prev*2;
	while(isPrime == false) {
	    nextPrime++;
	    int i = 1;
	    isPrime = true;
	    while(i < Math.sqrt(nextPrime) && isPrime == true) {
		if(nextPrime%++i == 0)
		    isPrime = false;
	    }   
	}
	return nextPrime;
    }
    
    /**
     * method to resize the array that the hashmap is built on 
     * will rehash each of the items in the hashmap based on the new 
     * array length
     * 
     */
    private void resize() {

	int newLength = nextPrime(table.length);
	LinkedList<MyEntry>[] newTable = (LinkedList<MyEntry>[]) new LinkedList[newLength];
	for(int i = 0; i < newLength; i++) {
	    newTable[i] = new LinkedList<MyEntry>();
	}
	
	//go through each MyEntry in the hashmap and rehash them to the new, handsomely bebucketed hashmap 
	for(int i = 0; i < table.length; i++) {
	    for(int j = 0; j < table[i].size(); j++) {
		MyEntry newEntry = table[i].get(j);
		int bucket = newEntry.hashCode()%newLength;
		if(bucket < 0) {
		    bucket = bucket + newLength;
		}
		newTable[bucket].add(newEntry);
		
	    }
	}
	table = newTable;

	
    }
    
}
