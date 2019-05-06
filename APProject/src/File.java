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
                        fileWriter.write(card.name);
                        fileWriter.write(" ");
                    }
                    fileWriter.write("\n");
                }
                fileWriter.write("Decks:\n");
                if (account.getCollection().getAllDecks().size() == 0) {
                    fileWriter.write("None\n");
                } else {
                    
                }

            }
            fileWriter.close();
        } catch (Exception e) {
            return;
        }

    }

}
