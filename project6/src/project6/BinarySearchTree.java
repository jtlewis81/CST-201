/*
 * Jamie Lewis
 * CST-201
 * 2/27/21
 * Instructor Jeremy Wright
 * 
 * Project 6 - Binary Search Tree
 * 
 * -------------------------------------------------------------------------------------------------------------------
 * Assignment Instructions:
 * For this assignment, you will implement a binary search tree and use it to store a large text file.
 * Your program should read text from a file and insert each word in a binary search tree. Do not store duplicate text.
 * 
 * Once the tree is populated, print the contents of the tree using an in-order traversal.
 * Next, allow the user to enter words to search for. For each word entered,
 * your program should report the number of elements inspected and whether or not the word was located.
 * Next, allow the user to enter words to remove from the tree. After each removal, print the contents of the tree.
 * -------------------------------------------------------------------------------------------------------------------
 * 
 * The second paragraph of the instructions above is copied into the text.in file for the program to use.
 * The BST class is designed to take a generic type argument (other than the cleanUp() helper method, which would not have to be called)
 * although the driver program is written to process strings.
 * 
 * This program is my own work, although partly consisting of heavily modified code from both the textbook and geeksforgeeks.org.
 * 
 * Loom video link: https://www.loom.com/share/f96408cd014149ecbacbddcb907e084e
 */

package project6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
 * Binary Search Tree Class
 * 
 * takes a generic type
 */
public class BinarySearchTree<T extends Comparable<T>> {

	// BinarySearchTree class attributes
	private Node root;
	// this int is purely for the assignment, not a necessary part of a binary search tree implementation
	private int seekCount;
	
	// Node subclass
	class Node {
		T data;
		Node left;
		Node right;

		// Node constructor
		public Node(T d) {
			this.data = d;
			this.left = null;
			this.right = null;
		}
	}

	// BinarySearchTree constructor
	public BinarySearchTree() {
		root = null;
		seekCount = 0;
	}

	/*
	 * get method
	 * 
	 * acts as search function
	 * calls the private recursive search() method
	 * 
	 * Time Complexity Analysis: BigO = O(h), where h is the height of the tree
	 * 		- returning the root node of the tree is O(1)
	 * 		- if the tree was built from an ordered array or list it will be skewed to one side,
	 * 		  causing h to be equal n, where n is the number of nodes in the tree.
	 * 
	 */
	public Node get(T d) {
		return search(root, d);
	}

	// private recursive method for get()
	private Node search(Node n, T d) {
		seekCount++;
		// if d not found in a node
		if (n == null) {
			return null;
		}
		// traverse left or right until node's data matches searched term 
		if (d.compareTo(n.data) < 0) {
			return search(n.left, d);
		} else if (d.compareTo(n.data) > 0) {
			return search(n.right, d);
		} else {
			// returns node where data matches searched term
			return n;
		}
	}

	/*
	 * put method
	 * 
	 * inserts new Node into tree
	 * calls the private recursive put() method
	 */
	public void insert(T d) {
		root = put(root, d);
	}

	// private recursive method for insert()
	private Node put(Node n, T d) {
		// creates a new node when the bottom of the tree was reached
		// this could be the new data or some data being moved down the tree
		if (n == null) {
			n = new Node(d);
			return n;
		}
		// traverses the tree, comparing new data to existing data
		// moves a lower value to the left child and
		// a higher value to a right child
		if (d.compareTo(n.data) < 0) {
			n.left = put(n.left, d);
		} else if (d.compareTo(n.data) > 0) {
			n.right = put(n.right, d);
		}
		// returns if the value already existed
		return n;
	}

	/*
	 * delete method
	 * 
	 * deletes a node
	 * calls the private recursive remove() method
	 * 
	 * Time Complexity Analysis: BigO = O(h), where h is the height of the tree
	 * 		- we could further optimize by tracking the successor's parent node and setting it's left child to null,
	 * 		  instead of recursively deleting the successor node.
	 * 		- as with the search method, worst case could be that h == n
	 * 
	 * 
	 */
	public void delete(T d) {
		root = remove(root, d);
	}

	// private recursive method for delete()
	private Node remove(Node n, T d) {
		// if the tree is empty
		if (n == null) {
			return n;
		}
		// traverse the tree to find the data to be deleted
		if (d.compareTo(n.data) < 0) {
			n.left = remove(n.left, d);
		} else if (d.compareTo(n.data) > 0) {
			n.right = remove(n.right, d);
		} else {
			// the data matched, so we handle a few possible cases
			
			// the node has only one or no child nodes
			if (n.left == null) {
				return n.right;
			} else if (n.right == null) {
				return n.left;
			}
			
			// the node has 2 children
			// get the successor and set the current node's data value to it's value
			n.data = successor(n.right);
			// delete the successor node - recur farther down the tree
			n.right = remove(n.right, n.data);
		}
		// we reached the bottom of the tree and the data was not found
		return n;
	}

	// successor helper method for node deletion
	// finds the left most child of the right child of the node to be deleted
	private T successor(Node n) {
		T successor = n.data;
		while (n.left != null) {
			successor = n.left.data;
			n = n.left;
		}
		return successor;
	}

	/*
	 * cleanUp helper method
	 * 
	 * copied from project 3 - DLinkedList, still my own work
	 */
	public static String cleanUp(String str) {
		// use StringBuilder rather than += operator
		StringBuilder sb = new StringBuilder();
		char[] c = str.toCharArray();
		// loop through char[]
		for (int i = 0; i < str.length(); i++) {
			// if any char is upper case
			if ((c[i] >= 65) && (c[i] <= 90)) {
				// make lowercase and add to new String
				c[i] = (char) (c[i] + 32);
				sb.append(c[i]);
				// if first or last char is not alphabetic
			} else if (((i == 0) || (i == str.length() - 1)) && (!(c[i] >= 97) && !(c[i] >= 122))) {
				// ignore it, do nothing
			} else {
				// add the lowercase letters
				sb.append(c[i]);
			}
		}
		str = sb.toString();
		return str;
	}

	/*
	 * printer helper method
	 * 
	 * print all nodes' data values using in-order tree traversal
	 * calls a private recursive print() method
	 */
	public void print() {
		print(root);
	}
	// private recursive print method
	private void print(Node n) {
		if (n != null) {
			print(n.left);
			System.out.print(n.data + ", ");
			print(n.right);
		}
	}

	/*
	 * getSeekCount method
	 * 
	 * seekCount getter
	 */
	public int getSeekCount() {
		return this.seekCount;
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

	//---------------------------------MAIN METHOD------------------------------------
	
	/*
	 * Driver program to test the BinarySearchTree class
	 */
	public static void main(String[] args) throws FileNotFoundException {
		
		// file and scanner objects
		File file = new File("text.in");
		Scanner s = new Scanner(file);
		
		// declare a new BinarySearchTree (BST) object
		BinarySearchTree<String> bst = new BinarySearchTree<>();

		// loop through the tokens in a file and add them, to the BST
		while (s.hasNext()) {
			// use cleanUp() helper
			String str = cleanUp(s.next());
			// insert data into tree
			bst.insert(str);
		}
		// close the scanner that reads the file
		s.close();

		// print the tree's data
		bst.print();

		// new scanner to get user input
		Scanner in = new Scanner(System.in);
		
		// variable to use for printing to console
		String search;

		// get user input for searching the tree
		System.out.println("\rEnter string to search for (-1 to quit) : ");
		while (in.hasNext()) {
			if (in.hasNextInt()) {
				if (in.nextInt() == -1) {
					break;
				} else {
					System.out.println("Bad input, please try again!");
				}
			} else {
				search = in.next();
				if (bst.get(search) != null) {
					System.out.println("\"" + search + "\" found after " + bst.getSeekCount() + " elements inspected.");
				} else {
					System.out.println(
							"\"" + search + "\" not found after " + bst.getSeekCount() + " elements inspected.");
				}
				bst.resetSeekCount();
			}
			System.out.println("\rEnter string to search for (-1 to quit) : ");
		}

		// get user input for deleting nodes
		System.out.println("\rEnter string to remove (-1 to quit) : ");
		while (in.hasNext()) {
			if (in.hasNextInt()) {
				if (in.nextInt() == -1) {
					break;
				} else {
					System.out.println("Bad input, please try again!");
				}
			} else {
				search = in.next();
				if (bst.get(search) != null) {
					bst.delete(search);
					bst.print();
				} else {
					System.out.println("\"" + search + "\" does not exist in the tree!");
				}
			}
			System.out.println("\r\rEnter string to remove (-1 to quit) : ");
		}

		System.out.println("GOODBYE!");
		
		// close scanner
		in.close();
	}

}
