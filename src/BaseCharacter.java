public class BaseCharacter implements Characters {
    //int[] {lv, hp, mp, atk, mAtk, def, mDef, spd};
    protected int[] stats;
    protected int[] baseStats;
    protected int cHp, cMp;
    protected Accessories[] accessoriesSlot;
    protected String name;
    protected String job;
    protected boolean isDead;
    private enum Acs {
        Ring, Necklace
    }

    public BaseCharacter(String name, String job, int[] baseStats) {
        this.name = name;
        this.job = job;
        this.baseStats = baseStats;
        stats = new int[8];
        accessoriesSlot = new Accessories[2];
        calculateStats(1);
        cHp = stats[1];
        cMp = stats[2];
    }

    private void calculateStats(int lv) {
        int[] accessoryStats = new int[8];
        for(Accessories a : accessoriesSlot) {
            if(a != null) {
                for(int i = 1; i < stats.length; i++) accessoryStats[i] += a.getStat()[i];
            }
        }

        stats[0] = lv; //level
        stats[1] = (int)(baseStats[1] + (5 * stats[0])) + accessoryStats[1]; //Hp
        stats[2] = (int)(baseStats[2] + (0.5 * stats[0])) + accessoryStats[1]; //Mp
        stats[3] = (int)(baseStats[3] + (1.5 * stats[0])) + accessoryStats[1]; //Atk
        stats[4] = (int)(baseStats[4] + (0.2 * stats[0])) + accessoryStats[1]; //MAtk
        stats[5] = (int)(baseStats[5] + (0.5 * stats[0])) + accessoryStats[1]; //Def
        stats[6] = (int)(baseStats[6] + (0.2 * stats[0])) + accessoryStats[1]; //MDef
        stats[7] = baseStats[7] + accessoryStats[1]; //Spd
    }

    public void setLevel(int lv) {
        calculateStats(lv);
        cHp = stats[1];
        cMp = stats[2];
    }

    //temporary set stats for buff
    public void setHp(int hp) {
        stats[1] = hp;
    }
    public void setMp(int mp) {
        stats[2] = mp;
    }
    public void setAtk(int atk) {
        stats[3] = atk;
    }
    public void setMAtk(int matk) {
        stats[4] = matk;
    }
    public void setDef(int def) {
        stats[5] = def;
    }
    public void setMDef(int mdef) {
        stats[6] = mdef;
    }
    public void setSpd(int spd) {
        stats[7] = spd;
    }
    public void setcHp(int hp) {
        cHp = hp;
    }
    public void setcMp(int mp) {
        cMp = mp;
    }

    public void takeDamage(int atkDamage, String attackType) {
        int damage;
        if(attackType.equals("atk")) damage = atkDamage - stats[5];
        else if (attackType.equals("matk")) damage = atkDamage - stats[6];
        else damage = atkDamage - stats[5];

        if(damage >= cHp) {
            cHp = 0;
            isDead = true;
        }
        else if(damage < 0) {
            return;
        }
        else {
            cHp -= damage;
        }
    }

    @Override
    public void attack(Characters target) {
        int atkDamage;
        String attackType;
        if(job.equals("Warrior")) {
            attackType = "atk";
            atkDamage = stats[3]; //Atk
        }
        else if(job.equals("Mage")) {
            attackType = "matk";
            atkDamage = stats[4]; //MAtk
        }
        else {
            attackType = "atk";
            atkDamage = stats[3]; //Defualt is Atk
        }

        if (target instanceof Warrior t) { //have to downcast
            t.takeDamage(atkDamage, attackType);
        }
        else if (target instanceof Mage t) {
            t.takeDamage(atkDamage, attackType);
        }
    }

    @Override
    public void useSkill(Characters target, int slot) {
        System.out.println("Skill Issue");
    }

    @Override
    public void equip(Accessories item) {
        String type = item.getType();
        for(Acs i : Acs.values()) { //check all types in enum list
            if(i.name().equals(type)) {
                if(accessoriesSlot[i.ordinal()] != null) unEquip(i.ordinal()); //swap
                accessoriesSlot[i.ordinal()] = item;
                accessoriesSlot[i.ordinal()].setEquiper(this);
            }
        }
        // int[] stat = item.getStat();
        // for(int i = 1; i < stats.length; i++) {
        //     this.stats[i] += stat[i];
        // }
        calculateStats(stats[0]);
    }

    @Override
    public void unEquip(int slot) {
        if(slot > accessoriesSlot.length) {
            System.out.println("Out of slot");
            return;
        }
        // int[] stat = accessoriesSlot[slot-1].getStat();
        // for(int i = 1; i < stats.length; i++) {
        //     this.stats[i] -= stat[i];
        // }
        accessoriesSlot[slot-1].setEquiper(null);
        accessoriesSlot[slot-1] = null;
        calculateStats(stats[0]);
    }

    @Override
    public void displayInfo() {
        //calculateStats(stats[0]);
        System.out.println();
        System.out.println("Name : " + name);
        System.out.println("Level : " + stats[0]);
        System.out.println("HP : " + cHp + "/" + stats[1]);
        System.out.println("MP : " + cMp + "/" + stats[2]);
        System.out.println("ATK : " + stats[3]);
        System.out.println("MATK : " + stats[4]);
        System.out.println("DEF : " + stats[5]);
        System.out.println("MDEF : " + stats[6]);
        System.out.println("SPD : " + stats[7]);
        System.out.print("Accessories : ");
        for (Accessories a : accessoriesSlot) {
            if(a != null) System.out.print("[Lv. " + a.getStat()[0] + " (" + a.getName() + ")] ");
            else System.out.print("[None] ");
        }
        System.out.println();
    }

    @Override
    public void calculate() {
        calculateStats(stats[0]);
    }
}