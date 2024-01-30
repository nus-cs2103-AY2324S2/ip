import java.util.List;

/*
TODO:
    1. Write comments to document code
    2. Write the README.md
 */

public class Mike {
    private static final String FILE_PATH = "./data/Mike.txt";
    private final TaskList taskList;
    private final Ui ui;
    private final Storage storage;

    Mike() {
        this.ui = new Ui();
        this.storage = new Storage(FILE_PATH);
        this.taskList = storage.load();
    }

    public void run() {
        ui.displayWelcome();
        boolean exitSeen = false;
        while (!exitSeen) {
            try {
                String userInput = ui.scanInput();
                Ui.displayLine();
                List<Token> tokens = new CommandScanner(userInput).scanTokens();
                Command command = new CommandParser(tokens).parse();
                command.execute(taskList);
                if (command.isExit()) {
                    exitSeen = true;
                }
            } catch (MikeException e) {
                Ui.displayError(e.getMessage());
            } finally {
                Ui.displayLine();
            }
        }
        storage.writeToFile(taskList);
    }

    /**
     * Main method that runs the program.
     * @param args n/a
     */
    public static void main(String[] args) {
        Mike mike = new Mike();
        mike.run();
    }
}
