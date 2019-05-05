import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Account {

    private String username;
    private String password;
    private int budget;
    private int counterOfWin;
    private int MP;
    private Collection collection;

    public Collection getCollection() {
        return collection;
    }

    public Account(String username, String password) {
        this.password = password;
        this.username = username;
        this.budget = 15000;
        collection = new Collection();
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

    public void addCounterOfWin() {
        counterOfWin++;
    }

    public int getBudget() {
        return budget;
    }

    public void addBudget(int money) {
        budget += money;
    }

    public void help() {
        System.out.println("1. enter collection");
        System.out.println("2. enter shop");
        System.out.println("3. enter battle");
        System.out.println("4. exit");
        System.out.println("5. logout");
        System.out.println("6. help");
    }

    public void menu(Account account) {
        String command;
        Pattern enterPat = Pattern.compile("^enter (?<entry>\\p{all}+)$");
        Matcher matcher;
        while (true) {
            command = Main.getScanner().nextLine();
            command = command.trim();
            if (command.toLowerCase().matches("help")) {
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
                        Battle.menu();
                    }
                }
            }
        }
    }

}
