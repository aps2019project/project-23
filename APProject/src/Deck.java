import java.util.ArrayList;

public class Deck {

    private String name;
    private ArrayList<Card> deckCard = new ArrayList<Card>();

    public Deck(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Card> getDeckCard() {
        return deckCard;
    }

    public int indexOfHero() {
        for (Card card : deckCard) {
            if (card instanceof Hero) {
                return deckCard.indexOf(card);
            }
        }
        return -1;
    }

    public void addCard(Card card) {
        deckCard.add(card);
    }

    public int indexOfCardInDeck(String cardID) {
        for (int i = 0; i < deckCard.size(); i++) {
            if (deckCard.get(i).cardID.matches(cardID))
                return i;
        }
        return -1;
    }

    public int counterOfCard() {
        int counter = 0;
        for (int i = 0; i < deckCard.size(); i++) {
            if (deckCard.get(i) instanceof Item)
                continue;
            else if (deckCard.get(i) instanceof Hero)
                continue;
            counter++;
        }
        return counter;
    }

    public boolean existHeroInDeck() {
        for (int i = 0; i < deckCard.size(); i++) {
            if (deckCard.get(i) instanceof Hero)
                return true;
        }
        return false;
    }

    public void deleteCard(String cardID) {
        deckCard.remove(indexOfCardInDeck(cardID));
    }

    public Deck copyOfDeck() {
        Deck deck = new Deck(name);
        for (Card card : deckCard) {
            deck.addCard(card.copyOfCard());
        }
        return deck;
    }

}
