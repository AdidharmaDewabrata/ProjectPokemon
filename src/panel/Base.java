package panel;

import pokemon.Battle;

import javax.swing.*;
import java.awt.*;

public class Base extends JFrame {
    private JPanel mainPanel;
    private CardLayout cardLayout;

    public Base() {
        this.setTitle("PooperMon");

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        GraphicsConfiguration gc = gd.getDefaultConfiguration();
        Rectangle usableBounds = gc.getBounds();
        Insets screenInsets = Toolkit.getDefaultToolkit().getScreenInsets(gc);

        int usableWidth = usableBounds.width - screenInsets.left - screenInsets.right;
        int usableHeight = usableBounds.height - screenInsets.top - screenInsets.bottom;

        this.setSize(usableWidth, usableHeight);
        this.setLocation(screenInsets.left, screenInsets.top);
        this.setUndecorated(false);
        this.setLocationRelativeTo(null);

        // CardLayout container
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Daftar halaman
        LandingPage landingPage = new LandingPage(cardLayout, mainPanel);
        HomePage homePage = new HomePage(cardLayout, mainPanel);
        ChoosePlayer1 choosePlayer1 = new ChoosePlayer1(cardLayout, mainPanel);
        BattlePage battlePage = new BattlePage(cardLayout, mainPanel);

        // Masukkan ke card layout
        mainPanel.add(landingPage, "panel.LandingPage");
        mainPanel.add(homePage, "panel.HomePage");
        mainPanel.add(choosePlayer1, "panel.ChoosePlayer1");
        mainPanel.add(battlePage, "panel.BattlePage");

        // Set tampilan awal
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
