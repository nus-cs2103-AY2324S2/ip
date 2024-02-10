package kai;

import kai.task.TaskList;
import kai.util.DukeException;
import kai.util.Parser;
import kai.util.Storage;
import kai.ui.Ui;

public class Kai {
    public Storage storage;
    public TaskList taskList;

    /**
     * Creates new Duke object.
     * Initialise Ui, Storage and TaskList.
     *
     * @param filePath FilePath of the save file.
     */
    public Kai(String filePath) {
        storage = new Storage(filePath);
        storage.createFile();
        Ui.showWelcome();
        taskList = new TaskList(storage.load());
    }

    public String getResponse(String input) throws DukeException {
        String response = "";
        response = response + Parser.parse(input, taskList);
        storage.writeFile(taskList);

        return response;
    }
}
