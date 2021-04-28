/*
 * Jamie Lewis
 * CST-201
 * 2/3/21
 * Instructor Jeremy Wright
 * 
 * Project 2: Singly-Linked List
 * 
 * This project is to use a singly linked node class to implement
 * a singly-linked list class that maintains its elements in
 * ascending order.
 * 
 * 
 * This is my own work.
 * 
 * Note: for iterating, I use "cNode" for the current node being pointed to.
 */

//*****************Assignment Details*************************|
/*															  |
	Implement the following methods in your class:			  |
															  |
	What it does:						How it's called:	  |
	A default constructor 				list<T> myList		  |TESTED
	A copy constructor 					list<T> myList(aList) |TESTED
	Access to first element				myList.front()		  |TESTED
	Access to last element				myList.back()		  |TESTED
	Insert value 						myList.insert(value)  |TESTED
	Remove value at front				myList.pop_front()	  |TESTED
	Remove value at tail				myList.pop_back()	  |TESTED
	Determine if empty					myList.empty()		  |TESTED
	Return # of elements				myList.size()		  |TESTED
	Reverse order of elements in list	myList.reverse()	  |TESTED
	Merge with another ordered list		myList.merge(aList)	  |TESTED
______________________________________________________________|
*/

package project2;

public class MyList {

	// first and last node references
	private Node head;
	private Node tail;
	
	// CONSTRUCTORS
	
	public MyList () {
		this.head = null;
		this.tail = null;
	}
	
	/*
	 * Copy Constructor
	 * 
	 * creates a shallow copy, which is fine since we are using a
	 * primitive int object for our node's data type
	 */
	public MyList(MyList list) { 
		this.head = list.head;
		this.tail = list.tail;
	}
	
	// METHODS
	
	/*
	 * front method
	 * 
	 * returns : Node
	 * 
	 * Time Complexity = O(1), always references only one value
	 * to which we already have a reference
	 */
	public Node front() {
		return this.head;
	}

	/*
	 * back method
	 * 
	 * returns : Node
	 * 
	 * Time Complexity = O(1), always references only one value
	 * to which we already have a reference
	 */
	public Node back() {
		return this.tail;
	}

	/*
	 * insert method
	 * 
	 * param : int
	 * 
	 * inserts a new value into the list by creating a new node and linking
	 * appropriately, maintains ascending order of list values
	 * 
	 * Time Complexity = O(n), because the entire list may have to be iterated
	 * if the new node goes at the end.
	 */
	public void insert(int data) {
		// create new node with the passed data
		Node n = new Node(data);
		// set new node to head if the list is empty
		if (this.head == null) {
			this.head = n;
			return;
		}
		// check if new data is less than the head element data
		if (this.head.data > data) {
			n.next = this.head;
			this.head = n;
			return;
		}
		// check if the list has a tail element (list is at least 2 elements) and
		// if so, new data is greater than the tail data
		// this might save us from iterating the entire loop
		if ((this.tail != null) && (this.tail.data < data)) {
			this.tail.next = n;
			this.tail = n;
			return;
		}
		// else, set the head to a cNode
		Node cNode = this.head;
		// iterate...
		while (cNode != null) {
			// check if the new data is the same as cNode's data
			if (cNode.data == data) {
				// if so, return without creating duplicate items in the list
				// check to see if the new data should come before cNode's data
				return;
			// if list only had one element, add to tail
			} else if(cNode.next == null) {
				cNode.next = n;
				this.tail = n;
				return;
			// insert somewhere in middle
			} else if ((cNode.data < data) && (cNode.next.data > data)) {
				n.next = cNode.next;
				cNode.next = n;
			}
			// iterate to next node
			cNode = cNode.next;
		}
	}
	
	/*
	 * pop_front method
	 * 
	 * removes the first element from the list
	 * 
	 * this does not affect the head of a copied list, only this list
	 * 
	 * Time Complexity = O(1), because we have a reference to the only element we access every time.
	 */
	public void pop_front() {
		if (this.head.next == null) {
			this.head = null;
			return;
		}
		this.head = this.head.next;
	}
	
	/*
	 * pop_back method
	 * 
	 * removes the last node from all copies of a list that reference this same node
	 * 
	 * Time Complexity = O(n), where (n = the length of the list)
	 */
	public void pop_back() {
		Node pNode = null;
		Node cNode = this.head;
		while (cNode.next != null) {
			pNode = cNode;
			cNode = cNode.next;
		}
		// set element before last to the tail
		this.tail = pNode;
		// set the value of next to null, removing the last element from the list
		pNode.next = null;
	}
	
	/*
	 * empty method
	 * 
	 * returns : boolean
	 * 
	 * returns true if the head of the list is null, and false if it is not
	 * 
	 * Time Complexity = O(1), because it only checks one reference every time
	 */
	public boolean empty() {
		if (this.head != null) {
			return false;
		} else {
			return true;
		}
	}
	
	/*
	 * size method
	 *  
	 * returns : int
	 *  
	 * returns the number of elements in the list as an int
	 *  
	 * Time Complexity = O(n), where (n = size of the list)
	 */
	public int size() {
		int size = 0;
		Node cNode = this.head;
		// if head is null, list is empty, will skip loop and return default size of 0
		while (cNode != null) {
			if(cNode.next != null) {
				size ++;
				cNode = cNode.next;
			} else {
				size++;
				return size;
			}
		}
		return size;
	}
	
	/*
	 * reverse method
	 * 
	 * reorders the list to descending order
	 * 
	 * Time Complexity = O(n), where (n = length of the list)
	 */
	public void reverse() {
		// three node pointers
        Node pNode = null;
		Node cNode = this.head;
        Node nNode = null;
        // iterate through the list with a while loop
        while (cNode != null) {
        	// set next to current
        	nNode = cNode;
        	// move current to next
        	cNode = cNode.next;
        	// set next to null on first iteration - makes it the new tail
        	// subsequent iterations point it to the previous current
        	nNode.next = pNode;
        	// set previous to next
            pNode = nNode;
            // set head to next
            this.head = nNode;
        }
	}
	
	/*
	 * merge method
	 * 
	 * param: MyList
	 * returns: MyList
	 * 
	 * returns this list with elements from parameter list merged together, nullifies the other list
	 * 
	 * NOTE: This is NOT the most efficient algorithm, but it is a lot simpler code.  One way to make
	 * this process possibly more efficient would be to see if one list's head value is larger or
	 * smaller then the other list's tail value since these are ordered lists, we could then just add
	 * one list to the end of the other.
	 * 
	 * Time Complexity = O(n^2), where (n = length of longest list).
	 * Could be O(n), where (n = total elements of both lists), if we iterated and did some complex comparisons,
	 * adding the elements to a temporary array and returning that array. But, this does function.
	 */
	public MyList merge(MyList list) {
		// These first couple of cases should rarely happen because of garbage collection
		// if both lists are empty
		if((this.head == null) && (list.head == null)) {
			return null;
		}
		// if one of the lists are empty
		if (this.head == null) {
			return list;
		}
		if (list.head == null) {
			return this;
		}
		// if neither list is null, insert shorter list elements into longer list
		// and nullify shorter list's head and tail
		Node cNode;
		if(this.size() <= list.size()) {
			cNode = list.head;
			while(cNode != null) {
				this.insert(cNode.data);
				cNode = cNode.next;
			}
			// let other list go to garbage collection
			list.head = null;
			list.tail = null;
			return this;
		} else {
			cNode = this.head;
			while(cNode != null) {
				list.insert(cNode.data);
				cNode = cNode.next;
			}
			// let other list go to garbage collection
			this.head = null;
			this.tail = null;
			return list;
		}
	}
	
	/*
	 * printList helper method
	 */
	public void printList() {
		if (this.empty() == false) {
			Node cNode = this.head;
			while (cNode != null) {
				System.out.println(cNode.getData());
				cNode = cNode.getNext();
			}
		} else {
			System.out.println("The list is empty!");
		}
	}
	
//******************************************************************************************
	
	/*
	 * Node Class
	 * 
	 * NOTE: would normally be private class, made class public, privatized attributes,
	 * and added public methods for testing in driver program
	 */
	public class Node {
		
		// node data value
		private int data;
		// reference to next node : the single link of the list!
		private Node next;
		
		/*
		 * Node Constructor
		 */
		private Node(int data) {
			this.data = data;
			this.next = null;
		}
		
		/*
		 * data Getter
		 */
		public int getData() {
			return this.data;
		}
		
		/*
		 * next Getter
		 */
		public Node getNext() {
			return this.next;
		}
	}
}
