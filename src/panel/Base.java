package panel;
import javax.swing.*;
import java.awt.*;

public class Base extends JFrame{
    private JPanel mainPanel;
    private CardLayout cardLayout;
    public Base() {
        this.setTitle("PooperMon");
        this.setSize(1920, 1080);

        //bikin container buat cardLayoutnya
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

       //List panel yang ada, pagenya gitu dah
        LandingPage homePage = new LandingPage(cardLayout, mainPanel);
        Menu menu = new Menu();

        //dimasukin ke card
        mainPanel.add(LandingPage, "LandingPage");
        mainPanel.add(menu, "menu");

        //nampilin panel utama, si homepagenya
        this.setContentPane(mainPanel);
        this.setVisible(true);

    }

}
