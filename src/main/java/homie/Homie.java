package homie;

import javax.imageio.IIOException;
import java.io.IOException;
import java.util.Scanner;

/**
 * A chatbot programme named Homie that helps you keep track
 * of to-do tasks, deadlines and events. Date and time can be specified for deadlines and events.
 * Other functions include adding tasks, finding tasks, marking or un-marking tasks as done,
 * deleting tasks and listing tasks.
 */
public class Homie {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for Homie class
     */
    public Homie() {
        this.ui = new Ui();
        try {
            this.storage = new Storage();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        this.tasks = new TaskList(storage.loadTasksFromFile());
        ui.showLoadingError();
    }

    /**
     * Returns a String response based on user input to the GUI.
     */
    public String getResponse(String input) throws HomieException {
        Parser parser = new Parser(this.tasks, this.ui, this.storage);
        try {
            String outputResponse = parser.parse(input);
            return outputResponse;
        } catch (HomieException | TodoException e) {
            return e.getMessage();
        }
    }
}
