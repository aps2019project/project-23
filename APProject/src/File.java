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
                        fileWriter.write(account.getAllMatches().indexOf(match) + ".");
                        fileWriter.write("name:" + match.getNameOfOpponent() + "win:" + match.getWinOrLose() + "time:" + match.getTimeOfMatch());
                        fileWriter.write("\n");
                    }
                }
                fileWriter.write("Collection:\n");
                if (account.getCollection().getAllCards().size() == 0) {
                    fileWriter.write("None\n");
                } else {
                    for (Card card : account.getCollection().getAllCards()) {
                        fileWriter.write(account.getCollection().getAllCards().indexOf(card) + ".");
                        fileWriter.write(card.name + "," + card.cardID);
                        fileWriter.write(" ");
                    }
                    fileWriter.write("\n");
                }
                fileWriter.write("Decks:\n");
                if (account.getCollection().getAllDecks().size() == 0) {
                    fileWriter.write("None\n");
                } else {
                    for (Deck deck : account.getCollection().getAllDecks()) {
                        fileWriter.write(account.getCollection().getAllDecks().indexOf(deck) + "." + deck.getName() + ": ");
                        for (Card card : deck.getDeckCard()) {
                            fileWriter.write(deck.getDeckCard().indexOf(card) + "." + card.name + "," + card.cardID + " ");
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
                        fileWriter.write(account.getCollection().getMainDeck().getDeckCard().indexOf(card) + "." + card.name + "," + card.cardID + " ");
                    }
                    fileWriter.write("\n");
                }
                fileWriter.write("\n");
                fileWriter.write("finish");

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

        while (Main.getScanner().hasNext()) {

            command = Main.getScanner().nextLine();
            String username = "";
            String password = "";
            matcher = usernamePat.matcher(command);
            if (matcher.find()) {
                Account account;
                username = matcher.group("username");
                command = Main.getScanner().nextLine();
                matcher = passwordPat.matcher(command);
                if (matcher.find()) {
                    password = matcher.group("password");
                    account = new Account(username, password);
                    
                }

            }

        }

    }

}
