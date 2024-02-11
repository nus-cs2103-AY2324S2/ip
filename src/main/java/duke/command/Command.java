package duke.command;

import duke.Storage;
import duke.exceptions.IllegalParamException;
import duke.task.TaskList;


/**
 * Commands are a family of classes that represent commands from the user.
 * They have a defining execute method.
 */
public abstract class Command {
    private String response = "";
    /**
     * Executes actions to complete the duke.command.
     * Also updates response field, showing result of executing command.
     *
     * @param list a duke.command.task.TaskList object containing current tasks.
     * @param storage duke.Storage object. For saving changes to memory.
     */
    public abstract void execute(TaskList list, Storage storage) throws IllegalParamException;

    /**
     * Returns a response as a result of executing command.
     *
     * @return Response as a result of executing command.
     */
    public String getResponse() {
        if (this.response.equals("")) {
            throw new RuntimeException("Response not available yet! Something went wrong.");
        }
        return this.response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
