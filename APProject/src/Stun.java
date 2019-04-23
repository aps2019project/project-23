import java.util.ArrayList;

public class Stun extends Buff {

    public Stun( boolean mainBuff,boolean allTurnEffect,boolean allTurn , boolean continuous , int turn){
        super(mainBuff,allTurnEffect, allTurn,continuous,turn);
        positive = false;
        posOrNeg = true;
    }

    public Buff copyBuff(){
        Stun stun = new Stun(mainBuff,allTurnEffect,allTurn,continuous,turn);
        return stun;
    }

    public void giveBuffsToCard(ArrayList<Integer[]> cellEffect, int numberOfPlayer) {
    }

    public void effectBuffsOnCard(Card card, int numberOfPlayer) {
    }

}
