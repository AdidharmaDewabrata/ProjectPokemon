package panel;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;
import java.io.IOException;
import java.net.URL;

public class BattlePage extends JPanel {
    private Image background;
    public static final String BATTLE_MUSIC = "battlePage.wav";
    private static Clip clip;

    public BattlePage(CardLayout cardLayout, JPanel cardPanelContainer, int p1, int p2) {
        background = new ImageIcon("C:\\Users\\asma\\IdeaProjects\\ProjectPokemon\\src\\panel\\6bea12ee9c7b069e8bdcf74726fdd299.jpg").getImage();
        setLayout(null);

        System.out.println("ini p1: "+p1);
        System.out.println("ini p2: "+p2);

        JPanel greenBox = new JPanel();
        greenBox.setBounds(0, 700, 1920, 180);
        greenBox.setBackground(Color.decode("#1f867b"));
        greenBox.setLayout(null);
        add(greenBox);

        //Buttons
        int x = 1000, y = 650;
        JButton[] button = new JButton[4];
        for (int i = 0; i < 4; i++) {
            button[i] = new JButton();
            button[i].setBackground(Color.decode("#c4cd8e"));
            if(i==2){
                x=1000;
                y=760;
                button[i].setBounds(x,y, 200, 90);
                add(button[i]);
                setComponentZOrder(button[i], 0);
            }
            button[i].setBounds(x,y, 200, 90);
            x += 220;
            add(button[i]);
            setComponentZOrder(button[i], 0);
        }
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
        g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
    }
    public void startMusic() {
        stopMusic();

        Thread musicThread = new Thread(() -> {
            try {
                URL musicUrl = BattlePage.class.getResource(BATTLE_MUSIC);
                if (musicUrl == null) {
                    System.err.println("File suara musik battlePage tidak ditemukan: " + BATTLE_MUSIC);
                    System.err.println("Lokasi kelas battlePage : " + BattlePage.class.getProtectionDomain().getCodeSource().getLocation());
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
                System.out.println("battlePage music started.");
            } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
                System.err.println("Terjadi kesalahan saat memutar musik battlePage : " + e.getMessage());
                e.printStackTrace();
            }
        });
        musicThread.start();
    }

    public void stopMusic() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.close();
            System.out.println("battle music stopped.");
        }
    }
}
