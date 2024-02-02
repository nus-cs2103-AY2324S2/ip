package duke;

//import java.io.IOException;
//import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showSeparator();
        ui.showWelcome();
        Scanner scanner = new Scanner(System.in);
        String input;
        ui.showSeparator();

        do {
            input = scanner.nextLine();
            processCommand(input);
        } while (!input.equalsIgnoreCase("bye"));

        scanner.close();
    }

    private void processCommand(String input) {
        try {
            Parser parser = new Parser(input);
            Command command = parser.parse();
            command.execute(tasks, ui, storage);
        } catch (DukeException e) {
            ui.showErrorMessage(e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
