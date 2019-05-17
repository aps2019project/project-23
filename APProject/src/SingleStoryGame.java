import java.util.ArrayList;

public class SingleStoryGame {

    private static Deck deckForStage1;
    private static Deck deckForStage2;
    private static Deck deckForStage3;

    public static int counterOfCard(ArrayList<Card> cards, String cardName) {

        int counter = 0;
        for (Card card : cards) {
            if (card.name.matches(cardName)) {
                counter++;
            }
        }
        return counter;
    }

    public static void setDeckForStage1() {
        Card card;
        deckForStage1 = new Deck("computer_deck");
        card = Shop.getAllCards().get(56).copyOfCard();
        card.cardID = "computer" + "_" + card.name + "_" + (counterOfCard(deckForStage1.getDeckCard(), card.name) + 1);
        deckForStage1.getDeckCard().add(card);
        card = Shop.getAllCards().get(0).copyOfCard();
        card.cardID = "computer" + "_" + card.name + "_" + (counterOfCard(deckForStage1.getDeckCard(), card.name) + 1);
        deckForStage1.getDeckCard().add(card);
        card = Shop.getAllCards().get(6).copyOfCard();
        card.cardID = "computer" + "_" + card.name + "_" + (counterOfCard(deckForStage1.getDeckCard(), card.name) + 1);
        deckForStage1.getDeckCard().add(card);
        card = Shop.getAllCards().get(9).copyOfCard();
        card.cardID = "computer" + "_" + card.name + "_" + (counterOfCard(deckForStage1.getDeckCard(), card.name) + 1);
        deckForStage1.getDeckCard().add(card);
        card = Shop.getAllCards().get(10).copyOfCard();
        card.cardID = "computer" + "_" + card.name + "_" + (counterOfCard(deckForStage1.getDeckCard(), card.name) + 1);
        deckForStage1.getDeckCard().add(card);
        card = Shop.getAllCards().get(11).copyOfCard();
        card.cardID = "computer" + "_" + card.name + "_" + (counterOfCard(deckForStage1.getDeckCard(), card.name) + 1);
        deckForStage1.getDeckCard().add(card);
        card = Shop.getAllCards().get(17).copyOfCard();
        card.cardID = "computer" + "_" + card.name + "_" + (counterOfCard(deckForStage1.getDeckCard(), card.name) + 1);
        deckForStage1.getDeckCard().add(card);
        card = Shop.getAllCards().get(19).copyOfCard();
        card.cardID = "computer" + "_" + card.name + "_" + (counterOfCard(deckForStage1.getDeckCard(), card.name) + 1);
        deckForStage1.getDeckCard().add(card);
        card = Shop.getAllCards().get(20).copyOfCard();
        card.cardID = "computer" + "_" + card.name + "_" + (counterOfCard(deckForStage1.getDeckCard(), card.name) + 1);
        deckForStage1.getDeckCard().add(card);
        card = Shop.getAllCards().get(28).copyOfCard();
        card.cardID = "computer" + "_" + card.name + "_" + (counterOfCard(deckForStage1.getDeckCard(), card.name) + 1);
        deckForStage1.getDeckCard().add(card);
        card = Shop.getAllCards().get(30).copyOfCard();
        card.cardID = "computer" + "_" + card.name + "_" + (counterOfCard(deckForStage1.getDeckCard(), card.name) + 1);
        deckForStage1.getDeckCard().add(card);
        card = Shop.getAllCards().get(30).copyOfCard();
        card.cardID = "computer" + "_" + card.name + "_" + (counterOfCard(deckForStage1.getDeckCard(), card.name) + 1);
        deckForStage1.getDeckCard().add(card);
        card = Shop.getAllCards().get(32).copyOfCard();
        card.cardID = "computer" + "_" + card.name + "_" + (counterOfCard(deckForStage1.getDeckCard(), card.name) + 1);
        deckForStage1.getDeckCard().add(card);
        card = Shop.getAllCards().get(36).copyOfCard();
        card.cardID = "computer" + "_" + card.name + "_" + (counterOfCard(deckForStage1.getDeckCard(), card.name) + 1);
        deckForStage1.getDeckCard().add(card);
        card = Shop.getAllCards().get(37).copyOfCard();
        card.cardID = "computer" + "_" + card.name + "_" + (counterOfCard(deckForStage1.getDeckCard(), card.name) + 1);
        deckForStage1.getDeckCard().add(card);
        card = Shop.getAllCards().get(40).copyOfCard();
        card.cardID = "computer" + "_" + card.name + "_" + (counterOfCard(deckForStage1.getDeckCard(), card.name) + 1);
        deckForStage1.getDeckCard().add(card);
        card = Shop.getAllCards().get(41).copyOfCard();
        card.cardID = "computer" + "_" + card.name + "_" + (counterOfCard(deckForStage1.getDeckCard(), card.name) + 1);
        deckForStage1.getDeckCard().add(card);
        card = Shop.getAllCards().get(45).copyOfCard();
        card.cardID = "computer" + "_" + card.name + "_" + (counterOfCard(deckForStage1.getDeckCard(), card.name) + 1);
        deckForStage1.getDeckCard().add(card);
        card = Shop.getAllCards().get(51).copyOfCard();
        card.cardID = "computer" + "_" + card.name + "_" + (counterOfCard(deckForStage1.getDeckCard(), card.name) + 1);
        deckForStage1.getDeckCard().add(card);
        card = Shop.getAllCards().get(53).copyOfCard();
        card.cardID = "computer" + "_" + card.name + "_" + (counterOfCard(deckForStage1.getDeckCard(), card.name) + 1);
        deckForStage1.getDeckCard().add(card);
        card = Shop.getAllCards().get(55).copyOfCard();
        card.cardID = "computer" + "_" + card.name + "_" + (counterOfCard(deckForStage1.getDeckCard(), card.name) + 1);
        deckForStage1.getDeckCard().add(card);
        card = Shop.getAllCards().get(66).copyOfCard();
        card.cardID = "computer" + "_" + card.name + "_" + (counterOfCard(deckForStage1.getDeckCard(), card.name) + 1);
        deckForStage1.getDeckCard().add(card);
    }

    public static void setDeckFroStage2() {
        Card card;
        deckForStage2 = new Deck("computer_deck");
        card = Shop.getAllCards().get(60).copyOfCard();
        card.cardID = "computer" + "_" + card.name + "_" + (counterOfCard(deckForStage2.getDeckCard(), card.name) + 1);
        deckForStage2.getDeckCard().add(card);
        card = Shop.getAllCards().get(1).copyOfCard();
        card.cardID = "computer" + "_" + card.name + "_" + (counterOfCard(deckForStage2.getDeckCard(), card.name) + 1);
        deckForStage2.getDeckCard().add(card);
        card = Shop.getAllCards().get(2).copyOfCard();
        card.cardID = "computer" + "_" + card.name + "_" + (counterOfCard(deckForStage2.getDeckCard(), card.name) + 1);
        deckForStage2.getDeckCard().add(card);
        card = Shop.getAllCards().get(4).copyOfCard();
        card.cardID = "computer" + "_" + card.name + "_" + (counterOfCard(deckForStage2.getDeckCard(), card.name) + 1);
        deckForStage2.getDeckCard().add(card);
        card = Shop.getAllCards().get(8).copyOfCard();
        card.cardID = "computer" + "_" + card.name + "_" + (counterOfCard(deckForStage2.getDeckCard(), card.name) + 1);
        deckForStage2.getDeckCard().add(card);
        card = Shop.getAllCards().get(7).copyOfCard();
        card.cardID = "computer" + "_" + card.name + "_" + (counterOfCard(deckForStage2.getDeckCard(), card.name) + 1);
        deckForStage2.getDeckCard().add(card);
        card = Shop.getAllCards().get(12).copyOfCard();
        card.cardID = "computer" + "_" + card.name + "_" + (counterOfCard(deckForStage2.getDeckCard(), card.name) + 1);
        deckForStage2.getDeckCard().add(card);
        card = Shop.getAllCards().get(18).copyOfCard();
        card.cardID = "computer" + "_" + card.name + "_" + (counterOfCard(deckForStage2.getDeckCard(), card.name) + 1);
        deckForStage2.getDeckCard().add(card);
        card = Shop.getAllCards().get(21).copyOfCard();
        card.cardID = "computer" + "_" + card.name + "_" + (counterOfCard(deckForStage2.getDeckCard(), card.name) + 1);
        deckForStage2.getDeckCard().add(card);
        card = Shop.getAllCards().get(22).copyOfCard();
        card.cardID = "computer" + "_" + card.name + "_" + (counterOfCard(deckForStage2.getDeckCard(), card.name) + 1);
        deckForStage2.getDeckCard().add(card);
        card = Shop.getAllCards().get(24).copyOfCard();
        card.cardID = "computer" + "_" + card.name + "_" + (counterOfCard(deckForStage2.getDeckCard(), card.name) + 1);
        deckForStage2.getDeckCard().add(card);
        card = Shop.getAllCards().get(27).copyOfCard();
        card.cardID = "computer" + "_" + card.name + "_" + (counterOfCard(deckForStage2.getDeckCard(), card.name) + 1);
        deckForStage2.getDeckCard().add(card);
        card = Shop.getAllCards().get(31).copyOfCard();
        card.cardID = "computer" + "_" + card.name + "_" + (counterOfCard(deckForStage2.getDeckCard(), card.name) + 1);
        deckForStage2.getDeckCard().add(card);
        card = Shop.getAllCards().get(34).copyOfCard();
        card.cardID = "computer" + "_" + card.name + "_" + (counterOfCard(deckForStage2.getDeckCard(), card.name) + 1);
        deckForStage2.getDeckCard().add(card);
        card = Shop.getAllCards().get(34).copyOfCard();
        card.cardID = "computer" + "_" + card.name + "_" + (counterOfCard(deckForStage2.getDeckCard(), card.name) + 1);
        deckForStage2.getDeckCard().add(card);
        card = Shop.getAllCards().get(38).copyOfCard();
        card.cardID = "computer" + "_" + card.name + "_" + (counterOfCard(deckForStage2.getDeckCard(), card.name) + 1);
        deckForStage2.getDeckCard().add(card);
        card = Shop.getAllCards().get(42).copyOfCard();
        card.cardID = "computer" + "_" + card.name + "_" + (counterOfCard(deckForStage2.getDeckCard(), card.name) + 1);
        deckForStage2.getDeckCard().add(card);
        card = Shop.getAllCards().get(46).copyOfCard();
        card.cardID = "computer" + "_" + card.name + "_" + (counterOfCard(deckForStage2.getDeckCard(), card.name) + 1);
        deckForStage2.getDeckCard().add(card);
        card = Shop.getAllCards().get(47).copyOfCard();
        card.cardID = "computer" + "_" + card.name + "_" + (counterOfCard(deckForStage2.getDeckCard(), card.name) + 1);
        deckForStage2.getDeckCard().add(card);
        card = Shop.getAllCards().get(48).copyOfCard();
        card.cardID = "computer" + "_" + card.name + "_" + (counterOfCard(deckForStage2.getDeckCard(), card.name) + 1);
        deckForStage2.getDeckCard().add(card);
        card = Shop.getAllCards().get(54).copyOfCard();
        card.cardID = "computer" + "_" + card.name + "_" + (counterOfCard(deckForStage2.getDeckCard(), card.name) + 1);
        deckForStage2.getDeckCard().add(card);
        card = Shop.getAllCards().get(75).copyOfCard();
        card.cardID = "computer" + "_" + card.name + "_" + (counterOfCard(deckForStage2.getDeckCard(), card.name) + 1);
        deckForStage2.getDeckCard().add(card);
    }

    public static void setDeckForStage3() {
        Card card;
        deckForStage3 = new Deck("computer_deck");
        card = Shop.getAllCards().get(62).copyOfCard();
        card.cardID = "computer" + "_" + card.name + "_" + (counterOfCard(deckForStage3.getDeckCard(), card.name) + 1);
        deckForStage3.getDeckCard().add(card);
        card = Shop.getAllCards().get(5).copyOfCard();
        card.cardID = "computer" + "_" + card.name + "_" + (counterOfCard(deckForStage3.getDeckCard(), card.name) + 1);
        deckForStage3.getDeckCard().add(card);
        card = Shop.getAllCards().get(9).copyOfCard();
        card.cardID = "computer" + "_" + card.name + "_" + (counterOfCard(deckForStage3.getDeckCard(), card.name) + 1);
        deckForStage3.getDeckCard().add(card);
        card = Shop.getAllCards().get(11).copyOfCard();
        card.cardID = "computer" + "_" + card.name + "_" + (counterOfCard(deckForStage3.getDeckCard(), card.name) + 1);
        deckForStage3.getDeckCard().add(card);
        card = Shop.getAllCards().get(13).copyOfCard();
        card.cardID = "computer" + "_" + card.name + "_" + (counterOfCard(deckForStage3.getDeckCard(), card.name) + 1);
        deckForStage3.getDeckCard().add(card);
        card = Shop.getAllCards().get(14).copyOfCard();
        card.cardID = "computer" + "_" + card.name + "_" + (counterOfCard(deckForStage3.getDeckCard(), card.name) + 1);
        deckForStage3.getDeckCard().add(card);
        card = Shop.getAllCards().get(15).copyOfCard();
        card.cardID = "computer" + "_" + card.name + "_" + (counterOfCard(deckForStage3.getDeckCard(), card.name) + 1);
        deckForStage3.getDeckCard().add(card);
        card = Shop.getAllCards().get(16).copyOfCard();
        card.cardID = "computer" + "_" + card.name + "_" + (counterOfCard(deckForStage3.getDeckCard(), card.name) + 1);
        deckForStage3.getDeckCard().add(card);
        card = Shop.getAllCards().get(25).copyOfCard();
        card.cardID = "computer" + "_" + card.name + "_" + (counterOfCard(deckForStage3.getDeckCard(), card.name) + 1);
        deckForStage3.getDeckCard().add(card);
        card = Shop.getAllCards().get(26).copyOfCard();
        card.cardID = "computer" + "_" + card.name + "_" + (counterOfCard(deckForStage3.getDeckCard(), card.name) + 1);
        deckForStage3.getDeckCard().add(card);
        card = Shop.getAllCards().get(29).copyOfCard();
        card.cardID = "computer" + "_" + card.name + "_" + (counterOfCard(deckForStage3.getDeckCard(), card.name) + 1);
        deckForStage3.getDeckCard().add(card);
        card = Shop.getAllCards().get(33).copyOfCard();
        card.cardID = "computer" + "_" + card.name + "_" + (counterOfCard(deckForStage3.getDeckCard(), card.name) + 1);
        deckForStage3.getDeckCard().add(card);
        card = Shop.getAllCards().get(35).copyOfCard();
        card.cardID = "computer" + "_" + card.name + "_" + (counterOfCard(deckForStage3.getDeckCard(), card.name) + 1);
        deckForStage3.getDeckCard().add(card);
        card = Shop.getAllCards().get(35).copyOfCard();
        card.cardID = "computer" + "_" + card.name + "_" + (counterOfCard(deckForStage3.getDeckCard(), card.name) + 1);
        deckForStage3.getDeckCard().add(card);
        card = Shop.getAllCards().get(39).copyOfCard();
        card.cardID = "computer" + "_" + card.name + "_" + (counterOfCard(deckForStage3.getDeckCard(), card.name) + 1);
        deckForStage3.getDeckCard().add(card);
        card = Shop.getAllCards().get(43).copyOfCard();
        card.cardID = "computer" + "_" + card.name + "_" + (counterOfCard(deckForStage3.getDeckCard(), card.name) + 1);
        deckForStage3.getDeckCard().add(card);
        card = Shop.getAllCards().get(44).copyOfCard();
        card.cardID = "computer" + "_" + card.name + "_" + (counterOfCard(deckForStage3.getDeckCard(), card.name) + 1);
        deckForStage3.getDeckCard().add(card);
        card = Shop.getAllCards().get(47).copyOfCard();
        card.cardID = "computer" + "_" + card.name + "_" + (counterOfCard(deckForStage3.getDeckCard(), card.name) + 1);
        deckForStage3.getDeckCard().add(card);
        card = Shop.getAllCards().get(47).copyOfCard();
        card.cardID = "computer" + "_" + card.name + "_" + (counterOfCard(deckForStage3.getDeckCard(), card.name) + 1);
        deckForStage3.getDeckCard().add(card);
        card = Shop.getAllCards().get(47).copyOfCard();
        card.cardID = "computer" + "_" + card.name + "_" + (counterOfCard(deckForStage3.getDeckCard(), card.name) + 1);
        deckForStage3.getDeckCard().add(card);
        card = Shop.getAllCards().get(49).copyOfCard();
        card.cardID = "computer" + "_" + card.name + "_" + (counterOfCard(deckForStage3.getDeckCard(), card.name) + 1);
        deckForStage3.getDeckCard().add(card);
        card = Shop.getAllCards().get(70).copyOfCard();
        card.cardID = "computer" + "_" + card.name + "_" + (counterOfCard(deckForStage3.getDeckCard(), card.name) + 1);
        deckForStage3.getDeckCard().add(card);
    }

    public static void goToGame(String command, Account account) {

        GameController.setFirstAll();
        Custom.setPlayer1Deck(account.getCollection().getMainDeck().copyOfDeck());
        Account account1 = new Account("compter", "");
        Main.setNullBuffsCell();
        Main.setNullCardsCell();
        if (command.matches("stage 1")) {
            Custom.setPlayer2Deck(deckForStage1.copyOfDeck());
            account1.getCollection().setMainDeck(Custom.player2Deck);
            System.out.println("start game in mode 1 with hero Div Sefid");
            KillMode.game(account, account1, "story");
        } else if (command.matches("stage 2")) {
            Custom.setPlayer2Deck(deckForStage2.copyOfDeck());
            account1.getCollection().setMainDeck(Custom.player2Deck);
            SingleCustomGame.setFlagInCell();
            System.out.println("start game in mode 2 with hero Zahak");
            OneFlagMode.game(account, account1, "story");
        } else if (command.matches("stage 3")) {
            Custom.setPlayer2Deck(deckForStage3.copyOfDeck());
            account1.getCollection().setMainDeck(Custom.player2Deck);
            SingleCustomGame.setAllFlagInCell(8);
            System.out.println("start game in mode 3 with hero Arash");
            MultiFlagMode.game(account, account1, 8, "story");
        }

    }

    public static void help() {
        System.out.println("1. stage 1 ( mode 1 with hero Div Sefid )");
        System.out.println("2. stage 2 ( mode 2 with hero Zahak )");
        System.out.println("3. stage 3 ( mode 3 with hero Arash )");
    }

    public static void menu(Account account) {

        String command;
        while (true) {
            command = Main.getScanner().nextLine().trim().toLowerCase();
            if (command.matches("exit")) {
                return;
            } else if (command.matches("help")) {
                help();
            } else {
                goToGame(command, account);
            }
        }

    }

}
