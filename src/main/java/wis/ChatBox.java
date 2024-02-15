package wis;

import java.io.IOException;
import java.util.InputMismatchException;

import wis.task.TaskList;
import wis.util.Printer;
import wis.util.WisException;

/**
 * Main window running chat box of the application.
 */
public class ChatBox {
    private Ui ui;
    private TaskList tasks;

    public ChatBox() {
        this.ui = new Ui("");
        this.tasks = new TaskList();
    }

    /**
     * Loads tasks from disk to launch chat box.
     */
    public String launch() {
        try {
            this.tasks = new TaskList(Storage.loadTasks());
            Printer.printActionAttach(Action.GREET);
            return "";
        } catch (IOException | InputMismatchException e) {
            return WisException.handleLoadFileException();
        }
    }

    public String getResponse(String string) {
        ui.setInput(string);
        return ui.parseInput(tasks);
    }

    public void save() {
        Storage.saveTasks(tasks);
    }
}
