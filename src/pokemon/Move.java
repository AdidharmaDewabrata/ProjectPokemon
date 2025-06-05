package pokemon;

import javax.swing.*;
import java.awt.*;

public class Move {
    private String name, pokemon;
    private Type type;
    private int power;
    private Image image;
    public Move(String Pokemon, String name, String type, int power) {
        this.pokemon = Pokemon;
        this.name = name;
        this.type = Type.valueOf(type);
        this.power = power;
    }

    public String getName() { return name; }
    public Type getType() { return type; }
    public int getPower() { return power; }
    public ImageIcon getTypeImage(Type type, int x , int y) {
        ImageIcon image = new ImageIcon("C:\\Users\\adksp\\IdeaProjects\\ProjectPokemon\\src\\assets\\Types\\"+type+".png");
        Image scaled = image.getImage().getScaledInstance(x, y, Image.SCALE_SMOOTH);
        return new ImageIcon(scaled);
    }

    private static double[][] typeChart = {
            //              FIRE, WATER, GRASS, NORMAL, ELECTRIC, FIGHTING, FLYING, FAIRY, BUG, DRAGON, ICE, GROUND, HEAL // ... add more types
            /* FIRE */     {1.0,  0.5,   2.0,   1.0,    1.0,      1.0,      1.0,    1.0,   2.0, 0.5,    2.0, 1.0, 1.0},
            /* WATER */    {2.0,  1.0,   0.5,   1.0,    1.0,      1.0,      1.0,    1.0,   1.0, 0.5,    1.0, 2.0, 1.0},
            /* GRASS */    {0.5,  2.0,   1.0,   1.0,    1.0,      1.0,      0.5,    1.0,   0.5, 0.5,    1.0, 2.0, 1.0},
            /* NORMAL */   {1.0,  1.0,   1.0,   1.0,    1.0,      1.0,      1.0,    1.0,   1.0, 0.5,    1.0, 1.0, 1.0},
            /* ELECTRIC */ {1.0,  2.0,   0.5,   1.0,    0.5,      1.0,      2.0,    1.0,   1.0, 0.5,    1.0, 0.0, 1.0},
            /* FIGHTING */ {1.0,  1.0,   1.0,   2.0,    1.0,      1.0,      0.5,    0.5,   0.5, 1.0,    2.0, 1.0, 1.0},
            /* FLYING */   {1.0,  1.0,   2.0,   1.0,    0.5,      2.0,      1.0,    1.0,   2.0, 1.0,    1.0, 1.0, 1.0},
            /* FAIRY */    {1.0,  1.0,   1.0,   1.0,    1.0,      2.0,      1.0,    1.0,   1.0, 2.0,    1.0, 1.0, 1.0},
            /* BUG */      {0.5,  1.0,   2.0,   1.0,    1.0,      0.5,      0.5,    0.5,   1.0, 1.0,    1.0, 1.0, 1.0},
            /* DRAGON */   {1.0,  1.0,   1.0,   1.0,    1.0,      1.0,      1.0,    0.0,   1.0, 2.0,    1.0, 1.0, 1.0},
            /* ICE */      {0.5,  0.5,   2.0,   1.0,    1.0,      1.0,      2.0,    1.0,   1.0, 2.0,    0.5, 2.0, 1.0},
            /* GROUND */   {2.0,  1.0,   0.5,   1.0,    2.0,      1.0,      0.0,    1.0,   0.5, 1.0,    1.0, 1.0, 1.0},
            /* HEAL */     {1.0,  1.0,   1.0,   1.0,    1.0,      1.0,      1.0,    1.0,   1.0, 1.0,    1.0, 1.0, 1.0},
    };

    private static double getTypeEffectiveness(Type attackType, Type defendType) {
        int attackIndex = attackType.ordinal(); // Get the index of the attack type in the enum
        int defendIndex = defendType.ordinal(); // Get the index of the defense type in the enum

        // Make sure your typeChart is large enough
        if (attackIndex < typeChart.length && defendIndex < typeChart[attackIndex].length) {
            return typeChart[attackIndex][defendIndex];
        } else {
            System.out.println("Type effectiveness data not found for " + attackType + " attacking " + defendType + ". Returning 1.0 (normal effectiveness).");
            return 1.0; // Default to normal effectiveness if data is missing
        }

    }
}
