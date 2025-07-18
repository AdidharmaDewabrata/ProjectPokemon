package pokemon;
import javax.swing.*;
import java.awt.*;

// Battle.java
public class Battle {
    private Pokemon pokemon1;
    private Pokemon pokemon2;

    public Battle(Pokemon pokemon1, Pokemon pokemon2) {
        this.pokemon1 = pokemon1;
        this.pokemon2 = pokemon2;
    }

    public void start() {
        System.out.println("A battle begins between " + pokemon1.getName() + " and " + pokemon2.getName() + "!");

        while (!pokemon1.isFainted() && !pokemon2.isFainted()) {
            turn(pokemon1, pokemon2); // Pokemon 1's turn
            if (pokemon2.isFainted()) break; // Check if Pokemon 2 fainted
            turn(pokemon2, pokemon1); // Pokemon 2's turn
            if (pokemon1.isFainted()) break; // Check if Pokemon 1 fainted
        }

        if (pokemon1.isFainted()) {
            System.out.println(pokemon2.getName() + " wins!");
        } else {
            System.out.println(pokemon1.getName() + " wins!");
        }
    }

    private void turn(Pokemon attacker, Pokemon defender) {
        // (Simplified) Choose a move (you might want to let the user choose)
        Move chosenMove = attacker.getMoves().get(0); // For now, just the first move

        attacker.attack(defender, chosenMove);
    }

    public static int calculateDamage(Pokemon attacker, Move move, Pokemon defender) {
        double effectiveness = getTypeEffectiveness(move.getType(), defender.getType()); // Type effectiveness
        int damage = (int) ((move.getPower() * attacker.getAttack() / defender.getDefense()) * effectiveness);
        return Math.max(0, damage); // Ensure damage is not negative
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
    Battle(){
        JFrame frame = new JFrame("Battle");
        frame.setSize(1920, 1080);
        frame.getContentPane().setBackground(Color.decode("#faf5e2"));
        frame.setVisible(true);
    }
}
