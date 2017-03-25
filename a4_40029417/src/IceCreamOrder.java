// -------------------------------------------------------
// Assignment 4
// Written by: Sagar Patel - 40029417
// For COMP 248 Section P PB – Fall 2016
// --------------------------------------------------------
//Explanation
// --------------------------------------------------------
/* This class is used to create a Menu pertaining to ice cream, therefore it utilizes the Menu class with its own situation specific variables.
It contains a no paramater constructor, and two constructors that require parameters. The no parameter constructor initializes several Menus and
sets all the information into the menus that are relevant to this class, in addition it also gathers user input for the instance variables.             
There are also set and get methods for all its private instance variables, a method to calculate price, and a toString method that prints all 
the relevant information in this class.*/
// --------------------------------------------------------
import java.text.DecimalFormat;

public class IceCreamOrder {

    //private instance variables
    private String flavor;
    private String vessel;
    private String amount;
    private double unitPrice;
    private int quantity;
    private int flavorChoice;
    private int vesselChoice;
    private int amountChoice;
    //Menu for different user prompts and options
    private Menu firstMenu;
    private Menu secondMenu;
    private Menu thirdMenu;
    private Menu fourthMenu;
    //String arrays that hold all the options for each list
    private String[] flavors = {"Avocado", "Banana", "Chocolate", "Coffee", "Hazelnut", "Lemon", "Mango", "Mocha", "Vanilla"};
    private String[] vessels = {"Cone", "Cup", "Sundae"};
    private String[] amountNames = {"Single Scoop", "Double Scoop", "Triple Scoop"};
    private String[] quantities = {"One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten"};
    //Table of prices
    double[][] prices = {{3.49, 4.49, 5.49},
    {2.99, 3.99, 4.99},
    {4.25, 5.25, 6.25}};
    private DecimalFormat df;

    //Constructor that takes parameters for all instance variables
    IceCreamOrder(String flav, String ves, String amnt, double price, int qty) {
        flavor = flav;
        vessel = ves;
        amount = amnt;
        unitPrice = price;
        quantity = qty;
    }

    //Constructor that takes paramters for all but one instance variable which gets defaulted
    IceCreamOrder(String flav, String ves, String amnt, double price) {
        flavor = flav;
        vessel = ves;
        amount = amnt;
        unitPrice = price;
        quantity = 1;
    }

    //no parameter constructor
    IceCreamOrder() {

        //initializes the firstMenu to show the flavors, including a top message+prompt
        //uses readOptionNumber to print the menu, and store user choice, then corresponds choice to flavor in array of options
        firstMenu = new Menu(flavors);
        firstMenu.setTopMessage("\nPlacing an order is as easy as ABC, and D.");
        firstMenu.setTopPrompt("Step A: Select your favorite flavour");
        flavorChoice = firstMenu.readOptionNumber();
        flavor = flavors[flavorChoice - 1];
        
        //Second and third menu similar to first apart from top prompt
        secondMenu = new Menu(vessels);
        secondMenu.setTopMessage("\nStep B: Select a vessel for your ice cream :");
        vesselChoice = secondMenu.readOptionNumber();
        vessel = vessels[vesselChoice - 1];

        thirdMenu = new Menu(amountNames);
        thirdMenu.setTopMessage("\nStep C: How much ice cream ?");
        amountChoice = thirdMenu.readOptionNumber();
        amount = amountNames[amountChoice - 1];

        fourthMenu = new Menu(quantities);
        fourthMenu.setTopMessage("\nStep D: how many orders of your current selection ?");
        quantity = fourthMenu.readOptionNumber();
        //Since prices are dependent on vessel and amount, uses user-selected options and matches it to price in 2d array table of prices
        unitPrice = prices[vesselChoice - 1][amountChoice - 1];

    }

    //set and get methods for instance variables
    public void setFlavor(String f) {
        flavor = f;
    }

    public void setVessel(String v) {
        vessel = v;
    }

    public void setAmount(String a) {
        amount = a;
    }

    public void setQuantity(int q) {
        quantity = q;
    }

    public void setUnitPrice(int p) {
        unitPrice = p;
    }

    public String getFlavor() {
        return flavor;
    }

    public String getVessel() {
        return vessel;
    }

    public String getAmount() {
        return amount;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    //calculates and returns price (using quantity and unitPrice)
    public double price() {
        return quantity * unitPrice;
    }

    //toString method that prints all the relevent information and makes use of DecimalFormat to keep the dollar amounts to 2 decimal places
    public String toString() {
        df = new DecimalFormat("#.00");
        return quantity + " order(s) of " + amount + " of " + flavor + " ice cream in a " + vessel + " for $" + df.format(price()) + " = " + quantity + " x " + unitPrice;
    }

}
