// -------------------------------------------------------
// Assignment 4
// Written by: Sagar Patel - 40029417
// For COMP 248 Section P PB – Fall 2016
// --------------------------------------------------------
//Explanation
// --------------------------------------------------------
/* This class uses the IceCreamOrder class which uses the Menu class. Essentially this class holds a number of IceCreamOrders, for this program
the maximum capacity of the cart is 5(IceCreamOrders). It contains a default constructor that initializes an array of IceCreamOrders (length 5), an 
add method for adding orders to the cart, a remove method for removing orders, a toString method to print all orders contained in the cart, methods
to check whether the cart is full or empty, and methods to return the size of the cart, and IceCreamOrders at a specific index in the cart*/
// --------------------------------------------------------
public class ShoppingCart {

    private IceCreamOrder[] cart;
    private int counter = 0;

    //default constructor to set array of IceCreamOrders to max capacity (5)
    ShoppingCart() {
        cart = new IceCreamOrder[5];
    }

    //If cart is not full, adds IceCreamOrder into array of IceCreamOrders(cart), increments counter that keeps track of number of items in cart
    public void add(IceCreamOrder order) {
        if (counter > 5) {
            System.out.println("Exceeded maximum cart capacity");
        } else {
            cart[counter] = order;
            counter++;
        }

    }

    //Removes order (by setting it equal to null) at specified index
    public void remove(int position) {
        //position-1 since cart is an array 
        //decrements counter since there's one less item in the cart
        cart[position - 1] = null;
        counter--;

        //for loop that pushes every item in the cart up by 1 from deleted item
        for (int i = position - 1; i < size(); i++) {
            cart[i] = cart[i + 1];
        }
        //one everything is pushed up sets the last index to null to finalize rotation
        cart[size()] = null;
    }

    //Uses IceCreamOrders to String method to print the contents of every order in the cart
    public String toString() {
        String output = "";
        for (int i = 0; i < size(); i++) {
            output += cart[i].toString() + "\n";
        }

        return output;
    }

    //Checks if cart is empty (utilizing counter)
    public boolean isEmpty() {
        if (counter == 0) {
            return true;
        } else {
            return false;
        }

    }

    //Checks if cart is full (utilizing counter)
    public boolean isFull() {
        if (counter == 5) {
            return true;
        } else {
            return false;
        }

    }

    //Returns IceCreamOrder at index in cart, if it exceeds cart size then prints error message and returns null instead
    public IceCreamOrder get(int position) {
        if (position > 5) {
            System.out.println("Position is out of range");
            return null;
        } else {
            return cart[position];
        }
    }

    //returns size of cart(utilizing counter)
    public int size() {
        return counter;
    }

}
