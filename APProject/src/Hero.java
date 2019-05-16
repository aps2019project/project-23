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
    private boolean onSpecialBuff;

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
        this.canCounterAttack = true;
        onSpecialBuff = true;

    }

<<<<<<< Updated upstream
=======
    public boolean isOnSpecialBuff() {
        return onSpecialBuff;
    }

    public void setOnSpecialBuff(boolean onSpecialBuff) {
        this.onSpecialBuff = onSpecialBuff;
    }

>>>>>>> Stashed changes
    public int getCooldown() {
        return cooldown;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public void addSpecialBuff(Buff buff) {
        specialBuff.add(buff);
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

    public int getAttackRange() {
        return attackRange;
    }

    public boolean isCanCounterAttack() {
        return canCounterAttack;
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
        for (Buff buff : this.buffs) {
            hero.addBuff(buff);
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
                    if (Main.getCardsCell()[i][j] == null){
                        continue;
                    }
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
            if (Main.getCardsCell()[x][y] != null && Main.getCardsCell()[x][y].numberOfPlayer != this.numberOfPlayer) {
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

    public void attack(int x, int y) {

        int counterOfHoly = 0;
        Card card = Main.getCardsCell()[x][y];
        int xOfAttacker = GameController.getLocation(this.cardID)[0];
        int yOfAttacker = GameController.getLocation(this.cardID)[1];
        if (onAttack) {
            for (Buff buff : specialBuff) {
                card.addBuff(buff);
                buff.effectBuffsOnCard(card, numberOfPlayer);
            }
        }
        for (Buff buff : card.getBuffs()) {
            if (buff instanceof Holy) {
                counterOfHoly++;
            }
        }
        if (card instanceof Hero) {
            if (((Hero) card).getHP() <= this.AP - counterOfHoly) {
                ((Hero) card).setHP(0);
            } else {
                ((Hero) card).addHP(-1 * this.AP + counterOfHoly);
            }
        } else if (card instanceof Minion) {
            if (((Minion) card).getHP() <= this.AP - counterOfHoly) {
                ((Minion) card).setHP(0);
            } else {
                ((Minion) card).addHP(-1 * this.AP + counterOfHoly);
            }
        }

        if (card instanceof Minion) {
            if (((Minion) card).getClas().matches("melee")) {
                if (Math.abs(x - xOfAttacker) > 1 || Math.abs(y - yOfAttacker) > 1) {
                    if (((Minion) card).getSpecialPower().matches("defend")) {
                        ((Minion) card).defendPower(this);
                    }
                    return;
                }
            } else if (((Minion) card).getClas().matches("ranged")) {
                if ((Math.abs(x - xOfAttacker) < 2 && Math.abs(y - yOfAttacker) < 2) || Math.abs(x - xOfAttacker) > ((Minion) card).getAttackRange() || Math.abs(y - yOfAttacker) > ((Minion) card).getAttackRange()) {
                    if (((Minion) card).getSpecialPower().matches("defend")) {
                        ((Minion) card).defendPower(this);
                    }
                    return;
                }
            } else if (((Minion) card).getClas().matches("hybrid")) {
                if (Math.abs(x - xOfAttacker) > ((Minion) card).getAttackRange() || Math.abs(y - yOfAttacker) > ((Minion) card).getAttackRange()) {
                    if (((Minion) card).getSpecialPower().matches("defend")) {
                        ((Minion) card).defendPower(this);
                    }
                    return;
                }
            }
        } else if (card instanceof Hero) {
            if (((Hero) card).getClas().matches("melee")) {
                if (Math.abs(x - xOfAttacker) > 1 || Math.abs(y - yOfAttacker) > 1) {
                    return;
                }
            } else if (((Hero) card).getClas().matches("ranged")) {
                if ((Math.abs(x - xOfAttacker) < 2 && Math.abs(y - yOfAttacker) < 2) || Math.abs(x - xOfAttacker) > ((Hero) card).getAttackRange() || Math.abs(y - yOfAttacker) > ((Hero) card).getAttackRange()) {
                    return;
                }
            } else if (((Hero) card).getClas().matches("hybrid")) {
                if (Math.abs(x - xOfAttacker) > ((Hero) card).getAttackRange() || Math.abs(y - yOfAttacker) > ((Hero) card).getAttackRange()) {
                    return;
                }
            }
        }

        if (card instanceof Hero) {
            if (!((Hero) card).isCanCounterAttack()) {
                return;
            }
        } else if (card instanceof Minion) {
            if (!((Minion) card).isCanCounterAttack()) {
                return;
            }
        }
        counterOfHoly = 0;
        for (Buff buff : buffs) {
            if (buff instanceof Holy) {
                counterOfHoly++;
            }
        }
        if (card instanceof Minion) {
            if (((Minion) card).getTimeOfSpechialPower().matches("defend")) {
                ((Minion) card).defendPower(this);
            }
            if (HP <= ((Minion) card).getAP() + counterOfHoly) {
                setHP(0);
            } else {
                addHP(-1 * ((Minion) card).getAP() + counterOfHoly);
            }
        } else if (card instanceof Hero) {
            if (HP <= ((Hero) card).getAP() + counterOfHoly) {
                setHP(0);
            } else {
                addHP(-1 * ((Hero) card).getAP() + counterOfHoly);
<<<<<<< Updated upstream
=======
            }
        }

    }

    public void useSpecialPower(int x, int y) {

        if (!setCellEffect(x, y)) {
            System.out.println("Invalid target");
            return;
        }
        setCellEffect(x,y);
        if (targetCommunity.matches("cell")) {
            for (int i = 0 ; i < cellEffect.size() ; i ++ ) {
                for (Buff buff:specialBuff) {
                    Main.getBuffCell()[cellEffect.get(i)[0]][cellEffect.get(i)[1]] = buff;
                }
            }
        }else {
            for (int i = 0 ; i < cellEffect.size() ; i ++ ) {
                for (Buff buff:specialBuff) {
                    Main.getCardsCell()[cellEffect.get(i)[0]][cellEffect.get(i)[1]].addBuff(buff);
                    buff.effectBuffsOnCard(Main.getCardsCell()[cellEffect.get(i)[0]][cellEffect.get(i)[1]],numberOfPlayer);
                }
>>>>>>> Stashed changes
            }
        }

    }

    public void useSpecialPower(int x , int y) {

        

    }


}
