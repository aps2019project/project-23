import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GameController {

    public static void gameInfo(int mode, Deck player1Deck, Deck player2Deck) {

        if (mode == 1) {
            System.out.printf("HP of Hero of Player_1 : %d\n", ((Hero) player1Deck.getDeckCard().get(player1Deck.indexOfHero())).getHP());
            System.out.printf("HP of Hero of Player_2 : %d\n", ((Hero) player2Deck.getDeckCard().get(player2Deck.indexOfHero())).getHP());
        } else if (mode == 2) {

        } else {

        }

    }

    public static void showMyMinion(Account account) {

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 5; j++) {
                if (Main.getCardsCell()[i][j].numberOfPlayer == account.getNumberOfPlayer()) {
                    System.out.printf("cardID : %s, ", Main.getCardsCell()[i][j].cardID);
                    if (Main.getCardsCell()[i][j] instanceof Hero) {
                        System.out.printf("health : %d, ", ((Hero) Main.getCardsCell()[i][j]).getHP());
                        System.out.printf("location : [%d,%d], ", i, j);
                        System.out.printf("power : %d\n", ((Hero) Main.getCardsCell()[i][j]).getAP());
                    } else if (Main.getCardsCell()[i][j] instanceof Minion) {
                        System.out.printf("health : %d, ", ((Minion) Main.getCardsCell()[i][j]).getHP());
                        System.out.printf("location : [%d,%d], ", i, j);
                        System.out.printf("power : %d\n", ((Minion) Main.getCardsCell()[i][j]).getAP());
                    }
                }
            }
        }

    }

    public static Card getCardInCell(String cardID, ArrayList<Card> handPlayer1) {

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 5; j++) {
                if (Main.getCardsCell()[i][j].cardID.matches(cardID)) {
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
            System.out.printf("     Cost : %d", card.cost);
            System.out.printf("     Desc : %s\n", ((Spell) card).getDesc());
        }

    }

    public static void selectCard(String cardID, Account account) {

        boolean existCard = false;
        int x = 0, y = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 5; j++) {
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
        if (account.getNumberOfPlayer() == 1) {
            KillMode.setSelectedCard1(Main.getCardsCell()[x][y]);
        } else if (account.getNumberOfPlayer() == 2) {
            KillMode.setSelectedCard2(Main.getCardsCell()[x][y]);
        }

    }

    public static void control(Account account, Account account1, String command, int mode) {

        Pattern showCardInfoPat = Pattern.compile("^show card info \\[(?<cardID>\\p{all}+)]$");
        Pattern selectPat = Pattern.compile("^select \\[(?<cardID>)\\p{all}+]$");
        Matcher matcher;

        if (command.matches("game info")) {
            gameInfo(mode, KillMode.player1Deck, KillMode.player2Deck);
        } else if (command.matches("show my minions")) {
            showMyMinion(account);
        } else if (command.matches("show opponent minions")) {
            showMyMinion(account1);
        }

        matcher = showCardInfoPat.matcher(command);
        if (matcher.find()) {
            showCardInfo(matcher.group("cardID"), KillMode.getHandPlayer1());
            return;
        }

        matcher = selectPat.matcher(command);
        if (matcher.find()) {
            selectCard(matcher.group("cardID"), account);
            return;
        }

    }

}
