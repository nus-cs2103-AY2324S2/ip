import commands.Command;
import excceptions.WeiException;
import parser.Parser;
import storage.Storage;
import taskList.TaskList;
import ui.Ui;

import java.io.IOException;
import java.util.Scanner;

/**
 * Represents a chatbot that can keep track of your tasks.
 */
public class Wei {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    private Parser parser;

    private Wei(String filePath) {
        ui = new Ui();
        parser = new Parser();
        try {
            storage = new Storage(filePath);
            tasks = storage.read();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void run() {
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                Scanner scanner = new Scanner(System.in);
                String input = scanner.nextLine();
                Command userCommand = parser.parse(input);
                userCommand.execute(tasks, ui);
                isExit = userCommand.isExit();
            } catch (WeiException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
        try {
            storage.save(tasks);
        } catch (IOException e) {
            ui.showError(e.getMessage());
        }
        ui.exit();
    }

    /**
     * Creates and runs the chatbot.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        new Wei("./data/history.txt").run();
    }
}
