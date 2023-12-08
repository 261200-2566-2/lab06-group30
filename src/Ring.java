import java.util.Random;

public class Ring implements Accessories {
    private int[] stats;
    private final String type;
    private int durability;
    private int upgraded;
    private Characters equiper;
    private String name;

    public Ring(String name, int[] baseStats) {
        this.stats = baseStats;
        this.type = "Ring";
        this.durability = 5;
        this.upgraded = 0;
        this.name = name;
    }

    public int getUpgraded() {
        return upgraded;
    }

    @Override
    public void setEquiper(Characters equiper) {
        this.equiper = equiper;
    }

    @Override
    public void upgrade() {
        if(durability <= 0) {
            System.out.println();
            System.out.println("Durability is not enough");
            return;
        }
        Random rand = new Random();
        int randed = rand.nextInt(99) + 1;
        int rng = (int)(80 * Math.pow(Math.exp(1), (-0.1 * upgraded)));
        if(randed <= rng && rng > 0) {
            for (int i = 1; i < stats.length; i++) {
                if(stats[i] > 0)stats[i] += 2;
            }
            upgraded++;
            name = type + " +" + upgraded;
            System.out.println();
            System.out.println("+" + upgraded + " Upgrade Success!!!");
            if(equiper != null) equiper.calculate();
        }
        else {
            durability--;
            System.out.println();
            System.out.println("Upgrade Fail~");
        }
    }

    @Override
    public void displayInfo() {
        String[] statsNames = {
            "Level : ",
            "HP : ",
            "MP : ",
            "ATK : ",
            "MATK : ",
            "DEF : ",
            "MDEF : ",
            "SPD : "
        };
        System.out.println();
        System.out.println("Name : " + name);
        for(int i = 0; i < stats.length; i++) {
            if(stats[i] > 0) {
                System.out.println(statsNames[i] + stats[i]);
            }
        }
        System.out.println("Durability : " + durability);
    }

    @Override
    public int[] getStat() {
        return stats;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getName() {
        return name;
    }
}
