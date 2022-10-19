import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class LinesOfCode {
	public static long count;
	public static long forLoops;
	public static long ifStatements;
	public static long whileLoops;
	public static long tryStatements;

	/**
	 * LinesOfCode is the constructor
	 * 
	 */
	public LinesOfCode() {

	}
	
	/**
	 * displayOutput takes all the counts and displays them back to main
	 * 
	 */
	public static String displayOutput() {
		return "Lines of Code: " + count + "\nFor Loops: " + forLoops + "\nIf Statements: " + ifStatements + "\nWhile Loops: " + whileLoops + "\nTry: " + tryStatements;
	}

	/**
	 * readLines takes the directory of the file and uses it to traverse the text file and count the lines of code.
	 * This excludes blank lines and comments
	 * 
	 */
	public static void readLines(String fileName) {
		count = 0;

		try {
			@SuppressWarnings("resource")
			BufferedReader reader = new BufferedReader(new FileReader(fileName));
			String currentLine = null;

			while ((currentLine = reader.readLine()) != null) {
				currentLine = currentLine.trim();

				if (currentLine.equals(" ") || currentLine.startsWith("//")
						|| currentLine.startsWith("/*") && currentLine.endsWith("*/")) {
					continue;
				} else {
					count++;
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * countingLoops goes through the file and counts the amount of times a certain loop has been used
	 * 
	 */
	public static void countingLoops(String path) {
		try {
			Scanner input = new Scanner(new File(path));
			String str = "";
			String forWord = "for";
			String ifWord = "if";
			String whileWord = "while";
			String tryWord = "try";

			// goes through the text lines
			while (input.hasNext()) {
				str += input.next() + " ";
			}

			String temp[] = str.split(" ");
			for (int i = 0; i < temp.length; i++) {
				if (forWord.equals(temp[i])) {
					forLoops++;
				} else if (ifWord.equals(temp[i])) {
					ifStatements++;
				}else if(whileWord.equals(temp[i])) {
					whileLoops++;
				}else if(tryWord.equals(temp[i])) {
					tryStatements++;
				}
			}
			input.close();
		} catch (FileNotFoundException e) {
			System.out.println();
		}
	}

}
