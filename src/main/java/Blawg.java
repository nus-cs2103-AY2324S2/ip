package blawg;

import commands.Command;
import exceptions.BlawgException;
import parser.Parser;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

/**
 * <p>
 *  Represents a chatBox Ai. A <code>Blawg</code> object allows a User to
 *  * relay instructions and have the instructions be executed. The commands
 *  * that Blawg supports are list, deadline, event, todo, delete, bye, mark, unmark.
 * </p>
 */
public class Blawg {
    private TaskList tasks;
    private Storage storage;
    private Ui ui;

    /**
     * initialises a Blawg class
     */
    public Blawg() {
        ui = new Ui();
        storage = new Storage("data/tasks.txt");
        try {
            tasks = new TaskList(storage.read());
        } catch (BlawgException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * initialises a Blawg class
     * @param filepath where the data storage file is read and written to
     */
    public Blawg(String filepath) {
        ui = new Ui();
        storage = new Storage(filepath);
        try {
            tasks = new TaskList(storage.read());
        } catch (BlawgException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }
    /**
     * This method runs the chatbot Blawg, allowing it to start receiving input
     * and outputting results to users.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.getUserInput();
                ui.showLine();
                Command c = new Parser().parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (Exception e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Blawg("src/main/java/data/tasks.txt").run();
    }

    public String getResponse(String input) {
        assert !input.trim().isEmpty() : "input should not be empty";
        try {
            Command c = new Parser().parse(input);
            String result = c.execute(tasks, ui, storage);
            return result;
        } catch (Exception e) {
            ui.showError(e.getMessage());
        }
        return "";
    }
}
