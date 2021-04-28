/*
 * Jamie Lewis
 * CST-201
 * 1/30/21
 * Instructor Jeremy Wright
 * 
 * Project 1: MyString
 * 	
 * This is the MyString class file, containing all required constructors and methods
 * required by the assignment instructions. 
 * 
 * This is my own work.
 */

package project1;

public class MyString {

// ATTRIBUTES
	
	private char[] charArray;
	private int curr_length;

// CONSTRUCTORS
	
	// Default Constructor
	public MyString() {
		this.charArray = null;
		this.curr_length = 0;
	}
	
	// Constructor taking a String arg
	public MyString(String s) {
		this.charArray = s.toCharArray();
		this.curr_length = s.length();
	}

	// Copy Constructor
	public MyString(MyString ms) {
		this.charArray = ms.charArray;
		this.curr_length = ms.curr_length;
	}

// METHODS
	
	/*
	 * length Method
	 * 
	 * getter for the curr_length attribute
	 * 
	 * constant single operation method -> O(1)
	 */
	public int length() {
		return curr_length;
	}
	
	/*
	 * ensureCapacity Method
	 * 
	 * Private Setter for curr_length?
	 * used in concat()
	 */
	private void ensureCapacity(int i) {
		// create a new charArray with a size of the passed int 
		this.charArray = new char[i];
		// set the current length
		this.curr_length = i;
	}
	
	/*
	 * toString Method
	 * 
	 * @overrides Object toString
	 * 
	 * linear time operation method -> O(n)
	 * n == number of elements in calling object's charArray
	 * each element gets 1 operation
	 */
	public String toString() {
		// variable to store first element of calling object's charArray
//		char c = this.get(0);
		// convert that variable to a String
//		String s = String.valueOf(c);
		String s = "";
		// starting index variable for the loop
		int i = 0;
		// add each next char to the String object
		while (i < this.length()) {
			s += this.get(i);
			i++;
		}
		// return the String
		return s;
	}
	
	/*
	 * concat Method
	 * 
	 * concatenates the passed MyString onto the calling MyString object
	 * calls ensureCapacity() to set the curr_length of a new charArray
	 */
	public MyString concat(MyString ms) {
		// length of the calling object
		int l = this.length();
		// length to set the new charArray to in ensureCapacity()
		int newL = l + ms.length();
		// save current charArray elements
		char[] temp = this.charArray;
		// reset object to new charArray with newL length
		this.ensureCapacity(newL);
		// add old elements back into new charArray
		for(int i = 0; i < l; i++) {
			this.charArray[i] = temp[i];
		}
		// index variable for parameter object
		int x = 0;
		// add new elements to the end of charArray		
		for(int j = l; j < newL; j++) {
			this.charArray[j] = ms.charArray[x];
			x++;
		}
		// return the modified object
		return this;
	}
	
	/*
	 * equals Method
	 * 
	 * compares memory addresses
	 */
	public boolean equals(MyString ms) {
		if (this == ms) {
			return true;
		} else {
			return false;
		}
	}
	
	/*
	 * compareTo Method
	 * 
	 * returns 1 if the parameter object is alphabetically before the calling object,
	 * 	as in, they are NOT in alphabetical order from left to right with in the statement.
	 * returns -1 if the parameter object is alphabetically after the calling object,
	 * 	as in, they are in alphabetical order from left to right with in the statement.
	 * returns 0 if compared strings are equal
	 */
	public int compareTo(MyString ms) {
		// return variable
		int r = 0;
		// loop through calling object indexes
		for (int i = 0; i < this.length() + 1; i++) {
			// compare current index to same index of parameter object
			if (this.get(i) > ms.get(i)) {
				// return 1 if any index of the calling object is alphabetically after that of the parameter object
				r = 1;
				// break out of the loop if condition is met
				break;
			// return -1 if any index of the calling object is alphabetically before that of the parameter object
			} else if ((this.get(i) < ms.get(i)) || ((i == this.length()) && (ms.get(i + 1) != 0))) {
				r = -1;
				break;
			// don't change return value from default and move to next index
			} else {
				r = 0;
			}
		}
		// returns 0
		return r;
	}
	
	/*
	 * get Method
	 * 
	 * returns the character at the requested index, or a blank space if the index is out of bounds
	 */
	public char get(int i) {
		// check if requested index is in bounds of the calling object
		if (i < this.curr_length) {
			// returns the character at this index 
			return this.charArray[i];
		} else {
			// returns a blank space: " "
			return 0;
		}
	}
	
	/*
	 * toUpper Method
	 * 
	 * returns the calling object in all CAPS
	 */
	public MyString toUpper() {
		for (int i = 0; i < this.length(); i++) {
			this.charArray[i] = Character.toUpperCase(this.charArray[i]);
		}
		return this;
	}

	/*
	 * toLower Method
	 * 
	 * returns the calling object in all lowercase
	 */
	public MyString toLower() {
		for (int i = 0; i < this.length(); i++) {
			this.charArray[i] = Character.toLowerCase(this.charArray[i]);
		}
		return this;
	}
	
	/*
	 * substring Method
	 * 
	 * takes a single int parameter and returns a String object built from that index forward out of the calling object
	 */
	public String substring(int i) {
		String s = "";
		for (int x = i; x < this.length(); x++) {
			s += this.get(x);
		}
		return s;
	}

	/*
	 * substring Method
	 * 
	 * takes 2 int parameters
	 * first on acts as in substring(i)
	 * second parameter determines length of returned String
	 */
	public String substring(int n, int m) {
		String s = "";
		for (int x = n; x < m; x++) {
			s += this.get(x);
		}
		return s;
	}
	
	/*
	 * indexOf Method
	 * 
	 * returns the first index of a matching MyString
	 * 
	 * room for optimization?
	 * maybe needs to check that the calling object is indeed longer than the parameter object?
	 * 
	 * number of operations affected by number of elements in calling object * number of elements in parameter object
	 * as well as the difference in length of the two objects
	 * some other factors as well, not sure of exact BigO notation
	 */
	public int indexOf(MyString ms) {
		// index variable for the calling MyString object
		int i = 0;
		// loop through the elements of the calling object until there will not be enough room left to fit the parameter object
		while (i < this.length()) {
			// index variable for the parameter object
			int y = 0;
			// when the value of the index of the calling object matches
			// the value of the first index of the parameter object
			if (this.get(i) == ms.get(y)) {
				// start a new index variable for the calling object index
				// so it doesn't mess up our place in the outer loop and we can return it for a completed match
				int x = i;
				// iterate through elements of both objects simultaneously for as long as they match
				while (this.get(x) == ms.get(y)) {
					// if the end of the parameter object is reached and all elements match
					if (y == (ms.length()-1)) {
						// return starting index of this loop stored in i variable
						return i;
					// until end of parameter object is reached
					} else {
						// increment index of both objects
						x++;
						y++;
					}
				}
			}
			// increment index of calling object when it
			// does not match the first element in the parameter object
			i++;
		}
		// return -1 if no match found
		return -1;
	}
	
	/*
	 * lastIndexOf Method
	 * 
	 * returns the last index of a matching MyString
	 * 
	 * works in reverse of indexOf()
	 * only the iteration of the calling object had to be reversed to make this work
	 * optimized by reversing the order of both object iterations
	 */
	public int lastIndexOf(MyString ms) {
		// length of the MyString we are searching for 
		int l = ms.length();
		// LAST index of the calling MyString object that can be the starting index for the length of the parameter object
		int i = this.length() - l;
		// loop backwards through the elements of the calling object until not enough elements left
		// to search that will fit the length of parameter object
		while (i >= 0) {
			// variable for the parameter object's LAST index
			int y = (l-1);
			// when the value of the index of the calling object matches
			// the value of the last index of the parameter object
			if (this.get(i) == ms.get(y)) {
				// start a new index variable for the calling object index
				// so it doesn't mess up our place in the outer loop and we can return it for a completed match
				int x = i;
				// iterate backwards through both objects simultaneously for as long as they match
				while (this.get(x) == ms.get(y)) {
					// if the beginning of the parameter object is reached and all elements match
					if (y == 0) {
						// return current index of the calling object in this loop
						return x;
					// until beginning of parameter object is reached
					} else {
						// decrement index of both objects
						x--;
						y--;
					}
				}
			}
			// decrement index of calling object when it
			// does not match the last element in the parameter object
			i--;
		}
		// return -1 if no match found
		return -1;
	}
}
