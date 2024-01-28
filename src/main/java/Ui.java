import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Ui {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public Ui() {
        System.out.println(this.greet());
    }
    public String talkToUser() throws IOException {
        String request = br.readLine();
        while (request != null && !request.equals("bye")) {
            try {
                new Request(request);
            } catch (NicoleException e) {
                System.out.println(e);
            }
            request = br.readLine();
        }
        return this.exit();
    }
    private String greet() {
        DateTimeFormatter digitalTime = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter parseHour = DateTimeFormatter.ofPattern("HH");
        LocalDateTime now = LocalDateTime.now();
        String salutation = "Good ";
        int currentHour = Integer.parseInt(parseHour.format(now));
        if (currentHour < 12 && currentHour >= 5) {
            salutation += "morns :D ! What's the plan today";
        } else if (currentHour >= 12 && currentHour < 17) {
            salutation += "afternoon zzz...how's it going";
        } else {
            salutation += "evening! Let's wrap it up soon? :P";
        }
        return "Welcome to Nicole Mark II. New conversation at " + digitalTime.format(now) + "\n" +
                "----------------------------- x ------------------------------" + "\n" +
                Nicole.botName +
                ": " + salutation;
    }
    private String exit() {
        return "Bye, for now ;) Let's catch up again soon.";
    }
}
