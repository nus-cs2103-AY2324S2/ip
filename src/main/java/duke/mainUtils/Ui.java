package duke.mainUtils;
import duke.exceptions.StorageException;
import duke.fileUtils.*;

public class Ui {
    private String[] userInput;
    public final void displayStart() throws StorageException {
        displayLine();
        displayLogo();
        System.out.println("      Hello! I'm RahhBot. RAHHHH!!\n");
        System.out.println("      What can I do for you today?\n");
        displayLine();
    }

    public final void displayLine() throws StorageException {
        FileUtil.displayFile(FilePaths.HORIZONTAL_LINE_PATH);
    }

    public final void displayLogo() throws StorageException {
        FileUtil.displayFile(FilePaths.LOGO_PATH);
    }

    public final void displayErrorGraphic(String errorMessage) throws StorageException {
        FileUtil.displayFile(FilePaths.ERROR_GRAPHIC_PATH);
        System.out.println(errorMessage);
    }

    public void storeCommand(String userInput) {
        this.userInput = userInput.trim().split("\\s+");
    }

    public String[] getCommand() {
        return this.userInput;
    }

}
