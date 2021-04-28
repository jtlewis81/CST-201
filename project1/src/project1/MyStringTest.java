/*
 * Jamie Lewis
 * CST-201
 * 1/30/21
 * Instructor Jeremy Wright
 * 
 * Project 1: MyString
 * 	
 * This file is the driver program to test the MyString class file.
 * The heavy use of System.out statements makes the code very self-commenting.
 * Every constructor and method is tested.
 * 
 * This is my own work.
 */

package project1;

public class MyStringTest {

	public static void main(String[] args) {
		
		// Test variables
		String s1 = "Computer";
		String s2 = "Programmer";
		String s3 = "Java";
		String s4 = "Developer";
		MyString e = new MyString("e");
		
		
		// TEST CONSTRUCTORS
		System.out.println("Testing CONSTRUCTORS:");
		
		System.out.println("\rTesting default constructor:");
		MyString defConstruct = new MyString();
		if (defConstruct.length() != 0) {
			System.out.println(defConstruct);
		} else {
			System.out.println("This MyString object is empty!");
		}
		System.out.println("But the object does exist! Here is its hashcode: " + defConstruct.hashCode());
		
		System.out.println("\rTesting non-default constructor, passing a String to myString1 variable:");
		MyString myString1 = new MyString(s1);
		System.out.println("MyString1 = " + myString1.toString());
		
		System.out.println("\rTesting Copy Constructor (MyString myString2 = new MyString(myString1)):");
		MyString myString2 = new MyString(myString1);
		System.out.println("MyString2 = " + myString2);
		
		// TEST METHODS
		
		System.out.println("\rThe toString() method was demonstrated in Constructor tests.");

		System.out.println("\rTesting the length() method:");
		System.out.println("Default Constructor object contains " + defConstruct.length() + " characters.");
		System.out.println("\"" + myString1 + "\" contains " + myString1.length() + " characters.");
		
		System.out.println("\rTesting the concat() method, which also uses the private ensureCapacity() method:");
		System.out.println("First we'll change myString2 to a new string: Programmer.");
		myString2 = new MyString(s2);
		System.out.println("MyString1 + MyString2 = " + myString1.concat(myString2));
		
		System.out.println("\rTesting the equals() method:");
		MyString myString3 = new MyString(s3);
		MyString myString4 = new MyString(s3);
		System.out.println("First, we'll instantiate a couple new MyString objects, giving them the same value:\r"
				+ "myString3 = " + myString3 + " and myString4 = " + myString4);
		System.out.println("Now we'll compare them with equals(): " + myString3.equals(myString4));
		myString3 = myString4;
		System.out.println("But if we set myString3 = myString4: " +  myString3.equals(myString4));
		
		System.out.println("\rTesting the compareTo() method:");
		System.out.println("Let's reset myString4 to its own memory space and give it the same value as myString3.");
		myString4 = new MyString(s3);
		System.out.println("myString3.compareTo(myString4) = " + myString3.compareTo(myString4));
		myString4 = new MyString(s4);
		System.out.println("Now let's set myString4 to another new value: " + myString4);
		System.out.println("So, \"" + myString3 + "\" compared to \"" + myString4 + "\" = " + myString3.compareTo(myString4));
		System.out.println("And, \"" + myString1 + "\" compared to \"" + myString3 + "\" = " + myString1.compareTo(myString3));
		myString2 = new MyString(s1);
		System.out.println("Let's reset myString2 to the first part of myString1. \"" + myString1
				+ "\" compared to \"" + myString2 + "\" = " + myString1.compareTo(myString2));
		
		System.out.println("\rWe already used the get() method inside some other methods,"
				+ "\rbut as an example, the 8th index of \"" + myString1 + "\" is : " + myString1.get(8));
		System.out.println("And if we try to get an index that is out of bounds, we get: \"" + myString3.get(5) + "\"");
		
		System.out.println("\rTesting toUpper() and toLower() methods using myString3:");
		System.out.println("\"" + myString3 + "\" toUpper() = " + myString3.toUpper());
		System.out.println("\"" + myString3 + "\" toLower() = " + myString3.toLower());
		
		System.out.println("\rTesting the substring() methods:");
		System.out.println("Let's pull a substring of \"" + myString4 + "\" starting at the 4th character, index 3: "
				+ myString4.substring(3));
		myString1 = new MyString(myString1.substring(0,8));
		System.out.println("Now, for fun, let's use substring() with 2 int parameters to reset the value of myString1 from"
				+ " ComputerProgrammer\rto just Computer using [myString1 = new MyString(myString1.substring(0,8));]: " + myString1);
		
		System.out.println("\rFinally, let's test indexOf() and lastIndexOf() methods. We'll use myString4, \"" + myString4 + "\","
				+ " and get the first and last indexes of character \"e\": ");
		System.out.println("The first occurrence of \"e\" in " + myString4 + " is at index: " + myString4.indexOf(e));
		System.out.println("The last occurrence of \"e\" in " + myString4 + " is at index: " + myString4.lastIndexOf(e));
		System.out.println("If a value does not occur in the MyString object, such as by searching for \"e\" in " + myString3
				+" (one return for each method): " + myString3.indexOf(e) + ", " + myString3.lastIndexOf(e));		
	}
}
