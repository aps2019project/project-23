import java.util.ArrayList;

public class Power extends Buff {

    private int addAP;
    private int addHealth;

    public Power(boolean mainBuff, boolean allTurnEffect, boolean allTurn, boolean continuous, int turn, int addAP, int addHealth) {
        super(mainBuff, allTurnEffect, allTurn, continuous, turn);
        this.addAP = addAP;
        this.addHealth = addHealth;
        positive = true;
        posOrNeg = true;
    }

    public Buff copyBuff() {
        Power power = new Power(mainBuff, allTurnEffect, allTurn, continuous, turn, addAP, addHealth);
        return power;
    }

    public void giveBuffsToCard(ArrayList<Integer[]> cellEffect, int numberOfPlayer) {
        for (int i = 0; i < cellEffect.size(); i++) {
            Main.getCardsCell()[cellEffect.get(i)[0]][cellEffect.get(i)[1]].addBuff(this.copyBuff());
            this.effectBuffsOnCard(Main.getCardsCell()[cellEffect.get(i)[0]][cellEffect.get(i)[1]], numberOfPlayer);
        }
    }

    public int getAddHealth() {
        return addHealth;
    }

    public int getAddAP() {
        return addAP;
    }

    public void effectBuffsOnCard(Card card, int numberOfPlayer) {
        if (card instanceof Hero) {
            if (addHealth != 0) {
                ((Hero) card).addHP(addHealth);
            } else if (addAP != 0) {
                ((Hero) card).addAP(addAP);
            }
        } else if (card instanceof Minion) {
            if (addHealth != 0) {
                ((Minion) card).addHP(addHealth);
            } else if (addAP != 0) {
                ((Minion) card).addAP(addAP);
            }
        }
    }

}
