package fr.bruno.enttech;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EX2_2M {

	private static int rootNode;
	private static Map<Integer, List<Integer>> graph = new HashMap<Integer, List<Integer>>();
	private static int globalPathIdx = 0;
	
	public static void main(String[] args) {
		// Parse arguments (First one is useless)
		for (int i = 1; i<args.length; i++) {	
			String[] values = args[i].split(" ");		
			int parentId = Integer.parseInt(values[0]);		
			int childId = Integer.parseInt(values[1]);		
			if (i==1) { // Root node expected to be the first one		
				rootNode = parentId;		
			}		
			// Store parent/Child information		
			if (! graph.containsKey(parentId)) {		
				graph.put(parentId, new ArrayList<Integer>());		
			}
			List<Integer> children = graph.get(parentId);
			children.add(childId);
		}
		// Now write possible paths
		// Begin with root node	
		computePaths(rootNode, "");
	}

	private static void computePaths(Integer parentNode, String computedText) {
		// If there is no child, just write the output	
		List<Integer> children = graph.get(parentNode);	
		if (children == null || children.isEmpty()) {
			System.out.println(computedText + " " + parentNode);		
		} else {	
			// Launch sub-paths		
			for (Integer child: children) {		
				computePaths(child, computedText + " " + parentNode);		
			}	
		}
	}
}
