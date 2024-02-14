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
        // Assert that ui, storage, and taskList are not null
        assert ui != null : "Ui should not be null";
        assert storage != null : "Storage should not be null";
        assert taskList != null : "Task list should not be null";
    }

    // /** Function to start running the chatbot */
    // public void run() {
    //     Scanner scanner = new Scanner(System.in);
    //     do { // continue the program until 'bye' command is inputted.
    //         String input = scanner.nextLine().trim();
    //         Command c = Parser.parseCommand(input);
    //         c.execute(this.taskList, this.ui, this.storage);
    //         this.storage.saveTasks(taskList);
    //         System.out.println("____________________________________________________________");
    //     } while (ui.isRunning);
    //     scanner.close();
    // }

    // /** Main method to run the chatbot */
    // public static void main(String[] args) {
    //     new Duke().run();
    // }

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
}
