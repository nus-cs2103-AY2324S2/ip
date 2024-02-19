package talkingjohn;

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
    private final String filePath = "./data/TalkingJohn.txt";


    /**
     * Constructs a TalkingJohn object.
     *
     */
    public TalkingJohn() {
        this.ui = new Ui();
        List<Task> taskArr = new ArrayList<>();
        this.storage = new Storage(filePath, taskArr);
        this.taskList = new TaskList(storage.loadTasksFromFile(), ui);
        assert taskArr != null : "taskArr cannot be null";
    }

    /**
     * Runs the TalkingJohn application.
     * Starts the parser to handle user input.
     */
    String getResponse(String input) {
        Parser parser = new Parser(ui, storage, taskList);
        return parser.handleInput(input);
    }
}
