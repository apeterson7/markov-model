import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;


public class MyHashMapTest {

    @Test
    public void testMyHashMapIntFloat() {
	MyHashMap<String, Integer> test = new MyHashMap<String, Integer>(11,(float) 0.75);
	HashMap<String, Integer> real = new HashMap<String,Integer>(11, (float)0.75);
	
	for(int i = 0; i<101;i++) {
	    test.put(""+i,i);
	    //real.put(""+i, i);
	    
	    
	    //System.out.println(real.toString());
	}
	for(int i = 0; i <101; i++) {
	    test.remove(Integer.toString(i));
	}
	assertTrue(test.size() == 0);
    }



    @Test
    public void testSize() {
	MyHashMap<String, Integer> test = new MyHashMap<String, Integer>(11,(float) 0.75);
	HashMap<String, Integer> real = new HashMap<String,Integer>(11, (float)0.75);
	
	for(int i = 0; i<101;i++) {
	    test.put(""+i,i);
	   
	}
	assertTrue(test.size() == 101);
	
	for(int i = 0; i <101; i++) {
	    test.remove(Integer.toString(i));
	}
	assertTrue(test.size() == 0);
    }

    @Test
    public void testClear() {
	MyHashMap<String, Integer> test = new MyHashMap<String, Integer>(11,(float) 0.75);
	HashMap<String, Integer> real = new HashMap<String,Integer>(11, (float)0.75);
	
	for(int i = 0; i<101;i++) {
	    test.put(""+i,i);
	   
	}
	test.clear();
	assertTrue(test.size() == 0);
	assertTrue(test.isEmpty());
    }

    @Test
    public void testRemove() {
	MyHashMap<String, Integer> test = new MyHashMap<String, Integer>(11,(float) 0.75);
	HashMap<String, Integer> real = new HashMap<String,Integer>(11, (float)0.75);
	
	for(int i = 0; i<101;i++) {
	    test.put(""+i,i);
	   
	}
	assertTrue(test.get("18") == 18);
	test.remove("18");
	assertTrue(test.get("18")== null);
    }

    @Test
    public void testContainsKey() {
	MyHashMap<String, Integer> test = new MyHashMap<String, Integer>(11,(float) 0.75);
	HashMap<String, Integer> real = new HashMap<String,Integer>(11, (float)0.75);
	
	for(int i = 0; i<101;i++) {
	    test.put(""+i,i);
	    assertTrue(test.containsKey(""+i));
	   
	}
	
    }

    @Test
    public void testContainsValue() {
	MyHashMap<String, Integer> test = new MyHashMap<String, Integer>(11,(float) 0.75);
	HashMap<String, Integer> real = new HashMap<String,Integer>(11, (float)0.75);
	
	for(int i = 0; i<101;i++) {
	    test.put(""+i,i);
	    assertTrue(test.containsValue(i));
	   
	}
    }

    @Test
    public void testKeys() {
	MyHashMap<String, Integer> test = new MyHashMap<String, Integer>(11,(float) 0.75);
	HashMap<String, Integer> real = new HashMap<String,Integer>(11, (float)0.75);
	
	for(int i = 0; i<101;i++) {
	    test.put(""+i,i);
	    assertTrue(test.containsKey(""+i));
	   
	}
	Iterator itr = test.keys();
	for(int i = 0; i < 5; i++) {
	    System.out.println(itr.next());
	}
	System.out.println(test);

    }

    @Test
    public void testValues() {
	fail("Not yet implemented");
    }

}
