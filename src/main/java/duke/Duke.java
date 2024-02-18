package duke;

import java.io.IOException;

/**
 * This class holds the main logic for the chatbot.
 */
public class Duke {
    private CommandHandler commandHandler;
    private TaskList tasks;
    private Storage storage;

    /**
     * Constructs the Duke object.
     */
    public Duke() {
        commandHandler = new CommandHandler();
        storage = new Storage();
        try {
            System.out.println("Load tasks");
            tasks = new TaskList(storage.load());
            System.out.println("Able to load tasks");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Returns responses to user input.
     * @param input user input
     */
    public String getResponse(String input) {
        try {
            return commandHandler.chat(input, tasks);
        } catch (RiriException | IOException e) {
            return e.getMessage();
        }
    }
}
