import java.util.ArrayList;

public class AddMP extends Buff {

    private ArrayList<Integer> addMP = new ArrayList<Integer>();
    private boolean allTurnAddMP;

    public AddMP(int turn, ArrayList<Integer> addMP, boolean allTurnAddMP) {

        super(false, true, false, false, turn);
        this.posOrNeg = false;
        for (int i = 0; i < addMP.size(); i++) {
            this.addMP.add(addMP.get(i));
        }
        this.allTurnAddMP = allTurnAddMP;

    }

    public Buff copyBuff() {
        AddMP addMP = new AddMP(turn, this.addMP, allTurnAddMP);
        return addMP;
    }

    public void giveBuffsToCard(ArrayList<Integer[]> cellEffect, int numberOfPlayer) {

    }

    public void effectBuffsOnCard(Card card, int numberOfPlayer) {

    }

}
