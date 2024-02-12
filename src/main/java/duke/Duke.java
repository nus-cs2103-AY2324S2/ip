package duke;

import duke.command.Command;
import duke.exception.ChatBotCommandException;
import duke.exception.ChatBotParameterException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;

public class Duke {

    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;



    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList();
        try {
            storage.loadTasksFromFileToTaskList(tasks);
        } catch (ChatBotParameterException | IOException e) {
            System.out.println(ui.showLoadingError());
        }
    }

    public void run() {
        System.out.println(ui.showWelcome());
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                System.out.println(ui.showLine()); // show the divider line ("_______")
                Command c = Parser.parseCommand(fullCommand);
                System.out.print(c.execute(storage, ui, tasks));
                isExit = c.isExit();
            } catch (ChatBotCommandException | ChatBotParameterException e) {
                System.out.println(ui.showError(e.getMessage()));
            } finally {
                System.out.println(ui.showLine());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("tasks.txt").run();
    }


}
