import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static Scanner scanner;
    private static Card[][] cardsCell = new Card[9][5];
    private static Buff[][] buffCell = new Buff[9][5];

    public static void setNullCardsCell(Card[][] cardsCell) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 5; j++) {
                cardsCell[i][j] = null;
            }
        }
    }

    public static Card[][] getCardsCell() {
        return cardsCell;
    }

    public static Buff[][] getBuffCell() {
        return buffCell;
    }

    static {
        scanner = new Scanner(System.in);
    }

    public static Scanner getScanner() {
        return scanner;
    }

    public static boolean compareTwoString(String string1, String string2) {
        for (int i = 0; i < string1.length() && i < string2.length(); i++) {
            if (string1.charAt(i) > string2.charAt(i))
                return true;
            else if (string1.charAt(i) < string2.charAt(i))
                return false;
        }
        if (string1.length() <= string2.length())
            return false;
        else
            return true;
    }

    public static void setNullArray(ArrayList<Buff> buffs) {
        for (int i = 0; i < buffs.size(); i++)
            buffs.remove(0);
    }

    public static void setShopSpell() {
        ArrayList<Buff> buffs = new ArrayList<Buff>();
        Disarm disarm = new Disarm(true, false, true, false, 0);
        buffs.add(disarm);
        Spell spell = new Spell("Total Disarm", "Disarm selected card until end", "rectangle enemy MH", 1000, 0, 1, 1, buffs);
        Shop.addAllCards(spell);
        setNullArray(buffs);
        Delete delete = new Delete(false, false, true, false, 1);
        buffs.add(delete);
        spell = new Spell("Area Dispel", "Destroy all positive and negative buff", "rectangle notallcell", 1500, 2, 2, 2, buffs);
        Shop.addAllCards(spell);
        setNullArray(buffs);
        Power power = new Power(false, false, true, false, 1, 2, 0);
        buffs.add(power);
        spell = new Spell("Empower", "Add 2 AP to selected card", "rectangle friendly MH", 250, 1, 1, 1, buffs);
        Shop.addAllCards(spell);
        setNullArray(buffs);
        Poison poison = new Poison(false, false, true, false, true, false, 1, 0, -4);
        buffs.add(poison);
        spell = new Spell("Fireball", "Knock 4 to selected card", "rectangle enemy MH", 400, 1, 1, 1, buffs);
        Shop.addAllCards(spell);
        setNullArray(buffs);
        power = new Power(false, false, true, false, 1, 4, 0);
        buffs.add(power);
        spell = new Spell("God Strenght", "Add 4 AP to own hero", "rectangle friendly H", 450, 2, 1, 1, buffs);
        Shop.addAllCards(spell);
        setNullArray(buffs);
        Fire fire = new Fire(false, false, true, false, 1, 2, -2);
        buffs.add(fire);
        spell = new Spell("Hell Fire", "Create fire cell for 2 turn", "rectangle allcell", 600, 3, 2, 2, buffs);
        Shop.addAllCards(spell);
        setNullArray(buffs);
        poison = new Poison(false, false, true, false, true, false, 1, 0, -8);
        buffs.add(poison);
        spell = new Spell("Lighting Bolt", "Knock 8 to enemy Hero", "rectangle enemy H", 1250, 2, 1, 1, buffs);
        Shop.addAllCards(spell);
        setNullArray(buffs);
        poison = new Poison(true, false, true, false, false, true, 1, 1, -1);
        buffs.add(poison);
        spell = new Spell("Poison Lake", "Create poison cell for 1 turn", "rectangle allcell", 900, 5, 3, 3, buffs);
        Shop.addAllCards(spell);
        setNullArray(buffs);
        power = new Power(false, false, false, false, 3, 4, 0);
        buffs.add(power);
        disarm = new Disarm(true, false, false, false, 3);
        buffs.add(disarm);
        spell = new Spell("Mandess", "Add 3 AP to selected card but disarm", "rectangle friendly MH", 650, 0, 1, 1, buffs);
        Shop.addAllCards(spell);
        setNullArray(buffs);
        disarm = new Disarm(true, false, false, false, 1);
        buffs.add(disarm);
        spell = new Spell("All Disarm", "Disarm all of enemy", "allcell enemy MH", 2000, 9, 0, 0, buffs);
        Shop.addAllCards(spell);
        setNullArray(buffs);
        poison = new Poison(true, true, true, false, false, false, 4, 0, -1);
        buffs.add(poison);
        spell = new Spell("All Poison", "Poison all enemy for 4 turn", "allcell enemy MH", 1500, 8, 0, 0, buffs);
        Shop.addAllCards(spell);
        setNullArray(buffs);
        delete = new Delete(false, false, true, false, 1);
        buffs.add(delete);
        spell = new Spell("Dispel", "Destroy positive and negative buff", "rectangle FE MH", 2100, 0, 1, 1, buffs);
        Shop.addAllCards(spell);
        setNullArray(buffs);
        Weakness weakness = new Weakness(true, false, false, false, 1, -6, 0);
        buffs.add(weakness);
        Holy holy = new Holy(true, true, false, false, 3);
        buffs.add(holy);
        buffs.add(holy);
        spell = new Spell("Health With Profit", "Give weakness buff with 6 and 2 holy buff for 3 turn ", "rectangle friendly MH", 2250, 0, 1, 1, buffs);
        Shop.addAllCards(spell);
        setNullArray(buffs);
        power = new Power(true, false, true, false, 1, 2, 0);
        buffs.add(power);
        spell = new Spell("Power UP", "Give power buff with 6 AP", "rectangle friendly MH", 2000, 4, 1, 1, buffs);
        Shop.addAllCards(spell);
        setNullArray(buffs);
        power = new Power(true, false, true, false, 1, 2, 0);
        buffs.add(power);
        spell = new Spell("All Power", "Give a power buff with 2 AP to all frienly", "allcell friendly MH", 2000, 4, 0, 0, buffs);
        Shop.addAllCards(spell);
        setNullArray(buffs);
        poison = new Poison(false, false, true, false, true, false, 1, 0, -6);
        buffs.add(poison);
        spell = new Spell("All Attack", "Knock 6 to all enemy in one column", "generalcell enemy MH", 1500, 4, 1, 5, buffs);
        Shop.addAllCards(spell);
        setNullArray(buffs);
        weakness = new Weakness(true, false, true, false, 1, 0, -4);
        buffs.add(weakness);
        spell = new Spell("Weakening", "Give weakness with 4 AP to minion", "rectangle enemy M", 1000, 1, 1, 1, buffs);
        Shop.addAllCards(spell);
        setNullArray(buffs);
        weakness = new Weakness(true, false, true, false, 1, -6, 0);
        buffs.add(weakness);
        power = new Power(true, false, true, false, 1, 8, 0);
        buffs.add(power);
        spell = new Spell("Sacrifice", "Give weakness buff with 6 AP and power buff with 8 AP", "rectangle friendly M", 1600, 2, 1, 1, buffs);
        Shop.addAllCards(spell);
        setNullArray(buffs);
        Kill kill = new Kill();
        buffs.add(kill);
        spell = new Spell("Kings Guard", "Kill Minion", "around H friendly M enemy", 1750, 9, 0, 0, buffs);
        Shop.addAllCards(spell);
        setNullArray(buffs);
        Stun stun = new Stun(true, false, false, false, 2);
        buffs.add(stun);
        spell = new Spell("Shock", "stun for 2 turn", "rectangle enemy MH", 1200, 1, 1, 1, buffs);
        Shop.addAllCards(spell);
    }

    public static void setMinion() {
        ArrayList<Buff> buffs = new ArrayList<Buff>();
        Minion minion = new Minion("Kamandar Fars", "ranged", 300, 2, 6, 4, 7, null, "", "");
        Shop.addAllCards(minion);
        Stun stun = new Stun(true, false, false, false, 1);
        buffs.add(stun);
        minion = new Minion("Shamshirzan Fars", "melee", 400, 2, 6, 4, 1, buffs, "attack", "attackCell MH");
        Shop.addAllCards(minion);
        setNullArray(buffs);
        minion = new Minion("Neyzedar Fars", "hybrid", 500, 1, 5, 3, 3, null, "", "");
        Shop.addAllCards(minion);
        minion = new Minion("Asbsavar Fars", "melee", 200, 4, 10, 6, 1, null, "", "");
        AddAP addAP = new AddAP(false, false, false, false, 0);
        buffs.add(addAP);
        minion = new Minion("Pahlavan Fars", "melee", 600, 9, 24, 6, 1, buffs, "", "attackCell MH");
        Shop.addAllCards(minion);
        setNullArray(buffs);
        minion = new Minion("Sepah Salar Fars", "melee", 800, 7, 12, 4, 1, null, "combo", "");
        Shop.addAllCards(minion);
        minion = new Minion("Kamandar Turani", "ranged", 500, 1, 3, 4, 5, null, "", "");
        Shop.addAllCards(minion);
        minion = new Minion("Qolab Sangdar Turani", "ranged", 600, 1, 4, 2, 7, null, "", "");
        Shop.addAllCards(minion);
        minion = new Minion("Neyzedar Turani", "hybrid", 600, 1, 4, 4, 3, null, "", "");
        Shop.addAllCards(minion);
        Disarm disarm = new Disarm(true, false, false, false, 1);
        buffs.add(disarm);
        Poison poison = new Poison(true, true, true, false, false, false, 4, 0, -1);
        buffs.add(poison);
        minion = new Minion("Jasus Turani", "melee", 700, 4, 6, 6, 1, buffs, "attack", "attackCell MH");
        Shop.addAllCards(minion);
        setNullArray(buffs);
        minion = new Minion("Gorzdar Turani", "melee", 450, 2, 3, 10, 1, null, "", "");
        Shop.addAllCards(minion);
        minion = new Minion("Shahzade Turani", "melee", 800, 6, 6, 10, 1, null, "combo", "");
        Shop.addAllCards(minion);
        minion = new Minion("Div Siah", "hybrid", 300, 9, 14, 10, 7, null, "", "");
        Shop.addAllCards(minion);
        minion = new Minion("Qul Sang Andaz", "ranged", 300, 9, 12, 12, 7, null, "", "");
        Shop.addAllCards(minion);
        Power power = new Power(true, true, true, false, 0, 0, 10);
        buffs.add(power);
        minion = new Minion("oqab", "ranged", 200, 2, 0, 2, 3, buffs, "passive", "I M");
        Shop.addAllCards(minion);
        setNullArray(buffs);
        minion = new Minion("Div Gorazsavar", "melee", 300, 6, 16, 8, 1, null, "", "");
        Shop.addAllCards(minion);
        poison = new Poison(false, false, true, false, true, false, 0, 0, -2);
        buffs.add(poison);
        minion = new Minion("Qul Tak Cheshm", "hybrid", 500, 7, 12, 11, 3, buffs, "death", "around enemy M 1");
        Shop.addAllCards(minion);
        setNullArray(buffs);
        poison = new Poison(true, true, true, false, false, false, 3, 0, -1);
        buffs.add(poison);
        minion = new Minion("Mar Sammi", "ranged", 300, 4, 5, 6, 4, buffs, "attack", "attackCell MH");
        Shop.addAllCards(minion);
        setNullArray(buffs);
        minion = new Minion("Ejdehaye Atash Andaz", "ranged", 250, 5, 9, 5, 4, null, "", "");
        Shop.addAllCards(minion);
        UnHoly unHoly = new UnHoly(true, true, false, 0, 1);
        buffs.add(unHoly);
        minion = new Minion("Shir Darande", "melee", 600, 2, 1, 8, 1, buffs, "attack", "attackCell MH");
        Shop.addAllCards(minion);
        setNullArray(buffs);
        unHoly = new UnHoly(true, true, false, 0, 2);
        buffs.add(unHoly);
        minion = new Minion("Mar Qul Peykar", "ranged", 500, 8, 14, 7, 5, buffs, "spawn", "around enemy M 2");
        Shop.addAllCards(minion);
        setNullArray(buffs);
        ArrayList<Integer> addHP = new ArrayList<Integer>();
        addHP.add(0);
        addHP.add(6);
        addHP.add(4);
        VariableAddHP variableAddHP = new VariableAddHP(false, true, false, 2, addHP);
        buffs.add(variableAddHP);
        minion = new Minion("Gorg Sefid", "melee", 400, 5, 8, 2, 1, buffs, "attack", "attackCell M");
        Shop.addAllCards(minion);
        setNullArray(buffs);
        addHP.remove(0);
        addHP.remove(0);
        addHP.remove(0);
        addHP.add(0);
        addHP.add(8);
        variableAddHP = new VariableAddHP(false, true, false, 1, addHP);
        buffs.add(variableAddHP);
        minion = new Minion("Palang", "melee", 400, 4, 6, 2, 1, buffs, "attack", "attackCell M");
        Shop.addAllCards(minion);
        setNullArray(buffs);
        addHP.remove(0);
        addHP.remove(0);
        addHP.add(0);
        addHP.add(6);
        variableAddHP = new VariableAddHP(false, true, false, 1, addHP);
        buffs.add(variableAddHP);
        minion = new Minion("Gorg", "melee", 400, 3, 6, 1, 1, buffs, "attack", "attackCell M");
        Shop.addAllCards(minion);
        setNullArray(buffs);
        power = new Power(true, false, false, false, 1, 2, 0);
        Weakness weakness = new Weakness(true, false, false, false, 1, -1, 0);
        buffs.add(power);
        buffs.add(weakness);
        minion = new Minion("Jadugar", "ranged", 550, 4, 5, 4, 3, buffs, "passive", "around enemy M 1 and I");
        Shop.addAllCards(minion);
        setNullArray(buffs);
        power = new Power(true, false, true, false, 0, 2, 0);
        Holy holy = new Holy(true, false, true, true, 0);
        buffs.add(power);
        buffs.add(holy);
        minion = new Minion("Jadugar Azam", "ranged", 550, 6, 6, 6, 5, buffs, "passive", "around enemy M 1 and I");
        Shop.addAllCards(minion);
        setNullArray(buffs);
        power = new Power(true, false, true, true, 0, 1, 0);
        buffs.add(power);
        minion = new Minion("Jen", "ranged", 500, 5, 10, 4, 4, buffs, "passive", "all friendly M");
        Shop.addAllCards(minion);
        setNullArray(buffs);
        poison = new Poison(false, false, true, false, false, false, 0, 0, -16);
        buffs.add(poison);
        minion = new Minion("Bahman", "melee", 450, 8, 16, 9, 1, buffs, "spawn", "randomEnemy M");
        Shop.addAllCards(minion);
        setNullArray(buffs);
        minion = new Minion("Eraj", "ranged", 500, 4, 6, 20, 3, null, "", "");
        Shop.addAllCards(minion);
        minion = new Minion("Qul Bozorg", "hybrid", 600, 9, 30, 8, 2, null, "", "");
        Shop.addAllCards(minion);
        Delete delete = new Delete(false, false, true, false, 1);
        buffs.add(delete);
        minion = new Minion("Qul Do Sar", "melee", 550, 4, 10, 4, 1, buffs, "attack", "attackCell MH");
        Shop.addAllCards(minion);
        setNullArray(buffs);
        stun = new Stun(true, false, false, false, 1);
        buffs.add(stun);
        minion = new Minion("Nane Sarma", "ranged", 500, 3, 3, 4, 5, buffs, "spawn", "around enemy M 1");
        Shop.addAllCards(minion);
        setNullArray(buffs);
        holy = new Holy(true, false, true, true, 0);
        for (int i = 0; i < 12; i++)
            buffs.add(holy);
        minion = new Minion("Fulad Zereh", "melee", 650, 3, 1, 1, 1, buffs, "passive", "I M");
        Shop.addAllCards(minion);
        setNullArray(buffs);
        poison = new Poison(true, false, true, false, true, false, 0, 0, -6);
        buffs.add(poison);
        minion = new Minion("Siavash", "melee", 350, 4, 8, 5, 1, buffs, "death", "randomEnemy H");
        Shop.addAllCards(minion);
        setNullArray(buffs);
        minion = new Minion("Shah Qul", "melee", 600, 5, 10, 4, 1, null, "combo", "");
        Shop.addAllCards(minion);
        minion = new Minion("Arjang Div", "melee", 600, 3, 6, 6, 1, null, "combo", "");
        Shop.addAllCards(minion);

    }

    public static void main(String[] args) {
        /*Hero hero = new Hero("shaj", 100, 1);
        Hero hero1 = new Hero("sds", 12, 1);
        hero1.setNumberOfPlayer(1);
        Minion minion = new Minion("sqhsq", 100, 1);
        minion.setNumberOfPlayer(1);
        Minion minion1 = new Minion("sdfs", 10, 12);
        minion1.setNumberOfPlayer(1);
        Minion minion2 = new Minion("sdsdsds", 100, 1212);
        minion2.setNumberOfPlayer(1);
        Minion minion3 = new Minion("Sd", 12, 21);
        minion3.setNumberOfPlayer(1);
        Minion minion4 = new Minion("Sd", 12, 21);
        minion4.setNumberOfPlayer(1);
        Minion minion5 = new Minion("Sd", 12, 21);
        minion5.setNumberOfPlayer(1);
        Minion minion6 = new Minion("Sd", 12, 21);
        Minion minion7 = new Minion("Sd", 12, 21);
        Minion minion8 = new Minion("Sd", 12, 21);
        Minion minion9 = new Minion("Sd", 12, 21);
        Minion minion10 = new Minion("Sd", 12, 21);
        Minion minion11 = new Minion("Sd", 12, 21);
        Minion minion12 = new Minion("Sd", 12, 21);

        Holy holy = new Holy(true, false, false, false, 1);
        minion.buffs.add(holy);

        cardsCell[0][0] = minion;
        cardsCell[3][0] = minion1;
        cardsCell[6][0] = minion12;
        cardsCell[8][0] = minion2;
        cardsCell[1][1] = minion3;
        cardsCell[4][1] = minion11;
        cardsCell[6][1] = minion4;
        cardsCell[2][2] = hero1;
        cardsCell[4][2] = minion5;
        cardsCell[6][2] = hero;
        cardsCell[8][2] = minion10;
        cardsCell[3][3] = minion6;
        cardsCell[0][4] = minion9;
        cardsCell[2][4] = minion8;
        cardsCell[6][4] = minion7;


        setShopSpell();

        Delete delete = new Delete(false, false, false, false, 1);
        delete.effectBuffsOnCard(minion, 0);

        for (int i = 0; i < minion.buffs.size(); i++)
            System.out.println(i);*/

        MainMenu.menu();
    }

}