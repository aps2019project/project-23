import java.util.ArrayList;

public class Card {

    protected String name;
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

    public void setNumberOfPlayer(int numberOfPlayer) {
        this.numberOfPlayer = numberOfPlayer;
    }

}