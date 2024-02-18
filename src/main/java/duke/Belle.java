package duke;

import java.util.ArrayList;

import duke.command.Command;
import duke.others.BelleException;
import duke.run.Parser;
import duke.run.Storage;
import duke.run.TaskList;
import duke.run.Ui;

/**
 * Manages tasks on a list in a chatbot form.
 */
public class Belle {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    private boolean isExit = false;

    /**
     * Constructs Belle.
     *
     * @param filePath Specifies filepath to store data.
     */
    public Belle(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        tasks = new TaskList(new ArrayList<>());

        try {
            tasks = new TaskList(storage.loadList());
        } catch (BelleException e) {
            ui.printError(e.getMessage());
            tasks = new TaskList(new ArrayList<>());
        }
    }

    /**
     * Returns string response from Belle according to the
     * specified command.
     *
     * @param input Users input command to Belle.
     * @return String Belle's string response.
     */
    public String getResponse(String input) {
        try {
            Command com = parser.parse(input);
            return com.execute(storage, tasks, ui);
        } catch (BelleException e) {
            return e.getMessage();
        }

    }
}
