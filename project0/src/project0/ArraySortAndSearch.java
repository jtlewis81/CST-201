/*
 * Jamie Lewis
 * CST-201
 * 1/28/21
 * Instructor Jeremy Wright
 * 
 * Project 0: Array Warm-Up
 * 	
 * The assignment is to write a program that reads text from a file, storing one
 * word into each element of an array which has a capacity for 10,000 Strings.
 * We then print that array, run it through a sorting method, and then print the
 * sorted array. Next, the user is asked to search for a word. If the word is found
 * it states as much and gives the index where the word is located in the array. If
 * the word is not found in the array, the output says so. If the user enters the
 * number 0, the program exits. We will demonstrate the program and then discuss the
 * sorting and searching methods.
 */

package project0;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class ArraySortAndSearch {
	
	// Create array with space for 10,000 Strings
	static String[] words = new String[10000];
	
	public static void main(String[] args) throws FileNotFoundException {
		
		// File and scanner objects
		File text = new File("text.txt");
		Scanner fileReader = new Scanner(text);
		
		// Read words from file and add to the array
		int index = 0;
		while (fileReader.hasNext()) {
			words[index] = fileReader.next();
			index++;
		}
		
		// Close the scanner to stop memory leak
		fileReader.close();
		
		// Read unsorted array
		printArray(words);
		
		// Sort the array
		sortArray(words);
		
		// Read sorted array
		printArray(words);
		
		// take user input and call binary search function on it, give appropriate output
		// new Scanner object
		Scanner input = new Scanner(System.in);
		
		// ask for user input
		System.out.println("Enter a word to search for (Enter 0 to quit):\n");
		
		// loop keeps input open for user until they enter a "0" to quit
		while (input.hasNext()) {
			String token = input.next();
			// check for a "0"
			if (token.contentEquals("0")) {
				// close input Scanner
				input.close();
				// say "goodbye"
				System.out.println("Thank you for playing!");
				// get out of the loop
				break;
			}
			// find index of user's input
			int searchIndex = searchArray(words, token);
			// print output according to whether or not a match for the input was found in the array
			if (searchIndex >= 0) {
				// input found
				System.out.println("\"" + token + "\" is in the array (at least once) at index: " + searchIndex);
			} else {
				// input not found
				System.out.println("\"" + token + "\" is not in the array!\n");
			}
		}
	}
	
	// END OF MAIN PROGRAM LOOP***************************************
	
	// HELPER METHODS*************************************************
	
	/*
	 * Printer Helper Method
	 * 
	 * Supposed to print 5 items on a line with white space between the items.
	 * For some reason, the first line gets 6 items, but it is not important to the assignment.
	 */
	public static void printArray(String[] array) {		
		for (int x = 0; x < array.length; x++) {
			if (array[x] != null) {
				if ((x > 0) && (x % 5 == 0)) {
					System.out.println(array[x]);
				} else {
					System.out.print(array[x] + "  ");
				}
			}
		}
		System.out.println();
	}
	
	/*
	 * Sorting Helper Method (Selection Sort)
	 * 
	 * For a selection sort, we take an array of items and iterate through them.
	 * Each one gets compared to every other item and the lowest value item is saved
	 * in the first array index. Then we iterate to the next index and do the same,
	 * ignoring previous indexes until the array has been worked all the way through,
	 * leaving the highest value item at the end. It is possible for an item to remain
	 * at the same index after sorting has been completed.
	 */
	public static void sortArray(String[] array) {
		// iterate through the array indexes
		for (int a = 0; a < (array.length - 1); a++) {
	        // compare the first element to each other element
			for (int b = a + 1; b < array.length; b++) {
				// ignore null array elements
	        	while(array[b] != null) {
	        		// do comparison, if another element is of lower value, switch them,
	        		// leaving the lowest value in the current element that the outer
	        		// for loop is iterated to
	        		if(array[a].compareTo(array[b]) > 0) {
	        			// temp variable for swapping
	        			String temp = array[a];
	        			array[a] = array[b];
	        			array[b] = temp;
	 	            } else {
	 	            	// if the current comparison does not require a swap,
	 	            	// jump back out to the inner for loop
	 	            	break;
	 	            }
	 	        }
	        }
		}
	}
	
	/*
	 * Search Helper Method (Binary Search)
	 * 
	 * The binary search method uses low, high, and mid integer values to point to an index.
	 * I added a helper method to set the high value to the last index in the array that is not null.
	 * A while loop is used to see when the high and low index values pass each other.
	 * The high and low values are used to calculate a middle (mid) index. That index is checked for
	 * a match to the searched word. If it is not a match, the compareTo method returns a value.
	 * If the value is negative, we need to set the high index to 1 less than the current mid index.
	 * If it is a positive value, we set the low index to 1 more than the current mid index.
	 * On the next while loop cycle, the mid index is recalculated using the new low/high indexes
	 * and the check is done again. If a match is found, the index number (mid) is returned by the
	 * method. If the low index value gets incremented to higher than the high
	 * index value, a match was not found and -1 is returned.
	 */
	public static int searchArray(String[] array, String word) {
		// index variables
		// low defaults to 0
		int low = 0;
		// high is set by calling the helper method
		int high = findNotNull(array);
		// loop runs until low is a higher value than high
		while (low <= high) {
			// calculate the mid index value
			int mid = low + (high - low) / 2;
			// compare the searched word to the mid index
			int check = word.compareTo(array[mid]);
			// if a match is found, return the index
			if (check == 0) {
				return mid;
			// or increment/decrement the low/high index values accordingly
			} else if (check > 0) {
				low = mid+1;
			} else {
				high = mid-1;
			}
		}
		// only reached if no match is found
		return -1;
	}
	
	/*
	 * Helper Method to determine how full the array is for Binary Search
	 * 
	 * This just iterates through the array until a null value is found and then returns
	 * the previous index number to the Binary Search Method. I made this private because
	 * this method could go in a separate class for the Binary Search method, and it
	 * would be private in that case, although it really doesn't matter for this assignment if it
	 * is private or public.
	 */
	private static int findNotNull(String[] array) {
		int maxIndex = 0;
		for (int i = 0; i < array.length -1; i++) {
			if (array[i] == null) {
				maxIndex = i - 1;
				return maxIndex;
			}
		}
		return maxIndex;
	}
}