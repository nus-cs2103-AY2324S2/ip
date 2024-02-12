package tasklist;

import java.util.Scanner;

import tasklist.commands.Command;

/** Main class of the chatbot application. Initializes the application */
public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /** constructor implementation */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        taskList = storage.loadTasks();
    }

    /** Function to start running the chatbot */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        ui.showWelcomeMessage();
        do { // continue the program until 'bye' command is inputted.
            String input = scanner.nextLine().trim();
            Command c = Parser.parseCommand(input);
            c.execute(this.taskList, this.ui, this.storage);
            this.storage.saveTasks(taskList);
            System.out.println("____________________________________________________________");
        } while (ui.isRunning);

        scanner.close();
    }

    /** Main method to run the chatbot */
    public static void main(String[] artodogs) {
        new Duke("./data/duke.ser").run();
    }
}
