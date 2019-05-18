import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MultiPlayer {

    public static void help() {
        System.out.println("1. show accounts");
        System.out.println("2. select user [UserName]");
        System.out.println("3. start multi player game [mode] [number of flag ( for mode 3 )]");
        System.out.println("4. exit");
    }

    public static void showAccounts(Account account) {
        int counter = 0;
        for (Account account1 : MainMenu.getAccounts()) {
            if (account1.getUsername().matches(account.getUsername())) {
                continue;
            }
            counter++;
            System.out.printf("%d. Username : %s\n", counter, account1.getUsername());
        }
    }

    public static Account getAccountWithUser(String userName) {

        for (Account account : MainMenu.getAccounts()) {
            if (account.getUsername().matches(userName)) {
                if (account.getCollection().getMainDeck() != null) {
                    return account;
                }
                System.out.println("Selected deck for second player is invalid");
                return null;
            }
        }
        System.out.println("Don't exist this username");
        return null;

    }

    public static void startGame(Account account, Account account1, int numberOfFlag, int mode) {

        if (mode < 1 || mode > 3) {
            System.out.println("Enter correct mode between 1 and 3");
            return;
        }
        if ((mode == 3 && numberOfFlag < 2) || (mode == 3 && numberOfFlag > 45)) {
            System.out.println("Enter correct number of flag");
            return;
        }
        Custom.setPlayer1Deck(account.getCollection().getMainDeck().copyOfDeck());
        Custom.setPlayer2Deck(account1.getCollection().getMainDeck().copyOfDeck());
        GameController.setFirstAll();
        Main.setNullCardsCell();
        Main.setNullBuffsCell();
        if (mode == 1) {
            System.out.printf("Start game with [%s] in mode 1\n", account1.getUsername());
        } else if (mode == 2) {
            System.out.printf("Start game with [%s] in mode 2\n", account1.getUsername());
        } else {
            System.out.printf("Start game with [%s] in mode 3 with %d flags\n", account1.getUsername(), numberOfFlag);
        }
        if (mode == 1) {
            KillMode.game(account, account1, "custom", false);
            return;
        } else if (mode == 2) {
            SingleCustomGame.setFlagInCell();
            OneFlagMode.game(account, account1, "custom", false);
            return;
        } else {
            SingleCustomGame.setAllFlagInCell(numberOfFlag);
            MultiFlagMode.game(account, account1, numberOfFlag, "custom", false);
        }

    }

    public static void menu(Account account) {

        Pattern selectUserPat = Pattern.compile("^select user \\[(?<userName>\\p{all}+)]$");
        Pattern gamePat = Pattern.compile("^start multi player game \\[(?<mode>[0-9])]$");
        Pattern gameMode3Pat = Pattern.compile("^start multi player game \\[3] \\[(?<numberOfFlag>[0-9]+)]$");
        Matcher matcher;
        System.out.println("You are in Multi Player Menu");
        String command;
        Account account1 = null;

        while (true) {
            command = Main.getScanner().nextLine().toLowerCase().trim();
            if (command.matches("exit")) {
                return;
            } else if (command.matches("help")) {
                help();
            } else if (command.matches("show accounts")) {
                showAccounts(account);
            }

            matcher = selectUserPat.matcher(command);
            if (matcher.find()) {
                account1 = getAccountWithUser(matcher.group("userName"));
            }

            matcher = gameMode3Pat.matcher(command);
            if (matcher.find()) {
                if (account1 == null) {
                    System.out.println("Select account and try again");
                    continue;
                }
                startGame(account, account1, Integer.parseInt(matcher.group("numberOfFlag")), 3);
                account1 = null;
            }

            matcher = gamePat.matcher(command);
            if (matcher.find()) {
                if (account1 == null) {
                    System.out.println("Select account and try again");
                    continue;
                }
                startGame(account, account1, 0, Integer.parseInt(matcher.group("mode")));
                account1 = null;
            }

        }

    }

}
