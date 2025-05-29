package pokemon;

import javax.swing.*;
import java.awt.*;

public class Move {
    private String name;
    private Type type;
    private int power;
    private Image image;
    public Move(String name, Type type, int power) {
        this.name = name;
        this.type = type;
        this.power = power;
    }

    public String getName() { return name; }
    public Type getType() { return type; }
    public int getPower() { return power; }
    public Image getTypeImage(Type type, int x , int y) {
        return new ImageIcon("C:\\Users\\adksp\\Downloads\\Sprites\\Types\\"+type+" type.png").getImage().getScaledInstance(x, y, Image.SCALE_SMOOTH);
    }
}