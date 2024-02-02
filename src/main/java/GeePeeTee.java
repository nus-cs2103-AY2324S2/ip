
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import exception.GeePeeTeeException;
import parser.Parser;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

/**
 * Represents the main class of the GeePeeTee application.
 * <p>
 * This class is responsible for initializing the application and running the
 * main
 * loop of the application, which processes user input and executes the
 * corresponding commands.
 * </p>
 */
public class GeePeeTee {

    private TaskList taskList;
    private Storage storage;
    private Ui ui;

    /**
   * Constructs a new {@code GeePeeTee} instance with the specified file path.
   *
   * @param filePath The file path to be associated with the application.
   */
  public GeePeeTee(String filePath) {
        ui = new Ui();
        try {
            storage = new Storage("GeePeeTee.txt");
            taskList = new TaskList(storage.loadTaskList());
        } catch (FileNotFoundException e) {
            ui.showFileNotFoundError();
        } catch (IOException e) {
            ui.showLoadingError();
        } catch (GeePeeTeeException e) {
            ui.showErrorMessage(e.getMessage());
        }
    }

    /**
   * Runs the main loop of the application, processing user input and executing
   * the corresponding commands.
   */
  public void run() {
        String input = "";
        ui.showWelcomeMessage();
        Scanner scanner = new Scanner(System.in);
        Parser parser = new Parser(taskList, storage, ui);
        while (!input.equals("bye")) {
            input = scanner.nextLine();
            System.out.println("\n--------------------------------------------------");
            if (input.equals("bye")) {
                scanner.close();
                ui.showGoodbyeMessage();
            }
            parser.parseInput(input);

            System.out.println("--------------------------------------------------\n");
        }
    }
}