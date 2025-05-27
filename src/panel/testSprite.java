package panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class testSprite extends JPanel {
    private int index = 0;
    private JLabel animate;

    public testSprite() {
        setPreferredSize(new Dimension(400, 400));
        setLayout(null);

        ImageIcon[] pikachuframe = new ImageIcon[58];
        Image[] scaledPikachu = new Image[58];
        ImageIcon[] pikachu = new ImageIcon[58];
        for (int i = 0; i < 58; i++) {
            // Correct concatenation: use parentheses!
            pikachuframe[i] = new ImageIcon("C:\\Users\\adksp\\Downloads\\Sprites\\Pikachu Evo\\pikachu frames front\\pikachu-export" + (i + 1) + ".png");
            scaledPikachu[i] = pikachuframe[i].getImage().getScaledInstance(200,200, Image.SCALE_SMOOTH);
            pikachu[i] = new ImageIcon(scaledPikachu[i]);
        }

        animate = new JLabel(pikachu[0]);
        animate.setBounds(0, 0, 200, 200); // Enlarged bounds for visibility
        add(animate);

        Timer animationTimer = new Timer(100, e -> {
            index = (index + 1) % 58;
            animate.setIcon(pikachu[index]);
        });
        animationTimer.start();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Pikachu Animation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new testSprite());
        frame.pack();
        frame.setLocationRelativeTo(null); // Center on screen
        frame.setVisible(true);
    }
}
