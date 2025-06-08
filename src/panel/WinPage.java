package panel;

import pokemon.Pokemon;

// import javax.smartcardio.Card; // <--- Ini tidak digunakan, bisa dihapus
import javax.swing.*;
import java.awt.*;

public class WinPage extends JPanel {
    private Image back;
    public WinPage(CardLayout cl, JPanel cardPanelContainer, int p1, String player) {
        setLayout(null);
        back = new ImageIcon("C:\\Users\\adksp\\IdeaProjects\\ProjectPokemon\\src\\assets\\win image.gif").getImage();

        JButton[] buttons = new JButton[3];
        int y = 200;
        for (int i = 0; i < 3; i++) {
            buttons[i] = new JButton();
            buttons[i].setBounds(150, y, 225, 115);
            buttons[i].setFont(new Font("Tahoma", Font.BOLD, 24));
            add(buttons[i]);
            y += 200; // Tambahkan jarak antar tombol agar tidak bertumpuk
        }
        buttons[0].setText("Play Again");
        buttons[1].setText("Home Page");
        buttons[2].setText("Exit Game");
        buttons[0].setBackground(Color.decode("#ffbd59"));
        buttons[1].setBackground(Color.decode("#c8e3b8"));
        buttons[2].setBackground(Color.decode("#faf5e2"));

        buttons[0].addActionListener(e -> {
            cl.show(cardPanelContainer, "panel.ChoosePlayer1");
        });
        buttons[1].addActionListener(e -> {
            cl.show(cardPanelContainer, "panel.HomePage");
        });
        buttons[2].addActionListener(e -> {
            System.exit(0);
        });

        Pokemon won = ChoosePlayer1.pokemon[p1];
        JLabel name = new JLabel(player + " Wins!"); // Tambah " Wins!" agar lebih jelas
        name.setBounds(750,100,300,150);
        name.setFont(new Font("Times New Roman", Font.BOLD, 40)); // Perbesar font
        name.setForeground(Color.RED); // Ubah warna agar lebih terlihat
        name.setHorizontalAlignment(SwingConstants.CENTER); // Pusatkan teks

        JLabel pokemon = new JLabel();
        new Animation(pokemon, won.getName(), "front", 400, 400).start(); // Pastikan Animation punya method .start() untuk GIF
        pokemon.setBounds(750,200,400,400);

        add(name);
        add(pokemon);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Menggambar gambar background GIF
        g.drawImage(back, 0, 0, getWidth(), getHeight(), this);
    }
}
