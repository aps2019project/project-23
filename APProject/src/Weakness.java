import java.util.ArrayList;

public class Weakness extends Buff {

    private int addHealth;
    private int addAP;
    private int totalOfAdding;

    public void addTotalOfAdding(int number) {
        totalOfAdding += number;
    }

    public int getTotalOfAdding() {
        return totalOfAdding;
    }

    public int getAddAP() {
        return addAP;
    }

    public int getAddHealth() {
        return addHealth;
    }

    public Weakness(boolean mainBuff, boolean allTurnEffect, boolean allTurn, boolean continuous, int turn, int addHealth, int addAP) {
        super(mainBuff, allTurnEffect, allTurn, continuous, turn);
        this.addHealth = addHealth;
        this.addAP = addAP;
        positive = false;
        posOrNeg = true;
    }

    public Buff copyBuff() {
        Weakness weakness = new Weakness(mainBuff, allTurnEffect, allTurn, continuous, turn, addHealth, addAP);
        return weakness;
    }

    public void giveBuffsToCard(ArrayList<Integer[]> cellEffect, int numberOfPlayer) {
        for (int i = 0; i < cellEffect.size(); i++) {
            Main.getCardsCell()[cellEffect.get(i)[0]][cellEffect.get(i)[1]].addBuff(this.copyBuff());
            this.effectBuffsOnCard(Main.getCardsCell()[cellEffect.get(i)[0]][cellEffect.get(i)[1]], numberOfPlayer);
        }
    }

    public void effectBuffsOnCard(Card card, int numberOfPlayer) {
        if (addAP != 0) {
            if (card instanceof Minion) {
                ((Minion) card).addAP(addAP);
                addTotalOfAdding(addAP);
            } else if (card instanceof Hero) {
                ((Hero) card).addAP(addAP);
                addTotalOfAdding(addAP);
            }
        } else if (addHealth != 0) {
            if (card instanceof Minion) {
                ((Minion) card).addHP(addHealth);
                addTotalOfAdding(addHealth);
            } else if (card instanceof Hero) {
                ((Hero) card).addHP(addHealth);
                addTotalOfAdding(addHealth);
            }
        }
    }

}
