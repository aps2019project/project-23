import java.util.ArrayList;

public class Poison extends Buff {

    private boolean isKnock;
    private boolean isCell;
    private int addHealth;
    private int turnInCell;

    public Poison(boolean mainBuff, boolean allTurnEffect, boolean allTurn, boolean continuous, boolean isKnock, boolean isCell, int turn, int turnInCell, int addHealth) {
        super(mainBuff, allTurnEffect, allTurn, continuous, turn);
        this.addHealth = addHealth;
        this.isKnock = isKnock;
        this.isCell = isCell;
        this.turnInCell = turnInCell;
        positive = false;
        posOrNeg = true;
    }

    public Buff copyBuff() {
        Poison poison = new Poison(mainBuff, allTurnEffect, allTurn, continuous, isKnock, isCell, turn, turnInCell, addHealth);
        return poison;
    }

    public void giveBuffsToCard(ArrayList<Integer[]> cellEffect, int numberOfPlayer) {
        for (int i = 0; i < cellEffect.size(); i++) {
            Main.getCardsCell()[cellEffect.get(i)[0]][cellEffect.get(i)[1]].addBuff(this.copyBuff());
            this.effectBuffsOnCard(Main.getCardsCell()[cellEffect.get(i)[0]][cellEffect.get(i)[1]], numberOfPlayer);
        }
    }

    public void effectBuffsOnCard(Card card, int numberOfPlayer) {
    }

}
