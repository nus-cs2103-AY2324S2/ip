package yue.command;

import yue.Storage;
import yue.tasks.TaskList;

public class GreetCommand extends Command{
    /**
     * Executes the ExitCommand, showing a greeting message.
     *
     * @param tasks   The list of tasks (not used).
     * @param storage The storage handler (not used).
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        String greetMessage = "Hello!\n" + "How can I help you?\n";

        return greetMessage;
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


