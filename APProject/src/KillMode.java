public class KillMode extends Custom {

    public static void setAccount(Deck deck, int numberOfPlayer, Account account) {
        for (Card card : deck.getDeckCard()) {
            card.setNumberOfPlayer(numberOfPlayer);
        }
        account.setMP(2);
    }

    public static void setCellCard() {

        for (Card card : player1Deck.getDeckCard()) {
            if (card instanceof Hero) {
                Main.getCardsCell()[0][2] = card;
            }
        }

        for (Card card : player2Deck.getDeckCard()) {
            if (card instanceof Hero) {
                Main.getCardsCell()[8][2] = card;
            }
        }

        for (Card card : player1Deck.getDeckCard()) {
            if (card instanceof Hero) {
                player1Deck.deleteCard(card.cardID);
            }
        }
        for (Card card : player2Deck.getDeckCard()) {
            if (card instanceof Hero) {
                player2Deck.deleteCard(card.cardID);
            }
        }

    }

    public static void game(Account account, Account account1) {

        int turn1 = 0;
        int turn2 = 0;
        boolean turnPlayer1 = true;
        boolean turnPlayer2 = false;
        Main.setNullCardsCell();
        Main.setNullBuffsCell();
        setAccount(player1Deck, 1, account);
        setAccount(player2Deck, 2, account1);

        String command;
        String[] commads;


        while (true) {

        }

    }

}
