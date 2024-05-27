package Gui;

import java.util.*;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;

class Player {
    private ArrayList<Monster> monsters;
    private Dungeon dungeon;
    private String nama;
    Scanner scanner = new Scanner(System.in);

    public Player(String nama) {
        this.nama = nama;
        this.monsters = new ArrayList<>();

        monsters.add(new MonsterApi("Charmander"));
        monsters.add(new MonsterAir("Squirtle"));
        monsters.add(new MonsterAngin("Pidgeot"));
        monsters.add(new MonsterEs("Pogo"));
        monsters.add(new MonsterTanah("Swayworm"));

        this.dungeon = new Dungeon(this);
    }

    public Player(String nama, ArrayList<Monster> monsters) {
        this.nama = nama;
        this.monsters = monsters;

        this.dungeon = new Dungeon(this);
    }

    public String getName() {
        return nama;
    }

    public ArrayList<Monster> getMonsters () {
        return monsters;
    }

    public void exploreDungeon() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose monsters to bring to the dungeon:");
        ArrayList<Monster> selectedMonsters = new ArrayList<>();
        ArrayList<Monster> avaiMonster = new ArrayList<>(monsters);

        while (selectedMonsters.size() < 3) {
            System.out.println("Select Monster:");
            int count = 1;
            for (Monster m : avaiMonster) {
                System.out.println(count++ + ". " + m.getName() + " - " + m.getElement() + " (lvl. " + m.getLevel() + ")");
            }
            System.out.print("Select Monster: ");
            int slc = scanner.nextInt();
            if (slc > 0 && slc <= avaiMonster.size()) {
                Monster m = avaiMonster.get(slc-1);
                selectedMonsters.add(m);
                avaiMonster.remove(slc-1);

                System.out.println();
                System.out.println("Selected " + m.getName() + " - " + m.getElement() + " (lvl. " + m.getLevel() + ")");
                System.out.print("Select again (Y/n)? ");

                String sel = scanner.next();
                System.out.println();
                if (sel.equalsIgnoreCase("n")) break;
            } else System.out.println("Input invalid!");
        }
        System.out.println();

        System.out.println("Exploring the dungeon...");

        boolean fight = true;

        while (fight) {
            Random ran = new Random();
            ArrayList<Monster> monsterList = dungeon.getMonster();
            Monster enemyMonster = monsterList.get(ran.nextInt(monsterList.size()));
            System.out.println("ENEMY FOUND!!!");
            System.out.println(enemyMonster.getName() + " - " + enemyMonster.getElement() + " (lvl. " + enemyMonster.getLevel() + ")");
            System.out.println();

            System.out.println("Your Monster:");
            int count = 1;
            for (Monster a : selectedMonsters) {
                System.out.println(count++ + ". " + a.getName() + " - " + a.getElement() + " (lvl. " + a.getLevel() + ")");
            }
            int select = 0;

            while (select < 1) {
                System.out.print("Select your monster: ");
                select = scanner.nextInt();
                if (select < 1 || select > selectedMonsters.size()) {
                    System.out.println("Input tidak valid!");
                }
            }
            System.out.println();

            Monster monster = selectedMonsters.get(select-1);

            dungeon.startBattle(monster, enemyMonster);
            
            if (monster.getHp() > 0) {
                System.out.println(monster.getName() + " survived the dungeon!");
                // Gain XP is already handled in the dungeon battle
            } else {
                System.out.println(monster.getName() + " fainted and was taken back to the home base.");
                monster.setHp(monster.getLevel() * 10);
                selectedMonsters.remove(select-1);
            }

            if (selectedMonsters.size() == 0) {
                System.out.println("All you monsters are defeated");
                System.out.println("Go to base!");
                System.out.println();
                break;
            }

            System.out.print("Continue explore Dungeon? (y/n): ");
            String inp = scanner.next();

            if (inp.equals("n") || inp.equals("N")) {
                fight = false;
            }
        }
    }

    public boolean upgradeMonster(Monster selectedMonster) {
        if (selectedMonster.canLevelUp()) {
            selectedMonster.levelUp();
            return true;
        } else {
            System.out.println(selectedMonster.getName() + " does not have enough XP to level up.");
            System.out.println();
            return false;
        }
    }

    public void saveProgress() {
        try {
          FileWriter myWriter = new FileWriter("save/" + nama + ".txt");
          for (Monster m : monsters) {
            String mons = m.getName() + " " + m.getLevel() + " " + m.getHp() + " " + m.getEp() + " " + m.getElement() + " " + m.getStrengthAgainst() + " " + m.getChanged();
            myWriter.write(mons + "\n");
          }
          myWriter.close();
          System.out.println("Your progress saved");
        } catch (IOException e) {
          System.out.println("An error occurred.");
          e.printStackTrace();
        }
        
        boolean found = false;

        try {
            File myObj = new File("save/user.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
              String data = myReader.nextLine();
              if (data.equals(nama)) found = true;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        if (!found) {
            ArrayList<String> users = new ArrayList<>();
            try {
                File myObj = new File("save/user.txt");
                Scanner myReader = new Scanner(myObj);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    users.add(data);
                }
                myReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
            
            try {
                FileWriter myWriter = new FileWriter("save/user.txt");
                for (String n : users) myWriter.write(n + "\n");
                myWriter.write(nama + "\n");
                myWriter.close();
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
    }

    public void evolution (Monster m, JLabel r, JLabel l, JLabel succ, JLabel ext) {
        int choo = monsters.indexOf(m) + 1;
        String e1 = "";
        String e2 = "";
        
        ImageIcon eves = new ImageIcon("Aset/ev_es.png");
        ImageIcon evair = new ImageIcon("Aset/ev_air.png");
        ImageIcon evangin = new ImageIcon("Aset/ev_angin.png");
        ImageIcon evapi = new ImageIcon("Aset/ev_api.png");
        ImageIcon evtanah = new ImageIcon("Aset/ev_tanah.png");
        
        switch (m.getElement()) {
            case "Air":
                r.setIcon(evangin);
                l.setIcon(eves);
                e1 = "Angin";
                e2 = "Es";
                break;
        
            case "Es":
                r.setIcon(evair);
                l.setIcon(evtanah);
                e1 = "Air";
                e2 = "Tanah";
                break;
    
            case "Tanah":
                r.setIcon(eves);
                l.setIcon(evapi);
                e1 = "Es";
                e2 = "Api";
                break;
    
            case "Api":
                r.setIcon(evtanah);
                l.setIcon(evangin);
                e1 = "Tanah";
                e2 = "Angin";
                break;
    
            case "Angin":
                r.setIcon(evapi);
                l.setIcon(evair);
                e1 = "Api";
                e2 = "Air";
                break;
    
            default:
                break;
        }

        final String e1a = e1;
        final String e2a = e2;

        r.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                switch (e1a) {
                    case "Air":
                        monsters.add(choo-1, new MonsterAir(m));
                        monsters.remove(choo);
                        break;
                
                    case "Es":
                        monsters.add(choo-1, new MonsterEs(m));
                        monsters.remove(choo);
                        break;
            
                    case "Tanah":
                        monsters.add(choo-1, new MonsterTanah(m));
                        monsters.remove(choo);
                        break;
            
                    case "Api":
                        monsters.add(choo-1, new MonsterApi(m));
                        monsters.remove(choo);
                        break;
            
                    case "Angin":
                        monsters.add(choo-1, new MonsterAngin(m));
                        monsters.remove(choo);
                        break;
                }

                System.out.println(monsters.get(choo-1).getName() + "'s element chaned to " + monsters.get(choo-1).getElement());
                System.out.println();

                succ.setVisible(true);
                ext.setVisible(true);
            }
        });

        l.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                switch (e2a) {
                    case "Air":
                        monsters.add(choo-1, new MonsterAir(m));
                        monsters.remove(choo);
                        break;
                
                    case "Es":
                        monsters.add(choo-1, new MonsterEs(m));
                        monsters.remove(choo);
                        break;
            
                    case "Tanah":
                        monsters.add(choo-1, new MonsterTanah(m));
                        monsters.remove(choo);
                        break;
            
                    case "Api":
                        monsters.add(choo-1, new MonsterApi(m));
                        monsters.remove(choo);
                        break;
            
                    case "Angin":
                        monsters.add(choo-1, new MonsterAngin(m));
                        monsters.remove(choo);
                        break;
                }

                System.out.println(monsters.get(choo-1).getName() + "'s element chaned to " + monsters.get(choo-1).getElement());
                System.out.println();

                succ.setVisible(true);
                ext.setVisible(true);
            }
        });
    }

    public void closeScanner() {
        scanner.close();
    }
}