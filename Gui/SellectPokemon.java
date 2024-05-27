package Gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicBoolean;

public class SellectPokemon {
    Player player;

    public SellectPokemon(Player player) {
        this.player = player;

        JFrame frame = new JFrame("UNITE");
        frame.setSize(1920, 1080);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLayeredPane layeredPane = new JLayeredPane();
        frame.add(layeredPane);

        placeComponents(layeredPane, frame, player);

        frame.setVisible(true);
    }

    public void placeComponents(JLayeredPane layeredPane, JFrame frame, Player player) {
        ImageIcon imageIcon = new ImageIcon("Aset/SellectPokemon.png");
        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setBounds(0, -60, imageIcon.getIconWidth(), imageIcon.getIconHeight());
        layeredPane.add(imageLabel, Integer.valueOf(2));

        ImageIcon wIcon = new ImageIcon("Aset/max3.png");
        JLabel warning = new JLabel(wIcon);
        warning.setBounds(545, 100, 200, 200);
        warning.setVisible(false);
        layeredPane.add(warning, Integer.valueOf(3));

        ImageIcon checked = new ImageIcon("Aset/checked.png");
        ImageIcon unchecked = new ImageIcon("Aset/unchecked.png");

        ArrayList<Monster> monsters = player.getMonsters();
        LinkedList<JLabel> selectBtn = new LinkedList<>();
        LinkedList<JLabel> pokemon = new LinkedList<>();
        ArrayList<Monster> selected = new ArrayList<>();
        LinkedList<AtomicBoolean> s = new LinkedList<>();

        ImageIcon next = new ImageIcon("Aset/next.gif");
        JLabel nxt = new JLabel(next);
        nxt.setBounds(1170, 25, 82, 89);
        nxt.setVisible(false);
        nxt.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                Battle b = new Battle(selected, player);
                b.showExploringPopup();
            }
        });
        layeredPane.add(nxt, Integer.valueOf(3));

        InputStream fontStream = null;
        Font retro = null;
        try {
            fontStream = new FileInputStream("Aset/retro.ttf");
            retro = Font.createFont(Font.TRUETYPE_FONT, fontStream);
            retro = retro.deriveFont(15f);
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
            JLabel select1 = new JLabel();
            s.add(new AtomicBoolean(false));
            select1.setIcon(unchecked);
            select1.setBounds(x + 75, 320, 50, 50);
            select1.setHorizontalAlignment(SwingConstants.CENTER);

            select1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (s.get(monsters.indexOf(m)).get()) {
                        select1.setIcon(unchecked);
                        s.get(monsters.indexOf(m)).set(false);
                        selected.remove(m);
                    } else {
                        if (selected.size() == 3) {
                            warning.setVisible(true);
                            Timer timer = new Timer(1000, new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    warning.setVisible(false);
                                }
                            });
                            timer.setRepeats(false);
                            timer.start();
                        } else {
                            selected.add(monsters.get(monsters.indexOf(m)));
                            select1.setIcon(checked);
                            s.get(monsters.indexOf(m)).set(true);
                        }
                    }

                    if (selected.size() > 0) nxt.setVisible(true);
                    else nxt.setVisible(false);
                }
            });

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
            });

            x += 235;

            selectBtn.add(select1);
            pokemon.add(imageLabel1);
        }

        for (JLabel jl : selectBtn) layeredPane.add(jl, Integer.valueOf(3));
        for (JLabel jl : pokemon) layeredPane.add(jl, Integer.valueOf(3));
    }

    public static void main(String[] args) {
        new SellectPokemon(new Player("Player"));
    }
}

