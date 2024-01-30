package duke;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import task.DukeException;

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

    public String readCommand() {
        String input = "";
        try {
            input = reader.readLine();
        } catch (IOException e) {
            System.out.println(HORIZONTAL_LINE);
            System.out.println("Something went wrong: " + e.getMessage());
            System.out.println(HORIZONTAL_LINE);
        }
        return input;
    }
}
