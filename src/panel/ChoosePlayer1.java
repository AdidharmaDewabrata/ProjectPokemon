package panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChoosePlayer1 extends JPanel {
    public ChoosePlayer1() {
        this.setLayout(new BorderLayout());

        // Background Image
        Image back = new ImageIcon("C:\\Users\\asma\\IdeaProjects\\ProjectPokemon\\src\\assets\\hutanlendir.jpg").getImage();
        JPanel panel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(back, 0, 0, getWidth(), getHeight(), this);
            }
        };

        // Set layout GridBagLayout untuk fleksibilitas posisi
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 40, 10, 40);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        // Title Label (Player 1) di atas paling atas
        JLabel titleLabel = new JLabel("Player 1");
        titleLabel.setFont(new Font("Source Sans Pro", Font.BOLD, 40));
        titleLabel.setForeground(Color.decode("#fcdc59"));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.weighty = 0.1;
        panel.add(titleLabel, gbc);

        // Status Panel
        JPanel statusPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        statusPanel.setOpaque(false);
        statusPanel.add(new JLabel("HP: 100"));
        statusPanel.add(new JLabel("ATT: 30"));
        statusPanel.add(new JLabel("DEF: 50"));
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.weighty = 0.1;
        panel.add(statusPanel, gbc);

        // Ability Panel di pojok kanan bawah
        JPanel abilityPanel = new JPanel(new GridBagLayout());
        abilityPanel.setOpaque(false);

// Layout tombol ability secara vertikal
        GridBagConstraints abGbc = new GridBagConstraints();
        abGbc.gridx = 0;
        abGbc.insets = new Insets(5, 5, 5, 5);
        abGbc.anchor = GridBagConstraints.CENTER;

        abGbc.gridy = 0;
        JButton flash = new JButton("Flash");
        flash.setOpaque(true);
        flash.setEnabled(false);
        abilityPanel.add(flash, abGbc);

        abGbc.gridy = 1;
        JButton sleepTalk = new JButton("Sleep Talk");
        sleepTalk.setOpaque(true);
        sleepTalk.setEnabled(false);
        abilityPanel.add(sleepTalk, abGbc);

        abGbc.gridy = 2;
        JButton bulletSeed = new JButton("Bullet Seed");
        bulletSeed.setOpaque(true);
        bulletSeed.setEnabled(false);
        abilityPanel.add(bulletSeed, abGbc);

// Tambahkan ke panel utama di pojok kanan bawah
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.SOUTHEAST;
        gbc.weightx = 1;
        gbc.weighty = 1;
        panel.add(abilityPanel, gbc);


//confirm button
        JButton confirm = new JButton("Confirm");
        confirm.setFont(new Font("Source Sans Pro", Font.BOLD, 30));
        confirm.setForeground(Color.BLACK);
        confirm.setBackground(Color.decode("#93cd8e"));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 20));
        buttonPanel.setOpaque(false);
        buttonPanel.add(confirm);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.SOUTH;
        gbc.weighty = 0.2;
        panel.add(buttonPanel, gbc);

        panel.setLayout(new BorderLayout());
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Tambahkan panel background ke main panel
        this.add(panel, BorderLayout.CENTER);
        this.setVisible(true);

        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Navigate to next panel or action here
            }
        });
    }
}

