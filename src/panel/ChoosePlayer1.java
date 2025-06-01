package panel;

import pokemon.Pokemon;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import javax.sound.sampled.*;

public class ChoosePlayer1 extends JPanel {
    private Image back;
    public static final String CHOOSEPLAYER_MUSIC = "choose_player.wav";
    private static Clip clip;

    private JLabel pokemonNameLabel;
    private JLabel hpLabel;
    private JLabel attackLabel;
    private JLabel defenseLabel;
    private JLabel displayPokemon;

    private ArrayList<Pokemon> availPokemon;
    private int currentPokemon;
    private Animation currentAnimation;

    public ChoosePlayer1(CardLayout cardLayout, JPanel cardPanelContainer) {
        this.setLayout(null);
        back = new ImageIcon("F:\\dv\\college\\code\\Intellij\\Pemlan\\ProjectPokemonGUI\\src\\assets\\hutanlendir.jpg").getImage();

        //Player 1 label
        JLabel player1 = new JLabel("Player 1");
        player1.setOpaque(false);
        player1.setBackground(new Color(0,0,0,0));
        player1.setForeground(Color.decode("#fcdc59"));
        player1.setBorder(null);
        player1.setFont(new Font("Source Sans Pro", Font.BOLD, 60));
        player1.setBounds(500,-30, 540,200);
        add(player1);

        // Label untuk Nama Pokémon
        pokemonNameLabel = new JLabel("Nama Pokemon");
        pokemonNameLabel.setBounds(300, 50, 400, 50);
        pokemonNameLabel.setFont(new Font("Source Sans Pro", Font.BOLD, 40));
        pokemonNameLabel.setForeground(Color.decode("#f7f7f0"));
        pokemonNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(pokemonNameLabel);

        // Label untuk Stats
        hpLabel = new JLabel("HP: 0");
        attackLabel = new JLabel("ATT: 0");
        defenseLabel = new JLabel("DEF: 0");

        hpLabel.setBounds(300, 150, 200, 30); // Sesuaikan posisi dan ukuran
        attackLabel.setBounds(300, 190, 200, 30);
        defenseLabel.setBounds(300, 230, 200, 30);

        hpLabel.setFont(new Font("Source Sans Pro", Font.PLAIN, 24));
        attackLabel.setFont(new Font("Source Sans Pro", Font.PLAIN, 24));
        defenseLabel.setFont(new Font("Source Sans Pro", Font.PLAIN, 24));

        hpLabel.setForeground(Color.WHITE);
        attackLabel.setForeground(Color.WHITE);
        defenseLabel.setForeground(Color.WHITE);

        add(hpLabel);
        add(attackLabel);
        add(defenseLabel);

        // JLabel untuk menampilkan animasi Pokémon
        displayPokemon = new JLabel();
        displayPokemon.setBounds(350, 300, 300, 300); // Sesuaikan ukuran area display Pokémon
        displayPokemon.setHorizontalAlignment(SwingConstants.CENTER);
        displayPokemon.setVerticalAlignment(SwingConstants.CENTER);
        add(displayPokemon);


        //arrow kanan
        URL right = getClass().getResource("F:\\dv\\college\\code\\Intellij\\Pemlan\\ProjectPokemonGUI\\src\\assets\\ArRight.png");
        if(right!=null){
            JLabel rightAr = new JLabel(new ImageIcon(right));
            rightAr.setBounds(750,200,200,200);
            add(rightAr);
            rightAr.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    showNextPokemon();
                }
            });
        } else{
            System.err.println("Image not found");
        }

        //arrow kiri
        URL left = getClass().getResource("F:\\dv\\college\\code\\Intellij\\Pemlan\\ProjectPokemonGUI\\src\\assets\\ArLeft.png");
        if(left!=null){
            JLabel leftAr = new JLabel(new ImageIcon(left));
            leftAr.setBounds(750,200,200,200);
            add(leftAr);
            leftAr.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    showPreviousPokemon();
                }
            });
        } else{
            System.err.println("Image not found");
        }

//        setComponentZOrder(right, 0);
//        setComponentZOrder(left, 0);

        // Tombol Confirm
        JButton confirm = new JButton("Confirm");
        confirm.setFont(new Font("Source Sans Pro", Font.BOLD, 30));
        confirm.setForeground(Color.BLACK);
        confirm.setBackground(Color.decode("#93cd8e"));
        confirm.setBounds(550,550,150,50);
        add(confirm);

        //daftar pokemon
        availPokemon = new ArrayList<>();
        availPokemon.add(new Pokemon("Blastoise", 100, 30, 50, "blastoise", "front"));
        availPokemon.add(new Pokemon("Charmander", 90, 40, 45, "charmander", "front"));
        availPokemon.add(new Pokemon("Squirtle", 110, 25, 55, "squirtle", "front"));
        availPokemon.add(new Pokemon("Pikachu", 80, 50, 30, "pikachu", "front"));
        availPokemon.add(new Pokemon("Snorlax", 150, 20, 70, "snorlax", "front"));
        availPokemon.add(new Pokemon("Venusaur", 110, 35, 60, "venusaur", "front"));


        // Tampilkan Pokémon pertama kali saat panel diinisialisasi
        displayPokemon(currentPokemon);

        //Kontrol musik otomatis
        addHierarchyListener(new HierarchyListener() {
            @Override
            public void hierarchyChanged(HierarchyEvent e) {
                if ((e.getChangeFlags() & HierarchyEvent.SHOWING_CHANGED) != 0) {
                    if (isShowing()) {
                        startMusic();
                        startCurrentPokemonAnimation();
                    } else {
                        stopMusic();
                        stopCurrentPokemonAnimation();
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

    private void displayPokemon(int index) {
        if (index >= 0 && index < availPokemon.size()) {
            Pokemon pokemon = availPokemon.get(index);

            // Perbarui teks label
            pokemonNameLabel.setText(pokemon.getName());
            hpLabel.setText("HP: " + pokemon.getHp());
            attackLabel.setText("ATT: " + pokemon.getAttack());
            defenseLabel.setText("DEF: " + pokemon.getDefense());

            stopCurrentPokemonAnimation();
            currentAnimation = new Animation(
                    displayPokemon,
                    pokemon.getAnimationIdentifier(),
                    pokemon.getAnimationState(),
                    displayPokemon.getWidth(),
                    displayPokemon.getHeight()
            );
            currentAnimation.start();

            System.out.println("Displaying Pokemon: " + pokemon.getName() + " (Index: " + index + ")");
        }
    }

    private void showNextPokemon() {
        currentPokemon++;
        if (currentPokemon >= availPokemon.size()) {
            currentPokemon = 0; // Kembali ke Pokémon pertama
        }
        displayPokemon(currentPokemon);
    }

    private void showPreviousPokemon() {
        currentPokemon--;
        if (currentPokemon < 0) {
            currentPokemon = availPokemon.size() - 1; // Kembali ke Pokémon terakhir
        }
        displayPokemon(currentPokemon);
    }

    private void startCurrentPokemonAnimation() {
        if (currentAnimation != null) {
            currentAnimation.start();
        } else {
            displayPokemon(currentPokemon);
        }
    }

    private void stopCurrentPokemonAnimation() {
        if (currentAnimation != null) {
            currentAnimation.stop();
            System.out.println("Animation for current Pokemon stopped.");
        }
    }

}






