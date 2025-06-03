package panel;
import pokemon.Pokemon;
import pokemon.Battle;
import pokemon.Move;
import panel.Animation;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Arrays;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.sound.sampled.*;

public class Showcase extends JPanel {
    private Image backgroundImage;
    public static final String HOMEPAGE_MUSIC = "homepage.wav";
    private static Clip clip;

    // Subkelas untuk panel dengan latar belakang gambar
    private static class BgPanel extends JPanel {
        private final Image background;

        //biar pasti bakal kepanggil pake class
        public BgPanel(String imagePath) {
            this.background = new ImageIcon(imagePath).getImage();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
        }
    }

    public Showcase() {
    }

    public Showcase(CardLayout cardLayout, JPanel cardPanelContainer) {
        this.setLayout(new BorderLayout());

        // Pastikan path ini valid di sistemmu
        String backgroundPath = "C:\\Users\\asma\\IdeaProjects\\ProjectPokemon\\src\\assets\\showcase.png";
        BgPanel bgPanel = new BgPanel(backgroundPath);//manggil kelas yang di atas tadi
        bgPanel.setLayout(new BorderLayout());
        bgPanel.setPreferredSize(new Dimension(1280, 720)); // Ukuran disesuaikan frame

        //buat container/panel biar mereka diisinya diposisiin di atas kayak di ui nya
        JLabel containpoke = new JLabel();
        containpoke.setBounds(20, 30, 800, 600);
        containpoke.setLayout(new BorderLayout());
        containpoke.setBackground(Color.decode("#1f867b"));
        containpoke.setOpaque(true); // ini wajib

        JPanel kotak = new JPanel(null); // gunakan null layout
        kotak.setOpaque(false); // biar latar belakang tembus
        kotak.setPreferredSize(new Dimension(800, 600));
        kotak.add(containpoke);


        // Panel isi konten dengan grid 3x4 dan jarak antar komponen
        JPanel scp = new JPanel(new GridLayout(3, 4, 45, 45));
        scp.setOpaque(false); // biar tembus background
        scp.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        //buat manggil pokemonnya
        JLabel blastoise = new JLabel();
        JLabel charizard = new JLabel();
        JLabel clefable = new JLabel();
        JLabel dragonite = new JLabel();
        JLabel galvantula = new JLabel();
        JLabel glaceon = new JLabel();
        JLabel infernape = new JLabel();
        JLabel marowak = new JLabel();
        JLabel masquerain = new JLabel();
        JLabel pikachu = new JLabel();
        JLabel snorlax = new JLabel();
        JLabel venusaur = new JLabel();
        new Animation(blastoise, "blastoise", "front", 80, 80).start();
        new Animation(charizard, "charizard", "front", 80, 80).start();
        new Animation(clefable, "clefable", "front", 80, 80).start();
        new Animation(dragonite, "dragonite", "front", 80, 80).start();
        new Animation(galvantula, "galvantula", "front", 80, 80).start();
        new Animation(glaceon, "glaceon", "front", 80, 80).start();
        new Animation(infernape, "infernape", "front", 80, 80).start();
        new Animation(marowak, "marowak", "front", 80, 80).start();
        new Animation(masquerain, "masquerain", "front", 80, 80).start();
        new Animation(pikachu, "pikachu", "front", 80, 80).start();
        new Animation(snorlax, "snorlax", "front", 80, 80).start();
        new Animation(venusaur, "venusaur", "front", 80, 80).start();

        // List semua JLabel Pokémon
        List<JLabel> pokeLabels = Arrays.asList(
                blastoise, charizard, clefable, dragonite, galvantula, glaceon,
                infernape, marowak, masquerain, pikachu, snorlax, venusaur
        );

        // List nama Pokémon buat ditampilin
        List<String> pokeNames = Arrays.asList(
                "Blastoise", "Charizard", "Clefable", "Dragonite", "Galvantula",
                "Glaceon", "Infernape", "Marowak", "Masquerain", "Pikachu", "Snorlax", "Venusaur"
        );


        // Tambahkan gambar ke grid, ini buat kotak backgroudnnya dulu aja
        for (int i = 0; i < 12; i++) {
            //buat masukin pokemonnya
            JPanel pokeBox = new JPanel(new BorderLayout());
            pokeBox.setPreferredSize(new Dimension(150, 150));
            pokeBox.setBackground(Color.decode("#c4cd8e"));

            //buat nama tiap pokemonnya
            JLabel pokeName = new JLabel(pokeNames.get(i));
            pokeName.setHorizontalAlignment(SwingConstants.CENTER);
            pokeName.setFont(new Font("Tahoma", Font.BOLD, 18));
            pokeName.setForeground(Color.black);
            pokeBox.add(pokeName, BorderLayout.NORTH);

            JLabel pokeLabel = pokeLabels.get(i);
            pokeLabel.setHorizontalAlignment(SwingConstants.CENTER);
            pokeLabel.setVerticalAlignment(SwingConstants.CENTER);
            pokeBox.add(pokeLabel, BorderLayout.CENTER);

            scp.add(pokeBox);
        }

        // Tambahkan grid ke panel background
        containpoke.add(scp, BorderLayout.CENTER);

        bgPanel.add(kotak, BorderLayout.CENTER);

        // Tambahkan bgPanel ke panel utama
        this.add(bgPanel, BorderLayout.CENTER);

        //tombol play sama back
        // BUTTON AREA
        JButton play = new JButton("Play");
        play.setBounds(1100, 250, 150, 75);
        play.setBackground(Color.decode("#aae6ff"));
        play.setFont(new Font("Tahoma", Font.BOLD, 24));
        bgPanel.add(play);

        JButton back = new JButton("Back");
        back.setBounds(1100, 550, 150, 75);
        back.setBackground(Color.decode("#fcdc59"));
        back.setFont(new Font("Tahoma", Font.BOLD, 24));
        bgPanel.add(back);

        play.addActionListener(e -> {
            cardLayout.show(cardPanelContainer, "panel.ChoosePlayer1");
        });
        back.addActionListener(e -> {
            cardLayout.show(cardPanelContainer, "panel.HomePage");
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

    // Main method untuk testing
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Showcase Page Test");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setContentPane(new Showcase());
            frame.pack(); // ukur berdasarkan preferred size panel
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
