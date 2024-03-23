package Tim.gui;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import Tim.exception.TimException;
import Tim.parser.TimParser;
import Tim.storage.FileHandler;
import Tim.storage.TaskList;
import javafx.fxml.FXML;


public class Tim {

    private final Path writeToFile;
    TaskList taskList = new TaskList();

    public Tim() throws IOException {
        Path filePath = Paths.get("./data/duke.txt");
        writeToFile = FileHandler.handleFile(taskList, filePath);

    }

    /**
     * Generate a response to user input.
     *
     * @param input
     * @return String response based on user input
     * @throws IOException
     * @throws TimException
     */
    @FXML
    String getResponse(String input)  {
        try {
            return new TimParser().parseCommand(input, writeToFile).execute(taskList);
        } catch (TimException e) {
            return e.getMessage();
        }
    }
}
