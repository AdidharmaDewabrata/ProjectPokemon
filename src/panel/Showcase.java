package panel;
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
    //private String selectedPokemon;

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

//public String getSelect(){
//        return selectedPokemon;
//}

//public static String getSelected(){
//        Showcase sc = new Showcase();
//        return sc.getSelect();
//}

public Showcase() {}

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

        // Buat satu set JLabel Animation di sini dan gunakan kembali
        // atau buat instance Animation baru di dalam loop jika memang perlu
        JLabel[] pokeAnimationLabels = new JLabel[12];
        List<String> pokeNames = Arrays.asList(
                "Pikachu", "Charizard", "Blastoise", "Venusaur", "Infernape", "Snorlax",
                "Clefable", "Masquerain", "Marowak", "Dragonite", "Galvantula", "Glaceon"
        );
        // Sesuaikan urutan pokeNames dengan urutan pokemon di ChoosePlayer1.pokemon[]
        // atau pastikan indeksnya cocok. Saya asumsikan urutan di ChoosePlayer1.pokemon adalah
        // Pikachu, Charizard, Blastoise, Venusaur, Infernape, Snorlax, Clefable, Masquerain, Marowak, Dragonite, Galvantula, Glaceon

        // Inisialisasi JLabel Animation
        for (int i = 0; i < pokeNames.size(); i++) {
            pokeAnimationLabels[i] = new JLabel();
            // Start animation, tapi jangan tampilkan dulu, nanti di DetailPokemon
            // new Animation(pokeAnimationLabels[i], pokeNames.get(i), "front", 80, 80).start();
        }

        // Tambahkan gambar ke grid
        for (int i = 0; i < pokeNames.size(); i++) {
            JButton pokeBox = new JButton();
            pokeBox.setLayout(new BorderLayout());
            pokeBox.setPreferredSize(new Dimension(150, 150));
            pokeBox.setBackground(Color.decode("#c4cd8e"));
            pokeBox.setFocusPainted(false);

            JLabel pokeNameLabel = new JLabel(pokeNames.get(i));
            pokeNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
            pokeNameLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
            pokeNameLabel.setForeground(Color.black);
            pokeBox.add(pokeNameLabel, BorderLayout.NORTH);

            // Tampilkan animasi Pokémon di sini
            JLabel currentPokeAnimLabel = new JLabel(); // Buat label baru untuk animasi di Showcase
            // Perbaiki ukuran animasi di Showcase
            new Animation(currentPokeAnimLabel, pokeNames.get(i), "front", 80, 80).start();
            currentPokeAnimLabel.setHorizontalAlignment(SwingConstants.CENTER);
            currentPokeAnimLabel.setVerticalAlignment(SwingConstants.CENTER);
            pokeBox.add(currentPokeAnimLabel, BorderLayout.CENTER);


            int j = i; // final index for lambda
            pokeBox.addActionListener(e -> {
                // Di sini, kita langsung panggil DetailPokemon dengan nama Pokémon yang dipilih
                DetailPokemon detailPanel = new DetailPokemon(cardLayout, cardPanelContainer, pokeNames.get(j));
                cardPanelContainer.add(detailPanel, "panel.DetailPokemon");
                cardLayout.show(cardPanelContainer, "panel.DetailPokemon");
            });

            scp.add(pokeBox);
        }
        // Tambahkan grid ke panel background
        containpoke.add(scp, BorderLayout.CENTER);
        bgPanel.add(kotak, BorderLayout.CENTER);

        //tombol play sama back
        JButton play = new JButton("Play");
        play.setBackground(Color.decode("#aae6ff"));
        play.setFont(new Font("Tahoma", Font.BOLD, 40));
//        bgPanel.add(play);

        JButton back = new JButton("Back");
        back.setBackground(Color.decode("#fcdc59"));
        back.setFont(new Font("Tahoma", Font.BOLD, 40));
//        bgPanel.add(back);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        //buttonPanel.setPreferredSize(new Dimension(250, 75));
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS)); // Gunakan BoxLayout untuk vertikal
        buttonPanel.add(Box.createVerticalGlue()); // Untuk push tombol ke bawah
        buttonPanel.add(play);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 50))); // Jarak antar tombol
        buttonPanel.add(back);
        buttonPanel.add(Box.createVerticalGlue()); // Untuk push tombol ke atas
        bgPanel.add(buttonPanel, BorderLayout.EAST); // Tambahkan buttonPanel ke bagian EAST

        play.addActionListener(e -> {
            cardLayout.show(cardPanelContainer, "panel.ChoosePlayer1");
        });
        back.addActionListener(e -> {
            cardLayout.show(cardPanelContainer, "panel.HomePage");
        });

        // Tambahkan bgPanel ke panel utama
        this.add(bgPanel, BorderLayout.CENTER);

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
                URL musicUrl = Showcase.class.getResource(HOMEPAGE_MUSIC);
                if (musicUrl == null) {
                    System.err.println("File suara musik Showcase tidak ditemukan: " + HOMEPAGE_MUSIC);
                    System.err.println("Lokasi kelas Showcase: " + Showcase.class.getProtectionDomain().getCodeSource().getLocation());
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
                System.out.println("Showcase music started.");
            } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
                System.err.println("Terjadi kesalahan saat memutar musik Showcase: " + e.getMessage());
                e.printStackTrace();
            }
        });
        musicThread.start();
    }

    public void stopMusic() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.close();
            System.out.println("Showcase music stopped.");
        }
    }
}
