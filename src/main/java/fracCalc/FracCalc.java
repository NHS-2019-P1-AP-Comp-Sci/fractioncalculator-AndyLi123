/**
 * @author Mr. Rasmussen
 */

package fracCalc;

import java.util.*;

public class FracCalc {

	public static void main(String[] args) {
		Scanner userInput = new Scanner(System.in);

		boolean jump = false;
		while (!jump) {
			String in = userInput.nextLine();
			if (in.equals("quit")) {
				jump = true;
				userInput.close();
			} else {
				System.out.println(produceAnswer(in));
			}
		}
		// TODO: Read the input from the user and call produceAnswer with an equation

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

	/*
	 * public static String produceAnswera(String in) {
	 * 
	 * String i = in; int space = i.indexOf(" "); String str = ""; String dtr = "";
	 * if (Character.isWhitespace(i.charAt(space + 2))) { String operator =
	 * i.substring(space + 1, space + 2); str = (i.substring(space + 3)); if
	 * (str.indexOf("/") != -1) { int frac = str.indexOf("/"); // String operator =
	 * i.substring(space+1, space + 2); dtr = (i.substring(0, space)); if
	 * (dtr.indexOf("_") != -1) { int dashx = dtr.indexOf("_"); String wholeNumberx
	 * = dtr.substring(0, dashx); String numx = dtr.substring(dashx + 1, frac);
	 * String denomx = dtr.substring(frac + 1); double wholex =
	 * Double.parseDouble(wholeNumberx); double numeratorx =
	 * Double.parseDouble(numx); double denominatorx = Double.parseDouble(denomx);
	 * double firstFraction = (wholex * denominatorx + numeratorx) / denominatorx;
	 * if (str.indexOf("_") != -1) { int dash = str.indexOf("_"); String wholeNumber
	 * = str.substring(0, dash); String num = str.substring(dash + 1, frac); String
	 * denom = str.substring(frac + 1); double whole =
	 * Double.parseDouble(wholeNumber); double numerator = Double.parseDouble(num);
	 * double denominator = Double.parseDouble(denom); double secondFraction =
	 * (whole * denominator + numerator) / denominator;
	 * 
	 * if (operator.equals("+")) { return (" 1"); } return "whole:" + whole +
	 * " numerator:" + num + " denominator:" + denom; } } else { return
	 * "whole:0 numerator:" + str.substring(0, str.indexOf('/')) + " denominator:" +
	 * str.substring(str.indexOf('/') + 1); }
	 * 
	 * } else { return "whole:" + str + " numerator:0 denominator:1"; } }
	 * 
	 * // "1/2 + 3/4"
	 * 
	 * // TODO: Implement this function to produce the solution to the input
	 * 
	 * return str;
	 * 
	 * }
	 */
	public static String produceAnswer(String in) {

        String first = "";
        String second = "";
        String result = "";
        if (in.indexOf('*') != -1) {
             // if it is multiply
             first = in.substring(0, in.indexOf("*"));
             second = in.substring(in.indexOf("*") + 1);
             first = getFrac(first);
             second = getFrac(second);

             String firstNum = first.substring(0, first.indexOf('/'));
             String firstDenom = first.substring(first.indexOf('/') + 1);

             String secondNum = second.substring(0, second.indexOf('/'));
             String secondDenom = second.substring(second.indexOf('/') + 1);
             result = "" + Integer.parseInt(firstNum) * Integer.parseInt(secondNum) + '/'
                      + Integer.parseInt(firstDenom) * Integer.parseInt(secondDenom);
        } else if (in.indexOf('+') != -1) {
             // if it is addition
             first = in.substring(0, in.indexOf('+'));
             second = in.substring(in.indexOf('+') + 1);
             first = getFrac(first);
             second = getFrac(second);
             String firstNum = first.substring(0, first.indexOf('/'));
             String firstDenom = first.substring(first.indexOf('/') + 1);

             String secondNum = second.substring(0, second.indexOf('/'));
             String secondDenom = second.substring(second.indexOf('/') + 1);
             result = ""
                      + (Integer.parseInt(firstNum) * Integer.parseInt(secondDenom)
                               + Integer.parseInt(secondNum) * Integer.parseInt(firstDenom))
                      + '/' + Integer.parseInt(firstDenom) * Integer.parseInt(secondDenom);
        } else if (in.indexOf(" / ") != -1) {
             // purely division
             first = in.substring(0, in.indexOf(" / "));
             second = in.substring(in.indexOf(" / ") + 2);
             first = getFrac(first);
             second = getFrac(second);

             String firstNum = first.substring(0, first.indexOf('/'));
             String firstDenom = first.substring(first.indexOf('/') + 1);

             String secondNum = second.substring(0, second.indexOf('/'));
             String secondDenom = second.substring(second.indexOf('/') + 1);

             result = "" + Integer.parseInt(firstNum) * Integer.parseInt(secondDenom) + '/'
                      + Integer.parseInt(firstDenom) * Integer.parseInt(secondNum);
        } else if (in.indexOf(" - ") != -1) {
             // subtraction
             first = in.substring(0, in.substring(1).indexOf(" - ") + 1);
             second = in.substring(in.substring(1).indexOf(" - ") + 3);
             first = getFrac(first);
             second = getFrac(second);
             String firstNum = first.substring(0, first.indexOf('/'));
             String firstDenom = first.substring(first.indexOf('/') + 1);

             String secondNum = second.substring(0, second.indexOf('/'));
             String secondDenom = second.substring(second.indexOf('/') + 1);
             result = ""
                      + (Integer.parseInt(firstNum) * Integer.parseInt(secondDenom)
                               - Integer.parseInt(secondNum) * Integer.parseInt(firstDenom))
                      + '/' + Integer.parseInt(firstDenom) * Integer.parseInt(secondDenom);
        } else {
             result = "null";
        }
        System.out.println(first);
        System.out.println(second);
        System.out.println(result);

        result = simplify(getFFrac(result));
        return result;
    }


	public static String getFrac(String in) {
		in = in.replaceAll(" ", "");
		if (in.indexOf('/') == -1)
			return in + "/1";
		else if (in.indexOf('_') == -1)
			return in;
		else {
			boolean isNegative = false;
			if (in.charAt(0) == '-') {
				isNegative = true;
				in = in.substring(1);
			}
			int fst = in.indexOf('_');
			int scd = in.indexOf('/');
			int whole = Integer.parseInt(in.substring(0, fst));
			int num = Integer.parseInt(in.substring(fst + 1, scd));
			int dnm = Integer.parseInt(in.substring(scd + 1));
			String result = whole * dnm + num + "/" + dnm;
			if (isNegative) {
				result = "-" + result;
			}
			return result;
		}
	}

	public static String simplify(String in) {
        if (in.charAt(0) == '-' && in.charAt(1) == '-')
             in = in.substring(2);
        String isNegative = "";
        if (in.charAt(0) == '-') {
             isNegative = "-";
             in = in.substring(1);
        }
        String result = "";
        String process;
        if (in.indexOf('_') != -1) {
             result += in.substring(0, in.indexOf('_') + 1);
             process = in.substring(in.indexOf('_') + 1);
        } else if (in.indexOf('/') != -1) {
             process = in;
        } else {
             return isNegative + in;
        }

        int num = Integer.parseInt(process.substring(0, process.indexOf('/')));
        int dnm = Integer.parseInt(process.substring(process.indexOf('/') + 1));

        if (dnm < 0) {
             if (isNegative.equals("-"))
                 isNegative = "";
             else
                 isNegative = "-";
             dnm = -dnm;
        }

        if (num == 0) {
             if (result.equals(""))
                 return "0";
             else
                 return isNegative + result.substring(0, result.indexOf('_'));
        }

        if (num % dnm == 0) {
             result += num / dnm;
        } else {
             for (int i = 2; i <= num; i++) {
                 if (num % i == 0 && dnm % i == 0) {
                      num /= i;
                      dnm /= i;
                      i--;
                 }
             }
             result += "" + num + '/' + dnm;
        }

        return isNegative + result;
    }

	public static String getFFrac(String in) {
        // given it does not have '_'

        if (in.indexOf('/') == -1)
             return in;
        else {
             in = simplify(in);
             int ind = in.indexOf('/');
             if (ind == -1) {
                 return in;
             } else {
                 int num = Integer.parseInt(in.substring(0, ind));
                 int dnm = Integer.parseInt(in.substring(ind + 1));

                 boolean isNegative = false;

                 if (Math.abs(num) < Math.abs(dnm)) {
                      pp("a");
                      return in;
                 } else if (num % dnm == 0) {
                      return "" + num / dnm;
                 } else {
                      if (num < 0) {
                           isNegative = !isNegative;
                           num = -num;
                      }
                      if (dnm < 0) {
                           isNegative = !isNegative;
                           dnm = -dnm;
                      }
                      String result = num / dnm + "_" + num % dnm + "/" + dnm;
                      if (isNegative)
                           result = '-' + result;
                      return result;
                 }
               }
             }
           }



	public static void pp(String a) {
		System.out.println(a);
	}

	// TODO: Fill in the space below with any helper methods that you think you will
	// need

}
