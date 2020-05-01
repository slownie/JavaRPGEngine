package data;

public class Item
{
    public String name;
    // CHANGE THIS TO ENUM LATER
    public int type;
    public int quantity; // How many of the item you have

    public Item (String name, int type, int quantity)
    {
        this.name = name;
        this.type = type;
        this.quantity = quantity;
    }
}
