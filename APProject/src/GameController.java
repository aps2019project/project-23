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
    private static int turnOfSpecialPower1;
    private static int turnOfSpecialPower2;

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

    public static void setMP(int turn, Account account, Account account1) {

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
            System.out.println("Invalid target");
            selectedCard = null;
            return;
        }
        if (Math.abs(x - xOfCard) + Math.abs(y - yOfCard) > 2) {
            System.out.println("Invalid target");
            selectedCard = null;
            return;
        }
        if (!checkMove(xOfCard, yOfCard, x, y)) {
            System.out.println("Invalid target");
            selectedCard = null;
            return;
        }
        if (Main.getCardsCell()[x][y] instanceof Item) {
            addCollectableItem(x, y, account);
        }
        if (Main.getBuffCell()[x][y] != null) {
            selectedCard.addBuff(Main.getBuffCell()[x][y]);
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
            }
        } else if (selectedCard instanceof Hero) {
            if (((Hero) selectedCard).getClas().matches("melee")) {
                if (Math.abs(x - xOfAttacker) > 1 || Math.abs(y - yOfAttacker) > 1) {
                    System.out.println("opponent minion is unavailable for attack");
                    selectedCard = null;
                    return;
                }
            } else if (((Hero) selectedCard).getClas().matches("ranged")) {
                if ((Math.abs(x - xOfAttacker) < 2 && Math.abs(y - yOfAttacker) < 2) || Math.abs(x - xOfAttacker) > ((Hero) selectedCard).getAttackRange() || Math.abs(y - yOfAttacker) > ((Hero) selectedCard).getAttackRange()) {
                    System.out.println("opponent minion is unavailable for attack");
                    selectedCard = null;
                    return;
                }
            } else if (((Hero) selectedCard).getClas().matches("hybrid")) {
                if (Math.abs(x - xOfAttacker) > ((Hero) selectedCard).getAttackRange() || Math.abs(y - yOfAttacker) > ((Hero) selectedCard).getAttackRange()) {
                    System.out.println("opponent minion is unavailable for attack");
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

    public static void addTurnOfSpecialPower(Account account, Account account1) {

        if (!getHero(account).isOnSpecialBuff()) {
            turnOfSpecialPower1++;
        }
        if (turnOfSpecialPower1 == getHero(account).getCooldown()) {
            getHero(account).setOnSpecialBuff(true);
        }
        if (!getHero(account1).isOnSpecialBuff()) {
            turnOfSpecialPower2++;
        }
        if (turnOfSpecialPower2 == getHero(account).getCooldown()) {
            getHero(account1).setOnSpecialBuff(true);
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
        getHero(account).setOnSpecialBuff(false);
        if (account.getNumberOfPlayer() == 1) {
            turnOfSpecialPower1 = 0;
        } else {
            turnOfSpecialPower2 = 0;
        }
        account.addMP(-1 * getHero(account).MP);
        getHero(account).useSpecialPower(x, y);


    }
    
    public static void showHand(ArrayList<Card> hand , Deck deck){
        
           
        
    }

    public static void control(Account account, Account account1, String command, int mode) {

        Pattern showCardInfoPat = Pattern.compile("^show card info \\[(?<cardID>\\p{all}+)]$");
        Pattern selectPat = Pattern.compile("^select \\[(?<cardID>\\p{all}+)]$");
        Pattern movePat = Pattern.compile("^move to \\(\\[(?<x>[1-9])],\\[(?<y>[1-5])]\\)$");
        Pattern attackPat = Pattern.compile("^attack \\[(?<cardID>\\p{all}+)]$");
        Pattern comboAttackPat = Pattern.compile("^attack combo \\[(?<cardID>\\p{all}+)] (\\[\\p{all}+])+$");
        Pattern useSpecialPowerPat = Pattern.compile("^use special power \\((?<x>[1-9]),(?<y>[1-5])\\)$");
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

    }

}
