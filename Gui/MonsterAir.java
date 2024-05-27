package Gui;

public class MonsterAir extends Monster {
    MonsterAir(String name) {
        super(name, "Air", "Api", "_air2_.gif");  // Water is strong against Fire
    }
    
    MonsterAir(String name, int level) {
        super(name, "Air", "Api", level, "_air2_.gif");  // Water is strong against Fire
    }
    
    MonsterAir(String name, int level, String gif) {
        super(name, "Air", "Api", level, gif);  // Water is strong against Fire
    }

    MonsterAir(String name, int level, int hp, int ep, boolean changed) {
        super(name, level, hp, ep, "Air", "Api", changed, "_air2_.gif");
    }

    MonsterAir(Monster a) {
        super(a.getName(), a.getLevel(), a.getHp(), a.getEp(), "Air", "Api", true, "_air2_.gif");
    }

    @Override
    public void basicAttack(Monster enemy) {
        System.out.println(getName() + " BASIC ATTACK to " + enemy.getName() + "!");
        int damage = getLevel() * 2;
        enemy.setHp(enemy.getHp() - damage);
        System.out.println(enemy.getName() + " takes " + damage + " damage.");
    }

    @Override
    public void specialAttack(Monster enemy) {
        System.out.println(getName() + " HYDRO PUMP to " + enemy.getName() + ".");
        int damage = getLevel() * 3;
        enemy.setHp(enemy.getHp() - damage);
        System.out.println(enemy.getName() + " takes " + damage + " damage.");
    }

    @Override
    public void elementalAttack(Monster enemy) {
        System.out.println(getName() + " TSUNAMI to " + enemy.getName() + ".");
        String enemyElement = enemy.getElement();

        if (enemyElement.equals(getStrengthAgainst())) {
            System.out.println("It's super effective!");
            int damage = getLevel() * 4;
            enemy.setHp(enemy.getHp() - damage);
            System.out.println(enemy.getName() + " takes " + damage + " damage.");
        } else {
            int damage = getLevel() * 2;
            enemy.setHp(enemy.getHp() - damage);
            System.out.println(enemy.getName() + " takes " + damage + " damage.");
        }
    }
}
