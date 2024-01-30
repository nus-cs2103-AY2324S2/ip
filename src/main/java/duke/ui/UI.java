package duke.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import duke.DukeException;

public class UI {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private String botName = "Duke";
    private BufferedReader reader;

    public UI(String name) {
        this.botName = name;
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    private void showLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    public void showBotError(DukeException e) {
        System.out.println(e.getBotMessage());
        showLine();
        showLine();
    }

    public void showWelcomeMessage() {
        System.out.println("Hello! I'm " + botName);
        System.out.println("What can I do for you?");
        showLine();
        showLine();
    }

    public void showResponse(String response) {
        if (response == null) {
            return;
        }
        System.out.println(response);
        showLine();
        showLine();
    }

    public String readCommand() {
        String input = "";
        try {
            input = reader.readLine();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
            showLine();
            showLine();
        }
        return input;
    }
}
