import java.util.ArrayList;

abstract public class Card {

    protected String name;
    protected String cardID = "";
    protected int cost;
    protected int MP;
    protected int numberOfPlayer;
    protected ArrayList<Buff> buffs = new ArrayList<Buff>();
    protected boolean move = false;
    protected boolean attack = false;
    private ArrayList<Item> flags = new ArrayList<Item>();

    public ArrayList<Item> getFlags() {
        return flags;
    }

    public void addFlag(Item item) {
        flags.add(item);
    }

    public boolean isAttack() {
        return attack;
    }

    public ArrayList<Buff> getBuffs() {
        return buffs;
    }

    public boolean isMove() {
        return move;
    }

    public void setAttack(boolean attack) {
        this.attack = attack;
    }

    public void setMove(boolean move) {
        this.move = move;
    }

    public void addBuff(Buff buff) {
        buffs.add(buff);
    }

    Card(String name, int cost, int MP) {
        this.name = name;
        this.cost = cost;
        this.MP = MP;
    }

    public void addMP(int MP) {
        this.MP += MP;
    }

    public void setCardID(String cardID) {
        this.cardID = cardID;
    }

    public void setNumberOfPlayer(int numberOfPlayer) {
        this.numberOfPlayer = numberOfPlayer;
    }

    public abstract Card copyOfCard();

}