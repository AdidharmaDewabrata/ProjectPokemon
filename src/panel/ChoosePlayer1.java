package panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ChoosePlayer1 extends JPanel {
    private Image back;
    public ChoosePlayer1(CardLayout cardLayout, JPanel cardPanelContainer) {
        this.setLayout(null);
        back = new ImageIcon("C:\\Users\\adksp\\Downloads\\de1bb37306257d5cd20a87c3df39a108.jpg").getImage();

        //Player 1 label
        JTextField player1 = new JTextField("Player 1");
        player1.setOpaque(false);
        player1.setBackground(new Color(0,0,0,0));
        player1.setForeground(Color.decode("#fcdc59"));
        player1.setBorder(null);
        player1.setFont(new Font("Times New Roman", Font.BOLD, 70));
        player1.setBounds(640,-30, 540,200);
        add(player1);

        //arrow kanan
        JLabel right = new JLabel(new ImageIcon("C:\\Users\\adksp\\Downloads\\Sprites\\right.png"));
//        right.setBounds()
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(back, 0, 0, getWidth(), getHeight(), this);
    }
}



