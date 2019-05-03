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
        Spell spell = new Spell("total disarm", "Disarm selected card until end", "rectangle enemy MH", 1000, 0, 1, 1, buffs);
        Shop.addAllCards(spell);
        setNullArray(buffs);
        Delete delete = new Delete(false, false, true, false, 1);
        buffs.add(delete);
        spell = new Spell("area dispel", "Destroy all positive and negative buff", "rectangle notallcell", 1500, 2, 2, 2, buffs);
        Shop.addAllCards(spell);
        setNullArray(buffs);
        Power power = new Power(false, false, true, false, 1, 2, 0);
        buffs.add(power);
        spell = new Spell("empower", "Add 2 AP to selected card", "rectangle friendly MH", 250, 1, 1, 1, buffs);
        Shop.addAllCards(spell);
        setNullArray(buffs);
        Poison poison = new Poison(false, false, true, false, true, false, 1, 0, -4);
        buffs.add(poison);
        spell = new Spell("fireball", "Knock 4 to selected card", "rectangle enemy MH", 400, 1, 1, 1, buffs);
        Shop.addAllCards(spell);
        setNullArray(buffs);
        power = new Power(false, false, true, false, 1, 4, 0);
        buffs.add(power);
        spell = new Spell("god strenght", "Add 4 AP to own hero", "rectangle friendly H", 450, 2, 1, 1, buffs);
        Shop.addAllCards(spell);
        setNullArray(buffs);
        Fire fire = new Fire(false, false, true, false, 1, 2, -2);
        buffs.add(fire);
        spell = new Spell("hell fire", "Create fire cell for 2 turn", "rectangle allcell", 600, 3, 2, 2, buffs);
        Shop.addAllCards(spell);
        setNullArray(buffs);
        poison = new Poison(false, false, true, false, true, false, 1, 0, -8);
        buffs.add(poison);
        spell = new Spell("lighting bolt", "Knock 8 to enemy Hero", "rectangle enemy H", 1250, 2, 1, 1, buffs);
        Shop.addAllCards(spell);
        setNullArray(buffs);
        poison = new Poison(true, false, true, false, false, true, 1, 1, -1);
        buffs.add(poison);
        spell = new Spell("poison lake", "Create poison cell for 1 turn", "rectangle allcell", 900, 5, 3, 3, buffs);
        Shop.addAllCards(spell);
        setNullArray(buffs);
        power = new Power(false, false, false, false, 3, 4, 0);
        buffs.add(power);
        disarm = new Disarm(true, false, false, false, 3);
        buffs.add(disarm);
        spell = new Spell("mandess", "Add 3 AP to selected card but disarm", "rectangle friendly MH", 650, 0, 1, 1, buffs);
        Shop.addAllCards(spell);
        setNullArray(buffs);
        disarm = new Disarm(true, false, false, false, 1);
        buffs.add(disarm);
        spell = new Spell("all disarm", "Disarm all of enemy", "allcell enemy MH", 2000, 9, 0, 0, buffs);
        Shop.addAllCards(spell);
        setNullArray(buffs);
        poison = new Poison(true, true, true, false, false, false, 4, 0, -1);
        buffs.add(poison);
        spell = new Spell("all poison", "Poison all enemy for 4 turn", "allcell enemy MH", 1500, 8, 0, 0, buffs);
        Shop.addAllCards(spell);
        setNullArray(buffs);
        delete = new Delete(false, false, true, false, 1);
        buffs.add(delete);
        spell = new Spell("dispel", "Destroy positive and negative buff", "rectangle FE MH", 2100, 0, 1, 1, buffs);
        Shop.addAllCards(spell);
        setNullArray(buffs);
        Weakness weakness = new Weakness(true, false, false, false, 1, -6, 0);
        buffs.add(weakness);
        Holy holy = new Holy(true, true, false, false, 3);
        buffs.add(holy);
        buffs.add(holy);
        spell = new Spell("health with profit", "Give weakness buff with 6 and 2 holy buff for 3 turn ", "rectangle friendly MH", 2250, 0, 1, 1, buffs);
        Shop.addAllCards(spell);
        setNullArray(buffs);
        power = new Power(true, false, true, false, 1, 2, 0);
        buffs.add(power);
        spell = new Spell("power up", "Give power buff with 6 AP", "rectangle friendly MH", 2500, 4, 1, 1, buffs);
        Shop.addAllCards(spell);
        setNullArray(buffs);
        power = new Power(true, false, true, false, 1, 2, 0);
        buffs.add(power);
        spell = new Spell("all power", "Give a power buff with 2 AP to all frienly", "allcell friendly MH", 2000, 4, 0, 0, buffs);
        Shop.addAllCards(spell);
        setNullArray(buffs);
        poison = new Poison(false, false, true, false, true, false, 1, 0, -6);
        buffs.add(poison);
        spell = new Spell("all attack", "Knock 6 to all enemy in one column", "generalcell enemy MH", 1500, 4, 1, 5, buffs);
        Shop.addAllCards(spell);
        setNullArray(buffs);
        weakness = new Weakness(true, false, true, false, 1, 0, -4);
        buffs.add(weakness);
        spell = new Spell("weakening", "Give weakness with 4 AP to minion", "rectangle enemy M", 1000, 1, 1, 1, buffs);
        Shop.addAllCards(spell);
        setNullArray(buffs);
        weakness = new Weakness(true, false, true, false, 1, -6, 0);
        buffs.add(weakness);
        power = new Power(true, false, true, false, 1, 8, 0);
        buffs.add(power);
        spell = new Spell("sacrifice", "Give weakness buff with 6 AP and power buff with 8 AP", "rectangle friendly M", 1600, 2, 1, 1, buffs);
        Shop.addAllCards(spell);
        setNullArray(buffs);
        Kill kill = new Kill();
        buffs.add(kill);
        spell = new Spell("kings guard", "Kill Minion", "around H friendly M enemy", 1750, 9, 0, 0, buffs);
        Shop.addAllCards(spell);
        setNullArray(buffs);
        Stun stun = new Stun(true, false, false, false, 2);
        buffs.add(stun);
        spell = new Spell("shock", "stun for 2 turn", "rectangle enemy MH", 1200, 1, 1, 1, buffs);
        Shop.addAllCards(spell);
    }

    public static void setShopMinion() {
        ArrayList<Buff> buffs = new ArrayList<Buff>();
        Minion minion = new Minion("kamandar fars", "ranged", 300, 2, 6, 4, 7, null, "", "", "Don't have");
        Shop.addAllCards(minion);
        Stun stun = new Stun(true, false, false, false, 1);
        buffs.add(stun);
        minion = new Minion("shamshirzan fars", "melee", 400, 2, 6, 4, 1, buffs, "attack", "attackCell MH", "Stun for one turn");
        Shop.addAllCards(minion);
        setNullArray(buffs);
        minion = new Minion("neyzedar fars", "hybrid", 500, 1, 5, 3, 3, null, "", "", "Don't have");
        Shop.addAllCards(minion);
        minion = new Minion("asbsavar fars", "melee", 200, 4, 10, 6, 1, null, "", "", "Don't have");
        Shop.addAllCards(minion);
        AddAP addAP = new AddAP(false, false, false, false, 0);
        buffs.add(addAP);
        minion = new Minion("pahlavan fars", "melee", 600, 9, 24, 6, 1, buffs, "", "attackCell MH", "Increases by 5 AP per attack to one enemy");
        Shop.addAllCards(minion);
        setNullArray(buffs);
        minion = new Minion("sepah salar fars", "melee", 800, 7, 12, 4, 1, null, "combo", "", "combo");
        Shop.addAllCards(minion);
        minion = new Minion("kamandar turani", "ranged", 500, 1, 3, 4, 5, null, "", "", "Don't have");
        Shop.addAllCards(minion);
        minion = new Minion("qolab sangdar turani", "ranged", 600, 1, 4, 2, 7, null, "", "", "Don't have");
        Shop.addAllCards(minion);
        minion = new Minion("neyzedar turani", "hybrid", 600, 1, 4, 4, 3, null, "", "", "Don't have");
        Shop.addAllCards(minion);
        Disarm disarm = new Disarm(true, false, false, false, 1);
        buffs.add(disarm);
        Poison poison = new Poison(true, true, true, false, false, false, 4, 0, -1);
        buffs.add(poison);
        minion = new Minion("jasus turani", "melee", 700, 4, 6, 6, 1, buffs, "attack", "attackCell MH", "Disarm for one turn and poison for 4 turn");
        Shop.addAllCards(minion);
        setNullArray(buffs);
        minion = new Minion("gorzdar turani", "melee", 450, 2, 3, 10, 1, null, "", "", "Don't have");
        Shop.addAllCards(minion);
        minion = new Minion("shahzade turani", "melee", 800, 6, 6, 10, 1, null, "combo", "", "combo");
        Shop.addAllCards(minion);
        minion = new Minion("div siah", "hybrid", 300, 9, 14, 10, 7, null, "", "", "Don't have");
        Shop.addAllCards(minion);
        minion = new Minion("qul sang andaz", "ranged", 300, 9, 12, 12, 7, null, "", "", "Don't have");
        Shop.addAllCards(minion);
        Power power = new Power(true, true, true, false, 0, 0, 10);
        buffs.add(power);
        minion = new Minion("oqab", "ranged", 200, 2, 0, 2, 3, buffs, "passive", "I M", "Have 10 power buff with adding HP");
        Shop.addAllCards(minion);
        setNullArray(buffs);
        minion = new Minion("div gorazsavar", "melee", 300, 6, 16, 8, 1, null, "", "", "Don't have");
        Shop.addAllCards(minion);
        poison = new Poison(false, false, true, false, true, false, 0, 0, -2);
        buffs.add(poison);
        minion = new Minion("qul tak cheshm", "hybrid", 500, 7, 12, 11, 3, buffs, "death", "around enemy M 1", "Knock 2 to all of neighber minion");
        Shop.addAllCards(minion);
        setNullArray(buffs);
        poison = new Poison(true, true, true, false, false, false, 3, 0, -1);
        buffs.add(poison);
        minion = new Minion("mar sammi", "ranged", 300, 4, 5, 6, 4, buffs, "attack", "attackCell MH", "poison for 3 turn");
        Shop.addAllCards(minion);
        setNullArray(buffs);
        minion = new Minion("ejdehaye atash andaz", "ranged", 250, 5, 9, 5, 4, null, "", "", "Don't have");
        Shop.addAllCards(minion);
        UnHoly unHoly = new UnHoly(true, true, false, 0, 1);
        buffs.add(unHoly);
        minion = new Minion("shir darande", "melee", 600, 2, 1, 8, 1, buffs, "attack", "attackCell MH", "Don't effect Holy buff");
        Shop.addAllCards(minion);
        setNullArray(buffs);
        unHoly = new UnHoly(true, true, false, 0, 2);
        buffs.add(unHoly);
        minion = new Minion("mar qul peykar", "ranged", 500, 8, 14, 7, 5, buffs, "spawn", "around enemy M 2", "Knock one more to minion with 2 cell distance");
        Shop.addAllCards(minion);
        setNullArray(buffs);
        ArrayList<Integer> addHP = new ArrayList<Integer>();
        addHP.add(0);
        addHP.add(6);
        addHP.add(4);
        VariableAddHP variableAddHP = new VariableAddHP(false, true, false, 2, addHP);
        buffs.add(variableAddHP);
        minion = new Minion("gorg sefid", "melee", 400, 5, 8, 2, 1, buffs, "attack", "attackCell M", "Add Health -6 for next turn and -4 for another turn");
        Shop.addAllCards(minion);
        setNullArray(buffs);
        addHP.remove(0);
        addHP.remove(0);
        addHP.remove(0);
        addHP.add(0);
        addHP.add(8);
        variableAddHP = new VariableAddHP(false, true, false, 1, addHP);
        buffs.add(variableAddHP);
        minion = new Minion("palang", "melee", 400, 4, 6, 2, 1, buffs, "attack", "attackCell M", "Add -8 HP to minion next turn");
        Shop.addAllCards(minion);
        setNullArray(buffs);
        addHP.remove(0);
        addHP.remove(0);
        addHP.add(0);
        addHP.add(6);
        variableAddHP = new VariableAddHP(false, true, false, 1, addHP);
        buffs.add(variableAddHP);
        minion = new Minion("gorg", "melee", 400, 3, 6, 1, 1, buffs, "attack", "attackCell M", "Add -6 HP in next turn");
        Shop.addAllCards(minion);
        setNullArray(buffs);
        power = new Power(true, false, false, false, 1, 2, 0);
        Weakness weakness = new Weakness(true, false, false, false, 1, -1, 0);
        buffs.add(power);
        buffs.add(weakness);
        minion = new Minion("jadugar", "ranged", 550, 4, 5, 4, 3, buffs, "passive", "around friendly M 1 and I", "Power buff with 2 add AP and weakness buff with -1 add HP");
        Shop.addAllCards(minion);
        setNullArray(buffs);
        power = new Power(true, false, true, false, 0, 2, 0);
        Holy holy = new Holy(true, false, true, true, 0);
        buffs.add(power);
        buffs.add(holy);
        minion = new Minion("jadugar azam", "ranged", 550, 6, 6, 6, 5, buffs, "passive", "around friendly M 1 and I", "Power buff with 2 add AP anf continue holy buff");
        Shop.addAllCards(minion);
        setNullArray(buffs);
        power = new Power(true, false, true, true, 0, 1, 0);
        buffs.add(power);
        minion = new Minion("jen", "ranged", 500, 5, 10, 4, 4, buffs, "passive", "all friendly M", "Continue power buff with 1 add AP");
        Shop.addAllCards(minion);
        setNullArray(buffs);
        poison = new Poison(false, false, true, false, false, false, 0, 0, -16);
        buffs.add(poison);
        minion = new Minion("bahman", "melee", 450, 8, 16, 9, 1, buffs, "spawn", "randomEnemy M", "Add -16 HP to rendom enemy minion");
        Shop.addAllCards(minion);
        setNullArray(buffs);
        minion = new Minion("eraj", "ranged", 500, 4, 6, 20, 3, null, "", "", "Don't have");
        Shop.addAllCards(minion);
        minion = new Minion("qul bozorg", "hybrid", 600, 9, 30, 8, 2, null, "", "", "Don't have");
        Shop.addAllCards(minion);
        Delete delete = new Delete(false, false, true, false, 1);
        buffs.add(delete);
        minion = new Minion("qul do sar", "melee", 550, 4, 10, 4, 1, buffs, "attack", "attackCell MH", "Delete all positive buff");
        Shop.addAllCards(minion);
        setNullArray(buffs);
        stun = new Stun(true, false, false, false, 1);
        buffs.add(stun);
        minion = new Minion("nane sarma", "ranged", 500, 3, 3, 4, 5, buffs, "spawn", "around enemy M 1", "Stun all neighbor minion for one turn");
        Shop.addAllCards(minion);
        setNullArray(buffs);
        holy = new Holy(true, false, true, true, 0);
        for (int i = 0; i < 12; i++)
            buffs.add(holy);
        minion = new Minion("fulad zereh", "melee", 650, 3, 1, 1, 1, buffs, "passive", "I M", "Have 12 continue holy buff");
        Shop.addAllCards(minion);
        setNullArray(buffs);
        poison = new Poison(true, false, true, false, true, false, 0, 0, -6);
        buffs.add(poison);
        minion = new Minion("siavash", "melee", 350, 4, 8, 5, 1, buffs, "death", "randomEnemy H", "Knock 6 to enemy hero");
        Shop.addAllCards(minion);
        setNullArray(buffs);
        minion = new Minion("shah Qul", "melee", 600, 5, 10, 4, 1, null, "combo", "", "combo");
        Shop.addAllCards(minion);
        minion = new Minion("arjang div", "melee", 600, 3, 6, 6, 1, null, "combo", "", "combo");
        Shop.addAllCards(minion);

    }

    public static void setShopHero() {

        ArrayList<Buff> buffs = new ArrayList<Buff>();
        Power power = new Power(true, false, true, false, 0, 4, 0);
        buffs.add(power);
        Hero hero = new Hero("div sefid", "melee", 8000, 1, 50, 4, 1, 2, "Give power buff with 4 AP to hero", buffs,"hero",true,false,false);
        Shop.addAllCards(hero);
        setNullArray(buffs);
        Stun stun = new Stun(true, false, false, false, 1);
        buffs.add(stun);
        hero = new Hero("simorq", "melee", 9000, 5, 50, 4, 1, 8, "Stun all of enemy for one turn", buffs,"all enemy" , true , false,false);
        Shop.addAllCards(hero);
        setNullArray(buffs);
        Disarm disarm = new Disarm(true, false, false, false, 1);
        buffs.add(disarm);
        hero = new Hero("ejdehaye haft sar", "melee", 8000, 0, 50, 4, 1, 1, "Disarm one of enemy", buffs,"one enemy",true,false,false);
        Shop.addAllCards(hero);
        setNullArray(buffs);
        stun = new Stun(true, false, false, false, 1);
        hero = new Hero("rakhsh", "melee", 8000, 1, 50, 4, 1, 2, "Stun one of enemy for one turn", buffs,"one enemy",true,false,false);
        Shop.addAllCards(hero);
        setNullArray(buffs);
        Poison poison = new Poison(true, false, false, false, false, false, 3, 0, -1);
        buffs.add(poison);
        hero = new Hero("zahak", "melee", 10000, 0, 50, 2, 1, 0, "Poison enemy for 3 turn", buffs,"" , false,true,false);
        Shop.addAllCards(hero);
        setNullArray(buffs);
        HolyCell holyCell = new HolyCell(3);
        buffs.add(holyCell);
        hero = new Hero("kave", "melee", 8000, 1, 50, 4, 1, 3, "Holy Cell for 3 turn", buffs,"cell" , true,false,false);
        Shop.addAllCards(hero);
        setNullArray(buffs);
        poison = new Poison(false, false, true, false, true, false, 0, 0, -4);
        buffs.add(poison);
        hero = new Hero("arash", "ranged", 10000, 2, 30, 2, 6, 2, "Knock 4 to enemy", buffs,"row" , true,false,false);
        Shop.addAllCards(hero);
        setNullArray(buffs);
        Delete delete = new Delete(false, false, true, false, 0);
        buffs.add(delete);
        hero = new Hero("afsane", "ranged", 11000, 1, 40, 3, 3, 2, "Dispel enemy", buffs,"one enemy" , true,false,false);
        Shop.addAllCards(hero);
        setNullArray(buffs);
        Holy holy = new Holy(true, false, true, true, 0);
        buffs.add(holy);
        buffs.add(holy);
        buffs.add(holy);
        hero = new Hero("esfandiar", "hybrid", 12000, 0, 35, 3, 3, 0, "Have 3 continue holy buff", buffs,"",false,false,true);
        Shop.addAllCards(hero);
        setNullArray(buffs);
        hero = new Hero("rostam", "hybrid", 8000, 0, 55, 7, 4, 0, "Don't have", buffs,"",false,false,false);
        Shop.addAllCards(hero);

    }

    public static void setShopItem() {



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
        setShopSpell();
        setShopMinion();
        setShopHero();
        setShopItem();
        MainMenu.menu();
    }

}