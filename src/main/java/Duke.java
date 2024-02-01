import java.io.IOException;

import exceptions.ChatbotException;
import commands.Command;
import dave.Ui;
import dave.Parser;
import dave.Storage;
import dave.TaskList;

public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui dave;

    public Duke(String filepath) {
        dave = new Ui();
        storage = new Storage(filepath);
        try {
            taskList = new TaskList(storage.load());
        } catch (IOException exc) {
            taskList = new TaskList();
        }
    }

    public void run() {
        dave.greet();
        boolean isExit = false;
        if (taskList.getNumberOfTasks() != 0) {
            dave.showLoadingSuccess(taskList.getNumberOfTasks());
        } else {
            dave.showLoadingError();
        }
        while (!isExit) {
            try {
                String fullCommand = dave.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, dave, storage);
                isExit = c.isExit();
            } catch (ChatbotException exc) {
                dave.showLoadingError(exc.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}