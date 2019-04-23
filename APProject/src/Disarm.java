import java.util.ArrayList;

public class Disarm extends Buff {

    public Disarm(boolean mainBuff, boolean allTurnEffect, boolean allTurn, boolean continuous, int turn) {
        super(mainBuff, allTurnEffect, allTurn, continuous, turn);
        positive = false;
        posOrNeg = true;
    }

    public Buff copyBuff() {
        Disarm disarm = new Disarm(mainBuff, allTurnEffect, allTurn, continuous, turn);
        return disarm;
    }

    public void giveBuffsToCard(ArrayList<Integer[]> cellEffect, int numberOfPlayer) {
    }

    public void effectBuffsOnCard(Card card, int numberOfPlayer) {
    }

}

