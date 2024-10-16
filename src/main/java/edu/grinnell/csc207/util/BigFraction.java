package edu.grinnell.csc207.util;

import java.math.BigInteger;


/**
 * 
 * A simple implementation of arbitrary-precision Fractions.
 *
 * 
 * 
 * @author Samuel A. Rebelsky
 * 
 * @author YOUR NAME HERE
 * 
 */

public class BigFraction {

  // +------------------+---------------------------------------------

  // | Design Decisions |

  // +------------------+


  /*
   * 
   * (1) Denominators are always positive. Therefore, negative fractions
   * 
   * are represented with a negative numerator. Similarly, if a fraction
   * 
   * has a negative numerator, it is negative.
   *
   * 
   * 
   * (2) Fractions are not necessarily stored in simplified form. To
   * 
   * obtain a fraction in simplified form, one must call the `simplify`
   * 
   * method.
   * 
   */


  // +-----------+---------------------------------------------------

  // | Constants |

  // +-----------+


  /** The default numerator when creating fractions. */

  private static final BigInteger DEFAULT_NUMERATOR = BigInteger.valueOf(2);


  /** The default denominator when creating fractions. */

  private static final BigInteger DEFAULT_DENOMINATOR = BigInteger.valueOf(7);


  // +--------+-------------------------------------------------------

  // | Fields |

  // +--------+


  /** The numerator of the fraction. Can be positive, zero or negative. */

  BigInteger num;


  /** The denominator of the fraction. Must be non-negative. */

  BigInteger denom;


  // +--------------+-------------------------------------------------

  // | Constructors |

  // +--------------+


  /**
   * 
   * Build a new fraction with numerator num and denominator denom.
   *
   *
   * 
   * 
   * 
   * @param numerator
   * 
   *        The numerator of the fraction.
   * 
   * @param denominator
   * 
   *        The denominator of the fraction.
   * 
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
   * 
   * Build a new fraction with numerator num and denominator denom.
   *
   * 
   * 
   * Warning! Not yet stable.
   *
   * 
   * 
   * @param numerator
   * 
   *        The numerator of the fraction.
   * 
   * @param denominator
   * 
   *        The denominator of the fraction.
   * 
   */

  public BigFraction(int numerator, int denominator) {

    this.num = BigInteger.valueOf(numerator);

    this.denom = BigInteger.valueOf(denominator).abs();

    this.simplify();

  } // BigFraction(int, int)


  /**
   * 
   * Build a new fraction by parsing a string.
   *
   * 
   * 
   * Warning! Not yet implemented.
   *
   * 
   * 
   * @param str
   * 
   *        The fraction in string form
   * 
   */

  public BigFraction(String str) {

    String[] fractionParts = str.split("/");

    if (fractionParts.length == 1) {
      BigInteger StrNum = new BigInteger(fractionParts[0]);
      this.num = StrNum;
      this.denom = BigInteger.ONE;
    } else {

      BigInteger StrNum = new BigInteger(fractionParts[0]);
      BigInteger StrDenom = new BigInteger(fractionParts[1]);

      this.denom = StrDenom.abs();
      this.num = StrNum;

      if (StrDenom.compareTo(BigInteger.ZERO) < 0) {
        this.num = this.num.multiply(BigInteger.valueOf(-1));
      } // if


    } // if
    this.simplify();
  } // BigFraction


  // +---------+------------------------------------------------------

  // | Methods |

  // +---------+


  /**
   * 
   * Express this fraction as a double.
   *
   * 
   * 
   * @return the fraction approxiamted as a double.
   * 
   */

  public double doubleValue() {

    return this.num.doubleValue() / this.denom.doubleValue();

  } // doubleValue()


  /**
   * 
   * Add another faction to this fraction.
   *
   * 
   * 
   * @param addend
   * 
   *        The fraction to add.
   *
   * 
   * 
   * @return the result of the addition.
   * 
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


  public BigFraction multiply(BigFraction addend) {


    BigInteger resultNumerator = this.num.multiply(addend.num);

    BigInteger resultDenominator = this.denom.multiply(addend.denom);


    // The denominator of the result is the product of this object's

    // denominator and addend's denominator

    BigFraction product = new BigFraction(resultNumerator, resultDenominator);
    product.simplify();
    return product;

  }



  public BigFraction fractional() {

    BigInteger remainder = this.num.remainder(this.denom);

    return new BigFraction(remainder, this.denom);

  }


  /**
   * 
   * Get the denominator of this fraction.
   *
   * 
   * 
   * @return the denominator
   * 
   */

  public BigInteger denominator() {

    return this.denom;

  } // denominator()


  /**
   * 
   * Get the numerator of this fraction.
   *
   * 
   * 
   * @return the numerator
   * 
   */

  public BigInteger numerator() {

    return this.num;

  } // numerator()


  public void simplify() {
    BigInteger BigGCD = num.abs().gcd(denom.abs());

    this.num = this.num.divide(BigGCD);
    this.denom = this.denom.divide(BigGCD);

    if (this.denom.compareTo(BigInteger.ZERO) < 0) {
      this.num = this.num.multiply(BigInteger.valueOf(-1));
      this.denom = this.denom.abs();
    } // if

  }

  public BigFraction subtract(BigFraction frac) {
    BigInteger num1 = this.num.multiply(frac.denom);
    BigInteger num2 = frac.num.multiply(this.denom);
    BigInteger resultNum = num1.subtract(num2);

    BigInteger resultDenom = this.denom.multiply(frac.denom);

    BigFraction difference = new BigFraction(resultNum, resultDenom);
    difference.simplify();
    return difference;
  }


  /**
   * 
   * Convert this fraction to a string for ease of printing.
   *
   * 
   * 
   * @return a string that represents the fraction.
   * 
   */


  public BigFraction divide(BigFraction frac) {
    BigInteger resultNumerator = this.num.multiply(frac.denom);

    BigInteger resultDenominator = this.denom.multiply(frac.num);

    BigFraction quotient = new BigFraction(resultNumerator, resultDenominator);
    quotient.simplify();

    return quotient;

  }

  public String toString() {

    // Special case: It's zero

    if (this.num.equals(BigInteger.ZERO)) {

      return "0";

    } // if it's zero

    if (this.denom.equals(BigInteger.ONE)) {
      return this.num.toString();
    }

    // Lump together the string represention of the numerator,

    // a slash, and the string representation of the denominator

    // return this.num.toString().concat("/").concat(this.denom.toString());

    return this.num + "/" + this.denom;

  } // toString()

} // class BigFraction


