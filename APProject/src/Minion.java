public class Minion extends Card {

    private int HP;
    private int AP;
    private int attackRange;
    private String clas;
    private boolean canCounterAttack;
    private boolean onOrOf;

    public Minion(String name, String clas, int cost, int MP, int HP, int AP, int attackRange) {
        super(name, cost, MP);
        this.clas = clas;
        this.HP = HP;
        this.AP = AP;
        this.attackRange = attackRange;
        this.canCounterAttack = true;
        this.onOrOf = true;
    }

    public void addHP(int HP) {
        this.HP += HP;
    }

    public void addAP(int AP) {
        this.AP += AP;
    }

    public int getHP() {
        return HP;
    }

    public int getAP() {
        return AP;
    }

    public boolean isOnOrOf() {
        return onOrOf;
    }

    public void setOnOrOf(boolean onOrOf) {
        this.onOrOf = onOrOf;
    }

    public void setCanCounterAttack(boolean canCounterAttack) {
        this.canCounterAttack = canCounterAttack;
    }

}
