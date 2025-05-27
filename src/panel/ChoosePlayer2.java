package panel;

import javax.swing.*;
import java.awt.*;

public class ChoosePlayer2 extends JPanel {
    public ChoosePlayer2(CardLayout cardLayout, JPanel mainPanel) {
        this.setLayout(new BorderLayout());
        Image back = new ImageIcon("F:\\dv\\college\\code\\Intellij\\Pemlan\\ProjectPokemonGUI\\src\\assets\\bg_choosePokemon.jpg").getImage();
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

// Tombol History
        JButton confirm = new JButton("History");
        confirm.setFont(new Font("Fredoka", Font.BOLD, 30));
        confirm.setForeground(Color.BLUE);
        confirm.setBackground(Color.decode("#faf5e2"));
        buttonPanel.add(confirm);

// Tambahkan button panel ke bawah
        panel.setLayout(new BorderLayout());
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Tambahkan ke ChoosePlayer2 panel utama
        this.add(panel, BorderLayout.CENTER);

        // Event listener
        confirm.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, " ");
        });
    }
}
