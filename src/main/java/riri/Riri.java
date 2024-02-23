package riri;

import java.io.IOException;

/**
 * This class holds the main logic for the chatbot.
 */
public class Riri {
    private final CommandHandler commandHandler;
    private TaskList tasks;
    private Storage storage;

    /**
     * Constructs the Riri object.
     */
    public Riri() {
        commandHandler = new CommandHandler();
        storage = new Storage();
        try {
            System.out.println("Load tasks");
            tasks = new TaskList(storage.load());
            System.out.println("Able to load tasks");
        } catch (IOException e) {
            System.out.println(e.getStackTrace());
        }
    }
    /**
     * Introduce the chatbot to the user.
     */
    public String welcome() {
        return "Hi my name is Riri! I am here to help you with your task list :)";
    }
    /**
     * Returns the exit response to the user.
     */
    public String endingResponse() {
        return "Bye! I will store your messages and turn off.";
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
