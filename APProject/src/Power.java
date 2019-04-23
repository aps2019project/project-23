import java.util.ArrayList;

public class Power extends Buff {

    private int addAP;
    private int addHealth;

    public Power(boolean mainBuff,boolean allTurnEffect,boolean allTurn , boolean continuous , int turn , int addAP , int addHealth){
        super(mainBuff,allTurnEffect,allTurn,continuous,turn);
        this.addAP = addAP;
        this.addHealth = addHealth;
        positive = true;
        posOrNeg = true;
    }

    public Buff copyBuff(){
        Power power = new Power(mainBuff,allTurnEffect,allTurn,continuous,turn,addAP,addHealth);
        return power;
    }

    public void giveBuffsToCard(ArrayList<Integer[]> cellEffect, int numberOfPlayer) {
    }

    public void effectBuffsOnCard(Card card, int numberOfPlayer) {
    }

}
