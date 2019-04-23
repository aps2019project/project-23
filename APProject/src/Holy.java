import java.util.ArrayList;

public class Holy extends Buff {

    public Holy( boolean mainBuff,boolean allTurnEffect,boolean allTurn , boolean continuous , int turn){
        super(mainBuff,allTurnEffect, allTurn,continuous,turn);
        positive = true;
        posOrNeg = true;
    }

    public Buff copyBuff(){
        Holy holy = new Holy(mainBuff,allTurnEffect,allTurn,continuous,turn);
        return holy;
    }

    public void giveBuffsToCard(ArrayList<Integer[]> cellEffect, int numberOfPlayer) {
    }

    public void effectBuffsOnCard(Card card, int numberOfPlayer) {
    }

}
