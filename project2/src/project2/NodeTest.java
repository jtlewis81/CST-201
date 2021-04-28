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
 */

package project2;

public class NodeTest {

	public static void main(String[] args) {
		// TEST MyList Class
		
		System.out.println("Creating a new list called myList1 with the default constructor MyList(). Calling myList1.size() tells us it exists and is empty.");		
		MyList myList1 = new MyList();
		System.out.println("myList1 size = " + myList1.size());
		
		System.out.println("\rNow, let's insert() a value and then use our printList() helper method to print the value.");
		myList1.insert(1);
		myList1.printList();
		
		System.out.println("\rNow, we'll make a new list and give it 2 new values.");
		MyList myList2 = new MyList();
		myList2.insert(2);
		myList2.insert(4);
		System.out.println("myList2:");
		myList2.printList();
		
		System.out.println("\rNext, we'll call myList2.front(): " + myList2.front() + ", and mylist2.back(): " + myList2.back() + "."
				+ "\nNow, let's use another helper method to get the values of them: " + myList2.front().getData() + ", " + myList2.back().getData());
		
		System.out.println("\rLet's create a couple of new lists, myList3 and myList4, and print them out.");
		System.out.println("myList3:");
		MyList myList3 = new MyList();
		myList3.insert(3);
		myList3.insert(5);
		myList3.insert(7);
		myList3.printList();
		System.out.println("myList4:");
		MyList myList4 = new MyList();
		myList4.insert(4);
		myList4.insert(5);
		myList4.insert(6);
		myList4.insert(7);
		myList4.printList();
		
		System.out.println("\rLet's merge() them into mergedList, notice duplicate values are removed.");
		MyList mergedList = myList3.merge(myList4);
		mergedList.printList();
		
		System.out.println("Copying mergedList to mergedList2 with \"MyList mergedList2 = new MyList(mergedList);\": ");
		MyList mergedList2 = new MyList(mergedList);
		System.out.println("mergedList2:");
		mergedList2.printList();
		
		System.out.println("\rRemoving head and tail values of mergedList2 with pop_front() and pop_back() methods.");
		mergedList2.pop_front();
		mergedList2.pop_back();
		System.out.println("mergedList2:");
		mergedList2.printList();
		System.out.println("And for reference, mergedList:");
		mergedList.printList();
		System.out.println("BUG: pop_front only affects the calling list, but pop_back affects all copies of a list.");

		System.out.println("\rLet's go back to myList1, try empty(), use pop_front() to remove the only value, and then try empty() again."
				+ "\rWe'll print the size after each empty() call as well.");
		System.out.println("empty() returns: " + myList1.empty());
		System.out.println("myList1 size = " + myList1.size());
		System.out.println("Running pop_front()...");
		myList1.pop_front();
		System.out.println("empty() returns: " + myList1.empty());
		System.out.println("myList1 size = " + myList1.size());
		
		System.out.println("\rAnd finally, let's reverse() mergedList");
				
		mergedList.reverse();
		mergedList.printList();
	}
}
