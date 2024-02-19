package drew;

import drew.command.Command;
import drew.storage.TaskList;
import drew.storage.Storage;
import drew.ui.Parser;
import drew.ui.Ui;

import java.io.FileNotFoundException;
import java.time.format.DateTimeParseException;

/**
 * Main chatbot class. Contains the logic of the chatbot.
 *
 * @author cocoanautz
 */
public class Drew {
    Storage storage;
    TaskList tasks;
    Ui ui;
    Parser parser;

    public Drew(String filePath) {
        storage = new Storage(filePath);
        ui = new Ui();
        parser = new Parser();
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e){
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
