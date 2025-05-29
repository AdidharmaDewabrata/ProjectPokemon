package panel;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class HomePage extends JPanel {
    private Image backgroundImage;

    public HomePage(CardLayout cardLayout, JPanel cardPanelContainer) { // Mengganti 'panel' menjadi 'cardPanelContainer' agar lebih jelas
        // Memuat gambar latar belakang saat objek HomePage dibuat
        backgroundImage = new ImageIcon("C:\\Users\\adksp\\Downloads\\1defd9939a5a1d4c5fd9da1990789882.jpg").getImage();
        setLayout(null);

        //images podium & poopermon
        ImageIcon poopermon = new ImageIcon("C:\\Users\\adksp\\Downloads\\Heading (4).png");
        ImageIcon podium = new ImageIcon("C:\\Users\\adksp\\Downloads\\Heading (6).png");
        Image pod1 = podium.getImage().getScaledInstance(1450, 850, Image.SCALE_SMOOTH);
        ImageIcon podium1 = new ImageIcon(pod1);

        JLabel title = new JLabel(poopermon);
        title.setBounds(-20, -30, poopermon.getIconWidth(), poopermon.getIconHeight());
        JLabel pod = new JLabel(podium1);
        pod.setVisible(true);
        pod.setLayout(null);
        pod.setBounds(-250, -30, podium.getIconWidth(), podium.getIconHeight());
        JLabel pods = new JLabel(podium1);
        pods.setVisible(true);
        pods.setLayout(null);
        pods.setBounds(250, -30, podium.getIconWidth(), podium.getIconHeight());

        JButton play = new JButton("Play");
        play.setBounds(1100, 250, 250, 75);
        play.setBackground(Color.decode("#fcdc59"));
        play.setFont(new Font("Tahoma", Font.BOLD, 40));

        JButton see = new JButton("See Pokemon's");
        see.setBounds(1100, 400, 250, 75);
        see.setBackground(Color.decode("#aae6ff"));
        see.setFont(new Font("Tahoma", Font.BOLD, 25));

        JButton back = new JButton("Back");
        back.setBounds(1100, 550, 250, 75);
        back.setBackground(Color.decode("#f23041"));
        back.setFont(new Font("Tahoma", Font.BOLD, 40));

        add(title);
        add(see);
        add(play);
        add(pod);
        add(pods);
        add(back);

        // Ini ke player 1 pick pokemon (pre-battle)
        play.addActionListener(e -> {
            cardLayout.show(cardPanelContainer, "panel.ChoosePlayer1");
        });

        // Lihat pokemon list
        see.addActionListener(e -> {
            cardLayout.show(cardPanelContainer, "pokemonList");
        });

        //back to landingpage
        back.addActionListener(e -> {
            cardLayout.show(cardPanelContainer, "panel.LandingPage");
        });

        JLabel blastoise = new JLabel();
        new Animation(blastoise, "blastoise", "front",350, 350).start();
        blastoise.setBounds(120, 320, 335, 350);
        blastoise.setVisible(true);
        add(blastoise);
        setComponentZOrder(blastoise,0);

        JLabel charizard = new JLabel();
        new Animation(charizard, "charizard", "front",435, 455).start();
        charizard.setBounds(580, 220, 435, 455);
        charizard.setVisible(true);
        add(charizard);
        setComponentZOrder(charizard,0);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Penting: Selalu panggil super.paintComponent()
        // Gambar gambar latar belakang memenuhi seluruh area HomePage
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}