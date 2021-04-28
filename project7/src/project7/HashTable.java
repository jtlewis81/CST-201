/*
 * Jamie Lewis
 * CST-201
 * 3/7/21
 * Instructor Jeremy Wright
 * 
 * Project 7 - Hash Table
 * 
 * ASSIGNMENT INSTRUCTIONS :
 * For this assignment, you will implement a hash table and use it to store words in a large text file.
 * Your hash table should use chaining for collision resolution. You may design any hash function you wish.
 * Your program will read a file and insert each word into a hash table. Once the table is populated, allow
 * the user to enter words to search for. For each word entered, your program should report the number of
 * elements inspected and whether or not the word was located.
 * 
 * NOTES : I implemented my HashTable to use a generic data type.
 * I also used modified versions of some helper methods from previous assignments.
 * 
 * This program is my own work.
 */

package project7;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import project7.HashTable.LinkedList.Node;

public class HashTable<Key, Value> {

	// HashTable attributes
	
	// the table is an array of linked lists
	LinkedList[] table;
	// the size of the array is used to generate an index using hash()
	int size;
	// this is for counting the number of elements inspected when searching for a key
	// incremented in search() and must be reset after a search is complete
	int seekCount = 0;
	// this is auxiliary for counting the total unique elements in the table
	int eCnt = 0;

	/*
	 * constructor must be passed an int value to create the table size
	 */
	public HashTable(int i) {
		this.size = i;
		this.table = new LinkedList[i];
		for (int x = 0; x < i; x++) {
			this.table[x] = new LinkedList(null);
		}
	}
	
	/*
	 * insert method for the table array
	 * 
	 * calls insert on the linked list as a hashed index
	 */	
	public void insert(Value val) {
		this.table[hash(val)].insert(val);
	}
	
	/*
	 * get method for the table array
	 * 
	 * calls get on the linked list
	 */
	public Value get(Value key) {
		return (Value) this.table[hash(key)].get(key);
	}
	
	/*
	 * search method for the table array
	 * 
	 * calls search on the linked list
	 */
	public Value search(Value key) {
		return (Value) this.table[hash(key)].search(key);
	}
	
	/*
	 * seekCount getter method
	 */
	public int getSeekCount() {
		return seekCount;
	}
	
	/*
	 * getEcount getter method
	 */
	public int getEcount() {
		return eCnt;
	}
	
	/*
	 * resetSeekCount method
	 * 
	 * resets seekCounter to 0 must be called after any get() call, similar to
	 * needing to close a Scanner, except with logic repercussions if you don't
	 */
	public void resetSeekCount() {
		this.seekCount = 0;
	}

	/*
	 * LinkedList class
	 */
	class LinkedList<Key, Value> {
		
		// a linked list is a node
		Node head;
		
		// constructor
		private LinkedList(Key key) {
			this.head = new Node(key);
		}
		/*
		 * insert method for LinkedList
		 * 
		 * TIME COMPLEXITY : O(1)
		 * We get the index from our hash function and insert directly to that index with no iteration of the table
		 */
		private void insert(Key key) {
			Node n = new Node(key);
			n.next= this.head;
			this.head = n;
			eCnt++;
		}
		
		// get method for LinkedList
		private Value get(Key key) {
			Node curr = this.head;
			while(curr.value != null) {
				if (curr.value.equals(key)) {
					return (Value) curr.value;
				} else {
					curr = curr.next;
				}
			}
			return null;
		}
		
		/*
		 *  search method for LinkedList
		 *  
		 *  TIME COMPLEXITY : Worst case is O(n), where n == length of a chain at any index in the table's array. Best Case is O(1).
		 *  Theoretically, the worst case could be O(1), but we have collisions due to this class not using a perfect hashing function.
		 */
		private Value search(Key key) {
			Node curr = this.head;
			while(curr.value != null) {
				seekCount++;
				if (curr.value.equals(key)) {
					return (Value) curr.value;
				} else {
					curr = curr.next;
				}
			}
			return null;
		}
		
		// Node subclass of LinkedList
		public class Node<Value> {

			Value value;
			Node next;
			
			private Node(Value value) {
				this.value = value;
				this.next = null;
			}
		}
	}
	
	/*
	 * hashing method
	 */
	public int hash(Value v) {
		int val = 0;
		String s = v.toString();
		for (int i = 0; i < s.length(); i++) {
			val += s.charAt(i);
		}
		val = val % size;
		return val;
	}
	
	/*
	 *cleanUp helper method 
	 */
	public static String cleanUp(String str) {
		StringBuilder sb = new StringBuilder();
		char[] c = str.toCharArray();
		for (int i = 0; i < str.length(); i++) {
			if (((c[i] >= 65) && (c[i] <= 90)) || ((c[i] >= 96) && (c[i] <= 122)) || c[i] == 39 || c[i] == 45) {
				sb.append(c[i]);
			}
		}
		str = sb.toString();
		return str;
	}
	
	/*
	 * Print helper method
	 */
	public void print() {
		for(int i = 0; i < this.size-1; i++) {
			if (this.table[i].head.value != null) {
				System.out.print("[" + i + "] \t: ");
			}
			Node curr = this.table[i].head;
			while(curr.value != null) {
				System.out.print(curr.value + ", ");
				if(curr.next != null) {
					curr = curr.next;
				}
			}
			if (this.table[i].head.value != null) {
				System.out.print("\r");
			}
		}
	}
	
	/*
	 * Main method
	 */
	public static void main(String[] args) throws FileNotFoundException {
		
		File file = new File("text.in");
		Scanner s = new Scanner(file);

		HashTable<Integer, String> ht = new HashTable<>(997);
		
		// read from file
		while (s.hasNext()) {
			String str = cleanUp(s.next());
			if (ht.get(str) == null) {
				ht.insert(str);
			}
		}
		// close file scanner
		s.close();

		// display the populated table
		System.out.println("Populated Table :");
		System.out.println("\r************************************************************************************\r");
		ht.print();
		System.out.println("\r************************************************************************************\r");
		
		Scanner in = new Scanner(System.in);
		String word;
		
		System.out.println("Total unique elements added : " + ht.getEcount());
		
		// get user input
		System.out.println("\rEnter string to search for (-1 to quit) : ");
		while (in.hasNext()) {
			if (in.hasNextInt()) {
				if (in.nextInt() == -1) {
					break;
				} else {
					System.out.println("Bad input, please try again!");
				}
			} else {
				word = in.next();
				if (ht.search(word) != null) {
					System.out.println("\"" + word + "\" found at table index [" + ht.hash(word) + "] after " + ht.getSeekCount() + " elements inspected.");
				} else {
					System.out.println("\"" + word + "\" not found after " + ht.getSeekCount() + " elements inspected. The index would have been [" + ht.hash(word) + "]");
				}
				ht.resetSeekCount();
			}
			System.out.println("\rEnter string to search for (-1 to quit) : ");
		}
		// cleanup
		System.out.println("\rGOODBYE!");
		in.close();
		return;
	}
}
