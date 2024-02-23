package tasklist;

import java.util.Scanner;

import tasklist.commands.Command;

/** Main class of the chatbot application. Initializes the application */
public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /** constructor implementation */
    public Duke() {
        ui = new Ui();
        storage = new Storage("./data/duke.ser");
        taskList = storage.loadTasks();
<<<<<<< HEAD
=======
        // Assert that ui, storage, and taskList are not null
        assert ui != null : "Ui should not be null";
        assert storage != null : "Storage should not be null";
        assert taskList != null : "Task list should not be null";
>>>>>>> branch-A-CodeQuality
    }

    public String processResponse(String input) {
        Command outputCommand = Parser.parseCommand(input);
        String output = outputCommand.execute(taskList, ui, storage);
        assert output != null : "Should always return a message";
        this.storage.saveTasks(taskList);
        return output;
    }

    public boolean isRunning() {
        return ui.isRunning;
    }

    public TaskList getList() {
        return this.taskList;
    }
}
