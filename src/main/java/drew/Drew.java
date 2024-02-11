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
     * Starts the chatbot.
     */
    public void run() {
        ui.greetUser();
        boolean isExit = false;

        while (!isExit) {
            String userInput = ui.readInput();
            Command command = parser.checkCommandIdentity(userInput);
            isExit = command == Command.BYE;
            String reply = parser.executeCommand(tasks, command, userInput);
            ui.reply(reply);
        }

        storage.save(tasks);
        ui.bidFarewell();
    }
    public static void main(String[] args) {
        Drew drew = new Drew("save/tasks.txt");
        drew.run();
    }
}
