package sample;

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
    private String profilePhoto = "";

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

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

