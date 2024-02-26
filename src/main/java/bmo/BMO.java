package bmo;

import javafx.fxml.FXML;
import bmo.command.Command;
import bmo.ui.Ui;
import bmo.util.Parser;
import bmo.util.Storage;
import bmo.util.TaskList;

import java.io.IOException;

/* BMO is a personal assistant chatbot that helps to keep track of various tasks.
 * It is able to add, delete, mark as done, list and find tasks.
 * It is also able to save and load tasks from a file.
 */
public class BMO {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Constructor for BMO.
     */
    public BMO() {
        ui = new Ui();
        storage = new Storage();

        try {
            tasks = new TaskList(storage.loadData(), ui, storage);
        } catch (IOException e) {
            System.out.println("Error: Unable to load data. " + e.getMessage());
            tasks = new TaskList(ui);
        }
    }

    public Storage getStorage() {
        return this.storage;
    }

    public Ui getUi() {
        return this.ui;
    }

    public TaskList getTasks() {
        return this.tasks;
    }


    /**
     * Gets the response from BMO.
     *
     * @param input The input from the user.
     * @return The response from BMO.
     * @throws IOException If there is an error reading the file.
     */
    @FXML
    public String getResponse(String input) throws IOException {
        assert input != null : "Input should not be null";
        Command c = Parser.parse(input.trim());
      
        String response = c.execute(tasks, ui, storage);
        assert response != null : "Response should not be null";
      
        storage.saveData(tasks);

        return response;
    }
}
