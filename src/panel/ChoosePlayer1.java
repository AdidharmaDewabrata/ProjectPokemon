package panel;

import pokemon.Pokemon;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import javax.sound.sampled.*;

public class ChoosePlayer1 extends JPanel {
    private Image back;
    private JLabel[] pokemonImage = new JLabel[12], pokemonName = new JLabel[12];
    private int j = 0;
    private Pokemon[] pokemon = new Pokemon[12];
    private String[][] dataPokemon = new String[12][5];

    public static final String CHOOSEPLAYER_MUSIC = "choose_player.wav";
    private static Clip clip;


    public ChoosePlayer1(CardLayout cardLayout, JPanel cardPanelContainer) {
        Scanner sc;
        this.setLayout(null);
        back = new ImageIcon("C:\\Users\\adksp\\Downloads\\de1bb37306257d5cd20a87c3df39a108.jpg").getImage();

        //Player 1 label
        JLabel player1 = new JLabel("Player 1");
        player1.setOpaque(false);
        player1.setBackground(new Color(0,0,0,0));
        player1.setForeground(Color.decode("#fcdc59"));
        player1.setBorder(null);
        player1.setFont(new Font("Source Sans Pro", Font.BOLD, 70));
        player1.setBounds(640,-30, 540,200);
        add(player1);

        //arrow kanan
        JLabel right = new JLabel(new ImageIcon("C:\\Users\\adksp\\Downloads\\Sprites\\right.png"));
        right.setBounds(940,400,100,100);
        add(right);

        //arrow kiri
        JLabel left = new JLabel(new ImageIcon("C:\\Users\\adksp\\Downloads\\Sprites\\left.png"));
        left.setBounds(550,400,100,100);
        add(left);

        //baca data dari pokemonchara.txt
        File file = new File("pokemonchara.txt");
        String[][] dataPokemon = new String[12][5]; // Assuming 12 Pokémon, 5 attributes
        Pokemon[] pokemon = new Pokemon[12];        // Your Pokémon array

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int o = 0;
            while ((line = br.readLine()) != null && o < 12) {
                sc = new Scanner(line);
                int u = 0;
                while (sc.hasNext() && u < 5) {
                    dataPokemon[o][u] = sc.next();
                    u++;
                }
                o++;
            }
        } catch (IOException e) {
            System.err.println(e);
        }

        for (int i = 0; i < 12; i++) {
            String s = dataPokemon[i][0];
            String w = dataPokemon[i][1];
            int hp = Integer.parseInt(dataPokemon[i][2]);
            int att = Integer.parseInt(dataPokemon[i][3]);
            int def = Integer.parseInt(dataPokemon[i][4]);

            pokemon[i] = new Pokemon(s, w, hp, att, def);
        }


        //ganti pokemon
        String[] pokemonlist = {"Pikachu","Charizard","Blastoise","Venusaur","Infernape","Snorlax","Clefable","Masquerain","Marowak","Dragonite","Galvantula","Glaceon"};
        for(int i = 0; i < pokemonlist.length; i++){
            pokemonImage[i] = new JLabel();
            pokemonImage[i].setVisible(false);
            new Animation(pokemonImage[i],pokemonlist[i],"front",300,300).start();
            pokemonImage[i].setBounds(640,300,300,300);
            add(pokemonImage[i]);
        }
        JLabel[] labelz = new JLabel[12];
        JLabel[] labelImage = new JLabel[12];
        //ganti nama pokemon
        for(int i = 0; i < pokemonName.length; i++){
            // Container label
            labelz[i] = new JLabel();
            labelz[i].setLayout(null);
            labelz[i].setBounds(500, 600, 600, 200);
            labelz[i].setOpaque(false); // optional: set true with color for debugging
            labelz[i].setVisible(false); // initially hidden
            add(labelz[i]);

            // Type image label
            labelImage[i] = new JLabel(pokemon[i].getTypeImage(325,175));
            labelImage[i].setBounds(-80, -20, 325, 175);
            setComponentZOrder(labelImage[i],0);// inside container
            labelz[i].add(labelImage[i]);

            // Name label
            pokemonName[i] = new JLabel(pokemon[i].getName());
            pokemonName[i].setForeground(Color.decode("#f7f7f0"));
            pokemonName[i].setFont(new Font("Times New Roman", Font.BOLD, 70));
            pokemonName[i].setBounds(160, 20, 400, 100); // relative to container
            labelz[i].add(pokemonName[i]);
            add(labelz[i]);
        }
        pokemonImage[0].setVisible(true);
        labelz[0].setVisible(true);
        setComponentZOrder(pokemonImage[0],0);

        right.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(j<11) {
                    j++;
                    pokemonImage[j - 1].setVisible(false);
                    pokemonImage[j].setVisible(true);
                    labelz[j - 1].setVisible(false);
                    labelz[j].setVisible(true);
                }
            }
        });

        left.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(j>0) {
                    j--;
                    pokemonImage[j + 1].setVisible(false);
                    pokemonImage[j].setVisible(true);
                    labelz[j + 1].setVisible(false);
                    labelz[j].setVisible(true);
                }
            }
        });

        JButton next = new JButton("Next");
        next.setFont(new Font("Times New Roman", Font.BOLD, 40));
        next.setForeground(Color.decode("#000000"));
        next.setBackground(Color.decode("#c4cd8e"));
        next.setBounds(550, 750, 250, 75);
        next.setVisible(true);
        setComponentZOrder(next,0);
        add(next);

        next.addActionListener(e -> {
            cardLayout.show(cardPanelContainer, "panel.ChoosePlayer2");
        });

        //Kontrol musik otomatis
        addHierarchyListener(new HierarchyListener() {
            @Override
            public void hierarchyChanged(HierarchyEvent e) {
                if ((e.getChangeFlags() & HierarchyEvent.SHOWING_CHANGED) != 0) {
                    if (isShowing()) {
                        startMusic();
                    } else {
                        stopMusic();
                    }
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(back, 0, 0, getWidth(), getHeight(), this);
    }

    public void startMusic() {
        stopMusic();

        Thread musicThread = new Thread(() -> {
            try {
                URL musicUrl = HomePage.class.getResource(CHOOSEPLAYER_MUSIC);
                if (musicUrl == null) {
                    System.err.println("File suara musik ChoosePlayer tidak ditemukan: " + CHOOSEPLAYER_MUSIC);
                    System.err.println("Lokasi kelas ChoosePlayer : " + HomePage.class.getProtectionDomain().getCodeSource().getLocation());
                    return;
                }

                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(musicUrl);

                if (clip != null) {
                    clip.close();
                }
                clip = AudioSystem.getClip();
                clip.open(audioInputStream);

                clip.loop(Clip.LOOP_CONTINUOUSLY);
                clip.start();
                System.out.println("ChoosePlayer music started.");
            } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
                System.err.println("Terjadi kesalahan saat memutar musik ChoosePlayer : " + e.getMessage());
                e.printStackTrace();
            }
        });
        musicThread.start();
    }

    public void stopMusic() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.close();
            System.out.println("ChoosePlayer music stopped.");
        }
    }
}






