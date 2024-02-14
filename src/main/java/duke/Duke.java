package duke;

import java.io.IOException;

/**
 * This class holds the main logic for the chatbot.
 */
public class Duke {
    private Ui ui;
    private TaskList tasks;
    private Storage storage;

    /**
     * Constructs the Duke object.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage();
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
//    /**
//     * Handles displaying the ui and loading and storing the task list on the local storage.
//     */
//    private void run() {
//        ui.introduce("riri");
//        try {
//            ui.chat(tasks);
//            storage.writeToFile(tasks.toString());
//        } catch (RiriException | IOException e) {
//            System.out.println(e.getMessage());
//        }
//        ui.exit();
//    }
//    public static void main(String[] args) {
//        new Duke().run();
//    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            return CommandHandler.chat(input, tasks);
        } catch (RiriException e) {
            return e.getMessage();
        }
    }
}
