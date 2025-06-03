package panel;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Arrays;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.sound.sampled.*;

public class DetailPokemon extends JPanel {
    private Image backgroundImage;
    //sound tergantung nanti dah
    //public static final String HOMEPAGE_MUSIC = "homepage.wav";
    private static Clip clip;
    private String pokemonName;

    // Subkelas untuk panel dengan latar belakang gambar
    private static class BgPanel extends JPanel {
        private final Image background;

        //biar pasti bakal kepanggil pake class
        public BgPanel(String imagePath) {
            this.background = new ImageIcon(imagePath).getImage();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
        }
    }

    //buat ngambil data dari list pokemon di showcase
    public DetailPokemon(String pokemonName) {
        this.pokemonName = pokemonName;
        // Gunakan pokemonName untuk menampilkan data/animasi/gambar detail
        System.out.println("Menampilkan detail untuk: " + pokemonName);
    }

    public DetailPokemon(CardLayout cardLayout, JPanel cardPanelContainer) {
        this.setLayout(new BorderLayout());

        // Pastikan path ini valid di sistem
        String backgroundPath = "C:\\Users\\asma\\IdeaProjects\\ProjectPokemon\\src\\panel\\6bea12ee9c7b069e8bdcf74726fdd299.jpg";
        DetailPokemon.BgPanel bgPanel = new DetailPokemon.BgPanel(backgroundPath);//manggil kelas yang di atas tadi
        bgPanel.setLayout(new BorderLayout());
        bgPanel.setPreferredSize(new Dimension(1280, 720)); // Ukuran disesuaikan frame

        JButton back = new JButton("Back");
        back.setBounds(950, 550, 250, 75);
        back.setBackground(Color.decode("#fcdc59"));
        back.setFont(new Font("Tahoma", Font.BOLD, 40));
    }
}
