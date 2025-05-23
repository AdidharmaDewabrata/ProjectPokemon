package panel;

import javax.swing.*;
import java.awt.*;

public class LandingPage extends JPanel {
    public LandingPage(CardLayout cardLayout, JPanel mainPanel) {
        this.setLayout(new BorderLayout());

        Image back = new ImageIcon("C:\\Users\\adksp\\Downloads\\Sprites\\Heading.jpg").getImage();

        JPanel panel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(back, 0, 0, getWidth(), getHeight(), this);
            }
        };

        // set layout dan ukuran
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(1920, 1080));

        // Tombol Start
        JButton start = new JButton("Start");
        start.setFont(new Font("Fredoka", Font.BOLD, 30));
        start.setForeground(Color.BLUE);
        start.setBounds(650, 580, 275, 80);
        start.setBackground(Color.decode("#faf5e2"));
        panel.add(start);

        // Tombol History
        JButton history = new JButton("History");
        history.setFont(new Font("Fredoka", Font.BOLD, 30));
        history.setForeground(Color.BLUE);
        history.setBounds(650, 670, 275, 80);
        history.setBackground(Color.decode("#faf5e2"));
        panel.add(history);

        // Tombol Exit
        JButton exitGame = new JButton("EXIT");
        exitGame.setFont(new Font("Fredoka", Font.BOLD, 30));
        exitGame.setForeground(Color.BLUE);
        exitGame.setBounds(1350, 800, 125, 40);
        exitGame.setBackground(Color.decode("#faf5e2"));
        panel.add(exitGame);

        // Tambahkan ke LandingPage panel utama
        this.add(panel, BorderLayout.CENTER);

        // Event listener
        start.addActionListener(e -> {
            // Contoh: show panel "menu"
            cardLayout.show(mainPanel, "menu");
        });

        history.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Masih belum kelar!");
        });

        exitGame.addActionListener(e -> {
            System.exit(0);
        });
    }
}
