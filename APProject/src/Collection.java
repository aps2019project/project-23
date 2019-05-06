import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Collection {

    private ArrayList<Card> allCards = new ArrayList<Card>();
    private ArrayList<Card> allOfCardInCollection = new ArrayList<Card>();
    private ArrayList<Deck> allDecks = new ArrayList<Deck>();
    private Deck mainDeck;

    public Deck getMainDeck() {
        return mainDeck;
    }
    
    public void addToAllOfCardInCollection(Card card) {
        allOfCardInCollection.add(card);
    }

    public void setMainDeck(Deck deck) {
        mainDeck = deck;
    }

    public ArrayList<Deck> getAllDecks() {
        return allDecks;
    }

    public ArrayList<Card> getAllOfCardInCollection() {
        return allOfCardInCollection;
    }

    public ArrayList<Card> getAllCards() {
        return allCards;
    }

    public boolean validDeck() {

        if (mainDeck != null) {
            System.out.println("selected deck is valid");
            return true;
        }
        System.out.println("selected deck is invalid");
        return false;

    }

    public void addCardToCollection(Card card) {
        allCards.add(card);
        allOfCardInCollection.add(card);
    }

    public void show(ArrayList<Card> allCards) {

        int counter = 0;
        System.out.println("Heroes :");
        for (int i = 0; i < allCards.size(); i++) {
            if (allCards.get(i) instanceof Hero) {
                counter++;
                System.out.printf("      %d : Name : %s - ", counter, ((Hero) allCards.get(i)).name);
                System.out.printf("Cost : %d - AP : %d - ", ((Hero) allCards.get(i)).cost, ((Hero) allCards.get(i)).getAP());
                System.out.printf("HP : %d - Class : %s - ", ((Hero) allCards.get(i)).getHP(), ((Hero) allCards.get(i)).getClas());
                System.out.printf("Special power : %s\n", ((Hero) allCards.get(i)).getSpecialPower());
            }
        }

        counter = 0;
        System.out.println("Items :");
        for (int i = 0; i < allCards.size(); i++) {
            if (allCards.get(i) instanceof Item) {
                counter++;
                System.out.printf("      %d : Name : %s - ", counter, ((Item) allCards.get(i)).name);
                System.out.printf("Cost : ");
                if (((Item) allCards.get(i)).isCollectable()) {
                    System.out.printf("collectible ");
                } else {
                    System.out.printf("%d - ", ((Item) allCards.get(i)).cost);
                }
                System.out.printf("Desc : %s\n", ((Item) allCards.get(i)).getDesc());
            }
        }

        counter = 0;
        System.out.println("Cards :");
        for (int i = 0; i < allCards.size(); i++) {
            if (allCards.get(i) instanceof Minion) {
                counter++;
                System.out.printf("      %d : Type : Minion - Name : %s - ", counter, ((Minion) allCards.get(i)).name);
                System.out.printf("Cost : %d - Class : %s - ", ((Minion) allCards.get(i)).cost, ((Minion) allCards.get(i)).getClas());
                System.out.printf("AP : %d - ", ((Minion) allCards.get(i)).getAP());
                System.out.printf("HP : %d - ", ((Minion) allCards.get(i)).getHP());
                System.out.printf("MP : %d - ", ((Minion) allCards.get(i)).MP);
                System.out.printf("Special power : %s\n", ((Minion) allCards.get(i)).getSpecialPower());
            } else if (allCards.get(i) instanceof Spell) {
                counter++;
                System.out.printf("      %d : Type : Spell - Name : %s - ", counter, ((Spell) allCards.get(i)).name);
                System.out.printf("Cost : %d - MP : %d - ", ((Spell) allCards.get(i)).cost, ((Spell) allCards.get(i)).MP);
                System.out.printf("Desc : %s\n", ((Spell) allCards.get(i)).getDesc());
            }
        }

    }

    public int indexOfCard(String cardName) {
        for (int i = 0; i < allCards.size(); i++) {
            if (allCards.get(i).name.matches(cardName))
                return i;
        }
        return -1;
    }

    public void search(String cardName) {
        if (indexOfCard(cardName) == -1) {
            System.out.println("Dont exist this card");
            return;
        }
        System.out.println("Exist");
        for (int i = 0; i < allCards.size(); i++) {
            if (allCards.get(i).name.matches(cardName)) {
                String[] cardID = allCards.get(i).cardID.split("_");
                System.out.println(Integer.parseInt(cardID[2]));
            }
        }
    }

    public int indexOfDeck(String deckName) {
        for (int i = 0; i < allDecks.size(); i++) {
            if (allDecks.get(i).getName().matches(deckName))
                return i;
        }
        return -1;
    }

    public void deleteCard(String cardID) {

        allCards.remove(indexOfCardID(cardID));
        for (int i = 0; i < allOfCardInCollection.size(); i++) {
            if (allOfCardInCollection.get(i).cardID.matches(cardID)) {
                allOfCardInCollection.remove(i);
                return;
            }
        }

    }

    public void createDeck(String deckName) {
        if (indexOfDeck(deckName) != -1) {
            System.out.println("Exist this deck");
            return;
        }
        Deck deck = new Deck(deckName);
        allDecks.add(deck);
    }

    public void deleteDeck(String deckName) {
        if (indexOfDeck(deckName) == -1) {
            System.out.println("Don't exist this deck");
            return;
        }
        for (int i = 0; i < allDecks.get(indexOfDeck(deckName)).getDeckCard().size(); i++) {
            allCards.add(allDecks.get(indexOfDeck(deckName)).getDeckCard().get(i));
        }
        allDecks.remove(indexOfDeck(deckName));
    }

    public int indexOfCardID(String cardID) {
        for (int i = 0; i < allCards.size(); i++) {
            if (allCards.get(i).cardID.matches(cardID))
                return i;
        }
        return -1;
    }

    public void addCardToDeck(String cardID, String deckName) {

        if (indexOfCardID(cardID) == -1) {
            System.out.println("Don't exist this card");
            return;
        }
        if (indexOfDeck(deckName) == -1) {
            System.out.println("Don't exist this deck");
            return;
        }
        if (allDecks.get(indexOfDeck(deckName)).indexOfCardInDeck(cardID) != -1) {
            System.out.println("This card exist in selected deck");
            return;
        }
        if (!(allCards.get(indexOfCardID(cardID)) instanceof Hero) && !((allCards.get(indexOfCardID(cardID)) instanceof Item)))
            if (allDecks.get(indexOfDeck(deckName)).counterOfCard() == 20) {
                System.out.println("Exist 20 cards in selected deck. You can't add more cards");
                return;
            }
        if (allCards.get(indexOfCardID(cardID)) instanceof Hero) {
            if (allDecks.get(indexOfDeck(deckName)).existHeroInDeck()) {
                System.out.println("Exist a Hero in selected deck");
                return;
            }
        }
        allDecks.get(indexOfDeck(deckName)).addCard(allCards.get(indexOfCardID(cardID)));
        allCards.remove(indexOfCardID(cardID));

    }

    public void removeCardFromDeck(String cardID, String deckName) {

        if (indexOfDeck(deckName) == -1) {
            System.out.println("Don't exist this deck");
            return;
        }
        if (allDecks.get(indexOfDeck(deckName)).indexOfCardInDeck(cardID) == -1) {
            System.out.println("Don't exist this card in selected deck");
            return;
        }
        allCards.add(allDecks.get(indexOfDeck(deckName)).getDeckCard().get(allDecks.get(indexOfDeck(deckName)).indexOfCardInDeck(cardID)));
        allDecks.get(indexOfDeck(deckName)).deleteCard(cardID);

    }

    public boolean validate(String deckName) {
        if (indexOfDeck(deckName) == -1) {
            System.out.println("Don't exist this deck");
            return false;
        }
        if (allDecks.get(indexOfDeck(deckName)).counterOfCard() < 20) {
            System.out.println("This deck don't have enough card");
            return false;
        }
        if (!allDecks.get(indexOfDeck(deckName)).existHeroInDeck()) {
            System.out.println("This deck don't have hero");
            return false;
        }
        System.out.println("Valid deck");
        return true;
    }

    public void selectDeck(String deckName) {

        if (!validate(deckName)) {
            System.out.println("Invalid deck");
            return;
        }
        mainDeck = allDecks.get(indexOfDeck(deckName));

    }

    public void showAllDecks() {

        boolean existMainDeck = false;

        if (mainDeck != null) {
            System.out.println("1 : deck_1 :");
            show(mainDeck.getDeckCard());
            existMainDeck = true;
        }
        for (int i = 0; i < allDecks.size(); i++) {
            if (allDecks.get(i).equals(mainDeck))
                continue;
            if (existMainDeck) {
                System.out.printf("%d : %s :\n", i + 2, allDecks.get(i).getName());
            } else {
                System.out.printf("%d : %s :\n", i + 1, allDecks.get(i).getName());
            }
            show(allDecks.get(i).getDeckCard());
        }

    }

    public void showDeck(String deckName) {
        if (indexOfDeck(deckName) == -1) {
            System.out.println("Don't exist this deck");
            return;
        }
        show(allDecks.get(indexOfDeck(deckName)).getDeckCard());
    }

    public void help() {

        System.out.println("1. exit");
        System.out.println("2. show");
        System.out.println("3. search [card name]");
        System.out.println("4. save");
        System.out.println("5. create deck [deck name]");
        System.out.println("6. delete deck [deck name]");
        System.out.println("7. add [card id] to deck [deck name]");
        System.out.println("8. remove [card id] from deck [deck name]");
        System.out.println("9. validate deck [deck name]");
        System.out.println("10. select deck [deck name]");
        System.out.println("11. show all decks");
        System.out.println("12. show deck [deck name]");

    }

    public int counterOfItem() {

        int counter = 0;
        for (int i = 0; i < allCards.size(); i++) {
            if (allCards.get(i) instanceof Item)
                counter++;
        }
        for (int i = 0; i < allDecks.size(); i++) {
            for (int j = 0; j < allDecks.get(i).getDeckCard().size(); j++) {
                if (allDecks.get(i).getDeckCard().get(i) instanceof Item)
                    counter++;
            }
        }
        return counter;

    }

    public void menu() {

        String command;
        Pattern searchPat = Pattern.compile("^search \\[(?<cardName>\\p{all}+)]$");
        Pattern createDeckPat = Pattern.compile("^create deck \\[(?<deckName>\\p{all}+)]$");
        Pattern deleteDeckPat = Pattern.compile("^delete deck \\[(?<deckName>\\p{all}+)]$");
        Pattern addPat = Pattern.compile("^add \\[(?<cardID>\\p{all}+)] to deck \\[(?<deckName>\\p{all}+)]$");
        Pattern removePat = Pattern.compile("^remove \\[(?<cardID>\\p{all}+)] from deck \\[(?<deckName>\\p{all}+)]$");
        Pattern validatePat = Pattern.compile("^validate deck \\[(?<deckName>\\p{all}+)]$");
        Pattern selectDeckPat = Pattern.compile("^select deck \\[(?<deckName>\\p{all}+)]$");
        Pattern showDeckPat = Pattern.compile("^show deck \\[(?<deckName>\\p{all}+)]$");

        Matcher matcher;
        while (true) {
            command = Main.getScanner().nextLine().toLowerCase().trim();
            if (command.matches("exit"))
                return;
            else if (command.matches("show"))
                show(allCards);
            else if (command.matches("show all decks"))
                showAllDecks();
            else if (command.matches("help"))
                help();
            matcher = searchPat.matcher(command);
            if (matcher.find()) {
                search(matcher.group("cardName"));
                continue;
            }

            matcher = createDeckPat.matcher(command);
            if (matcher.find()) {
                createDeck(matcher.group("deckName"));
                continue;
            }

            matcher = deleteDeckPat.matcher(command);
            if (matcher.find()) {
                deleteDeck(matcher.group("deckName"));
                continue;
            }

            matcher = addPat.matcher(command);
            if (matcher.find()) {
                addCardToDeck(matcher.group("cardID"), matcher.group("deckName"));
                continue;
            }

            matcher = removePat.matcher(command);
            if (matcher.find()) {
                removeCardFromDeck(matcher.group("cardID"), matcher.group("deckName"));
                continue;
            }

            matcher = validatePat.matcher(command);
            if (matcher.find()) {
                validate(matcher.group("deckName"));
                continue;
            }

            matcher = selectDeckPat.matcher(command);
            if (matcher.find()) {
                selectDeck(matcher.group("deckName"));
                continue;
            }

            matcher = showDeckPat.matcher(command);
            if (matcher.find()) {
                showDeck(matcher.group("deckName"));
            }

        }

    }

}
