package project4;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class BinaryMazeGenerator {

	public static void main(String[] args) throws IOException {
		// change these to customize the size of the maze
		int rows = 20;
		int cols = 20;
		
		// File and writer
		File file = new File("Maze1.in");
		PrintWriter pw = new PrintWriter(file);
		
		// add rows and cols to first line of file
		pw.append(rows + " " + cols + "\n");
		
		// maze array
		int[][] maze = new int[rows][cols];
		for(int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				Random r = new Random();
				maze[i][j] = r.nextInt(2);
			}
		}
		
		// replace 2 random indexes with a start and end value
		Random randomRow = new Random();
		Random randomCol = new Random();
		maze[randomRow.nextInt(rows)][randomCol.nextInt(cols)] = 3; // start
		maze[randomRow.nextInt(rows)][randomCol.nextInt(cols)] = 4; // end
		
		// print the maze to the file
		for(int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				pw.append(maze[i][j] + " ");
			}
			pw.append("\n");
		}
		
		// close the file writer
		pw.close();
	}

}
