package panel;
import javax.swing.*;
import java.awt.*;

public class Base extends JFrame{
    private JPanel mainPanel;
    private CardLayout cardLayout;
    public Base() {
        this.setTitle("PooperMon");
//        this.setSize(1024, 750);
//        this.setLocationRelativeTo(null);

//        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
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

        //bikin container buat cardLayoutnya
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

       //List panel yang ada, pagenya gitu dah
        LandingPage landingPage = new LandingPage(cardLayout, mainPanel);
        Menu menu = new Menu();

        //dimasukin ke card
        mainPanel.add(landingPage, "panel.LandingPage");
        mainPanel.add(menu, "menu");

        //nampilin panel utama, si homepagenya
        this.setContentPane(mainPanel);
        this.setVisible(true);

    }

}
