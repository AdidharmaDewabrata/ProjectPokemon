package panel;

import pokemon.Pokemon;
import pokemon.Move;
import pokemon.Type;
import panel.BattlePage; // Impor BattlePage untuk mengakses typeChart

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DetailPokemon extends JPanel {
    private String pokeName;
    private static final String TYPE_ICON_PATH = "C:\\Users\\asma\\IdeaProjects\\ProjectPokemon\\src\\assets\\Types\\";
    private static final String STAT_ICON_PATH = "C:\\Users\\asma\\IdeaProjects\\ProjectPokemon\\src\\assets\\Icons\\";
    private static final String ARROW_ICON_PATH = "C:\\Users\\asma\\IdeaProjects\\ProjectPokemon\\src\\assets\\";

    // ... (Deskripsi Pokémon dan POKEMON_NAMES_ORDER tetap sama) ...
    private static final String PIKACHU_DESC = "This bright yellow creature is a bundle of electrifying energy! With its iconic lightning tail and rosy cheek pouches that crackle with power, Pikachu is a quick and feisty ally. They're often seen scurrying through grassy plains, always ready to unleash a jolt and stand by their trainer's side. Catch one, and you'll have a truly shocking companion!";
    private static final String CHARIZARD_DESC = "A majestic fiery dragon that soars the skies. Its breath can melt boulders, and its wings can carry it higher than any mountain peak. Charizard is a powerful and loyal companion, fiercely protecting its trainer.";
    private static final String BLASTOISE_DESC = "A giant tortoise with powerful water cannons on its back. Blastoise can blast water with enough force to pierce thick steel. Its heavy shell provides excellent defense, making it a formidable opponent and steadfast ally.";
    private static final String VENUSAUR_DESC = "The final evolution of Bulbasaur, this plant-like creature carries a large flower on its back. The flower absorbs sunlight to generate energy, making Venusaur a potent force in battle, capable of unleashing powerful plant-based attacks.";
    private static final String INFERNAPE_DESC = "An agile Fire/Fighting type, Infernape is known for its blazing speed and powerful martial arts. The flames on its head and fists burn fiercely, reflecting its passionate and competitive spirit in battle.";
    private static final String SNORLAX_DESC = "Despite its massive size and love for napping, Snorlax possesses immense strength. It can devour almost anything and is incredibly resilient. When it finally wakes up, its power is truly astounding.";
    private static final String CLEFABLE_DESC = "A graceful Fairy Pokémon said to live in secluded, moonlit areas. Clefable is known for its gentle nature but can unleash powerful magical attacks. Its delicate wings allow it to float lightly.";
    private static final String MASQUERAIN_DESC = "This elegant Bug/Flying type can hypnotize foes with its eye-like patterns on its wings. Masquerain glides gracefully over water, using its delicate movements to confuse and disorient opponents.";
    private static final String MAROWAK_DESC = "A Ground-type Pokémon that wields a bone as a weapon. Marowak has a tough exterior, often seen wearing the skull of its mother. It's a fierce and protective fighter, known for its strong bond with its bone.";
    private static final String DRAGONITE_DESC = "A rare and kindhearted Dragon/Flying type, Dragonite is known for its compassionate nature. Despite its large size, it can fly at incredible speeds, circling the globe in just 16 hours. It often rescues people lost at sea.";
    private static final String GALVANTULA_DESC = "An Electric/Bug type spider that creates webs charged with electricity. Galvantula is a tricky opponent, trapping foes in its sticky, electrified threads before delivering a powerful shock.";
    private static final String GLACEON_DESC = "An Ice-type evolution of Eevee, Glaceon can instantly freeze the air around it, creating diamond dust. Its elegant appearance hides a powerful icy demeanor, capable of unleashing blizzards and ice shards.";

    private static final String[] POKEMON_NAMES_ORDER = { // Urutan ini harus sama dengan ChoosePlayer1.pokemon[]
            "Pikachu", "Charizard", "Blastoise", "Venusaur", "Infernape", "Snorlax",
            "Clefable", "Masquerain", "Marowak", "Dragonite", "Galvantula", "Glaceon"
    };

    private static final String[] DESCRIPTIONS = {
            PIKACHU_DESC, CHARIZARD_DESC, BLASTOISE_DESC, VENUSAUR_DESC, INFERNAPE_DESC, SNORLAX_DESC,
            CLEFABLE_DESC, MASQUERAIN_DESC, MAROWAK_DESC, DRAGONITE_DESC, GALVANTULA_DESC, GLACEON_DESC
    };

    // Struktur untuk menyimpan data Nature dari file
    // private static List<String[]> naturesData; // TIDAK DIGUNAKAN LAGI UNTUK TUJUAN INI

    // Konstan untuk ukuran dan posisi komponen
    private static final Dimension PANEL_SIZE = new Dimension(1280, 720);
    private static final int STATS_PANEL_WIDTH = 330;
    private static final int STATS_PANEL_HEIGHT = 400; // Tetap tinggi untuk menampung panah dan ikon
    private static final int MOVES_PANEL_WIDTH = 330;
    private static final int MOVES_PANEL_HEIGHT = 340;
    private static final int DESC_PANEL_WIDTH = 550;
    private static final int DESC_PANEL_HEIGHT = 150;
    private static final int POKEMON_ANIM_SIZE = 300;

    // Subkelas untuk panel dengan latar belakang gambar
    private static class BgPanel extends JPanel {
        private final Image background;

        public BgPanel(String imagePath) {
            this.background = new ImageIcon(imagePath).getImage();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
        }
    }

    // Metode bantuan untuk mendapatkan ikon dengan ukuran diskalakan
    private ImageIcon getIcon(String path, int width, int height) {
        ImageIcon icon = new ImageIcon(path);
        if (icon.getImageLoadStatus() == MediaTracker.COMPLETE) {
            Image scaledImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(scaledImage);
        } else {
            //System.err.println("Warning: Gagal memuat gambar di " + path);
            return new ImageIcon(new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB)); // Mengembalikan gambar kosong
        }
    }

    // Metode untuk membuat baris stat dengan ikon
    private JPanel createStatRow(String iconFileName, String valueText) {
        JPanel row = new JPanel();
        row.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        row.setOpaque(true);
        row.setBackground(Color.decode("#fcdc59"));

        JLabel iconLabel = new JLabel(getIcon(STAT_ICON_PATH + iconFileName, 64, 64));
        JLabel valueLabel = new JLabel(valueText);
        valueLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
        valueLabel.setForeground(Color.BLACK);

        row.add(iconLabel);
        row.add(valueLabel);
        return row;
    }

    // Metode untuk membuat baris move dengan ikon tipe
    private JPanel createMoveRow(Move move) {
        JPanel movePanel = new JPanel();
        movePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        movePanel.setOpaque(true);
        movePanel.setBackground(Color.decode("#c4cd8e"));

        JLabel typeIconLabel = new JLabel(getIcon(TYPE_ICON_PATH + move.getType().toString() + ".png", 64, 64));
        JLabel moveNameLabel = new JLabel(move.getName());
        moveNameLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
        moveNameLabel.setForeground(Color.BLACK);
        moveNameLabel.setAlignmentY(Component.CENTER_ALIGNMENT);
        typeIconLabel.setAlignmentY(Component.CENTER_ALIGNMENT);

        movePanel.add(typeIconLabel);
        movePanel.add(moveNameLabel);
        return movePanel;
    }

    // Konstruktor utama
    public DetailPokemon(CardLayout cardLayout, JPanel cardPanelContainer, String pokeName) {
        this.pokeName = pokeName;
        this.setLayout(new BorderLayout());

        String backgroundPath = "C:\\Users\\asma\\IdeaProjects\\ProjectPokemon\\src\\assets\\hutanlendir.jpg";
        BgPanel bgPanel = new BgPanel(backgroundPath);
        bgPanel.setLayout(null);
        bgPanel.setPreferredSize(PANEL_SIZE);

        // 1. Cari Pokémon yang sesuai nama dari array statis di ChoosePlayer1
        Pokemon selected = null;
        int pokemonIndex = -1;
        for (int i = 0; i < ChoosePlayer1.pokemon.length; i++) {
            Pokemon p = ChoosePlayer1.pokemon[i];
            if (p != null && p.getName().equalsIgnoreCase(pokeName)) {
                selected = p;
                pokemonIndex = i;
                break;
            }
        }

        // --- Pembacaan data Nature (pokemon_nature.txt) dihilangkan ---
        // Karena kita akan menampilkan kelemahan/ketahanan tipe dari typeChart

        // --- Panel Header (Nama Pokemon & Tipe Icon) ---
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
        headerPanel.setOpaque(false);
        headerPanel.setBounds(PANEL_SIZE.width / 2 - 200, 10, 400, 100);

        if (selected != null) {
            JLabel typeIconLabel = new JLabel(getIcon(TYPE_ICON_PATH + selected.getType().toString() + ".png", 100, 100));
            headerPanel.add(typeIconLabel);

            JLabel pokemonNameLabel = new JLabel(selected.getName());
            pokemonNameLabel.setFont(new Font("Tahoma", Font.BOLD, 40));
            pokemonNameLabel.setForeground(Color.decode("#fcdc59"));
            headerPanel.add(pokemonNameLabel);
        }
        bgPanel.add(headerPanel);

        // --- Panel Stats ---
        JPanel statsPanel = new JPanel();
        statsPanel.setLayout(new BoxLayout(statsPanel, BoxLayout.Y_AXIS));
        statsPanel.setBounds(50, 80, STATS_PANEL_WIDTH, STATS_PANEL_HEIGHT);
        statsPanel.setBackground(Color.black);
        statsPanel.setOpaque(true);

        JLabel statTitleLabel = new JLabel("Stats");
        statTitleLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
        statTitleLabel.setForeground(Color.decode("#fcdc59"));
        statTitleLabel.setBackground(Color.BLACK);
        statTitleLabel.setOpaque(true);
        statTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        statTitleLabel.setPreferredSize(new Dimension(STATS_PANEL_WIDTH, 40));
        statTitleLabel.setMaximumSize(new Dimension(STATS_PANEL_WIDTH, 40));
        statTitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        statsPanel.add(statTitleLabel);
        statsPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        if (selected != null) {
            statsPanel.add(createStatRow("heal.png", "Health: " + selected.getHealth()));
            statsPanel.add(createStatRow("sword.png", "Attack: " + selected.getAttack()));
            statsPanel.add(createStatRow("shield.png", "Defense: " + selected.getDefense()));
        } else {
            statsPanel.add(new JLabel("Pokémon tidak ditemukan"));
        }
        statsPanel.add(Box.createVerticalGlue());

        // --- Panel untuk Kelemahan/Ketahanan Tipe (sesuai gambar) ---
        JPanel typeEffectivenessPanel = new JPanel();
        typeEffectivenessPanel.setLayout(new BoxLayout(typeEffectivenessPanel, BoxLayout.Y_AXIS)); // Box untuk vertikal
        typeEffectivenessPanel.setOpaque(false);
        typeEffectivenessPanel.setAlignmentX(Component.CENTER_ALIGNMENT); // Pusatkan di BoxLayout utama statsPanel

        if (selected != null) {
            Type defendType = selected.getType();
            // Mendapatkan semua tipe enum untuk iterasi
            Type[] allTypes = Type.values();
            List<Type> superEffective = new ArrayList<>();
            List<Type> notVeryEffective = new ArrayList<>();
            List<Type> immune = new ArrayList<>();

            // Iterasi melalui semua tipe untuk menemukan kelemahan/ketahanan
            for (Type attackType : allTypes) {
                // Gunakan getTypeEffectiveness dari BattlePage
                double effectiveness = BattlePage.getTypeEffectiveness(attackType, defendType);

                if (effectiveness > 1.0 && effectiveness != 1.5) { // Super Effective (lebih dari 1.5x)
                    superEffective.add(attackType);
                } else if (effectiveness < 1.0 && effectiveness != 0.0 && effectiveness != 0.5) { // Not Very Effective (kurang dari 1x, bukan 0.5)
                    notVeryEffective.add(attackType);
                } else if (effectiveness == 0.0) { // Immune (0x damage)
                    immune.add(attackType);
                }
                // Jika ingin memasukkan 0.5x, 1.5x secara eksplisit, bisa tambahkan kondisi lain
                else if (effectiveness == 0.5) { // 0.5x damage
                    notVeryEffective.add(attackType);
                }
            }

            // --- Panah Atas (Biasanya untuk ketahanan) ---
            JPanel arrowUpPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
            arrowUpPanel.setOpaque(false);
            arrowUpPanel.add(new JLabel(getIcon(ARROW_ICON_PATH + "up.png", 46,46))); // Panah atas
            // Tambahkan ikon tipe yang tidak efektif
            for (Type type : notVeryEffective) {
                arrowUpPanel.add(new JLabel(getIcon(TYPE_ICON_PATH + type.toString() + ".png", 64, 64)));
            }
            typeEffectivenessPanel.add(arrowUpPanel);


            // --- Panah Bawah (Biasanya untuk kelemahan) ---
            JPanel arrowDownPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
            arrowDownPanel.setOpaque(false);
            arrowDownPanel.add(new JLabel(getIcon(ARROW_ICON_PATH + "down.png", 46, 46))); // Panah bawah
            // Tambahkan ikon tipe yang super efektif
            for (Type type : superEffective) {
                arrowDownPanel.add(new JLabel(getIcon(TYPE_ICON_PATH + type.toString() + ".png", 64,64)));
            }
//            // Tambahkan ikon tipe yang immune (jika ada, bisa juga di panel terpisah)
//            for (Type type : immune) {
//                arrowDownPanel.add(new JLabel(getIcon(TYPE_ICON_PATH + type.toString() + ".png", 46,46)));
//            }
            typeEffectivenessPanel.add(arrowDownPanel);
//
//            // Tambahkan juga panel untuk Immune (jika ingin lebih jelas)
//            if (!immune.isEmpty()) {
//                JPanel immunePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
//                immunePanel.setOpaque(false);
//                immunePanel.add(new JLabel("Immune: ")); // Atau ikon khusus
//                for (Type type : immune) {
//                    immunePanel.add(new JLabel(getIcon(TYPE_ICON_PATH + type.toString() + ".png", 32, 32)));
//                }
//                typeEffectivenessPanel.add(immunePanel);
//            }

        } else {
            typeEffectivenessPanel.add(new JLabel("Efektivitas Tipe: N/A"));
        }
        statsPanel.add(typeEffectivenessPanel);


        bgPanel.add(statsPanel);

        // --- Panel Animasi Pokémon ---
        JPanel pokemonAnimPanel = new JPanel();
        pokemonAnimPanel.setLayout(new BorderLayout());
        pokemonAnimPanel.setOpaque(false);
        pokemonAnimPanel.setPreferredSize(new Dimension(POKEMON_ANIM_SIZE, POKEMON_ANIM_SIZE));

        JLabel animatedPokemonLabel = new JLabel();
        new Animation(animatedPokemonLabel, pokeName, "front", POKEMON_ANIM_SIZE, POKEMON_ANIM_SIZE).start();
        animatedPokemonLabel.setHorizontalAlignment(SwingConstants.CENTER);
        animatedPokemonLabel.setVerticalAlignment(SwingConstants.CENTER);
        pokemonAnimPanel.add(animatedPokemonLabel, BorderLayout.CENTER);

        int xAnim = (PANEL_SIZE.width / 2) - (POKEMON_ANIM_SIZE / 2);
        int yAnim = (PANEL_SIZE.height / 2) - (POKEMON_ANIM_SIZE / 2) - 50;
        pokemonAnimPanel.setBounds(xAnim, yAnim, POKEMON_ANIM_SIZE, POKEMON_ANIM_SIZE);
        bgPanel.add(pokemonAnimPanel);

        // --- Panel Moves ---
        JPanel movesPanel = new JPanel();
        movesPanel.setLayout(new BoxLayout(movesPanel, BoxLayout.Y_AXIS));
        movesPanel.setBounds(PANEL_SIZE.width - MOVES_PANEL_WIDTH - 50, 80, MOVES_PANEL_WIDTH, MOVES_PANEL_HEIGHT);
        movesPanel.setBackground(Color.BLACK);
        movesPanel.setOpaque(true);

        JLabel movesTitleLabel = new JLabel("Moves");
        movesTitleLabel.setFont(new Font("Tahoma", Font.BOLD, 32));
        movesTitleLabel.setForeground(Color.decode("#fcdc59"));
        movesTitleLabel.setBackground(Color.BLACK);
        movesTitleLabel.setOpaque(true);
        movesTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        movesTitleLabel.setPreferredSize(new Dimension(MOVES_PANEL_WIDTH, 40));
        movesTitleLabel.setMaximumSize(new Dimension(MOVES_PANEL_WIDTH, 40));
        movesTitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        movesPanel.add(movesTitleLabel);
        movesPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        if (selected != null) {
            for (Move move : selected.getMoves()) {
                movesPanel.add(createMoveRow(move));
                movesPanel.add(Box.createRigidArea(new Dimension(0, 5)));
            }
        }
        movesPanel.add(Box.createVerticalGlue());


        bgPanel.add(movesPanel);

        // --- Panel Deskripsi ---
        JPanel descPanel = new JPanel();
        descPanel.setLayout(new BorderLayout());
        int xDesc = (PANEL_SIZE.width / 2) - (DESC_PANEL_WIDTH / 2);
        int yDesc = pokemonAnimPanel.getY() + pokemonAnimPanel.getHeight() + 50;
        descPanel.setBounds(xDesc, yDesc, DESC_PANEL_WIDTH, DESC_PANEL_HEIGHT);
        descPanel.setBackground(new Color(0, 0, 0, 150));
        descPanel.setOpaque(true);

        JTextArea descriptionArea = new JTextArea();
        descriptionArea.setEditable(false);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setFont(new Font("Tahoma", Font.PLAIN, 16));
        descriptionArea.setForeground(Color.WHITE);
        descriptionArea.setOpaque(false);

        String pokemonDescription = "Tidak ada deskripsi tersedia.";
        if (pokemonIndex != -1 && pokemonIndex < DESCRIPTIONS.length) {
            pokemonDescription = DESCRIPTIONS[pokemonIndex];
        }
        descriptionArea.setText(pokemonDescription);

        JScrollPane scrollPane = new JScrollPane(descriptionArea);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        descPanel.add(scrollPane, BorderLayout.CENTER);

        bgPanel.add(descPanel);


        // --- Tombol Back ---
        JButton backButton = new JButton("Back");
        backButton.setBounds(PANEL_SIZE.width - 250 - 50, PANEL_SIZE.height - 75 - 50, 250, 75);
        backButton.setBackground(Color.decode("#fcdc59"));
        backButton.setFont(new Font("Tahoma", Font.BOLD, 40));
        backButton.addActionListener(e -> {
            cardLayout.show(cardPanelContainer, "panel.Showcase");
        });

        bgPanel.add(backButton);

        this.add(bgPanel, BorderLayout.CENTER);
    }
}