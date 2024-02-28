package nicole.userrequests;

import java.io.IOException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import nicole.nicoleexceptions.NicoleException;

public class Ui {

    /**
     * Initialises a new Request.
     *
     * @param request the user request.
     * @return nicole's response to the request or stringified NicoleExceptions that are caught.
     * @throws IOException if there are issues saving or loading tasks from Storage.
     */
    public String interactWithUser(String request) throws IOException {
        try {
            Request requestHandler = new Request(request);
            return requestHandler.handleRequest(request);
        } catch (NicoleException e) {
            return e.toString();
        }
    }

    /**
     * Dynamically Greets the user with the current date and appropriate salutation.
     *
     * @return a greeting.
     */
    public static String greetUser() {
        DateTimeFormatter digitalTime = DateTimeFormatter.ofPattern("dd/MM/yyyy | HH:mm:ss");
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
                + digitalTime.format(now)
                + "\n\n"
                + salutation;
    }
}
