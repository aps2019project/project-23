import java.util.Calendar;

public class Match {

    private String nameOfOpponent;
    private String winOrLose;
    private String timeOfMatch;

    public Match(String nameOfOppenent, boolean load) {
        if (!load) {
            Calendar time = Calendar.getInstance();
            timeOfMatch = time.toString();
        }
        this.nameOfOpponent = nameOfOppenent;
    }

    public void setWonOrLose(boolean winOrLose) {
        if (winOrLose) {
            this.winOrLose = "win";
        } else {
            this.winOrLose = "lose";
        }
    }

    public void show() {
        System.out.printf("Opponent : %s ", nameOfOpponent);
        System.out.printf("Win/Lose : %s ", winOrLose);
        System.out.printf("Time : %s\n\n", timeOfMatch);
    }

    public String getWinOrLose() {
        return winOrLose;
    }

    public String getNameOfOpponent() {
        return nameOfOpponent;
    }

    public String getTimeOfMatch() {
        return timeOfMatch;
    }

    public void setTimeOfMatch(String timeOfMatch) {
        this.timeOfMatch = timeOfMatch;
    }

}
