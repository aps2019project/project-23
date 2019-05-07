import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Account {

    private String username;
    private String password;
    private int budget;
    private int counterOfWin;
    private int MP;
    private Collection collection;
    private ArrayList<Match> allMatches = new ArrayList<Match>();
    private int numberOfPlayer;

    public ArrayList<Match> getAllMatches() {
        return allMatches;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public void addMatch(Match match) {
        allMatches.add(match);
    }

    public void setNumberOfPlayer(int numberOfPlayer) {
        this.numberOfPlayer = numberOfPlayer;
    }

    public int getNumberOfPlayer() {
        return numberOfPlayer;
    }

    public Collection getCollection() {
        return collection;
    }

    public Account(String username, String password) {
        this.password = password;
        this.username = username;
        this.budget = 15000;
        collection = new Collection();
    }

    public void addMP(int MP) {
        this.MP += MP;
    }

    public int getMP() {
        return MP;
    }

    public void setMP(int MP) {
        this.MP = MP;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public int getCounterOfWin() {
        return counterOfWin;
    }

    public int getBudget() {
        return budget;
    }

    public void addBudget(int money) {
        budget += money;
    }

    public void setCounterOfWin() {
        int counterOfWin = 0;
        for (Match match : allMatches) {
            if (match.getWinOrLose().matches("win")) {
                counterOfWin++;
            }
        }
        this.counterOfWin = counterOfWin;
    }

    public void help() {
        System.out.println("1. enter collection");
        System.out.println("2. enter shop");
        System.out.println("3. enter battle");
        System.out.println("4. match history");
        System.out.println("5. exit");
        System.out.println("6. logout");
    }

    public void matchHistory() {
        for (int i = 0; i < allMatches.size(); i++) {
            System.out.printf("%d.", i+1);
            System.out.println("    ");
            allMatches.get(i).show();
        }
    }

    public void menu(Account account) {
        String command;
        Pattern enterPat = Pattern.compile("^enter (?<entry>\\p{all}+)$");
        Matcher matcher;
        while (true) {
            command = Main.getScanner().nextLine();
            command = command.trim();
            if (command.toLowerCase().matches("match history")) {
                matchHistory();
            } else if (command.toLowerCase().matches("help")) {
                help();
                continue;
            } else if (command.toLowerCase().matches("exit")) {
                MainMenu.saveAccounts();
                System.exit(0);
            } else if (command.toLowerCase().matches("logout"))
                return;
            command = command.toLowerCase();
            matcher = enterPat.matcher(command);
            if (matcher.find()) {
                if (matcher.group("entry").matches("collection"))
                    collection.menu();
                else if (matcher.group("entry").matches("shop"))
                    Shop.menu(account);
                else if (matcher.group("entry").matches("battle")) {
                    if (collection.validDeck()) {
                        Battle.menu(account);
                    }
                }
            }
        }
    }

}
