package zotapp;

public class Item {
    private String name;
    private String developer;
    private double price;
    private String icon;
    private int quantity;
    
    public Item(String itemName, String itemDeveloper, double itemPrice, String itemIcon) {
        name = itemName;
        developer = itemDeveloper;
        price = itemPrice;
        icon = itemIcon;
        quantity = 1;
    }
    
    public String getName() {return name;}
    public String getDeveloper() {return developer;}
    public double getPrice() {return price;}
    public String getIcon() {return icon;}
    public int getQuantity() {return quantity;}
    
    //Checks if this object is equal to the item object passed in
    public boolean equals(Item item) {
        return (item.name).equals(this.name);
    }
    public void addQuantity() {quantity++;}
    public double getTotalPrice() {return price * quantity;}

}