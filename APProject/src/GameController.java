import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GameController {

    private static ArrayList<Card> handPlayer1 = new ArrayList<Card>(5);
    private static ArrayList<Card> handPlayer2 = new ArrayList<Card>(5);
    private static ArrayList<Item> collectableItem1 = new ArrayList<>();
    private static Card selectedCard = null;
    protected static Deck player1Deck;
    protected static Deck player2Deck;

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

        } else {

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

    public static void addCollectableItem(int x, int y, Account account) {
        String cardID;
        int counter = 0;
        for (int i = 0; i < collectableItem1.size(); i++) {
            if (collectableItem1.get(i).name.matches(((Item) Main.getCardsCell()[x][y]).name)) {
                counter++;
            }
        }
        cardID = account.getUsername() + Main.getCardsCell()[x][y].name + counter;
        Main.getCardsCell()[x][y].setCardID(cardID);
        collectableItem1.add(((Item) Main.getCardsCell()[x][y]));
        Main.getCardsCell()[x][y] = null;
    }

    public static void moveCard(int x, int y, Account account) {

        int xOfCard = getLocation(selectedCard.cardID)[0];
        int yOfCard = getLocation(selectedCard.cardID)[1];

        if (selectedCard instanceof Hero) {
            if (!((Hero) selectedCard).isOnOrOf()) {
                return;
            }
        } else if (selectedCard instanceof Minion) {
            if (!((Minion) selectedCard).isOnOrOf()) {
                return;
            }
        }

        if (x == xOfCard && y == yOfCard) {
            selectedCard = null;
            return;
        }
        if (selectedCard.move || selectedCard.attack) {
            System.out.println("Can't move");
            selectedCard = null;
            return;
        }
        if (Main.getCardsCell()[x][y] != null && !(Main.getCardsCell()[x][y] instanceof Item)) {
            System.out.println("Invalid target3");
            selectedCard = null;
            return;
        }
        if (Math.abs(x - xOfCard) + Math.abs(y - yOfCard) > 2) {
            System.out.println("Invalid target2");
            selectedCard = null;
            return;
        }
        if (!checkMove(xOfCard, yOfCard, x, y)) {
            System.out.println("Invalid target1");
            selectedCard = null;
            return;
        }
        if (Main.getCardsCell()[x][y] instanceof Item) {
            addCollectableItem(x, y, account);
        }
        if (Main.getBuffCell()[x][y] != null) {
            selectedCard.addBuff(Main.getBuffCell()[x][y]);
        }
        selectedCard.setMove(true);
        Main.getCardsCell()[getLocation(selectedCard.cardID)[0]][getLocation(selectedCard.cardID)[1]] = null;
        Main.getCardsCell()[x][y] = selectedCard;
        System.out.printf("[%s] moved to [%d] [%d]\n", selectedCard.cardID, x + 1, y + 1);
        selectedCard = null;

    }

    public static void attack(String cardID, Account account) {

        int xOfAttacker = getLocation(selectedCard.cardID)[0];
        int yOfAttacker = getLocation(selectedCard.cardID)[1];
        int x = getLocation(cardID)[0];
        int y = getLocation(cardID)[1];

        if (selectedCard instanceof Hero) {
            if (!((Hero) selectedCard).isOnOrOf()) {
                return;
            }
        } else if (selectedCard instanceof Minion) {
            if (!((Minion) selectedCard).isOnOrOf()) {
                return;
            }
        }

        if (selectedCard.isAttack()) {
            System.out.printf("Card with [%s] can't attack\n", selectedCard.cardID);
            selectedCard = null;
            return;
        }
        if (getLocation(cardID) == null) {
            System.out.println("Invalid card id");
            selectedCard = null;
            return;
        }
        if (Main.getCardsCell()[getLocation(cardID)[0]][getLocation(cardID)[1]].numberOfPlayer == account.getNumberOfPlayer()) {
            System.out.println("Invalid card id");
            selectedCard = null;
            return;
        }
        if (selectedCard instanceof Minion) {
            if (((Minion) selectedCard).getClas().matches("melee")) {
                if (Math.abs(x - xOfAttacker) > 1 || Math.abs(y - yOfAttacker) > 1) {
                    System.out.println("opponent minion is unavailable for attack");
                    selectedCard = null;
                    return;
                }
            } else if (((Minion) selectedCard).getClas().matches("ranged")) {
                if ((Math.abs(x - xOfAttacker) < 2 && Math.abs(y - yOfAttacker) < 2) || Math.abs(x - xOfAttacker) > ((Minion) selectedCard).getAttackRange() || Math.abs(y - yOfAttacker) > ((Minion) selectedCard).getAttackRange()) {
                    System.out.println("opponent minion is unavailable for attack");
                    selectedCard = null;
                    return;
                }
            } else if (((Minion) selectedCard).getClas().matches("hybrid")) {
                if (Math.abs(x - xOfAttacker) > ((Minion) selectedCard).getAttackRange() || Math.abs(y - yOfAttacker) > ((Minion) selectedCard).getAttackRange()) {
                    System.out.println("opponent minion is unavailable for attack");
                    selectedCard = null;
                    return;
                }
            } else {

            }
        } else if (selectedCard instanceof Hero) {
            if (((Hero) selectedCard).getClas().matches("melee")) {
                if (Math.abs(x - xOfAttacker) > 1 || Math.abs(y - yOfAttacker) > 1) {
                    System.out.println("opponent minion is unavailable for attack");
                    selectedCard = null;
                    return;
                }
            } else if (((Hero) selectedCard).getClas().matches("ranged")) {
                if ((Math.abs(x - xOfAttacker) < 2 && Math.abs(y - yOfAttacker) < 2) || Math.abs(x - xOfAttacker) > ((Minion) selectedCard).getAttackRange() || Math.abs(y - yOfAttacker) > ((Minion) selectedCard).getAttackRange()) {
                    System.out.println("opponent minion is unavailable for attack");
                    selectedCard = null;
                    return;
                }
            } else if (((Hero) selectedCard).getClas().matches("hybrid")) {
                if (Math.abs(x - xOfAttacker) > ((Minion) selectedCard).getAttackRange() || Math.abs(y - yOfAttacker) > ((Minion) selectedCard).getAttackRange()) {
                    System.out.println("opponent minion is unavailable for attack");
                    selectedCard = null;
                    return;
                }
            } else {

            }
        }

    }

    public static void control(Account account, Account account1, String command, int mode) {

        Pattern showCardInfoPat = Pattern.compile("^show card info \\[(?<cardID>\\p{all}+)]$");
        Pattern selectPat = Pattern.compile("^select \\[(?<cardID>\\p{all}+)]$");
        Pattern movePat = Pattern.compile("^move to \\(\\[(?<x>[1-9])],\\[(?<y>[1-5])]\\)$");
        Pattern attackPat = Pattern.compile("^attack \\[(?<cardID>\\p{all}+)]$");
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
            moveCard(Integer.parseInt(matcher.group("x")) - 1, Integer.parseInt(matcher.group("y")) - 1, account);
            return;
        }

        matcher = attackPat.matcher(command);
        if (matcher.find()) {
            if (selectedCard == null) {
                System.out.println("Select card and try again");
                return;
            }
            attack(matcher.group("cardID"), account);
            return;
        }

    }

}
