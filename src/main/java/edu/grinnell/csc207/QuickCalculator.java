package edu.grinnell.csc207;

// Sheilla Muligande
// Course: CSC207-01
// Instructor: Sam Rebelsky
// Date:9/14/2024
// File: Cipher.java

import edu.grinnell.csc207.util.BFCalculator;
import edu.grinnell.csc207.util.BFRegisterSet;
import edu.grinnell.csc207.util.BigFraction;

// import edu.grinnell.csc207.util.CipherUtils;

/**
 * An implementation of the Caesar and Vigenere Ciphers.
 *
 * @author Sheilla Muligande
 */
public class QuickCalculator {
  /**
   * an interactive calculator.
   * @param args the big fractions that will be calculated.
   */
  public static void main(String[] args) {
    BFCalculator newCalculator = new BFCalculator();
    BFRegisterSet newRegister = new BFRegisterSet();

    for (int i = 0; i < args.length; i++) {
      String arg = args[i];

      String[] splitInput = arg.split(" ");
      BigFraction result = null;
      // new BigFraction(0, 1);
      String operator = null;

      for (int x = 0; x < splitInput.length; x++) {
        String inputPiece = splitInput[x];

        if (inputPiece.equals("STORE")) {
          if (x + 1 < splitInput.length && splitInput[x + 1].length() == 1) {
            char register = splitInput[x + 1].charAt(0);
            newRegister.store(register, newCalculator.get());
          } else {
            System.err.println("ERROR");
          } // if
          continue;
        } else if (inputPiece.equals("+") || inputPiece.equals("-") || inputPiece.equals("/")
            || inputPiece.equals("*")) {
          operator = inputPiece;
        } else if (Character.isLetter(inputPiece.charAt(0))) {
          BigFraction val = newRegister.get(inputPiece.charAt(0));
          // if (Character.isLetter(inputPiece.charAt(0))) {
          // val = newRegister.get(inputPiece.charAt(0));
          // } // if

          if (operator == null) {
            result = val;
          } else if (operator.equals("+")) {
            result = result.add(val);
          } else if (operator.equals("*")) {
            result = result.multiply(val);
          } else if (operator.equals("-")) {
            result = result.subtract(val);
          } else if (operator.equals("/")) {
            result = result.divide(val);
          } // if

        } else {
          BigFraction val = new BigFraction(inputPiece);
          if (operator == null) {
            result = val;
          } else if (operator.equals("+")) {
            result = result.add(val);
          } else if (operator.equals("*")) {
            result = result.multiply(val);
          } else if (operator.equals("-")) {
            result = result.subtract(val);
          } else if (operator.equals("/")) {
            result = result.divide(val);
          } // if
        } // if
      } // for

      newCalculator.add(result);
      System.out.printf("%s", result);

    } // for

  } // main()
} // QuickCalculator
