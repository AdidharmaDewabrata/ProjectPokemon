package panel;
import pokemon.Move;
import pokemon.Pokemon;
import pokemon.Type;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BattlePage extends JPanel {
    private Image background; private boolean dead = false;
    private boolean p1Turn = true;
    private Move[] moveP1 = new Move[3]; // PERBAIKAN: Hanya 3 moves, bukan 4
    private Move[] moveP2 = new Move[3]; // PERBAIKAN: Hanya 3 moves, bukan 4
    private JProgressBar[] progressBars = new JProgressBar[2];
    // PERBAIKAN: Dimensi kedua menjadi 3 (untuk 3 moves per Pokemon)
    private String[][][] gerakan = new String[2][3][4];

    public BattlePage(CardLayout cardLayout, JPanel cardPanelContainer, int p1, int p2) {
        background = new ImageIcon("C:\\Users\\adksp\\IdeaProjects\\ProjectPokemon\\src\\panel\\battleForest.jpg").getImage();
        setLayout(null);

        JPanel greenBox = new JPanel(null);
        greenBox.setBounds(0, 700, 1920, 180);
        greenBox.setBackground(Color.decode("#1f867b"));
        add(greenBox);

        Pokemon player1 = ChoosePlayer1.pokemon[p1];
        JLabel p1Label = new JLabel();
        new Animation(p1Label, player1.getName(), "back", 300, 300).start();
        p1Label.setBounds(150, 400, 300, 300);
        add(p1Label);

        JLabel container1 = createPokemonContainer(player1, 0, 150, 200, 180);
        add(container1);

        Pokemon player2 = ChoosePlayer1.pokemon[p2];
        JLabel p2Label = new JLabel();
        new Animation(p2Label, player2.getName(), "front", 300, 300).start();
        p2Label.setBounds(900, 200, 300, 300);
        add(p2Label);

        JLabel container2 = createPokemonContainer(player2, 1, 900, 0, 180);
        add(container2);

        System.out.println(container2.getBounds());

        // --- PERBAIKAN UTAMA #1: Loop untuk mengisi gerakan[] harus 3 kali ---
        for (int i = 0; i <= 1; i++) {
            int pokemonIndex = (i == 0) ? p1 : p2;
            for (int j = 0; j < 3; j++) { // PERBAIKAN: Loop hanya 3 kali untuk moves (0, 1, 2)
                for (int k = 0; k < 4; k++) { // PERBAIKAN: k harus dimulai dari 0 dan kurang dari 4
                    gerakan[i][j][k] = ChoosePlayer1.getDataMoves()[pokemonIndex][j][k];
                }
            }
        }

        // --- PERBAIKAN UTAMA #2: Loop untuk tombol serangan P1 (hanya 3 serangan) ---
        // buttonP1 tetap 4 jika tombol ke-4 untuk HEAL, jika tidak, ubah menjadi 3.
        // Asumsi tombol ke-4 itu selalu ada dan itu tombol HEAL, tidak terkait dataMove.
        JButton[] buttonP1 = new JButton[4];
        int[][] buttonP1Positions = {{1000, 650}, {1220, 650}, {1000, 760}, {1220, 760}};
        for (int i = 0; i < 3; i++) {
            Type type = Type.valueOf(gerakan[0][i][2]);
            JLabel icon = new JLabel(getTypeImage(type, 110, 80));
            icon.setBounds(-20, 0, 110, 80);
            JLabel name = new JLabel(gerakan[0][i][1]);
            name.setBounds(60, 20, 200, 40);
            name.setFont(new Font("Times New Roman", Font.BOLD, 24));
            name.setForeground(Color.white);
            buttonP1[i] = new JButton(); // Ini mengisi tombol serangan (0,1,2)
            buttonP1[i].setLayout(null);
            buttonP1[i].setVisible(true);
            buttonP1[i].add(icon);
            buttonP1[i].add(name);
            buttonP1[i].setBounds(buttonP1Positions[i][0], buttonP1Positions[i][1], 200, 90);
            buttonP1[i].setBackground(Color.decode("#c4cd8e"));
            add(buttonP1[i]);
            setComponentZOrder(buttonP1[i], 0);
        }
        // Inisialisasi tombol HEAL untuk Player 1 (indeks 3)
        buttonP1[3] = new JButton("Heal"); // Inisialisasi buttonP1[3]
        buttonP1[3].setFont(new Font("Times New Roman", Font.BOLD, 24));
        buttonP1[3].setForeground(Color.white);
        buttonP1[3].setBackground(Color.decode("#90EE90")); // Warna hijau untuk Heal
        buttonP1[3].setBounds(buttonP1Positions[3][0], buttonP1Positions[3][1], 200, 90);
        buttonP1[3].setVisible(true);
        add(buttonP1[3]);
        setComponentZOrder(buttonP1[3], 0);


        JButton[] buttonP2 = new JButton[4];
        int[][] buttonP2Positions = {
                {1000, 650},
                {1220, 650},
                {1000, 760},
                {1220, 760}};
        for (int i = 0; i < 3; i++) {
            Type type = Type.valueOf(gerakan[1][i][2]);
            JLabel icon = new JLabel(getTypeImage(type, 110, 80));
            icon.setBounds(-20, 0, 110, 80);
            JLabel name = new JLabel(gerakan[1][i][1]);
            name.setBounds(60, 20, 200, 40);
            name.setFont(new Font("Times New Roman", Font.BOLD, 24));
            name.setForeground(Color.white);
            buttonP2[i] = new JButton(); // Ini mengisi tombol serangan (0,1,2)
            buttonP2[i].setLayout(null);
            buttonP2[i].setVisible(false); // Awalnya tidak terlihat untuk P2
            buttonP2[i].add(icon);
            buttonP2[i].add(name);
            buttonP2[i].setBounds(buttonP2Positions[i][0], buttonP2Positions[i][1], 200, 90);
            buttonP2[i].setBackground(Color.decode("#c4cd8e"));
            add(buttonP2[i]);
            setComponentZOrder(buttonP2[i], 0);
        }
        // Inisialisasi tombol HEAL untuk Player 2 (indeks 3)
        buttonP2[3] = new JButton("Heal"); // Inisialisasi buttonP2[3]
        buttonP2[3].setFont(new Font("Times New Roman", Font.BOLD, 24));
        buttonP2[3].setForeground(Color.white);
        buttonP2[3].setBackground(Color.decode("#90EE90")); // Warna hijau untuk Heal
        buttonP2[3].setBounds(buttonP2Positions[3][0], buttonP2Positions[3][1], 200, 90);
        buttonP2[3].setVisible(false); // Sembunyikan untuk P2 di awal
        add(buttonP2[3]);
        setComponentZOrder(buttonP2[3], 0);


        JLabel text = new JLabel("Ready...");
        text.setBounds(100, 50, 900, 75);
        text.setFont(new Font("Times New Roman", Font.BOLD, 37));
        text.setForeground(Color.decode("#fcdc59"));
        greenBox.add(text);

        String[] message = {"Set..", "Battle!", "What will " + player1.getName() + " do?"};
        Timer timer = new Timer(500, null);
        timer.addActionListener(new ActionListener() {
            int index = 0;
            @Override
            public void actionPerformed(ActionEvent e) {
                if (index < message.length) {
                    text.setText(message[index]);
                    index++;
                } else {
                    timer.stop();
                }
            }
        });
        timer.start();

        // --- PERBAIKAN #4: Inisialisasi moveP1 dan moveP2 (hanya 3 moves) ---
        for (int i = 0; i < 3; i++) { // PERBAIKAN: Loop hanya 3 kali
            String pType = ChoosePlayer1.getDataMoves()[p1][i][0];
            String n = ChoosePlayer1.getDataMoves()[p1][i][1];
            String a = ChoosePlayer1.getDataMoves()[p1][i][2];
            int pwr = Integer.parseInt(ChoosePlayer1.getDataMoves()[p1][i][3]);
            moveP1[i] = new Move(pType, n, a, pwr);
        }
        for (int i = 0; i < 3; i++) { // PERBAIKAN: Loop hanya 3 kali
            String pType = ChoosePlayer1.getDataMoves()[p2][i][0];
            String n = ChoosePlayer1.getDataMoves()[p2][i][1];
            String a = ChoosePlayer1.getDataMoves()[p2][i][2];
            int pwr = Integer.parseInt(ChoosePlayer1.getDataMoves()[p2][i][3]);
            moveP2[i] = new Move(pType, n, a, pwr);
        }

        // --- Player 1's Attack Buttons ---
        for (int i = 0; i < 3; i++) { // PERBAIKAN: Loop hanya 3 kali untuk serangan
            int finalI = i;
            buttonP1[i].addActionListener(e -> {
                if(p1Turn && !dead) {
                    Type attackType = Type.valueOf(gerakan[0][finalI][2]);
                    Type defenderType = player2.getType();
                    int damage = calculateDamage(player1, moveP1[finalI], player2);
                    player2.setCurrentHealth(player2.getCurrentHealth() - damage);
                    progressBars[1].setValue(player2.getCurrentHealth());

                    text.setText(player1.getName() + " used " + gerakan[0][finalI][1] + "!");

                    if (player2.getCurrentHealth() <= 0) {
                        text.setText(player2.getName() + " has fainted");
                        dead = true;

                        Timer deathMessage = new Timer(500, e3 -> {
                            text.setText(player1.getName() + " has won the battle!");
                            Timer nextPage = new Timer(500, e9 ->{
                                WinPage winPage = new WinPage(cardLayout, cardPanelContainer, p1, "Player 1");
                                cardPanelContainer.add(winPage, "panel.WinPage");
                                cardLayout.show(cardPanelContainer,"panel.WinPage");
                            });
                            nextPage.setRepeats(false);
                            nextPage.start();
                        });
                        deathMessage.setRepeats(false);
                        deathMessage.start();

                    } else {
                        Timer effectivenessTimer = new Timer(500, e1 -> {
                            double effectiveness = getTypeEffectiveness(attackType, defenderType);
                            if (effectiveness > 1.5) {
                                text.setText("It was super effective!");
                            } else if (effectiveness == 1.5) {
                                text.setText("It was effective!");
                            } else if (effectiveness < 1.0 && effectiveness != 0.0) {
                                text.setText("It was not very effective...");
                            } else if (effectiveness == 0.0) {
                                text.setText("It had no effect!");
                            }
                            else {
                                text.setText("");
                            }

                            Timer damageTimer = new Timer(500, e2 -> {
                                text.setText(player2.getName() + " lost " + damage + " health!");

                                Timer nextTurn = new Timer(300,e4 -> {
                                    text.setText("It's now " + player2.getName() + "'s turn");
                                    for (int a = 0; a < 4; a++) {
                                        buttonP1[a].setVisible(false);
                                        buttonP2[a].setVisible(true);
                                    }
                                    p1Turn = false;
                                });
                                nextTurn.setRepeats(false);
                                nextTurn.start();
                            });
                            damageTimer.setRepeats(false);
                            damageTimer.start();
                        });
                        effectivenessTimer.setRepeats(false);
                        effectivenessTimer.start();
                    }
                }
            });
        }
        // --- Player 1's Heal Button (index 3) ---
        buttonP1[3].addActionListener(e -> {
            if(p1Turn && !dead) {
                if (player1.getCurrentHealth() == player1.getHealth()) {
                    text.setText(player1.getName() + " is already at full health!");
                } else {
                    int healed = Math.min(30, player1.getHealth() - player1.getCurrentHealth());
                    player1.setCurrentHealth(player1.getCurrentHealth() + healed);
                    progressBars[0].setValue(player1.getCurrentHealth());
                    if (player1.getCurrentHealth() >= player1.getHealth()) {
                        text.setText(player1.getName() + " has fully regained its health!");
                    } else {
                        text.setText(player1.getName() + " gained " + healed + " HP!");
                    }
                }

                Timer healMessageTimer = new Timer(500, e5 -> {
                    Timer nextTurn = new Timer(300,e4 -> {
                        text.setText("It's now " + player2.getName() + "'s turn");
                        for(int a = 0; a < 4; a++ ) {
                            buttonP1[a].setVisible(false);
                            buttonP2[a].setVisible(true);
                        }
                        p1Turn = false;
                    });
                    nextTurn.setRepeats(false);
                    nextTurn.start();
                });
                healMessageTimer.setRepeats(false);
                healMessageTimer.start();
            }
        });

        // --- Player 2's Attack Buttons ---
        for (int i = 0; i < 3; i++) { // PERBAIKAN: Loop hanya 3 kali untuk serangan
            int finalI = i;
            buttonP2[i].addActionListener(e -> {
                if(!p1Turn && !dead) {
                    Type attackType = Type.valueOf(gerakan[1][finalI][2]);
                    Type defenderType = player1.getType();
                    int damage = calculateDamage(player2, moveP2[finalI], player1);
                    player1.setCurrentHealth(player1.getCurrentHealth() - damage);
                    progressBars[0].setValue(player1.getCurrentHealth());

                    text.setText(player2.getName() + " used " + gerakan[1][finalI][1] + "!");

                    if (player1.getCurrentHealth() <= 0) {
                        text.setText(player1.getName() + " has fainted");
                        dead = true;

                        Timer deathMessage = new Timer(500, e3 -> {
                            text.setText(player2.getName() + " has won the battle!");
                            Timer nextPage = new Timer(500, e9 ->{
                                WinPage winPage = new WinPage(cardLayout, cardPanelContainer, p2, "Player 2");
                                cardPanelContainer.add(winPage, "panel.WinPage");
                                cardLayout.show(cardPanelContainer,"panel.WinPage");
                            });
                            nextPage.setRepeats(false);
                            nextPage.start();
                        });
                        deathMessage.setRepeats(false);
                        deathMessage.start();

                    } else {
                        Timer effectivenessTimer = new Timer(500, e1 -> {
                            double effectiveness = getTypeEffectiveness(attackType, defenderType);
                            if (effectiveness > 1.5) {
                                text.setText("It was super effective!");
                            } else if (effectiveness == 1.5) {
                                text.setText("It was effective!");
                            } else if (effectiveness < 1.0) {
                                text.setText("It was not very effective...");
                            } else {
                                text.setText("");
                            }

                            Timer damageTimer = new Timer(500, e2 -> {
                                text.setText(player1.getName() + " lost " + damage + " health!");

                                Timer nextTurn = new Timer(300,e4 -> {
                                    text.setText("It's now " + player1.getName() + "'s turn");
                                    for (int a = 0; a < 4; a++) {
                                        buttonP1[a].setVisible(true);
                                        buttonP2[a].setVisible(false);
                                    }
                                    p1Turn = true;
                                });
                                nextTurn.setRepeats(false);
                                nextTurn.start();
                            });
                            damageTimer.setRepeats(false);
                            damageTimer.start();
                        });
                        effectivenessTimer.setRepeats(false);
                        effectivenessTimer.start();
                    }
                }
            });
        }
        // --- Player 2's Heal Button (index 3) ---
        buttonP2[3].addActionListener(e -> {
            if(!p1Turn && !dead) {
                if (player2.getCurrentHealth() == player2.getHealth()) {
                    text.setText(player2.getName() + " is already at full health!");
                } else {
                    int healed = Math.min(30, player2.getHealth() - player2.getCurrentHealth());
                    player2.setCurrentHealth(player2.getCurrentHealth() + healed);
                    progressBars[1].setValue(player2.getCurrentHealth());
                    if (player2.getCurrentHealth() >= player2.getHealth()) {
                        text.setText(player2.getName() + " has fully regained its health!");
                    } else {
                        text.setText(player2.getName() + " gained " + healed + " HP!");
                    }
                }

                Timer healMessageTimer = new Timer(500, e5 -> {
                    Timer nextTurn = new Timer(300,e4 -> {
                        text.setText("It's now " + player1.getName() + "'s turn");
                        for(int a = 0; a < 4; a++ ) {
                            buttonP1[a].setVisible(true);
                            buttonP2[a].setVisible(false);
                        }
                        p1Turn = true;
                    });
                    nextTurn.setRepeats(false);
                    nextTurn.start();
                });
                healMessageTimer.setRepeats(false);
                healMessageTimer.start();
            }
        });
    }

    private JLabel createPokemonContainer(Pokemon player, int index, int x, int y, int py) {
        JLabel container = new JLabel();
        container.setLayout(null);
        container.setBounds(x, y, 400, 300);

        JLabel name = new JLabel(player.getName());
        name.setBounds(0, 0, 400, 300);
        name.setFont(new Font("Times New Roman", Font.BOLD, 40));
        name.setForeground(Color.decode("#fcdc59"));
        container.add(name);

        progressBars[index] = new JProgressBar();
        progressBars[index].setMaximum(player.getHealth());
        progressBars[index].setValue(player.getHealth());
        progressBars[index].setBounds(0, py, 350, 20);
        progressBars[index].setStringPainted(true);
        progressBars[index].setForeground(Color.GREEN);
        progressBars[index].setBackground(Color.RED);
        progressBars[index].setBorder(BorderFactory.createLineBorder(Color.black, 5));
        container.add(progressBars[index]);

        return container;
    }

    public ImageIcon getTypeImage(Type type, int x, int y) {
        // PERBAIKAN: Perbaiki path gambar latar belakang
        ImageIcon image = new ImageIcon("C:\\Users\\asma\\IdeaProjects\\ProjectPokemon\\src\\assets\\Types\\" + type + ".png");
        Image scaled = image.getImage().getScaledInstance(x, y, Image.SCALE_SMOOTH);
        return new ImageIcon(scaled);
    }

    public static int calculateDamage(Pokemon attacker, Move move, Pokemon defender) {
        // PERBAIKAN: Gunakan getCurrentHealth() untuk mendapatkan HP saat ini untuk defender.getDefense()
        // Namun, defense umumnya tidak bergantung pada currentHealth, melainkan base defense stat.
        // Jika defense stat dihitung berdasarkan health, ini perlu logika lebih lanjut.
        // Asumsi defender.getDefense() mengembalikan base defense stat.
        double effectiveness = getTypeEffectiveness(move.getType(), defender.getType());
        int damage = (int) ((move.getPower() * attacker.getAttack() / defender.getDefense()) * effectiveness);
        return Math.max(0, damage);
    }

    public static double getTypeEffectiveness(Type attackType, Type defendType) {
        // ... (kode getTypeEffectiveness tidak berubah) ...
        int attackIndex = attackType.ordinal();
        int defendIndex = defendType.ordinal();
        if (attackIndex < typeChart.length && defendIndex < typeChart[attackIndex].length) {
            return typeChart[attackIndex][defendIndex];
        }
        else {return 1.0;}
    }

    // PERBAIKAN: typeChart sudah sesuai dengan 13 tipe yang diberikan
    private static final double[][] typeChart = {
            //       FIRE  WATR  GRAS  NORM  ELEC  FIGT  FLY   FAIR  BUG   DRAG  ICE   GRND  HEAL
            /*FIRE*/  {0.5, 0.5, 2.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 0.5, 2.0, 0.5, 1.0}, // FIRE
            /*WATER*/ {2.0, 0.5, 0.5, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.5, 1.0, 2.0, 1.0}, // WATER
            /*GRASS*/ {0.5, 2.0, 0.5, 1.0, 1.0, 1.0, 0.5, 1.0, 0.5, 0.5, 2.0, 2.0, 1.0}, // GRASS
            /*NORMAL*/{1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0}, // NORMAL (Fighting is 2x, Ghost 0x, but no Ghost type here)
            /*ELEC*/  {1.0, 2.0, 0.5, 1.0, 0.5, 1.0, 2.0, 1.0, 1.0, 0.5, 1.0, 0.0, 1.0}, // ELECTRIC (Ground 0x)
            /*FIGHT*/ {1.0, 1.0, 1.0, 2.0, 1.0, 1.0, 0.5, 0.5, 0.5, 1.0, 2.0, 1.0, 1.0}, // FIGHTING
            /*FLY*/   {1.0, 1.0, 2.0, 1.0, 0.5, 2.0, 1.0, 1.0, 2.0, 1.0, 1.0, 1.0, 1.0}, // FLYING
            /*FAIRY*/ {1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 1.0, 1.0, 1.0, 2.0, 1.0, 1.0, 1.0}, // FAIRY (Fighting 2x, Dragon 2x)
            /*BUG*/   {0.5, 1.0, 2.0, 1.0, 1.0, 0.5, 0.5, 0.5, 1.0, 1.0, 1.0, 1.0, 1.0}, // BUG
            /*DRAGON*/{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.5, 1.0, 2.0, 1.0, 1.0, 1.0}, // DRAGON (Fairy 0.5x, Dragon 2x)
            /*ICE*/   {0.5, 0.5, 2.0, 1.0, 1.0, 2.0, 2.0, 1.0, 1.0, 2.0, 0.5, 2.0, 1.0}, // ICE
            /*GROUND*/{2.0, 1.0, 0.5, 1.0, 2.0, 1.0, 0.0, 1.0, 0.5, 1.0, 1.0, 1.0, 1.0}, // GROUND (Flying 0x)
            /*HEAL*/  {1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0}  // HEAL (Asumsi netral untuk semua)
    };

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
    }
}