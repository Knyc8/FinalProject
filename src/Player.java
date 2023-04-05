import java.util.ArrayList;

public class Player {
    private String name;
    private int maxHealth;
    private int health;
    private int defense;
    private int damage;
    private int speed;
    private ArrayList<Item> inventory;

    public Player()
    {
        name = "Player";
        maxHealth = 3;
        health = maxHealth;
        defense = 0;
        damage = 1;
        speed = 1;
        inventory = new ArrayList<Item>();
    }

    public String getName() {return name;}

    public int getMaxHealth() {return maxHealth;}

    public int getHealth() {return health;}

    public int getDefense() {return defense;}

    public int getDamage() {return damage;}

    public int getSpeed() {return speed;}

    public ArrayList<Item> getInventory() {return inventory;}

    public void setName(String n) {name=n;}
    public void increaseMaxHP(int val) {maxHealth+=val;}

    public void heal (int val) {
        health += val;
        if (health > maxHealth)
        {
            health = maxHealth;
        }
    }

    public void takeDamage(int val) {health-=val;}

    public void increaseDamage(int val) {damage+=val;}

    public void increaseSpeed (int val) {speed+=val;}

    public void addToInventory (Item o) {inventory.add(o);}

    public void removeFromInventory (Item o) {inventory.remove(o);}

    //For testing and saving purposes
    public String toString() {
        return ("Name: " + name +
                "\nHealth: " + maxHealth +
                "\nDefense: " + defense +
                "\nDamage: " + damage +
                "\nSpeed: " + speed +
                "\nInventory: " + inventory.toString());
    }

    public boolean equipItem(int idx)
    {
        if (inventory.get(idx) instanceof Equippable) {
            ((Equippable) inventory.get(idx)).equip();
        }
        return false;
    }
    public boolean usePotion(int idx)
    {
        if (inventory.get(idx) instanceof Potion) {
            heal(((Potion) inventory.get(idx)).getHealingPoints());
            inventory.remove(idx);
            return true;
        }
        return false;
    }
}
