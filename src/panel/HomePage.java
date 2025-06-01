package panel;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class HomePage extends JPanel {
    private Image backgroundImage;
    public static final String HOMEPAGE_MUSIC = "homepage.wav";
    private static Clip clip;

    public HomePage(CardLayout cardLayout, JPanel cardPanelContainer) {
        backgroundImage = new ImageIcon("F:\\dv\\college\\code\\Intellij\\Pemlan\\ProjectPokemonGUI\\src\\assets\\showcase.png").getImage();
        setLayout(null);

        // images podium & poopermon
        ImageIcon poopermon = new ImageIcon("F:\\dv\\college\\code\\Intellij\\Pemlan\\ProjectPokemonGUI\\src\\assets\\headpoopermon.jpg.png");
        ImageIcon podium = new ImageIcon("F:\\dv\\college\\code\\Intellij\\Pemlan\\ProjectPokemonGUI\\src\\assets\\podium.png");
        Image pod1 = podium.getImage().getScaledInstance(1450, 850, Image.SCALE_SMOOTH);
        ImageIcon podium1 = new ImageIcon(pod1);

        JLabel title = new JLabel(poopermon);
        title.setBounds(-20, -30, poopermon.getIconWidth(), poopermon.getIconHeight());
        JLabel pod = new JLabel(podium1);
        pod.setVisible(true);
        pod.setLayout(null);
        pod.setBounds(-250, -30, podium.getIconWidth(), podium.getIconHeight());
        JLabel pods = new JLabel(podium1);
        pods.setVisible(true);
        pods.setLayout(null);
        pods.setBounds(250, -30, podium.getIconWidth(), podium.getIconHeight());

        JButton play = new JButton("Play");
        play.setBounds(1000, 250, 250, 75);
        play.setBackground(Color.decode("#fcdc59"));
        play.setFont(new Font("Tahoma", Font.BOLD, 40));

        JButton see = new JButton("See Pokemon's");
        see.setBounds(1000, 400, 250, 75);
        see.setBackground(Color.decode("#aae6ff"));
        see.setFont(new Font("Tahoma", Font.BOLD, 25));

        JButton back = new JButton("Back");
        back.setBounds(1000, 550, 250, 75);
        back.setBackground(Color.decode("#f23041"));
        back.setFont(new Font("Tahoma", Font.BOLD, 40));

        add(title);
        add(see);
        add(play);
        add(pod);
        add(pods);
        add(back);

        // Ini ke player 1 pick pokemon (pre-battle)
        play.addActionListener(e -> {
            cardLayout.show(cardPanelContainer, "panel.ChoosePlayer1");
            stopMusic();
        });

        // Lihat pokemon list
        see.addActionListener(e -> {
            cardLayout.show(cardPanelContainer, "panel.Showcase");
            stopMusic();
        });

        // back to landingpage
        back.addActionListener(e -> {
            cardLayout.show(cardPanelContainer, "panel.LandingPage");
//            stopMusic();
        });

        JLabel blastoise = new JLabel();
        new Animation(blastoise, "blastoise", "front",350, 350).start();
        blastoise.setBounds(120, 320, 335, 350);
        blastoise.setVisible(true);
        add(blastoise);
        setComponentZOrder(blastoise,0);

        JLabel charizard = new JLabel();
        new Animation(charizard, "charizard", "front",435, 455).start();
        charizard.setBounds(580, 220, 435, 455);
        charizard.setVisible(true);
        add(charizard);
        setComponentZOrder(charizard,0);

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
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    public void startMusic() {
        stopMusic();

        Thread musicThread = new Thread(() -> {
            try {
                URL musicUrl = HomePage.class.getResource(HOMEPAGE_MUSIC);
                if (musicUrl == null) {
                    System.err.println("File suara musik Homepage tidak ditemukan: " + HOMEPAGE_MUSIC);
                    System.err.println("Lokasi kelas HomePage: " + HomePage.class.getProtectionDomain().getCodeSource().getLocation());
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
                System.out.println("Homepage music started.");
            } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
                System.err.println("Terjadi kesalahan saat memutar musik Homepage: " + e.getMessage());
                e.printStackTrace();
            }
        });
        musicThread.start();
    }

    public void stopMusic() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.close();
            System.out.println("Homepage music stopped.");
        }
    }
}
