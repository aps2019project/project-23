import java.util.ArrayList;

public class Minion extends Card {

    private int HP;
    private int AP;
    private int attackRange;
    private String clas;
    private String timeOfSpechialPower;
    private boolean canCounterAttack;
    private boolean onOrOf;
    private ArrayList<Buff> spechialBuff = new ArrayList<Buff>();
    private ArrayList<Integer[]> cellEffect = new ArrayList<Integer[]>();
    private String targetCommunity;
    private String specialPower;
    private boolean locationOfAttack;

    public Minion(String name, String clas, int cost, int MP, int HP, int AP, int attackRange, ArrayList<Buff> spechialBuff, String timeOfSpechialPower, String targetCommunity, String specialPower, boolean locationOfAttack) {
        super(name, cost, MP);
        this.clas = clas;
        this.HP = HP;
        this.AP = AP;
        this.attackRange = attackRange;
        this.canCounterAttack = true;
        this.onOrOf = true;
        if (spechialBuff != null) {
            for (Buff buff : spechialBuff) {
                this.spechialBuff.add(buff);
            }
        }
        this.timeOfSpechialPower = timeOfSpechialPower;
        this.targetCommunity = targetCommunity;
        this.specialPower = specialPower;
        this.locationOfAttack = locationOfAttack;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getAttackRange() {
        return attackRange;
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

    public boolean isOnOrOf() {
        return onOrOf;
    }

    public String getClas() {
        return clas;
    }

    public String getSpecialPower() {
        return specialPower;
    }

    public void setOnOrOf(boolean onOrOf) {
        this.onOrOf = onOrOf;
    }

    public void setCanCounterAttack(boolean canCounterAttack) {
        this.canCounterAttack = canCounterAttack;
    }

    public void setNullCellEffect() {
        for (int i = 0; i < cellEffect.size(); i++) {
            cellEffect.remove(0);
        }
    }

    public boolean isCanCounterAttack() {
        return canCounterAttack;
    }

    public void setAttackCell(int x, int y, String[] targetCommunities) {
        if (targetCommunities[1].matches("M")) {
            if (Main.getCardsCell()[x][y] instanceof Minion) {
                Integer[] cell = {x, y};
                cellEffect.add(cell);
            }
        } else if (targetCommunities[1].matches("H")) {
            if (Main.getCardsCell()[x][y] instanceof Hero) {
                Integer[] cell = {x, y};
                cellEffect.add(cell);
            }
        } else if (targetCommunities[1].matches("MH")) {
            if (Main.getCardsCell()[x][y] instanceof Hero || Main.getCardsCell()[x][y] instanceof Minion) {
                Integer[] cell = {x, y};
                cellEffect.add(cell);
            }
        }

    }

    public void setRandomCell(String[] targetCommunities) {
        if (targetCommunities[1].matches("H")) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 5; j++) {
                    if (Main.getCardsCell()[i][j] instanceof Hero) {
                        if (Main.getCardsCell()[i][j].numberOfPlayer != this.numberOfPlayer) {
                            Integer[] cell = {i, j};
                            cellEffect.add(cell);
                        }
                    }
                }
            }
        } else if (targetCommunities[1].matches("M")) {
            int counterOfEnemyMinion = 0;
            int randomNumber;
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 5; j++) {
                    if (Main.getCardsCell()[i][j] instanceof Minion) {
                        if (Main.getCardsCell()[i][j].numberOfPlayer != this.numberOfPlayer)
                            counterOfEnemyMinion++;
                    }
                }
            }
            randomNumber = (int) (Math.random() * counterOfEnemyMinion + 1);
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 5; j++) {
                    if (Main.getCardsCell()[i][j] instanceof Minion) {
                        if (Main.getCardsCell()[i][j].numberOfPlayer != numberOfPlayer) {
                            randomNumber--;
                        }
                        if (randomNumber == 0) {
                            Integer[] cell = {i, j};
                            cellEffect.add(cell);
                            return;
                        }
                    }
                }
            }
        }
    }

    public void setCellAll(String[] targetCommunities) {

        if (targetCommunities[1].matches("friendly")) {
            if (targetCommunities[2].matches("M")) {
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 5; j++) {
                        if (Main.getCardsCell()[i][j] instanceof Minion) {
                            if (Main.getCardsCell()[i][j].numberOfPlayer == this.numberOfPlayer) {
                                Integer[] cell = {i, j};
                                cellEffect.add(cell);
                            }
                        }
                    }
                }
            }
        }
    }

    public void setAroundCell(int x, int y, String[] targetCommunities) {

        int number = Integer.parseInt(targetCommunities[3]);
        if (targetCommunities[1].matches("enemy")) {
            if (targetCommunities[2].matches("M")) {
                for (int i = -1 * number; i < number + 1; i++) {
                    for (int j = -1 * number; j < number + 1; j++) {
                        if (Main.getCardsCell()[x + i][y + j] instanceof Minion) {
                            if (Main.getCardsCell()[x + i][y + j].numberOfPlayer != this.numberOfPlayer) {
                                Integer[] cell = {x + i, y + j};
                                cellEffect.add(cell);
                            }
                        }
                    }
                }
            }
        } else if (targetCommunities[1].matches("friendly")) {
            if (targetCommunities[2].matches("M")) {
                for (int i = -1 * number; i < number + 1; i++) {
                    for (int j = -1 * number; j < number + 1; j++) {
                        if (Main.getCardsCell()[x + i][y + j] instanceof Minion) {
                            if (Main.getCardsCell()[x + i][y + j].numberOfPlayer == this.numberOfPlayer) {
                                Integer[] cell = {x + i, y + j};
                                cellEffect.add(cell);
                            }
                        }
                    }
                }
            }
        }

        if (targetCommunities.length > 3) {
            Integer[] cell = {x, y};
            cellEffect.add(cell);
        }

    }

    public void setCellEffect(int x, int y) {

        setNullCellEffect();
        String[] targetCommunities = this.targetCommunity.split(" ");
        if (targetCommunities[0].matches("attackCell") || targetCommunities[1].matches("I")) {
            setAttackCell(x, y, targetCommunities);
        } else if (targetCommunities[0].matches("randomEnemy")) {
            setRandomCell(targetCommunities);
        } else if (targetCommunities[0].matches("all")) {
            setCellAll(targetCommunities);
        } else if (targetCommunities[0].matches("around")) {
            setAroundCell(x, y, targetCommunities);
        }

    }

    public void attack(int x, int y) {

        int counterOfHoly = 0;
        int counterUnHoly = 0;
        Card card = Main.getCardsCell()[x][y];
        for (Buff buff : card.getBuffs()) {
            if (buff instanceof UnHoly) {
                if (((UnHoly) buff).getAddAPForHoly() == 1) {
                    counterOfHoly++;
                    break;
                }
            }
        }
        if (counterUnHoly == 0) {
            for (Buff buff : card.getBuffs()) {
                if (buff instanceof UnHoly) {
                    buff.effectBuffsOnCard(card, card.numberOfPlayer);
                } else if (buff instanceof Holy) {
                    counterOfHoly++;
                }
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
        if (locationOfAttack) {
            setCellEffect(GameController.getLocation(cardID)[0], GameController.getLocation(cardID)[1]);
        } else {
            setCellEffect(x, y);
        }
        for (Buff buff : spechialBuff) {
            Main.getCardsCell()[x][y].addBuff(buff);
            buff.effectBuffsOnCard(Main.getCardsCell()[x][y], Main.getCardsCell()[x][y].numberOfPlayer);
        }

    }

    public void defendPower(Card card) {

        if (locationOfAttack) {
            setCellEffect(GameController.getLocation(this.cardID)[0], GameController.getLocation(this.cardID)[1]);
        } else {
            setCellEffect(GameController.getLocation(card.cardID)[0], GameController.getLocation(card.cardID)[1]);
        }
        for (int i = 0; i < cellEffect.size(); i++) {
            for (Buff buff : spechialBuff) {
                if (Main.getCardsCell()[cellEffect.get(i)[0]][cellEffect.get(i)[1]] != null) {
                    Main.getCardsCell()[cellEffect.get(i)[0]][cellEffect.get(i)[1]].addBuff(buff);
                    buff.effectBuffsOnCard(Main.getCardsCell()[cellEffect.get(i)[0]][cellEffect.get(i)[1]], Main.getCardsCell()[cellEffect.get(i)[0]][cellEffect.get(i)[1]].numberOfPlayer);
                }
            }
        }

    }

    public Card copyOfCard() {

        ArrayList<Buff> buffs = new ArrayList<Buff>();
        for (Buff buff : spechialBuff) {
            buffs.add(buff.copyBuff());
        }
        Minion minion = new Minion(name, clas, cost, MP, HP, AP, attackRange, buffs, timeOfSpechialPower, targetCommunity, specialPower, locationOfAttack);
        if (cardID != null) {
            minion.setCardID(cardID);
        }
        return minion;

    }

}
