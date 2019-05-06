import java.io.FileWriter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class File {

    public static void saveAccountInFile(ArrayList<Account> accounts) {

        try {
            FileWriter fileWriter = new FileWriter("/home/shajusoni/IdeaProjects/APProject/account.txt");
            for (Account account : accounts) {
                fileWriter.write("Username:" + account.getUsername() + "\n");
                fileWriter.write("Password:" + account.getPassword() + "\n");
                fileWriter.write("Money:" + account.getBudget() + "\n");
                fileWriter.write("MatchHistory:\n");
                if (account.getAllMatches().size() == 0) {
                    fileWriter.write("None\n");
                } else {
                    for (Match match : account.getAllMatches()) {
                        fileWriter.write(account.getAllMatches().indexOf(match) + ")");
                        fileWriter.write("name:" + match.getNameOfOpponent() + "win:" + match.getWinOrLose() + "time:" + match.getTimeOfMatch());
                        fileWriter.write("\n");
                    }
                }
                fileWriter.write("Collection:\n");
                if (account.getCollection().getAllCards().size() == 0) {
                    fileWriter.write("None\n");
                } else {
                    for (Card card : account.getCollection().getAllCards()) {
                        fileWriter.write(account.getCollection().getAllCards().indexOf(card) + ")");
                        fileWriter.write(card.name + "_" + card.cardID);
                        fileWriter.write("\n");
                    }
                    fileWriter.write("\n");
                }
                fileWriter.write("Decks:\n");
                if (account.getCollection().getAllDecks().size() == 0) {
                    fileWriter.write("None\n");
                } else {
                    for (Deck deck : account.getCollection().getAllDecks()) {
                        fileWriter.write(account.getCollection().getAllDecks().indexOf(deck) + ")" + deck.getName() + ": ");
                        for (Card card : deck.getDeckCard()) {
                            fileWriter.write(deck.getDeckCard().indexOf(card) + "." + card.name + "_" + card.cardID + "\n");
                        }
                        fileWriter.write("\n");
                    }
                }
                fileWriter.write("MainDeck:\n");
                if (account.getCollection().getMainDeck() == null) {
                    fileWriter.write("None\n");
                } else {
                    fileWriter.write(account.getCollection().getMainDeck().getName() + ": ");
                    for (Card card : account.getCollection().getMainDeck().getDeckCard()) {
                        fileWriter.write(account.getCollection().getMainDeck().getDeckCard().indexOf(card) + ")" + card.name + "_" + card.cardID + "\n");
                    }
                    fileWriter.write("\n");
                }
                fileWriter.write("\n");
                fileWriter.write("finish\n\n");

            }
            fileWriter.close();
        } catch (Exception e) {
            return;
        }

    }


    public static void loadAccount() {

        if (!Main.setFileScanner()) {
            return;
        }
        String command;
        Matcher matcher;
        Pattern usernamePat = Pattern.compile("^Username:(?<username>\\p{all}+)$");
        Pattern passwordPat = Pattern.compile("^Password:(?<password>\\p{all}+)$");
        Pattern matchHistoryPat = Pattern.compile("^[\\d]+[)]name:(?<name>\\p{all}+)win:(?<win>\\p{all}+)time:(?<time>\\p{all}+)$");
        Pattern collectionPat = Pattern.compile("^[\\d]+[)](?<cardName>\\p{all}+)_(?<cardID>\\p{all}+)$");

        Account account = new Account("", "");
        String username = "";
        String password = "";
        while (Main.getScanner().hasNext()) {

            command = Main.getScanner().nextLine();
            matcher = usernamePat.matcher(command);
            if (matcher.find()) {
                username = matcher.group("username");
                continue;
            }

            matcher = passwordPat.matcher(command);
            if (matcher.find()) {
                password = matcher.group("password");
                account = new Account(username, password);
                continue;
            }

            if (command.matches("MatchHistory")) {
                command = Main.getScanner().nextLine();
                matcher = matchHistoryPat.matcher(command);
                while (matcher.find()) {
                    Match match = new Match(matcher.group("name"), true);
                    if (matcher.group("win").matches("win")) {
                        match.setWonOrLose(true);
                    } else {
                        match.setWonOrLose(false);
                    }
                    match.setTimeOfMatch(matcher.group("time"));
                    account.addMatch(match);
                    command = Main.getScanner().nextLine();
                    matcher = matchHistoryPat.matcher(command);
                }

            }

            if (command.matches("Collection:")) {
                command = Main.getScanner().nextLine();
                matcher = collectionPat.matcher(command);
                while (matcher.find()) {
                    Card card = Shop.getCard(matcher.group("cardName"));
                    card.setCardID(matcher.group("cardID"));
                    account.getCollection().addCardToCollection(card);
                    command = Main.getScanner().nextLine();
                    matcher = collectionPat.matcher(command);
                }
            }

            if (command.matches("Deck:")) {
                
            }


        }

    }

}
