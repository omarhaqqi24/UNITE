package Gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class SellectPokemon {
    public SellectPokemon(){
        JFrame frame = new JFrame("UNITE");
        // Mengatur ukuran frame
        frame.setSize(1920, 1080);
        // Mengatur aksi default saat frame ditutup
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Membuat instance dari JLayeredPane
        JLayeredPane layeredPane = new JLayeredPane();
        frame.add(layeredPane);

        // Memanggil method placeComponents untuk menambahkan gambar
        placeComponents(layeredPane, frame);

        frame.setVisible(true);
    }

    public static void placeComponents(JLayeredPane layeredPane, JFrame frame) {
        ImageIcon imageIcon = new ImageIcon("Aset/SellectPokemon.png");
        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setBounds(0, -60, imageIcon.getIconWidth(), imageIcon.getIconHeight());
        layeredPane.add(imageLabel, Integer.valueOf(2));

        JLabel select1 = new JLabel("Pokemon 1");
        select1.setBounds(75, 280, 200, 50);
        select1.setOpaque(true);
        select1.setBackground(Color.GREEN);
        select1.setForeground(Color.WHITE);
        select1.setHorizontalAlignment(SwingConstants.CENTER);
        layeredPane.add(select1, Integer.valueOf(3));

        ImageIcon imageIcon1 = new ImageIcon("Aset/_angin_.gif");
        JLabel imageLabel1 = new JLabel(imageIcon1);
        imageLabel1.setBounds(75, 330, imageIcon1.getIconWidth(), imageIcon1.getIconHeight());
        layeredPane.add(imageLabel1, Integer.valueOf(3));
        
        ImageIcon imageIcon2 = new ImageIcon("Aset/_es_.gif");
        JLabel imageLabel2 = new JLabel(imageIcon2);
        imageLabel2.setBounds(310, 330, imageIcon2.getIconWidth(), imageIcon2.getIconHeight());
        layeredPane.add(imageLabel2, Integer.valueOf(3));

        ImageIcon imageIcon3 = new ImageIcon("Aset/_api_.gif");
        JLabel imageLabel3 = new JLabel(imageIcon3);
        imageLabel3.setBounds(545, 330, imageIcon3.getIconWidth(), imageIcon3.getIconHeight());
        layeredPane.add(imageLabel3, Integer.valueOf(3));

        ImageIcon imageIcon4 = new ImageIcon("Aset/_air_.gif");
        JLabel imageLabel4 = new JLabel(imageIcon4);
        imageLabel4.setBounds(780, 330, imageIcon4.getIconWidth(), imageIcon4.getIconHeight());
        layeredPane.add(imageLabel4, Integer.valueOf(4));

        ImageIcon imageIcon5 = new ImageIcon("Aset/_tanah_.gif");
        JLabel imageLabel5 = new JLabel(imageIcon5);
        imageLabel5.setBounds(1015, 330, imageIcon5.getIconWidth(), imageIcon5.getIconHeight());
        layeredPane.add(imageLabel5, Integer.valueOf(4));

        JButton sellectButton1 = new JButton("1");
        sellectButton1.setBounds(75, 330, 200, 300);
        sellectButton1.setBackground(Color.GREEN);
        layeredPane.add(sellectButton1, Integer.valueOf(2));

        JButton sellectButton2 = new JButton("2");
        sellectButton2.setBounds(310, 330, 200, 300);
        sellectButton2.setBackground(Color.GREEN);
        layeredPane.add(sellectButton2, Integer.valueOf(2));

        JButton sellectButton3 = new JButton("3");
        sellectButton3.setBounds(545, 330, 200, 300);
        sellectButton3.setBackground(Color.GREEN);
        layeredPane.add(sellectButton3, Integer.valueOf(2));

        JButton sellectButton4 = new JButton("4");
        sellectButton4.setBounds(780, 330, 200, 300);
        sellectButton4.setBackground(Color.GREEN);
        layeredPane.add(sellectButton4, Integer.valueOf(2));

        JButton sellectButton5 = new JButton("5");
        sellectButton5.setBounds(1015, 330, 200, 300);
        sellectButton5.setBackground(Color.GREEN);
        layeredPane.add(sellectButton5, Integer.valueOf(2));

        sellectButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                NewGame.main(null);
            }
        });
        sellectButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                NewGame.main(null);
            }
        });
        sellectButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                NewGame.main(null);
            }
        });
        sellectButton4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                NewGame.main(null);
            }
        });
        sellectButton5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                NewGame.main(null);
            }
        });
    }

    public static void main(String[] args) {
        // Membuat objek Home
        new SellectPokemon();
    }
}


