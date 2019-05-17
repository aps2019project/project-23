public class MultiFlagMode extends Custom {

    public static int checkFlag(int numberOfFlag) {

        int counter = 0;
        for (Item item : GameController.getCollectableItem1()) {
            if (item.name.matches("flag")) {
                counter++;
            }
        }
        if (2 * counter >= numberOfFlag) {
            return 1;
        }
        counter = 0;
        for (Item item : GameController.getCollectableItem2()) {
            if (item.name.matches("flag")) {
                counter++;
            }
        }
        if (2 * counter >= numberOfFlag) {
            return 2;
        }
        return 0;

    }

    public static void game(Account account, Account account1, int numberOfFlag) {

        boolean turnPlayer1 = true;
        boolean turnPlayer2 = false;
        GameController.setAccount(player1Deck, 1, account);
        GameController.setAccount(player2Deck, 2, account1);
        GameController.setCellCard(player1Deck, player2Deck);
        player1Deck.deleteHero();
        player2Deck.deleteHero();
        account.setNumberOfPlayer(1);
        account1.setNumberOfPlayer(2);
        GameController.setRandomHand(GameController.getHandPlayer1(), player1Deck);
        GameController.setRandomHand(GameController.getHandPlayer2(), player2Deck);
        GameController.setRandomDeck(player1Deck);
        GameController.setRandomDeck(player2Deck);
        GameController.setPlayer1Deck(player1Deck);
        GameController.setPlayer2Deck(player2Deck);
        Main.setCollectableItemInCells();

        String command;
        while (true) {

            if (turnPlayer1) {
                command = Main.getScanner().nextLine().toLowerCase().trim();
                if (command.matches("exit")) {
                    System.out.println("Cancel game");
                    return;
                } else if (command.matches("end turn")) {
                    turnPlayer1 = false;
                    turnPlayer2 = true;
                    GameController.endTurn(account, account1);
                } else {
                    GameController.control(account, account1, command, 3, true);
                }
            } else if (turnPlayer2) {
                GameController.endTurn(account1, account);
                turnPlayer1 = true;
                turnPlayer2 = false;
            }
            GameController.death();
            if (checkFlag(numberOfFlag) == 1) {
                endGame(account, true, 3);
                return;
            } else if (checkFlag(numberOfFlag) == 2) {
                endGame(account, false, 3);
                return;
            }

        }

    }

}
