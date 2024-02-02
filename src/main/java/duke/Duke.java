package duke;

import duke.task.TaskList;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.Ui;

public class Duke {
    public Storage storage;
    public TaskList taskList;
    public Ui ui;

    /**
     * Creates new Duke object.
     * Initialise Ui, Storage and TaskList.
     *
     * @param filePath FilePath of the save file.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        taskList = new TaskList(storage.load());
    }

    /**
     * Reads previous user input, creates new file if the save file is not found.
     * Writes new list of tasks into the save file.
     */
    public void run() {
        ui.showWelcome();
        storage.createFile();
        String input = ui.readUserInput();
        while(!input.equals("bye")) {
            Parser.parse(input, taskList);
            input = ui.readUserInput();
        }
        storage.writeFile(taskList);
        ui.showFarewell();
    }

    public static void main(String[] args) {
        final String FILE_LOCATION = "data/duke.txt";
        new Duke(FILE_LOCATION).run();
    }
}
