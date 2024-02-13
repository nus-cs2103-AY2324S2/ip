package duke;

import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.exceptions.StorageException;
import duke.fileUtils.FilePaths;
import duke.mainUtils.Storage;
import duke.mainUtils.Ui;
import duke.tasks.TaskList;
import duke.mainUtils.Parser;

import java.util.Scanner;

public class Duke {
    private final Storage storage;
    private final TaskList taskList;
    private final Ui ui;
    private final Scanner scanner;

    public Duke() {
        ui = new Ui();
        taskList = new TaskList();
        storage = new Storage(FilePaths.TASKS_SAVE_FILE_PATH, taskList);
        scanner = new Scanner(System.in);

    }

    public void run() throws StorageException {
        ui.displayStart();
        boolean isExit = false;
        storage.load();
        while (!isExit) {
            try {
                ui.storeCommand(scanner.nextLine());
                String[] splitFullCommand = ui.getCommand();
                ui.displayLine();
                if (splitFullCommand[0].equalsIgnoreCase("bye")) {
                    isExit = true;
                }
                Command c = Parser.parseUserInput(splitFullCommand);
                c.execute(taskList, ui, storage);
            } catch (DukeException e) {
                ui.displayErrorGraphic(e.getMessage());
            } finally {
                ui.displayLine();
            }
        }
        storage.save();
    }

    public static void main(String[] args) {
        try {
            new Duke().run();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
}
