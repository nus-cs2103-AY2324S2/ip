package Duke;

import java.io.IOException;
import java.io.FileNotFoundException;

public class Duke {
    private static final String FILE_PATH = "./data/duke.txt";
    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            storage.ensureDataFileExists();
            tasks = storage.loadTasksFromFile();
        } catch (DukeDataCorruptedException e) {
            ui.showDukeDataCorruptionMessage(e);
        } catch (FileNotFoundException e) {
            System.out.println("Data file not found. Creating a new one.");
            // Handle the case when the data file is not found, and create a new one
            ui.showFileNotFoundExceptionMessage();
        } catch (IOException e) {
            // Handle IOException (e.g., file-related issues)
            ui.showIOExceptionMessage();
        } catch (NumberFormatException e) {
            // Handle NumberFormatException (e.g., when parsing integers)
            ui.showNumberFormatExceptionMessage();
        }
    }

    public void run() {
        ui.showWelcomeMessage();

        while (true) {
            try {
                String userInput = ui.getUserInput();
                if (Parser.isExitCommand(userInput)) {
                    ui.showGoodbyeMessage();
                    storage.saveTasksToFile(tasks);
                    break;
                }
                if (userInput.trim().isEmpty()) {
                    throw new DukeException("Please enter an action and a task");
                }
                //System.out.println((tasks.getTask(0)).isDone());
                Command command = Parser.parseCommand(userInput);
                command.execute(tasks, ui, storage);
                storage.saveTasksToFile(tasks);
            } catch (DukeException e) {
               ui.showDukeExceptionMessage(e);
            tasks = new TaskList();
            } catch (NumberFormatException e) {
                // Handle NumberFormatException (e.g., when parsing integers)
                ui.showNumberFormatExceptionMessage();
            } catch (IOException e) {
                ui.showIOExceptionMessage();
            }
        }
    }

    public static void main(String[] args) {
        new Duke(FILE_PATH).run();
    }
}

class DukeDataCorruptedException extends Exception {
    public DukeDataCorruptedException(String message) {
        super(message);
    }
}

class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
}


