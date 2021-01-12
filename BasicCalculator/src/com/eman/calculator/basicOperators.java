/**
 * @author : Emmanuel D. Molines Jr.
 * @date : January 12, 2021
 * @version : 1.0
 */

package com.eman.calculator;

/*
 This basicOperators class is being used to
 solve the basic arithmetic operations.
*/
public class basicOperators {

    //Method used to solve Addition
    public static void addition(double firstNumber, double secondNumber){
        System.out.println(firstNumber + " + "+secondNumber + " = "+(firstNumber+secondNumber));
    }

    //Method used to solve Subtraction
    public static void subtraction(double firstNumber, double secondNumber){
        System.out.println(firstNumber + " - "+secondNumber + " = "+(firstNumber-secondNumber));
    }

    //Method used to solve Multiplication
    public static void multiplication(double firstNumber, double secondNumber){
        System.out.println(firstNumber + " * "+secondNumber + " = "+(firstNumber*secondNumber));
    }

    //Method used to solve Division
    public static void division(double firstNumber, double secondNumber){
        System.out.println(firstNumber + " ÷ "+secondNumber + " = "+(firstNumber/secondNumber));
    }

    //Method used to solve Modulo
    public static void modulo(double firstNumber, double secondNumber){
        System.out.println(firstNumber + " % "+secondNumber + " = "+(firstNumber%secondNumber));
    }
}
