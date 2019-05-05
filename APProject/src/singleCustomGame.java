import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class singleCustomGame {

    public static void help() {
        System.out.println("start game [deck name] [mode] [number of flag (for mode 3)]");
    }

    public static int setGame(String deckName, int mode, int numberOfFlag, Account account) {
        if (account.getCollection().indexOfDeck(deckName) == -1) {
            System.out.println("Don't exist this deck");
            return 0;
        }
        if (mode > 3 || mode < 0) {
            System.out.println("Enter correct mode");
            return 1;
        }
        if (numberOfFlag > 45) {
            System.out.println("Enter correct number og flag");
            return 1;
        }

        return 2;

    }

    public static void menu(Account account) {

        System.out.println("Mode :");
        System.out.println("    1. Kill hero");
        System.out.println("    2. Collect flag for 6 turn");
        System.out.println("    3. Collect 1/2 of all flag");
        account.getCollection().showAllDecks();
        String command;
        Matcher matcher;
        Pattern startGamePat = Pattern.compile("^start game \\[(?<deckName>\\p{all}+)] \\[(?<mode>[0-9])]$");
        Pattern startGameMode3 = Pattern.compile("^start game \\[(?<deckName>\\p{all}+)] \\[(?<mode>[0-9])] \\[(?<numberOfFlag>[0-9]+)]$");
        while (true) {

            command = Main.getScanner().nextLine().trim().toLowerCase();
            if (command.matches("help")) {
                help();
            } else if (command.matches("exit")) {
                return;
            }
            matcher = startGamePat.matcher(command);
            if (matcher.find()) {
                setGame(matcher.group("deckName"), Integer.parseInt(matcher.group("mode")), 0, account);

            }

            matcher = startGameMode3.matcher(command);
            if (matcher.find()) {
                setGame(matcher.group("deckName"), Integer.parseInt(matcher.group("mode")), Integer.parseInt(matcher.group("numberOfFlag")), account);

            }

        }

    }

}

