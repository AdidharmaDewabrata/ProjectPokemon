package pokemon;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Pokemon {
    private String name, color;
    private Type type;
    private int health;
    private int attack;
    private int defense;
    private int currentHealth;
    private List<Move> moves;
    private String spritesPath;

    public Pokemon(String name, String type, int health, int attack, int defense, String color) {
        this.name = name;
        this.type = Type.valueOf(type);
        this.health = health;
        this.attack = attack;
        this.defense = defense;
        this.moves = new ArrayList<>();
        this.color = color;
        this.currentHealth = health;
    }

    // ***Getters and Setters (Crucial!)***
    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public int getHealth() {
        return health;
    }

    public void setCurrentHealth(int currentHealth) { this.currentHealth = currentHealth;}

    public int getCurrentHealth() { return currentHealth;}

    public void setHealth(int health) { // Setter for health (needed for healing)
        this.health = health;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void addMove(Move move) {
        moves.add(move);
    }

    public void attack(Pokemon target, Move move) {
        int damage = Battle.calculateDamage(this, move, target);
        target.takeDamage(damage);
        System.out.println(this.name + " used " + move.getName() + " on " + target.getName() + " for " + damage + " damage!");
    }

    public void takeDamage(int damage) {
        this.health -= damage;
        if (this.health <= 0) {
            System.out.println(this.name + " fainted!");
        }
    }

    public boolean isFainted() {
        return health <= 0;
    }

    public ImageIcon getTypeImage(int x, int y) {
        ImageIcon image = new ImageIcon("C:\\Users\\adksp\\IdeaProjects\\ProjectPokemon\\src\\assets\\Types\\"+type+".png");
        Image scaled = image.getImage().getScaledInstance(x, y, Image.SCALE_SMOOTH);
        return new ImageIcon(scaled); // ✅ Return ImageIcon
    }

    public String getColor() {
        return color;
    }

    //buat cek masuk atau ga datanya
    @Override
    public String toString() {
        return name + " | " + type + " | HP: " + health + " | ATK: " + attack + " | DEF: " + defense + " | Sprites: \n" + spritesPath;
    }
}
