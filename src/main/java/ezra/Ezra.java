package ezra;

import java.io.FileNotFoundException;

/**
 * Main class for the Ezra chatbot.
 */
public class Ezra {

    private Storage storage;
    private TaskList tasks;

    /**
     * Constructs an Ezra object with the specified file path to load saved tasks from.
     *
     * @param filepath The file path for storing tasks.
     */
    public Ezra(String filepath) {
        this.storage = new Storage(filepath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (FileNotFoundException | WrongFormatException e) {
            this.tasks = new TaskList();
        }
    }

    /**
     * Generates a response based on the given input using the Parser class.
     *
     * @param input The input given the user.
     * @return The response message to the user's input.
     */
    public String getResponse(String input) {
        return Parser.generateReply(input, storage, tasks);
    }
}
