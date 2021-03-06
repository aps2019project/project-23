public class KillMode extends Custom {

    public static boolean checkEndGame(Account account) {

        if (GameController.getHero(account).getHP() == 0) {
            return true;
        } else {
            return false;
        }

    }

    public static void game(Account account, Account account1, String singlePlayer, boolean single) {

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
                    GameController.checkBuffInTheEndOfTurn();
                    GameController.endTurn(account, account1);
                } else {
                    GameController.control(account, account1, command, 1, true);
                }
            } else if (turnPlayer2) {
                if (single) {
                    GameController.AIForsinglePlayer(account1);
                    GameController.checkBuffInTheEndOfTurn();
                    GameController.endTurn(account1, account);
                    turnPlayer1 = true;
                    turnPlayer2 = false;
                } else {
                    command = Main.getScanner().nextLine().toLowerCase().trim();
                    if (command.matches("exit")) {
                        System.out.println("Cancel game");
                        return;
                    } else if (command.matches("end turn")) {
                        turnPlayer1 = false;
                        turnPlayer2 = true;
                        GameController.checkBuffInTheEndOfTurn();
                        GameController.endTurn(account, account1);
                    } else {
                        GameController.control(account, account1, command, 1, true);
                    }
                }
            }
            GameController.checkStunAndDisarm();
            GameController.death();
            if (checkEndGame(account)) {
                endGame(account, account1, false, 1, singlePlayer);
                return;
            }
            if (checkEndGame(account1)) {
                endGame(account, account1, true, 1, singlePlayer);
                return;
            }
        }

    }

}
