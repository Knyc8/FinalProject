public class Potion extends Item{
    public Potion(String n)
    {
        super(n);
    }

    public int getHealingPoints()
    {
        if (super.getName().equals("Lesser Healing Potion"))
        {
            return 1;
        }
        if (super.getName().equals("Normal Healing Potion"))
        {
            return 3;
        }
        if (super.getName().equals("Greater Healing Potion"))
        {
            return 5;
        }
        else return 0;
    }
}
