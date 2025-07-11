package panel;

import pokemon.Pokemon;
import pokemon.Move;
import javax.swing.*;
import java.awt.*;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.net.URL;
import java.util.Scanner;
import javax.sound.sampled.*;

public class ChoosePlayer1 extends JPanel {
    private Image backgroundImage;
    public static final String CHOOSEPLAYER_MUSIC = "choosePlayer.wav";
    private static Clip clip;
    static Pokemon[] pokemon = new Pokemon[12];
    private int[] p1pick = new int[2];
    private Image back;
    private JLabel[] pokemonImage = new JLabel[12], pokemonName = new JLabel[12];
    private int j = 0, p = 5; // Hapus titik koma ganda di sini, bukan error tapi good practice
    static boolean flag = true;

    // --- PERBAIKAN UTAMA DI SINI ---
    // Ubah dataMove menjadi static dan sesuaikan dimensinya untuk 3 moves per Pokemon
    private static String[][][] dataMove = new String[12][3][4]; // 12 Pokemon, 3 moves per Pokemon, 4 detail per move

    // Getter statis untuk dataMove
    public static String[][][] getDataMoves() {
        return dataMove;
    }

    // Konstruktor default yang tidak digunakan untuk inisialisasi data.
    // Jika tidak ada kode yang memanggil ChoosePlayer1() tanpa argumen,
    // sebaiknya dihapus atau dijadikan private untuk mencegah salah penggunaan.
    public ChoosePlayer1() {
        // Untuk memastikan dataMove diisi HANYA SEKALI,
        // kita bisa menambahkan pengecekan di sini, atau lebih baik pastikan
        // konstruktor ChoosePlayer1(CardLayout, JPanel) adalah satu-satunya jalur inisialisasi.
        // Untuk saat ini, asumsikan konstruktor utama akan dipanggil pertama kali.
    }

    public ChoosePlayer1(CardLayout cardLayout, JPanel cardPanelContainer) {
        Scanner sc;
        this.setLayout(null);
        back = new ImageIcon("F:\\dv\\college\\code\\Intellij\\Pemlan\\ProjectPokemon\\src\\assets\\hutanlendir.jpg").getImage();

        //Player 1 label
        JLabel player1 = new JLabel("Player 1");
        player1.setOpaque(false);
        player1.setBackground(new Color(0,0,0,0));
        player1.setForeground(Color.decode("#fcdc59"));
        player1.setBorder(null);
        player1.setFont(new Font("Times New Roman", Font.BOLD, 70));
        player1.setBounds(640,-30, 540,200);
        add(player1);

        //arrow kanan
        JLabel right = new JLabel(new ImageIcon("C:\\Users\\asma\\IdeaProjects\\ProjectPokemon\\src\\assets\\ArRight.png "));
        right.setBounds(940,300,100,100);
        add(right);

        //arrow kiri
        JLabel left = new JLabel(new ImageIcon("C:\\Users\\asma\\IdeaProjects\\ProjectPokemon\\src\\assets\\Arleft.png"));
        left.setBounds(550,300,100,100);
        add(left);

        //baca data dari pokemonchara.txt
        File file = new File("pokemonchara.txt");
        String[][] dataPokemon = new String[12][6];

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int o = 0;
            while ((line = br.readLine()) != null && o < 12) {
                sc = new Scanner(line);
                int u = 0;
                while (sc.hasNext() && u < 6) {
                    dataPokemon[o][u] = sc.next();
                    u++;
                }
                o++;
            }
        } catch (IOException e) {
            System.err.println(e);
        }

        for (int i = 0; i < 12; i++) {
            String s = dataPokemon[i][0];
            String w = dataPokemon[i][1];
            int hp = Integer.parseInt(dataPokemon[i][2]);
            int att = Integer.parseInt(dataPokemon[i][3]);
            int def = Integer.parseInt(dataPokemon[i][4]);
            String c = dataPokemon[i][5];

            pokemon[i] = new Pokemon(s, w, hp, att, def, c);
        }

        // --- Perbaikan di sini: Pastikan dataMove hanya diisi sekali ---
        // Jika dataMove sudah terisi (misalnya dari instansiasi sebelumnya),
        // tidak perlu membaca file lagi.
        if (dataMove[0][0][0] == null) { // Cek apakah elemen pertama null, menandakan belum terisi
            File file2 = new File("moves.txt");
            try (BufferedReader br = new BufferedReader(new FileReader(file2))) {
                String line;
                int pokemonIdx = 0; // Index untuk Pokémon saat ini
                int moveIdx = 0;    // Index untuk move dalam Pokémon saat ini (0-2, karena 3 move)

                while ((line = br.readLine()) != null && pokemonIdx < 12) {
                    sc = new Scanner(line);
                    int detailIdx = 0; // Index untuk detail move (power type, name, type, power value)
                    while (sc.hasNext() && detailIdx < 4) { // Masih mengharapkan 4 detail per baris
                        dataMove[pokemonIdx][moveIdx][detailIdx] = sc.next();
                        detailIdx++;
                    }
                    moveIdx++; // Pindah ke move berikutnya untuk Pokémon saat ini
                    // Jika sudah membaca 3 move (indeks 0, 1, 2)
                    if (moveIdx == 3) {
                        pokemonIdx++; // Pindah ke Pokémon berikutnya
                        moveIdx = 0;  // Reset moveIdx untuk Pokémon berikutnya
                    }
                }
            } catch (IOException e) {
                System.err.println("Error reading moves.txt: " + e.getMessage());
            }
        }


        // Loop untuk menambahkan move ke setiap objek Pokemon
        for (int i = 0; i < 12; i++) { // Iterasi untuk setiap Pokemon
            // Pastikan Pokemon ini belum memiliki move yang ditambahkan sebelumnya
            // Ini penting jika ChoosePlayer1 bisa dibuat ulang.
            if (pokemon[i].getMoves().isEmpty()) { // Cek apakah daftar moves kosong
                for (int k = 0; k < 3; k++) { // Iterasi untuk 3 move setiap Pokemon
                    // Hati-hati dengan NPE di sini jika dataMove tidak terisi dengan benar
                    String powerType = dataMove[i][k][0];
                    String moveName = dataMove[i][k][1];
                    String moveType = dataMove[i][k][2];
                    int movePower = Integer.parseInt(dataMove[i][k][3]);

                    pokemon[i].addMove(new Move(powerType, moveName, moveType, movePower));
                }
            }
        }

        //ganti pokemon
        String[] pokemonlist = {"Pikachu","Charizard","Blastoise","Venusaur","Infernape","Snorlax","Clefable","Masquerain","Marowak","Dragonite","Galvantula","Glaceon"};
        for(int i = 0; i < pokemonlist.length; i++){
            pokemonImage[i] = new JLabel();
            pokemonImage[i].setVisible(false);
            new Animation(pokemonImage[i],pokemonlist[i],"front",300,300).start();
            pokemonImage[i].setBounds(640,200,300,300);
            add(pokemonImage[i]);
        }
        JLabel[] labelz = new JLabel[12];
        JLabel[] labelImage = new JLabel[12];
        JLabel[] labelStatContainer = new JLabel[12];
        JLabel[] health = new JLabel[12];
        JLabel[] attack = new JLabel[12];
        JLabel[] defend = new JLabel[12];
        //ganti nama pokemon
        for(int i = 0; i < pokemonName.length; i++){
            // Container label
            labelz[i] = new JLabel();
            labelz[i].setLayout(null);
            labelz[i].setBounds(500, 500, 600, 200);
            labelz[i].setOpaque(false); // optional: set true with color for debugging
            labelz[i].setVisible(false); // initially hidden
            add(labelz[i]);

            // Type image label
            labelImage[i] = new JLabel(pokemon[i].getTypeImage(325,175));
            labelImage[i].setBounds(-80, -20, 325, 175);
            setComponentZOrder(labelImage[i],0);// inside container
            labelz[i].add(labelImage[i]);

            // Name label
            pokemonName[i] = new JLabel(pokemon[i].getName());
            pokemonName[i].setForeground(Color.decode("#f7f7f0"));
            pokemonName[i].setFont(new Font("Times New Roman", Font.BOLD, 70));
            pokemonName[i].setBounds(160, 20, 400, 100); // relative to container
            labelz[i].add(pokemonName[i]);
            add(labelz[i]);
        }
        //ganti stats pokemon
        for(int i = 0; i < 12; i++){
            labelStatContainer[i] = new JLabel();
            labelStatContainer[i].setLayout(null);
            labelStatContainer[i].setOpaque(false);
            labelStatContainer[i].setVisible(false);
            labelStatContainer[i].setBounds(500, 575, 600, 200);
            setComponentZOrder(labelStatContainer[i],0);
            add(labelStatContainer[i]);

            health[i] = new JLabel("HP: "+pokemon[i].getHealth());
            health[i].setFont(new Font("Times New Roman", Font.BOLD, 40));
            health[i].setForeground(Color.decode("#fcdc59"));
            health[i].setBounds(0,0,150,200);
            labelStatContainer[i].add(health[i]);

            attack[i] = new JLabel("Att: "+pokemon[i].getAttack());
            attack[i].setFont(new Font("Times New Roman", Font.BOLD, 40));
            attack[i].setForeground(Color.decode("#fcdc59"));
            attack[i].setBounds(200,0,150,200);
            labelStatContainer[i].add(attack[i]);

            defend[i] = new JLabel("Def: "+pokemon[i].getDefense());
            defend[i].setFont(new Font("Times New Roman", Font.BOLD, 40));
            defend[i].setForeground(Color.decode("#fcdc59"));
            defend[i].setBounds(400,0,150,200);
            labelStatContainer[i].add(defend[i]);
        }

        pokemonImage[0].setVisible(true);
        labelz[0].setVisible(true);
        labelStatContainer[0].setVisible(true);
        setComponentZOrder(pokemonImage[0],0);

        right.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(j<11) {
                    j++;
                    pokemonImage[j - 1].setVisible(false);
                    pokemonImage[j].setVisible(true);
                    labelz[j - 1].setVisible(false);
                    labelz[j].setVisible(true);
                    labelStatContainer[j - 1].setVisible(false);
                    labelStatContainer[j].setVisible(true);
                }
            }
        });

        left.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(j>0) {
                    j--;
                    pokemonImage[j + 1].setVisible(false);
                    pokemonImage[j].setVisible(true);
                    labelz[j + 1].setVisible(false);
                    labelz[j].setVisible(true);
                    labelStatContainer[j + 1].setVisible(false);
                    labelStatContainer[j].setVisible(true);
                }
            }
        });

        JButton next = new JButton("Next");
        next.setFont(new Font("Times New Roman", Font.BOLD, 40));
        next.setForeground(Color.decode("#000000"));
        next.setBackground(Color.decode("#c4cd8e"));
        next.setBounds(650, 750, 250, 75);
        next.setVisible(true);
        setComponentZOrder(next,0);
        add(next);

        JPanel show = new JPanel();
        show.setLayout(null);
        show.setBackground(Color.decode("#d9d9d9"));
        show.setBounds(0,0, 425,250);
        show.setVisible(false);
        add(show);

        JLabel p1 = new JLabel();
        JLabel p1name = new JLabel();
        p1name.setFont(new Font("Times New Roman", Font.BOLD, 35));
        p1name.setBounds(240,15,250,100);
        p1name.setVisible(false);
        show.add(p1name);
        JLabel[] p1stats = new JLabel[3];
        JLabel p1PName = new JLabel();
        p1PName.setLayout(null);
        p1PName.setBounds(-10,127,400,300);
        p1PName.setVisible(false);
        show.add(p1PName);

        next.addActionListener(e -> {
            if(flag) {
                p1pick[0] = j;
                next.setText("Confirm");
                pokemonImage[j].setVisible(false);
                labelz[j].setVisible(false);
                labelStatContainer[j].setVisible(false);
                player1.setText("Player 2");
                p1name.setText(pokemon[j].getName());
                new Animation(p1, pokemonlist[j], "front", 150, 150).start();
                p1.setBounds(255, 80, 150, 150);
                show.setBackground(Color.decode("#c8e3b8"));
                show.add(p1);
                //show.setBackground(Color.decode(pokemon[j].getColor()));
                p1stats[0] = new JLabel("HP: "+pokemon[j].getHealth());
                p1stats[1] = new JLabel("ATT: "+pokemon[j].getAttack());
                p1stats[2] = new JLabel("DEF: "+pokemon[j].getDefense());

                JLabel name = new JLabel(pokemon[j].getName());
                name.setBounds(100, 0, 300,150);
                name.setFont(new Font("Times New Roman", Font.BOLD, 30));
                p1PName.add(name);

                JLabel imej = new JLabel(pokemon[j].getTypeImage(150,120));
                imej.setBounds(-30, 0, 180,150);
                p1PName.add(imej);

                p1PName.setVisible(true);
                for(int k = 0; k < 3; k++) {
                    p1stats[k].setBounds(85,p,140,120);
                    p1stats[k].setFont(new Font("Times New Roman", Font.BOLD, 25));
                    p1stats[k].setForeground(Color.BLACK);
                    p1stats[k].setVisible(true);
                    p+=40;
                    show.add(p1stats[k]);
                }
                p1name.setVisible(true);
                show.setVisible(true);
                j = 0;
                pokemonImage[j].setVisible(true);
                labelz[j].setVisible(true);
                labelStatContainer[j].setVisible(true);
                flag = false;
            }
            else{
                p1pick[1] = j;
                PreBattle preBattle = new PreBattle(cardLayout, cardPanelContainer, p1pick[0],p1pick[1]);
                System.out.println("ini p1: "+p1pick[0]);
                System.out.println("ini p2: "+p1pick[1]);
                cardPanelContainer.add(preBattle,"panel.PreBattle");
                cardLayout.show(cardPanelContainer, "panel.PreBattle");
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
                    System.err.println("File suara musik chooseplayer tidak ditemukan: " + CHOOSEPLAYER_MUSIC);
                    System.err.println("Lokasi kelas chooseplayer : " + HomePage.class.getProtectionDomain().getCodeSource().getLocation());
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
                System.out.println("chooseplayer music started.");
            } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
                System.err.println("Terjadi kesalahan saat memutar musik chooseplayer : " + e.getMessage());
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