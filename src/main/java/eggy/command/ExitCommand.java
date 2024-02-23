package eggy.command;

import eggy.response.Response;
import eggy.storage.Storage;
import eggy.task.TaskList;

/**
 * Represents a command to exit the chatbot.
 */
public class ExitCommand extends Command {
    /**
     * Prints a goodbye message.
     *
     * @param tasks The task list of the chatbot.
     * @param response The response of the chatbot.
     * @param storage The storage of the chatbot.
     */
    @Override
    public void execute(TaskList tasks, Response response, Storage storage) {
        response.setGoodbyeResponse();
    }

    /**
     * Returns whether the command is an exit command.
     *
     * @return Whether the command is an exit command.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
