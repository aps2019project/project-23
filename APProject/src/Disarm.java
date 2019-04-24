import java.util.ArrayList;

public class Disarm extends Buff {

    public Disarm(boolean mainBuff, boolean allTurnEffect, boolean allTurn, boolean continuous, int turn) {
        super(mainBuff, allTurnEffect, allTurn, continuous, turn);
        positive = false;
        posOrNeg = true;
    }

    public Buff copyBuff() {
        Disarm disarm = new Disarm(mainBuff, allTurnEffect, allTurn, continuous, turn);
        return disarm;
    }

    public void giveBuffsToCard(ArrayList<Integer[]> cellEffect, int numberOfPlayer) {
        for (int i = 0 ; i < cellEffect.size() ; i ++ ){
            Main.getCardsCell()[cellEffect.get(i)[0]][cellEffect.get(i)[1]].addBuff(this.copyBuff());
            this.effectBuffsOnCard(Main.getCardsCell()[cellEffect.get(i)[0]][cellEffect.get(i)[1]],numberOfPlayer);
        }
    }

    public void effectBuffsOnCard(Card card, int numberOfPlayer) {
        if (card instanceof Hero){
            ((Hero) card).setCanCounterAttack(false);
        }
        else if ( card instanceof Minion){
            ((Minion) card).setCanCounterAttack(false);
        }
    }

}

