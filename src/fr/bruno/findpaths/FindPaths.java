package fr.bruno.findpaths;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class FindPaths {

	private static int m = 3;
	private static int n = 3;
	private static HashMap<int[], ArrayList<int[]>> positionList = new HashMap<int[], ArrayList<int[]>>();
	private static HashSet<ArrayList<int[]>> paths = new HashSet<ArrayList<int[]>>();
	private static int[] rootPosition = new int[2];
	
	public static void main (String[] args) {
		
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				int[] position = new int[] {i, j};
				if ( i == 0 && j == 0 ) rootPosition = position;
				ArrayList<int[]> adjascentPositions = new ArrayList<int[]>();
				if(i + 1 < m) adjascentPositions.add(new int[]{i+1, j});
				if(j + 1 < n) adjascentPositions.add(new int[]{i, j+1});
				positionList.put(position, adjascentPositions);
			}
		}
		calculatePaths(rootPosition, new ArrayList<int[]>());
		
		System.out.println("posible paths : " + paths.size());
		for (ArrayList<int[]> path1 : paths) {
			for (int[] node : path1) {
				System.out.print("[" + node[0] + "-" + node[1] + "] ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	private static void calculatePaths(int[] key, ArrayList<int[]> path) {
		
		// Recherche de la clé originale
		for ( int[] k : positionList.keySet() ) {
		    if(k[0] == key[0] && k[1] == key[1])
		    	key = k;
		}
		
		ArrayList<int[]> childrenNodes = positionList.get(key);
		
		if(childrenNodes == null ||  childrenNodes.isEmpty()) {
			paths.add(path);
			
		} else {
			for (int[] childNode : childrenNodes) {
				ArrayList<int[]> newPath = new ArrayList<int[]>();
				for(int[] nod: path) {
					newPath.add(nod);
				}
				newPath.add(key);
				calculatePaths(childNode, newPath);
			}
		}
	}
}
