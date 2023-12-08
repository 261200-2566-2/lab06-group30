public interface Characters {
    public void attack(Characters target);
    public void useSkill(Characters target, int slot);
    public void equip(Accessories item);
    public void unEquip(int slot);
    public void displayInfo();
    public void calculate();
}