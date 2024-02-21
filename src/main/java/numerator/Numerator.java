package numerator;

import java.nio.file.Path;

import numerator.exceptions.NumeratorException;
import numerator.exceptions.storage.LoadingException;
import numerator.task.TaskList;

/**
 * Represents the main class of the Numerator program.
 */
public class Numerator {
    private final Storage storage;
    private TaskList taskList;

    /**
     * Constructs a Numerator object with the default file path "data/storage.txt".
     */
    public Numerator() {
        this("data/storage.txt");
    }

    /**
     * Constructs a Numerator object with the specified file path.
     *
     * @param filePath the path to the file to be loaded and saved.
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
     * Gets the response from the Numerator program in response to a user's input
     * This method can be used to link the UI to the Numerator program.
     *
     * @param response the input from the user.
     * @return the response to the user.
     */
    public String getResponseFromInput(String response) {
        try {
            return Parser.parseArguments(response, taskList, storage);
        } catch (NumeratorException e) {
            return e.getMessage();
        }
    }

}

