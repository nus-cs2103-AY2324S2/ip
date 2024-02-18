package jimmy;

import jimmy.essentials.Parser;
import jimmy.essentials.Storage;
import jimmy.essentials.TaskList;
import jimmy.essentials.Ui;
import jimmy.exceptions.JimmyException;

/**
 * Represents the main bot class.
 */
public class Jimmy {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;
    private final Parser parser;

    /**
     * Constructor for jimmy.Jimmy bot.
     *
     * @param filePath The file path to the file where the tasks are stored.
     */
    public Jimmy(String filePath) {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage, ui);
        } catch (JimmyException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Greets the user.
     *
     * @return The greeting message.
     */
    public String greet() {
        return ui.greetUser();
    }

    /**
     * Says goodbye to the user.
     *
     * @return The goodbye message.
     */
    public String goodbye() {
        return ui.exit();
    }

    /**
     * Run the bot.
     */
    public String getResponse(String userInput) {
        assert userInput != null;
        try {
            return parser.parseUserInput(userInput, ui, storage, tasks);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: Please only use the specified commands in the user guide.");
        } catch (JimmyException e) {
            System.out.println(e.getMessage());
        }
        return "";
    }
}
