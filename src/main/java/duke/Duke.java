package duke;

import command.Command;
import exception.DukeException;
import exception.EmptyInputException;
import exception.EmptyTimeException;
import exception.InvalidDateTimeException;
import exception.InvalidFormatException;
import exception.InvalidInputException;

/**
 * Duke is the chatbot program.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * The constructor of Duke.
     *
     * @param filePath The file path to be passed into to load the initial tasks.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(storage.load());
        this.parser = new Parser(this.tasks, this.ui, this.storage);
    }

    /**
     * Run the program
     *
     * @param input The input of the user.
     * @return The response of bee.
     */
    public String getResponse(String input) {
        String response;
        ui.readCommand(input);
        Command c = parser.parse(input);
        try {
            response = c.execute(tasks, ui, storage);
        } catch (EmptyInputException e) {
            response = e.getMessage();
        } catch (EmptyTimeException e) {
            response = e.getMessage();
        } catch (InvalidFormatException e) {
            response = e.getMessage();
        } catch (InvalidDateTimeException e) {
            response = e.getMessage();
        } catch (InvalidInputException e) {
            response = e.getMessage();
        } catch (DukeException e) {
            response = e.getMessage();
        } finally {
            // Write tasks to storage regardless of whether an exception occurred
            storage.writeTasks(tasks);
        }

        return response;
    }
}
