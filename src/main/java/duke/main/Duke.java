package duke.main;
import duke.command.Command;
import duke.exception.DukeException;
/**
 * This class represents a chat application.
 */

//modify to fit coding standard
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    public static void main(String[] args) {
        Duke duke = new Duke("data/duke.txt");
        duke.startChat();
    }

    /**
     * Constructs a task list of size.
     */
    private Duke(String filePath) {
        storage = new Storage(filePath);
        ui = new Ui();
        try {
            tasks = new TaskList(storage.loadList());
        }
        catch (DukeException e) {
            ui.showLoadingError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Initiates the chat by invoking the sayHi() method.
     * Handles user input to display the list for "list" input, exit the chat for "bye" input,
     * marks or unmarks tasks as done, deletes tasks, or append to the list for to-do/deadline/event input,
     * separates responses based on the type of task.
     */
    private void startChat() {
        ui.sayHi();
        boolean exited = false;
        while (!exited) {
            String fullCommand = ui.readCommand();
            try {
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                exited = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }


}