package panel;

import pokemon.Pokemon; //ini

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class PreBattle extends JPanel {
    public static final String PREBATTLE_MUSIC = "preBattle.wav";
    private static Clip clip;
    private Image background, back;
    private JLabel pod1Label;
    private JLabel pod2Label;
    public int[] pickedPok = new int[2];

    private static final String[] POKEMON_ANIMATION_NAMES = {
            "Pikachu","Charizard","Blastoise","Venusaur","Infernape","Snorlax",
            "Clefable","Masquerain","Marowak","Dragonite","Galvantula","Glaceon"
    };

    public PreBattle(CardLayout cardLayout, JPanel cardPanelContainer, int p1_idx, int p2_idx) {
        back = new ImageIcon("C:\\Users\\asma\\IdeaProjects\\ProjectPokemon\\src\\assets\\bg_prebattle.png").getImage();
        setLayout(null);

        URL podiumUrl = getClass().getResource("/assets/podium.png");
        ImageIcon podiumIcon = null;
        if (podiumUrl != null) {
            podiumIcon = new ImageIcon(podiumUrl);
        } else {
            System.err.println("Podium image not found: /assets/podium.png. Pastikan path di dalam folder resources benar.");
        }

        Image scaledPodiumImage = podiumIcon.getImage().getScaledInstance(1450, 850, Image.SCALE_SMOOTH);
        ImageIcon scaledPodiumIcon = new ImageIcon(scaledPodiumImage);

        pod1Label = new JLabel(scaledPodiumIcon);
        pod1Label.setBounds(50, -40, 1450, 850);

        pod2Label = new JLabel(scaledPodiumIcon);
        pod2Label.setBounds(850, -40, 1450, 850);

        JButton playButton = new JButton("Play");
        playButton.setForeground(Color.BLACK);
        playButton.setFont(new Font("Source Sans Pro", Font.BOLD, 30));
        playButton.setBackground(Color.decode("#aae6ff"));
        playButton.setBounds(650, 520, 250, 75);
        add(playButton);
        playButton.addActionListener(e -> {
            BattlePage battlePage = new BattlePage(cardLayout, cardPanelContainer, p1_idx, p2_idx);
            cardPanelContainer.add(battlePage, "panel.BattlePage");
            cardLayout.show(cardPanelContainer, "panel.BattlePage");
        });

        JLabel p1_label = new JLabel("Player 1");
        p1_label.setOpaque(false);
        p1_label.setBackground(new Color(0, 0, 0, 0));
        p1_label.setForeground(Color.decode("#fcdc59"));
        p1_label.setBorder(null);
        p1_label.setFont(new Font("Source Sans Pro", Font.BOLD, 50));
        p1_label.setBounds(245, 100, 540, 200);
        add(p1_label);

        JLabel p2_label = new JLabel("Player 2");
        p2_label.setOpaque(false);
        p2_label.setBackground(new Color(0, 0, 0, 0));
        p2_label.setForeground(Color.decode("#fcdc59"));
        p2_label.setBorder(null);
        p2_label.setFont(new Font("Source Sans Pro", Font.BOLD, 50));
        p2_label.setBounds(1050, 100, 540, 200);
        add(p2_label);

        JLabel pickedPok1Display = new JLabel();
        Animation anim1 = new Animation(pickedPok1Display, POKEMON_ANIMATION_NAMES[p1_idx], "front", 300, 300);
        anim1.start();

        pickedPok1Display.setBounds(200, 250, 300, 300);
        add(pickedPok1Display);

        JLabel pickedPok2Display = new JLabel();
        Animation anim2 = new Animation(pickedPok2Display, POKEMON_ANIMATION_NAMES[p2_idx], "front", 300, 300);
        anim2.start();

        pickedPok2Display.setBounds(1000, 250, 300, 300);
        add(pickedPok2Display);

        Pokemon player1 = ChoosePlayer1.pokemon[p1_idx];
        Pokemon player2 = ChoosePlayer1.pokemon[p2_idx];
        //label nama pokemon
        JLabel container = new JLabel();
        container.setLayout(null);
        container.setBounds(215,530, 300, 125);
        JLabel name1 = new JLabel(player1.getName());
        name1.setForeground(Color.decode(player1.getColor()));
        name1.setBounds(90, 10, 200, 50);
        name1.setFont(new Font("Source Sans Pro", Font.BOLD, 30));
        JLabel image1 = new JLabel(player1.getTypeImage(110,80));
        container.add(name1);
        image1.setBounds(0,0,110,80);
        container.add(image1);
        setComponentZOrder(container,0);
        add(container);

        JLabel container2 = new JLabel();
        container2.setLayout(null); container2.setBounds(1030,530, 300, 75);
        JLabel name2 = new JLabel(player2.getName()); name2.setForeground(Color.decode(player2.getColor())); name2.setBounds(90, 10, 200, 50);
        name2.setFont(new Font("Source Sans Pro", Font.BOLD, 30));
        JLabel image2 = new JLabel(player2.getTypeImage(110,80)); container2.add(name2); image2.setBounds(0,0,110,80);container2.add(image2);
        setComponentZOrder(container2,0);
        add(container2);


        //label buat label hp, att, def
        JLabel[] hp = new JLabel[2];    JLabel[] att = new JLabel[2];    JLabel[] def = new JLabel[2];

        hp[0] = new JLabel("HP : " + player1.getHealth());
        att[0] = new JLabel("Att : " + player1.getAttack());
        def[0] = new JLabel("Def : " + player1.getDefense());
        hp[1] = new JLabel("HP : " + player2.getHealth());
        att[1] = new JLabel("Att : " + player2.getAttack());
        def[1] = new JLabel("Def : " + player2.getDefense());
        int x = 270, y = 625;

        for(int i = 0; i<2; i++) {
            hp[i].setBounds(x, y, 300, 60);
            y+=40;
            att[i].setBounds(x, y, 300, 60);
            y+=40;
            def[i].setBounds(x, y, 300, 60);
            hp[i].setForeground(Color.decode("#fcdc59"));
            att[i].setForeground(Color.decode("#fcdc59"));
            def[i].setForeground(Color.decode("#fcdc59"));
            hp[i].setFont(new Font("Source Sans Pro", Font.BOLD, 30));
            att[i].setFont(new Font("Source Sans Pro", Font.BOLD, 30));
            def[i].setFont(new Font("Source Sans Pro", Font.BOLD, 30));
            add(hp[i]); add(att[i]); add(def[i]);
            x+=825; y = 625;
        }

        add(pod1Label);
        add(pod2Label);

        //kontrol musik otomatis
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
        if (back != null) {
            g.drawImage(back, 0, 0, getWidth(), getHeight(), this);
        } else {
            g.setColor(Color.GRAY);
            g.fillRect(0,0,getWidth(), getHeight());
        }
    }

    public void startMusic() {
        stopMusic();

        Thread musicThread = new Thread(() -> {
            try {
                URL musicUrl = PreBattle.class.getResource(PREBATTLE_MUSIC);
                if (musicUrl == null) {
                    System.err.println("File suara musik prebattle tidak ditemukan: " + PREBATTLE_MUSIC);
                    System.err.println("Lokasi kelas prebattle : " + PreBattle.class.getProtectionDomain().getCodeSource().getLocation());
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
                System.out.println("prebattle music started.");
            } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
                System.err.println("Terjadi kesalahan saat memutar musik prebattle : " + e.getMessage());
                e.printStackTrace();
            }
        });
        musicThread.start();
    }

    public void stopMusic() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.close();
            System.out.println("prebattle music stopped.");
        }
    }
}

