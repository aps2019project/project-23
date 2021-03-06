package sample;

import java.util.ArrayList;
import java.util.Random;
import java.util.jar.Manifest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GameController {

    private static ArrayList<Card> handPlayer1 = new ArrayList<Card>(5);
    private static ArrayList<Card> handPlayer2 = new ArrayList<Card>(5);
    private static ArrayList<Item> collectableItem1 = new ArrayList<Item>();
    private static ArrayList<Item> collectableItem2 = new ArrayList<Item>();
    private static ArrayList<Card> graveYard1 = new ArrayList<Card>();
    private static ArrayList<Card> graveYard2 = new ArrayList<Card>();
    private static Card selectedCard;
    private static Item selectedItem;
    protected static Deck player1Deck;
    protected static Deck player2Deck;
    public static int turnOfSpecialPower1;
    public static int turnOfSpecialPower2;
    private static int turn;

    public static ArrayList<Item> getCollectableItem1() {
        return collectableItem1;
    }

    public static ArrayList<Item> getCollectableItem2() {
        return collectableItem2;
    }

    public static void addTurn() {
        turn++;
    }

    public static void setFirstAll() {

        handPlayer2.clear();
        handPlayer1.clear();
        collectableItem2.clear();
        collectableItem1.clear();
        selectedCard = null;
        selectedItem = null;
        player1Deck = null;
        player2Deck = null;
        turnOfSpecialPower1 = 0;
        turnOfSpecialPower2 = 0;
        graveYard1.clear();
        graveYard2.clear();
        turn = 1;

    }

    public static ArrayList<Card> getHandPlayer2() {
        return handPlayer2;
    }

    public static ArrayList<Card> getHandPlayer1() {
        return handPlayer1;
    }

    public static void setPlayer2Deck(Deck player2Deck) {
        GameController.player2Deck = player2Deck;
    }

    public static void setPlayer1Deck(Deck player1Deck) {
        GameController.player1Deck = player1Deck;
    }

    public static void showFlag() {

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 5; j++) {
                if (Main.getCardsCell()[i][j] instanceof Item) {
                    if (((Item) Main.getCardsCell()[i][j]).name.matches("flag")) {
                        System.out.printf("Flag is in location (%d,%d) and dose not belong to anyone\n", i + 1, j + 1);
                        return;
                    }
                }
            }
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 5; j++) {
                if (Main.getCardsCell()[i][j] == null) {
                    continue;
                }
                if (Main.getCardsCell()[i][j] instanceof Item) {
                    continue;
                }
                if (Main.getCardsCell()[i][j].getFlags().size() > 0) {
                    if (Main.getCardsCell()[i][j].numberOfPlayer == 1) {
                        System.out.printf("Flag belong to player 1 in location (%d,%d) with card [%s]\n", i + 1, j + 1, Main.getCardsCell()[i][j].cardID);
                    } else {
                        System.out.printf("Flag belong to player 2 in location (%d,%d) with card [%s]\n", i + 1, j + 1, Main.getCardsCell()[i][j].cardID);
                    }
                }
            }
        }

    }

    public static void showAllFlag() {

        int counter = 1;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 5; j++) {
                if (Main.getCardsCell()[i][j] == null) {
                    continue;
                }
                if (Main.getCardsCell()[i][j] instanceof Item) {
                    if (Main.getCardsCell()[i][j].name.matches("flag")) {
                        System.out.printf("%d. Flah in cell with location (%d,%d)\n", counter, i + 1, j + 1);
                        counter++;
                    }
                    continue;
                }
                if (Main.getCardsCell()[i][j].getFlags().size() == 0) {
                    continue;
                }
                if (Main.getCardsCell()[i][j].numberOfPlayer == 1) {
                    System.out.printf("%d. [%s] from player 1 has %d flag in location (%d,%d)\n", counter, Main.getCardsCell()[i][j].cardID, Main.getCardsCell()[i][j].getFlags().size(), i + 1, j + 1);
                    counter++;
                } else {
                    System.out.printf("%d. [%s] from player 2 has %d flag in location (%d,%d)\n", counter, Main.getCardsCell()[i][j].cardID, Main.getCardsCell()[i][j].getFlags().size(), i + 1, j + 1);
                    counter++;
                }

            }
        }

    }

    public static void gameInfo(int mode, Deck player1Deck, Deck player2Deck) {

        if (mode == 1) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 5; j++) {
                    if (Main.getCardsCell()[i][j] instanceof Hero) {
                        if (Main.getCardsCell()[i][j].numberOfPlayer == 1) {
                            System.out.printf("HP of Hero of Player_1 : %d\n", ((Hero) Main.getCardsCell()[i][j]).getHP());
                        } else {
                            System.out.printf("HP of Hero of Player_2 : %d\n", ((Hero) Main.getCardsCell()[i][j]).getHP());
                        }
                    }
                }
            }
        } else if (mode == 2) {
            showFlag();
        } else {
            showAllFlag();
        }

    }

    public static void setMP(Account account, Account account1) {

        if (turn % 2 == 1) {
            switch (turn) {
                case 3:
                    account.setMP(3);
                    break;
                case 5:
                    account.setMP(4);
                    break;
                case 7:
                    account.setMP(5);
                    break;
                case 9:
                    account.setMP(6);
                    break;
                case 11:
                    account.setMP(7);
                    break;
                case 13:
                    account.setMP(8);
                    break;
            }
            if (turn > 14) {
                account.setMP(9);
            }
        } else {
            switch (turn) {
                case 2:
                    account1.setMP(3);
                    break;
                case 4:
                    account1.setMP(4);
                    break;
                case 6:
                    account1.setMP(5);
                    break;
                case 8:
                    account1.setMP(6);
                    break;
                case 10:
                    account1.setMP(7);
                    break;
                case 12:
                    account1.setMP(8);
                    break;
            }
            if (turn > 12) {
                account1.setMP(9);
            }
        }

    }

    public static void setFlagInCell(int x, int y, Card card) {

        Random random = new Random();
        int x1, y1;
        for (Item item : card.getFlags()) {
            if (card.numberOfPlayer == 1) {
                collectableItem1.remove(item);
            } else {
                collectableItem2.remove(item);
            }
        }
        Main.getCardsCell()[x][y] = card.getFlags().get(0);
        card.getFlags().remove(0);
        for (Item item : card.getFlags()) {
            do {
                x1 = random.nextInt(9);
                y1 = random.nextInt(5);
            } while (Main.getCardsCell()[x1][y1] != null);
            Main.getCardsCell()[x1][y1] = item;
        }
        card.getFlags().clear();

    }

    public static void death() {

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 5; j++) {
                if (Main.getCardsCell()[i][j] == null) {
                    continue;
                }
                if (Main.getCardsCell()[i][j] instanceof Hero) {
                    if (((Hero) Main.getCardsCell()[i][j]).getHP() <= 0) {
                        if (Main.getCardsCell()[i][j].getFlags().size() > 0) {
                            setFlagInCell(i, j, Main.getCardsCell()[i][j]);
                        }
                        if (Main.getCardsCell()[i][j].numberOfPlayer == 1) {
                            graveYard1.add(Main.getCardsCell()[i][j]);
                            Main.getCardsCell()[i][j] = null;
                        } else {
                            graveYard2.add(Main.getCardsCell()[i][j]);
                            Main.getCardsCell()[i][j] = null;
                        }
                    }
                } else if (Main.getCardsCell()[i][j] instanceof Minion) {
                    if (((Minion) Main.getCardsCell()[i][j]).getHP() <= 0) {
                        if (((Minion) Main.getCardsCell()[i][j]).getSpecialPower().matches("death")) {
                            ((Minion) Main.getCardsCell()[i][j]).deathPower(i, j);
                        }
                        if (Main.getCardsCell()[i][j].getFlags().size() > 0) {
                            setFlagInCell(i, j, Main.getCardsCell()[i][j]);
                        }
                        if (Main.getCardsCell()[i][j].numberOfPlayer == 1) {
                            graveYard1.add(Main.getCardsCell()[i][j]);
                            Main.getCardsCell()[i][j] = null;
                        } else {
                            graveYard2.add(Main.getCardsCell()[i][j]);
                            Main.getCardsCell()[i][j] = null;
                        }

                    }
                }
            }
        }

    }

    public static void setAccount(Deck deck, int numberOfPlayer, Account account) {
        for (Card card : deck.getDeckCard()) {
            card.setNumberOfPlayer(numberOfPlayer);
        }
        account.setMP(2);
    }

    public static void setCellCard(Deck player1Deck, Deck player2Deck) {

        for (Card card : player1Deck.getDeckCard()) {
            if (card instanceof Hero) {
                Main.getCardsCell()[0][2] = card;
            }
        }
        for (Card card : player2Deck.getDeckCard()) {
            if (card instanceof Hero) {
                Main.getCardsCell()[8][2] = card;
            }
        }

        for (int i = 0; i < player1Deck.getDeckCard().size(); i++) {
            if (player1Deck.getDeckCard().get(i).name.matches("parsimorq")) {
                ((Hero) Main.getCardsCell()[0][2]).addAP(2);
            }
        }

        for (int i = 0; i < player2Deck.getDeckCard().size(); i++) {
            if (player2Deck.getDeckCard().get(i).name.matches("parsimorq")) {
                ((Hero) Main.getCardsCell()[8][2]).addAP(2);
            }
        }

    }

    public static void setRandomHand(ArrayList<Card> hand, Deck deck) {

        Random random = new Random();
        int randomNumber;
        for (int i = 0; i < 5; i++) {
            do {
                randomNumber = random.nextInt(deck.getDeckCard().size());
            } while (deck.getDeckCard().get(randomNumber) instanceof Item);
            hand.add(deck.getDeckCard().get(randomNumber));
            deck.getDeckCard().remove(randomNumber);
        }

    }

    public static void setRandomDeck(Deck deck) {

        Random random = new Random();
        int randomNumber;
        for (int i = 0; i < 7; i++) {
            randomNumber = random.nextInt(deck.getDeckCard().size());
            deck.getDeckCard().add(0, deck.getDeckCard().get(randomNumber));
            deck.getDeckCard().remove(randomNumber + 1);
        }

    }

    public static void showMyMinion(Account account) {

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 5; j++) {
                if (Main.getCardsCell()[i][j] == null) {
                    continue;
                }
                if (Main.getCardsCell()[i][j].numberOfPlayer == account.getNumberOfPlayer()) {
                    System.out.printf("cardID : %s, ", Main.getCardsCell()[i][j].cardID);
                    if (Main.getCardsCell()[i][j] instanceof Hero) {
                        System.out.printf("health : %d, ", ((Hero) Main.getCardsCell()[i][j]).getHP());
                        System.out.printf("location : [%d,%d], ", i + 1, j + 1);
                        System.out.printf("power : %d\n", ((Hero) Main.getCardsCell()[i][j]).getAP());
                    } else if (Main.getCardsCell()[i][j] instanceof Minion) {
                        System.out.printf("health : %d, ", ((Minion) Main.getCardsCell()[i][j]).getHP());
                        System.out.printf("location : [%d,%d], ", i + 1, j + 1);
                        System.out.printf("power : %d\n", ((Minion) Main.getCardsCell()[i][j]).getAP());
                    }
                }
            }
        }

    }

    public static Card getCardInCell(String cardID, ArrayList<Card> handPlayer1) {

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 5; j++) {
                if (Main.getCardsCell()[i][j] != null && Main.getCardsCell()[i][j].cardID.matches(cardID)) {
                    return Main.getCardsCell()[i][j];
                }
            }
        }

        for (int i = 0; i < handPlayer1.size(); i++) {
            if (handPlayer1.get(i).cardID.matches(cardID)) {
                return handPlayer1.get(i);
            }
        }
        return null;

    }

    public static void showCardInfo(String cardID, ArrayList<Card> handPlayer1) {

        if (getCardInCell(cardID, handPlayer1) == null) {
            System.out.printf("Don't exist this card");
            return;
        }
        Card card = getCardInCell(cardID, handPlayer1);

        if (card instanceof Hero) {
            System.out.println("Hero :");
            System.out.printf("     Name : %s\n", card.name);
            System.out.printf("     Cost : %d\n", card.cost);
            System.out.printf("     Desc : %s\n", ((Hero) card).getSpecialPower());
            System.out.printf("     MP : %d\n", card.MP);
            System.out.printf("     HP : %d\n", ((Hero) card).getHP());
            System.out.printf("     AP : %d\n", ((Hero) card).getAP());
        } else if (card instanceof Minion) {
            System.out.println("Minion :");
            System.out.printf("     Name : %s\n", card.name);
            System.out.printf("     Cost : %d\n", card.cost);
            System.out.printf("     HP : %d AP : %d MP : %d\n", ((Minion) card).getHP(), ((Minion) card).getAP(), card.MP);
            System.out.printf("     Desc : %s\n", ((Minion) card).getSpecialPower());
            System.out.printf("     Combo-ability : ");
            if (((Minion) card).getSpecialPower().matches("combo")) {
                System.out.printf("Have\n");
            } else {
                System.out.printf("Don't have");
            }
            System.out.printf("     Range : %d\n", ((Minion) card).getAttackRange());
        } else if (card instanceof Spell) {
            System.out.println("Spell : ");
            System.out.printf("     Name : %s\n", card.name);
            System.out.printf("     MP : %d\n", card.MP);
            System.out.printf("     Cost : %d\n", card.cost);
            System.out.printf("     Desc : %s\n", ((Spell) card).getDesc());
        }

    }

    public static void selectCard(String cardID, Account account) {
        boolean existCard = false;
        int x = 0, y = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 5; j++) {
                if (Main.getCardsCell()[i][j] == null) {
                    continue;
                }
                if (Main.getCardsCell()[i][j].cardID.matches(cardID)) {
                    existCard = true;
                    x = i;
                    y = j;
                }
            }
        }
        if (!existCard) {
            System.out.println("Invalid card");
            return;
        }
        if (Main.getCardsCell()[x][y].numberOfPlayer != account.getNumberOfPlayer()) {
            System.out.println("This card is not for you");
            return;
        }
        selectedCard = Main.getCardsCell()[x][y];

    }

    public static boolean checkMove(int x1, int y1, int x2, int y2) {

        if (y1 == y2) {
            if (x1 > x2) {
                if (Main.getCardsCell()[x1 - 1][y1] != null && !(Main.getCardsCell()[x1 - 1][y1] instanceof Item)) {
                    return false;
                }
            } else {
                if (Main.getCardsCell()[x1 + 1][y1] != null && !(Main.getCardsCell()[x1 + 1][y1] instanceof Item)) {
                    return false;
                }
            }
        } else if (x1 == x2) {
            if (y1 > y2) {
                if (Main.getCardsCell()[x1][y1 - 1] != null && !(Main.getCardsCell()[x1][y1 - 1] instanceof Item)) {
                    return false;
                }
            } else {
                if (Main.getCardsCell()[x1][y1 + 1] != null && !(Main.getCardsCell()[x1][y1 + 1] instanceof Item)) {
                    return false;
                }
            }
        } else {
            if (x1 == x2 + 1 && y1 == y2 + 1) {
                if (Main.getCardsCell()[x1 - 1][y1] != null && !(Main.getCardsCell()[x1 - 1][y1] instanceof Item)) {
                    if (Main.getCardsCell()[x1][y1 - 1] != null && !(Main.getCardsCell()[x1][y1 - 1] instanceof Item)) {
                        return false;
                    }
                }
            } else if (x1 == x2 + 1 && y1 == y2 - 1) {
                if (Main.getCardsCell()[x1 - 1][y1] != null && !(Main.getCardsCell()[x1 - 1][y1] instanceof Item)) {
                    if (Main.getCardsCell()[x1][y1 + 1] != null && !(Main.getCardsCell()[x1][y1 + 1] instanceof Item)) {
                        return false;
                    }
                }
            } else if (x1 == x2 - 1 && y1 == y2 + 1) {
                if (Main.getCardsCell()[x1 + 1][y1] != null && !(Main.getCardsCell()[x1 + 1][y1] instanceof Item)) {
                    if (Main.getCardsCell()[x1][y1 - 1] != null && !(Main.getCardsCell()[x1][y1 - 1] instanceof Item)) {
                        return false;
                    }
                }
            } else {
                if (Main.getCardsCell()[x1 + 1][y1] != null && !(Main.getCardsCell()[x1 + 1][y1] instanceof Item)) {
                    if (Main.getCardsCell()[x1][y1 + 1] != null && !(Main.getCardsCell()[x1][y1 + 1] instanceof Item)) {
                        return false;
                    }
                }
            }
        }
        return true;

    }

    public static int[] getLocation(String cardID) {

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 5; j++) {
                if (Main.getCardsCell()[i][j] == null) {
                    continue;
                }
                if (Main.getCardsCell()[i][j].cardID.matches(cardID)) {
                    int[] cell = {i, j};
                    return cell;
                }
            }
        }
        return null;

    }

    public static void addCollectableItem(int x, int y, Account account, ArrayList<Item> collectableItem) {
        String cardID;
        int counter = 0;
        for (int i = 0; i < collectableItem.size(); i++) {
            if (collectableItem.get(i).name.matches(((Item) Main.getCardsCell()[x][y]).name)) {
                counter++;
            }
        }
        cardID = account.getUsername() + "_" + Main.getCardsCell()[x][y].name + "_" + (counter + 1);
        Main.getCardsCell()[x][y].setCardID(cardID);
        collectableItem.add(((Item) Main.getCardsCell()[x][y]));
        Main.getCardsCell()[x][y] = null;
    }

    public static void moveCard(int x, int y, Account account, boolean type) {

        int xOfCard = getLocation(selectedCard.cardID)[0];
        int yOfCard = getLocation(selectedCard.cardID)[1];

        if (selectedCard instanceof Hero) {
            if (!((Hero) selectedCard).isOnOrOf()) {
                System.out.println("Can't move");
                return;
            }
        } else if (selectedCard instanceof Minion) {
            if (!((Minion) selectedCard).isOnOrOf()) {
                System.out.println("Can't move");
                return;
            }
        }

        if (x == xOfCard && y == yOfCard) {
            selectedCard = null;
            return;
        }
        if (selectedCard.move || selectedCard.attack) {
            if (type) {
                System.out.println("Can't move");
            }
            selectedCard = null;
            return;
        }
        if (Main.getCardsCell()[x][y] != null && !(Main.getCardsCell()[x][y] instanceof Item)) {
            if (type) {
                System.out.println("Invalid target");
            }
            selectedCard = null;
            return;
        }
        if (Math.abs(x - xOfCard) + Math.abs(y - yOfCard) > 2) {
            if (type) {
                System.out.println("Invalid target");
            }
            selectedCard = null;
            return;
        }
        if (!checkMove(xOfCard, yOfCard, x, y)) {
            if (type) {
                System.out.println("Invalid target");
            }
            selectedCard = null;
            return;
        }
        if (Main.getCardsCell()[x][y] instanceof Item) {
            if (Main.getCardsCell()[x][y].name.matches("flag")) {
                selectedCard.addFlag(((Item) Main.getCardsCell()[x][y]));
            }
            if (account.getNumberOfPlayer() == 1) {
                Main.getCardsCell()[x][y].setNumberOfPlayer(1);
                addCollectableItem(x, y, account, collectableItem1);
            } else {
                Main.getCardsCell()[x][y].setNumberOfPlayer(2);
                addCollectableItem(x, y, account, collectableItem2);
            }
        }
        if (Main.getBuffCell()[x][y] != null) {
            selectedCard.addBuff(Main.getBuffCell()[x][y].copyBuff());
            if ((Main.getBuffCell()[x][y] instanceof Poison)) {
                Main.getBuffCell()[x][y].effectBuffsOnCard(selectedCard, selectedCard.numberOfPlayer);
            }
        }
        selectedCard.setMove(true);
        Main.getCardsCell()[getLocation(selectedCard.cardID)[0]][getLocation(selectedCard.cardID)[1]] = null;
        Main.getCardsCell()[x][y] = selectedCard;
        if (type) {
            System.out.printf("[%s] moved to [%d] [%d]\n", selectedCard.cardID, x + 1, y + 1);
        }
        selectedCard = null;

    }

    public static void attack(String cardID, Account account, boolean type) {

        int xOfAttacker = getLocation(selectedCard.cardID)[0];
        int yOfAttacker = getLocation(selectedCard.cardID)[1];
        int x = getLocation(cardID)[0];
        int y = getLocation(cardID)[1];

        if (selectedCard instanceof Hero) {
            if (!((Hero) selectedCard).isOnOrOf()) {
                System.out.println("Can't attack");
                return;
            }
        } else if (selectedCard instanceof Minion) {
            if (!((Minion) selectedCard).isOnOrOf()) {
                System.out.println("Can't attack");
                return;
            }
        }

        if (selectedCard.isAttack()) {
            if (type) {
                System.out.printf("Card with [%s] can't attack\n", selectedCard.cardID);
            }
            selectedCard = null;
            return;
        }
        if (getLocation(cardID) == null) {
            if (type) {
                System.out.println("Invalid card id");
            }
            selectedCard = null;
            return;
        }
        if (Main.getCardsCell()[getLocation(cardID)[0]][getLocation(cardID)[1]].numberOfPlayer == account.getNumberOfPlayer()) {
            if (type) {
                System.out.println("Invalid card id");
            }
            selectedCard = null;
            return;
        }
        if (selectedCard instanceof Minion) {
            if (((Minion) selectedCard).getClas().matches("melee")) {
                if (Math.abs(x - xOfAttacker) > 1 || Math.abs(y - yOfAttacker) > 1) {
                    if (type) {
                        System.out.println("opponent minion is unavailable for attack");
                    }
                    selectedCard = null;
                    return;
                }
            } else if (((Minion) selectedCard).getClas().matches("ranged")) {
                if ((Math.abs(x - xOfAttacker) < 2 && Math.abs(y - yOfAttacker) < 2) || Math.abs(x - xOfAttacker) > ((Minion) selectedCard).getAttackRange() || Math.abs(y - yOfAttacker) > ((Minion) selectedCard).getAttackRange()) {
                    if (type) {
                        System.out.println("opponent minion is unavailable for attack");
                    }
                    selectedCard = null;
                    return;
                }
            } else if (((Minion) selectedCard).getClas().matches("hybrid")) {
                if (Math.abs(x - xOfAttacker) > ((Minion) selectedCard).getAttackRange() || Math.abs(y - yOfAttacker) > ((Minion) selectedCard).getAttackRange()) {
                    if (type) {
                        System.out.println("opponent minion is unavailable for attack");
                    }
                    selectedCard = null;
                    return;
                }
            }
        } else if (selectedCard instanceof Hero) {
            if (((Hero) selectedCard).getClas().matches("melee")) {
                if (Math.abs(x - xOfAttacker) > 1 || Math.abs(y - yOfAttacker) > 1) {
                    if (type) {
                        System.out.println("opponent minion is unavailable for attack");
                    }
                    selectedCard = null;
                    return;
                }
            } else if (((Hero) selectedCard).getClas().matches("ranged")) {
                if ((Math.abs(x - xOfAttacker) < 2 && Math.abs(y - yOfAttacker) < 2) || Math.abs(x - xOfAttacker) > ((Hero) selectedCard).getAttackRange() || Math.abs(y - yOfAttacker) > ((Hero) selectedCard).getAttackRange()) {
                    if (type) {
                        System.out.println("opponent minion is unavailable for attack");
                    }
                    selectedCard = null;
                    return;
                }
            } else if (((Hero) selectedCard).getClas().matches("hybrid")) {
                if (Math.abs(x - xOfAttacker) > ((Hero) selectedCard).getAttackRange() || Math.abs(y - yOfAttacker) > ((Hero) selectedCard).getAttackRange()) {
                    if (type) {
                        System.out.println("opponent minion is unavailable for attack");
                    }
                    selectedCard = null;
                    return;
                }
            }
        }
        if (selectedCard instanceof Minion) {
            ((Minion) selectedCard).attack(x, y);
            selectedCard.setAttack(true);
        } else if (selectedCard instanceof Hero) {
            ((Hero) selectedCard).attack(x, y);
            selectedCard.setAttack(true);
        }

    }

    public static boolean existCardID(String cardID) {

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 5; j++) {
                if (Main.getCardsCell()[i][j] == null) {
                    continue;
                }
                if (Main.getCardsCell()[i][j].cardID.matches(cardID)) {
                    return true;
                }
            }
        }
        return false;

    }

    public static Card getCardInGame(String cardID) {

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 5; j++) {
                if (Main.getCardsCell()[i][j] == null) {
                    continue;
                }
                if (Main.getCardsCell()[i][j].cardID.matches(cardID)) {
                    return Main.getCardsCell()[i][j];
                }
            }
        }
        return null;

    }

    public static void comboAttack(ArrayList<String> myCardID, String opponentCardID, Account account) {

        if (!existCardID(opponentCardID)) {
            System.out.printf("Don't exist [%s]\n", opponentCardID);
            return;
        }
        for (String string : myCardID) {
            if (!existCardID(string)) {
                System.out.printf("Don't exist [%s]\n", string);
                return;
            }
            if (getCardInGame(string) != null) {
                if (getCardInGame(string).numberOfPlayer != account.getNumberOfPlayer()) {
                    System.out.printf("[%s] don't for you\n", string);
                    return;
                }
            }
        }
        if (getCardInGame(opponentCardID) != null) {
            if (getCardInGame(opponentCardID).numberOfPlayer == account.getNumberOfPlayer()) {
                System.out.printf("[%s] don't your opponent\n", opponentCardID);
                return;
            }
        }
        for (String string : myCardID) {
            if (getCardInGame(string) != null) {
                if (!(getCardInGame(string) instanceof Minion)) {
                    System.out.printf("[%s] can't combo attack\n", string);
                    return;
                } else if (!(((Minion) getCardInGame(string)).getSpecialPower().matches("combo"))) {
                    System.out.printf("[%s] can't combo attack\n", string);
                    return;
                }
            }
        }
        int x = getLocation(opponentCardID)[0];
        int y = getLocation(opponentCardID)[1];

        for (String string : myCardID) {

            if (getCardInGame(string) instanceof Minion) {
                int xOfAttacker = getLocation(string)[0];
                int yOfAttacker = getLocation(string)[1];
                if (((Minion) getCardInGame(string)).getClas().matches("melee")) {
                    if (Math.abs(x - xOfAttacker) > 1 || Math.abs(y - yOfAttacker) > 1) {
                        System.out.println("opponent minion is unavailable for attack");
                        return;
                    }
                } else if (((Minion) getCardInGame(string)).getClas().matches("ranged")) {
                    if ((Math.abs(x - xOfAttacker) < 2 && Math.abs(y - yOfAttacker) < 2) || Math.abs(x - xOfAttacker) > ((Minion) getCardInGame(string)).getAttackRange() || Math.abs(y - yOfAttacker) > ((Minion) getCardInGame(string)).getAttackRange()) {
                        System.out.println("opponent minion is unavailable for attack");
                        return;
                    }
                } else if (((Minion) getCardInGame(string)).getClas().matches("hybrid")) {
                    if (Math.abs(x - xOfAttacker) > ((Minion) getCardInGame(string)).getAttackRange() || Math.abs(y - yOfAttacker) > ((Minion) getCardInGame(string)).getAttackRange()) {
                        System.out.println("opponent minion is unavailable for attack");
                        return;
                    }
                }

            }

        }
        for (String string : myCardID) {
            if (getCardInGame(string) instanceof Minion) {
                if (getCardInGame(string).isAttack()) {
                    System.out.printf("[%s] can't attack\n", string);
                }
            }
        }
        for (int i = 1; i < myCardID.size(); i++) {
            ((Minion) getCardInGame(myCardID.get(0))).addAP(((Minion) getCardInGame(myCardID.get(i))).getAP());
        }
        ((Minion) getCardInGame(myCardID.get(0))).attack(getLocation(opponentCardID)[0], getLocation(opponentCardID)[1]);
        for (int i = 1; i < myCardID.size(); i++) {
            ((Minion) getCardInGame(myCardID.get(0))).addAP(((Minion) getCardInGame(myCardID.get(i))).getAP() * -1);
        }
        for (int i = 0; i < myCardID.size(); i++) {
            getCardInGame(myCardID.get(i)).setAttack(true);
        }

    }

    public static Hero getHero(Account account) {

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 5; j++) {
                if (Main.getCardsCell()[i][j] instanceof Hero) {
                    if (Main.getCardsCell()[i][j].numberOfPlayer == account.getNumberOfPlayer()) {
                        return ((Hero) Main.getCardsCell()[i][j]);
                    }
                }
            }
        }
        return null;

    }

    public static void passive(Account account) {

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 5; j++) {
                if (Main.getCardsCell()[i][j] == null) {
                    continue;
                }
                if (Main.getCardsCell()[i][j].numberOfPlayer != account.getNumberOfPlayer()) {
                    continue;
                }
                if (!(Main.getCardsCell()[i][j] instanceof Minion)) {
                    continue;
                }
                if (!(((Minion) Main.getCardsCell()[i][j]).getSpecialPower().matches("passive"))) {
                    continue;
                }
                ((Minion) Main.getCardsCell()[i][j]).passivePower(i, j);
            }
        }

    }

    public static void addTurnOfSpecialPower(Account account, Account account1) {

        if (account.getNumberOfPlayer() == 1) {
            if (!getHero(account).isOnSpecialBuff()) {
                turnOfSpecialPower1++;
            }
            if (turnOfSpecialPower1 == getHero(account).getCooldown()) {
                getHero(account).setOnSpecialBuff(true);
            }
            if (!getHero(account1).isOnSpecialBuff()) {
                turnOfSpecialPower2++;
            }
            if (turnOfSpecialPower2 == getHero(account1).getCooldown()) {
                getHero(account1).setOnSpecialBuff(true);
            }
        } else {
            if (!getHero(account1).isOnSpecialBuff()) {
                turnOfSpecialPower1++;
            }
            if (turnOfSpecialPower1 == getHero(account1).getCooldown()) {
                getHero(account1).setOnSpecialBuff(true);
            }
            if (!getHero(account).isOnSpecialBuff()) {
                turnOfSpecialPower2++;
            }
            if (turnOfSpecialPower2 == getHero(account).getCooldown()) {
                getHero(account).setOnSpecialBuff(true);
            }
        }
    }

    public static void useSpecialPower(int x, int y, Account account) {

        if (getHero(account).getCooldown() == 0) {
            System.out.println("Your hero don't have special power");
            return;
        }
        if (account.getMP() < getHero(account).MP) {
            System.out.println("You don't have enough money");
            return;
        }
        if (!getHero(account).isOnSpecialBuff()) {
            System.out.println("You can't use special power");
            return;
        }
        if (getHero(account).useSpecialPower(x, y)) {
            getHero(account).setOnSpecialBuff(false);
            if (account.getNumberOfPlayer() == 1) {
                turnOfSpecialPower1 = 0;
            } else {
                turnOfSpecialPower2 = 0;
            }
            account.addMP(-1 * getHero(account).MP);
        }

    }

    public static void showHand(ArrayList<Card> hand, Deck deck) {

        for (Card card : hand) {
            if (card instanceof Minion) {
                System.out.printf("%d. cardID : %s , type : Minion , AP : %d , HP : %d , MP : %d\n", hand.indexOf(card) + 1, card.cardID, ((Minion) card).getAP(), ((Minion) card).getHP(), card.MP);
            } else if (card instanceof Spell) {
                System.out.printf("%d. cardID : %s , type : Spell , MP : %s , desc : %s\n", hand.indexOf(card) + 1, card.cardID, card.MP, ((Spell) card).getDesc());
            }
        }
        if (deck.getDeckCard().size() == 0) {
            System.out.println("Your deck in empty");
            return;
        }
        System.out.println("Last of card in deck :");
        if (deck.getDeckCard().get(0) instanceof Minion) {
            System.out.printf("cardID : %s , type : Minion , AP : %d , HP : %d , MP : %d\n", deck.getDeckCard().get(0).cardID, ((Minion) deck.getDeckCard().get(0)).getAP(), ((Minion) deck.getDeckCard().get(0)).getHP(), deck.getDeckCard().get(0).MP);
        } else if (deck.getDeckCard().get(0) instanceof Spell) {
            System.out.printf("cardID : %s , type : Spell , MP : %s , desc : %s\n", deck.getDeckCard().get(0).cardID, deck.getDeckCard().get(0).MP, ((Spell) deck.getDeckCard().get(0)).getDesc());
        }

    }

    public static Card existInHand(ArrayList<Card> hand, String cardID) {

        for (Card card : hand) {
            if (card.cardID.matches(cardID)) {
                return card;
            }
        }
        return null;

    }

    public static boolean checkMinionInsert(int x, int y, Account account) {

        if (Main.getCardsCell()[x][y] != null && !(Main.getCardsCell()[x][y] instanceof Item)) {
            return false;
        }
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (x + i > 8 || x + i < 0 || y + j > 4 || y + j < 0) {
                    continue;
                }
                if (Main.getCardsCell()[x + i][y + j] instanceof Hero) {
                    if (Main.getCardsCell()[x + i][y + j].numberOfPlayer == account.getNumberOfPlayer()) {
                        return true;
                    }
                } else if (Main.getCardsCell()[x + i][y + j] instanceof Minion) {
                    if (Main.getCardsCell()[x + i][y + j].numberOfPlayer == account.getNumberOfPlayer()) {
                        return true;
                    }
                }
            }
        }
        return false;

    }

    public static void checkBuff(Buff buff, Card card) {

        if (buff.allTurnEffect) {
            buff.effectBuffsOnCard(card, card.numberOfPlayer);
        }
        if (buff.continuous && !buff.isOn) {
            buff.setOn(true);
        }
        if (!buff.allTurn) {
            buff.turn--;
        }
        if (buff.turn == 0 && !buff.allTurn) {
            if (buff instanceof Poison) {
                if (((Poison) buff).getAddHealth() == -1) {
                    if (card instanceof Minion) {
                        ((Minion) card).addHP(1);
                    } else if (card instanceof Hero) {
                        ((Hero) card).addHP(1);
                    }
                }
            } else if (buff instanceof Power) {
                if (((Power) buff).getAddAP() == 1) {
                    if (card instanceof Minion) {
                        ((Minion) card).addAP(-1);
                    } else if (card instanceof Hero) {
                        ((Hero) card).addAP(-1);
                    }
                } else if (((Power) buff).getAddHealth() == 1) {
                    if (card instanceof Minion) {
                        ((Minion) card).addHP(-1);
                    } else if (card instanceof Hero) {
                        ((Hero) card).addHP(-1);
                    }
                }
            } else if (buff instanceof Weakness) {
                if (((Weakness) buff).getAddHealth() == -1) {
                    if (card instanceof Minion) {
                        ((Minion) card).addHP(1);
                    } else if (card instanceof Hero) {
                        ((Hero) card).addHP(1);
                    }
                } else if (((Weakness) buff).getAddAP() == -1) {
                    if (card instanceof Minion) {
                        ((Minion) card).addAP(1);
                    } else if (card instanceof Hero) {
                        ((Hero) card).addAP(1);
                    }
                }
            }
            card.getBuffs().remove(buff);
        }
        if (buff instanceof Delete) {
            card.getBuffs().remove(buff);
        }

    }

    public static void checkBuffInTheEndOfTurn() {

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 5; j++) {
                if (Main.getCardsCell()[i][j] == null) {
                    continue;
                }
                if (Main.getCardsCell()[i][j] instanceof Item) {
                    continue;
                }
                if (Main.getCardsCell()[i][j].getBuffs() != null) {
                    for (int k = 0; k < Main.getCardsCell()[i][j].getBuffs().size(); k++) {
                        checkBuff(Main.getCardsCell()[i][j].getBuffs().get(k), Main.getCardsCell()[i][j]);
                        while (k > Main.getCardsCell()[i][j].getBuffs().size() - 1) {
                            k--;
                        }
                        while (k < 0) {
                            k++;
                        }
                    }
                }

            }
        }

    }

    public static boolean existStun(ArrayList<Buff> buffs) {

        for (Buff buff : buffs) {
            if (buff instanceof Stun) {
                return true;
            }
        }
        return false;

    }

    public static boolean existDisarm(ArrayList<Buff> buffs) {

        for (Buff buff : buffs) {
            if (buff instanceof Disarm) {
                return true;
            }
        }
        return false;

    }

    public static void checkStunAndDisarm() {

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 5; j++) {
                if (Main.getCardsCell()[i][j] == null) {
                    continue;
                }
                if (Main.getCardsCell()[i][j] instanceof Item) {
                    continue;
                }
                if (!existDisarm(Main.getCardsCell()[i][j].getBuffs())) {
                    if (Main.getCardsCell()[i][j] instanceof Minion) {
                        ((Minion) Main.getCardsCell()[i][j]).setCanCounterAttack(true);
                    } else if (Main.getCardsCell()[i][j] instanceof Hero) {
                        ((Hero) Main.getCardsCell()[i][j]).setCanCounterAttack(true);
                    }
                }
                if (!existStun(Main.getCardsCell()[i][j].getBuffs())) {
                    if (Main.getCardsCell()[i][j] instanceof Hero) {
                        ((Hero) Main.getCardsCell()[i][j]).setOnOrOf(true);
                    } else if (Main.getCardsCell()[i][j] instanceof Minion) {
                        ((Minion) Main.getCardsCell()[i][j]).setOnOrOf(true);
                    }
                }
            }
        }

    }

    public static void insertCard(ArrayList<Card> hand, int x, int y, String cardID, Account account, boolean type) {
        if (existInHand(hand, cardID) == null) {
            if (type) {
                System.out.println("Invalid card id");
            }
            return;
        }
        if (existInHand(hand, cardID).MP > account.getMP()) {
            if (type) {
                System.out.println("You don't have enough mana");
            }
            return;
        }
        Card card = existInHand(hand, cardID);
        if (card instanceof Minion) {
            if (!checkMinionInsert(x, y, account)) {
                if (type) {
                    System.out.println("Invalid target");
                }
                return;
            } else {
                if (((Minion) card).getSpecialPower().matches("spawn")) {
                    ((Minion) card).spawn(x, y);
                }
                if (Main.getCardsCell()[x][y] instanceof Item) {
                    if (card.numberOfPlayer == 1) {
                        Main.getCardsCell()[x][y].setNumberOfPlayer(1);
                        collectableItem1.add(((Item) Main.getCardsCell()[x][y]));
                    } else {
                        Main.getCardsCell()[x][y].setNumberOfPlayer(2);
                        collectableItem2.add(((Item) Main.getCardsCell()[x][y]));
                    }
                    if (((Item) Main.getCardsCell()[x][y]).name.matches("flag")) {
                        card.addFlag(((Item) Main.getCardsCell()[x][y]));
                    }
                    Main.getCardsCell()[x][y] = null;
                }
                Main.getCardsCell()[x][y] = card;
                hand.remove(card);
                account.addMP(-1 * card.MP);
                if (type) {
                    System.out.printf("[%s] inserted to (%d,%d)\n", cardID, x + 1, y + 1);
                }
            }
        } else if (card instanceof Spell) {
            if (!((Spell) card).setCellEffect(x, y)) {
                if (type) {
                    System.out.println("Invalid target");
                }
                return;
            }
            if (account.getNumberOfPlayer() == 1) {
                graveYard1.add(card);
            } else {
                graveYard2.add(card);
            }
            hand.remove(card);
            account.addMP(-1 * card.MP);
            if (type) {
                System.out.printf("[%s] inserted to (%d,%d)\n", cardID, x + 1, y + 1);
            }
            if (((Spell) card).isForCell()) {
                for (int i = 0; i < ((Spell) card).getCellEffect().size(); i++) {
                    int x1 = ((Spell) card).getCellEffect().get(i)[0];
                    int y1 = ((Spell) card).getCellEffect().get(i)[1];
                    Main.getBuffCell()[x1][y1] = card.getBuffs().get(0);
                }
            } else {
                for (int i = 0; i < ((Spell) card).getCellEffect().size(); i++) {
                    int x1 = ((Spell) card).getCellEffect().get(i)[0];
                    int y1 = ((Spell) card).getCellEffect().get(i)[1];
                    if (Main.getCardsCell()[x1][y1] == null || Main.getCardsCell()[x1][y1] instanceof Item) {
                        continue;
                    }
                    for (Buff buff : card.getBuffs()) {
                        Main.getCardsCell()[x1][y1].addBuff(buff);
                        buff.effectBuffsOnCard(Main.getCardsCell()[x1][y1], account.getNumberOfPlayer());
                    }
                }
            }
        }

    }

    public static void showMap() {

        System.out.printf("\n");
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) {
                if (Main.getCardsCell()[j][i] == null) {
                    System.out.printf("%d.  null     ", j + 1);
                    continue;
                }
                System.out.printf("%d.  %s , %d      ", j + 1, Main.getCardsCell()[j][i].cardID, Main.getCardsCell()[j][i].numberOfPlayer);
            }
            System.out.printf("\n\n");
        }

    }

    public static void endTurn(Account account, Account account1) {

        selectedItem = null;
        selectedCard = null;
        addTurn();
        if (account.getNumberOfPlayer() == 1) {
            GameController.setMP(account, account1);
        } else {
            GameController.setMP(account1, account);
        }
        GameController.addTurnOfSpecialPower(account, account1);
        GameController.passive(account);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 5; j++) {
                if (Main.getCardsCell()[i][j] == null) {
                    continue;
                }
                if (Main.getCardsCell()[i][j].numberOfPlayer != account.getNumberOfPlayer()) {
                    continue;
                }
                Main.getCardsCell()[i][j].setAttack(false);
                Main.getCardsCell()[i][j].setMove(false);
            }
        }
        if (account.getNumberOfPlayer() == 1) {
            if (handPlayer1.size() == 5) {
                return;
            }
            if (player1Deck.getDeckCard().size() == 0) {
                return;
            }
            handPlayer1.add(player1Deck.getDeckCard().get(0));
            player1Deck.getDeckCard().remove(0);
        } else {
            if (handPlayer2.size() == 5) {
                return;
            }
            if (player2Deck.getDeckCard().size() == 0) {
                return;
            }
            handPlayer2.add(player2Deck.getDeckCard().get(0));
            player2Deck.getDeckCard().remove(0);
        }

    }

    public static void showCollectable(Account account, ArrayList<Item> collectableItem) {

        for (Item item : collectableItem) {
            if (item.name.matches("flag")) {
                continue;
            }
            System.out.printf("%d. CardID : %s , Desc : %s\n", collectableItem.indexOf(item) + 1, item.cardID, item.getDesc());
        }

    }

    public static Item existItem(String itemID, ArrayList<Item> collectableItem) {

        for (Item item : collectableItem) {
            if (item.cardID.matches(itemID)) {
                return item;
            }
        }
        return null;

    }

    public static void selectItem(String itemID, ArrayList<Item> collectableItem) {

        if (existItem(itemID, collectableItem) == null) {
            System.out.println("You don't have this item");
            return;
        }
        selectedItem = existItem(itemID, collectableItem);

    }

    public static void showNextCard(Deck deck) {

        if (deck.getDeckCard().size() == 0) {
            System.out.println("Your deck is empty");
            return;
        }
        Card card = deck.getDeckCard().get(0);
        if (card instanceof Minion) {
            System.out.printf("CardID : %s , Type : Minion , AP : %d , HP : %d , MP : %d , Class : %s , Special power : %s\n", card.cardID, ((Minion) card).getAP(), ((Minion) card).getHP(), card.MP, ((Minion) card).getClas(), ((Minion) card).getSpecialPower());
        } else if (card instanceof Spell) {
            System.out.printf("CardID : %s , Type : Spell , MP : %d , Class : %s\n", card.cardID, card.MP, ((Spell) card).getDesc());
        }

    }

    public static void showCard(Card card, boolean showInfo) {

        Pattern pattern = Pattern.compile("^\\p{all}+_(?<cardName>\\w+)_\\d+$");
        Matcher matcher = pattern.matcher(card.cardID);
        String cardName = "";
        if (matcher.find()) {
            cardName = matcher.group("cardName");
        }
        Card card1 = Shop.getCard(cardName);
        if (card1 instanceof Hero) {
            if (showInfo) {
                System.out.printf("CardID : %s , AP : %d , HP : %d , Class : %s , special power : %s\n", card.cardID, ((Hero) card1).getAP(), ((Hero) card1).getHP(), ((Hero) card1).getClas(), ((Hero) card1).getSpecialPower());
            } else {
                System.out.printf("CardID : %s\n", card.cardID);
            }
        } else if (card1 instanceof Minion) {
            if (showInfo) {
                System.out.printf("CardID : %s , AP : %s , HP : %d , Class : %s , Special power : %s\n", card.cardID, ((Minion) card1).getAP(), ((Minion) card1).getHP(), card1.MP, ((Minion) card1).getClas(), ((Minion) card1).getSpecialPower());
            } else {
                System.out.printf("CardID : %s\n", card.cardID);
            }
        } else if (card1 instanceof Spell) {
            if (showInfo) {
                System.out.printf("CardID : %s , Desc : %s\n", card.cardID, ((Spell) card1).getDesc());
            } else {
                System.out.printf("CardID : %s\n", card.cardID);
            }
        }

    }

    public static void graveYard(ArrayList<Card> graveYard) {

        Pattern pattern = Pattern.compile("^show info \\[(?<cardID>\\p{all}+)]$");
        Matcher matcher;
        String command;
        while (true) {

            command = Main.getScanner().nextLine().toLowerCase().trim();
            if (command.matches("exit")) {
                return;
            } else if (command.matches("show cards")) {
                for (Card card : graveYard) {
                    System.out.printf("%d. ", graveYard.indexOf(card) + 1);
                    showCard(card, false);
                }
            }
            matcher = pattern.matcher(command);
            if (matcher.find()) {
                for (Card card : graveYard) {
                    if (card.cardID.matches(matcher.group("cardID"))) {
                        showCard(card, true);
                        break;
                    }
                }
            }

        }

    }

    public static void showCommands() {

        System.out.println("1. game info");
        System.out.println("2. show my minions");
        System.out.println("3. show opponent minions");
        System.out.println("4. show card info [card id]");
        System.out.println("5. select [card id]");
        System.out.println("6. move to ([x],[y])");
        System.out.println("7. attack [opponent card id]");
        System.out.println("8. attack combo [opponent card id] [my card id] [my card id] ....");
        System.out.println("9. use special power (x,y)");
        System.out.println("10. show hand");
        System.out.println("11. insert [card id] in (x,y)");
        System.out.println("12. end turn");
        System.out.println("13. show collectables");
        System.out.println("14. select [collectable id]");
        System.out.println("15. show info");
        System.out.println("16. use [x,y]");
        System.out.println("17. enter graveyard");
        System.out.println("18. show info [card id]");
        System.out.println("19. show cards");
        System.out.println("20. help");
        System.out.println("21. end game");
        System.out.println("22. exit");

    }

    public static boolean checkEmptyAroundLocation(int x, int y) {

        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (i != 0 && j != 0) {
                    continue;
                }
                if (i + x > 8 || i + x < 0 || j + y > 4 || j + y < 0) {
                    continue;
                }
                if (Main.getCardsCell()[i + x][j + y] == null) {
                    return true;
                }
            }
        }
        return false;

    }

    public static void help(ArrayList<Card> hand, Account account) {

        boolean exist = false;
        int counter = 1;
        System.out.println("You can insert cards in below :");
        for (Card card : hand) {
            if (card.MP < account.getMP()) {
                System.out.printf("          %d. CardID : %s\n", counter, card.cardID);
                counter++;
                exist = true;
            }
        }
        if (!exist) {
            System.out.println("          None");
        }
        exist = false;
        System.out.println("You can move cards in below : ");
        counter = 1;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 5; j++) {
                if (Main.getCardsCell()[i][j] == null) {
                    continue;
                }
                if (Main.getCardsCell()[i][j].numberOfPlayer != account.getNumberOfPlayer()) {
                    continue;
                }
                if (!Main.getCardsCell()[i][j].isAttack() && !Main.getCardsCell()[i][j].isMove()) {
                    if (checkEmptyAroundLocation(i, j)) {
                        System.out.printf("          %d. CardID : %s\n", counter, Main.getCardsCell()[i][j].cardID);
                        counter++;
                        exist = true;
                    }
                }
            }
        }
        if (!exist) {
            System.out.println("          None");
        }
        exist = false;
        System.out.println("You can attack to cards in below :");
        counter = 1;
        ArrayList<Card> cards = new ArrayList<Card>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 5; j++) {
                if (Main.getCardsCell()[i][j] == null) {
                    continue;
                }
                if (Main.getCardsCell()[i][j].numberOfPlayer != account.getNumberOfPlayer()) {
                    continue;
                }
                if (Main.getCardsCell()[i][j].isAttack()) {
                    continue;
                }
                for (Card card : attackToTheseCard(i, j, account)) {
                    System.out.printf("          %d. CardID : [%s] with your card with [%s]\n", counter, card.cardID, Main.getCardsCell()[i][j].cardID);
                    counter++;
                    exist = true;
                }
            }
        }
        if (!exist) {
            System.out.println("          None");
        }

    }

    public static ArrayList<Card> attackToTheseCard(int x, int y, Account account) {

        ArrayList<Card> allCardYouCanAttack = new ArrayList<Card>();
        Card card = Main.getCardsCell()[x][y];

        if (card instanceof Minion) {
            if (((Minion) card).getClas().matches("melee")) {
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 5; j++) {
                        if (Main.getCardsCell()[i][j] == null) {
                            continue;
                        }
                        if (Main.getCardsCell()[i][j].numberOfPlayer == account.getNumberOfPlayer()) {
                            continue;
                        }
                        if (Math.abs(i - x) > 1 || Math.abs(j - y) > 1) {
                            continue;
                        }
                        allCardYouCanAttack.add(Main.getCardsCell()[i][j]);
                    }
                }
            } else if (((Minion) card).getClas().matches("ranged")) {
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 5; j++) {
                        if (Main.getCardsCell()[i][j] == null) {
                            continue;
                        }
                        if (Main.getCardsCell()[i][j].numberOfPlayer == account.getNumberOfPlayer()) {
                            continue;
                        }
                        if ((Math.abs(x - i) < 2 && Math.abs(y - j) < 2) || Math.abs(x - i) > ((Minion) card).getAttackRange() || Math.abs(y - j) > ((Minion) card).getAttackRange()) {
                            continue;
                        }
                        allCardYouCanAttack.add(Main.getCardsCell()[i][j]);
                    }
                }
            } else {
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 5; j++) {
                        if (Main.getCardsCell()[i][j] == null) {
                            continue;
                        }
                        if (Main.getCardsCell()[i][j].numberOfPlayer == account.getNumberOfPlayer()) {
                            continue;
                        }
                        if (Math.abs(i - x) > ((Minion) card).getAttackRange() || Math.abs(j - y) > ((Minion) card).getAttackRange()) {
                            continue;
                        }
                        allCardYouCanAttack.add(Main.getCardsCell()[i][j]);
                    }
                }
            }
        } else if (card instanceof Hero) {
            if (((Hero) card).getClas().matches("melee")) {
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 5; j++) {
                        if (Main.getCardsCell()[i][j] == null) {
                            continue;
                        }
                        if (Main.getCardsCell()[i][j].numberOfPlayer == account.getNumberOfPlayer()) {
                            continue;
                        }
                        if (Math.abs(i - x) > 1 || Math.abs(j - y) > 1) {
                            continue;
                        }
                        allCardYouCanAttack.add(Main.getCardsCell()[i][j]);
                    }
                }
            } else if (((Hero) card).getClas().matches("ranged")) {
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 5; j++) {
                        if (Main.getCardsCell()[i][j] == null) {
                            continue;
                        }
                        if (Main.getCardsCell()[i][j].numberOfPlayer == account.getNumberOfPlayer()) {
                            continue;
                        }
                        if ((Math.abs(x - i) < 2 && Math.abs(y - j) < 2) || Math.abs(x - i) > ((Hero) card).getAttackRange() || Math.abs(y - j) > ((Hero) card).getAttackRange()) {
                            continue;
                        }
                        allCardYouCanAttack.add(Main.getCardsCell()[i][j]);
                    }
                }
            } else {
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 5; j++) {
                        if (Main.getCardsCell()[i][j] == null) {
                            continue;
                        }
                        if (Main.getCardsCell()[i][j].numberOfPlayer == account.getNumberOfPlayer()) {
                            continue;
                        }
                        if (Math.abs(i - x) > ((Hero) card).getAttackRange() || Math.abs(j - y) > ((Hero) card).getAttackRange()) {
                            continue;
                        }
                        allCardYouCanAttack.add(Main.getCardsCell()[i][j]);
                    }
                }
            }
        }

        return allCardYouCanAttack;

    }

    public static void AIForsinglePlayer(Account account1) {

        /*for (int i = 0; i < handPlayer2.size(); i++) {
            for (int k = 0; k < 9; k++) {
                for (int j = 0; j < 5; j++) {
                    if (i > handPlayer2.size() - 1) {
                        i--;
                    }
                    insertCard(handPlayer2, k, j, handPlayer2.get(i).cardID, account1, false);
                }
            }
        }*/

    }

    public static void useCollectableItem(int x, int y, Item item) {

        item.setCellEffect(x, y);
        if (item.getCellEffect().size() == 0) {
            System.out.println("Can't use in this location");
            return;
        }
        for (int i = 0; i < item.getCellEffect().size(); i++) {
            for (Buff buff : item.getBuffs()) {
                Main.getCardsCell()[item.getCellEffect().get(i)[0]][item.getCellEffect().get(i)[1]].addBuff(buff);
                buff.effectBuffsOnCard(Main.getCardsCell()[item.getCellEffect().get(i)[0]][item.getCellEffect().get(i)[1]], item.numberOfPlayer);
            }
        }

    }

    public static void control(Account account, Account account1, String command, int mode, boolean type) {

        Pattern showCardInfoPat = Pattern.compile("^show card info \\[(?<cardID>\\p{all}+)]$");
        Pattern selectPat = Pattern.compile("^select \\[(?<cardID>\\p{all}+)]$");
        Pattern movePat = Pattern.compile("^move to \\(\\[(?<x>[1-9])],\\[(?<y>[1-5])]\\)$");
        Pattern attackPat = Pattern.compile("^attack \\[(?<cardID>\\p{all}+)]$");
        Pattern comboAttackPat = Pattern.compile("^attack combo \\[(?<cardID>\\p{all}+)] (\\[\\p{all}+])+$");
        Pattern useSpecialPowerPat = Pattern.compile("^use special power \\((?<x>[1-9]),(?<y>[1-5])\\)$");
        Pattern insertPat = Pattern.compile("^insert \\[(?<cardID>\\p{all}+)] in \\((?<x>[1-9]),(?<y>[1-5])\\)$");
        Pattern selectItemPat = Pattern.compile("^select collectable \\[(?<itemID>\\p{all}+)]$");
        Pattern useItemPat = Pattern.compile("^use \\[(?<x>[1-9]),(?<y>[1-5])]$");
        Matcher matcher;

        if (command.matches("game info")) {
            gameInfo(mode, KillMode.player1Deck, KillMode.player2Deck);
            return;
        } else if (command.matches("show my minions")) {
            showMyMinion(account);
            return;
        } else if (command.matches("show opponent minions")) {
            showMyMinion(account1);
            return;
        }

        matcher = showCardInfoPat.matcher(command);
        if (matcher.find()) {
            showCardInfo(matcher.group("cardID"), handPlayer1);
            return;
        }

        matcher = selectPat.matcher(command);
        if (matcher.find()) {
            selectCard(matcher.group("cardID"), account);
            return;
        }

        matcher = movePat.matcher(command);
        if (matcher.find()) {
            if (selectedCard == null) {
                System.out.println("Select card and try again");
                return;
            }
            moveCard(Integer.parseInt(matcher.group("x")) - 1, Integer.parseInt(matcher.group("y")) - 1, account, type);
            return;
        }

        matcher = attackPat.matcher(command);
        if (matcher.find()) {
            if (selectedCard == null) {
                System.out.println("Select card and try again");
                return;
            }
            attack(matcher.group("cardID"), account, type);
            return;
        }

        matcher = comboAttackPat.matcher(command);
        if (matcher.find()) {
            Pattern pattern = Pattern.compile("^\\[(?<cardID>\\p{all}+)]$");
            ArrayList<String> myCardID = new ArrayList<String>();
            String opponentCardID = "";
            Matcher matcher1;
            String[] commands = command.split(" ");
            for (int i = 3; i < commands.length; i++) {
                matcher1 = pattern.matcher(commands[i]);
                if (matcher1.find()) {
                    myCardID.add(matcher1.group("cardID"));
                }
            }
            matcher1 = pattern.matcher(commands[2]);
            if (matcher1.find()) {
                opponentCardID = matcher1.group("cardID");
            }
            comboAttack(myCardID, opponentCardID, account);
        }

        matcher = useSpecialPowerPat.matcher(command);
        if (matcher.find()) {
            useSpecialPower(Integer.parseInt(matcher.group("x")) - 1, Integer.parseInt(matcher.group("y")) - 1, account);
        }

        if (command.matches("show hand")) {
            if (account.getNumberOfPlayer() == 1) {
                showHand(handPlayer1, player1Deck);
            } else {
                showHand(handPlayer2, player2Deck);
            }
        }

        matcher = insertPat.matcher(command);
        if (matcher.find()) {
            if (account.getNumberOfPlayer() == 1) {
                insertCard(handPlayer1, Integer.parseInt(matcher.group("x")) - 1, Integer.parseInt(matcher.group("y")) - 1, matcher.group("cardID"), account, type);
            } else {
                insertCard(handPlayer2, Integer.parseInt(matcher.group("x")) - 1, Integer.parseInt(matcher.group("y")) - 1, matcher.group("cardID"), account, type);
            }
        }

        if (command.matches("show collectables")) {
            if (account.getNumberOfPlayer() == 1) {
                showCollectable(account, collectableItem1);
            } else {
                showCollectable(account, collectableItem2);
            }
        }

        matcher = selectItemPat.matcher(command);
        if (matcher.find()) {
            if (account.getNumberOfPlayer() == 1) {
                selectItem(matcher.group("itemID"), collectableItem1);
            } else {
                selectItem(matcher.group("itemID"), collectableItem2);
            }
        }

        if (command.matches("show info")) {
            if (selectedItem == null) {
                System.out.println("Select item and try again");
                return;
            }
            System.out.printf("desc : %s\n", selectedItem.getDesc());
            selectedItem = null;
        }

        if (command.matches("show next card")) {
            if (account.getNumberOfPlayer() == 1) {
                showNextCard(player1Deck);
            } else {
                showNextCard(player2Deck);
            }
        }

        if (command.matches("enter graveyard")) {
            if (account.getNumberOfPlayer() == 1) {
                graveYard(graveYard1);
            } else {
                graveYard(graveYard2);
            }
        }

        if (command.matches("show commands")) {
            showCommands();
        }

        if (command.matches("help")) {
            if (account.getNumberOfPlayer() == 1) {
                help(handPlayer1, account);
            } else {
                help(handPlayer2, account);
            }
        }

        matcher = useItemPat.matcher(command);
        if (matcher.find()) {
            if (selectedItem == null) {
                System.out.println("Select item and try again");
                return;
            }
            useCollectableItem(Integer.parseInt(matcher.group("x")) - 1, Integer.parseInt(matcher.group("y")) - 1, selectedItem);
            selectedItem = null;
        }

        if (command.matches("show")) {
            showMap();
        }

    }

}
