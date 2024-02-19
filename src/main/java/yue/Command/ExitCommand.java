package yue.Command;

import yue.Storage;
import yue.Tasks.TaskList;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Represents a command to exit the application.
 */
public class ExitCommand extends Command {

    /**
     * Executes the ExitCommand, showing a goodbye message.
     *
     * @param tasks   The list of tasks (not used).
     * @param storage The storage handler (not used).
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        String goodbyeMessage = "Bye. Hope to see you again soon!\n";
        Timer timer = new Timer();
        System.out.println("Bye. Hope to see you again soon!");

        // Schedule a task to exit after 3 seconds
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.exit(0); // Exit the program
            }
        }, 1000);

        return goodbyeMessage;

    }



    /**
     * Checks if the command is an exit command.
     *
     * @return Always returns true, as this is an exit command.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
