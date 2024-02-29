package homie;

import java.io.IOException;

/**
 * The main class for Homie, a chatbot task management application.
 */
public class Homie {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for Homie.
     * Initialises storage to load past data and update new data.
     * Initialises TaskList to store user tasks for current session.
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
        if (input.equals("bye") || input.equals("b")) {
            return "bye!";
        }
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
