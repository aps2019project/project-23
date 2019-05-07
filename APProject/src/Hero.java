import java.lang.reflect.Array;
import java.util.ArrayList;

public class Hero extends Card {

    private int HP;
    private int AP;
    private int attackRange;
    private int cooldown;
    private String clas;
    private boolean canCounterAttack;
    private boolean onOrOf;
    private String specialPower;
    private ArrayList<Buff> specialBuff = new ArrayList<Buff>();
    private String targetCommunity;
    private boolean usable;
    private ArrayList<Integer[]> cellEffect = new ArrayList<Integer[]>();
    private boolean onAttack;
    private boolean haveBuff;

    public Hero(String name, String clas, int cost, int MP, int HP, int AP, int attackRange, int cooldown, String specialPower, ArrayList<Buff> buffs, String targetCommunity, boolean usable, boolean onAttack, boolean haveBuff) {
        super(name, cost, MP);
        this.clas = clas;
        this.cooldown = cooldown;
        this.AP = AP;
        this.attackRange = attackRange;
        this.HP = HP;
        this.onOrOf = true;
        this.specialPower = specialPower;
        if (!haveBuff) {
            if (buffs != null) {
                for (Buff buff : buffs)
                    this.specialBuff.add(buff);
            }
        } else {
            for (Buff buff : buffs) {
                this.buffs.add(buff);
            }
        }
        this.targetCommunity = targetCommunity;
        this.usable = usable;
        this.onAttack = onAttack;
        this.haveBuff = haveBuff;

    }

    public void addHP(int HP) {
        this.HP += HP;
    }

    public void addAP(int AP) {
        this.AP += AP;
    }

    public int getHP() {
        return HP;
    }

    public int getAP() {
        return AP;
    }

    public String getClas() {
        return clas;
    }

    public String getSpecialPower() {
        return specialPower;
    }

    public boolean isOnOrOf() {
        return onOrOf;
    }

    public void setOnOrOf(boolean onOrOf) {
        this.onOrOf = onOrOf;
    }

    public void setCanCounterAttack(boolean canCounterAttack) {
        this.canCounterAttack = canCounterAttack;
    }

    public Card copyOfCard() {

        ArrayList<Buff> buffs = new ArrayList<Buff>();
        for (Buff buff : specialBuff) {
            buffs.add(buff.copyBuff());
        }
        Hero hero = new Hero(name, clas, cost, MP, HP, AP, attackRange, cooldown, specialPower, buffs, targetCommunity, usable, onAttack, haveBuff);
        if (cardID != null) {
            hero.setCardID(cardID);
        }
        return hero;

    }

    public void setNullEffect() {
        for (int i = 0; i < cellEffect.size(); i++) {
            cellEffect.remove(0);
        }
    }

    public boolean setAll(String[] targetCommunities) {

        if (targetCommunities[1].matches("enemy")) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 5; j++) {
                    if (Main.getCardsCell()[i][j].numberOfPlayer != this.numberOfPlayer) {
                        Integer[] cell = {i, j};
                        cellEffect.add(cell);
                    }
                }
            }
        }
        return true;

    }

    public boolean oneOf(String[] targetCommunities, int x, int y) {

        if (targetCommunities[1].matches("enemy")) {
            if (Main.getCardsCell()[x][y].numberOfPlayer != this.numberOfPlayer) {
                Integer[] cell = {x, y};
                cellEffect.add(cell);
                return true;
            }
        }
        return false;

    }

    public boolean setRow(int x, int y, String[] targetCommunities) {

        if (!(Main.getCardsCell()[x][y] instanceof Hero))
            return false;
        if (Main.getCardsCell()[x][y].numberOfPlayer != this.numberOfPlayer)
            return false;
        for (int i = 0; i < 9; i++) {
            Integer[] cell = {i, y};
            cellEffect.add(cell);
        }
        return true;

    }

    public boolean setCellEffect(int x, int y) {

        setNullEffect();
        String[] targetCommunities = targetCommunity.split(" ");
        if (targetCommunities[0].matches("hero")) {
            if (!(Main.getCardsCell()[x][y] instanceof Hero))
                return false;
            else {
                Integer[] cell = {x, y};
                cellEffect.add(cell);
            }
        } else if (targetCommunities[0].matches("all")) {
            return setAll(targetCommunities);
        } else if (targetCommunities[0].matches("one")) {
            return oneOf(targetCommunities, x, y);
        } else if (targetCommunities[0].matches("cell")) {
            Integer[] cell = {x, y};
            cellEffect.add(cell);
            return true;
        } else if (targetCommunities[0].matches("row")) {
            return setRow(x, y, targetCommunities);
        }
        return true;

    }


}
