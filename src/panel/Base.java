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
    private Showcase showcase; // Harus diinisialisasi di sini atau sebelum add ke mainPanel
    // private Battle battle; // Ini tidak diinisialisasi, mungkin tidak perlu di sini
    // private PreBattle preBattle; // Akan diinisialisasi di ChoosePlayer1
    // private BattlePage battlePage; // Akan diinisialisasi di PreBattle
    // private DetailPokemon detailPokemon; // Akan diinisialisasi di Showcase

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

        // Inisialisasi semua panel yang diperlukan di awal
        landingPage = new LandingPage(cardLayout, mainPanel);
        homePage = new HomePage(cardLayout, mainPanel);
        showcase = new Showcase(cardLayout, mainPanel); // Inisialisasi Showcase
        choosePlayer1 = new ChoosePlayer1(cardLayout, mainPanel); // ChoosePlayer1 akan membaca data statis

        // Panel seperti PreBattle, BattlePage, DetailPokemon akan dibuat DYNAMICALLY
        // saat navigasi diperlukan, karena mereka membutuhkan parameter spesifik.
        // HAPUS INISIALISASI di sini:
        // PreBattle preBattle = new PreBattle(cardLayout, mainPanel, 1 , 2);
        // BattlePage battlePage = new BattlePage(cardLayout, mainPanel, 1, 2);
        // DetailPokemon detailPokemon = new DetailPokemon(cardLayout, mainPanel, Showcase.getSelected()); // INI PENYEBAB MASALAHNYA

        // Masukkan panel awal ke card layout
        mainPanel.add(landingPage, "panel.LandingPage");
        mainPanel.add(homePage, "panel.HomePage");
        mainPanel.add(showcase, "panel.Showcase");
        mainPanel.add(choosePlayer1, "panel.ChoosePlayer1");
        // Hapus penambahan PreBattle, BattlePage, DetailPokemon di sini.
        // Mereka akan ditambahkan ketika dipanggil oleh ActionListener di panel lain.

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
                //if (battlePage != null) battlePage.stopMusic();

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
