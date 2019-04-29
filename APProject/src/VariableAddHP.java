import java.util.ArrayList;

public class VariableAddHP extends Buff {

    private ArrayList<Integer> addHP = new ArrayList<Integer>();

    public VariableAddHP(boolean allTurnEffect, boolean allTurn, boolean continuous, int turn , ArrayList<Integer> addHP){

        super(false,allTurnEffect,allTurn,continuous,turn);
        for ( int i = 0 ; i < addHP.size() ; i ++ ) {
            this.addHP.add(addHP.get(i));
        }

    }

    public Buff copyBuff() {
        VariableAddHP variableAddHP = new VariableAddHP(allTurnEffect, allTurn, continuous, turn, addHP);
        return variableAddHP;
    }

    public void giveBuffsToCard(ArrayList<Integer[]> cellEffect, int numberOfPlayer) {
    }

    public void effectBuffsOnCard(Card card, int numberOfPlayer) {
    }

}
