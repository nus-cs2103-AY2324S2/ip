import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private Ui ui;
    private Storage storage;
    public enum Command {
        BYE, LIST, MARK, DELETE, TODO, DEADLINE, EVENT, UNKNOWN
    }

    private TaskList tasks;
    private static final String FILE_PATH = "./data/duke.txt";
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcomeMessage();

        Scanner sc = new Scanner(System.in);
        boolean isExit = false;

        while (!isExit) {
            try {

                String userInput = ui.getUserInput();
                if (userInput.equals("Bye")) {
                    isExit = true;
                }
                Parser.parseAndExecute(userInput, tasks, ui, storage);
                storage.saveTasks(tasks.getTasks());
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }

        ui.showGoodbyeMessage();
    }

    public static void main(String[] args) {

        new Duke("data/duke.txt").run();
    }
}
