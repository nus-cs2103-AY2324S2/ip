package jiayou.function;

import jiayou.task.TaskList;

/**
 * Represents a chatbot named Jiayou.
 * @author Liu Jiayao
 */
public class Jiayou {
    private static final String FILE_PATH = "./data/tasks.txt";
    private Storage storage;
    private TaskList tasks;
    private Parser parser;

    /**
     * Returns a new Jiayou instance.
     *
     */
    public Jiayou() {
        this.tasks = new TaskList();
        this.parser = new Parser();
        this.storage = new Storage(FILE_PATH, this.tasks);
        this.tasks.linkStorage(this.storage);
        this.storage.loadFromFile();
    }

    /**
     * Generates a response to user input.
     *
     * @param input the user input.
     * @return a response message.
     */
    public String getResponse(String input) {
        return parser.parseCommand(tasks, input);
    }

    /**
     * This is the entry point for the Jiayou chatbot.
     *
     * @param args command line arguments passed to the application.
     */
    public static void main(String[] args){
        // Implementation will be added in the future.
    }
}
