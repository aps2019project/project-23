import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

    private static Scanner scanner;
    private static Card[][] cardsCell = new Card[9][5];
    private static Buff[][] buffCell = new Buff[9][5];


    public static void setNullCardsCell() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 5; j++) {
                cardsCell[i][j] = null;
            }
        }
    }

    public static void setNullBuffsCell() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 5; j++) {
                buffCell[i][j] = null;
            }
        }
    }

    public static void setCollectableItemInCells() {

        Random random = new Random();
        int collectable1 = random.nextInt(Item.getCollectableItems().size());
        int collectable2 = random.nextInt(Item.getCollectableItems().size());
        int collectable3 = random.nextInt(Item.getCollectableItems().size());

        cardsCell[4][0] = Item.getCollectableItems().get(collectable1).copyOfCard();
        cardsCell[5][2] = Item.getCollectableItems().get(collectable2).copyOfCard();
        cardsCell[4][4] = Item.getCollectableItems().get(collectable3).copyOfCard();

    }

    public static Card[][] getCardsCell() {
        return cardsCell;
    }

    public static Buff[][] getBuffCell() {
        return buffCell;
    }

    public static void setScannerUser() {
        scanner = new Scanner(System.in);
    }

    public static boolean setFileScanner() {
        try {
            scanner = new Scanner(new File("/home/shajusoni/IdeaProjects/APProject/account.txt"));
            return true;
        } catch (Exception e) {
            return false;
        }
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
        int size = buffs.size();
        for (int i = 0; i < size; i++) {
            buffs.remove(0);
        }
    }

    public static void setShopSpell() {
        ArrayList<Buff> buffs = new ArrayList<Buff>();
        Disarm disarm = new Disarm(true, false, true, false, 0);
        buffs.add(disarm);
        Spell spell = new Spell("totaldisarm", "Disarm selected card until end", "rectangle enemy MH", 1000, 0, 1, 1, buffs, false);
        Shop.addAllCards(spell);
        setNullArray(buffs);
        Delete delete = new Delete(false, false, true, false, 1);
        buffs.add(delete);
        spell = new Spell("areadispel", "Destroy all positive and negative buff", "rectangle notallcell", 1500, 2, 2, 2, buffs, false);
        Shop.addAllCards(spell);
        setNullArray(buffs);
        Power power = new Power(false, false, true, false, 0, 2, 0);
        buffs.add(power);
        spell = new Spell("empower", "Add 2 AP to selected card", "rectangle friendly MH", 250, 1, 1, 1, buffs, false);
        Shop.addAllCards(spell);
        setNullArray(buffs);
        Poison poison = new Poison(false, false, true, false, true, false, 1, 0, -4);
        buffs.add(poison);
        spell = new Spell("fireball", "Knock 4 to selected card", "rectangle enemy MH", 400, 1, 1, 1, buffs, false);
        Shop.addAllCards(spell);
        setNullArray(buffs);
        power = new Power(false, false, true, false, 1, 4, 0);
        buffs.add(power);
        spell = new Spell("godstrenght", "Add 4 AP to own hero", "rectangle friendly H", 450, 2, 1, 1, buffs, false);
        Shop.addAllCards(spell);
        setNullArray(buffs);
        Fire fire = new Fire(false, false, true, false, 1, 2, -2);
        buffs.add(fire);
        spell = new Spell("hellfire", "Create fire cell for 2 turn", "rectangle allcell", 600, 3, 2, 2, buffs, true);
        Shop.addAllCards(spell);
        setNullArray(buffs);
        poison = new Poison(false, false, true, false, true, false, 1, 0, -8);
        buffs.add(poison);
        spell = new Spell("lightingbolt", "Knock 8 to enemy Hero", "rectangle enemy H", 1250, 2, 1, 1, buffs, false);
        Shop.addAllCards(spell);
        setNullArray(buffs);
        poison = new Poison(true, false, true, false, false, true, 1, 1, -1);
        buffs.add(poison);
        spell = new Spell("poisonlake", "Create poison cell for 1 turn", "rectangle allcell", 900, 5, 3, 3, buffs, true);
        Shop.addAllCards(spell);
        setNullArray(buffs);
        power = new Power(false, false, false, false, 3, 4, 0);
        buffs.add(power);
        disarm = new Disarm(true, false, false, false, 3);
        buffs.add(disarm);
        spell = new Spell("mandess", "Add 3 AP to selected card but disarm", "rectangle friendly MH", 650, 0, 1, 1, buffs, false);
        Shop.addAllCards(spell);
        setNullArray(buffs);
        disarm = new Disarm(true, false, false, false, 1);
        buffs.add(disarm);
        spell = new Spell("alldisarm", "Disarm all of enemy", "allcell enemy MH", 2000, 9, 0, 0, buffs, false);
        Shop.addAllCards(spell);
        setNullArray(buffs);
        poison = new Poison(true, true, true, false, false, false, 4, 0, -1);
        buffs.add(poison);
        spell = new Spell("allpoison", "Poison all enemy for 4 turn", "allcell enemy MH", 1500, 8, 0, 0, buffs, false);
        Shop.addAllCards(spell);
        setNullArray(buffs);
        delete = new Delete(false, false, true, false, 1);
        buffs.add(delete);
        spell = new Spell("dispel", "Destroy positive and negative buff", "rectangle FE MH", 2100, 0, 1, 1, buffs, false);
        Shop.addAllCards(spell);
        setNullArray(buffs);
        Weakness weakness = new Weakness(true, false, false, false, 1, -6, 0);
        buffs.add(weakness);
        Holy holy = new Holy(true, true, false, false, 3);
        buffs.add(holy);
        buffs.add(holy);
        spell = new Spell("healthwithprofit", "Give weakness buff with 6 and 2 holy buff for 3 turn ", "rectangle friendly MH", 2250, 0, 1, 1, buffs, false);
        Shop.addAllCards(spell);
        setNullArray(buffs);
        power = new Power(true, false, true, false, 1, 2, 0);
        buffs.add(power);
        spell = new Spell("powerup", "Give power buff with 6 AP", "rectangle friendly MH", 2500, 4, 1, 1, buffs, false);
        Shop.addAllCards(spell);
        setNullArray(buffs);
        power = new Power(true, false, true, false, 1, 2, 0);
        buffs.add(power);
        spell = new Spell("allpower", "Give a power buff with 2 AP to all frienly", "allcell friendly MH", 2000, 4, 0, 0, buffs, false);
        Shop.addAllCards(spell);
        setNullArray(buffs);
        poison = new Poison(false, false, true, false, true, false, 1, 0, -6);
        buffs.add(poison);
        spell = new Spell("allattack", "Knock 6 to all enemy in one column", "generalcell enemy MH", 1500, 4, 1, 5, buffs, false);
        Shop.addAllCards(spell);
        setNullArray(buffs);
        weakness = new Weakness(true, false, true, false, 1, 0, -4);
        buffs.add(weakness);
        spell = new Spell("weakening", "Give weakness with 4 AP to minion", "rectangle enemy M", 1000, 1, 1, 1, buffs, false);
        Shop.addAllCards(spell);
        setNullArray(buffs);
        weakness = new Weakness(true, false, true, false, 1, -6, 0);
        buffs.add(weakness);
        power = new Power(true, false, true, false, 1, 8, 0);
        buffs.add(power);
        spell = new Spell("sacrifice", "Give weakness buff with 6 AP and power buff with 8 AP", "rectangle friendly M", 1600, 2, 1, 1, buffs, false);
        Shop.addAllCards(spell);
        setNullArray(buffs);
        Kill kill = new Kill();
        buffs.add(kill);
        spell = new Spell("kingsguard", "Kill Minion", "around H friendly M enemy", 1750, 9, 0, 0, buffs, false);
        Shop.addAllCards(spell);
        setNullArray(buffs);
        Stun stun = new Stun(true, false, false, false, 2);
        buffs.add(stun);
        spell = new Spell("shock", "stun for 2 turn", "rectangle enemy MH", 1200, 1, 1, 1, buffs, false);
        Shop.addAllCards(spell);
    }

    public static void setShopMinion() {
        ArrayList<Buff> buffs = new ArrayList<Buff>();
        Minion minion = new Minion("kamandarfars", "ranged", 300, 2, 6, 4, 7, null, "", "", "Don't have", false);
        Shop.addAllCards(minion);
        Stun stun = new Stun(true, false, false, false, 1);
        buffs.add(stun);
        minion = new Minion("shamshirzanfars", "melee", 400, 2, 6, 4, 1, buffs, "attack", "attackCell MH", "Stun for one turn", false);
        Shop.addAllCards(minion);
        setNullArray(buffs);
        minion = new Minion("neyzedarfars", "hybrid", 500, 1, 5, 3, 3, null, "", "", "Don't have", false);
        Shop.addAllCards(minion);
        minion = new Minion("asbsavarfars", "melee", 200, 4, 10, 6, 1, null, "", "", "Don't have", false);
        Shop.addAllCards(minion);
        AddAP addAP = new AddAP(false, false, true, false, 0);
        minion = new Minion("pahlavanfars", "melee", 600, 9, 24, 6, 1, buffs, "attack", "attackCell MH", "Increases by 5 AP per attack to one enemy", false);
        minion.addSpecialBuffs(addAP);
        Shop.addAllCards(minion);
        setNullArray(buffs);
        minion = new Minion("sepahsalarfars", "melee", 800, 7, 12, 4, 1, null, "combo", "", "combo", false);
        Shop.addAllCards(minion);
        minion = new Minion("kamandarturani", "ranged", 500, 1, 3, 4, 5, null, "", "", "Don't have", false);
        Shop.addAllCards(minion);
        minion = new Minion("qolabsangdarturani", "ranged", 600, 1, 4, 2, 7, null, "", "", "Don't have", false);
        Shop.addAllCards(minion);
        minion = new Minion("neyzedarturani", "hybrid", 600, 1, 4, 4, 3, null, "", "", "Don't have", false);
        Shop.addAllCards(minion);
        Disarm disarm = new Disarm(true, false, false, false, 1);
        buffs.add(disarm);
        Poison poison = new Poison(true, true, true, false, false, false, 4, 0, -1);
        buffs.add(poison);
        minion = new Minion("jasusturani", "melee", 700, 4, 6, 6, 1, buffs, "attack", "attackCell MH", "Disarm for one turn and poison for 4 turn", false);
        Shop.addAllCards(minion);
        setNullArray(buffs);
        minion = new Minion("gorzdarturani", "melee", 450, 2, 3, 10, 1, null, "", "", "Don't have", false);
        Shop.addAllCards(minion);
        minion = new Minion("shahzadeturani", "melee", 800, 6, 6, 10, 1, null, "combo", "", "combo", false);
        Shop.addAllCards(minion);
        minion = new Minion("divsiah", "hybrid", 300, 9, 14, 10, 7, null, "", "", "Don't have", false);
        Shop.addAllCards(minion);
        minion = new Minion("qulsangandaz", "ranged", 300, 9, 12, 12, 7, null, "", "", "Don't have", false);
        Shop.addAllCards(minion);
        setNullArray(buffs);
        Power power = new Power(true, true, true, false, 0, 0, 10);
        buffs.add(power);
        minion = new Minion("oqab", "ranged", 200, 2, 0, 2, 3, buffs, "passive", "I M", "Have 10 power buff with adding HP", true);
        Shop.addAllCards(minion);
        setNullArray(buffs);
        minion = new Minion("divgorazsavar", "melee", 300, 6, 16, 8, 1, null, "", "", "Don't have", false);
        Shop.addAllCards(minion);
        poison = new Poison(false, false, true, false, true, false, 0, 0, -2);
        buffs.add(poison);
        minion = new Minion("qultakcheshm", "hybrid", 500, 7, 12, 11, 3, buffs, "death", "around enemy M 1", "Knock 2 to all of neighber minion", true);
        Shop.addAllCards(minion);
        setNullArray(buffs);
        poison = new Poison(true, true, true, false, false, false, 3, 0, -1);
        buffs.add(poison);
        minion = new Minion("marsammi", "ranged", 300, 4, 5, 6, 4, buffs, "attack", "attackCell MH", "poison for 3 turn", false);
        Shop.addAllCards(minion);
        setNullArray(buffs);
        minion = new Minion("ejdehayeatashandaz", "ranged", 250, 5, 9, 5, 4, null, "", "", "Don't have", false);
        Shop.addAllCards(minion);
        UnHoly unHoly = new UnHoly(true, true, false, 0, 1);
        minion = new Minion("shirdarande", "melee", 600, 2, 1, 8, 1, buffs, "attack", "attackCell MH", "Don't effect Holy buff", true);
        minion.buffs.add(unHoly);
        Shop.addAllCards(minion);
        setNullArray(buffs);
        unHoly = new UnHoly(true, true, false, 0, 2);
        minion = new Minion("marqulpeykar", "ranged", 500, 8, 14, 7, 5, buffs, "spawn", "around enemy M 2", "Knock one more to minion with 2 cell distance", true);
        minion.buffs.add(unHoly);
        Shop.addAllCards(minion);
        setNullArray(buffs);
        ArrayList<Integer> addHP = new ArrayList<Integer>();
        addHP.add(0);
        addHP.add(6);
        addHP.add(4);
        VariableAddHP variableAddHP = new VariableAddHP(false, true, false, 2, addHP);
        buffs.add(variableAddHP);
        minion = new Minion("gorgsefid", "melee", 400, 5, 8, 2, 1, buffs, "attack", "attackCell M", "Add Health -6 for next turn and -4 for another turn", false);
        Shop.addAllCards(minion);
        setNullArray(buffs);
        addHP.remove(0);
        addHP.remove(0);
        addHP.remove(0);
        addHP.add(0);
        addHP.add(8);
        variableAddHP = new VariableAddHP(false, true, false, 1, addHP);
        buffs.add(variableAddHP);
        minion = new Minion("palang", "melee", 400, 4, 6, 2, 1, buffs, "attack", "attackCell M", "Add -8 HP to minion next turn", false);
        Shop.addAllCards(minion);
        setNullArray(buffs);
        addHP.remove(0);
        addHP.remove(0);
        addHP.add(0);
        addHP.add(6);
        variableAddHP = new VariableAddHP(false, true, false, 1, addHP);
        buffs.add(variableAddHP);
        minion = new Minion("gorg", "melee", 400, 3, 6, 1, 1, buffs, "attack", "attackCell M", "Add -6 HP in next turn", false);
        Shop.addAllCards(minion);
        setNullArray(buffs);
        power = new Power(true, false, false, false, 1, 2, 0);
        Weakness weakness = new Weakness(true, false, false, false, 1, -1, 0);
        buffs.add(power);
        buffs.add(weakness);
        minion = new Minion("jadugar", "ranged", 550, 4, 5, 4, 3, buffs, "passive", "around friendly M 1 and I", "Power buff with 2 add AP and weakness buff with -1 add HP", true);
        Shop.addAllCards(minion);
        setNullArray(buffs);
        power = new Power(true, false, true, false, 0, 2, 0);
        Holy holy = new Holy(true, false, true, true, 0);
        buffs.add(power);
        buffs.add(holy);
        minion = new Minion("jadugarazam", "ranged", 550, 6, 6, 6, 5, buffs, "passive", "around friendly M 1 and I", "Power buff with 2 add AP anf continue holy buff", true);
        Shop.addAllCards(minion);
        setNullArray(buffs);
        power = new Power(true, false, true, true, 0, 1, 0);
        buffs.add(power);
        minion = new Minion("jen", "ranged", 500, 5, 10, 4, 4, buffs, "passive", "all friendly M", "Continue power buff with 1 add AP", false);
        Shop.addAllCards(minion);
        setNullArray(buffs);
        poison = new Poison(false, false, true, false, false, false, 0, 0, -16);
        buffs.add(poison);
        minion = new Minion("bahman", "melee", 450, 8, 16, 9, 1, buffs, "spawn", "randomEnemy M", "Add -16 HP to rendom enemy minion", false);
        Shop.addAllCards(minion);
        setNullArray(buffs);
        minion = new Minion("eraj", "ranged", 500, 4, 6, 20, 3, null, "", "", "Don't have", false);
        Shop.addAllCards(minion);
        minion = new Minion("qulbozorg", "hybrid", 600, 9, 30, 8, 2, null, "", "", "Don't have", false);
        Shop.addAllCards(minion);
        Delete delete = new Delete(false, false, true, false, 1);
        buffs.add(delete);
        minion = new Minion("quldosar", "melee", 550, 4, 10, 4, 1, buffs, "attack", "attackCell MH", "Delete all positive buff", false);
        Shop.addAllCards(minion);
        setNullArray(buffs);
        stun = new Stun(true, false, false, false, 1);
        buffs.add(stun);
        minion = new Minion("nanesarma", "ranged", 500, 3, 3, 4, 5, buffs, "spawn", "around enemy M 1", "Stun all neighbor minion for one turn", true);
        Shop.addAllCards(minion);
        setNullArray(buffs);
        holy = new Holy(true, false, true, true, 0);
        for (int i = 0; i < 12; i++)
            buffs.add(holy);
        minion = new Minion("fuladzereh", "melee", 650, 3, 1, 1, 1, buffs, "passive", "I M", "Have 12 continue holy buff", true);
        Shop.addAllCards(minion);
        setNullArray(buffs);
        poison = new Poison(true, false, true, false, true, false, 0, 0, -6);
        buffs.add(poison);
        minion = new Minion("siavash", "melee", 350, 4, 8, 5, 1, buffs, "death", "randomEnemy H", "Knock 6 to enemy hero", false);
        Shop.addAllCards(minion);
        setNullArray(buffs);
        minion = new Minion("shahqul", "melee", 600, 5, 10, 4, 1, null, "combo", "", "combo", false);
        Shop.addAllCards(minion);
        minion = new Minion("arjangdiv", "melee", 600, 3, 6, 6, 1, null, "combo", "", "combo", false);
        Shop.addAllCards(minion);

    }

    public static void setShopHero() {

        ArrayList<Buff> buffs = new ArrayList<Buff>();
        Power power = new Power(true, false, true, false, 0, 4, 0);
        buffs.add(power);
        Hero hero = new Hero("divsefid", "melee", 8000, 1, 50, 4, 1, 2, "Give power buff with 4 AP to hero", buffs, "hero", true, false, false);
        Shop.addAllCards(hero);
        setNullArray(buffs);
        Stun stun = new Stun(true, false, false, false, 1);
        buffs.add(stun);
        hero = new Hero("simorq", "melee", 9000, 5, 50, 4, 1, 8, "Stun all of enemy for one turn", buffs, "all enemy", true, false, false);
        Shop.addAllCards(hero);
        setNullArray(buffs);
        Disarm disarm = new Disarm(true, false, false, false, 1);
        buffs.add(disarm);
        hero = new Hero("ejdehayehaftsar", "melee", 8000, 0, 50, 4, 1, 1, "Disarm one of enemy", buffs, "one enemy", true, false, false);
        Shop.addAllCards(hero);
        setNullArray(buffs);
        stun = new Stun(true, false, false, false, 1);
        buffs.add(stun);
        hero = new Hero("rakhsh", "melee", 8000, 1, 50, 4, 1, 2, "Stun one of enemy for one turn", buffs, "one enemy", true, false, false);
        Shop.addAllCards(hero);
        setNullArray(buffs);
        Poison poison = new Poison(true, false, false, false, false, false, 3, 0, -1);
        buffs.add(poison);
        hero = new Hero("zahak", "melee", 10000, 0, 50, 2, 1, 0, "Poison enemy for 3 turn", buffs, "", false, true, false);
        Shop.addAllCards(hero);
        setNullArray(buffs);
        Holy holy = new Holy(true, false, false, false, 1);
        buffs.add(holy);
        hero = new Hero("kave", "melee", 8000, 1, 50, 4, 1, 3, "Holy Cell for 3 turn", buffs, "cell", true, false, false);
        Shop.addAllCards(hero);
        setNullArray(buffs);
        poison = new Poison(false, false, true, false, true, false, 0, 0, -4);
        buffs.add(poison);
        hero = new Hero("arash", "ranged", 10000, 2, 30, 2, 6, 2, "Knock 4 to enemy", buffs, "row", true, false, false);
        Shop.addAllCards(hero);
        setNullArray(buffs);
        Delete delete = new Delete(false, false, true, false, 0);
        buffs.add(delete);
        hero = new Hero("afsane", "ranged", 11000, 1, 40, 3, 3, 2, "Dispel enemy", buffs, "one enemy", true, false, false);
        Shop.addAllCards(hero);
        setNullArray(buffs);
        holy = new Holy(true, false, true, true, 0);
        hero = new Hero("esfandiar", "hybrid", 12000, 0, 35, 3, 3, 0, "Have 3 continue holy buff", buffs, "", false, false, true);
        hero.addBuff(holy);
        hero.addBuff(holy);
        hero.addBuff(holy);
        Shop.addAllCards(hero);
        setNullArray(buffs);
        hero = new Hero("rostam", "hybrid", 8000, 0, 55, 7, 4, 0, "Don't have", buffs, "", false, false, false);
        Shop.addAllCards(hero);

    }

    public static void setShopItem() {

        ArrayList<Buff> buffs = new ArrayList<Buff>();
        ArrayList<Integer> integers = new ArrayList<Integer>();
        integers.add(1);
        integers.add(1);
        integers.add(1);
        AddMP addMP = new AddMP(3, integers, false);
        buffs.add(addMP);
        Item item = new Item("tajdanayi", 300, 0, "Add 1 MP for 3 turn", false, buffs, "first");
        Shop.addAllCards(item);
        setNullArray(buffs);
        Holy holy = new Holy(true, false, true, false, 0);
        for (int i = 0; i < 12; i++) {
            buffs.add(holy);
        }
        item = new Item("namussepar", 400, 0, "Give 12 Holy Buff to hero", false, buffs, "first");
        Shop.addAllCards(item);
        setNullArray(buffs);
        Disarm disarm = new Disarm(true, false, false, false, 1);
        buffs.add(disarm);
        item = new Item("kamandamol", 30000, 0, "Disarm when hero attack for one turn", false, buffs, "attack H");
        Shop.addAllCards(item);
        setNullArray(buffs);
        Power power = new Power(false, false, true, false, 0, 0, 6);
        buffs.add(power);
        item = new Item("nushdaru", 0, 0, "Add 6 HP", true, buffs, "random MH");
        Item.addCollectableItem(item);
        setNullArray(buffs);
        power = new Power(false, false, true, false, 0, 2, 0);
        buffs.add(power);
        item = new Item("tirdoshakh", 0, 0, "Add 2 AP", true, buffs, "rangedHybrid MH");
        Item.addCollectableItem(item);
        setNullArray(buffs);
        power = new Power(false, false, true, false, 0, -2, 0);
        buffs.add(power);
        item = new Item("parsimorq", 3500, 0, "Add -2 AP to enemy hero", false, buffs, "first");
        Shop.addAllCards(item);
        setNullArray(buffs);
        power = new Power(false, false, true, false, 0, 0, 3);
        buffs.add(power);
        Power power1 = new Power(true, false, true, false, 0, 3, 0);
        buffs.add(power);
        item = new Item("eksir", 0, 0, "Add 3 HP and Give power buff with 3 AP", true, buffs, "random M");
        Item.addCollectableItem(item);
        setNullArray(buffs);
        integers = new ArrayList<Integer>();
        integers.add(0);
        integers.add(3);
        addMP = new AddMP(2, integers, false);
        buffs.add(addMP);
        item = new Item("majunmana", 0, 0, "Add 3 MP next turn", true, buffs, "collect");
        Item.addCollectableItem(item);
        setNullArray(buffs);
        holy = new Holy(true, false, false, false, 2);
        for (int i = 0; i < 10; i++) {
            buffs.add(holy);
        }
        item = new Item("majunruyintani", 0, 0, "Give 10 holy buff for 2 turn", true, buffs, "random MH");
        Item.addCollectableItem(item);
        setNullArray(buffs);
        Poison poison = new Poison(false, false, true, false, true, false, 0, 0, -8);
        buffs.add(poison);
        item = new Item("nefrinmarg", 0, 0, "Add -8 HP on death", true, buffs, "death M");
        Item.addCollectableItem(item);
        setNullArray(buffs);
        power = new Power(false, false, true, false, 0, 2, 0);
        buffs.add(power);
        item = new Item("randomdamage", 0, 0, "Add 2 AP", true, buffs, "random MH");
        Item.addCollectableItem(item);
        setNullArray(buffs);
        Weakness weakness = new Weakness(true, false, false, false, 1, 0, -2);
        buffs.add(weakness);
        item = new Item("terrorhood", 5000, 0, "Give weakness with -2 AP", false, buffs, "random MH");
        Shop.addAllCards(item);
        setNullArray(buffs);
        power = new Power(false, false, true, false, 0, 6, 0);
        buffs.add(power);
        item = new Item("bladesofagility", 0, 0, "Add 6 AP", true, buffs, "first");
        Item.addCollectableItem(item);
        setNullArray(buffs);
        integers = new ArrayList<Integer>();
        integers.add(1);
        addMP = new AddMP(0, integers, true);
        buffs.add(addMP);
        item = new Item("kingwisdom", 9000, 0, "Add 1 MP for all turn", false, buffs, "first");
        Shop.addAllCards(item);
        setNullArray(buffs);
        poison = new Poison(false, false, true, false, true, false, 0, 0, -1);
        buffs.add(poison);
        item = new Item("assissnationdagger", 7000, 0, "Knock 1 to enemy hero", false, buffs, "addCard");
        Shop.addAllCards(item);
        setNullArray(buffs);
        poison = new Poison(true, true, false, false, false, false, 1, 0, -1);
        buffs.add(poison);
        item = new Item("poisonousdagger", 7000, 0, "Give poison buff to one turn to enemy", false, buffs, "attack MH");
        Shop.addAllCards(item);
        setNullArray(buffs);
        disarm = new Disarm(true, false, false, false, 1);
        buffs.add(disarm);
        item = new Item("shockhammer", 15000, 0, "Disarm enemy for one turn", false, buffs, "attack H");
        Shop.addAllCards(item);
        setNullArray(buffs);
        power = new Power(true, false, true, false, 0, 1, 0);
        buffs.add(power);
        item = new Item("souleater", 25000, 0, "Give power buff with 1 AP", false, buffs, "death MH");
        Shop.addAllCards(item);
        setNullArray(buffs);
        holy = new Holy(true, false, false, false, 2);
        buffs.add(holy);
        item = new Item("qosltamid", 20000, 0, "Give to holy buff to minion when spawn", false, buffs, "spawn M");
        Shop.addAllCards(item);
        setNullArray(buffs);
        power = new Power(false, false, true, false, 0, 5, 0);
        buffs.add(power);
        item = new Item("shamshirchini", 0, 0, "Add 5 AP to melee", true, buffs, "all melee");
        Item.addCollectableItem(item);

    }

    public static void main(String[] args) {

        try {
            setScannerUser();
            setShopSpell();
            setShopMinion();
            setShopHero();
            setShopItem();
            SingleStoryGame.setDeckForStage1();
            SingleStoryGame.setDeckFroStage2();
            SingleStoryGame.setDeckForStage3();
            MainMenu.loadAccounts();
            MainMenu.menu();
        } catch (Exception e) {
            System.out.println("Error!!");
        }

    }

}
