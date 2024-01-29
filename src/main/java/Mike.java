import java.util.List;

/*
TODO:
    1. Write comments to document code
    2. Write the README.md
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
                Ui.displayError(e.getMessage());
            } finally {
                ui.displayLine();
            }
        }
        taskList.writeToFile();
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
