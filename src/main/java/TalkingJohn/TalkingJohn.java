package TalkingJohn;

import java.util.ArrayList;
import java.util.List;


/**
 * The main class for the TalkingJohn application.
 * TalkingJohn is a chatbot that helps manage tasks.
 */
public class TalkingJohn {
    private final Ui ui;
    private final Storage storage;
    private final TaskList taskList;

    /**
     * Constructs a TalkingJohn object.
     *
     * @param filePath The file path for storing task data.
     */
    public TalkingJohn(String filePath) {
        this.ui = new Ui();
        List<Task> taskArr = new ArrayList<>();
        this.storage = new Storage(filePath, taskArr);
        this.taskList = new TaskList(storage.loadTasksFromFile(), ui);
    }

    /**
     * Runs the TalkingJohn application.
     * Starts the parser to handle user input.
     */
    public void run() {
        Parser parser = new Parser(ui, storage, taskList);
        parser.handleInput();
    }

    /**
     * The main method to start the TalkingJohn application.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        TalkingJohn chatBot = new TalkingJohn("./data/TalkingJohn.txt");
        chatBot.run();
    }
}
