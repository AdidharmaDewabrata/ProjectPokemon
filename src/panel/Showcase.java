package panel;

import javax.swing.*;
import java.awt.*;

public class Showcase extends JPanel {
    //subkelas buat manggil backgorund
    private static class BgPanel extends JPanel {
        private final Image background;
        public BgPanel(String imagePath) {
            this.background = new ImageIcon(imagePath).getImage();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
        }
    }

    public Showcase() {
        this.setLayout(new BorderLayout());

        //panggil di sini
        BgPanel bgPanel = new BgPanel("assets/showcase.png");

        // set layout dan ukuran
        bgPanel.setLayout(null);
        bgPanel.setPreferredSize(new Dimension(1920, 1080));

        JPanel scp = new JPanel(new GridLayout(3,4 , 15, 15));
    }
}
