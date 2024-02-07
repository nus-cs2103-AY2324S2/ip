package duke;

import java.io.IOException;
/**
 * This class holds the main logic for the chatbot.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    /**
     * Constructs the Duke object.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage();
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    /**
     * Handles displaying the ui and loading and storing the task list on the local storage.
     */
    private void run() {
        ui.introduce("riri");
        try {
            ui.chat(tasks);
            storage.writeToFile(tasks.toString());
        } catch (RiriException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        ui.exit();
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
