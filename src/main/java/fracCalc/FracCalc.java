/**
 * @author Mr. Rasmussen
 */

package fracCalc;

import java.util.*;

public class FracCalc {

	public static void main(String[] args) {
		String str = produceAnswer("5_3/4 + 6_5/8");
		System.out.println(str);
		// TODO: Read the input from the user and call produceAnswer with an equation

	}

	public static String userInput() {
		Scanner userInput = new Scanner(System.in);
		System.out.println("Type equation below:");
		String x = userInput.nextLine();
		userInput.close();
		return x;
	}

	// ** IMPORTANT ** DO NOT DELETE THIS FUNCTION. This function will be used to
	// test your code
	// This function takes a String 'input' and produces the result
	//
	// input is a fraction string that needs to be evaluated. For your program, this
	// will be the user input.
	// e.g. input ==> "1/2 + 3/4"
	//
	// The function should return the result of the fraction after it has been
	// calculated
	// e.g. return ==> "1_1/4"
	public static String produceAnswer(String in) {
		String i = in;
		int space = i.indexOf(" ");
		String str = "";
		if (Character.isWhitespace(i.charAt(space + 2))) {
			str = (i.substring(space + 3));
			if (str.indexOf("/") != -1) {
				int frac = str.indexOf("/");
				if (str.indexOf("_") != -1) {
					int dash = str.indexOf("_");
					String wholeNumber = str.substring(0, dash);
					String num = str.substring(dash + 1, frac);
					String denom = str.substring(frac + 1);
					return "whole:" + wholeNumber + " numerator:" + num + " denominator:" + denom;
				} else {
					return "whole:0 numerator:" + str.substring(0, str.indexOf('/')) + " denominator:"
							+ str.substring(str.indexOf('/') + 1);
				}

			} else {
				return "whole:" + str + " numerator:0 denominator:1";
			}
		}

		// "1/2 + 3/4"

		// TODO: Implement this function to produce the solution to the input

		return str;
	}

	// TODO: Fill in the space below with any helper methods that you think you will
	// need

}
