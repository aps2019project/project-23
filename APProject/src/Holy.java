import java.util.ArrayList;

public class Holy extends Buff {

    public Holy(boolean mainBuff, boolean allTurnEffect, boolean allTurn, boolean continuous, int turn) {
        super(mainBuff, allTurnEffect, allTurn, continuous, turn);
        positive = true;
        posOrNeg = true;
    }

    public Buff copyBuff() {
        Holy holy = new Holy(mainBuff, allTurnEffect, allTurn, continuous, turn);
        return holy;
    }

    public void giveBuffsToCard(ArrayList<Integer[]> cellEffect, int numberOfPlayer) {
        for (int i = 0; i < cellEffect.size(); i++) {
            Main.getCardsCell()[cellEffect.get(i)[0]][cellEffect.get(i)[1]].addBuff(this.copyBuff());
        }
    }

    public void effectBuffsOnCard(Card card, int numberOfPlayer) {
        if (card instanceof Hero) {
            ((Hero) card).addHP(1);
        } else if (card instanceof Minion) {
            ((Minion) card).addHP(1);
        }
    }

}
