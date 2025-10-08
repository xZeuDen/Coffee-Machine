public class menuItem {
    private String name;
    private double price;

    //Constructor
    public MenuItem(String name, double price) {
        this.name = name;
        this.price = price;
    }

    //Returns the name of the menu item
    public String getName() {
        return name;
    }
    //Returns the price of the menu item
    public double getPrice() {
        return price;
    }
}
