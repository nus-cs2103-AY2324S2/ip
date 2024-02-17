package duke.mainUtils;
import duke.exceptions.StorageException;
import duke.fileUtils.*;

/**
 * The Ui class handles user interaction and display functionalities for the Duke application.
 * It provides methods to display start-up messages, graphical elements, and error messages,
 * as well as to parse and store user commands.
 *
 * @author Justin Leng Chern Harn
 * @version 1.0
 * @see duke.fileUtils.FileUtil
 * @see duke.exceptions.StorageException
 * @see duke.fileUtils.FilePaths
 */
public class Ui {
    private String[] userInput;

    /**
     * Displays the start-up message for the Duke application.
     * This method displays the application's logo and a greeting message.
     *
     * @throws StorageException if there is an error loading graphical resources.
     */
    public final void displayStart() throws StorageException {
        displayLine();
        displayLogo();
        System.out.println("      Hello! I'm RahhBot. RAHHHH!!\n");
        System.out.println("      What can I do for you today?\n");
        displayLine();
    }

    /**
     * Displays a horizontal line graphic.
     * This method reads and displays the horizontal line graphic from a file.
     *
     * @throws StorageException if there is an error loading graphical resources.
     */
    public final void displayLine() throws StorageException {
        FileUtil.displayFile(FilePaths.HORIZONTAL_LINE_PATH);
    }

    /**
     * Displays the Duke application's logo.
     * This method reads and displays the logo graphic from a file.
     *
     * @throws StorageException if there is an error loading graphical resources.
     */
    public final void displayLogo() throws StorageException {
        FileUtil.displayFile(FilePaths.LOGO_PATH);
    }

    /**
     * Displays an error message along with an error graphic.
     *
     * @param errorMessage the error message to display.
     * @throws StorageException if there is an error loading graphical resources.
     */
    public final void displayErrorGraphic(String errorMessage) throws StorageException {
        FileUtil.displayFile(FilePaths.ERROR_GRAPHIC_PATH);
        System.out.println(errorMessage);
    }

    /**
     * Stores the user input as an array of strings.
     *
     * @param userInput the user input string to store.
     */
    public void storeCommand(String userInput) {
        this.userInput = userInput.trim().split("\\s+");
    }

    /**
     * Retrieves the user command stored in the Ui object.
     *
     * @return an array of strings representing the user command.
     */
    public String[] getCommand() {
        return this.userInput;
    }

}
