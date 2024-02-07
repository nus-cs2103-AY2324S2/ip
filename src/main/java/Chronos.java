import java.util.Scanner;
import java.io.IOException;

import ui.Ui;
import storage.Storage;
import parser.Parser;
import task.TaskList;
import exception.ChronosException;

/**
 * Represents the main class of the Chronos Task Management System.
 */
public class Chronos {
    private static final String FILE_PATH = "./data/chronos.txt";
    private static TaskList tasks;
    private static Ui ui;
    private static Storage storage;

    public Chronos(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList();
    }

    public void run() throws IOException, ChronosException {
        ui.greetUser();

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String fullCommand = ui.readCommand(sc);
            int statusCode = Parser.processCommand(fullCommand, ui, storage, tasks);
            if (statusCode == 0) {
                break;
            }
        }
        sc.close();
    }

    /**
     * Initialises text file to store task list and processes user commands.
     *
     * @throws @IOException If directory or file is not found.
     */
    public static void main(String[] args) throws IOException, ChronosException {
        new Chronos(FILE_PATH).run();
    }
}