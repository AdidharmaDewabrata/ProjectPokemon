import panel.Base;
import panel.HomePage;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
//        pokemon.Pokemon pikachu = new pokemon.Pokemon("Pikachu", pokemon.Type.ELECTRIC, 35, 55, 40);
//        pikachu.addMove(new pokemon.Move("Thunderbolt", pokemon.Type.ELECTRIC, 90));
//        pikachu.addMove(new pokemon.Move("Quick Attack", pokemon.Type.NORMAL, 40));
//
//        pokemon.Pokemon bulbasaur = new pokemon.Pokemon("Bulbasaur", pokemon.Type.GRASS, 45, 49, 49);
//        bulbasaur.addMove(new pokemon.Move("Vine Whip", pokemon.Type.GRASS, 45));
//        bulbasaur.addMove(new pokemon.Move("Tackle", pokemon.Type.NORMAL, 40));
//
//        pokemon.Battle battle = new pokemon.Battle(pikachu, bulbasaur);
//        battle.start();

        SwingUtilities.invokeLater(Base::new);
    }
}