package homie;

import java.io.IOException;

/**
 * A chatbot program named Homie that helps you keep track
 * of to-do tasks, deadlines and events. Date and time can be specified for deadlines and events.
 * Other functions include adding tasks, finding tasks, marking or un-marking tasks as done,
 * deleting tasks and listing tasks.
 */
public class Homie {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for Homie class. Initialise storage and task list to be used later.
     */
    public Homie() {
        this.ui = new Ui();
        try {
            this.storage = new Storage();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        this.tasks = new TaskList(storage.loadTasksFromFile());
    }

    /**
     * Returns a String response based on user input to the GUI.
     */
    public String getResponse(String input) {
        Parser parser = new Parser(this.tasks, this.ui, this.storage);
        try {
            return parser.parse(input);
        } catch (HomieException | TodoException | EventException | DeadlineException | UnmarkException | MarkException
                 | DeleteException | FindException e) {
            return e.getMessage();
        } catch (Exception ex) {
            return "Invalid Command!!";
        }
    }
}
