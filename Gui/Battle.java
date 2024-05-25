package Gui;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import javax.sound.sampled.*;

public class Battle {
    public static void main(String[] args) {
        JFrame frame = new JFrame("UNITE");
        frame.setSize(1920, 1080);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLayeredPane layeredPane = new JLayeredPane();
        frame.add(layeredPane);
        placeComponents(layeredPane, frame);
        frame.setVisible(true);

        // Putar musik latar belakang
        playBackgroundMusic("Aset/naruto.wav");
    }

    private static void placeComponents(JLayeredPane layeredPane, JFrame frame) {
        ImageIcon originalIcon = new ImageIcon("Aset/battle.jpg");
        Image originalImage = originalIcon.getImage();
        // Mengubah ukuran gambar sesuai dengan ukuran frame
        Image scaledImage = originalImage.getScaledInstance(1296,729, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel imageLabel = new JLabel(scaledIcon);
        imageLabel.setBounds(0, 0, 1296,729);
        layeredPane.add(imageLabel, Integer.valueOf(0));

        // layeredPane.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight()));

        ImageIcon atkIcon = new ImageIcon("Aset/logokucing.png");
        Image image = atkIcon.getImage(); // Mendapatkan gambar sebagai objek Image
        Image resizedImage = image.getScaledInstance(600, 300, Image.SCALE_SMOOTH); // Mengubah ukuran gambar
        ImageIcon resizedatkIcon = new ImageIcon(resizedImage); // Mengonversi kembali ke ImageIcon
        JLabel atkLabel = new JLabel(resizedatkIcon); // Membuat label dengan gambar yang sudah diresize
        atkLabel.setBounds(616, 279, 400, 600);
        layeredPane.add(atkLabel, Integer.valueOf(2));

        ImageIcon atkIcon2 = new ImageIcon("Aset/17.png");
        Image image2 = atkIcon2.getImage(); // Mendapatkan gambar sebagai objek Image
        Image resizedImage2 = image2.getScaledInstance(600, 300, Image.SCALE_SMOOTH); // Mengubah ukuran gambar
        ImageIcon resizedatkIcon2= new ImageIcon(resizedImage2); // Mengonversi kembali ke ImageIcon
        JLabel atkLabel2 = new JLabel(resizedatkIcon2); // Membuat label dengan gambar yang sudah diresize
        atkLabel2.setBounds(250, 250, 400, 600);
        layeredPane.add(atkLabel2, Integer.valueOf(2));
        
        ImageIcon atkIcon3 = new ImageIcon("Aset/18.png");
        Image image3 = atkIcon3.getImage(); // Mendapatkan gambar sebagai objek Image
        Image resizedImage3 = image3.getScaledInstance(600, 300, Image.SCALE_SMOOTH); // Mengubah ukuran gambar
        ImageIcon resizedatkIcon3= new ImageIcon(resizedImage3); // Mengonversi kembali ke ImageIcon
        JLabel atkLabel3 = new JLabel(resizedatkIcon3); // Membuat label dengan gambar yang sudah diresize
        atkLabel3.setBounds(422, 250, 400, 600);
        layeredPane.add(atkLabel3, Integer.valueOf(2));

        ImageIcon atkIcon4 = new ImageIcon("Aset/19.png");
        Image image4 = atkIcon4.getImage(); // Mendapatkan gambar sebagai objek Image
        Image resizedImage4 = image4.getScaledInstance(600, 300, Image.SCALE_SMOOTH); // Mengubah ukuran gambar
        ImageIcon resizedatkIcon4= new ImageIcon(resizedImage4); // Mengonversi kembali ke ImageIcon
        JLabel atkLabel4 = new JLabel(resizedatkIcon4); // Membuat label dengan gambar yang sudah diresize
        atkLabel4.setBounds(509, 323, 400, 600);
        layeredPane.add(atkLabel4, Integer.valueOf(2));

        ImageIcon atkIcon5 = new ImageIcon("Aset/20.png");
        Image image5 = atkIcon5.getImage(); // Mendapatkan gambar sebagai objek Image
        Image resizedImage5 = image5.getScaledInstance(600, 300, Image.SCALE_SMOOTH); // Mengubah ukuran gambar
        ImageIcon resizedatkIcon5= new ImageIcon(resizedImage5); // Mengonversi kembali ke ImageIcon
        JLabel atkLabel5 = new JLabel(resizedatkIcon5); // Membuat label dengan gambar yang sudah diresize
        atkLabel5.setBounds(512, 323, 400, 600);
        layeredPane.add(atkLabel5, Integer.valueOf(2));

        // ImageIcon atkicon2 = new ImageIcon("Aset/17.png");
        // JLabel atklabel2 = new JLabel(atkicon2);
        // atklabel2.setBounds(0, -60, atkicon2.getIconWidth(),atkicon2.getIconHeight());
        // layeredPane.add(atklabel2, Integer.valueOf(2));

        
    }

    // Method untuk memutar musik latar belakang
    private static void playBackgroundMusic(String musicPath) {
        try {
            File musicFile = new File(musicPath);
            if (musicFile.exists()) {
                System.out.println("Playing sound from: " + musicFile.getAbsolutePath());
                // Menggunakan Java Sound API untuk memutar musik
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(musicFile);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
                clip.loop(Clip.LOOP_CONTINUOUSLY); // Loop the background music
            } else {
                System.out.println("File not found: " + musicFile.getAbsolutePath());
            }
        } catch (UnsupportedAudioFileException e) {
            System.err.println("Unsupported audio file: " + e.getMessage());
        } catch (LineUnavailableException e) {
            System.err.println("Line unavailable: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
