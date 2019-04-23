import java.util.ArrayList;

public class Shop {

    private static ArrayList<Card> allCards = new ArrayList<Card>();

    public static void addAllCards(Card card){
        allCards.add(card);
    }

    public static ArrayList<Card> getAllCards() {
        return allCards;
    }

    public static void menu() {

    }

}

