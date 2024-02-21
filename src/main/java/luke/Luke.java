package luke;

import java.io.IOException;

import luke.exception.SaveFileCorruptedException;

/**
 * The entry point of the app.
 */
public class Luke /* extends Application */ {
    private Ui ui = new Ui();
    private Parser parser = new Parser();
    private String fileName = "./data/duke.txt";
    private Storage storage = new Storage(fileName);
    private List taskList = new List(storage.loadTasks());

    public Luke() throws IOException, SaveFileCorruptedException {

    }

    /**
     * Responds to the user input.
     * @param input The user input.
     * @return String to be displayed to the user.
     * @throws IOException As it writes to a save file in Storage class.
     */
    String getResponse(String input) throws IOException {
        assert input != null;
        return parser.parse(input, taskList, ui, storage);
    }
}
