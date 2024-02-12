import java.io.IOException;
import java.util.Scanner;

import exception.ChronosException;
import tool.Parser;
import tool.Storage;
import tool.TaskList;
import tool.Ui;

/**
 * Represents the main class of the Chronos Task Management System.
 */
public class Chronos {
    private static final String FILE_PATH = "./data/chronos.txt";
    private static Ui ui;
    private static Storage storage;
    private static TaskList tasks;

    /**
     * Constructs a Chronos object with the given file path.
     *
     * @param filePath File path of the saved tasks.
     */
    public Chronos(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.loadTasksFromFile());
        } catch (Exception e) {
            tasks = new TaskList();
        }
    }

    /**
     * Initialises program and processes user input.
     *
     * @throws IOException If there is an exception when processing input/output.
     * @throws ChronosException If there are invalid commands provided.
     */
    public void runProgram() throws IOException, ChronosException {
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
     * Represents the main function to run the Chronos Task Management System.
     *
     * @throws IOException If there is an exception when processing input/output.
     * @throws ChronosException If there are invalid commands provided.
     */
    public static void main(String[] args) throws IOException, ChronosException {
        new Chronos(FILE_PATH).runProgram();
    }
}