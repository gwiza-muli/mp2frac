package edu.grinnell.csc207.util;

import java.math.BigInteger;
import edu.grinnell.csc207.util.BigFraction;



public class BFCalculator {
  
///////////FIELDS/////////////

BigFraction lastVal;

///////////CONSTRUCTOR//////////

public BFCalculator(){
  this.lastVal = new BigFraction(BigInteger.ZERO, BigInteger.ONE); 
} //BFCalculator

////////////METHODS//////////

public BigFraction get() {
  return this.lastVal;
} //get

public void add(BigFraction val){
  this.lastVal= this.lastVal.add(val);
} //add


public void subtract(BigFraction val)  {
  this.lastVal = this.lastVal.subtract(val);
} //subtract


public void multiply(BigFraction val)  {
  this.lastVal = this.lastVal.multiply(val);
} //divide


public void divide(BigFraction val) {
  this.lastVal = this.lastVal.divide(val);
} //divide


public void clear() {
  this.lastVal = new BigFraction(BigInteger.ZERO, BigInteger.ONE); 
} //clear

}
