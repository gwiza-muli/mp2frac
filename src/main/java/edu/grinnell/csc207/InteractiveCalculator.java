package edu.grinnell.csc207;

import java.io.PrintWriter;
import java.util.Scanner;
import edu.grinnell.csc207.util.BFCalculator;
import edu.grinnell.csc207.util.BFRegisterSet;
import edu.grinnell.csc207.util.BigFraction;
/**
 * An implementation of an interactive Calculator.
 *
 * @author Sheilla Muligande
 */

public class InteractiveCalculator {
  /**
   * an interactive calculator.
   * @param args the big fractions that will be calculated.
   */
  public static void main(String[] args) {
    PrintWriter pen = new PrintWriter(System.out, true);
    Scanner eyes = new Scanner(System.in);
    BFCalculator newCalculator = new BFCalculator();
    BFRegisterSet newRegister = new BFRegisterSet();

    pen.print("Enter an expression to evaluate, STORE, or QUIT:");


    for (;;) {
      pen.flush();
      String input = eyes.nextLine();
      if (input.equals("QUIT")) {
        break;
      } // if

      String[] splitInput = null;
      if (input.startsWith("STORE")) {
        splitInput = input.split(" ");
        char register = 0;

        for (int i = 1; i < splitInput.length; i++) {
          if (splitInput[i].length() == 1) {
            register = splitInput[i].charAt(0);
            break;
          } // if
        } // for

        if (register != 0) {
          newRegister.store(register, newCalculator.get());
        } else {
          pen.printf("Error: invalid register.");
        } // if
        continue;
      } //if

      splitInput = input.split(" ");
      BigFraction result = new BigFraction(0, 1);
      String function = null;

      for (String inputPiece : splitInput) {
        if (inputPiece.equals("+") || inputPiece.equals("-") || inputPiece.equals("/")
            || inputPiece.equals("*")) {
          function = inputPiece;
        } else {
          BigFraction val;
          if (Character.isLetter(inputPiece.charAt(0))) {
            val = newRegister.get(inputPiece.charAt(0));
          } else {
            val = new BigFraction(inputPiece);
          } //if
          if (function == null) {
            result = val;
          } else if (function.equals("+")) {
            result = result.add(val);
          } else if (function.equals("*")) {
            result = result.multiply(val);
          } else if (function.equals("-")) {
            result = result.subtract(val);
          } else if (function.equals("/")) {
            result = result.divide(val);
          } // if



        } // if

      } // for

      newCalculator.add(result);
      pen.printf("%s\n", result);

    } // for



  } // main(String[])

} // class Interact
