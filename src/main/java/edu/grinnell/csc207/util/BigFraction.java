package edu.grinnell.csc207.util;

import java.math.BigInteger;


/**
 * A simple implementation of arbitrary-precision Fractions.
 * @author Samuel A. Rebelsky
 * @author YSheilla Muligande
 */

public class BigFraction {

  // +------------------+---------------------------------------------

  // | Design Decisions |

  // +------------------+
  /*
   * (1) Denominators are always positive. Therefore, negative fractions are represented with a
   * negative numerator. Similarly, if a fraction has a negative numerator, it is negative. (2).
   * Fractions are not necessarily stored in simplified form. To obtain a fraction in simplified
   * form, one must call the `simplify` method.
   */


//constants


  /** The default numerator when creating fractions. */

  private static final BigInteger DEFAULT_NUMERATOR = BigInteger.valueOf(2);


  /** The default denominator when creating fractions. */

  private static final BigInteger DEFAULT_DENOMINATOR = BigInteger.valueOf(7);

//fields


  /** The numerator of the fraction. Can be positive, zero or negative. */

  BigInteger num;


  /** The denominator of the fraction. Must be non-negative. */

  BigInteger denom;


//constructors


  /**
   * Build a new fraction with numerator num and denominator denom.
   * @param numerator The numerator of the fraction.
   * @param denominator The denominator of the fraction.
   */

  public BigFraction(BigInteger numerator, BigInteger denominator) {

    this.num = numerator;

    this.denom = denominator.abs();

    if (denominator.compareTo(BigInteger.ZERO) < 0) {
      this.num = this.num.multiply(BigInteger.valueOf(-1));
    } // if

    this.simplify();

  } // BigFraction(BigInteger, BigInteger)


  /**
   * Build a new fraction with numerator num and denominator denom. Warning! Not yet stable.
   * @param numerator numerator of the fraction.
   * @param denominator The denominator of the fraction.
   */

  public BigFraction(int numerator, int denominator) {

    this.num = BigInteger.valueOf(numerator);

    this.denom = BigInteger.valueOf(denominator).abs();

    this.simplify();

  } // BigFraction(int, int)


  /**
   * Build a new fraction by parsing a string.
   * @param str fraction in string form
   */

  public BigFraction(String str) {

    String[] fractionParts = str.split("/");

    if (fractionParts.length == 1) {
      BigInteger strNum = new BigInteger(fractionParts[0]);
      this.num = strNum;
      this.denom = BigInteger.ONE;
    } else {

      BigInteger strNum = new BigInteger(fractionParts[0]);
      BigInteger strDenom = new BigInteger(fractionParts[1]);

      this.denom = strDenom.abs();
      this.num = strNum;

      if (strDenom.compareTo(BigInteger.ZERO) < 0) {
        this.num = this.num.multiply(BigInteger.valueOf(-1));
      } // if


    } // if
    this.simplify();
  } // BigFraction


  // +---------+------------------------------------------------------

  // | Methods |

  // +---------+


  /**
   * Express this fraction as a double.
   * @return the fraction approxiamted as a double.
   */

  public double doubleValue() {

    return this.num.doubleValue() / this.denom.doubleValue();

  } // doubleValue()


  /**
   * Add another faction to this fraction.
   * @param addend The fraction to add.
   * @return the result of the addition.
   */
  public BigFraction add(BigFraction addend) {

    BigInteger resultNumerator;

    BigInteger resultDenominator;


    // The denominator of the result is the product of this object's

    // denominator and addend's denominator

    resultDenominator = this.denom.multiply(addend.denom);

    // The numerator is more complicated

    resultNumerator =

        (this.num.multiply(addend.denom)).add(addend.num.multiply(this.denom));


    // Return the computed value

    BigFraction sum = new BigFraction(resultNumerator, resultDenominator);
    sum.simplify();

    return sum;

  } // add(BigFraction)

  /**
   * Add another fraction to this fraction.
   * @param addend The fraction to multiply.
   * @return the result of the addition.
   */
  public BigFraction multiply(BigFraction addend) {


    BigInteger resultNumerator = this.num.multiply(addend.num);

    BigInteger resultDenominator = this.denom.multiply(addend.denom);


    // The denominator of the result is the product of this object's

    // denominator and addend's denominator

    BigFraction product = new BigFraction(resultNumerator, resultDenominator);
    product.simplify();
    return product;

  } //multiply()



  /**
   * Get the denominator of this fraction.
   * @return the denominator
   */

  public BigInteger denominator() {

    return this.denom;

  } // denominator()


  /**
   * Get the numerator of this fraction.
   * @return the numerator
   */

  public BigInteger numerator() {

    return this.num;

  } // numerator()


  /**
   * simplifies a fraction.
   */
  public void simplify() {
    BigInteger bigGCD = num.abs().gcd(denom.abs());

    this.num = this.num.divide(bigGCD);
    this.denom = this.denom.divide(bigGCD);

    if (this.denom.compareTo(BigInteger.ZERO) < 0) {
      this.num = this.num.multiply(BigInteger.valueOf(-1));
      this.denom = this.denom.abs();
    } // if

  } // simplify()

  /**
   * subtract a fraction from this fraction.
   * @param frac the fraction to be subtracted.
   * @return the difference.
   */
  public BigFraction subtract(BigFraction frac) {
    BigInteger num1 = this.num.multiply(frac.denom);
    BigInteger num2 = frac.num.multiply(this.denom);
    BigInteger resultNum = num1.subtract(num2);

    BigInteger resultDenom = this.denom.multiply(frac.denom);

    BigFraction difference = new BigFraction(resultNum, resultDenom);
    difference.simplify();
    return difference;
  } // subtract()


  /**
   * Convert this fraction to a string for ease of printing.
   * @return a string that represents the fraction.
   * @param frac the dividend
   */
  public BigFraction divide(BigFraction frac) {
    BigInteger resultNumerator = this.num.multiply(frac.denom);

    BigInteger resultDenominator = this.denom.multiply(frac.num);

    BigFraction quotient = new BigFraction(resultNumerator, resultDenominator);
    quotient.simplify();

    return quotient;

  } // divide()

  /**
   * Convert this fraction to a string for ease of printing.
   * @return a string that represents the fraction.
   */
  public String toString() {
    // Special case: It's zero
    if (this.num.equals(BigInteger.ZERO)) {

      return "0";

    } // if it's zero

    if (this.denom.equals(BigInteger.ONE)) {
      return this.num.toString();
    } // if
    // Lump together the string represention of the numerator,
    // a slash, and the string representation of the denominator
    // return this.num.toString().concat("/").concat(this.denom.toString());
    return this.num + "/" + this.denom;
  } // toString()

} // class BigFraction


