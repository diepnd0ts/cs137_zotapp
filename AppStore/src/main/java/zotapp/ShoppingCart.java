package zotapp;

import java.util.ArrayList;

public class ShoppingCart {
    private ArrayList<Item> cart;
    private int totalQuantity;
    private double totalPrice;
    
    public ShoppingCart() {
        cart = new ArrayList<Item>();
        totalQuantity = 0;
        totalPrice = 0.0;
    }
    
    /*
        GETTER FUNCTIONS
    */
    public int getTotalQuantity() { return totalQuantity; }
    public String getTotalPrice() { return "$" + String.format("%.2f", totalPrice); }
    public int getCartSize() { return cart.size(); }
    
    //GETTER FUNCTIONS WITH INDEXING
    public Item getItem(int index) { return cart.get(index); }
    public String getItemName(int index) { return cart.get(index).getName(); }
    public String getItemPrice(int index) { return "$" + String.format("%.2f", cart.get(index).getPrice()); }
    public String getItemDeveloper(int index) { return cart.get(index).getDeveloper(); }
    public String getItemIcon(int index) { return cart.get(index).getIcon(); }
    public int getItemQuantity(int index) { return cart.get(index).getQuantity(); }
    
    //Checks if the new item already exists in the cart. If so, just add the quantity of the item
    //Else add the new item in the cart. Update the cart's item total quantity and price
    public void addToCart(Item newItem) {
        totalQuantity++;
        totalPrice += newItem.getPrice();
        //Loop through items currently in the cart
        for (int cartItem = 0; cartItem < cart.size(); cartItem++) {
            if ((cart.get(cartItem)).equals(newItem)) {
                (cart.get(cartItem)).addQuantity();
                return;
            }
        }
       cart.add(newItem);
    }
    
}