import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Collection {

    private ArrayList<Card> allCards = new ArrayList<Card>();

    public void show() {

        int counter = 0;
        System.out.println("Heroes :");
        for (int i = 0; i < allCards.size(); i++) {
            if (allCards.get(i) instanceof Hero) {
                counter++;
                System.out.printf("      %d : Name : %s - ", counter, ((Hero) allCards.get(i)).name);
                System.out.printf("Cost : %d - AP : %d - ", ((Hero) allCards.get(i)).cost, ((Hero) allCards.get(i)).getAP());
                System.out.printf("HP : %d - Class : %s - ", ((Hero) allCards.get(i)).getHP(), ((Hero) allCards.get(i)).getClas());
                System.out.printf("Special power : %s\n", ((Hero) allCards.get(i)).getSpecialPower());
            }
        }

        counter = 0;
        System.out.println("Items :");
        for (int i = 0; i < allCards.size(); i++) {
            if (allCards.get(i) instanceof Item) {
                counter++;
                System.out.printf("      %d : Name : %s - ", counter, ((Item) allCards.get(i)).name);
                System.out.printf("Cost : ");
                if (((Item) allCards.get(i)).isCollectable()) {
                    System.out.printf("collectible ");
                } else {
                    System.out.printf("%d - ", ((Item) allCards.get(i)).cost);
                }
                System.out.printf("Desc : %s\n", ((Item) allCards.get(i)).getDesc());
            }
        }

        counter = 0;
        System.out.println("Cards :");
        for (int i = 0; i < allCards.size(); i++) {
            if (allCards.get(i) instanceof Minion) {
                counter++;
                System.out.printf("      %d : Type : Minion - Name : %s - ", counter, ((Minion) allCards.get(i)).name);
                System.out.printf("Cost : %d - Class : %s - ", ((Minion) allCards.get(i)).cost, ((Minion) allCards.get(i)).getClas());
                System.out.printf("AP : %d - ", ((Minion) allCards.get(i)).getAP());
                System.out.printf("HP : %d - ", ((Minion) allCards.get(i)).getHP());
                System.out.printf("MP : %d - ", ((Minion) allCards.get(i)).MP);
                System.out.printf("Special power : %s\n", ((Minion) allCards.get(i)).getSpecialPower());
            } else if (allCards.get(i) instanceof Spell) {
                counter++;
                System.out.printf("      %d : Type : Spell - Name : %s - ", counter, ((Spell) allCards.get(i)).name);
                System.out.printf("Cost : %d - MP : %d - ", ((Spell) allCards.get(i)).cost, ((Spell) allCards.get(i)).MP);
                System.out.printf("Desc : %s\n", ((Spell) allCards.get(i)).getDesc());
            }
        }

    }

    public void search(String cardName){

    }

    public void menu() {

        String command;
        Pattern searchPat = Pattern.compile("^search \\[(?<cardName>)\\p{all}+]$");
        Matcher matcher ;
        while (true) {
            command = Main.getScanner().nextLine().toLowerCase();
            if (command.matches("exit"))
                return;
            else if (command.matches("show"))
                show();
            matcher = searchPat.matcher(command);
            if ( matcher.find() ) {
                search(matcher.group("cardName"));
            }

        }

    }

}
