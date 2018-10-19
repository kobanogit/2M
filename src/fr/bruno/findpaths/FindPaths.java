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
				ArrayList<int[]> adjacentPositions = new ArrayList<int[]>();
				if(i + 1 < m) adjacentPositions.add(new int[]{i+1, j});
				if(j + 1 < n) adjacentPositions.add(new int[]{i, j+1});
				positionList.put(position, adjacentPositions);
			}
		}
		calculatePaths(rootPosition, new ArrayList<int[]>());
		
		System.out.println("posible paths : " + paths.size());
		for (ArrayList<int[]> path1 : paths) {
			for (int[] posit : path1) {
				System.out.print("[" + posit[0] + "-" + posit[1] + "] ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	private static void calculatePaths(int[] key, ArrayList<int[]> path) {
		
		// Replace searched key by original key to get according signatures
		for ( int[] k : positionList.keySet() ) {
		    if(k[0] == key[0] && k[1] == key[1]) key = k;
		}
		
		ArrayList<int[]> adjacentPositions = positionList.get(key);
		
		if(adjacentPositions == null ||  adjacentPositions.isEmpty()) {
			paths.add(path);
			
		} else {
			for (int[] position : adjacentPositions) {
				ArrayList<int[]> newPath = new ArrayList<int[]>();
				for(int[] pos: path) {
					newPath.add(pos);
				}
				newPath.add(key);
				calculatePaths(position, newPath);
			}
		}
	}
}
