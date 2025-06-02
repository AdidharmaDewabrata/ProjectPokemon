package panel;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class PreBattle extends JPanel {
    private Image background, back;
    private JLabel pod1Label;
    private JLabel pod2Label;

    public PreBattle(CardLayout cardLayout, JPanel cardPanelContainer, int p1, int p2) {
        back = new ImageIcon("C:\\Users\\adksp\\IdeaProjects\\ProjectPokemon\\src\\assets\\bg_prebattle.jpg").getImage();
        setLayout(null);

        //podium
        URL podiumUrl = getClass().getResource("/assets/podium.png");
        ImageIcon podiumIcon = null;
        if (podiumUrl != null) {
            podiumIcon = new ImageIcon(podiumUrl);
        } else {
            System.err.println("Podium image not found: /assets/podium.png");
        }

        if (podiumIcon != null && podiumIcon.getImageLoadStatus() == MediaTracker.COMPLETE) {
            Image scaledPodiumImage = podiumIcon.getImage().getScaledInstance(1450, 850, Image.SCALE_SMOOTH);
            ImageIcon scaledPodiumIcon = new ImageIcon(scaledPodiumImage);

            pod1Label = new JLabel(scaledPodiumIcon);
            pod1Label.setBounds(0, -150, 1450, 850);
            add(pod1Label);

            pod2Label = new JLabel(scaledPodiumIcon);
            pod2Label.setBounds(700, -150, 1450, 850);
            add(pod2Label);
        } else {
            System.err.println("Podium icon is null or failed to load. Check path and file existence.");
        }

        //Playbutton
        JButton playButton = new JButton("Play");
        playButton.setForeground(Color.BLACK);
        playButton.setFont(new Font("Source Sans Pro", Font.BOLD, 30));
        playButton.setBackground(Color.decode("#aae6ff"));
        playButton.setBounds(550, 425, 200, 50);
        add(playButton);
        playButton.addActionListener(e -> {
            BattlePage battlePage = new BattlePage(cardLayout, cardPanelContainer, p1, p2);
            cardPanelContainer.add(battlePage, "panel.BattlePage");
            cardLayout.show(cardPanelContainer, "panel.BattlePage");
        });

        //Label player1&2
        JLabel player1 = new JLabel("Player 1");
        player1.setOpaque(false);
        player1.setBackground(new Color(0,0,0,0));
        player1.setForeground(Color.decode("#fcdc59"));
        player1.setBorder(null);
        player1.setFont(new Font("Source Sans Pro", Font.BOLD, 50));
        player1.setBounds(205,-30, 540,200);
        add(player1);

        JLabel player2 = new JLabel("Player 2");
        player2.setOpaque(false);
        player2.setBackground(new Color(0,0,0,0));
        player2.setForeground(Color.decode("#fcdc59"));
        player2.setBorder(null);
        player2.setFont(new Font("Source Sans Pro", Font.BOLD, 50));
        player2.setBounds(900,-30, 540,200);
        add(player2);

        //pokemon 1&2

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (back != null) {
            g.drawImage(back, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
