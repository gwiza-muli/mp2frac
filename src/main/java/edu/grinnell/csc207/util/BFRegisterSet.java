package edu.grinnell.csc207.util;


/**
 * a Big fraction register set.
 * @author Sam Rebelsky
 * @author Sheilla Muligande
 */
public class BFRegisterSet {

  // +--------+-------------------------------------------------------

  // | Fields |

  // +--------+
  /**
   * An array that will hold all of the registers from a to z.
   */
  BigFraction[] registerStorage = new BigFraction[26];


  // +---------+------------------------------------------------------

  // | Methods |

  // +---------+

  /**
   * calculates the index equivalent of a register.
   * @param register the letter register.
   * @return the integer equivalent of the register.
   */
  public int indexCalculator(char register) {
    int index = register - 'a';
    if (index < 0 || index >= 26) {
      System.err.printf("Error: Invalid register.");
      return -1;
    } //if
    return index;
  } //indexcalculator()

  /**
   * stores the given value in the specified register.
   * @param register the letter register.
   * @param val the value to store.
   */
  public void store(char register, BigFraction val) {
    int index = indexCalculator(register);

    if (index < 0 || index >= 26) {
      System.err.printf("Error: invalid register");
    } else {
      registerStorage[index] = val;
    } // else
  } // store

  /**
   * returns the register's corresponding value.
   * @param register the letter register.
   * @return the fraction stored.
   */
  public BigFraction get(char register) {
    int index = indexCalculator(register);
    if (index < 0 || index >= 26) {
      System.err.printf("Error: invalid register");
      return null;
    } else {
      return registerStorage[index];
    } // else
  } // get
} // BFRegisterSet
