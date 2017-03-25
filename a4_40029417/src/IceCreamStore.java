// -------------------------------------------------------
// Assignment 4
// Written by: Sagar Patel - 40029417
// For COMP 248 Section P PB – Fall 2016
// --------------------------------------------------------
//Explanation
// --------------------------------------------------------
/* This class utilizes all the previous classes (Menu, IceCreamOrder, and ShoppingCart). In the default constructor it initializes a cart and a menu
along with a String array of options and prints this new Main Menu. The class contains a method for placing an order and adding it to the cart,
a method to delete an order, in which a new delete menu is created and the user inputted option is removed from the cart, a method to 
calculate the total price of the cart, and a method to print all the articles in the cart. There is also a method for printing the main menu
which varies depending on the state of the cart (full empty neither) and validates input, and finally calls the corresponding method
depending on the desired action by the user, this program can be run for as long as the user wishes*/
// --------------------------------------------------------
import java.text.DecimalFormat;

public class IceCreamStore {

    //instance variables, cart to contain orders and menus to hold options and read user input 
    private ShoppingCart cart;
    private Menu mainMenu;
    private Menu deleteMenu;
    private boolean running;
    private DecimalFormat df;

    //Default constructor that contains array of options, and initializes cart and mainMenu, also sets running to true so that the program will
    //run until the user wishes to terminate it (thus setting running to false), and lastly prints the mainMenu which is explained below
    IceCreamStore() {
        String[] options = {"Place an order",
            "Delete an order",
            "Price the cart",
            "List the cart",
            "Proceed to checkout",
            "Exit this menu"};
        cart = new ShoppingCart();
        mainMenu = new Menu(options);
        running = true;

        printMenu();

    }

    //Creates new IceCreamOrder (thus having to go through everything inside default constructor of IceCreamOrder and adds it to the cart
    public void placeOrder() {
        IceCreamOrder order = new IceCreamOrder();
        cart.add(order);
    }

    //Prints a delete menu and removes corresponding item from the cart
    public void deleteOrder() {
        int option;
        //Initialize a deleteOptions array of strings and fill it with all the items in the cart so the user can select which item they want to delete 
        //this is done using for loop, and adittionally adds an exit menu option at the last index of the delete options array (once all the items are added)
        String[] deleteOptions = new String[cart.size() + 1];

        for (int i = 0; i < deleteOptions.length; i++) {
            if (i == deleteOptions.length - 1) {
                deleteOptions[i] = "Exit this menu";
            } else {
                deleteOptions[i] = cart.get(i).toString();
            }
        }
        //Sets array of options to deleteMenu and adds corresponding messages to menu, then using methods in Menu class, prints the menu and gets input 
        //from the user
        deleteMenu = new Menu(deleteOptions);
        deleteMenu.setTopMessage("\nYou have selected to remove an order from your cart"
                + "\nWhat would you like to do?");
        option = deleteMenu.readOptionNumber();
        
        //If user inputs an option that's an actual item in the cart (not exit menu or out of bounds) then removes item at index using remove method in 
        //ShoppingCart and prints corresponding message
        if (option != deleteOptions.length) {
            cart.remove(option);
            System.out.println("\nThe order you selected was deleted");

        }
    }

    //Utilizes the price() method in cart to create a running total of cost for each item in the cart, and returns total
    public double computeTotalPrice() {
        double price = 0;

        for (int i = 0; i < cart.size(); i++) {
            price += cart.get(i).price();
        }

        return price;
    }

    //Prints the total price from above method using df.format to go to nearest 2 decimals
    public void printTotalPrice() {
        df = new DecimalFormat("#.00");
        System.out.println("\n- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -=\n"
                + "Total price of all your orders in the cart : $" + df.format(computeTotalPrice()) + "\n"
                + "- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -=");

    }

    //Utilizing carts toString method, prints all the orders in the cart
    public void reviewOrders() {
        System.out.println("\nYour current selections of our scrumptious ice creams\n"
                + "-----------------------------------------------------"
                + "\n" + cart.toString() + "-----------------------------------------------------");

    }

    //Simply calls above two methods at once
    public void checkout() {
        reviewOrders();
        printTotalPrice();
    }

    //Prints the main menu used in constructor above
    private void printMenu() {
        int optionNum = 0;

        //If the cart is empty sets corresponding messages
        if (cart.isEmpty()) {
            mainMenu.setTopMessage("Your shopping Cart is empty.");
            mainMenu.setBottomMessage("You have only two options: 1 or 6");
            mainMenu.setBottomPrompt("Enter an option: ");
            //Uses do while to keep prompting user for input until it is valid (makes use of method in Menu class)
            do {
                optionNum = mainMenu.getOptionNumber();
            } while (optionNum != 1 && optionNum != 6);

        //Similar as above for when cart is full and valid options are different
        } else if (cart.isFull()) {
            mainMenu.setTopMessage("\nYour shopping Cart is full with " + cart.size() + " ice cream orders.");
            mainMenu.setTopPrompt("Cannot place orders! what would you like to do?");
            mainMenu.setBottomMessage("Please select option 2, 3, 4, 5, or 6");
            mainMenu.setBottomPrompt("Enter an option: ");

            do {
                optionNum = mainMenu.getOptionNumber();
            } while (optionNum != 2 && optionNum != 3 && optionNum != 4 && optionNum != 5 && optionNum != 6);

        //Since every option is valid no do-while loop here, additionally prints cart size in top menu message so user knows how many articles are
        //in their cart. Uses readOptionNumber to store user input
        } else {
            mainMenu.setTopMessage("\nYour shopping Cart contains " + cart.size() + " ice cream order(s).");
            mainMenu.setTopPrompt("What would you like to do?");
            mainMenu.setBottomPrompt("Enter an option: ");
            optionNum = mainMenu.readOptionNumber();
        }

        //Checks userinput since every option corresponds to a different method
        if (optionNum == 1) {
            placeOrder();
        } else if (optionNum == 2) {
            deleteOrder();
        } else if (optionNum == 3) {
            printTotalPrice();
        } else if (optionNum == 4) {
            reviewOrders();
        } else if (optionNum == 5) {
            checkout();
        //If user wishes to exit, program prints closing message and sets running to false so that the program terminates
        } else if (optionNum == 6) {
            running = false;
            System.out.println("\nCheers!");
        }

    }

    //Runs program while running is true, this is done by printing the main menu so the user can continue to use the program and all its options for
    //as long as they wish
    public void run() {
        while (running) {
            printMenu();
        }

    }
}
