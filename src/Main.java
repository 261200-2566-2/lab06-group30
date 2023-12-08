public class Main {
    public static void main(String[] args) {
        Characters A = new Warrior("A");
        //Characters B = new Mage("B");
        Accessories ring = new Ring("All 10 Ring", new int[] {10, 10, 10, 10, 10, 10, 10, 10}); //int[] {lv, hp, mp, atk, mAtk, def, mDef, spd};
        Accessories necklace = new Necklace("Power Necklace", new int[] {15, 40, 0, 20, 0, 5, 0, 0});

        // A.attack(B); //21 - 5 = 16
        // B.displayInfo(); //hp 85 => 69

        // A.useSkill(B, 1); //heal 25 hp
        // A.displayInfo(); //mp 50 => 30
        // B.displayInfo(); //hp 69 => 85(max)

        A.displayInfo();
        ring.displayInfo();
        necklace.displayInfo();

        A.equip(ring);
        A.displayInfo();

        A.equip(necklace);
        A.displayInfo();

        necklace.upgrade();
        necklace.displayInfo();
        A.displayInfo();

        A.unEquip(1);
        A.unEquip(2);
        A.displayInfo();
    }
}