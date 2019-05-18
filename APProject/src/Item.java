import java.awt.*;
import java.util.ArrayList;

public class Item extends Card {

    private String desc;
    private boolean collectable;
    private static ArrayList<Item> collectableItems = new ArrayList<Item>();
    private ArrayList<Integer[]> cellEffect = new ArrayList<Integer[]>();
    private String effect;

    public ArrayList<Integer[]> getCellEffect() {
        return cellEffect;
    }

    public static ArrayList<Item> getCollectableItems() {
        return collectableItems;
    }

    public static void addCollectableItem(Item item) {
        collectableItems.add(item);
    }

    public Item(String name, int cost, int MP, String desc, boolean collectable, ArrayList<Buff> buffs, String effect) {

        super(name, cost, MP);
        this.desc = desc;
        this.collectable = collectable;
        this.effect = effect;
        if (buffs == null) {
            return;
        }
        for (Buff buff : buffs) {
            this.buffs.add(buff);
        }

    }

    public void setNullCellEffect() {

        for (int i = 0; i < cellEffect.size(); i++) {
            cellEffect.remove(0);
            i = 0;
        }

    }

    public void setRandomCell(String[] commands) {

        if (commands[1].matches("M")) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 5; j++) {
                    if (Main.getCardsCell()[i][j] instanceof Minion) {
                        if (Main.getCardsCell()[i][j].numberOfPlayer == numberOfPlayer) {
                            Integer[] cell = {i, j};
                            cellEffect.add(cell);
                            return;
                        }
                    }
                }
            }
        } else if (commands[1].matches("MH")) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 5; j++) {
                    if (Main.getCardsCell()[i][j] instanceof Minion || Main.getCardsCell()[i][j] instanceof Hero) {
                        if (Main.getCardsCell()[i][j].numberOfPlayer == numberOfPlayer) {
                            Integer[] cell = {i, j};
                            cellEffect.add(cell);
                        }
                    }
                }
            }
        }

    }

    public void setAllMelee() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 5; j++) {
                if (Main.getCardsCell()[i][j] == null || Main.getCardsCell()[i][j] instanceof Item) {
                    continue;
                }
                if (Main.getCardsCell()[i][j].numberOfPlayer != numberOfPlayer) {
                    continue;
                }
                if (Main.getCardsCell()[i][j] instanceof Minion) {
                    if (((Minion) Main.getCardsCell()[i][j]).getClas().matches("melee")) {
                        Integer[] cell = {i, j};
                        cellEffect.add(cell);
                    }
                } else if (Main.getCardsCell()[i][j] instanceof Hero) {
                    if (((Hero) Main.getCardsCell()[i][j]).getClas().matches("melee")) {
                        Integer[] cell = {i, j};
                        cellEffect.add(cell);
                    }
                }
            }
        }
    }

    public void setNotMelee(String[] commands) {

        if (commands[1].matches("MH")) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 5; j++) {
                    if (Main.getCardsCell()[i][j] == null || Main.getCardsCell()[i][j] instanceof Item) {
                        continue;
                    }
                    if (Main.getCardsCell()[i][j].numberOfPlayer != numberOfPlayer) {
                        continue;
                    }
                    if (Main.getCardsCell()[i][j] instanceof Hero) {
                        if (!((Hero) Main.getCardsCell()[i][j]).getClas().matches("melee")) {
                            Integer[] cell = {i, j};
                            cellEffect.add(cell);
                        }
                    } else if (Main.getCardsCell()[i][j] instanceof Minion) {
                        if (!((Minion) Main.getCardsCell()[i][j]).getClas().matches("melee")) {
                            Integer[] cell = {i, j};
                            cellEffect.add(cell);
                        }
                    }
                }
            }
        }

    }

    public void setCellEffect(int x, int y) {

        setNullCellEffect();
        String[] commands = effect.split(" ");
        if (commands[0].matches("random")) {
            setRandomCell(commands);
        }
        if (effect.matches("all melee")) {
            setAllMelee();
        }
        if (commands[0].matches("rangedHybrid")) {
            setNotMelee(commands);
        }

    }

    public String getDesc() {
        return desc;
    }

    public boolean isCollectable() {
        return collectable;
    }

    public Card copyOfCard() {

        ArrayList<Buff> buffs = new ArrayList<Buff>();
        for (Buff buff : this.buffs) {
            buffs.add(buff.copyBuff());
        }
        Item item = new Item(name, cost, MP, desc, collectable, buffs, effect);
        if (cardID != null) {
            item.setCardID(cardID);
        }
        return item;

    }

}
