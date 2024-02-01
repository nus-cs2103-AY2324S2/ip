package duke;

import duke.command.Command;

import java.io.IOException;
import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.load());
        } catch (IOException ie) {
            System.exit(0);
        } catch (JamieException e) {
            tasks = new TaskList();
        }
    }


    public void run() throws IOException{
        ui.showWelcome();
        Scanner scanner = new Scanner(System.in);
        boolean isExit = false;
        while (!isExit) {
            String userInput = scanner.nextLine();
            try {
                Command command = Parser.parse(userInput);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (JamieException e) {
                ui.showError(e.getMessage());
            }
        }
        ui.showExitMessage();
        scanner.close();
    }

    public static void main(String[] args) throws IOException {
        new Duke("data/Jamie.txt").run();
    }
}
