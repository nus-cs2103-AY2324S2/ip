package tasklist;

import tasklist.commands.Command;

/** Main class of the chatbot application. Initializes the application */
public class Michelle {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /** constructor implementation */
    public Michelle() {
        ui = new Ui();
        storage = new Storage("./data/michelle.ser");
        taskList = storage.loadTasks();
        // Assert that ui, storage, and taskList are not null
        assert ui != null : "Ui should not be null";
        assert storage != null : "Storage should not be null";
        assert taskList != null : "Task list should not be null";
    }

    /** Process user input from the gui */
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
