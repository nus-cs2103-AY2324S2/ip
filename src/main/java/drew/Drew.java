package drew;

import java.io.FileNotFoundException;
import java.time.format.DateTimeParseException;

import drew.command.Command;
import drew.storage.Storage;
import drew.storage.TaskList;
import drew.ui.Parser;
import drew.ui.Ui;

/**
 * Main chatbot class. Contains the logic of the chatbot.
 *
 * @author cocoanautz
 */
public class Drew {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Starting point of the chatbot.
     * @param filePath String that contains the save file's path, relative to the project root directory.
     */
    public Drew(String filePath) {
        storage = new Storage(filePath);
        ui = new Ui();
        parser = new Parser();
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            System.out.println("Load status: File not found");
            tasks = new TaskList();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            tasks = new TaskList();
        } catch (DateTimeParseException e) {
            System.out.println("Date corrupted");
            tasks = new TaskList();
        }
    }

    /**
     * Calls the chatbot to execute the input given.
     * @param input String containing user input.
     * @return Response from executing the command.
     */
    public String getResponse(String input) {
        Command command = parser.checkCommandId(input);
        String reply;
        try {
            reply = command.execute(tasks, storage);
        } catch (IllegalArgumentException e) {
            reply = e.getMessage();
        }

        return reply;
    }
}
