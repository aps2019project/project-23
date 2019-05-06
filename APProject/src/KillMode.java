import java.util.ArrayList;
import java.util.Random;

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

    public static void setRandomHand(ArrayList<Card> hand, Deck deck) {

        Random random = new Random();
        int randomNumber;
        for (int i = 0; i < 5; i++) {
            do {
                randomNumber = random.nextInt(deck.getDeckCard().size());
            } while (deck.getDeckCard().get(randomNumber) instanceof Item);
            hand.add(deck.getDeckCard().get(randomNumber));
            deck.getDeckCard().remove(randomNumber);
        }

    }

    public static void setRandomDeck(Deck deck) {

        Random random = new Random();
        int randomNumber;
        for (int i = 0; i < 7; i++) {
            randomNumber = random.nextInt(deck.getDeckCard().size());
            deck.getDeckCard().add(0, deck.getDeckCard().get(randomNumber));
            deck.getDeckCard().remove(randomNumber + 1);
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
        account.setNumberOfPlayer(1);
        setAccount(player2Deck, 2, account1);
        account1.setNumberOfPlayer(2);
        setCellCard();
        ArrayList<Card> handPlayer1 = new ArrayList<Card>(5);
        ArrayList<Card> handPlayer2 = new ArrayList<Card>(5);
        setRandomHand(handPlayer1, player1Deck);
        setRandomHand(handPlayer2, player2Deck);
        setRandomDeck(player1Deck);
        setRandomDeck(player2Deck);

        String command;
        while (true) {

            if (turnPlayer1) {
                command = Main.getScanner().nextLine().toLowerCase().trim();
                if (command.matches("exit")) {
                    return;
                } else {
                    GameController.control(account, account1, player1Deck, player2Deck, handPlayer1, command, 1);
                }
            } else {

            }

        }

    }

}
