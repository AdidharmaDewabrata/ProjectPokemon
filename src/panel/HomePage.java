package panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


public class HomePage extends JPanel {
    public HomePage(CardLayout cardLayoutm, JPanel mainPanel) {
        this.setLayout(new BorderLayout());
        //this.setBounds(0, 0, getWidth(), getHeight());

        //backgroudnya
        Image back = new ImageIcon("C:\\Users\\adksp\\Downloads\\Sprites\\Heading.jpg").getImage();

        //panel 1, homepage
        JPanel bgPanel = new JPanel(){
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(back, 0, 0, getWidth(), getHeight(),this);
            }
        };
        bgPanel.setLayout(null);
        bgPanel.setBounds(0, 0, getWidth(), getHeight());

        //tambah button
        JButton newGame = new JButton("New Game");
        newGame.setFont(new Font("Fredoka", Font.BOLD, 30));
        newGame.setForeground(Color.BLUE);
        newGame.setBounds(450, 630, 275, 80);
        newGame.setBackground(Color.decode("#faf5e2"));
        //pindah karena buttonnya
        newGame.addActionListener(e -> {
                cardLayoutm.show(mainPanel, "New Game");
        });


        JButton loadGame = new JButton("Load Game");
        loadGame.setFont(new Font("Fredoka", Font.BOLD, 30));
        loadGame.setForeground(Color.BLUE);
        loadGame.setBounds(850, 630, 275, 80);
        loadGame.setBackground(Color.decode("#faf5e2"));
        //pindah karena buttonya
        loadGame.addActionListener(e -> {
            cardLayoutm.show(mainPanel, "Load Game");
        });

        JButton exitGame = new JButton("EXIT");
        exitGame.setFont(new Font("Fredoka", Font.BOLD, 30));
        exitGame.setForeground(Color.BLUE);
        exitGame.setBounds(1350, 800, 125, 40);
        exitGame.setBackground(Color.decode("#faf5e2"));
        //pindah karena buttonya
        exitGame.addActionListener(e -> System.exit(0));

        bgPanel.add(newGame);
        bgPanel.add(loadGame);
        bgPanel.add(exitGame);
// sek ntar cek lagi
        this.setLayout(null);
        this.add(bgPanel, BorderLayout.CENTER);
    }

}
