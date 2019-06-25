package sample;

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
        int collectable2 = random.nextInt(4);
        int collectable3 = random.nextInt(Item.getCollectableItems().size());

        if (collectable2 == 0) {
            collectable2 = 0;
        } else if (collectable2 == 1) {
            collectable2 = 6;
        } else if (collectable2 == 2) {
            collectable2 = 7;
        } else {
            collectable2 = 8;
        }

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
            scanner = new Scanner(new File("/home/shajusoni/IdeaProjects/APProjectGraph/account.txt"));
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
        Spell spell = new Spell("totaldisarm", "Disarm selected card until end", "rectangle enemy MH", 1000, 0, 1, 1, buffs, false,"totaldisarm");
        Shop.addAllCards(spell);
        setNullArray(buffs);
        Delete delete = new Delete(false, false, true, false, 1);
        buffs.add(delete);
        spell = new Spell("areadispel", "Destroy all positive, negative buff", "rectangle notallcell", 1500, 2, 2, 2, buffs, false,"areadispel");
        Shop.addAllCards(spell);
        setNullArray(buffs);
        Power power = new Power(false, false, true, false, 0, 2, 0);
        buffs.add(power);
        spell = new Spell("empower", "Add 2 AP to selected card", "rectangle friendly MH", 250, 1, 1, 1, buffs, false,"empower");
        Shop.addAllCards(spell);
        setNullArray(buffs);
        Poison poison = new Poison(false, false, true, false, true, false, 1, 0, -4);
        buffs.add(poison);
        spell = new Spell("fireball", "Knock 4 to selected card", "rectangle enemy MH", 400, 1, 1, 1, buffs, false,"fireball");
        Shop.addAllCards(spell);
        setNullArray(buffs);
        power = new Power(false, false, true, false, 1, 4, 0);
        buffs.add(power);
        spell = new Spell("godstrenght", "Add 4 AP to own hero", "rectangle friendly H", 450, 2, 1, 1, buffs, false,"godstrenght");
        Shop.addAllCards(spell);
        setNullArray(buffs);
        Fire fire = new Fire(false, false, true, false, 1, 2, -2);
        buffs.add(fire);
        spell = new Spell("hellfire", "Create fire cell 2 turn", "rectangle allcell", 600, 3, 2, 2, buffs, true,"hellfire");
        Shop.addAllCards(spell);
        setNullArray(buffs);
        poison = new Poison(false, false, true, false, true, false, 1, 0, -8);
        buffs.add(poison);
        spell = new Spell("lightingbolt", "Knock 8 to enemy Hero", "rectangle enemy H", 1250, 2, 1, 1, buffs, false,"lightingbolt");
        Shop.addAllCards(spell);
        setNullArray(buffs);
        poison = new Poison(true, false, true, false, false, true, 1, 1, -1);
        buffs.add(poison);
        spell = new Spell("poisonlake", "Create poison cell 1 turn", "rectangle allcell", 900, 5, 3, 3, buffs, true,"poisonlake");
        Shop.addAllCards(spell);
        setNullArray(buffs);
        power = new Power(false, false, false, false, 3, 4, 0);
        buffs.add(power);
        disarm = new Disarm(true, false, false, false, 3);
        buffs.add(disarm);
        spell = new Spell("mandess", "Add 3 AP selected card,disarm", "rectangle friendly MH", 650, 0, 1, 1, buffs, false,"mandess");
        Shop.addAllCards(spell);
        setNullArray(buffs);
        disarm = new Disarm(true, false, false, false, 1);
        buffs.add(disarm);
        spell = new Spell("alldisarm", "Disarm all of enemy", "allcell enemy MH", 2000, 9, 0, 0, buffs, false,"alldisarm");
        Shop.addAllCards(spell);
        setNullArray(buffs);
        poison = new Poison(true, true, true, false, false, false, 4, 0, -1);
        buffs.add(poison);
        spell = new Spell("allpoison", "Poison all enemy 4 turn", "allcell enemy MH", 1500, 8, 0, 0, buffs, false,"allpoison");
        Shop.addAllCards(spell);
        setNullArray(buffs);
        delete = new Delete(false, false, true, false, 1);
        buffs.add(delete);
        spell = new Spell("dispel", "Destroy positive, negative buff", "rectangle FE MH", 2100, 0, 1, 1, buffs, false,"dispel");
        Shop.addAllCards(spell);
        setNullArray(buffs);
        Weakness weakness = new Weakness(true, false, false, false, 1, -6, 0);
        buffs.add(weakness);
        Holy holy = new Holy(true, true, false, false, 3);
        buffs.add(holy);
        buffs.add(holy);
        spell = new Spell("healthwithprofit", "Give weakness 6,2 holy 3 turn ", "rectangle friendly MH", 2250, 0, 1, 1, buffs, false,"healthwithprofit");
        Shop.addAllCards(spell);
        setNullArray(buffs);
        power = new Power(true, false, true, false, 1, 2, 0);
        buffs.add(power);
        spell = new Spell("powerup", "Give power buff 6 AP", "rectangle friendly MH", 2500, 4, 1, 1, buffs, false,"powerup");
        Shop.addAllCards(spell);
        setNullArray(buffs);
        power = new Power(true, false, true, false, 1, 2, 0);
        buffs.add(power);
        spell = new Spell("allpower", "Give a buff 2 AP all frienly", "allcell friendly MH", 2000, 4, 0, 0, buffs, false,"allpower");
        Shop.addAllCards(spell);
        setNullArray(buffs);
        poison = new Poison(false, false, true, false, true, false, 1, 0, -6);
        buffs.add(poison);
        spell = new Spell("allattack", "Knock 6 all enemy in 1 column", "generalcell enemy MH", 1500, 4, 1, 5, buffs, false,"allattack");
        Shop.addAllCards(spell);
        setNullArray(buffs);
        weakness = new Weakness(true, false, true, false, 1, 0, -4);
        buffs.add(weakness);
        spell = new Spell("weakening", "Give weakness 4 AP minion", "rectangle enemy M", 1000, 1, 1, 1, buffs, false,"weakening");
        Shop.addAllCards(spell);
        setNullArray(buffs);
        weakness = new Weakness(true, false, true, false, 1, -6, 0);
        buffs.add(weakness);
        power = new Power(true, false, true, false, 1, 8, 0);
        buffs.add(power);
        spell = new Spell("sacrifice", "Give buff 6 AP,buff 8 AP", "rectangle friendly M", 1600, 2, 1, 1, buffs, false,"sacrifice");
        Shop.addAllCards(spell);
        setNullArray(buffs);
        Kill kill = new Kill();
        buffs.add(kill);
        spell = new Spell("kingsguard", "Kill Minion", "around H friendly M enemy", 1750, 9, 0, 0, buffs, false,"kingsguard");
        Shop.addAllCards(spell);
        setNullArray(buffs);
        Stun stun = new Stun(true, false, false, false, 4);
        buffs.add(stun);
        spell = new Spell("shock", "stun for 2 turn", "rectangle enemy MH", 1200, 1, 1, 1, buffs, false,"shock");
        Shop.addAllCards(spell);
    }

    public static void setShopMinion() {
        ArrayList<Buff> buffs = new ArrayList<Buff>();
        Minion minion = new Minion("kamandarfars", "ranged", 300, 2, 6, 4, 7, null, "", "", "Don't have special power", false,"kamandarfars");
        Shop.addAllCards(minion);
        Stun stun = new Stun(true, false, false, false, 1);
        buffs.add(stun);
        minion = new Minion("shamshirzanfars", "melee", 400, 2, 6, 4, 1, buffs, "attack", "attackCell MH", "Stun for one turn", false,"shamshirzanfars");
        Shop.addAllCards(minion);
        setNullArray(buffs);
        minion = new Minion("neyzedarfars", "hybrid", 500, 1, 5, 3, 3, null, "", "", "Don't have special power", false,"neyzedarfars");
        Shop.addAllCards(minion);
        minion = new Minion("asbsavarfars", "melee", 200, 4, 10, 6, 1, null, "", "", "Don't have special power", false,"asbsavarfars");
        Shop.addAllCards(minion);
        AddAP addAP = new AddAP(false, false, true, false, 0);
        minion = new Minion("pahlavanfars", "melee", 600, 9, 24, 6, 1, buffs, "attack", "attackCell MH", "Increases by 5 AP per attack enemy", false,"pahlavanfars");
        minion.addSpecialBuffs(addAP);
        Shop.addAllCards(minion);
        setNullArray(buffs);
        minion = new Minion("sepahsalarfars", "melee", 800, 7, 12, 4, 1, null, "combo", "", "combo", false,"sepahsalarfars");
        Shop.addAllCards(minion);
        minion = new Minion("kamandarturani", "ranged", 500, 1, 3, 4, 5, null, "", "", "Don't have special power", false,"kamandarturani");
        Shop.addAllCards(minion);
        minion = new Minion("qolabsangdarturani", "ranged", 600, 1, 4, 2, 7, null, "", "", "Don't have special power", false,"qolabsangdarturani");
        Shop.addAllCards(minion);
        minion = new Minion("neyzedarturani", "hybrid", 600, 1, 4, 4, 3, null, "", "", "Don't have special power", false,"neyzedarturani");
        Shop.addAllCards(minion);
        Disarm disarm = new Disarm(true, false, false, false, 1);
        buffs.add(disarm);
        Poison poison = new Poison(true, true, true, false, false, false, 4, 0, -1);
        buffs.add(poison);
        minion = new Minion("jasusturani", "melee", 700, 4, 6, 6, 1, buffs, "attack", "attackCell MH", "Disarm one turn,poison 4 turn", false,"jasusturani");
        Shop.addAllCards(minion);
        setNullArray(buffs);
        minion = new Minion("gorzdarturani", "melee", 450, 2, 3, 10, 1, null, "", "", "Don't have special power", false,"gorzdarturani");
        Shop.addAllCards(minion);
        minion = new Minion("shahzadeturani", "melee", 800, 6, 6, 10, 1, null, "combo", "", "combo", false,"shahzadeturani");
        Shop.addAllCards(minion);
        minion = new Minion("divsiah", "hybrid", 300, 9, 14, 10, 7, null, "", "", "Don't have special power", false,"divsiah");
        Shop.addAllCards(minion);
        minion = new Minion("qulsangandaz", "ranged", 300, 9, 12, 12, 7, null, "", "", "Don't have special power", false,"qulsangandaz");
        Shop.addAllCards(minion);
        setNullArray(buffs);
        Power power = new Power(true, true, true, false, 0, 0, 10);
        buffs.add(power);
        minion = new Minion("oqab", "ranged", 200, 2, 0, 2, 3, buffs, "passive", "I M", "Have 10 power with adding HP", true,"oqab");
        Shop.addAllCards(minion);
        setNullArray(buffs);
        minion = new Minion("divgorazsavar", "melee", 300, 6, 16, 8, 1, null, "", "", "Don't have special power", false,"divgorazsavar");
        Shop.addAllCards(minion);
        poison = new Poison(false, false, true, false, true, false, 0, 0, -2);
        buffs.add(poison);
        minion = new Minion("qultakcheshm", "hybrid", 500, 7, 12, 11, 3, buffs, "death", "around enemy M 1", "Knock 2 to all of neighbor minion", true,"qultakcheshm");
        Shop.addAllCards(minion);
        setNullArray(buffs);
        poison = new Poison(true, true, true, false, false, false, 3, 0, -1);
        buffs.add(poison);
        minion = new Minion("marsammi", "ranged", 300, 4, 5, 6, 4, buffs, "attack", "attackCell MH", "poison for 3 turn", false,"marsammi");
        Shop.addAllCards(minion);
        setNullArray(buffs);
        minion = new Minion("ejdehayeatashandaz", "ranged", 250, 5, 9, 5, 4, null, "", "", "Don't have special power", false,"ejdehayeatashandaz");
        Shop.addAllCards(minion);
        UnHoly unHoly = new UnHoly(true, true, false, 0, 1);
        minion = new Minion("shirdarande", "melee", 600, 2, 1, 8, 1, buffs, "attack", "attackCell MH", "Don't effect Holy buff", true,"shirdarande");
        minion.buffs.add(unHoly);
        Shop.addAllCards(minion);
        setNullArray(buffs);
        unHoly = new UnHoly(true, true, false, 0, 2);
        minion = new Minion("marqulpeykar", "ranged", 500, 8, 14, 7, 5, buffs, "spawn", "around enemy M 2", "Knock 1 more minion 2 cell distance", true,"marqulpeykar");
        minion.buffs.add(unHoly);
        Shop.addAllCards(minion);
        setNullArray(buffs);
        ArrayList<Integer> addHP = new ArrayList<Integer>();
        addHP.add(0);
        addHP.add(6);
        addHP.add(4);
        VariableAddHP variableAddHP = new VariableAddHP(true, true, false, 2, addHP);
        buffs.add(variableAddHP);
        minion = new Minion("gorgsefid", "melee", 400, 5, 8, 2, 1, buffs, "attack", "attackCell M", "Add -6 HP next turn,-4 another turn", false,"gorgsefid");
        Shop.addAllCards(minion);
        setNullArray(buffs);
        addHP.remove(0);
        addHP.remove(0);
        addHP.remove(0);
        addHP.add(0);
        addHP.add(8);
        variableAddHP = new VariableAddHP(true, true, false, 1, addHP);
        buffs.add(variableAddHP);
        minion = new Minion("palang", "melee", 400, 4, 6, 2, 1, buffs, "attack", "attackCell M", "Add -8 HP to minion next turn", false,"palang");
        Shop.addAllCards(minion);
        setNullArray(buffs);
        addHP.remove(0);
        addHP.remove(0);
        addHP.add(0);
        addHP.add(6);
        variableAddHP = new VariableAddHP(true, true, false, 1, addHP);
        buffs.add(variableAddHP);
        minion = new Minion("gorg", "melee", 400, 3, 6, 1, 1, buffs, "attack", "attackCell M", "Add -6 HP in next turn", false,"gorg");
        Shop.addAllCards(minion);
        setNullArray(buffs);
        power = new Power(true, false, false, false, 1, 2, 0);
        Weakness weakness = new Weakness(true, false, false, false, 1, -1, 0);
        buffs.add(power);
        buffs.add(weakness);
        minion = new Minion("jadugar", "ranged", 550, 4, 5, 4, 3, buffs, "passive", "around friendly M 1 and I", "Buff 2 add AP,buff -1 add HP", true,"jadugar");
        Shop.addAllCards(minion);
        setNullArray(buffs);
        power = new Power(true, false, true, false, 0, 2, 0);
        Holy holy = new Holy(true, false, true, true, 0);
        buffs.add(power);
        buffs.add(holy);
        minion = new Minion("jadugarazam", "ranged", 550, 6, 6, 6, 5, buffs, "passive", "around friendly M 1 and I", "Buff 2 add AP,continue holy buff", true,"jadugarazam");
        Shop.addAllCards(minion);
        setNullArray(buffs);
        power = new Power(true, false, true, true, 0, 1, 0);
        buffs.add(power);
        minion = new Minion("jen", "ranged", 500, 5, 10, 4, 4, buffs, "passive", "all friendly M", "Continue power buff,1 add AP", false,"jen");
        Shop.addAllCards(minion);
        setNullArray(buffs);
        poison = new Poison(false, false, true, false, false, false, 0, 0, -16);
        buffs.add(poison);
        minion = new Minion("bahman", "melee", 450, 8, 16, 9, 1, buffs, "spawn", "randomEnemy M", "Add -16 HP random enemy minion", false,"bahman");
        Shop.addAllCards(minion);
        setNullArray(buffs);
        minion = new Minion("eraj", "ranged", 500, 4, 6, 20, 3, null, "", "", "Don't have special power", false,"eraj");
        Shop.addAllCards(minion);
        minion = new Minion("qulbozorg", "hybrid", 600, 9, 30, 8, 2, null, "", "", "Don't have special power", false,"qulbozorg");
        Shop.addAllCards(minion);
        Delete delete = new Delete(false, false, true, false, 1);
        buffs.add(delete);
        minion = new Minion("quldosar", "melee", 550, 4, 10, 4, 1, buffs, "attack", "attackCell MH", "Delete all positive buff", false,"quldosar");
        Shop.addAllCards(minion);
        setNullArray(buffs);
        stun = new Stun(true, false, false, false, 1);
        buffs.add(stun);
        minion = new Minion("nanesarma", "ranged", 500, 3, 3, 4, 5, buffs, "spawn", "around enemy M 1", "Stun all neighbor minion 1 turn", true,"nanesarma");
        Shop.addAllCards(minion);
        setNullArray(buffs);
        holy = new Holy(true, false, true, true, 0);
        for (int i = 0; i < 12; i++)
            buffs.add(holy);
        minion = new Minion("fuladzereh", "melee", 650, 3, 1, 1, 1, buffs, "passive", "I M", "Have 12 continue holy buff", true,"fuladzereh");
        Shop.addAllCards(minion);
        setNullArray(buffs);
        poison = new Poison(true, false, true, false, true, false, 0, 0, -6);
        buffs.add(poison);
        minion = new Minion("siavash", "melee", 350, 4, 8, 5, 1, buffs, "death", "randomEnemy H", "Knock 6 to enemy hero", false,"siavash");
        Shop.addAllCards(minion);
        setNullArray(buffs);
        minion = new Minion("shahqul", "melee", 600, 5, 10, 4, 1, null, "combo", "", "combo", false,"shahqul");
        Shop.addAllCards(minion);
        minion = new Minion("arjangdiv", "melee", 600, 3, 6, 6, 1, null, "combo", "", "combo", false,"arjangdiv");
        Shop.addAllCards(minion);

    }

    public static void setShopHero() {

        ArrayList<Buff> buffs = new ArrayList<Buff>();
        Power power = new Power(true, false, true, false, 0, 4, 0);
        buffs.add(power);
        Hero hero = new Hero("divsefid", "melee", 8000, 1, 50, 4, 1, 4, "Give power buff 4 AP hero", buffs, "hero", true, false, false,"divsefid");
        Shop.addAllCards(hero);
        setNullArray(buffs);
        Stun stun = new Stun(true, false, false, false, 1);
        buffs.add(stun);
        hero = new Hero("simorq", "melee", 9000, 5, 50, 4, 1, 8, "Stun all enemy 1 turn", buffs, "all enemy", true, false, false,"simorq");
        Shop.addAllCards(hero);
        setNullArray(buffs);
        Disarm disarm = new Disarm(true, false, false, false, 1);
        buffs.add(disarm);
        hero = new Hero("ejdehayehaftsar", "melee", 8000, 0, 50, 4, 1, 1, "Disarm 1 of enemy", buffs, "one enemy", true, false, false,"ejdehayehaftsar");
        Shop.addAllCards(hero);
        setNullArray(buffs);
        stun = new Stun(true, false, false, false, 2);
        buffs.add(stun);
        hero = new Hero("rakhsh", "melee", 8000, 1, 50, 4, 1, 2, "Stun 1 enemy 1 turn", buffs, "one enemy", true, false, false,"rakhsh");
        Shop.addAllCards(hero);
        setNullArray(buffs);
        Poison poison = new Poison(true, false, false, false, false, false, 3, 0, -1);
        buffs.add(poison);
        hero = new Hero("zahak", "melee", 10000, 0, 50, 2, 1, 0, "Poison enemy 3 turn", buffs, "", false, true, false,"zahak");
        Shop.addAllCards(hero);
        setNullArray(buffs);
        Holy holy = new Holy(true, false, false, false, 1);
        buffs.add(holy);
        hero = new Hero("kave", "melee", 8000, 1, 50, 4, 1, 3, "Holy Cell for 3 turn", buffs, "cell", true, false, false,"kave");
        Shop.addAllCards(hero);
        setNullArray(buffs);
        poison = new Poison(false, false, true, false, true, false, 0, 0, -4);
        buffs.add(poison);
        hero = new Hero("arash", "ranged", 10000, 2, 30, 2, 6, 2, "Knock 4 to enemy", buffs, "row", true, false, false,"arash");
        Shop.addAllCards(hero);
        setNullArray(buffs);
        Delete delete = new Delete(false, false, true, false, 0);
        buffs.add(delete);
        hero = new Hero("afsane", "ranged", 11000, 1, 40, 3, 3, 2, "Dispel enemy", buffs, "one enemy", true, false, false,"afsane");
        Shop.addAllCards(hero);
        setNullArray(buffs);
        holy = new Holy(true, false, true, true, 0);
        hero = new Hero("esfandiar", "hybrid", 12000, 0, 35, 3, 3, 0, "Have 3 continue holy", buffs, "", false, false, true,"esfandiar");
        hero.addBuff(holy);
        hero.addBuff(holy);
        hero.addBuff(holy);
        Shop.addAllCards(hero);
        setNullArray(buffs);
        hero = new Hero("rostam", "hybrid", 8000, 0, 55, 7, 4, 0, "Don't have special power", buffs, "", false, false, false,"rostam");
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
        Item item = new Item("tajdanayi", 300, 0, "Add 1 MP for 3 turn", false, buffs, "first","tajdanayi");
        Shop.addAllCards(item);
        setNullArray(buffs);
        Holy holy = new Holy(true, false, true, false, 0);
        for (int i = 0; i < 12; i++) {
            buffs.add(holy);
        }
        item = new Item("namussepar", 400, 0, "Give 12 Holy Buff to hero", false, buffs, "first","namussepar");
        Shop.addAllCards(item);
        setNullArray(buffs);
        Disarm disarm = new Disarm(true, false, false, false, 1);
        buffs.add(disarm);
        item = new Item("kamandamol", 30000, 0, "Disarm when hero attack 1 turn", false, buffs, "attack H","kamandamol");
        Shop.addAllCards(item);
        setNullArray(buffs);
        Power power = new Power(false, false, true, false, 0, 0, 6);
        buffs.add(power);
        item = new Item("nushdaru", 0, 0, "Add 6 HP", true, buffs, "random MH","nushdaru");
        Item.addCollectableItem(item);
        setNullArray(buffs);
        power = new Power(false, false, true, false, 0, 2, 0);
        buffs.add(power);
        item = new Item("tirdoshakh", 0, 0, "Add 2 AP", true, buffs, "rangedHybrid MH","tirdoshakh");
        Item.addCollectableItem(item);
        setNullArray(buffs);
        power = new Power(false, false, true, false, 0, -2, 0);
        buffs.add(power);
        item = new Item("parsimorq", 3500, 0, "Add -2 AP enemy hero", false, buffs, "first","parsimorq");
        Shop.addAllCards(item);
        setNullArray(buffs);
        power = new Power(false, false, true, false, 0, 0, 3);
        buffs.add(power);
        Power power1 = new Power(true, false, true, false, 0, 3, 0);
        buffs.add(power);
        item = new Item("eksir", 0, 0, "Add 3 HP,Give power 3 AP", true, buffs, "random M","eksir");
        Item.addCollectableItem(item);
        setNullArray(buffs);
        integers = new ArrayList<Integer>();
        integers.add(0);
        integers.add(3);
        addMP = new AddMP(2, integers, false);
        buffs.add(addMP);
        item = new Item("majunmana", 0, 0, "Add 3 MP next turn", true, buffs, "collect","majunmana");
        Item.addCollectableItem(item);
        setNullArray(buffs);
        holy = new Holy(true, false, false, false, 2);
        for (int i = 0; i < 10; i++) {
            buffs.add(holy);
        }
        item = new Item("majunruyintani", 0, 0, "Give 10 holy 2 turn", true, buffs, "random MH","majunruyintani");
        Item.addCollectableItem(item);
        setNullArray(buffs);
        Poison poison = new Poison(false, false, true, false, true, false, 0, 0, -8);
        buffs.add(poison);
        item = new Item("nefrinmarg", 0, 0, "Add -8 HP on death", true, buffs, "death M","nefrinmarg");
        Item.addCollectableItem(item);
        setNullArray(buffs);
        power = new Power(false, false, true, false, 0, 2, 0);
        buffs.add(power);
        item = new Item("randomdamage", 0, 0, "Add 2 AP", true, buffs, "random MH","randomdamage");
        Item.addCollectableItem(item);
        setNullArray(buffs);
        Weakness weakness = new Weakness(true, false, false, false, 1, 0, -2);
        buffs.add(weakness);
        item = new Item("terrorhood", 5000, 0, "Give weakness with -2 AP", false, buffs, "random MH","terrorhood");
        Shop.addAllCards(item);
        setNullArray(buffs);
        power = new Power(false, false, true, false, 0, 6, 0);
        buffs.add(power);
        item = new Item("bladesofagility", 0, 0, "Add 6 AP", true, buffs, "random MH","bladesofagility");
        Item.addCollectableItem(item);
        setNullArray(buffs);
        integers = new ArrayList<Integer>();
        integers.add(1);
        addMP = new AddMP(0, integers, true);
        buffs.add(addMP);
        item = new Item("kingwisdom", 9000, 0, "Add 1 MP for all turn", false, buffs, "first","kingwisdom");
        Shop.addAllCards(item);
        setNullArray(buffs);
        poison = new Poison(false, false, true, false, true, false, 0, 0, -1);
        buffs.add(poison);
        item = new Item("assassnationdagger", 7000, 0, "Knock 1 to enemy hero", false, buffs, "addCard","assassnationdagger");
        Shop.addAllCards(item);
        setNullArray(buffs);
        poison = new Poison(true, true, false, false, false, false, 1, 0, -1);
        buffs.add(poison);
        item = new Item("poisonousdagger", 7000, 0, "Give poison 1 turn enemy", false, buffs, "attack MH","poisonousdagger");
        Shop.addAllCards(item);
        setNullArray(buffs);
        disarm = new Disarm(true, false, false, false, 1);
        buffs.add(disarm);
        item = new Item("shockhammer", 15000, 0, "Disarm enemy for one turn", false, buffs, "attack H","shockhammer");
        Shop.addAllCards(item);
        setNullArray(buffs);
        power = new Power(true, false, true, false, 0, 1, 0);
        buffs.add(power);
        item = new Item("souleater", 25000, 0, "Give power buff with 1 AP", false, buffs, "death MH","souleater");
        Shop.addAllCards(item);
        setNullArray(buffs);
        holy = new Holy(true, false, false, false, 2);
        buffs.add(holy);
        item = new Item("qosltamid", 20000, 0, "Give 2 holy minion when spawn", false, buffs, "spawn M","qosltamid");
        Shop.addAllCards(item);
        setNullArray(buffs);
        power = new Power(false, false, true, false, 0, 5, 0);
        buffs.add(power);
        item = new Item("shamshirchini", 0, 0, "Add 5 AP to melee", true, buffs, "all melee","shamshirchini");
        Item.addCollectableItem(item);

    }

    public static void fisrt() {

        setScannerUser();
        setShopSpell();
        setShopMinion();
        setShopHero();
        setShopItem();
        SingleStoryGame.setDeckForStage1();
        SingleStoryGame.setDeckFroStage2();
        SingleStoryGame.setDeckForStage3();
        MainMenu.loadAccounts();

    }

}
