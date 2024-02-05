import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Iterator;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    public void run() throws IOException {
        ui.showWelcome();
        Command command;
        do {
            String input = ui.readInput();
            command = new Parser().parse(input);
            command.execute(tasks, storage);
        } while (!ExitCommand.isExit(command));
        exit();
    }

    public void exit() {
        ui.showGoodbyeMessage();
        System.exit(0);
    }
    public static void main(String[] args) throws IOException {
        new Duke("data/duke.txt").run();
    }
}

