package userrequests;

import nicoleexceptions.NicoleException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Ui {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    /**
     * Initialises Ui and executes the user interaction loop.
     *
     * @throws IOException if Request throws this exception due to issues with
     *                     posting and retrieving data from hard drive.
     */
    public Ui() throws IOException {
        System.out.println(this.greet());
        this.talkToUser();
    }

    private void talkToUser() throws IOException {
        String request = br.readLine();
        while (request != null && !request.equals("bye")) {
            try {
                new Request(request);
            } catch (NicoleException e) {
                System.out.println(e);
            }
            request = br.readLine();
        }
        System.out.println(this.exit());
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
        return "Welcome to Nicole Mark II. New conversation at "
                + digitalTime.format(now) + "\n"
                + "----------------------------- x ------------------------------"
                + "\n" + "Nicole: " + salutation;
    }

    private String exit() {
        return "Bye, for now ;) Let's catch up again soon.";
    }
}
