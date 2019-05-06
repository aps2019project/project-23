public class Battle {

    public static void menuHelp() {
        System.out.println("1. single player");
        System.out.println("2. multi player");
        System.out.println("3. exit");
    }

    public static void singlePlayMenuHelp() {
        System.out.println("1. story");
        System.out.println("2. custom game");
        System.out.println("3. exit");
    }

    public static void singlePlayMenu(Account account) {

        String command;
        while (true) {

            command = Main.getScanner().nextLine().toLowerCase().trim();
            if (command.matches("help")) {
                singlePlayMenuHelp();
            } else if (command.matches("exit")) {
                return;
            }
            else if (command.matches("custom game")){
                singleCustomGame.menu(account);
            }

        }

    }

    public static void menu(Account account) {

        String command;
        while (true) {

            command = Main.getScanner().nextLine().trim().toLowerCase();
            if (command.matches("single player")) {
                singlePlayMenu(account);
            } else if (command.matches("multi player")) {

            } else if (command.matches("exit")) {
                return;
            } else if (command.matches("help")) {
                menuHelp();
            }

        }

    }

}
