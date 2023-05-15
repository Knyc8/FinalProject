package Dungeon;

import Characters.Player;

public class Dungeon {
    private Player player;
    private int variant;
    private String difficulty;

    public Dungeon()
    {
    }
    public Player getPlayer() {
        return player;
    }

    public int getVariant() {
        return variant;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String d) {
        difficulty = d;
    }

}
