package fr.bruno.findpaths;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class FindPaths {

	private static int m;
	private static int n;
	private static HashMap<int[], ArrayList<int[]>> positionList = new HashMap<int[], ArrayList<int[]>>();
	private static HashSet<ArrayList<int[]>> paths = new HashSet<ArrayList<int[]>>();
	private static int[] rootPosition = new int[2];
	private static ArrayList<int[]> walls = new ArrayList<int[]>();
	
	public static void main (String[] args) {
		/*Scanner in = new Scanner(System.in);
		int m = in.nextInt();
        int n = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }*/
		int m = Integer.parseInt(args[0]);
        int n = Integer.parseInt(args[1]);
		
        // Save wall cells :
        for (int i = 2; i < args.length; i++) {
			String ROW = args[i];
            for (int j = 0; j < n; j++) {
                if(Character.toString(ROW.charAt(j)).equals("1")) {
                    walls.add(new int[]{i-2, j});
                }
            }
		}
        /*for (int i = 0; i < m; i++) {
            String ROW = in.nextLine();
            for (int j = 0; j < n; j++) {
                if(Character.toString(ROW.charAt(j)).equals("1")) {
                    walls.add(new int[]{i, j});
                }
            }
        }*/
        String infoLogs = "M : " + m + " N : " + n;
        String wallsString = "";
        for (int[] wall: walls) {
            wallsString += " [" + wall[0] + " " + wall[1] + "] ";
        }
        infoLogs += "\nWalls : " + wallsString;
        
        
        for (int i = 0; i < m; i++) {
            // Save empty cells :
			for (int j = 0; j < n; j++) {
				int[] position = new int[] {i, j};
				// Check if new position is not a wall
				if(!positionIsAWall(walls, position)) {
    				if ( i == 0 && j == 0 ) rootPosition = position;
    				ArrayList<int[]> adjacentPositions = new ArrayList<int[]>();
    				if(i + 1 < m && !positionIsAWall(walls, new int[]{i+1, j})) 
    				    adjacentPositions.add(new int[]{i+1, j});
    				if(j + 1 < n && !positionIsAWall(walls, new int[]{i, j+1})) 
    				    adjacentPositions.add(new int[]{i, j+1});
    				positionList.put(position, adjacentPositions);
				}
			}
		}

		calculatePaths(rootPosition, new ArrayList<int[]>());
		
		// Show paths
		String pathsLogs = "\npaths : \n";
		for (ArrayList<int[]> path1 : paths) {
			for (int[] posit : path1) {
				pathsLogs += "[" + posit[0] + "-" + posit[1] + "]";
			}
			pathsLogs += "\n";
		}
//		infoLogs += pathsLogs;
		
		System.err.println("" + infoLogs + "\n");
		System.out.println("posible paths : " + paths.size());
	}
	
	private static void calculatePaths(int[] key, ArrayList<int[]> path) {
		// Replace searched key by original key to get according signatures
		for ( int[] k : positionList.keySet() ) {
		    if(k[0] == key[0] && k[1] == key[1]) key = k;
		}
		ArrayList<int[]> adjacentPositions = positionList.get(key);
		if(adjacentPositions == null ||  adjacentPositions.isEmpty()) {

			// 10x10 : paths  containing a wall are smaller
			int requiredPathSize = m + n - 2;
			if(path.size() == requiredPathSize) paths.add(path);
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
	
    private static boolean positionIsAWall( 
    		ArrayList<int[]> Positions,  int[] posChecked){
        for(int[] arr: Positions){
            if(arr[0] == posChecked[0] && arr[1] == posChecked[1]) return true;
        }
        return false;
    }
}
/*

10 x 10 arguments :

10
10
"0000001000"
"0000000000"
"0000000000"
"0010001000"
"0000000000"
"0000100000"
"0000100010"
"0000000000"
"0000000100"
"0000000010"

2 x 2 arguments :

2
2
00
10

*/
