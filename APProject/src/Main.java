import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static Scanner scanner;
    private static Card[][] cardsCell = new Card[9][5];

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
        Disarm disarm = new Disarm(true,false, true, false, 0);
        buffs.add(disarm);
        Spell spell = new Spell("Total Disarm", "Disarm selected card until end", "rectangle enemy MH", 1000, 0, 1, 1, buffs);
        Shop.addAllCards(spell);
        setNullArray(buffs);
        Delete delete = new Delete(false,false, true, false, 1);
        buffs.add(delete);
        spell = new Spell("Area Dispel", "Destroy all positive and negative buff", "rectangle notallcell", 1500, 2, 2, 2, buffs);
        Shop.addAllCards(spell);
        setNullArray(buffs);
        Power power = new Power(false,false, true, false, 1, 2, 0);
        buffs.add(power);
        spell = new Spell("Empower", "Add 2 AP to selected card", "rectangle friendly MH", 250, 1, 1, 1, buffs);
        Shop.addAllCards(spell);
        setNullArray(buffs);
        Poison poison = new Poison(false,false, true, false, true, false, 1, 0, -4);
        buffs.add(poison);
        spell = new Spell("Fireball", "Knock 4 to selected card", "rectangle enemy MH", 400, 1, 1, 1, buffs);
        Shop.addAllCards(spell);
        setNullArray(buffs);
        power = new Power(false,false, true, false, 1, 4, 0);
        buffs.add(power);
        spell = new Spell("God Strenght", "Add 4 AP to own hero", "rectangle friendly H", 450, 2, 1, 1, buffs);
        Shop.addAllCards(spell);
        setNullArray(buffs);
        poison = new Poison(false,false, true, false, false, true, 1, 2, -2);
        buffs.add(poison);
        spell = new Spell("Hell Fire", "Create fire cell for 2 turn", "rectangle allcell", 600, 3, 2, 2, buffs);
        Shop.addAllCards(spell);
        setNullArray(buffs);
        poison = new Poison(false,false, true, false, true, false, 1, 0, -8);
        buffs.add(poison);
        spell = new Spell("Lighting Bolt", "Knock 8 to enemy Hero", "rectangle enemy H", 1250, 2, 1, 1, buffs);
        Shop.addAllCards(spell);
        setNullArray(buffs);
        poison = new Poison(true,false, true, false, false, true, 1, 1, -1);
        buffs.add(poison);
        spell = new Spell("Poison Lake", "Create poison cell for 1 turn", "rectangle allcell", 900, 5, 3, 3, buffs);
        Shop.addAllCards(spell);
        setNullArray(buffs);
        power = new Power(false,false, false, false, 3, 4, 0);
        buffs.add(power);
        disarm = new Disarm(true,false, false, false, 3);
        buffs.add(disarm);
        spell = new Spell("Mandess", "Add 3 AP to selected card but disarm", "rectangle friendly MH", 650, 0, 1, 1, buffs);
        Shop.addAllCards(spell);
        setNullArray(buffs);
        disarm = new Disarm(true,false, false, false, 1);
        buffs.add(disarm);
        spell = new Spell("All Disarm", "Disarm all of enemy", "allcell enemy MH", 2000, 9, 0, 0, buffs);
        Shop.addAllCards(spell);
        setNullArray(buffs);
        poison = new Poison(true,true, true, false, false, false, 4, 0, -1);
        buffs.add(poison);
        spell = new Spell("All Poison", "Poison all enemy for 4 turn", "allcell enemy MH", 1500, 8, 0, 0, buffs);
        Shop.addAllCards(spell);
        setNullArray(buffs);
        delete = new Delete(false,false, true, false, 1);
        buffs.add(delete);
        spell = new Spell("Dispel", "Destroy positive and negative buff", "rectangle FE MH", 2100, 0, 1, 1, buffs);
        Shop.addAllCards(spell);
        setNullArray(buffs);
        Weakness weakness = new Weakness(true,false, false, false, 1, -6, 0);
        buffs.add(weakness);
        Holy holy = new Holy(true,true, false, false, 3);
        buffs.add(holy);
        buffs.add(holy);
        spell = new Spell("Health With Profit", "Give weakness buff with 6 and 2 holy buff for 3 turn ", "rectangle friendly MH", 2250, 0, 1, 1, buffs);
        Shop.addAllCards(spell);
        setNullArray(buffs);
        power = new Power(true,false, true, false, 1, 2, 0);
        buffs.add(power);
        spell = new Spell("Power UP", "Give power buff with 6 AP", "rectangle friendly MH", 2000, 4, 1, 1, buffs);
        Shop.addAllCards(spell);
        setNullArray(buffs);
        power = new Power(true,false, true, false, 1, 2, 0);
        buffs.add(power);
        spell = new Spell("All Power", "Give a power buff with 2 AP to all frienly", "allcell friendly MH", 2000, 4, 0, 0, buffs);
        Shop.addAllCards(spell);
        setNullArray(buffs);
        poison = new Poison(false,false, true, false, true, false, 1, 0, -6);
        buffs.add(poison);
        spell = new Spell("All Attack", "Knock 6 to all enemy in one column", "generalcell enemy MH", 1500, 4, 1, 5, buffs);
        Shop.addAllCards(spell);
        setNullArray(buffs);
        weakness = new Weakness(true,false, true, false, 1, -4, 0);
        buffs.add(weakness);
        spell = new Spell("Weakening", "Give weakness with 4 AP to minion", "rectangle enemy M", 1000, 1, 1, 1, buffs);
        Shop.addAllCards(spell);
        setNullArray(buffs);
        weakness = new Weakness(true,false, true, false, 1, -6, 0);
        buffs.add(weakness);
        power = new Power(true,false, true, false, 1, 8, 0);
        buffs.add(power);
        spell = new Spell("Sacrifice", "Give weakness buff with 6 AP and power buff with 8 AP", "rectangle friendly M", 1600, 2, 1, 1, buffs);
        Shop.addAllCards(spell);
        setNullArray(buffs);
        Kill kill = new Kill();
        buffs.add(kill);
        spell = new Spell("Kings Guard", "Kill Minion", "around H friendly M enemy", 1750, 9, 0, 0, buffs);
        Shop.addAllCards(spell);
        setNullArray(buffs);
        Stun stun = new Stun(true,false, true, false, 1);
        buffs.add(stun);
        spell = new Spell("Shock", "stun for 2 turn", "rectangle enemy MH", 1200, 1, 1, 1, buffs);
        Shop.addAllCards(spell);
    }

    public static void main(String[] args) {
        Hero hero = new Hero("shaj", 100, 1);
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

        Holy holy = new Holy(true,false,false,false,1);
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

        Delete delete = new Delete(false,false,false,false,1);
        delete.effectBuffsOnCard(minion,0);

        for ( int i = 0 ; i < minion.buffs.size() ; i ++ )
            System.out.println(i);

        MainMenu.menu();
    }

}