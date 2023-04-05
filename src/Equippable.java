public class Equippable extends Item{
    private boolean equipped;

    public Equippable(String n) {
        super(n);
        equipped = false;
    }

    public boolean isEquipped()
    {
        return equipped;
    }

    public boolean equip()
    {
        if (!equipped) {
            equipped = true;
            return true;
        }
        else
        {
            return false;
        }
    }
}
