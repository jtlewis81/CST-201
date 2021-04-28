/*
 * Jamie Lewis
 * CST-201
 * 3/7/21
 * Instructor Jeremy Wright
 * 
 * Project 8 - Graph
 * 
 * ASSIGNMENT INSTRUCTIONS :
 * The purpose of this assignment is to assess your ability to implement a graph abstract data type.
 * A graph is a set of vertices and a set of edges. Represent the vertices in your graph with an array of strings.
 * Represent the edges in your graph as a two-dimensional array of integers.
 * Use the distances shown in the graph pictured here.
 * Add the following functions to your graph class:
 * 		A getDistance function that takes two vertices and returns the length of the edge between them.
 * 			If the vertices are not connected, the function should return the max value for an integer.
 * 		A getNeighbors function that takes a single vertex and returns a list of all the vertices connected to that vertex.  
 * 		A print method that outputs an adjacency matrix for your graph. Write a test program for your Graph class.
 * Create a Loom video in which you walk through your code and execute your program.
 * Be sure your test program demonstrates all functionality.
 * 
 * NOTES : I implemented my Graph to use a generic data type for the Vertex. I did not include any methods for removing
 * 			a vertex or an edge since we were given a defined data set for the assignment and no API specification other than
 * 			getDistance, getNeighbors, and print. I had thought about making the getDistance method return the combined edge
 * 			length of a path between two non-neighbor vertices, but that seemed to be outside the scope of this assignment.
 * 			Also, there is nothing in the instructions about time complexity.
 * 
 * This program is my own work.
 */

package project8;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MyGraph<T> {

	// abstract array of objects that are the vertices of the graph
	Vertex<T>[] vertices;
	// the value stored at the coordinate of the 2D array will be the length of the edge
	// default is 0 for every value
	// could also be implemented to be true or false (1 || 0)
	int[][] edges;
	// the number of Vertices in the Graph
	int size;

	// constructor only takes a size
	public MyGraph(int i) {
		this.vertices = new Vertex[i];
		this.edges = new int[i][i];
		this.size = i;
	}

	// insert a new vertex
	public void insertVertex(int i, T data) {
		Vertex<T> v = new Vertex<T>(data);
		this.vertices[i] = v;
	}

	// add a new edge
	public void addEdge(int x, int y, int z) {
		this.edges[x][y] = z;
		this.vertices[x].adjLength++;
	}

	// returns a Vertex object from a given index
	public Vertex<T> getVertex(int i) {
		return this.vertices[i];
	}

	/*
	 * ASSIGNED
	 * 
	 * getDistance method
	 * 
	 * returns the length of an edge between two connected vertices
	 * or 0 if the the same vertex is passed for both parameters
	 * or Integer.MAX_VALUE if they are not connected
	 */
	public int getDistance(int i, int j) {
		// original implementation - returns value of an edge length or 0
//		return edges[i][j];
		
		// Dijkstra's algorithm implementation - explores the entire graph to ensure minimum distance is returned
		
		// store the values of minimum distance to each other vertex from the source
		int dist[] = new int[size];
  
        // used to mark vertices as processed 
        Boolean isVisited[] = new Boolean[size];
  
        // Initialize all distances as INFINITE and isVisited[] as false 
        for (int d = 0; d < size; d++) { 
            dist[d] = Integer.MAX_VALUE; 
            isVisited[d] = false; 
        } 
  
        // Distance of source vertex from itself is always 0 
        dist[i] = 0; 
  
        // Find shortest path for all vertices 
        for (int count = 0; count < size - 1; count++) {
        	
            // find the next closest, unvisited/unprocessed vertex
        	// uses the minDistance() helper method below
            int u = minDistance(dist, isVisited); 
  
            // Mark the current vertex as visited/processed 
            isVisited[u] = true; 
  
            // Update distance value of the adjacent vertices 
            for (int v = 0; v < size; v++) {
            	
            	// Update dist[v] only if :
            	// v is not in isVisited <[ !isVisited[v] ]>
            	// AND there is an edge from u to v <[ && edges[u][v] != 0 ]>
            	// AND total weight (combined edge length) of path from i to v through u is not infinity (unreachable) <[ && dist[u] != Integer.MAX_VALUE ]>
            	// AND total weight (combined edge length) of path from i to v through u is smaller than current value of dist[v] <[ && dist[u] + edges[u][v] < dist[v] ]>
                if (!isVisited[v] && edges[u][v] != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + edges[u][v] < dist[v]) {
                	dist[v] = dist[u] + edges[u][v];
                }
                
                // return the minimum distance from the source vertex to the target vertex
                if(u == j) {
                	return dist[j];
                }
            }
        } 
  
        // returns 0 if target is not connected to source
        // or Integer.MAX_VALUE (infinity) if vertices are not connected
        return dist[j];
		
	}
	
	// minDistance helper method for getDistance()
	int minDistance(int dist[], Boolean isVisited[]) {
        int min = Integer.MAX_VALUE;
        int min_index = -1;
        for (int v = 0; v < size; v++)
        	// if a vertex has not been visited/processed <[ isVisited[v] == false ]>
        	// AND the stored distance to that vertex from the source index is less than infinity <[ && dist[v] <= min ]>
        	// update min to lower value and set min_index to current index (v)
            if (isVisited[v] == false && dist[v] <= min) { 
                min = dist[v]; 
                min_index = v; 
            }
        // return last updated min_index
        return min_index; 
    }

	/*
	 * ASSIGNED
	 * 
	 * getNeighbors method(s)
	 * 
	 * returns a list of adjacent vertices for the passed vertex
	 * 
	 * takes an index as the argument
	 */
	public Vertex<T>[] getNeighbors(int v) {
		Vertex<T>[] list = new Vertex[this.vertices[v].getAdjLength()];
		int l = 0;
		// iterate through the edges array and find values greater than 0
		while(l < list.length) {
			for(int i = 0; i < this.size; i++) {
				if(this.edges[v][i] > 0) {
					list[l] = this.vertices[i];
					l++;
				}
			}
		}
		return list;
	}

	// Vertex Class
	class Vertex<T> {

		private int adjLength;
		private T data;

		// default constructor
		public Vertex() {
			
		}

		// Vertex constructor
		public Vertex(T data) {
			this.adjLength = 0;
			this.data = data;
		}

		// data getter
		public T getData() {
			return this.data;
		}

		// adjLength getter
		public int getAdjLength() {
			return this.adjLength;
		}

	}

	/*
	 * ASSIGNED
	 * 
	 * print helper method
	 * 
	 * prints an adjacency matrix
	 */
	public void print() {
		System.out.println("\rAdjacency Matrix (0 == no Edge || !0 == Edge length) :\r");
		System.out.print("\t");
		for(int v = 0; v < this.size; v++) {
			System.out.print( v + "\t");
		}
		System.out.println("\r");
		for(int v = 0; v < this.size; v++) {
			System.out.print(v );
			for(int e = 0; e < this.size; e++) {
				System.out.print("\t" + edges[v][e]);
			}
			System.out.println("\r");
		}
	}

	
	// main method for testing the class
	public static void main(String[] args) throws FileNotFoundException {
		
		File file = new File("data.in");
		Scanner in = new Scanner(file);
		
		// first line in file contains integer to define number of graph vertices, and therefore the size of the edges array
		int entries = in.nextInt();
		
		// instantiate the graph object
		MyGraph<String> map = new MyGraph<String>(entries);
		
		// go to next line on file
		in.nextLine();
		
		// some nice ux bits...
		System.out.println("Building Graph...");
		System.out.println("Adding Vertices...");
		// add the vertices
		for(int i = 0; i < entries; i++) {
			String next = in.nextLine();
			map.insertVertex(i, next);
			System.out.println("Added index [" + i + "] : " + next);
		}
		System.out.println("Adding Edges...");
		// add the edges
		while(in.hasNext()) {
			int x = in.nextInt();
			int y = in.nextInt();
			int z = in.nextInt();
			map.addEdge(x, y, z);
		}
		in.close();
		System.out.println("Build Complete!\r");
		
		// test the print method
		map.print();
		
		Scanner input = new Scanner(System.in);
		
		// test the getNeighbors method
		System.out.println("\rEnter a Vertex to get a list of it's neighbors (Use the index from the list above, -1 to quit) :");
		while (input.hasNext()) {
			int index = input.nextInt();
			if (index == -1) {
				break;
			} else if(index > -1 && index < map.size ) {
				MyGraph<String>.Vertex<String>[] list = map.getNeighbors(index);
				if (list.length > 0) {
					System.out.println(map.vertices[index].getData() + "'s neighbors are :");
					for(int i = 0; i < list.length; i++) {
						System.out.println(list[i].getData());
					}
				} else {
					System.out.println(map.vertices[index].getData() + " has no neighbors! They are sad and lonely. :-(");
				}
				
			} else {
				System.out.println("Please use valid input!");
			}
			System.out.println("\rEnter a Vertex to get a list of it's neighbors (Use the index from the list above, -1 to quit) :");
		}
		
		// test the getDistance method
		System.out.println("\rEnter two connected vertices to find the length of their edge (press Enter after each one) :");
		while (input.hasNext()) {
			if(input.hasNextInt()) {
				// get two vertices, allow exit on either entry point
				int x = input.nextInt();
				if (x == -1) {
					break;
				}
				int y = input.nextInt();
				if (y == -1) {
					break;
				}
				int d = map.getDistance(x, y);
				if(x == y) {
					System.out.println(map.vertices[x].getData() + " and " + map.vertices[y].getData() + " are the same Vertex! (returned " + d + ")");
				} else if(d == Integer.MAX_VALUE) {
					System.out.println("Those vertices are not connected! (returned " + d + " (Interger.MAX_VALUE representing infinity))");
				} else {
					System.out.println("The distance between " + map.vertices[x].getData() + " and " + map.vertices[y].getData() + " is : " + d);
				}
				
			} else {
				System.out.println("Please use valid input!");
			}
			System.out.println("\rEnter two connected vertices to find the length of their edge (press Enter after each one) :");
		}

		// cleanup
		System.out.println("GOODBYE!");
		input.close();
		return;
	}
	
}
