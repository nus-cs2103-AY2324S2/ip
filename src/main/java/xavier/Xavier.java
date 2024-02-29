package xavier;
import java.util.NoSuchElementException;

import command.Command;
import common.DukeException;
import common.Parser;
import common.Storage;
import common.Ui;
import task.TaskList;

/**
 * The Duke program implements a chatbot, now named NextGenerationJarvis, that keeps track of tasks for the user.
 */
public class Xavier {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private boolean isExit;

    /**
     * Returns an instance of the program and loads the tasks from the file found at the provided filepath.
     *
     * @param filepath The filepath of the file to load/save the tasks.
     */
    public Xavier(String filepath) {
        ui = new Ui();
        storage = new Storage(filepath);
        tasks = new TaskList(storage.loadData());

    }

    public String showWelcome() {
        return ui.showWelcome();
    }

    /**
     * Returns the response from the program as a String when given the input provided.
     *
     * @param input The input for the program to parse and execute.
     * @return The response from the program.
     */
    public String getResponse(String input) {
        try {
            Command cmd = new Parser(input, tasks).parse();
            isExit = cmd.isExit();
            return cmd.execute();

        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid task number. :(");
            return "Invalid task number. :(";

        } catch (NumberFormatException e) {
            System.out.println("Input is not an integer. :(");
            return "Input is not an integer. :(";

        } catch (NoSuchElementException e) {
            System.out.println("Missing task number. :(");
            return "Missing task number. :(";

        } catch (DukeException e) {
            System.out.println(e.getMessage());
            return e.getMessage();

        } finally {
            Ui.showLine();
        }
    }

    public boolean getIsExit() {
        return isExit;
    }
}
