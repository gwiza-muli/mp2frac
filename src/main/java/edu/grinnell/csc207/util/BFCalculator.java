package edu.grinnell.csc207.util;

import java.math.BigInteger;



/**
 * A simple implementation of a big fraction calculator.
 * @author Samuel A. Rebelsky
 * @author YSheilla Muligande
 */
public class BFCalculator {

  /////////// FIELDS/////////////
/**
 * the last computed value.
 */
  BigFraction lastVal;

  /////////// CONSTRUCTOR//////////

  /**
   * initializes this to 0.
   */
  public BFCalculator() {
    this.lastVal = new BigFraction(BigInteger.ZERO, BigInteger.ONE);
  } // BFCalculator

  //////////// METHODS//////////

  /**
   *  gets the last computed value (returns 0 if there is no such value).
   * @return the last computed value.
   */
  public BigFraction get() {
    return this.lastVal;
  } // get

  /**
   * adds val to the last computed value.
   * @param val the addend.
   */
  public void add(BigFraction val) {
    this.lastVal = this.lastVal.add(val);
  } // add

  /**
   * subtracts val from the last computed value.
   * @param val the subtrahend.
   */
  public void subtract(BigFraction val) {
    this.lastVal = this.lastVal.subtract(val);
  } // subtract

  /**
   * multiplies the last computed value by val.
   * @param val
   */
  public void multiply(BigFraction val) {
    this.lastVal = this.lastVal.multiply(val);
  } // divide

  /**
   * divides the last computed value by val.
   * @param val the dividend.
   */
  public void divide(BigFraction val) {
    this.lastVal = this.lastVal.divide(val);
  } // divide

  /**
   * resets the last computed value to 0.
   */
  public void clear() {
    this.lastVal = new BigFraction(BigInteger.ZERO, BigInteger.ONE);
  } // clear

} //BFCalculator
