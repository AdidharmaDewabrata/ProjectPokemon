import javax.swing.*;
import java.awt.*;


public class HomePage extends JFrame {
    HomePage() {
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
        JButton newGame = new JButton("New Game");
        newGame.setFont(new Font("Fredoka", Font.BOLD, 30));
        newGame.setForeground(Color.BLUE);
        newGame.setBounds(450, 630, 275, 80);
        newGame.setBackground(Color.decode("#faf5e2"));
        panel.add(newGame);
        JButton loadGame = new JButton("Load Game");
        loadGame.setFont(new Font("Fredoka", Font.BOLD, 30));
        loadGame.setForeground(Color.BLUE);
        loadGame.setBounds(850, 630, 275, 80);
        loadGame.setBackground(Color.decode("#faf5e2"));
        panel.add(loadGame);
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

    }
}
