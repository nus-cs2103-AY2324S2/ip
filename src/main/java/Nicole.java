import java.io.BufferedReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.*;

public class Nicole {
    private static final String botName = "Nicole";

    private static String greet() {
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
        return "Welcome to Nicole 1.0. New conversation at " + digitalTime.format(now) + "\n" +
                "----------------------------- x ------------------------------" + "\n" +
                Nicole.botName +
                ": " + salutation + " What's our objective for today?";
    }

    private static String exit() {
        return "Bye, for now ;) Let me know if you need anything more.";
    }

    private static String echo() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String request = br.readLine();
        while (!request.equals("bye")) {
            System.out.println("You said: " + request);
            request = br.readLine();
        }
        return Nicole.exit();
    }

    public static void main(String[] args) {
        System.out.println(Nicole.greet());
        try {
            System.out.println(Nicole.echo());
        } catch (IOException ioException) {
            System.out.println("Error reading request.");
        }
    }
}
