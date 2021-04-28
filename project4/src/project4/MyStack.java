/*
 * Jamie Lewis
 * CST-201
 * 2/12/21
 * Instructor Jeremy Wright
 * 
 * Project 4 - Maze Solver
 * 
 * MyStack Class
 * 
 * This is my own work.
 */

package project4;

public class MyStack<Item> {

	private Node top;
	private int size;
	
	/*
	 * constructor
	 */
	public MyStack() {
		this.top = null;
		this.size = 0;
	}
	
	/*
	 * push an item to the top of the stack
	 */
	public void push(Item i) {
		Node n = new Node();
		n.item = i;
		if(this.size == 0) {
			this.top = n;
			n.next = null;
		} else {
			n.next = this.top;
			this.top = n;
		}
		this.size ++;
	}
	
	/*
	 * pop (remove) an item from the top of the stack
	 */
	
	public void pop() {
		if (this.top != null && this.top.next != null) {
			this.top = this.top.next;
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
	public Item top() {
		if(this.top != null) {
			return this.top.item;
		} else {
			return null;
		}
	}
	
	
	private class Node {
		Item item;
		Node next;
	}
	
	/*
	 * toString override helper method
	 */
	public String toString() {
		Node n = this.top;
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
