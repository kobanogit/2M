package fr.bruno.enttech;

import java.util.HashMap;
import java.util.Map; 
import java.util.Map.Entry;

public class Ex1 {
	
	public static void main (String[] args) {
		System.out.println("Provided arguments : ");			
		for(String arg: args) {
			System.out.println(arg);			
		}
		HashMap<String, Integer> argsHashMap = stringArgsToHashMap(Integer.parseInt(args[0]), args[1], args[2]);
		String highestValueKey = getHighestValueKey(argsHashMap);
		System.out.println("\nHighest value key : " + highestValueKey);
	}
	
	private static HashMap<String, Integer> stringArgsToHashMap(int valuesQuantity, String keys, String values) {
		HashMap<String, Integer> argsHashMap = new HashMap<String, Integer>();
		String[] keysArray = keys.split(" ");
		String[] valuesArray = values.split(" ");
		for (int i = 0; i < valuesQuantity; i++) {
			argsHashMap.put(keysArray[i], Integer.parseInt(valuesArray[i]));
		}
		return argsHashMap;
	}
	
	private static String getHighestValueKey(HashMap<String, Integer> argsHashMap) {
		Map.Entry<String, Integer> highestValueEntry = null;
		for(Map.Entry<String, Integer> entry : argsHashMap.entrySet()) {
		    String key = entry.getKey();
		    Integer value = entry.getValue();
			if(highestValueEntry == null || highestValueEntry.getValue() < entry.getValue()) {
				highestValueEntry = (Entry<String, Integer>) entry;
			}
		}
		return highestValueEntry.getKey();
	}
}
