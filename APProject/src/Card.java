import java.util.ArrayList;

abstract public class Card {

    protected String name;
    protected String cardID;
    protected int cost;
    protected int MP;
    protected int numberOfPlayer;
    protected ArrayList<Buff> buffs = new ArrayList<Buff>();

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