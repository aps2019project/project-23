import java.util.ArrayList;

public class UnHoly extends Buff {

    private int addAPForHoly;

    public UnHoly(boolean allTurnEffect, boolean allTurn, boolean continuous, int turn, int addAPForHoly) {

        super(false, allTurnEffect, allTurn, continuous, turn);
        posOrNeg = false;
        this.addAPForHoly = addAPForHoly;

    }

    public Buff copyBuff() {
        UnHoly unHoly = new UnHoly(allTurnEffect, allTurn, continuous, turn, addAPForHoly);
        return unHoly;
    }

    public void giveBuffsToCard(ArrayList<Integer[]> cellEffect, int numberOfPlayer) {
    }

    public void effectBuffsOnCard(Card card, int numberOfPlayer) {
    }

}
