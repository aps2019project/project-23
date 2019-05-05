import java.util.Calendar;

public class Match {

    private String nameOfOpponent;
    private String winOrLose;
    private String timeOfMatch;

    public Match(String nameOfOppenent) {
        Calendar time = Calendar.getInstance();
        timeOfMatch = time.toString();
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
        System.out.printf("Time : %s\n", timeOfMatch);
    }

    public String getWinOrLose() {
        return winOrLose;
    }

}
