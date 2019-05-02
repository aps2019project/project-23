import java.util.ArrayList;

abstract public class Buff {

    protected int turn;
    protected boolean allTurn;
    protected boolean allTurnEffect;
    protected boolean continuous;
    protected boolean positive;
    protected boolean posOrNeg;
    protected boolean mainBuff;
    protected boolean isOn;

    public boolean isPositive() {
        return positive;
    }

    public boolean isPosOrNeg() {
        return posOrNeg;
    }

    public boolean isAllTurn() {
        return allTurn;
    }

    public boolean isContinuous() {
        return continuous;
    }

    public boolean isMainBuff() {
        return mainBuff;
    }

    public int getTurn() {
        return turn;
    }

    public Buff(boolean mainBuff, boolean allTurnEffect, boolean allTurn, boolean continuous, int turn) {
        this.allTurn = allTurn;
        this.mainBuff = mainBuff;
        this.continuous = continuous;
        this.turn = turn;
        this.allTurnEffect = allTurnEffect;
        this.isOn = true;
    }

    public void setOn(boolean on) {
        isOn = on;
    }

    public abstract Buff copyBuff();

    public abstract void giveBuffsToCard(ArrayList<Integer[]> cellEffect, int numberOfPlayer);

    public abstract void effectBuffsOnCard(Card card, int numberOfPlayer);

}
