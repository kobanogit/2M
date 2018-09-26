package fr.bruno.enttech;

import java.util.ArrayList;

public class Ex2 { 
	
	public static void main (String[] args) {
		ArrayList<ArrayList<Integer>>  providedArgs = StringArgsArrayToIntArray(args);
		System.out.println("Provided arguments : \n\n" + providedArgs);
		ArrayList<ArrayList<Integer>> paths = getPaths(providedArgs);
		System.out.println("\nNodes organisation : ");
		for(ArrayList<Integer> newPath: paths) {
			System.out.println();
			for(int value: newPath) {
				System.out.print(value + " ");	
			}
		}
	}
	
	private static ArrayList<ArrayList<Integer>> StringArgsArrayToIntArray(String[] args) {
		ArrayList<ArrayList<Integer>> PathsList = new ArrayList<ArrayList<Integer>>();
		for (int i = 1; i < (Integer.parseInt(args[0]) + 1); i++) {
			String[] argStringArray = args[i].split(" ");
			ArrayList<Integer> argArray = new ArrayList<Integer>();
			argArray.add(Integer.parseInt(argStringArray[0]));
			argArray.add(Integer.parseInt(argStringArray[1]));
			PathsList.add(argArray);
		}
		return PathsList;
	}

	private static ArrayList<ArrayList<Integer>> getPaths(ArrayList<ArrayList<Integer>> providedArgs) {
		
		ArrayList<ArrayList<Integer>> paths = null;
		// initialisation
		while(!providedArgs.isEmpty()) {
			// Initialisation de la variable renvoyée en result 
			if(paths == null) {
				paths = new ArrayList<>();
				ArrayList<Integer> smallest = findSmallest(providedArgs);
				// Une fois le plus petit trouvé, ajout au paths
				for(int i = 0; i < providedArgs.size(); i++){
					if(providedArgs.get(i).get(0) == smallest.get(0)) {
						smallest = providedArgs.get(i);
						paths.add(smallest);
						providedArgs.remove(i); // Suppression de l'argument
						break; // pour conserver un éventuel parent identique
					}
				}
			// Ajout des branches parallèles et des nodes suivants
			}else {
				// Recherche du plus petit dans les liaisons restantes
				ArrayList<Integer> smallest = findSmallest(providedArgs);
				// Enregistrement sans suprimer une seconde branche partant de la racine
				for(int i = 0; i < providedArgs.size(); i++){
					if(providedArgs.get(i).get(0) == smallest.get(0)) {
						smallest = providedArgs.get(i);
						providedArgs.remove(i); // Suppression de l'argument
						
						
						// Recherche node identique
						boolean sameNode = false;
						for(ArrayList<Integer> path: paths){
							if(path.get(0) == smallest.get(0)) {
								sameNode = true;
							}
						}
						if(sameNode) {
							paths.add(smallest);
						}
						
						
						// Recherche enfants
						Integer branchNumber = null;
						for (int b = 0 ; b < paths.size(); b++) {
							if(paths.get(b).get(paths.get(b).size() - 1) == smallest.get(0)) {
								branchNumber = b;
							}
						}
						if(branchNumber != null) {
							paths.get(branchNumber).add(smallest.get(1));
						}
					}
				}
			}
			
		}
		
		return paths;
	}
	
	private static ArrayList<Integer> findSmallest(ArrayList<ArrayList<Integer>> argsArrayList){
		// initialisation en prenant le premier élément en référence
		ArrayList<Integer> smallest = new ArrayList<>();
		smallest = argsArrayList.get(0);
		for(int i = 0; i < argsArrayList.size(); i++){
			if(argsArrayList.get(i).get(0) < smallest.get(0)) {
				smallest = argsArrayList.get(i);
			}
		}
		return smallest;
	}
	
}
