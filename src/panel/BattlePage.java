package panel;

import javax.swing.*;
import java.awt.*;

public class BattlePage extends JPanel {
    private Image background;

    public BattlePage(CardLayout cardLayout, JPanel cardPanelContainer) {
        background = new ImageIcon("C:\\Users\\adksp\\Downloads\\6bea12ee9c7b069e8bdcf74726fdd299.jpg").getImage();
        setLayout(null);

        JPanel greenBox = new JPanel();
        greenBox.setBounds(0, 700, 1920, 180);
        greenBox.setBackground(Color.decode("#1f867b"));
        greenBox.setLayout(null);
        add(greenBox);

        //Buttons
        int x = 1000, y = 650;
        JButton[] button = new JButton[4];
        for (int i = 0; i < 4; i++) {
            button[i] = new JButton();
            button[i].setBackground(Color.decode("#c4cd8e"));
            if(i==2){
                x=1000;
                y=760;
                button[i].setBounds(x,y, 200, 90);
                add(button[i]);
                setComponentZOrder(button[i], 0);
            }
            button[i].setBounds(x,y, 200, 90);
            x += 220;
            add(button[i]);
            setComponentZOrder(button[i], 0);


        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
    }

}
