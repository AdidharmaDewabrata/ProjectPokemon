package panel;
import pokemon.Pokemon;

import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.sound.sampled.*;
import java.util.List;
import java.util.Scanner;

public class DetailPokemon extends JPanel {
    private Image backgroundImage;
    //sound tergantung nanti dah
    //public static final String HOMEPAGE_MUSIC = "homepage.wav";
    private static Clip clip;
    private String pokemonName;

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


    private JPanel createStatRow(String valueText) {
        JPanel row = new JPanel();
        row.setLayout(new FlowLayout(FlowLayout.LEFT));
        row.setBackground(Color.decode("#fcdc59"));

        //JLabel iconLabel = new JLabel(new ImageIcon("C:\\Users\\asma\\IdeaProjects\\ProjectPokemon\\src\\assets\\" + iconFileName));
        JLabel valueLabel = new JLabel(valueText);
        valueLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        valueLabel.setForeground(Color.BLACK);

        //row.add(iconLabel);
        //row.add(Box.createRigidArea(new Dimension(10, 0))); // Spasi antar icon & teks
        row.add(valueLabel);

        return row;
    }


    public DetailPokemon(CardLayout cardLayout, JPanel cardPanelContainer, String pokemonName) {
        Scanner sc;
        this.pokemonName = pokemonName;
//        String s,w, c;
//        int hp,att,def;
        this.setLayout(new BorderLayout());

        // Pastikan path ini valid di sistem
        String backgroundPath = "C:\\Users\\asma\\IdeaProjects\\ProjectPokemon\\src\\assets\\hutanlendir.jpg";
        DetailPokemon.BgPanel bgPanel = new DetailPokemon.BgPanel(backgroundPath);//manggil kelas yang di atas tadi
        bgPanel.setLayout(null);
        bgPanel.setPreferredSize(new Dimension(1280, 720)); // Ukuran disesuaikan frame

        JPanel stats = new JPanel();
        stats.setLayout(new BoxLayout(stats, BoxLayout.Y_AXIS));
        stats.setBounds(10, 60 , 330, 330);
        stats.setBackground(Color.decode("#fcdc59"));

        // 1. Baca semua data dari file
        List<Pokemon> pokemonList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("pokemonchara.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length >= 5) {
                    String name = parts[0];
                    String type = parts[1];
                    int hp = Integer.parseInt(parts[2]);
                    int attack = Integer.parseInt(parts[3]);
                    int defense = Integer.parseInt(parts[4]);

                    Pokemon p = new Pokemon(name, type, hp, attack, defense);
                    pokemonList.add(p);
                }
            }
        } catch (IOException e) {
            System.err.println("Gagal baca file: " + e.getMessage());
        }

        // 2. Cari Pokémon yang sesuai nama
        Pokemon selected = null;
        for (Pokemon p : pokemonList) {
            if (p.getName().equalsIgnoreCase(pokemonName)) {
                selected = p;
                break;
            }
        }
        JTextField statTitle = new JTextField("Stats");
        statTitle.setFont(new Font("Tahoma", Font.BOLD, 24));
        statTitle.setForeground(Color.decode("#fcdc59"));
        statTitle.setBackground(Color.BLACK);
        statTitle.setHorizontalAlignment(JTextField.CENTER);
        statTitle.setEditable(false); // Supaya user gak bisa edit
        statTitle.setBorder(null);    // Opsional: hilangkan border
        statTitle.setPreferredSize(new Dimension(400, 40)); // Atur ukuran kotaknya
        statTitle.setMaximumSize(new Dimension(400, 40));


        // 3. Tampilkan stat-nya kalau ketemu
        if (selected != null) {
            stats.add(statTitle);
            stats.add(createStatRow("Health: "+ String.valueOf(selected.getHealth())));
            stats.add(createStatRow("Attack: " + String.valueOf(selected.getAttack())));
            stats.add(createStatRow("Defense: " + String.valueOf(selected.getDefense())));
        } else {
            stats.add(new JLabel("Pokémon tidak ditemukan"));
        }

        bgPanel.add(stats);

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
        new Animation(blastoise, "blastoise", "front", 100, 100).start();
        new Animation(charizard, "charizard", "front", 100, 100).start();
        new Animation(clefable, "clefable", "front", 100, 100).start();
        new Animation(dragonite, "dragonite", "front", 100, 100).start();
        new Animation(galvantula, "galvantula", "front", 100, 100).start();
        new Animation(glaceon, "glaceon", "front", 100, 100).start();
        new Animation(infernape, "infernape", "front", 100, 100).start();
        new Animation(marowak, "marowak", "front", 100, 100).start();
        new Animation(masquerain, "masquerain", "front", 100, 100).start();
        new Animation(pikachu, "pikachu", "front", 100, 100).start();
        new Animation(snorlax, "snorlax", "front", 100, 100).start();
        new Animation(venusaur, "venusaur", "front", 100, 100).start();

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

        JPanel base = new JPanel();
        base.setOpaque(true);
        base.setLayout(new BorderLayout());
        base.setPreferredSize(new Dimension(300, 300));
        base.setBackground(Color.decode("#c4cd8e"));
        String sp = pokemonName;
//        JPanel chara = new JPanel();
//        chara.setOpaque(true);

        int idx = pokeNames.indexOf(sp);
        if (idx != -1) {
            JLabel selectedPokemonLabel = pokeLabels.get(idx);
            base.add(selectedPokemonLabel, BorderLayout.CENTER);
        }

        JTextField names = new JTextField(sp);
        names.setFont(new Font("Tahoma", Font.BOLD, 24));
        names.setForeground(Color.decode("#fcdc59"));
        names.setOpaque(false);
        names.setBorder(null);
        names.setHorizontalAlignment(JTextField.CENTER);
        base.add(names, BorderLayout.NORTH);

        int panelWidth = 300; // Sesuaikan dengan ukuran animasi
        int panelHeight = 300;
        int xPos = (bgPanel.getPreferredSize().width / 2) - (panelWidth / 2); // Pusatkan secara horizontal
        int yPos = (bgPanel.getPreferredSize().height / 2) - (panelHeight / 2) + 50; //
        base.setBounds(xPos, yPos, panelWidth, panelHeight);

//        JPanel chara = pokeNames.contains(sp) ? new JPanel() : new JPanel();
//        chara.setOpaque(true);
//        base.setComponentZOrder(chara, 0);
//        base.add(chara);
        bgPanel.add(base);



        JButton back = new JButton("Back");
        back.setBounds(1000, 550, 250, 75);
        back.setBackground(Color.decode("#fcdc59"));
        back.setFont(new Font("Tahoma", Font.BOLD, 40));
        back.addActionListener(e -> {
            cardLayout.show(cardPanelContainer, "panel.Showcase");
        });

        bgPanel.add(back);

        this.add(bgPanel, BorderLayout.CENTER);
    }

}
