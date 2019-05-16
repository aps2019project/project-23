public class KillMode extends Custom {

    public static boolean checkEndGame(Account account) {

        if (GameController.getHero(account).getHP() == 0) {
            return true;
        } else {
            return false;
        }

    }

    public static void game(Account account, Account account1) {

        boolean turnPlayer1 = true;
        boolean turnPlayer2 = false;
        Main.setNullCardsCell();
        Main.setNullBuffsCell();
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
                    GameController.control(account, account1, command, 1, true);
                }
                if (checkEndGame(account)) {
                    endGame(account, false);
                    return;
                }
                if (checkEndGame(account1)) {
                    endGame(account, true);
                    return;
                }
            } else if (turnPlayer2) {
                GameController.endTurn(account1, account);

                turnPlayer1 = true;
                turnPlayer2 = false;
            }
            GameController.death();
        }

    }

}
