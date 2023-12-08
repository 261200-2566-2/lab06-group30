public class Warrior extends BaseCharacter {
    //int[] {lv, hp, mp, atk, mAtk, def, mDef, spd};
    static int[] baseStats = new int[]{1, 100, 50, 20, 5, 10, 5, 5};
    public Warrior(String name) {
        super(name, "Warrior", baseStats);
    }
    @Override
    public void useSkill(Characters target, int slot) {
        if(target != null) {
            if(slot == 1) { // heal hp
                int heal = (int)(20 + (5 * stats[0]));
                if(cMp >= 20) {
                    if(target == this) {
                        cHp += heal;
                        if(cHp >= stats[1]) cHp = stats[1];
                    }
                    else {
                        if (target instanceof Warrior t) { //have to downcast
                            t.cHp += heal;
                            if(t.cHp >= t.stats[1]) t.cHp = t.stats[1];
                        }
                        else if (target instanceof Mage t) {
                            t.cHp += heal;
                            if(t.cHp >= t.stats[1]) t.cHp = t.stats[1];
                        }
                    }
                    cMp -= 20;
                }
                else System.out.println("MP is not enough");
            }
            else;
            //TODO add more skills
        }
        else {
            System.out.println("Skill or Target not found");
        }
    }
}