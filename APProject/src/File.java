package sample;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class File {

    public static void saveAccountInFile(ArrayList<Account> accounts) {

        try {
            FileWriter fileWriter = new FileWriter("/home/shajusoni/IdeaProjects/APProjectGraph/account.txt");
            for (Account account : accounts) {
                fileWriter.write("Username:" + account.getUsername() + "\n");
                fileWriter.write("Password:" + account.getPassword() + "\n");
                fileWriter.write("Money:" + account.getBudget() + "\n");
                fileWriter.write("ProfilePhoto:" + account.getProfilePhoto() + "\n");
                fileWriter.write("MatchHistory:\n");
                if (account.getAllMatches().size() == 0) {
                    fileWriter.write("None\n");
                } else {
                    for (Match match : account.getAllMatches()) {
                        fileWriter.write(account.getAllMatches().indexOf(match) + ")");
                        fileWriter.write("name:" + match.getNameOfOpponent() + "win:" + match.getWinOrLose() + "time:" + match.getTimeOfMatch());
                        fileWriter.write("\n");
                    }
                }
                fileWriter.write("Collection:\n");
                if (account.getCollection().getAllCards().size() == 0) {
                    fileWriter.write("None\n");
                } else {
                    for (Card card : account.getCollection().getAllCards()) {
                        fileWriter.write(account.getCollection().getAllCards().indexOf(card) + ")");
                        fileWriter.write(card.name + " " + card.cardID);
                        fileWriter.write("\n");
                    }
                    fileWriter.write("\n");
                }

                for (Deck deck : account.getCollection().getAllDecks()) {
                    if (deck == account.getCollection().getMainDeck()) {
                        continue;
                    }
                    fileWriter.write("Deck: ");
                    fileWriter.write(account.getCollection().getAllDecks().indexOf(deck) + ")" + deck.getName() + ": ");
                    for (Card card : deck.getDeckCard()) {
                        fileWriter.write(deck.getDeckCard().indexOf(card) + ")" + card.name + "," + card.cardID + " ");
                    }
                    fileWriter.write("\n");
                }

                if (account.getCollection().getMainDeck() != null) {
                    fileWriter.write("MainDeck: ");
                    fileWriter.write(account.getCollection().getMainDeck().getName() + ": ");
                    for (Card card : account.getCollection().getMainDeck().getDeckCard()) {
                        fileWriter.write(account.getCollection().getMainDeck().getDeckCard().indexOf(card) + ")" + card.name + "," + card.cardID + " ");
                    }
                    fileWriter.write("\n");
                }
                fileWriter.write("\n");
                fileWriter.write("finish\n\n");

            }
            fileWriter.close();
        } catch (Exception e) {
            return;
        }

    }


    public static void loadAccount() {

        if (!Main.setFileScanner()) {
            return;
        }
        String command;
        Matcher matcher;
        Pattern photoPat = Pattern.compile("^ProfilePhoto:(?<profilePhoto>\\p{all}+)$");
        Pattern usernamePat = Pattern.compile("^Username:(?<username>\\p{all}+)$");
        Pattern passwordPat = Pattern.compile("^Password:(?<password>\\p{all}+)$");
        Pattern matchHistoryPat = Pattern.compile("^[\\d]+[)]name:(?<name>\\p{all}+)win:(?<win>\\p{all}+)time:(?<time>\\p{all}+)$");
        Pattern collectionPat = Pattern.compile("^[\\d]+[)](?<cardName>\\p{all}+) (?<cardID>\\p{all}+)$");
        Pattern moneyPat = Pattern.compile("^Money:(?<money>\\d+)$");
        Pattern deckPat = Pattern.compile("^[\\d]+[)](?<deckName>\\p{all}+):$");
        Pattern cardInDeckPat = Pattern.compile("^[\\d]+[)](?<cardName>\\p{all}+),(?<cardID>\\p{all}+)$");
        Pattern mainDeckPat = Pattern.compile("^(?<deckName>\\p{all}+):$");

        Account account = new Account("", "");
        String username = "";
        String password = "";
        String[] commands;
        while (Main.getScanner().hasNext()) {

            command = Main.getScanner().nextLine();
            commands = command.split(" ");

            if (commands[0].matches("Deck:")) {
                matcher = deckPat.matcher(commands[1]);
                Deck deck;
                if (matcher.find()) {
                    deck = new Deck(matcher.group("deckName"));
                    for (int i = 2; i < commands.length; i++) {
                        matcher = cardInDeckPat.matcher(commands[i]);
                        if (matcher.find()) {
                            Card card = Shop.getCard(matcher.group("cardName"));
                            card.setCardID(matcher.group("cardID"));
                            account.getCollection().addToAllOfCardInCollection(card);
                            deck.addCard(card);
                        }
                    }
                    account.getCollection().addAllDeck(deck);
                }
            } else if (commands[0].matches("MainDeck:")) {
                matcher = mainDeckPat.matcher(commands[1]);
                Deck deck;
                if (matcher.find()) {
                    deck = new Deck(matcher.group("deckName"));
                    for (int i = 2; i < commands.length; i++) {
                        matcher = cardInDeckPat.matcher(commands[i]);
                        if (matcher.find()) {
                            Card card = Shop.getCard(matcher.group("cardName"));
                            card.setCardID(matcher.group("cardID"));
                            deck.addCard(card);
                        }
                    }
                    account.getCollection().setMainDeck(deck);
                    account.getCollection().addAllDeck(deck);
                }
            }

            matcher = usernamePat.matcher(command);
            if (matcher.find()) {
                username = matcher.group("username");
                continue;
            }

            matcher = passwordPat.matcher(command);
            if (matcher.find()) {
                password = matcher.group("password");
                account = new Account(username, password);
                continue;
            }

            matcher = moneyPat.matcher(command);
            if (matcher.find()) {
                account.setBudget(Integer.parseInt(matcher.group("money")));
                continue;
            }

            if (command.matches("MatchHistory:")) {
                command = Main.getScanner().nextLine();
                matcher = matchHistoryPat.matcher(command);
                while (matcher.find()) {
                    Match match = new Match(matcher.group("name"), true);
                    if (matcher.group("win").matches("win")) {
                        match.setWonOrLose(true);
                    } else {
                        match.setWonOrLose(false);
                    }
                    match.setTimeOfMatch(matcher.group("time"));
                    account.addMatch(match);
                    command = Main.getScanner().nextLine();
                    matcher = matchHistoryPat.matcher(command);
                }

            }

            if (command.matches("Collection:")) {
                command = Main.getScanner().nextLine();
                matcher = collectionPat.matcher(command);
                while (matcher.find()) {
                    Card card = Shop.getCard(matcher.group("cardName"));
                    if (card != null) {
                        card.setCardID(matcher.group("cardID"));
                        account.getCollection().addCardToCollection(card);
                    }
                    command = Main.getScanner().nextLine();
                    matcher = collectionPat.matcher(command);
                }
            }

            matcher = photoPat.matcher(command);
            if (matcher.find()) {
                account.setProfilePhoto(matcher.group("profilePhoto"));
            }

            if (command.matches("finish")) {
                MainMenu.addAccount(account);
            }

        }

    }

}
