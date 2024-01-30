package duke;

import duke.command.Command;
import duke.exception.ChatBotCommandException;
import duke.exception.ChatBotParameterException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class Duke {

    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;



    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList();
        storage.loadTasksFromFileToTaskList(tasks);
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parseCommand(fullCommand);
                c.execute(storage, ui, tasks);
                isExit = c.isExit();
            } catch (ChatBotParameterException | ChatBotCommandException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("tasks.txt").run();
    }


}
