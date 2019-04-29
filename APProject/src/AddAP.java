import java.util.ArrayList;

public class AddAP extends Buff {

    private ArrayList<Card> cards = new ArrayList<Card>();
    private ArrayList<Integer> counterOfAttack = new ArrayList<Integer>();

    public AddAP(boolean mainBuff, boolean allTurnEffect, boolean allTurn, boolean continuous, int turn) {
        super(mainBuff, allTurnEffect, allTurn, continuous, turn);
    }

    public Buff copyBuff() {
        AddAP addAP = new AddAP(mainBuff, allTurnEffect, allTurn, continuous, turn);
        return addAP;
    }

    ;

    public void giveBuffsToCard(ArrayList<Integer[]> cellEffect, int numberOfPlayer) {
        for (int i = 0; i < cellEffect.size(); i++) {
            this.effectBuffsOnCard(Main.getCardsCell()[cellEffect.get(1)[0]][cellEffect.get(i)[1]], numberOfPlayer);
        }
    }

    ;

    public void effectBuffsOnCard(Card card, int numberOfPlayer) {
        if (cards.indexOf(card) != -1) {
            if (card instanceof Minion) {
                ((Minion) card).addHP(-5 * counterOfAttack.get(cards.indexOf(card)));
            } else if (card instanceof Hero) {
                ((Hero) card).addHP(-5 * counterOfAttack.get(cards.indexOf(card)));
            }
            counterOfAttack.add(cards.indexOf(card), counterOfAttack.get(cards.indexOf(card)) + 1);
            counterOfAttack.remove(cards.indexOf(card) + 1);
        } else {
            cards.add(card);
            counterOfAttack.add(1);
        }
    }

    ;

}
