public class singleCustomGame {

    public static void help() {
        System.out.println("start game [deck name] [mode] [number of flag (for mode 3)]");
    }

    public static void menu(Account account){

        System.out.println("Mode :");
        System.out.println("    1. Kill hero");
        System.out.println("    2. Collect flag for 6 turn");
        System.out.println("    3. Collect 1/2 of all flag");
        account.getCollection().showAllDecks();
        String command;
        String [] commands;
        while (true){

            command = Main.getScanner().nextLine().trim().toLowerCase();
            if (command.matches("help")){
                help();
            }
            else if (command.matches("exit")){
                return;
            }
            
        }

    }

}

