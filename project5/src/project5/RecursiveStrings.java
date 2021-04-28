/*
 * Jamie Lewis
 * CST-201
 * 2/21/21
 * Instructor Jeremy Wright
 * 
 * Project 5 - Recursive Strings
 * 
 * The assignment is to write three methods which process a String recursively.
 * 
 * This code is my own work.
 */

package project5;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RecursiveStrings {
	
	public static void main(String[] args) throws FileNotFoundException {
		
		// file input
		File file = new File("text.in");
		Scanner scan = new Scanner(file);
		
		// first line of file is the expected number of strings
		int numOfStrings = scan.nextInt();
		
		// test SLOPs
		System.out.println("SLOPS OUTPUT");
		for(int i = 0; i < numOfStrings; i++) {
			String next = scan.next();
			if(isSlop(next)) {
				System.out.println("YES");
			} else {
				System.out.println("NO");
			}
		}
		System.out.println("END OF OUTPUT");
		
/* additional SLOPs for testing - copy/paste into text.in file
	6
	AHDFG
	ADFGCDFFFFFG
	ABAEFGCCDFEFFFFFG
	AHDFGA
	DFGAH
	ABABCC
*/

		// test SLAPs - don't forget to change file name!
//		System.out.println("SLAPS OUTPUT (" + numOfStrings + ")");
//		for(int i = 0; i < numOfStrings; i++) {
//			String next = scan.next();
//			System.out.println(next + " = " + isSlap(next));
//		}
//		System.out.println("END OF OUTPUT");
		
		// test SLIPS - don't forget to change file name!
//		System.out.println("SLIPS OUTPUT (" + numOfStrings + ")");
//		for(int i = 0; i < numOfStrings; i++) {
//			String next = scan.next();
//			System.out.println(next + " = " + isSlip(next));
//		}
//		System.out.println("END OF OUTPUT");
		
		// close Scanner
		scan.close();
	} // end of main program loop
	
	
	/*
	 * isSlop() public method
	 * 
	 * A Slop is a character string that consists of a Slap followed by a Slip.
	 */
	public static boolean isSlop(String str) {
		// base case for a slop is that it starts with an "A" and ends with a "G"
		if(!(str.startsWith("A") && str.endsWith("G"))) {
			// if not, immediately return false
			return false;
		} else {
			// else, process through private recursive method,
			// passing an index of the second char since we already know the first is an "A"
			return isSlop(str, 1);
		}
	}
	
	// private recursive method for isSlop()
	private static boolean isSlop(String str, int offset) {
		// iterate the offset through the length of the string, splitting the string into two substrings
		// checking for a Slap before the offset and a Slip after the offset
		while(offset <= str.length()) {
			// if valid substrings, return true
			if(isSlap(str.substring(0, offset)) && isSlip(str.substring(offset, str.length()))) {
				return true;
			// else increment offset and make recursive call
			} else {
				offset++;
				return isSlop(str, offset);
			}
		}
		// return false if no valid substring combination
		return false;
	}

	
	/*
	 * isSlap() method
	 * 
	 * A Slap is a character string that has the following properties:
		•	Its first character is an 'A'
		•	If it is a two-character Slap then its second and last character is an 'H'
		•	If it is not a two-character Slap, then it is in one of these two forms:
			o	'A' followed by 'B' followed by a Slap, followed by a 'C'
			o	'A' followed by a Slip (see above) followed by a 'C'
		•	Nothing else is a Slap
	 */
	public static boolean isSlap(String str) {
		int offset = 0;
		// if string is 2 chars and not "AH", or if does not start with "A", return false
		if(!str.startsWith("A") || (str.length() == 2 && !str.contentEquals("AH"))) {
			return false;
		// else if string is 2 chars and == "AH", return true
		} else if(str.length() == 2 && str.contentEquals("AH")) {
			return true;
		// else check recursively
		} else {
			// if "AB + Slap + C", the isSlap() in the if statement is the recursive call, iterating down to the core of the string from the ends, checking for another slap "AH", "AB...C", or "A...C" in the middle of the string
			if(str.startsWith("AB") && str.endsWith("C") && isSlap(str.substring(offset+2, str.length()-1))) {
				return true;
			// if "A...C", check for a Slip in the middle 
			} else if (str.startsWith("A") && str.endsWith("C")) {
				// increment offset and set another int variable to 2 indexes past it (min length of a Slip is 3)
				offset++;
				int i = offset+2;
				// loop through the string, incrementing the second index each time till end of the string is reached, return true if a valid Slip is found
				while (i < str.length()) {
					// pass the substring to the isSlip() recursive method
					if (isSlip(str.substring(offset, i))) {
						return true;
					} else {
						i++;
					}
				}
				// if no Slip is found, the while loop fails and returns false
				return false;
			// if none of the above conditions are met, the string is not a slap, return false
			} else {
				return false;
			}
		}
	}
	
	
	/*
	 * isSlip() public method
	 * 
	 * A Slip is a character string that has the following properties:
		•	Its first character is either a 'D' or an 'E'
		•	The first character is followed by a string of one or more 'F's
		•	The string of one or more 'F's is followed by either a Slip or a 'G'
		•	The Slip or 'G' that follows the F's ends the Slip. For example, DFFEFFFG is a Slip since it has a 'D' for its first character, followed by a string of two F's, and ended by the Slip 'EFFFG'
		•	Nothing else is a Slip
	 */
	public static boolean isSlip(String str) {
		// if string does not meet base case for true, return false
		if (!((str.startsWith("D") || str.startsWith("E")) && str.contains("F") && str.endsWith("G"))) {
			return false;
		// else iterate with private recursive method
		} else {
			return isSlip(str, 0);
		}
	}
	
	// private recursive method for isSlip()
	private static boolean isSlip(String str, int offset) {
		// if char at current offset index == one of these, increment offset and call this method again with new offset value (recursion!)
		if(str.startsWith("DF", offset) || str.startsWith("EF", offset) || str.startsWith("F", offset)) {
			offset++;
			return isSlip(str, offset);
		// if the current index char is the last char in the string and is a "G", return true
		} else if (str.startsWith("G", offset) && (offset == str.length()-1)) {
			return true;
		// an invalid char is in the string, it is not a slip, return false
		} else {
			return false;
		}
	}
}
