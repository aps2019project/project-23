package sample;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SingleCustomGame {

    public static void setCardIDForComputer(Deck deck) {

        Pattern pattern = Pattern.compile("^(?<username>\\p{all}+)_(?<cardName>\\p{all}+)_(?<number>\\d+)$");
        Matcher matcher;
        String cardID = "";

        for (Card card : deck.getDeckCard()) {
            matcher = pattern.matcher(card.cardID);
            if (matcher.find()) {
                cardID = "computer" + "_" + matcher.group("cardName") + "_" + matcher.group("number");
                card.setCardID(cardID);
            }
        }

    }

    public static void help() {
        System.out.println("1. start game [deck name] [mode] [number of flag (for mode 3)]");
        System.out.println("2. show decks");
        System.out.println("3. exit");
    }

    public static void setFlagInCell() {

        Item item = new Item("flag", 0, 0, "", false, null, "","");
        Random random = new Random();
        int y;
        do {
            y = random.nextInt(5);
        } while (y == 0 || y == 4 || y == 2);
        Main.getCardsCell()[4][y] = item;

    }

    public static void setAllFlagInCell(int numberOfFlag) {

        Item item;
        Random random = new Random();
        int x, y;
        boolean number = false;
        if (numberOfFlag % 2 == 1) {
            number = true;
            numberOfFlag--;
        }
        numberOfFlag = numberOfFlag / 2;
        ArrayList<Integer[]> cell = new ArrayList<Integer[]>();
        while (numberOfFlag > 0) {
            do {
                x = random.nextInt(4);
                y = random.nextInt(5);
            } while ((x == 0 && y == 2) || (x == 8 && y == 2) || (x == 4 && y == 0) || (x == 4 && y == 4) || (x == 5 && y == 2) || (x == 3 && y == 2) || Main.getCardsCell()[x][y] != null);
            item = new Item("flag", 0, 0, "", false, null, "","");
            Integer[] integers = {x, y};
            cell.add(integers);
            Main.getCardsCell()[x][y] = item;
            numberOfFlag--;
        }
        for (int i = 0; i < cell.size(); i++) {
            int x1 = 8 - cell.get(i)[0];
            int y1 = cell.get(i)[1];
            item = new Item("flag", 0, 0, "", false, null, "","");
            Main.getCardsCell()[x1][y1] = item;
        }
        if (number) {
            int y1 = random.nextInt(5);
            item = new Item("flag", 0, 0, "", false, null, "","");
            Main.getCardsCell()[4][y1] = item;
        }

    }

    public static void setGame(String deckName, int mode, int numberOfFlag, Account account) {
        if (account.getCollection().indexOfDeck(deckName) == -1) {
            System.out.println("Don't exist this deck");
            return;
        }
        if (!account.getCollection().validate(deckName)) {
            return;
        }
        if (mode > 3 || mode < 1) {
            System.out.println("Enter correct mode between 1 and 3");
            return;
        }
        if (mode == 3 && (numberOfFlag > 45 || numberOfFlag < 1)) {
            System.out.println("Enter correct number of flag");
            return;
        }
        GameController.setFirstAll();
        Custom.setPlayer1Deck(account.getCollection().getMainDeck().copyOfDeck());
        Custom.setPlayer2Deck(account.getCollection().getAllDecks().get(account.getCollection().indexOfDeck(deckName)).copyOfDeck());
        Account account1 = new Account("computer", "");
        account1.getCollection().setMainDeck(Custom.player2Deck);
        setCardIDForComputer(account1.getCollection().getMainDeck());
        Main.setNullCardsCell();
        Main.setNullBuffsCell();
        if (mode == 1) {
            KillMode.game(account, account1, "custom", true);
        } else if (mode == 2) {
            setFlagInCell();
            OneFlagMode.game(account, account1, "custom", true);
        } else {
            setAllFlagInCell(numberOfFlag);
            MultiFlagMode.game(account, account1, numberOfFlag, "custom", true);
        }

    }

    public static void menu(Account account) {

        System.out.println("Mode :");
        System.out.println("    1. Kill hero");
        System.out.println("    2. Collect flag for 6 turn");
        System.out.println("    3. Collect 1/2 of all flag\n");
        String command;
        Matcher matcher;
        Pattern startGamePat = Pattern.compile("^start game \\[(?<deckName>\\p{all}+)] \\[(?<mode>[0-9])]$");
        Pattern startGameMode3 = Pattern.compile("^start game \\[(?<deckName>\\p{all}+)] \\[(?<mode>[0-9])] \\[(?<numberOfFlag>[2-9]+)]$");
        while (true) {

            command = Main.getScanner().nextLine().trim().toLowerCase();
            if (command.matches("exiiiiiiiiiit")) {
                MainMenu.saveAccounts();
                System.exit(0);
            }
            if (command.matches("help")) {
                help();
            } else if (command.matches("exit")) {
                return;
            } else if (command.matches("show decks")) {
                account.getCollection().showAllDecks();
            }

            matcher = startGameMode3.matcher(command);
            if (matcher.find()) {
                setGame(matcher.group("deckName"), Integer.parseInt(matcher.group("mode")), Integer.parseInt(matcher.group("numberOfFlag")), account);
                continue;
            }

            matcher = startGamePat.matcher(command);
            if (matcher.find()) {
                setGame(matcher.group("deckName"), Integer.parseInt(matcher.group("mode")), 0, account);
            }

        }

    }

}
