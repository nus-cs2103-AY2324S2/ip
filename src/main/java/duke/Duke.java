package duke;

import duke.command.Command;
import duke.task.Parser;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke {
    private static final String FILE_NAME = "duke.state";

    private TaskList taskList;
    private Storage storage;
    private final Ui ui;

    public Duke(String fileName) {
        storage = new Storage(fileName);
        ui = new Ui();
        try {
            taskList = storage.readTaskList();
        } catch (IOException | ClassNotFoundException e) {
            taskList = new TaskList();
        }
    }

    public void repl() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (Parser.InvalidCommand e) {
                ui.showCommandNotFound(e.getCommand());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke(FILE_NAME);
        boolean successful = false;
        try {
            duke.taskList = duke.storage.readTaskList();
            successful = true;
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find state file \"" + FILE_NAME + "\"");
        } catch (IOException e) {
            System.out.println("Cannot read from state file \"" + FILE_NAME + "\"");
        } catch (ClassNotFoundException e) {
            System.out.println("The data has been corrupted");
        } finally {
            if (!successful) {
                System.out.println("Continuing with no saved state.");
            }
        }

        duke.repl();

        try {
            duke.storage.writeTaskList(duke.taskList);
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find state file \"" + FILE_NAME + "\"");
        } catch (IOException e) {
            System.out.println("Cannot write to state file \"" + FILE_NAME + "\"");
        }
    }
}
