/*
 * Jamie Lewis
 * CST-201
 * 2/6/21
 * Instructor Jeremy Wright
 * 
 * Project 3: Doubly-Linked List
 * 
 * The instructions for this project are to build specified methods for the class
 * so that the program outputs an expected result. A certain amount of code has been
 * supplied that is not to be altered. Although, I did clean up some white space.
 * 
 * I have added a separator to distinguish my code from the supplied code as follows:
 * 
 * "//		********************************************************************************************** \/"
 * 		(My code...)
 * "//		********************************************************************************************** /\"
 * 
 * TIME COMPLEXITY (Big O) ANALYSIS:
 * 
 * 		InsertOrderUnique() : O(n), where (n == the number of nodes in the list).
 * 
 * 		Merge() : O(n + m), where (n == the number of nodes in the calling list) && (m == the number of nodes in the parameter list).
 * 				  Each list is only iterated through 1 time!
 * 				  In my video, I made a comment about this maybe being O(2(n+m)), but then realized that is still O(n+m).
 * 
 * The added code is my own work.
 */

package project3;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * provided file for DLinkedList Assignment 
 *
 * @author lkfritz
 */
public class DLinkedList<T extends Comparable<T>> {

    public static void main(String[] args) throws FileNotFoundException {

        DLinkedList<String> lst1 = new DLinkedList<>();
        DLinkedList<String> lst2 = new DLinkedList<>();        

        Scanner fin = new Scanner(new File("text1.in"));
        String str;

        while (fin.hasNext()) {
            str = fin.next();
            str = cleanUp(str);           
            lst1.insertOrderUnique(str);
        }
        fin.close();

        fin = new Scanner(new File("text2.in"));
        while (fin.hasNext()) {
            str = fin.next();
            str = cleanUp(str);
            lst2.insertOrderUnique(str);
        }

        System.out.println("List 1:  " + lst1);
        System.out.println("List 2:  " + lst2);
        
        DLinkedList combined = lst1.merge(lst2);
        
        System.out.println("\nAFTER MERGE");
        System.out.println("List 1:  " + lst1);
        System.out.println("List 2:  " + lst2);
        System.out.println("\n" + combined);
    }
    

    /**
     * ASSIGNED
     * @param str
     * @return str in all lower case with LEADING and TRAILING non-alpha
     * chars removed
     */
    public static String cleanUp(String str) {
    	
//      ********************************************************************************************** \/ DONE
    	
    	// use StringBuilder rather than += operator
    	StringBuilder sb = new StringBuilder();
		char[] c = str.toCharArray();
	    // loop through char[]
    	for(int i = 0; i < str.length(); i++) {
    		// if any char is upper case
    		if ((c[i] >= 65) && (c[i] <= 90)) {
    			// make lowercase and add to new String
    			c[i] = (char) (c[i] + 32);
    			sb.append(c[i]);
    		// if first or last char is not alphabetic
    		} else if (((i == 0) || (i == str.length()-1)) && (!(c[i] >= 97) && !(c[i] >= 122))) {
    			// ignore it, do nothing
    		} else {
    			// add the lowercase letters
    			sb.append(c[i]);
    		}
		}
    	str = sb.toString();
    	
//      ********************************************************************************************** /\ DONE
    	
        return str;
    }

    //inner DNode class:  PROVIDED
    private class DNode {

        private DNode next, prev;
        private T data;

        private DNode(T val) {
            this.data = val;
            next = prev = this;
        }
    }

    //DLinkedList fields:  PROVIDED
    private DNode header;

    //create an empty list:  PROVIDED
    public DLinkedList() {
        header = new DNode(null);
    }

    /**
     * PROVIDED add
     *
     * @param item return ref to newly inserted node
     */
    public DNode add(T item) {
        //make a new node
        DNode newNode = new DNode(item);
        //update newNode
        newNode.prev = header;
        newNode.next = header.next;
        //update surrounding nodes
        header.next.prev = newNode;
        header.next = newNode;
        return newNode;
    }

    //PROVIDED
    public String toString() {
        String str = "[";
        DNode curr = header.next;
        while (curr != header) {
            str += curr.data + " ";
            curr = curr.next;
        }
        str = str.substring(0, str.length() - 1);
        return str + "]";
    }

    /**
     * ASSIGNED
     * remove val from the list
     *
     * @param val
     * @return true if successful, false otherwise
     */
    public boolean remove(T val) {
    	
//      ********************************************************************************************** \/ DONE
    	
    	DNode cNode = this.header.next;
    	while(cNode != header) {
    		if(cNode.data == val) {
    			// set pointers of prev and next nodes to cut out the node containing the passed value
    			cNode.prev.next = cNode.next;
    			cNode.next.prev = cNode.prev;
    			break;
    		} else if (cNode.next == null){
    			// the value was not found in the list
    			return false;
    		}
    		cNode = cNode.next;
    	}
    	
//      ********************************************************************************************** /\ DONE
    	
        return true;
    }

    /**
     * ASSIGNED
     *
     * @param item
     */
    public void insertOrder(T item) {
    	
//      ********************************************************************************************** \/ DONE

    	// Create new node with passed item
    	DNode newNode = new DNode(item);
    	// pointer for iteration
    	DNode cNode = this.header.next;
    	// if list is empty, add 1st node after header
    	if(this.header.next.data == null) {
    		// link header to new node
			cNode.prev.next = newNode;
			newNode.prev = cNode.prev;
			newNode.next = header;
			header.prev = newNode;
    		return;
    	}
    	// else iterate...
    	while(cNode != header) {    		
    		// if comparable returns 0 or greater, the new item is lesser than cNode.data value,
    		// insert new node in prev position
    		if(cNode.data.compareTo(item) >= 0) {
    			// link previous node to new node
    			cNode.prev.next = newNode;
    			newNode.prev = cNode.prev;
    			// link new node to current node as next
    			newNode.next = cNode;
    			cNode.prev = newNode;
    			return;
    		// if reached header, add to end of list
    		} else if (cNode.next == header){
    			newNode.prev = cNode;
    			cNode.next = newNode;
    			newNode.next = header;
    			header.prev = newNode;
    			return;
    		}
    		cNode = cNode.next;
    	}
    	
//      ********************************************************************************************** /\ DONE
    }

    /**
     * ASSIGNED
     *
     * @param item
     */
    public boolean insertOrderUnique(T item) {
    	
//		********************************************************************************************** \/ DONE
    	
    	// Create new node with passed item
    	DNode newNode = new DNode(item);
    	// pointer for iteration
    	DNode cNode = this.header.next;
    	// if list is empty, add 1st node after header
    	if(this.header.next.data == null) {
    		// link header to new node
			cNode.prev.next = newNode;
			newNode.prev = cNode.prev;
			newNode.next = header;
			header.prev = newNode;
			return true;
    	}
    	// else iterate...
    	while(cNode != header) {    		
    		// if comparable returns 0 or greater, the new item is lesser than cNode.data value,
    		// insert new node in prev position
    		if(cNode.data.compareTo(item) > 0) {
    			// link previous node to new node
    			cNode.prev.next = newNode;
    			newNode.prev = cNode.prev;
    			// link new node to current node as next
    			newNode.next = cNode;
    			cNode.prev = newNode;
    			return true;
    		// if value already exists in list
    		} else if(cNode.data.compareTo(item) == 0) {
    			return false;
    		// if reached header, add to end of list
    		} else if (cNode.next == header){
    			newNode.prev = cNode;
    			cNode.next = newNode;
    			newNode.next = header;
    			header.prev = newNode;
    			return true;
    		}
    		cNode = cNode.next;
    	}
    	
//      ********************************************************************************************** /\ DONE
    	
        return true;
    }

    /**
     * ASSIGNED
     * PRE:  this and rhs are sorted lists
     * @param rhs
     * @return list that contains this and rhs merged into a sorted list
     * POST:  returned list will not contain duplicates
     */
    public DLinkedList merge(DLinkedList rhs) {
        DLinkedList result = new DLinkedList();
    	
//      ********************************************************************************************** \/ DONE
        
        // node pointers
 		DNode cNode1 = this.header.next;
		DNode cNode2 = rhs.header.next;
		DNode rNode = result.header.next;
        // if both lists are empty
 		if((cNode1 == null) && (cNode2 == null)) {
     			return null;
 		}
 		// if one of the lists are empty
 		if (cNode1 == null) {
 			return rhs;
 		}
 		if (cNode2 == null) {
 			return this;
 		}
 		// add first node to result
 		if(cNode1.data.compareTo(cNode2.data) < 0) {
 			// if this is bigger, insert and iterate this
 			rNode = new DNode(cNode1.data);
 			cNode1 = cNode1.next;
 			rNode = rNode.next;
 		} else if(cNode1.data.compareTo(cNode2.data) > 0) {
 			// if rhs is bigger, insert and iterate rhs
 			rNode = new DNode(cNode2.data);
 			cNode2 = cNode2.next;
 			rNode = rNode.next;
 		} else {
 			// if compared values are equal, add one and iterate both lists
 			rNode = new DNode(cNode1.data);
 			cNode1 = cNode1.next;
 			cNode2 = cNode2.next;
 			rNode = rNode.next;
 		}
 		// link new node to result.header
		rNode.prev = result.header;
		result.header.next = rNode;
		rNode.next = result.header;
		result.header.prev = rNode;
 		// iterate while both lists have nodes...
 		while((cNode1 != this.header) && (cNode2 != rhs.header)) { // if either list is empty, exit the loop
 			// if this is bigger, insert and iterate this
 			if(cNode1.data.compareTo(cNode2.data) < 0) {
 	 			rNode = new DNode(cNode1.data);
    			cNode1 = cNode1.next;
 			// if rhs is bigger, insert and iterate rhs
 			} else if(cNode1.data.compareTo(cNode2.data) > 0) {
 	 			rNode = new DNode(cNode2.data);
    			cNode2 = cNode2.next;
 			// if compared values are equal, add one and iterate both lists
 			} else {
 	 			rNode = new DNode(cNode1.data);
    			cNode1 = cNode1.next;
    			cNode2 = cNode2.next;
 			}
 			// link new node to existing result nodes
			rNode.prev = result.header.prev;
			rNode.next = result.header;
			result.header.prev = rNode;
			rNode.prev.next = rNode;
			// iterate result to check loop conditions again...
			rNode = rNode.next;
 		}
 		// check which list still has values and add all of them to end of new list
 		if(cNode1 != this.header) {
 			while(cNode1 != this.header) {
 				rNode = new DNode(cNode1.data);
 				rNode.prev = result.header.prev;
 				rNode.next = result.header;
 				result.header.prev = rNode;
 				rNode.prev.next = rNode;
 				rNode = rNode.next;
 				cNode1 = cNode1.next;
 			}
 		} else {
 			while(cNode2 != rhs.header) {
 	 			rNode = new DNode(cNode2.data);
 	 			rNode.prev = result.header.prev;
				rNode.next = result.header;
				result.header.prev = rNode;
				rNode.prev.next = rNode;
				rNode = rNode.next;
				cNode2 = cNode2.next;
 			}
 		}
 		// clear header of each original list, the old nodes will be removed through garbage collection
    	this.header.next = this.header.prev = this.header;
    	this.header.prev = this.header.next = this.header;
    	rhs.header.next = rhs.header.prev = rhs.header;
    	rhs.header.prev = rhs.header.next = rhs.header;
    	
//      ********************************************************************************************** /\ DONE
    	
        return result;
    }

}