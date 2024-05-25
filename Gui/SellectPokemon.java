package Gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.atomic.AtomicBoolean;
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

        ImageIcon checked = new ImageIcon("Aset/checked.png");
        ImageIcon unchecked = new ImageIcon("Aset/unchecked.png");
        
        JLabel select1 = new JLabel();
        AtomicBoolean s1 = new AtomicBoolean(false);
        select1.setIcon(unchecked);
        select1.setBounds(150, 280, 50, 50);
        select1.setHorizontalAlignment(SwingConstants.CENTER);
        layeredPane.add(select1, Integer.valueOf(3));

        select1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (s1.get()) {
                    s1.set(false);;
                    select1.setIcon(checked);
                } else {
                    s1.set(true);
                    select1.setIcon(unchecked);
                }
            }
        });

        ImageIcon imageIcon1 = new ImageIcon("Aset/_angin_.gif");
        JLabel imageLabel1 = new JLabel(imageIcon1);
        imageLabel1.setBounds(75, 330, imageIcon1.getIconWidth(), imageIcon1.getIconHeight());
        layeredPane.add(imageLabel1, Integer.valueOf(3));
        
        JLabel select2 = new JLabel();
        AtomicBoolean s2 = new AtomicBoolean(false);
        select2.setIcon(unchecked);
        select2.setBounds(385, 280, 50, 50);
        select2.setHorizontalAlignment(SwingConstants.CENTER);
        layeredPane.add(select2, Integer.valueOf(3));

        select2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (s2.get()) {
                    s2.set(false);;
                    select2.setIcon(checked);
                } else {
                    s2.set(true);
                    select2.setIcon(unchecked);
                }
            }
        });
        
        ImageIcon imageIcon2 = new ImageIcon("Aset/_es_.gif");
        JLabel imageLabel2 = new JLabel(imageIcon2);
        imageLabel2.setBounds(310, 330, imageIcon2.getIconWidth(), imageIcon2.getIconHeight());
        layeredPane.add(imageLabel2, Integer.valueOf(3));
        
        JLabel select3 = new JLabel();
        AtomicBoolean s3 = new AtomicBoolean(false);
        select3.setIcon(unchecked);
        select3.setBounds(620, 280, 50, 50);
        select3.setHorizontalAlignment(SwingConstants.CENTER);
        layeredPane.add(select3, Integer.valueOf(3));

        select3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (s3.get()) {
                    s3.set(false);;
                    select3.setIcon(checked);
                } else {
                    s3.set(true);
                    select3.setIcon(unchecked);
                }
            }
        });

        ImageIcon imageIcon3 = new ImageIcon("Aset/_api_.gif");
        JLabel imageLabel3 = new JLabel(imageIcon3);
        imageLabel3.setBounds(545, 330, imageIcon3.getIconWidth(), imageIcon3.getIconHeight());
        layeredPane.add(imageLabel3, Integer.valueOf(3));
        
        JLabel select4 = new JLabel();
        AtomicBoolean s4 = new AtomicBoolean(false);
        select4.setIcon(unchecked);
        select4.setBounds(855, 280, 50, 50);
        select4.setHorizontalAlignment(SwingConstants.CENTER);
        layeredPane.add(select4, Integer.valueOf(3));

        select4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (s4.get()) {
                    s4.set(false);;
                    select4.setIcon(checked);
                } else {
                    s4.set(true);
                    select4.setIcon(unchecked);
                }
            }
        });

        ImageIcon imageIcon4 = new ImageIcon("Aset/_air_.gif");
        JLabel imageLabel4 = new JLabel(imageIcon4);
        imageLabel4.setBounds(780, 330, imageIcon4.getIconWidth(), imageIcon4.getIconHeight());
        layeredPane.add(imageLabel4, Integer.valueOf(4));
        
        JLabel select5 = new JLabel();
        AtomicBoolean s5 = new AtomicBoolean(false);
        select5.setIcon(unchecked);
        select5.setBounds(1090, 280, 50, 50);
        select5.setHorizontalAlignment(SwingConstants.CENTER);
        layeredPane.add(select5, Integer.valueOf(3));

        select5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (s5.get()) {
                    s5.set(false);;
                    select5.setIcon(checked);
                } else {
                    s5.set(true);
                    select5.setIcon(unchecked);
                }
            }
        });

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


