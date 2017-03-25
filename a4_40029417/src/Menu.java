// -------------------------------------------------------
// Assignment 4
// Written by: Sagar Patel - 40029417
// For COMP 248 Section P PB – Fall 2016
// --------------------------------------------------------
//Explanation
// --------------------------------------------------------
/* This class is used to build a basic menu with several methods for added functionality. Attributes of this menu include : messages/prompts at both
the bottom and top of the menu, an organized list of options, and a way to select options/read user input. More specifically it contains a no-argument/argument
constructor, basic set and get methods for the messages that can be added to the menu, methods that check whether the menu is empty, its length, and
to read+validate user input*/
// --------------------------------------------------------
import java.util.Scanner;

public class Menu {

    //private instance variables
    private String topMessage;
    private String topPrompt;
    private String[] optionList;
    private String bottomPrompt;
    private String bottomMessage;
    private Scanner input;
    private int optionNumber;

    //constructor that requires String array as parameter and sets it to instance variable using for loop, defaults top/bottom prompts and sets rest to null
    Menu(String[] options) {
        optionList = new String[options.length];
        for (int i = 0; i < options.length; i++) {
            optionList[i] = options[i];
        }

        topPrompt = "Choose an option:";
        bottomPrompt = "Enter an option number: ";
        topMessage = null;
        bottomMessage = null;
    }

    //no parameter constructor, defaults all instance variables to null
    Menu() {
        topMessage = null;
        topPrompt = null;
        bottomPrompt = null;
        bottomMessage = null;
        optionList = null;
    }

    //method that checks if list of options in menu is null, i.e. empty
    public boolean isEmpty() {
        if (optionList == null) {
            return true;
        }

        return false;
    }

    //method that returns length of array of options, if empty just returns 0
    public int length() {
        if (optionList == null) {
            return 0;
        }

        return optionList.length;
    }

    //to String method to return the entire menu, firstly checks to make sure that variable is not null, then appends each valid instance variable
    //to output string (including every option in the array of options)
    public String toString() {
        String output = "";

        if (isNull(topMessage)) {
            output += topMessage + "\n";
        }
        if (isNull(topPrompt)) {
            output += topPrompt + "\n";
        }

        if (optionList != null) {
            for (int i = 0; i < optionList.length; i++) {
                output += "\t(" + (i + 1) + ")" + " " + optionList[i] + "\n";
            }
        }

        if (isNull(bottomMessage)) {
            output += bottomMessage + "\n";
        }
        output += "?-> ";
        if (isNull(bottomPrompt)) {
            output += bottomPrompt;
        }

        return output;
    }

    //Set and get methods for private instance variables
    public void setTopMessage(String tmsg) {
        topMessage = tmsg;
    }

    public void setTopPrompt(String tprompt) {
        topPrompt = tprompt;
    }

    public void setBottomMessage(String bmsg) {
        bottomMessage = bmsg;
    }

    public void setBottomPrompt(String bprompt) {
        bottomPrompt = bprompt;
    }

    public String getTopMessage() {
        return topMessage;
    }

    public String getTopPrompt() {
        return topPrompt;
    }

    public String getBottomMessage() {
        return bottomMessage;
    }

    public String getBottomPrompt() {
        return bottomPrompt;
    }

    public int getOptionNumber() {
        return this.readOptionNumber();
    }

    //Method used to print the menu, read user input, and return it - firstly if the list of options is empty, returns what user inputs.
    //Otherwise method validates user input and continues to ask for input if it is invalid (<= 0 or greater than available options), also 
    //prints a message showing the user what the valid range of inputs is
    public int readOptionNumber() {

        input = new Scanner(System.in);

        if (optionList == null) {
            System.out.print(this.toString());
            optionNumber = input.nextInt();

        } else {

            System.out.print(this.toString());
            optionNumber = input.nextInt();

            while (optionNumber < 1 || optionNumber > length()) {
                System.out.println("Invalid choice " + optionNumber + ". It must be in the range [1, " + length() + "]\n");
                System.out.print(this.toString());
                optionNumber = input.nextInt();
            }

        }
        return optionNumber;
    }

    //private method to check if the instance variable is null (to simplify code in toString method)
    private boolean isNull(String s) {
        if (s == null) {
            return false;
        }

        return true;
    }

}
