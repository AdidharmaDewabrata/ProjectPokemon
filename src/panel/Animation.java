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
            Back[i] = new ImageIcon("C:\\Users\\asma\\IdeaProjects\\ProjectPokemon\\src\\assets\\Blastoise Back\\blastoise (1)-export"+(i+1)+".png");
            scaled1[i] = Back[i].getImage().getScaledInstance(x,y,Image.SCALE_SMOOTH);
            blastoiseBack[i] = new ImageIcon(scaled1[i]);
        }
        for(int i = 0; i < 244; i++){
            Front[i] = new ImageIcon("C:\\Users\\asma\\IdeaProjects\\ProjectPokemon\\src\\assets\\Blastoise Front\\blastoise-export"+(i+1)+".png");
            scaled2[i] = Front[i].getImage().getScaledInstance(x,y,Image.SCALE_SMOOTH);
            blastoiseFront[i] = new ImageIcon(scaled2[i]);
        }
        if(direction.contains("front")){
            return blastoiseFront;
        } else {
            return blastoiseBack;
        }
    }

    public ImageIcon[] getClefable(String direction, int x, int y){
        ImageIcon[] Front = new ImageIcon[102];
        Image[] scaled1 = new Image[102];
        ImageIcon[] clefableFront = new ImageIcon[102];
        ImageIcon[] Back = new ImageIcon[102];
        Image[] scaled2 = new Image[102];
        ImageIcon[] clefableBack = new ImageIcon[102];
        for(int i = 0; i < 102; i++){
            Back[i] = new ImageIcon("C:\\Users\\asma\\IdeaProjects\\ProjectPokemon\\src\\assets\\Clefable Back\\clefable (back)-export"+(i+1)+".png");
            scaled1[i] = Back[i].getImage().getScaledInstance(x,y,Image.SCALE_SMOOTH);
            clefableBack[i] = new ImageIcon(scaled1[i]);
        }
        for(int i = 0; i < 102; i++){
            Front[i] = new ImageIcon("C:\\Users\\asma\\IdeaProjects\\ProjectPokemon\\src\\assets\\Clefable Front\\clefable-export"+(i+1)+".png");
            scaled2[i] = Front[i].getImage().getScaledInstance(x,y,Image.SCALE_SMOOTH);
            clefableFront[i] = new ImageIcon(scaled2[i]);
        }
        if(direction.contains("front")){
            return clefableFront;
        } else {
            return clefableBack;
        }
    }

    public ImageIcon[] getDragonite(String direction, int x, int y){
        ImageIcon[] Front = new ImageIcon[102];
        Image[] scaled1 = new Image[102];
        ImageIcon[] dragoniteFront = new ImageIcon[102];
        ImageIcon[] Back = new ImageIcon[102];
        Image[] scaled2 = new Image[102];
        ImageIcon[] dragoniteBack = new ImageIcon[102];
        for(int i = 0; i < 102; i++){
            Back[i] = new ImageIcon("C:\\Users\\asma\\IdeaProjects\\ProjectPokemon\\src\\assets\\Dragonite Back\\dragonite (1)-export"+(i+1)+".png");
            scaled1[i] = Back[i].getImage().getScaledInstance(x,y,Image.SCALE_SMOOTH);
            dragoniteBack[i] = new ImageIcon(scaled1[i]);
        }
        for(int i = 0; i < 102; i++){
            Front[i] = new ImageIcon("C:\\Users\\asma\\IdeaProjects\\ProjectPokemon\\src\\assets\\Dragonite Front\\dragonite-export"+(i+1)+".png");
            scaled2[i] = Front[i].getImage().getScaledInstance(x,y,Image.SCALE_SMOOTH);
            dragoniteFront[i] = new ImageIcon(scaled2[i]);
        }
        if(direction.contains("front")){
            return dragoniteFront;
        } else {
            return dragoniteBack;
        }
    }

    public ImageIcon[] getGalvantula(String direction, int x, int y){
        ImageIcon[] Front = new ImageIcon[143];
        Image[] scaled1 = new Image[143];
        ImageIcon[] galvantulaFront = new ImageIcon[143];
        ImageIcon[] Back = new ImageIcon[143];
        Image[] scaled2 = new Image[143];
        ImageIcon[] galvantulaBack = new ImageIcon[143];
        for(int i = 0; i < 143; i++){
            Back[i] = new ImageIcon("C:\\Users\\asma\\IdeaProjects\\ProjectPokemon\\src\\assets\\Galvantula Back\\galvantula-export"+(i+1)+".png");
            scaled1[i] = Back[i].getImage().getScaledInstance(x,y,Image.SCALE_SMOOTH);
            galvantulaBack[i] = new ImageIcon(scaled1[i]);
        }
        for(int i = 0; i < 143; i++){
            Front[i] = new ImageIcon("C:\\Users\\asma\\IdeaProjects\\ProjectPokemon\\src\\assets\\Galvantula Front\\galvantula-export"+(i+1)+".png");
            scaled2[i] = Front[i].getImage().getScaledInstance(x,y,Image.SCALE_SMOOTH);
            galvantulaFront[i] = new ImageIcon(scaled2[i]);
        }
        if(direction.contains("front")){
            return galvantulaFront;
        } else {
            return galvantulaBack;
        }
    }

    public ImageIcon[] getGlaceon(String direction, int x, int y){
        ImageIcon[] Front = new ImageIcon[171];
        Image[] scaled1 = new Image[171];
        ImageIcon[] glaceonFront = new ImageIcon[171];
        ImageIcon[] Back = new ImageIcon[171];
        Image[] scaled2 = new Image[171];
        ImageIcon[] glaceonBack = new ImageIcon[171];
        for(int i = 0; i < 171; i++){
            Back[i] = new ImageIcon("C:\\Users\\asma\\IdeaProjects\\ProjectPokemon\\src\\assets\\Glaceon Back\\glaceon (1)-export"+(i+1)+".png");
            scaled1[i] = Back[i].getImage().getScaledInstance(x,y,Image.SCALE_SMOOTH);
            glaceonBack[i] = new ImageIcon(scaled1[i]);
        }
        for(int i = 0; i < 171; i++){
            Front[i] = new ImageIcon("C:\\Users\\asma\\IdeaProjects\\ProjectPokemon\\src\\assets\\Glaceon Front\\glaceon-export"+(i+1)+".png");
            scaled2[i] = Front[i].getImage().getScaledInstance(x,y,Image.SCALE_SMOOTH);
            glaceonFront[i] = new ImageIcon(scaled2[i]);
        }
        if(direction.contains("front")){
            return glaceonFront;
        } else {
            return glaceonBack;
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
            Back[i] = new ImageIcon("C:\\Users\\asma\\IdeaProjects\\ProjectPokemon\\src\\assets\\Charizard Back\\charizard (1)-export"+(i+1)+".png");
            scaled1[i] = Back[i].getImage().getScaledInstance(x,y,Image.SCALE_SMOOTH);
            charizardBack[i] = new ImageIcon(scaled1[i]);
        }
        for(int i = 0; i < 143; i++){
            Front[i] = new ImageIcon("C:\\Users\\asma\\IdeaProjects\\ProjectPokemon\\src\\assets\\Charizard Front\\charizard-export"+(i+1)+".png");
            scaled2[i] = Front[i].getImage().getScaledInstance(x,y,Image.SCALE_SMOOTH);
            charizardFront[i] = new ImageIcon(scaled2[i]);
        }
        if(direction.contains("front")){
            return charizardFront;
        } else {
            return charizardBack;
        }
    }

    public ImageIcon[] getInfernape(String direction, int x, int y){
        ImageIcon[] Front = new ImageIcon[133];
        Image[] scaled1 = new Image[133];
        ImageIcon[] infernapeFront = new ImageIcon[133];
        ImageIcon[] Back = new ImageIcon[133];
        Image[] scaled2 = new Image[133];
        ImageIcon[] infernapeBack = new ImageIcon[133];
        for(int i = 0; i < 133; i++){
            Back[i] = new ImageIcon("C:\\Users\\asma\\IdeaProjects\\ProjectPokemon\\src\\assets\\Infernape Back\\infernape (1)-export"+(i+1)+".png");
            scaled1[i] = Back[i].getImage().getScaledInstance(x,y,Image.SCALE_SMOOTH);
            infernapeBack[i] = new ImageIcon(scaled1[i]);
        }
        for(int i = 0; i < 133; i++){
            Front[i] = new ImageIcon("C:\\Users\\asma\\IdeaProjects\\ProjectPokemon\\src\\assets\\Infernape Front\\infernape-export"+(i+1)+".png");
            scaled2[i] = Front[i].getImage().getScaledInstance(x,y,Image.SCALE_SMOOTH);
            infernapeFront[i] = new ImageIcon(scaled2[i]);
        }
        if(direction.contains("front")){
            return infernapeFront;
        } else {
            return infernapeBack;
        }
    }

    public ImageIcon[] getMarowak(String direction, int x, int y){
        ImageIcon[] Front = new ImageIcon[133];
        Image[] scaled1 = new Image[133];
        ImageIcon[] marowakFront = new ImageIcon[133];
        ImageIcon[] Back = new ImageIcon[133];
        Image[] scaled2 = new Image[133];
        ImageIcon[] marowakBack = new ImageIcon[133];
        for(int i = 0; i < 133; i++){
            Back[i] = new ImageIcon("C:\\Users\\asma\\IdeaProjects\\ProjectPokemon\\src\\assets\\Marowak Back\\marowak (1)-export"+(i+1)+".png");
            scaled1[i] = Back[i].getImage().getScaledInstance(x,y,Image.SCALE_SMOOTH);
            marowakBack[i] = new ImageIcon(scaled1[i]);
        }
        for(int i = 0; i < 133; i++){
            Front[i] = new ImageIcon("C:\\Users\\asma\\IdeaProjects\\ProjectPokemon\\src\\assets\\Marowak Front\\marowak-export"+(i+1)+".png");
            scaled2[i] = Front[i].getImage().getScaledInstance(x,y,Image.SCALE_SMOOTH);
            marowakFront[i] = new ImageIcon(scaled2[i]);
        }
        if(direction.contains("front")){
            return marowakFront;
        } else {
            return marowakBack;
        }
    }

    public ImageIcon[] getMasquerain(String direction, int x, int y){
        ImageIcon[] Front = new ImageIcon[171];
        Image[] scaled1 = new Image[171];
        ImageIcon[] masquerainFront = new ImageIcon[171];
        ImageIcon[] Back = new ImageIcon[171];
        Image[] scaled2 = new Image[171];
        ImageIcon[] masquerainBack = new ImageIcon[171];
        for(int i = 0; i < 171; i++){
            Back[i] = new ImageIcon("C:\\Users\\asma\\IdeaProjects\\ProjectPokemon\\src\\assets\\Masquerain Back\\masquerain (1)-export"+(i+1)+".png");
            scaled1[i] = Back[i].getImage().getScaledInstance(x,y,Image.SCALE_SMOOTH);
            masquerainBack[i] = new ImageIcon(scaled1[i]);
        }
        for(int i = 0; i < 171; i++){
            Front[i] = new ImageIcon("C:\\Users\\asma\\IdeaProjects\\ProjectPokemon\\src\\assets\\Masquerain Front\\masquerain-export"+(i+1)+".png");
            scaled2[i] = Front[i].getImage().getScaledInstance(x,y,Image.SCALE_SMOOTH);
            masquerainFront[i] = new ImageIcon(scaled2[i]);
        }
        if(direction.contains("front")){
            return masquerainFront;
        } else {
            return masquerainBack;
        }
    }

    public ImageIcon[] getPikachu(String direction, int x, int y){
        ImageIcon[] Front = new ImageIcon[113];
        Image[] scaled1 = new Image[113];
        ImageIcon[] pikachuFront = new ImageIcon[113];
        ImageIcon[] Back = new ImageIcon[113];
        Image[] scaled2 = new Image[113];
        ImageIcon[] pikachuBack = new ImageIcon[113];
        for(int i = 0; i < 113; i++){
            Back[i] = new ImageIcon("C:\\Users\\asma\\IdeaProjects\\ProjectPokemon\\src\\assets\\Pikachu Back\\pikachu (1)-export"+(i+1)+".png");
            scaled1[i] = Back[i].getImage().getScaledInstance(x,y,Image.SCALE_SMOOTH);
            pikachuBack[i] = new ImageIcon(scaled1[i]);
        }
        for(int i = 0; i < 113; i++){
            Front[i] = new ImageIcon("C:\\Users\\asma\\IdeaProjects\\ProjectPokemon\\src\\assets\\Pikachu Front\\pikachu-export"+(i+1)+".png");
            scaled2[i] = Front[i].getImage().getScaledInstance(x,y,Image.SCALE_SMOOTH);
            pikachuFront[i] = new ImageIcon(scaled2[i]);
        }
        if(direction.contains("front")){
            return pikachuFront;
        } else {
            return pikachuBack;
        }
    }

    public ImageIcon[] getSnorlax(String direction, int x, int y){
        ImageIcon[] Front = new ImageIcon[173];
        Image[] scaled1 = new Image[173];
        ImageIcon[] snorlaxFront = new ImageIcon[173];
        ImageIcon[] Back = new ImageIcon[173];
        Image[] scaled2 = new Image[173];
        ImageIcon[] snorlaxBack = new ImageIcon[173];
        for(int i = 0; i < 173; i++){
            Back[i] = new ImageIcon("C:\\Users\\asma\\IdeaProjects\\ProjectPokemon\\src\\assets\\Snorlax Back\\snorlax (1)-export"+(i+1)+".png");
            scaled1[i] = Back[i].getImage().getScaledInstance(x,y,Image.SCALE_SMOOTH);
            snorlaxBack[i] = new ImageIcon(scaled1[i]);
        }
        for(int i = 0; i < 113; i++){
            Front[i] = new ImageIcon("C:\\Users\\asma\\IdeaProjects\\ProjectPokemon\\src\\assets\\Snorlax Front\\snorlax-export"+(i+1)+".png");
            scaled2[i] = Front[i].getImage().getScaledInstance(x,y,Image.SCALE_SMOOTH);
            snorlaxFront[i] = new ImageIcon(scaled2[i]);
        }
        if(direction.contains("front")){
            return snorlaxFront;
        } else {
            return snorlaxBack;
        }
    }

    public ImageIcon[] getVenusaur(String direction, int x, int y){
        ImageIcon[] Front = new ImageIcon[87];
        Image[] scaled1 = new Image[87];
        ImageIcon[] venusaurFront = new ImageIcon[87];
        ImageIcon[] Back = new ImageIcon[87];
        Image[] scaled2 = new Image[87];
        ImageIcon[] venusaurBack = new ImageIcon[87];
        for(int i = 0; i < 87; i++){
            Back[i] = new ImageIcon("C:\\Users\\asma\\IdeaProjects\\ProjectPokemon\\src\\assets\\Venusaur Back\\venusaur (back)-export"+(i+1)+".png");
            scaled1[i] = Back[i].getImage().getScaledInstance(x,y,Image.SCALE_SMOOTH);
            venusaurBack[i] = new ImageIcon(scaled1[i]);
        }
        for(int i = 0; i < 87; i++){
            Front[i] = new ImageIcon("C:\\Users\\asma\\IdeaProjects\\ProjectPokemon\\src\\assets\\Venusaur Front\\venusaur-"+(i+1)+".png");
            scaled2[i] = Front[i].getImage().getScaledInstance(x,y,Image.SCALE_SMOOTH);
            venusaurFront[i] = new ImageIcon(scaled2[i]);
        }
        if(direction.contains("front")){
            return venusaurFront;
        } else {
            return venusaurBack;
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
            case "clefable":
                this.image = getClefable(direction, sizeX, sizeY);
                break;
            case "glaceon":
                this.image = getGlaceon(direction, sizeX, sizeY);
                break;
            case "galvantula":
                this.image = getGalvantula(direction, sizeX, sizeY);
                break;
            case "dragonite":
                this.image = getDragonite(direction, sizeX, sizeY);
                break;
            case "snorlax":
                this.image = getSnorlax(direction, sizeX, sizeY);
                break;
            case "pikachu":
                this.image = getPikachu(direction, sizeX, sizeY);
                break;
            case "masquerain":
                this.image = getMasquerain(direction, sizeX, sizeY);
                break;
            case "marowak":
                this.image = getMarowak(direction, sizeX, sizeY);
                break;
            case "infernape":
                this.image = getInfernape(direction, sizeX, sizeY);
                break;
            case "venusaur":
                this.image = getVenusaur(direction, sizeX, sizeY);
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
