package Gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;

public class Evolution {
    Player player;

    public Evolution(Player player){
        this.player = player;

        JFrame frame = new JFrame("UNITE");
        // Mengatur ukuran frame
        frame.setSize(1920, 1080);
        // Mengatur aksi default saat frame ditutup
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Membuat instance dari JLayeredPane
        JLayeredPane layeredPane = new JLayeredPane();
        frame.add(layeredPane);

        // Memanggil method placeComponents untuk menambahkan gambar
        placeComponents(layeredPane, frame, player);

        frame.setVisible(true);
    }

    public static void placeComponents(JLayeredPane layeredPane, JFrame frame, Player player) {

        JLabel back = new JLabel("<");
        back.setFont(new Font("Arial", Font.PLAIN, 50));
        back.setForeground(Color.WHITE);
        back.setBounds(20, 20, 50, 50);
        layeredPane.add(back, Integer.valueOf(7));

        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                new Home(player);
            }
        });

        InputStream fontStream2 = null;
        Font retro2 = null;
        try {
            fontStream2 = new FileInputStream("Aset/retro.ttf");
            retro2 = Font.createFont(Font.TRUETYPE_FONT, fontStream2);
            retro2 = retro2.deriveFont(40f); // Mengatur ukuran font menjadi 24
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (FontFormatException | IOException e1) {
            e1.printStackTrace();
        } finally {
            if (fontStream2 != null) {
                try {
                    fontStream2.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }

        ImageIcon evsq = new ImageIcon("Aset/evolve_sq.png");

        ImageIcon imgsuc = new ImageIcon("Aset/ev_success.png");
        JLabel ev_succ = new JLabel(imgsuc);
        ev_succ.setBounds(500, 200, 400, 400);
        ev_succ.setVisible(false);
        layeredPane.add(ev_succ, Integer.valueOf(5));

        JLabel ext = new JLabel("Exit");
        ext.setBounds(650, 400, 100, 100);
        ext.setFont(retro2);
        ext.setVisible(false);
        ext.setForeground(new Color(221, 184, 146));
        layeredPane.add(ext, Integer.valueOf(6));

        ext.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                new Home(player);
            }
        });

        JLabel evosq = new JLabel(evsq);
        evosq.setBounds(500, 200, 400, 400);
        evosq.setVisible(false);
        layeredPane.add(evosq, Integer.valueOf(3));

        JLabel evori = new JLabel();
        evori.setBounds(530, 270, 160, 300);
        evori.setVisible(false);
        layeredPane.add(evori, Integer.valueOf(4));

        JLabel evole = new JLabel();
        evole.setBounds(710, 270, 160, 300);
        evole.setVisible(false);
        layeredPane.add(evole, Integer.valueOf(4));

        ImageIcon imageIcon = new ImageIcon("Aset/SellectPokemon.png");
        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setBounds(0, -60, imageIcon.getIconWidth(), imageIcon.getIconHeight());
        layeredPane.add(imageLabel, Integer.valueOf(2));
        
        ImageIcon wIcon = new ImageIcon("Aset/max3.png");
        JLabel warning = new JLabel(wIcon);
        warning.setBounds(545, 100, 200, 200);
        warning.setVisible(false);
        layeredPane.add(warning, Integer.valueOf(3));
        
        ArrayList<Monster> monsters = player.getMonsters();
        LinkedList<JLabel> pokemon = new LinkedList<>();
        ArrayList<Monster> selected = new ArrayList<>();

        InputStream fontStream = null;
        Font retro = null;
        try {
            fontStream = new FileInputStream("Aset/retro.ttf");
            retro = Font.createFont(Font.TRUETYPE_FONT, fontStream);
            retro = retro.deriveFont(15f); // Mengatur ukuran font menjadi 24
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (FontFormatException | IOException e1) {
            e1.printStackTrace();
        } finally {
            if (fontStream != null) {
                try {
                    fontStream.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }

        JLabel nameLevel = new JLabel("-");
        nameLevel.setFont(retro);
        nameLevel.setBounds(110, 140, 300, 40);
        layeredPane.add(nameLevel, Integer.valueOf(3));

        JLabel hp = new JLabel("HP = -");
        hp.setFont(retro);
        hp.setBounds(110, 165, 300, 40);
        layeredPane.add(hp, Integer.valueOf(3));

        JLabel xp = new JLabel("XP = -");
        xp.setFont(retro);
        xp.setBounds(110, 190, 300, 40);
        layeredPane.add(xp, Integer.valueOf(3));

        JLabel strengthAgainst = new JLabel("Strength Against -");
        strengthAgainst.setFont(retro);
        strengthAgainst.setBounds(110, 215, 300, 40);
        layeredPane.add(strengthAgainst, Integer.valueOf(3));

        int x = 75;
        ImageIcon ppr = new ImageIcon("Aset/paper.png");
        JLabel description = new JLabel(ppr);
        description.setBounds(75, 100, 400, 200);
        layeredPane.add(description, Integer.valueOf(3));

        for (Monster m : monsters) {
            if (m.getChanged()) continue;
    
            ImageIcon imageIcon1 = new ImageIcon("Aset/" + m.getGif());
            System.out.println(m.getGif());
            JLabel imageLabel1 = new JLabel(imageIcon1);
            imageLabel1.setBounds(x, 330, imageIcon1.getIconWidth(), imageIcon1.getIconHeight());
            imageLabel1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    String el = "";
                    String sa = "";

                    switch (m.getElement()) {
                        case "Es":
                            el = "Ice";
                            break;
                    
                        case "Air":
                            el = "Water";
                            break;
                
                        case "Api":
                            el = "Fire";
                            break;
                
                        case "Tanah":
                            el = "Earth";
                            break;
                
                        case "Angin":
                            el = "Wind";
                            break;
                    
                        default:
                            break;
                    }

                    switch (m.getStrengthAgainst()) {
                        case "Es":
                            sa = "Ice";
                            break;
                    
                        case "Air":
                            sa = "Water";
                            break;
                
                        case "Api":
                            sa = "Fire";
                            break;
                
                        case "Tanah":
                            sa = "Earth";
                            break;
                
                        case "Angin":
                            sa = "Wind";
                            break;
                    
                        default:
                            break;
                    }

                    nameLevel.setText(m.getName() + " - " + el + " (Lvl." + m.getLevel() + ")");
                    hp.setText("HP = " + m.getHp());
                    xp.setText("XP = " + m.getEp());
                    strengthAgainst.setText("Strength Against " + sa);
                }

                @Override
                public void mouseClicked(MouseEvent e) {
                    evosq.setVisible(true);
                    evori.setVisible(true);
                    evole.setVisible(true);
                    player.evolution(m, evori, evole, ev_succ, ext);
                }
            });

            x += 235;

            pokemon.add(imageLabel1);
        }

        for (JLabel jl : pokemon) layeredPane.add(jl, Integer.valueOf(3));
    }
}