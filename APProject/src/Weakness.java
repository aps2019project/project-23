import java.util.ArrayList;

public class Weakness extends Buff {

    private int addHealth;
    private int addAP;

    public Weakness( boolean mainBuff,boolean allTurnEffect,boolean allTurn , boolean continuous , int turn , int addHealth , int addAP){
        super(mainBuff,allTurnEffect, allTurn,continuous,turn);
        this.addHealth = addHealth;
        this.addAP = addAP;
        positive = false;
        posOrNeg = true;
    }

    public Buff copyBuff(){
        Weakness weakness = new Weakness(mainBuff,allTurnEffect,allTurn,continuous,turn,addHealth,addAP);
        return weakness;
    }

    public void giveBuffsToCard(ArrayList<Integer[]> cellEffect, int numberOfPlayer) {
    }

    public void effectBuffsOnCard(Card card, int numberOfPlayer) {
    }

}
