package dino;

import java.io.IOException;

import dino.command.Command;

/** The main class for the Dino application. */
public class Dino {
    private static final String FILE_PATH = "./data/dino.txt";
    private Storage storage;
    private TaskList tasks;

    /**
     * Enumeration representing the types of tasks in the Dino application.
     * Tasks can be of type TODO, DEADLINE, or EVENT.
     */
    public enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }

    /**
     * Constructs a new Dino instance with the specified file path.
     */
    public Dino() {
        storage = new Storage(FILE_PATH);
        tasks = storage.loadTasksFromFile();
    }

    /**
     * Returns feedback message based on user command.
     *
     * @param input Input command and parameters for Duke to process.
     * @return Message on command execution.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parseCommand(input);
            return command.execute(this.tasks, this.storage);
        } catch (DinoException e) {
            return e.getMessage();
        } catch (IOException e) {
            return "Hmm.. it seems that the file is not working... Try restarting?";
        }
    }
}
