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

    public static void control(Account account, Account account1, Deck player1Deck, Deck player2Deck, String command, int mode) {

        if (command.matches("game info")) {
            gameInfo(mode, player1Deck, player2Deck);
        } else if (command.matches("show my minions")) {
            showMyMinion(account);
        }

    }

}
