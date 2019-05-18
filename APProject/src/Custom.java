public class Custom {

    protected static Deck player1Deck;
    protected static Deck player2Deck;

    public static void setPlayer1Deck(Deck player1Deck) {
        Custom.player1Deck = player1Deck;
    }

    public static void setPlayer2Deck(Deck player2Deck) {
        Custom.player2Deck = player2Deck;
    }

    public static void endGame(Account account, Account account1, boolean winOrLose, int mode, String singlePlayer) {

        String command;
        Match match = new Match("None", false);
        if (mode == 1) {
            match = new Match("kill hero mode with hero [ " + GameController.getHero(account1).name + "]", false);
        } else if (mode == 2) {
            match = new Match("one flag collect mode with hero [ " + GameController.getHero(account1).name + "]", false);
        } else if (mode == 3) {
            match = new Match("collect 1/2 of all flags mode with hero [" + GameController.getHero(account1).name + "]", false);
        }
        match.setWonOrLose(winOrLose);
        account.addMatch(match);
        int drick = 0;
        if (singlePlayer.matches("custom")) {
            drick = 1000;
        } else if (singlePlayer.matches("story")) {
            if (mode == 1) {
                drick = 500;
            } else if (mode == 2) {
                drick = 1000;
            } else {
                drick = 1500;
            }
        }
        if (winOrLose) {
            account.addBudget(drick);
            System.out.printf("%s win , Won %d drick\n", account.getUsername(), drick);
        } else {
            System.out.printf("%s win", account.getUsername());
            if (!account1.getUsername().matches("computer")) {
                System.out.printf(" , Won %d drick", drick);
            } else {
                System.out.println();
            }
            account1.addBudget(drick);
        }
        while (true) {
            command = Main.getScanner().nextLine().toLowerCase().trim();
            if (command.matches("end game")) {
                return;
            }
        }

    }

}
