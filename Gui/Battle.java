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
import java.util.Random;

public class Battle {
    Player player;
    private ArrayList<Monster> selected;
    private Monster enemy;
    private Monster playerMonster;

    public Battle(ArrayList<Monster> selected, Player player) {
        this.selected = selected;
        this.player = player;
    }

    public Battle(ArrayList<Monster> selected, Monster playerMonster, Monster enemy, Player player) {
        this.selected = selected;
        this.player = player;
        this.playerMonster = playerMonster;
        this.enemy = enemy;

        JFrame frame = new JFrame("UNITE");
        frame.setSize(1920, 1080);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLayeredPane layeredPane = new JLayeredPane();
        frame.add(layeredPane);

        placeComponents(layeredPane, frame);
        frame.setVisible(true);

        // Play background music
        BackgroundMusicManager.getInstance().playBackgroundMusic("Aset/naruto.wav");
    }

    public void showExploringPopup() {
        JDialog dialog = new JDialog();
        dialog.setSize(300, 200);
        dialog.setLocationRelativeTo(null);
        dialog.setLayout(new BorderLayout());
        JLabel label = new JLabel("Exploring dungeon...", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        dialog.add(label, BorderLayout.CENTER);
        dialog.setUndecorated(true);
        dialog.setModal(true);

        Timer timer = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
                showEnemyFoundPopup();
            }
        });
        timer.setRepeats(false);
        timer.start();
        dialog.setVisible(true);
    }

    private void showEnemyFoundPopup() {
        JDialog dialog = new JDialog();
        dialog.setSize(300, 200);
        dialog.setLocationRelativeTo(null);
        dialog.setLayout(new BorderLayout());
        JLabel label = new JLabel("Enemy found!", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        dialog.add(label, BorderLayout.CENTER);
        dialog.setUndecorated(true);
        dialog.setModal(true);

        Timer timer = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
                showSelectPokemonForBattlePopup();
            }
        });
        timer.setRepeats(false);
        timer.start();
        dialog.setVisible(true);
    }

    private void showSelectPokemonForBattlePopup() {
        JDialog dialog = new JDialog();
        dialog.setSize(400, 650);  // Adjust size to fit images
        dialog.setLocationRelativeTo(null);
        dialog.setLayout(new BorderLayout());
        JPanel panel = new JPanel(new GridLayout(0, 1));
        
        for (Monster monster : selected) {
            JPanel pokemonPanel = new JPanel(new BorderLayout());
            
            // Adding Pokemon image
            ImageIcon pokemonIcon = new ImageIcon("Aset/" + monster.getGif());
            JLabel pokemonLabel = new JLabel(pokemonIcon);
            pokemonPanel.add(pokemonLabel, BorderLayout.WEST);
            
            // Adding Pokemon name and level
            JLabel nameLabel = new JLabel(monster.getName() + " (Lvl." + monster.getLevel() + ")", SwingConstants.CENTER);
            pokemonPanel.add(nameLabel, BorderLayout.CENTER);
            
            // Adding select button
            JButton selectButton = new JButton("Select");
            selectButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dialog.dispose();
                    ArrayList<Monster> selectedMonster = new ArrayList<>();
                    selectedMonster.add(monster);
                    
                    Random ran = new Random();
                    ArrayList<Monster> monsterList = player.getEnemyMonsters();
                    Monster enemyMonster = monsterList.get(ran.nextInt(monsterList.size()));

                    new Battle(selected, monster, enemyMonster, player);
                }
            });
            pokemonPanel.add(selectButton, BorderLayout.EAST);
            
            panel.add(pokemonPanel);
        }
        
        dialog.add(panel, BorderLayout.CENTER);
        dialog.setVisible(true);
    }

    private void placeComponents(JLayeredPane layeredPane, JFrame frame) {
        Random random = new Random();

        InputStream fontStream = null;
        Font retro = null;
        try {
            fontStream = new FileInputStream("Aset/retro.ttf");
            retro = Font.createFont(Font.TRUETYPE_FONT, fontStream);
            retro = retro.deriveFont(20f); // Mengatur ukuran font menjadi 24
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

        JLabel defeeat = new JLabel("You Defeated");
        defeeat.setOpaque(true);
        defeeat.setBackground(Color.GREEN);
        defeeat.setBounds(200, 200, 700, 170);
        defeeat.setForeground(Color.BLACK);
        defeeat.setHorizontalAlignment(SwingConstants.CENTER);
        defeeat.setVerticalAlignment(SwingConstants.TOP);
        defeeat.setVisible(false);
        layeredPane.add(defeeat, Integer.valueOf(5));

        JLabel exit = new JLabel("Exit");
        exit.setOpaque(true);
        exit.setBackground(Color.BLACK);
        exit.setBounds(220, 260, 80, 60);
        exit.setForeground(Color.GREEN);
        exit.setVisible(false);
        layeredPane.add(exit, Integer.valueOf(6));

        exit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new Home(player);
            }
        });

        JLabel next = new JLabel("Next");
        next.setOpaque(true);
        next.setBackground(Color.BLACK);
        next.setBounds(760, 260, 80, 60);
        next.setForeground(Color.GREEN);
        next.setVisible(false);
        layeredPane.add(next, Integer.valueOf(6));

        next.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                Battle b = new Battle(selected, player);
                b.showExploringPopup();
            }
        });
        
        JLabel plHP = new JLabel("HP: " + playerMonster.getHp());
        plHP.setFont(retro);
        plHP.setForeground(Color.WHITE);
        plHP.setBounds(10, 25, 300, 50);
        layeredPane.add(plHP, Integer.valueOf(3));

        JLabel enHP = new JLabel("HP: " + enemy.getHp());
        enHP.setFont(retro);
        enHP.setForeground(Color.WHITE);
        enHP.setBounds(940, 25, 300, 50);
        layeredPane.add(enHP, Integer.valueOf(3));

        ImageIcon originalIcon = new ImageIcon("Aset/battle.jpg");
        Image originalImage = originalIcon.getImage();
        // Mengubah ukuran gambar sesuai dengan ukuran frame
        Image scaledImage = originalImage.getScaledInstance(1296,729, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel imageLabel = new JLabel(scaledIcon);
        imageLabel.setBounds(0, 0, 1296,729);
        layeredPane.add(imageLabel, Integer.valueOf(0));
        
        //nama hero kita
        JLabel pokemon = new JLabel(playerMonster.getName() + " - " + playerMonster.getElement());
        pokemon.setBackground(null);
        pokemon.setBounds(20,-290,400,600);
        pokemon.setFont(new Font("Arial", Font.ITALIC,20));
        layeredPane.add(pokemon, Integer.valueOf(2));
        pokemon.setForeground(Color.WHITE);

        ImageIcon BarHp = new ImageIcon("Aset/hp_border.png");
        Image bar2 = BarHp.getImage(); // Mendapatkan gambar sebagai objek Image
        Image resizedBarHp = bar2.getScaledInstance(350, 52, Image.SCALE_SMOOTH); // Mengubah ukuran gambar
        ImageIcon resizedBarHpIcon= new ImageIcon(resizedBarHp); // Mengonversi kembali ke ImageIcon
        JLabel BarHpLabel = new JLabel(resizedBarHpIcon); // Membuat label dengan gambar yang sudah diresize
        BarHpLabel.setBounds(-178, 0, 700, 103);
        layeredPane.add(BarHpLabel, Integer.valueOf(2));

        //nama hero musuh 
        JLabel pokemon2 = new JLabel(enemy.getName() + " - " + enemy.getElement());
        pokemon2.setBackground(null);
        pokemon2.setBounds(950,-290,400,600);
        pokemon2.setFont(new Font("Arial", Font.ITALIC,20));
        layeredPane.add(pokemon2, Integer.valueOf(2));
        pokemon2.setForeground(Color.WHITE);
        //bar HP musuh
        ImageIcon BarHp2 = new ImageIcon("Aset/hp_border.png");
        Image bar3 = BarHp2.getImage(); // Mendapatkan gambar sebagai objek Image
        Image resizedBarHp2 = bar3.getScaledInstance(350, 52, Image.SCALE_SMOOTH); // Mengubah ukuran gambar
        ImageIcon resizedBarHpIcon2= new ImageIcon(resizedBarHp2); // Mengonversi kembali ke ImageIcon
        JLabel BarHpLabel2 = new JLabel(resizedBarHpIcon2); // Membuat label dengan gambar yang sudah diresize
        BarHpLabel2.setBounds(750, 0, 700, 103);
        layeredPane.add(BarHpLabel2, Integer.valueOf(2));

        ImageIcon atkIcon2 = new ImageIcon("Aset/healbg.png");
        Image image2 = atkIcon2.getImage(); // Mendapatkan gambar sebagai objek Image
        Image resizedImage2 = image2.getScaledInstance(100, 75, Image.SCALE_SMOOTH); // Mengubah ukuran gambar
        ImageIcon resizedatkIcon2= new ImageIcon(resizedImage2); // Mengonversi kembali ke ImageIcon
        JLabel atkLabel2 = new JLabel(resizedatkIcon2); // Membuat label dengan gambar yang sudah diresize
        atkLabel2.setBounds(250 + 130, 424 + 130, 100, 75);
        layeredPane.add(atkLabel2, Integer.valueOf(2));

        
        ImageIcon atkIcon3 = new ImageIcon("Aset/kaburbg.png");
        Image image3 = atkIcon3.getImage(); // Mendapatkan gambar sebagai objek Image
        Image resizedImage3 = image3.getScaledInstance(100, 75, Image.SCALE_SMOOTH); // Mengubah ukuran gambar
        ImageIcon resizedatkIcon3= new ImageIcon(resizedImage3); // Mengonversi kembali ke ImageIcon
        JLabel flee = new JLabel(resizedatkIcon3); // Membuat label dengan gambar yang sudah diresize
        flee.setBounds(336 + 130, 425 + 130, 100, 75);
        layeredPane.add(flee, Integer.valueOf(2));

        ImageIcon atkIcon = new ImageIcon("Aset/elementbg.png");
        Image image = atkIcon.getImage(); // Mendapatkan gambar sebagai objek Image
        Image resizedImage = image.getScaledInstance(100, 75, Image.SCALE_SMOOTH); // Mengubah ukuran gambar
        ImageIcon resizedatkIcon = new ImageIcon(resizedImage); // Mengonversi kembali ke ImageIcon
        JLabel elementalAtk = new JLabel(resizedatkIcon); // Membuat label dengan gambar yang sudah diresize
        elementalAtk.setBounds(616 + 130, 422 + 130, 100, 75);
        layeredPane.add(elementalAtk, Integer.valueOf(2));

        elementalAtk.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                playerMonster.elementalAttack(enemy);
                plHP.setText("HP: " + Math.max(0, playerMonster.getHp()));
                enHP.setText("HP: " + Math.max(0, enemy.getHp()));
                
                plHP.setText("HP: " + playerMonster.getHp());
                if (enemy.getHp() <= 0) {
                    defeeat.setText("You Win!");
                    defeeat.setVisible(true);
                    exit.setVisible(true);
                    next.setVisible(true);
                }
                
                int enemyAtt = random.nextInt(3) + 1;
            
                switch (enemyAtt) {
                    case 1:
                        enemy.basicAttack(playerMonster);
                        break;
                
                    case 2:
                    enemy.specialAttack(playerMonster);
                    break;
            
                    case 3:
                    enemy.elementalAttack(playerMonster);
                    break;
            
                    default:
                        break;
                }

                plHP.setText("HP: " + Math.max(0, playerMonster.getHp()));
                enHP.setText("HP: " + Math.max(0, enemy.getHp()));
                if (playerMonster.getHp() <= 0) {
                    System.out.println(selected == null);
                    selected.remove(playerMonster);

                    if (selected.size() == 0) {
                        defeeat.setText("You Lose! No Monster left");
                        defeeat.setVisible(true);
                        exit.setVisible(true);
                    } else {
                        defeeat.setText("You Lose!");
                        defeeat.setVisible(true);
                        exit.setVisible(true);
                        next.setVisible(true);
                    }
                }
            }
        });

        ImageIcon atkIcon4 = new ImageIcon("Aset/pedangBg.png");
        Image image4 = atkIcon4.getImage(); // Mendapatkan gambar sebagai objek Image
        Image resizedImage4 = image4.getScaledInstance(100, 75, Image.SCALE_SMOOTH); // Mengubah ukuran gambar
        ImageIcon resizedatkIcon4= new ImageIcon(resizedImage4); // Mengonversi kembali ke ImageIcon
        JLabel basicAtk = new JLabel(resizedatkIcon4); // Membuat label dengan gambar yang sudah diresize
        basicAtk.setBounds(523 + 130, 422 + 130, 100, 75);
        layeredPane.add(basicAtk, Integer.valueOf(3));

        basicAtk.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                playerMonster.basicAttack(enemy);

                plHP.setText("HP: " + Math.max(0, playerMonster.getHp()));
                enHP.setText("HP: " + Math.max(0, enemy.getHp()));
                
                plHP.setText("HP: " + playerMonster.getHp());
                if (enemy.getHp() <= 0) {
                    defeeat.setText("You Win!");
                    defeeat.setVisible(true);
                    exit.setVisible(true);
                    next.setVisible(true);
                }
                
                int enemyAtt = random.nextInt(3) + 1;
            
                switch (enemyAtt) {
                    case 1:
                        enemy.basicAttack(playerMonster);
                        break;
                
                    case 2:
                    enemy.specialAttack(playerMonster);
                    break;
            
                    case 3:
                    enemy.elementalAttack(playerMonster);
                    break;
            
                    default:
                        break;
                }

                plHP.setText("HP: " + Math.max(0, playerMonster.getHp()));
                enHP.setText("HP: " + Math.max(0, enemy.getHp()));
                if (playerMonster.getHp() <= 0) {
                    System.out.println(selected == null);
                    selected.remove(playerMonster);

                    if (selected.size() == 0) {
                        defeeat.setText("You Lose! No Monster left");
                        defeeat.setVisible(true);
                        exit.setVisible(true);
                    } else {
                        defeeat.setText("You Lose!");
                        defeeat.setVisible(true);
                        exit.setVisible(true);
                        next.setVisible(true);
                    }
                }
            }
        });

        ImageIcon atkIcon5 = new ImageIcon("Aset/spessAtbg.png");
        Image image5 = atkIcon5.getImage(); // Mendapatkan
        Image scaledImage5 = image5.getScaledInstance(100, 75, Image.SCALE_SMOOTH); // Mengubah ukuran gambar
        ImageIcon resizedatkIcon5= new ImageIcon(scaledImage5); // Mengonversi kembali ke ImageIcon
        JLabel specialAtk = new JLabel(resizedatkIcon5); // Membuat label dengan gambar yang sudah diresize
        specialAtk.setBounds(429 + 120,423 + 130,130, 75);
        layeredPane.add(specialAtk, Integer.valueOf(3));
        
        specialAtk.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                playerMonster.specialAttack(enemy);

                plHP.setText("HP: " + Math.max(0, playerMonster.getHp()));
                enHP.setText("HP: " + Math.max(0, enemy.getHp()));
                
                plHP.setText("HP: " + playerMonster.getHp());
                if (enemy.getHp() <= 0) {
                    defeeat.setText("You Win!");
                    defeeat.setVisible(true);
                    exit.setVisible(true);
                    next.setVisible(true);
                }
                
                int enemyAtt = random.nextInt(3) + 1;
            
                switch (enemyAtt) {
                    case 1:
                        enemy.basicAttack(playerMonster);
                        break;
                
                    case 2:
                    enemy.specialAttack(playerMonster);
                    break;
            
                    case 3:
                    enemy.elementalAttack(playerMonster);
                    break;
            
                    default:
                        break;
                }

                plHP.setText("HP: " + Math.max(0, playerMonster.getHp()));
                enHP.setText("HP: " + Math.max(0, enemy.getHp()));
                if (playerMonster.getHp() <= 0) {
                    System.out.println(selected == null);
                    
                    selected.remove(playerMonster);

                    if (selected.size() == 0) {
                        defeeat.setText("You Lose! No Monster left");
                        defeeat.setVisible(true);
                        exit.setVisible(true);
                    } else {
                        defeeat.setText("You Lose!");
                        defeeat.setVisible(true);
                        exit.setVisible(true);
                        next.setVisible(true);
                    }
                }
            }
        });

        ImageIcon monPlayerImg = new ImageIcon("Aset/" + playerMonster.getGif());
        JLabel monPlayer = new JLabel(monPlayerImg);
        monPlayer.setBounds(100, 300, 200, 300);
        layeredPane.add(monPlayer, Integer.valueOf(7));

        ImageIcon monEnemyImg = new ImageIcon("Aset/" + enemy.getGif());
        JLabel monEnemy = new JLabel(monEnemyImg);
        monEnemy.setBounds(900, 300, 200, 300);
        layeredPane.add(monEnemy, Integer.valueOf(7));
    }
}