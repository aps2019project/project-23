import java.util.ArrayList;

public class Delete extends Buff {

    public Delete(boolean mainBuff, boolean allTurnEffect, boolean allTurn, boolean continuous, int turn) {
        super(mainBuff, allTurnEffect, allTurn, continuous, turn);
        posOrNeg = false;
    }

    public Buff copyBuff() {
        Delete delete = new Delete(mainBuff, allTurnEffect, allTurn, continuous, turn);
        return delete;
    }

    public void giveBuffsToCard(ArrayList<Integer[]> cellEffect, int numberOfPlayer) {
        for (int i = 0; i < cellEffect.size(); i++) {
            this.effectBuffsOnCard(Main.getCardsCell()[cellEffect.get(i)[0]][cellEffect.get(i)[1]], numberOfPlayer);
        }
    }

    public void effectBuffsOnCard(Card card, int numberOfPlayer) {
        if (card.numberOfPlayer == numberOfPlayer) {
            for (int i = 0 ; i < card.buffs.size() ; i ++ ) {
                if (!card.buffs.get(i).positive && card.buffs.get(i).mainBuff && card.buffs.get(i).posOrNeg)
                    card.buffs.remove(i);
            }
        } else {
            for (int i = 0 ; i < card.buffs.size() ; i ++ ) {
                if (card.buffs.get(i).positive && card.buffs.get(i).mainBuff && card.buffs.get(i).posOrNeg)
                    card.buffs.remove(i);
            }
        }
    }

}
