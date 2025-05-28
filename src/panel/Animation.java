package panel;

import javax.swing.*;
import java.awt.*;

public class Animation{
private String direction, name;
private ImageIcon[] image;
private int x, y, index = 0;
private JLabel label;

    public ImageIcon[] getBlastoise(String direction, int x, int y){
        ImageIcon[] Front = new ImageIcon[244];
        Image[] scaled1 = new Image[244];
        ImageIcon[] blastoiseFront = new ImageIcon[244];
        ImageIcon[] Back = new ImageIcon[244];
        Image[] scaled2 = new Image[244];
        ImageIcon[] blastoiseBack = new ImageIcon[244];
        for(int i = 0; i < 244; i++){
            Back[i] = new ImageIcon("C:\\Users\\adksp\\IdeaProjects\\ProjectPokemon\\src\\assets\\Blastoise Back\\blastoise (1)-export"+(i+1)+".png");
            scaled1[i] = Back[i].getImage().getScaledInstance(x,y,Image.SCALE_SMOOTH);
            blastoiseBack[i] = new ImageIcon(scaled1[i]);
        }
        for(int i = 0; i < 244; i++){
            Front[i] = new ImageIcon("C:\\Users\\adksp\\IdeaProjects\\ProjectPokemon\\src\\assets\\Blastoise Front\\blastoise-export"+(i+1)+".png");
            scaled2[i] = Front[i].getImage().getScaledInstance(x,y,Image.SCALE_SMOOTH);
            blastoiseFront[i] = new ImageIcon(scaled2[i]);
        }
        if(direction.contains("front")){
            return blastoiseFront;
        } else {
            return blastoiseBack;
        }
    }

    public ImageIcon[] getCharizard(String direction, int x, int y){
        ImageIcon[] Front = new ImageIcon[143];
        Image[] scaled1 = new Image[143];
        ImageIcon[] charizardFront = new ImageIcon[143];
        ImageIcon[] Back = new ImageIcon[143];
        Image[] scaled2 = new Image[143];
        ImageIcon[] charizardBack = new ImageIcon[143];
        for(int i = 0; i < 143; i++){
            Back[i] = new ImageIcon("C:\\Users\\adksp\\IdeaProjects\\ProjectPokemon\\src\\assets\\Charizard Back\\charizard (1)-export"+(i+1)+".png");
            scaled1[i] = Back[i].getImage().getScaledInstance(x,y,Image.SCALE_SMOOTH);
            charizardBack[i] = new ImageIcon(scaled1[i]);
        }
        for(int i = 0; i < 143; i++){
            Front[i] = new ImageIcon("C:\\Users\\adksp\\IdeaProjects\\ProjectPokemon\\src\\assets\\Charizard Front\\charizard-export"+(i+1)+".png");
            scaled2[i] = Front[i].getImage().getScaledInstance(x,y,Image.SCALE_SMOOTH);
            charizardFront[i] = new ImageIcon(scaled2[i]);
        }
        if(direction.contains("front")){
            return charizardFront;
        } else {
            return charizardBack;
        }
    }

    public Animation(JLabel label, String name, String direction, int sizeX, int sizeY){
        this.label = label;
        this.direction = direction;
        this.x = sizeX;
        this.y = sizeY;
        String name1 = name.toLowerCase();
        switch(name1){
            case "blastoise":
                this.image = getBlastoise(direction, sizeX, sizeY);
                break;
            case "charizard":
                this.image = getCharizard(direction, sizeX, sizeY);
                break;
        }
    }

    public void start(){
        Timer timer = new Timer(75, null);
        timer.addActionListener( e -> {
            index = (index + 1) % image.length;
            this.label.setIcon(this.image[index]);
        });
        timer.start();
    }
}
