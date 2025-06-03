package panel;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class PreBattle extends JPanel {
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

        if (podiumIcon != null && podiumIcon.getImageLoadStatus() == MediaTracker.COMPLETE) {
            Image scaledPodiumImage = podiumIcon.getImage().getScaledInstance(1450, 850, Image.SCALE_SMOOTH);
            ImageIcon scaledPodiumIcon = new ImageIcon(scaledPodiumImage);

            pod1Label = new JLabel(scaledPodiumIcon);
            pod1Label.setBounds(0, -150, 1450, 850);
            add(pod1Label);

            pod2Label = new JLabel(scaledPodiumIcon);
            pod2Label.setBounds(700, -150, 1450, 850);
            add(pod2Label);
        } else {
            System.err.println("Podium icon is null or failed to load. Check path and file existence.");
        }

        JButton playButton = new JButton("Play");
        playButton.setForeground(Color.BLACK);
        playButton.setFont(new Font("Source Sans Pro", Font.BOLD, 30));
        playButton.setBackground(Color.decode("#aae6ff"));
        playButton.setBounds(550, 425, 200, 50);
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
        p1_label.setBounds(205, -30, 540, 200);
        add(p1_label);

        JLabel p2_label = new JLabel("Player 2");
        p2_label.setOpaque(false);
        p2_label.setBackground(new Color(0, 0, 0, 0));
        p2_label.setForeground(Color.decode("#fcdc59"));
        p2_label.setBorder(null);
        p2_label.setFont(new Font("Source Sans Pro", Font.BOLD, 50));
        p2_label.setBounds(900, -30, 540, 200);
        add(p2_label);

        if (p1_idx >= 0 && p1_idx < POKEMON_ANIMATION_NAMES.length &&
                p1_idx < ChoosePlayer1.pokemon.length && ChoosePlayer1.pokemon[p1_idx] != null) {

            JLabel pickedPok1Display = new JLabel();
            try {
                Animation anim1 = new Animation(pickedPok1Display, POKEMON_ANIMATION_NAMES[p1_idx], "front", 350, 350);
                anim1.start();

                pickedPok1Display.setBounds(150, 100, 335, 350);
                add(pickedPok1Display);

            } catch (Exception e) {
                System.err.println("Gagal membuat/memulai animasi untuk Player 1 Pokemon: " + POKEMON_ANIMATION_NAMES[p1_idx]);
                e.printStackTrace();
            }
        } else {
            System.err.println("Error: Indeks tidak valid atau Pokemon null untuk Player 1 saat di PreBattle. Indeks: " + p1_idx);
        }

        if (p2_idx >= 0 && p2_idx < POKEMON_ANIMATION_NAMES.length &&
                p2_idx < ChoosePlayer1.pokemon.length && ChoosePlayer1.pokemon[p2_idx] != null) {

            JLabel pickedPok2Display = new JLabel();
            try {
                Animation anim2 = new Animation(pickedPok2Display, POKEMON_ANIMATION_NAMES[p2_idx], "front", 350, 350);
                anim2.start();

                pickedPok2Display.setBounds(850, 100, 335, 350);
                add(pickedPok2Display);

            } catch (Exception e) {
                System.err.println("Gagal membuat/memulai animasi untuk Player 2 Pokemon: " + POKEMON_ANIMATION_NAMES[p2_idx]);
                e.printStackTrace();
            }
        } else {
            System.err.println("Error: Indeks tidak valid atau Pokemon null untuk Player 2 saat di PreBattle. Indeks: " + p2_idx);
        }
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
}
