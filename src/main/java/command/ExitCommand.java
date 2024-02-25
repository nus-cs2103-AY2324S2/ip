package command;

import emis.TaskList;
import emis.Storage;

import javafx.application.Platform;

/**
 * The ExitCommand class represents a command to exit the EMIS application.
 * When executed, it terminates the application.
 */
public class ExitCommand extends Command {

    /**
     * Constructs a new ExitCommand object.
     */
    public ExitCommand() {
    }

    /**
     * Executes the exit command by terminating the application.
     *
     * @param tasklist The TaskList object representing the list of tasks (not used in this command).
     * @param storage The Storage object handling loading and saving of tasks (not used in this command).
     * @return A farewell message to the user.
     */
    @Override
    public String execute(TaskList tasklist, Storage storage) {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(1500);
                Platform.runLater(() -> Platform.exit());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        
        return "Bye. Hope to see you again soon!";
    }
}
