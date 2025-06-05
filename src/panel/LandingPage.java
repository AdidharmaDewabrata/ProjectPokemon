package panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.*;

public class LandingPage extends JPanel {
    public static final String LANDINGPAGE_MUSIC = "landingpage.wav";
    private static Clip clip;
    public LandingPage(CardLayout cardLayout, JPanel mainPanel) {
        this.setLayout(new BorderLayout());
        Image back = new ImageIcon("C:\\Users\\asma\\IdeaProjects\\ProjectPokemon\\src\\assets\\poopermon.jpg").getImage();
        JPanel panel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(back, 0, 0, getWidth(), getHeight(), this);
            }
        };

        // set layout dan ukuran
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(1920, 1080));

        // Set layout panel
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 40, 10, 40);
// Panel tombol di bawah
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 20));
        buttonPanel.setOpaque(false);

// Tombol Start
        JButton start = new JButton("Start");
        start.setFont(new Font("Fredoka", Font.BOLD, 30));
        start.setForeground(Color.BLUE);
        start.setBackground(Color.decode("#faf5e2"));
        buttonPanel.add(start);

// Tombol History
        JButton history = new JButton("History");
        history.setFont(new Font("Fredoka", Font.BOLD, 30));
        history.setForeground(Color.BLUE);
        history.setBackground(Color.decode("#faf5e2"));
        buttonPanel.add(history);

// Tombol Exit
        JButton exitGame = new JButton("EXIT");
        exitGame.setFont(new Font("Fredoka", Font.BOLD, 30));
        exitGame.setForeground(Color.BLUE);
        exitGame.setBackground(Color.decode("#faf5e2"));
        buttonPanel.add(exitGame);

// Tambahkan button panel ke bawah
        panel.setLayout(new BorderLayout());
        panel.add(buttonPanel, BorderLayout.SOUTH);


        // Tambahkan ke LandingPage panel utama
        this.add(panel, BorderLayout.CENTER);

        // Event listener
        start.addActionListener(e -> {
            cardLayout.show(mainPanel, "panel.HomePage");
        });

        history.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Masih belum kelar!");
        });

        exitGame.addActionListener(e -> {
            System.exit(0);
        });

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
    public void startMusic() {
        stopMusic();

        Thread musicThread = new Thread(() -> {
            try {
                URL musicUrl = LandingPage.class.getResource(LANDINGPAGE_MUSIC);
                if (musicUrl == null) {
                    System.err.println("File suara musik LandingPage tidak ditemukan: " + LANDINGPAGE_MUSIC);
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
                System.out.println("LandingPage music started.");
            } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
                System.err.println("Terjadi kesalahan saat memutar musik LandingPage: " + e.getMessage());
                e.printStackTrace();
            }
        });
        musicThread.start();
    }


    public void stopMusic() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.close();
            System.out.println("LandingPage music stopped.");
        }
    }

}
