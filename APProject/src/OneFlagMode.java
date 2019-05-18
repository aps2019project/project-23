public class OneFlagMode extends Custom {

    private static int turnOfFlagInPlayer1;
    private static int turnOfFlagInPlayer2;

    public static int checkFlag() {

        boolean existInPlayer1 = false;
        boolean existInPlayer2 = false;
        for (Item item : GameController.getCollectableItem1()) {
            if (item.name.matches("flag")) {
                turnOfFlagInPlayer1++;
                existInPlayer1 = true;
                break;
            }
        }
        for (Item item : GameController.getCollectableItem2()) {
            if (item.name.matches("flag")) {
                turnOfFlagInPlayer2++;
                existInPlayer2 = true;
                break;
            }
        }
        if (!existInPlayer1) {
            turnOfFlagInPlayer1 = 0;
        }
        if (!existInPlayer2) {
            turnOfFlagInPlayer2 = 0;
        }
        if (turnOfFlagInPlayer1 == 6) {
            return 1;
        }
        if (turnOfFlagInPlayer2 == 6) {
            return 2;
        }
        return 0;

    }

    public static void game(Account account, Account account1, String singlePlayer, boolean single) {

        turnOfFlagInPlayer1 = 0;
        turnOfFlagInPlayer2 = 0;
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
                command = Main.getScanner().nextLine().trim().toLowerCase();
                if (command.matches("exit")) {
                    System.out.println("Cancel game");
                    return;
                } else if (command.matches("end turn")) {
                    int checkEndGame = checkFlag();
                    if (checkEndGame == 1) {
                        endGame(account, account1, true, 2, singlePlayer);
                        return;
                    } else if (checkEndGame == 2) {
                        endGame(account, account1, false, 2, singlePlayer);
                        return;
                    }
                    turnPlayer2 = true;
                    turnPlayer1 = false;
                    GameController.checkBuffInTheEndOfTurn();
                    GameController.endTurn(account, account1);
                } else {
                    GameController.control(account, account1, command, 2, true);
                }
            } else if (turnPlayer2) {
                if (single) {
                    int checkEndGame = checkFlag();
                    if (checkEndGame == 1) {
                        endGame(account, account1, true, 2, singlePlayer);
                        return;
                    } else if (checkEndGame == 2) {
                        endGame(account, account1, false, 2, singlePlayer);
                        return;
                    }
                    GameController.AIForsinglePlayer(account1);
                    GameController.checkBuffInTheEndOfTurn();
                    GameController.endTurn(account1, account1);
                    turnPlayer1 = true;
                    turnPlayer2 = false;
                } else {
                    command = Main.getScanner().nextLine().trim().toLowerCase();
                    if (command.matches("exit")) {
                        System.out.println("Cancel game");
                        return;
                    } else if (command.matches("end turn")) {
                        int checkEndGame = checkFlag();
                        if (checkEndGame == 1) {
                            endGame(account, account1, true, 2, singlePlayer);
                            return;
                        } else if (checkEndGame == 2) {
                            endGame(account, account1, false, 2, singlePlayer);
                            return;
                        }
                        turnPlayer2 = true;
                        turnPlayer1 = false;
                        GameController.checkBuffInTheEndOfTurn();
                        GameController.endTurn(account, account1);
                    } else {
                        GameController.control(account, account1, command, 2, true);
                    }
                }
            }
            GameController.checkStunAndDisarm();
            GameController.death();

        }

    }

}
