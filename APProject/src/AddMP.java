import java.util.ArrayList;

public class AddMP extends Buff {

    private ArrayList<Integer> addMP = new ArrayList<Integer>();

    public AddMP(int turn, ArrayList<Integer> addMP) {

        super(false, true, false, false, turn);
        this.posOrNeg = false;
        for ( int i = 0 ; i < addMP.size() ; i ++ ) {
            this.addMP.add(addMP.get(i));
        }

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
