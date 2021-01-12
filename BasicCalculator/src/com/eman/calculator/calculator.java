/**
 * @author : Emmanuel D. Molines Jr.
 * @date : January 12, 2021
 * @version : 1.0
 */

package com.eman.calculator;
import java.util.Scanner;
import static com.eman.calculator.basicOperators.*;


/*
This calculator class will be used to to make a menu which will be
used to determine how each created method in basicOperators class will be use.
 */
public class calculator {
    static Scanner sc = new Scanner(System.in);
    static double fNumber;
    static double sNumber;

    /*
        mainMenu method which is being used to determined
        how the app will do its task.
     */
    public static void mainMenu() {
        boolean loop = true;
            do {
                System.out.println("================Basic Calculator================");

                System.out.println("[1] Addition \n" +
                        "[2] Subtraction \n" +
                        "[3] Multiplication \n" +
                        "[4] Division \n" +
                        "[5] Modulo \n" +
                        "[6] Exit");
                String choice = sc.nextLine();

                if (choice.equals("1")) {
                    userInput();
                    addition(fNumber, sNumber);
                        System.out.println("Press Enter to continue");
                        sc.nextLine();
                        sc.nextLine();
                } else if (choice.equals("2")) {
                    userInput();
                    subtraction(fNumber, sNumber);
                        System.out.println("Press Enter to continue");
                        sc.nextLine();
                        sc.nextLine();
                } else if (choice.equals("3")) {
                    userInput();
                    multiplication(fNumber, sNumber);
                        System.out.println("Press Enter to continue");
                        sc.nextLine();
                        sc.nextLine();
                } else if (choice.equals("4")) {
                    userInput();
                    division(fNumber, sNumber);
                        System.out.println("Press Enter to continue");
                        sc.nextLine();
                        sc.nextLine();
                } else if (choice.equals("5")) {
                    userInput();
                    modulo(fNumber, sNumber);
                        System.out.println("Press Enter to continue");
                        sc.nextLine();
                        sc.nextLine();
                }else if (choice.equals("6")) {
                    System.out.println("Thank you for using this program.");
                    System.exit(0);
                } else {
                    System.out.println("--->Choice should only from 1-5. Please try again.");
                }
            } while (loop);

    }

    /*
    userInput method which is being used to input
    the first and second value for the computation of
    basic arithmetic operations.
    */
    public static void userInput(){
        try {
            System.out.print("Input First Number: ");
            fNumber = sc.nextDouble();
            System.out.print("Input Second Number: ");
            sNumber = sc.nextDouble();
        }catch (Exception e){
            System.out.println("--->Something went wrong. Please try again.");
            fNumber = 0;
            sNumber = 0;
        }
    }


}
