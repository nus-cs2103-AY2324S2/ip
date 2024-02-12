package mike;

import java.util.List;

import mike.command.Command;

/**
 * Mike class.
 */
public class Mike {
    private static final String FILE_PATH = "./data/mike.txt";
    private final TaskList taskList;
    private final Ui ui;
    private final Storage storage;

    /**
     * Constructor.
     */
    public Mike() {
        // Referenced from https://nus-cs2103-ay2324s2.github.io/website/schedule/week3/project.html#a-moreoop
        this.ui = new Ui();
        this.storage = new Storage(FILE_PATH);
        this.taskList = storage.load();
    }

    /**
     * Starts the main control loop of Mike.
     */
    public void run() {
        ui.displayWelcome();

        boolean isExit = false;

        // Referenced from https://nus-cs2103-ay2324s2.github.io/website/schedule/week3/project.html#a-moreoop
        while (!isExit) {
            try {
                String userInput = ui.scanInput();
                Ui.displayLine();

                List<Token> tokens = new CommandScanner(userInput).scanTokens();

                Command command = new CommandParser(tokens).parse();

                String response = command.execute(taskList);
                Ui.display(response);

                if (command.isExit()) {
                    isExit = true;
                }
            } catch (MikeException e) {
                Ui.displayError(e.getMessage());
            } finally {
                Ui.displayLine();
            }
        }

        storage.writeToFile(taskList);
        Ui.display("Session terminated: data saved successfully.");
    }

    /**
     * Saves the tasklist into file.
     */
    public void save() {
        // ui.close();
        storage.writeToFile(taskList);
    }

    public String getResponse(String userInput) {
        try {
            List<Token> tokens = new CommandScanner(userInput).scanTokens();
            Command command = new CommandParser(tokens).parse();
            String response = command.execute(taskList);
            return response;
        } catch (MikeException e) {
            return e.getMessage();
        } catch (NullPointerException e) {
            return e.getMessage();
        }
    }
}
