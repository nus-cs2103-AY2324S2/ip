package Duke;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import javafx.fxml.FXML;


public class Tim {

    private final Path writeToFile;
    TaskList storage = new TaskList();

    public Tim() throws IOException {
        Path filePath = Paths.get("./data/duke.txt");
        writeToFile = FileHandler.handleFile(storage, filePath);

    }

    /**
     * Main function of the Duke program. Displays opening logo. Scans for user input.
     *
     * @param args
     * @throws DukeException
     * @throws IOException
     */
    public static void main(String[] args) throws DukeException, IOException {

        UI.showLogo();
        UI.showAvailCommands();
        TaskList storage = new TaskList();
        Path filePath = Paths.get("./data/duke.txt");
        Path writeToFile = FileHandler.handleFile(storage, filePath);
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        while (!input.isEmpty()) {
            if (input.equals("bye")) {
                UI.showExitMsg();
                break;
            }
            TaskHandler.doTasks(input, storage, writeToFile);

            input = scan.nextLine();

        }

    }

    /**
     * Generate a response to user input.
     *
     * @param input
     * @return String response based on user input
     * @throws IOException
     * @throws DukeException
     */
    @FXML
    String getResponse(String input) throws IOException, DukeException {
        return TaskHandler.doTasks(input, storage, writeToFile);

    }
}
