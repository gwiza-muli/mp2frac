package edu.grinnell.csc207.util;

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
    
    public int indexCalculator(char register){
     int index = register - 'a';
     if (index < 0 || index >= 26){
       System.err.printf("Error: Invalid register.");
       return -1;
     }
     return index;
    }
    
    public void store(char register, BigFraction val){
     int index = indexCalculator(register);
    
     if (index > 0){
       System.err.printf("Error: invalid register");
     } //if
    
     else {
       registerStorage[index] = val;
     } //else
    } //store
    
    public BigFraction get(char register){
     int index = indexCalculator(register);
     if (index > 0){
       System.err.printf("Error: invalid register");
       return null;
     } //if
     else {
       return registerStorage[index];
     } //else
    } //get
    


}//BFRegisterSet
