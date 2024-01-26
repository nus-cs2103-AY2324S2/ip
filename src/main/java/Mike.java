import java.util.List;

/*
TODO:
    1. Update text ui test
    2. Extend text ui test cases
    3. Write comments to document code
    4. Write the README.md
 */

public class Mike {
    private final TaskList taskList;
    private final Ui ui;

    Mike() {
        this.taskList = new TaskList();
        this.ui = new Ui();
    }

    public void run() {
        this.ui.displayWelcome();
        boolean exitSeen = false;
        while (!exitSeen) {
            try {
                String userInput = ui.scanInput();
                ui.displayLine();
                List<Token> tokens = new CommandScanner(userInput).scanTokens();
                // System.out.println(tokens);
                Command command = new CommandParser(tokens).parse();
                command.execute(taskList, ui);
                if (command.isExit()) {
                    exitSeen = true;
                }
            } catch (MikeException e) {
                ui.displayError(e.getMessage());
            } finally {
                ui.displayLine();
            }
        }
    }

    /**
     * Main method that runs the program.
     * @param args n/a
     */
    public static void main(String[] args) {
        new Mike().run();
    }
}
