package panel;

import pokemon.Battle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Base extends JFrame {
    private JPanel mainPanel;
    private CardLayout cardLayout;

    private LandingPage landingPage;
    private HomePage homePage;
    private ChoosePlayer1 choosePlayer1;
    //private ChoosePlayer2 choosePlayer2;
    private Battle battle;
    private PreBattle preBattle;
    private Showcase showcase;

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
        Showcase showcase = new Showcase(cardLayout, mainPanel);
        ChoosePlayer1 choosePlayer1 = new ChoosePlayer1(cardLayout, mainPanel);
        //ChoosePlayer2 choosePlayer2 = new ChoosePlayer2(cardLayout, mainPanel);
        PreBattle preBattle = new PreBattle(cardLayout, mainPanel, 1 , 2);
        BattlePage battlePage = new BattlePage(cardLayout, mainPanel, 1, 2);
        DetailPokemon detailPokemon = new DetailPokemon(cardLayout, mainPanel);

        // Masukkan ke card layout
        mainPanel.add(landingPage, "panel.LandingPage");
        mainPanel.add(homePage, "panel.HomePage");
        mainPanel.add(showcase, "panel.Showcase");
        mainPanel.add(choosePlayer1, "panel.ChoosePlayer1");
        //mainPanel.add(choosePlayer2, "panel.ChoosePlayer2");
        mainPanel.add(preBattle, "panel.PreBattle");
        mainPanel.add(battlePage, "panel.BattlePage");
        mainPanel.add(showcase, "panel.Showcase");
        mainPanel.add(detailPokemon, "panel.DetailPokemon");

        // Set tampilan awal
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (landingPage != null) landingPage.stopMusic();
                if (homePage != null) homePage.stopMusic();
                if (choosePlayer1 != null) choosePlayer1.stopMusic();
//                if (battlePage != null) battlePage.stopMusic(); // Jika BattlePage punya musik

                dispose();
                System.exit(0);
            }
        });
    }

public CardLayout getCardLayout() {
    return cardLayout;
}

public JPanel getMainPanel() {
    return mainPanel;
}
}

