import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Shop {

    private static ArrayList<Card> allCards = new ArrayList<Card>();

    public static void addAllCards(Card card) {
        allCards.add(card);
    }

    public static ArrayList<Card> getAllCards() {
        return allCards;
    }

    public static int indexOfCardInShop(String cardName) {

        for (int i = 0; i < allCards.size(); i++) {
            if (allCards.get(i).name.matches(cardName))
                return i;
        }
        return -1;

    }

    public static void searchShop(String cardName) {

        if (indexOfCardInShop(cardName) == -1)
            System.out.println("Don't exist this card in shop");
        else
            System.out.println("Exist");

    }

    public static void buy(String cardName, Account account) {

        if (indexOfCardInShop(cardName) == -1) {
            System.out.println("Don't exist this card in shop");
            return;
        }
        if (account.getBudget() < allCards.get(indexOfCardInShop(cardName)).cost) {
            System.out.println("You don't have enough budget");
            return;
        }
        if (allCards.get(indexOfCardInShop(cardName)) instanceof Item) {
            if (account.getCollection().counterOfItem() == 3) {
                System.out.println("You have 3 items. You can't buy more item");
                return;
            }
        }

        String cardID;
        int counter = 0;
        for (int i = 0; i < account.getCollection().getAllOfCardInCollection().size(); i++) {
            if (account.getCollection().getAllOfCardInCollection().get(i).name.matches(cardName)) {
                counter++;
            }
        }
        counter++;
        cardID = account.getUsername() + "_" + cardName + "_" + counter;
        Card card = Shop.getAllCards().get(indexOfCardInShop(cardName)).copyOfCard();
        card.setCardID(cardID);
        account.getCollection().addCardToCollection(card);
        account.addBudget(-1 * card.cost);

    }

    public static void setCardID(String cardID, Account account) {

        int id = 0;
        String cardName = "";
        String playerName = "";
        String newCardID = "";
        Pattern cardIDPat = Pattern.compile("^(?<playerName>\\p{all}+)_(?<cardName>\\p{all}+)_(?<ID>\\d+)$");
        Matcher matcher = cardIDPat.matcher(cardID);
        if (matcher.find()) {
            System.out.printf("sasas\n");
            id = Integer.parseInt(matcher.group("ID"));
            cardName = matcher.group("cardName");
            playerName = matcher.group("playerName");
        }
        for (int i = 0; i < account.getCollection().getAllOfCardInCollection().size(); i++) {
            if (account.getCollection().getAllOfCardInCollection().get(i).name.matches(cardName)) {
                matcher = cardIDPat.matcher(account.getCollection().getAllOfCardInCollection().get(i).cardID);
                if (matcher.find()) {
                    System.out.printf("qwert\n");
                    if (Integer.parseInt(matcher.group("ID")) > id) {
                        int newID = Integer.parseInt(matcher.group("ID"))-1;
                        newCardID = playerName + "_" + cardName + "_" + newID;
                        account.getCollection().getAllOfCardInCollection().get(i).setCardID(newCardID);
                    }
                }
            }
        }

    }

    public static void sell(String cardID, Account account) {

        if (account.getCollection().indexOfCardID(cardID) == -1) {
            System.out.println("You don't have this card");
            return;
        }
        setCardID(cardID, account);
        account.addBudget(account.getCollection().getAllCards().get(account.getCollection().indexOfCardID(cardID)).cost);
        account.getCollection().deleteCard(cardID);

    }

    public static void help() {

        System.out.println("1. exit");
        System.out.println("2. show collection");
        System.out.println("3. search [card name]");
        System.out.println("4. search collection [card name]");
        System.out.println("5. buy [card name]");
        System.out.println("6. sell [cardID]");
        System.out.println("7. show");

    }

    public static void show(Account account) {

        account.getCollection().show(allCards);

    }

    public static void menu(Account account) {

        String command;
        Matcher matcher;
        Pattern searchShopPat = Pattern.compile("^search \\[(?<cardName>\\p{all}+)]$");
        Pattern searchCollectionPat = Pattern.compile("^search collection \\[(?<cardName>\\p{all}+)]$");
        Pattern buyPat = Pattern.compile("^buy \\[(?<cardName>\\p{all}+)]$");
        Pattern sellPat = Pattern.compile("^sell \\[(?<cardID>\\p{all}+)]$");

        while (true) {
            command = Main.getScanner().nextLine().trim().toLowerCase();
            if (command.matches("exit"))
                return;
            else if (command.matches("show collection"))
                account.getCollection().show(account.getCollection().getAllOfCardInCollection());
            else if (command.matches("help"))
                help();
            else if (command.matches("show"))
                show(account);

            matcher = searchShopPat.matcher(command);
            if (matcher.find()) {
                searchShop(matcher.group("cardName"));
                continue;
            }

            matcher = searchCollectionPat.matcher(command);
            if (matcher.find()) {
                account.getCollection().search(matcher.group("cardName"));
                continue;
            }

            matcher = buyPat.matcher(command);
            if (matcher.find()) {
                buy(matcher.group("cardName"), account);
                continue;
            }

            matcher = sellPat.matcher(command);
            if (matcher.find()) {
                sell(matcher.group("cardID"), account);
            }

        }

    }

}
