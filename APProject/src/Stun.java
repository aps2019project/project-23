import java.util.ArrayList;

public class Stun extends Buff {

    public Stun(boolean mainBuff, boolean allTurnEffect, boolean allTurn, boolean continuous, int turn) {
        super(mainBuff, allTurnEffect, allTurn, continuous, turn);
        positive = false;
        posOrNeg = true;
    }

    public Buff copyBuff() {
        Stun stun = new Stun(mainBuff, allTurnEffect, allTurn, continuous, turn);
        return stun;
    }

    public void giveBuffsToCard(ArrayList<Integer[]> cellEffect, int numberOfPlayer) {
        for (int i = 0; i < cellEffect.size(); i++) {
            Main.getCardsCell()[cellEffect.get(i)[0]][cellEffect.get(i)[1]].addBuff(this.copyBuff());
            this.effectBuffsOnCard(Main.getCardsCell()[cellEffect.get(i)[0]][cellEffect.get(i)[1]], numberOfPlayer);
        }
    }

    public void effectBuffsOnCard(Card card, int numberOfPlayer) {
        if (card instanceof Hero) {
            ((Hero) card).setOnOrOf(false);
        } else if (card instanceof Minion) {
            ((Minion) card).setOnOrOf(false);
        }
    }

}
