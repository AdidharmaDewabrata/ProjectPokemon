import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class LandingPage extends JFrame {
    LandingPage() {
        this.setTitle("PooperMon");
        this.setSize(1920, 1080);
        this.setLayout(null);

        Image back = new ImageIcon("C:\\Users\\adksp\\Downloads\\Sprites\\Heading.jpg").getImage();

        JPanel panel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(back, 0, 0, getWidth(), getHeight(), this);
            }
        };

        //tambah button
        JButton start = new JButton("Start");
        start.setFont(new Font("Fredoka", Font.BOLD, 30));
        start.setForeground(Color.BLUE);
        start.setBounds(650, 580, 275, 80);
        start.setBackground(Color.decode("#faf5e2"));
        panel.add(start);

        JButton history = new JButton("History");
        history.setFont(new Font("Fredoka", Font.BOLD, 30));
        history.setForeground(Color.BLUE);
        history.setBounds(650, 670, 275, 80);
        history.setBackground(Color.decode("#faf5e2"));
        panel.add(history);

        JButton exitGame = new JButton("EXIT");
        exitGame.setFont(new Font("Fredoka", Font.BOLD, 30));
        exitGame.setForeground(Color.BLUE);
        exitGame.setBounds(1350, 800, 125, 40);
        exitGame.setBackground(Color.decode("#faf5e2"));
        panel.add(exitGame);

        panel.setBounds(0, 0, getWidth(), getHeight());
        panel.setLayout(null);
        panel.setVisible(true);

        this.setContentPane(panel);
        this.setVisible(true);

        start.addActionListener(e -> {
            new Battle();
            LandingPage.this.setVisible(false);
        });

        history.addActionListener(e -> {
            System.out.println("masi blom kelar");
        });

        exitGame.addActionListener(e -> {
            System.exit(0);
        });

    }
}
