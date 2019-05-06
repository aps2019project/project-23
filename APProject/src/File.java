import java.io.FileWriter;
import java.util.ArrayList;

public class File {

    public static void saveAccountInFile(ArrayList<Account> accounts) {

        try {
            FileWriter fileWriter = new FileWriter("/home/shajusoni/IdeaProjects/APProject/account.txt");
            for (Account account : accounts) {
                fileWriter.write("Username : " + account.getUsername() + "\n");
                fileWriter.write("Password : " + account.getPassword() + "\n");
                fileWriter.write("Money : " + account.getBudget() + "\n");
                fileWriter.write("Match history :\n");
                if (account.getAllMatches().size() == 0) {
                    fileWriter.write("      Don't exist any matches\n");
                } else {
                    for (Match match : account.getAllMatches()) {
                        fileWriter.write("      " + account.getAllMatches().indexOf(match) + ". ");
                        fileWriter.write(match.getNameOfOpponent() + " " + match.getWinOrLose() + " " + match.getTimeOfMatch());
                    }
                }

            }
            fileWriter.close();
        } catch (Exception e) {
            return;
        }

    }

}
