/*
 * Jamie Lewis
 * CST-201
 * 2/12/21
 * Instructor Jeremy Wright
 * 
 * Project 4 - Maze Solver
 * 
 * MyQueue Class
 * 
 * This is my own work.
 */

package project4;

public class MyQueue<Item> {

	private Node front;
	private Node back;
	private int size;
	
	/*
	 * constructor
	 */
	public MyQueue() {
		this.front = null;
		this.back = null;
		this.size = 0;
	}
	
	/*
	 * push an item to the back of the queue
	 */
	public void push(Item i) {
		Node n = new Node();
		n.item = i;
		if(this.size == 0) {
			this.front = n;
			this.back = null;
			n.next = null;
		} else if(this.size == 1) {
			this.front.next = n;
			this.back = n;
			n.next = null;
		} else {
			this.back.next = n;
			this.back = n;
		}
		this.size ++;
	}
	
	/*
	 * pop (remove) an item from the front of the queue
	 */
	
	public void pop() {
		if(this.front != null) {
			this.front = this.front.next;
			this.size --;
		}
	}
	
	/*
	 * returns true if the queue is empty
	 */
	public boolean empty() {
		if(this.size == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	/*
	 * returns the number of items in the queue
	 */
	public int size() {
		return this.size;
	}
	
	/*
	 * access the front node's item
	 */
	public Item front() {
		if(this.front != null) {
			return this.front.item;
		} else {
			return null;
		}
	}
	
	/*
	 * private node class
	 */
	private class Node {
		Item item;
		Node next;
	}
	
	/*
	 * toString override helper method
	 */
	public String toString() {
		Node n = this.front;
		StringBuilder s = new StringBuilder();
		s.append("[");
		for(int i = 0; i < this.size; i++) {
			s.append(" " + n.item);
			n = n.next;
		}
		s.append(" ]");
		
		return s.toString();
	}
	
}
