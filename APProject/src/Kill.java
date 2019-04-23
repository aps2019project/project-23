import java.util.ArrayList;

public class Kill extends Buff {

    public Kill(){
        super(false,false,false,false,1);
    }

    public Buff copyBuff(){
        Kill kill = new Kill();
        return kill;
    }

    public void giveBuffsToCard(ArrayList<Integer[]> cellEffect, int numberOfPlayer) {
    }

    public void effectBuffsOnCard(Card card, int numberOfPlayer) {
    }

}
