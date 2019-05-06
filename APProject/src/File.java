import java.io.FileWriter;
import java.util.ArrayList;

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
                fileWriter.write(".............................." + accounts.indexOf(account) + "..............................\n\n");

            }
            fileWriter.close();
        } catch (Exception e) {
            return;
        }

    }

}
