import java.util.ArrayList;

public class Fire extends Buff {

    private int addHealth;
    private int turnInCell;

    public Fire(boolean mainBuff, boolean allTurnEffect, boolean allTurn, boolean continuous, int turn, int turnInCell, int addHealth) {
        super(mainBuff, allTurnEffect, allTurn, continuous, turn);
        posOrNeg = false;
        this.addHealth = addHealth;
        this.turnInCell = turnInCell;
    }

    public Buff copyBuff() {
        Fire fire = new Fire(mainBuff, allTurnEffect, allTurn, continuous, turn, turnInCell, addHealth);
        return fire;
    }

    public void giveBuffsToCard(ArrayList<Integer[]> cellEffect, int numberOfPlayer) {
        for (int i = 0; i < cellEffect.size(); i++) {
            Main.getBuffCell()[cellEffect.get(i)[0]][cellEffect.get(i)[1]] = this.copyBuff();
        }
    }

    public void effectBuffsOnCard(Card card, int numberOfPlayer) {
        if (card instanceof Hero) {
            ((Hero) card).addHP(addHealth);
        } else if (card instanceof Minion) {
            ((Minion) card).addHP(addHealth);
        }
    }

}
