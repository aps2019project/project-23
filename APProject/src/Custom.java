public class Custom {

    protected static Deck player1Deck;
    protected static Deck player2Deck;

    public static void setPlayer1Deck(Deck player1Deck) {
        Custom.player1Deck = player1Deck;
    }

    public static void setPlayer2Deck(Deck player2Deck) {
        Custom.player2Deck = player2Deck;
    }

    public static void endGame(Account account, boolean winOrLose) {

        String command;
        Match match = new Match("kill hero (single player)", false);
        match.setWonOrLose(winOrLose);
        account.addMatch(match);
        if (winOrLose) {
            account.addBudget(1000);
            System.out.printf("%s win , you won 1000 drick", account.getUsername());
        } else {
            System.out.printf("%s lose :(((\n", account.getUsername());
        }
        while (true) {
            command = Main.getScanner().nextLine().toLowerCase().trim();
            if (command.matches("end game")) {
                return;
            }
        }

    }

}
