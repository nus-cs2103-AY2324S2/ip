import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Nicole {
    private static final String botName = "Nicole";

    private static String exit() {
        return "Bye, for now ;) Let me know if you need anything more.";
    }

    public static void main(String[] args) {
        DateTimeFormatter digitalTime = DateTimeFormatter.ofPattern("dd/MM/yyyy | HH:mm:ss");
        DateTimeFormatter parseHour = DateTimeFormatter.ofPattern("HH");
        LocalDateTime now = LocalDateTime.now();
        String salutation = "Good ";
        int currentHour = Integer.parseInt(parseHour.format(now));
        if (currentHour < 12 && currentHour >= 5) {
            salutation += "morning!";
        } else if (currentHour >= 12 && currentHour <= 5) {
            salutation += "afternoon!";
        } else {
            salutation += "evening!";
        }
        System.out.println( "Welcome to Nicole 1.0. New conversation at " + digitalTime.format(now) + "\n" +
                            "----------------------------- x ------------------------------" + "\n" +
                            Nicole.botName +
                            ": " + salutation + " What's our objective for today?" +
                            "\n\n" + Nicole.botName +
                            ": " + Nicole.exit());
    }
}
