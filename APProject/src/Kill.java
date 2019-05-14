import java.util.ArrayList;

public class Kill extends Buff {

    public Kill() {
        super(false, false, false, false, 1);
        posOrNeg = false;
    }

    public Buff copyBuff() {
        Kill kill = new Kill();
        return kill;
    }

    public void giveBuffsToCard(ArrayList<Integer[]> cellEffect, int numberOfPlayer) {
        for (int i = 0; i < cellEffect.size(); i++) {
            this.effectBuffsOnCard(Main.getCardsCell()[cellEffect.get(i)[0]][cellEffect.get(i)[1]], numberOfPlayer);
        }
    }

    public void effectBuffsOnCard(Card card, int numberOfPlayer) {
        if (card instanceof Hero) {
            ((Hero) card).setHP(0);
        } else if (card instanceof Minion) {
            ((Minion) card).setHP(0);
        }
    }

}
