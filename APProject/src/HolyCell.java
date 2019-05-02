import java.util.ArrayList;

public class HolyCell extends Buff {

    public HolyCell(int turn) {

        super(false, false, false, false, turn);
        posOrNeg = false;

    }

    public Buff copyBuff() {
        HolyCell holyCell = new HolyCell(turn);
        return holyCell;
    }

    public void giveBuffsToCard(ArrayList<Integer[]> cellEffect, int numberOfPlayer) {

    }

    public void effectBuffsOnCard(Card card, int numberOfPlayer) {

    }

}
