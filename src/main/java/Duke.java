import java.io.IOException;

import java.time.format.DateTimeParseException;

import java.util.List;

/**
 * Duke Class.
 * This is the main class for the chatbot.
 */
public class Duke {

    public static void main(String[] args) {
        String fileName = "./data/history.txt";
        Storage storage = new Storage(fileName);
        List<Task> lst = storage.getInputFromFile();
        Ui ui = new Ui();

        while (true) {
            try {
                String[] subStr = ui.validateInput();
                Parser.parse(subStr, lst, storage);
            } catch (DukeException | IOException | DateTimeParseException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}