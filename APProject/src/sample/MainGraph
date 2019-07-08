package sample;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainMenu {

    private static ArrayList<Account> accounts = new ArrayList<Account>();

    public static void addAccount(Account account) {
        accounts.add(account);
    }

    public static void saveAccounts() {
        File.saveAccountInFile(accounts);
    }

    public static void loadAccounts() {
        File.loadAccount();
        Main.setScannerUser();
    }

    public static ArrayList<Account> getAccounts() {
        return accounts;
    }

    public static void rankAccounts() {
        for (int i = accounts.size() - 1; i > 0; i--) {
            for (int j = 0; j < accounts.size() - 1; j++) {
                if (accounts.get(j).getCounterOfWin() < accounts.get(j + 1).getCounterOfWin()) {
                    accounts.add(j, accounts.get(j + 1));
                    accounts.remove(j + 2);
                } else if (accounts.get(j).getCounterOfWin() == accounts.get(j + 1).getCounterOfWin()) {
                    if (Main.compareTwoString(accounts.get(j).getUsername(), accounts.get(j + 1).getUsername())) {
                        accounts.add(j, accounts.get(j + 1));
                        accounts.remove(j + 2);
                    }
                }
            }
        }
    }

    public static void showLeaderboard() {
        for (Account account : accounts) {
            account.setCounterOfWin();
        }
        rankAccounts();
        for (Account account : accounts) {
            System.out.printf("%d - UserName : %s - Wins : ", accounts.indexOf(account) + 1, account.getUsername());
            System.out.println(account.getCounterOfWin());
        }
    }

    public static void help() {
        System.out.println("1. create account [username]");
        System.out.println("2. login [username]");
        System.out.println("3. show leaderboard");
        System.out.println("4. save");
    }

    public static boolean createAccount(String username) {
        System.out.printf("Enter password:");
        String password = MainGraph.getPassword();
        if (indexOfAccount(username) != -1) {
            System.out.println("Exist username. choose another username");
            return false;
        }
        Account account = new Account(username, password);
        addAccount(account);
        return true;
    }

    public static int indexOfAccount(String username) {
        for (Account account1 : accounts) {
            if (account1.getUsername().matches(username))
                return accounts.indexOf(account1);
        }
        return -1;
    }

    public static boolean login(String username) {
        System.out.printf("Enter password:");
        String password = MainGraph.getPassword();
        if (indexOfAccount(username) == -1) {
            System.out.println("Don't exist this username");
            return false;
        }
        for (Account account : accounts) {
            if (account.getUsername().matches(username)) {
                if (account.getPassword().matches(password)) {
                    return true;
                } else {
                    System.out.println("Invalid password");
                    return false;
                }
            }
        }
        return false;
    }

    public static boolean menu() {

        Pattern createAccountPat = Pattern.compile("^create account \\[(?<username>\\p{all}+)]$");
        Pattern loginPat = Pattern.compile("^login \\[(?<username>\\p{all}+)]$");
        Matcher matcher;
        String command;
        command = MainGraph.getCommand();
        command = command.toLowerCase().trim();

        matcher = createAccountPat.matcher(command);
        if (matcher.find()) {
            return createAccount(matcher.group("username"));
        }
        matcher = loginPat.matcher(command);
        if (matcher.find())
            return login(matcher.group("username"));
        if (command.matches("show leaderboard"))
            System.out.println();//showLeaderboard();
        else if (command.matches("help"))
            help();
        else if (command.matches("exit")) {
            System.exit(0);
        }

        return true;

    }

}

