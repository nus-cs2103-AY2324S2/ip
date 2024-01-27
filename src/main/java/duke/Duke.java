package duke;

import java.util.Scanner;

public class Duke {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError("Error loading tasks: " + e.getMessage());
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcomeMessage();
        Scanner scanner = new Scanner(System.in);

        boolean isExit = false;
        while (!isExit) {
            String command = scanner.nextLine().trim();

            try {
                Parser.parseAndExecute(command, tasks, ui, storage);
                isExit = command.equals("bye");
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }

        scanner.close();
    }

    public static void main(String[] args) {
        new Duke("./duke.txt").run();
    }
}
