import java.util.ArrayList;

public class AddMP extends Buff {

    private int addMP;

    public AddMP(int turn, int addMP) {

        super(false, true, false, false, turn);
        this.posOrNeg = false;
        this.addMP = addMP;

    }

    public Buff copyBuff() {

        AddMP addMP = new AddMP(turn, this.addMP);
        return addMP;

    }

    public void giveBuffsToCard(ArrayList<Integer[]> cellEffect, int numberOfPlayer) {

    }

    public void effectBuffsOnCard(Card card, int numberOfPlayer) {

    }

}
