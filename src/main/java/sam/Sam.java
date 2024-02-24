package sam;

import sam.command.Command;
import java.io.IOException;

public class Sam {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    /**
     * Constructs a new Sam object.
     *
     * Initializes the user interface, storage, and taskList.
     * If loading tasks from the specified file path fails due to IOException or SamException,
     * displays a loading error message and initializes an empty task list.
     *
     * @param FILE_PATH the file path from which to load tasks
     */
    public Sam(String FILE_PATH) {
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException | SamException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the program.
     *
     * Greets the user, then enters a loop to continuously read commands from the user.
     * Each command is parsed and executed. If a command execution results in an exit signal,
     * the loop terminates. Displays error messages if any SamException occurs during command execution.
     */
    public void run() {
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (SamException e) {
                ui.showError(e.getMessage());
            }
        }
    }
    public static void main(String[] args) {
        new Sam("././data/Sam.txt").run();
    }
}
