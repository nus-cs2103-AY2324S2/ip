package wis;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

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
            return "";
        } catch (IOException e) {
            return WisException.loadFileExceptionHandler();
        } catch (InputMismatchException e) {
            return WisException.loadFileExceptionHandler();
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
