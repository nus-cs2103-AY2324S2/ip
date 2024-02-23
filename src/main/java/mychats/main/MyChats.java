package mychats.main;
import mychats.command.Command;
import mychats.exception.MyChatsException;

/**
 * Represents the main class for the chat application.
 */
public class MyChats {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private static final String filePath = "data/mychats.txt";
    private boolean isExit = false;

    /**
     * Generates a response to user input.
     */
    String getResponse(String input) {
        if (isExit) {
            return "";
        }
        try {
            ui.repeat();
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
            isExit = c.isExit();
            return ui.getAnswer();
        } catch (MyChatsException e) {
            return e.getMessage();
        }
    }

    /**
     * Constructs a MyChats object.
     * Initialises the storage, user interface and task list.
     */
    MyChats() {
        storage = new Storage(filePath);
        ui = new Ui();
        try {
            tasks = new TaskList(storage.loadList());
        } catch (MyChatsException e) {
            System.out.println(e.getMessage());
            tasks = new TaskList();
        }
    }
}