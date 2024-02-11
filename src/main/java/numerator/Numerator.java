package numerator;

import java.nio.file.Path;
import java.util.Scanner;

import numerator.exceptions.NumeratorException;
import numerator.exceptions.storage.LoadingException;
import numerator.task.TaskList;

/**
 * Represents the main class of the Numerator program
 */
public class Numerator {
    private final Storage storage;
    private TaskList taskList;

    /**
     * Constructs a Numerator object with the default file path "data/storage.txt"
     */
    public Numerator() {
        this("data/storage.txt");
    }

    /**
     * Constructs a Numerator object with the specified file path
     *
     * @param filePath the path to the file to be loaded and saved
     */
    public Numerator(String filePath) {
        Path path = Path.of(filePath);
        storage = new Storage(path);

        try {
            taskList = storage.loadFile();
        } catch (LoadingException e) {
            Ui.printLoadingError(e);
            taskList = new TaskList();
        }
    }

    /**
     * Instantiates The entry point of the Numerator program
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Numerator().run();
    }

    /**
     * Gets the response from the Numerator program in response to a user's input
     * This method can be used to link the UI to the Numerator program.
     *
     * @param response the input from the user
     * @return the response to the user
     */
    public String getResponseFromInput(String response) {
        try {
            return Parser.parseArguments(response, taskList, storage);
        } catch (NumeratorException e) {
            return e.getMessage();
        }
    }

    /**
     * Runs the Numerator program
     */
    public void run() {
        Ui.printLogo();
        Scanner sc = new Scanner(System.in);

        String input;
        while (sc.hasNext()) {
            try {
                input = sc.nextLine();
                Ui.printLine();
                String response = Parser.parseArguments(input, taskList, storage);
                storage.saveFile(taskList);

                if (response.equals(Parser.BYE_STRING)) {
                    Ui.printExit();
                    break;
                }
            } catch (NumeratorException e) {
                Ui.printError(e);
            } finally {
                Ui.printLine();
            }
        }

        sc.close();

    }
}

