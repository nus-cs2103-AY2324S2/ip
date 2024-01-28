import java.io.IOException;

/**
 * Main chatbot program.
 *
 * @author KohGuanZeh
 */
public class Duke {
    // File directory of stored data.
    private static final String FILE_DIRECTORY = "data";
    // File name of stored data.
    private static final String FILE_NAME = "duke.txt";

    public static void main(String[] args) {
        Ui ui = new Ui();
        TaskList taskList = new TaskList();
        Storage storage = null;
        try {
            storage = new Storage(Duke.FILE_DIRECTORY, Duke.FILE_NAME);
            taskList.loadTasks(storage.load(), ui);
        } catch (IOException e) {
            ui.showError("Error in creating or accessing data file. Exiting...");
            return;
        }

        ui.showGreeting();

        boolean isQuit = false;
        while (!isQuit) {
            try {
                String input = ui.readline();
                ui.showLine();
                Command command = Parser.parseInput(input);
                command.run(taskList, ui, storage);
                isQuit = command.isExit();
            } catch (CommandException e) {
                ui.showError(e.getMessage());
            } catch (IOException e) {
                ui.showError("An error has occurred with the save file. Exiting...");
                isQuit = true;
            }
            finally {
                ui.showLine();
            }
        }
    }
}