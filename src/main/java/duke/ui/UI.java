package duke.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import duke.DukeException;

/**
 * Represents the user interface of the bot.
 */
public class UI {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private String botName = "Sylvia";
    private final BufferedReader reader;

    public UI(String name) {
        this.botName = name;
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    private void showLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    public void showBotError(DukeException e) {
        showLine();
        System.out.println(e.getBotMessage());
        showLine();
    }

    public void showWelcomeMessage() {
        showLine();
        System.out.println("Hello! I'm " + botName);
        System.out.println("What can I do for you?");
        showLine();
    }

    /**
     * Shows the response from the bot. If the response is null, nothing will be
     * shown.
     * 
     * @param response The response from the bot.
     */
    public void showResponse(String response) {
        if (response == null) {
            return;
        }
        showLine();
        System.out.println(response);
        showLine();
    }

    /**
     * Reads a command from the user.
     * 
     * @return The command from the user.
     */
    public String readCommand() {
        String input = "";
        try {
            input = reader.readLine();
        } catch (IOException e) {
            showLine();
            System.out.println("Something went wrong: " + e.getMessage());
            showLine();
        }
        return input;
    }
}
