package duke.command;

import duke.DukeException;
import duke.state.ProgramState;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

/**
 * Represents a command to add a simple todo.
 */
public class TodoCommand extends Command {
    public TodoCommand(String body) {
        super(body);
    }

    /**
     * Executes the command. This command adds a todo to the task list and sets the
     * program state to normal after the command is executed, even if the command
     * fails to execute.
     *
     * @param list  The task list to be modified.
     * @param state The program state to be modified.
     * @return The response to be displayed to the user.
     * @throws DukeException If the body of the command is empty, or if the command
     *                       fails to execute.
     */
    public String execute(TaskList list, ProgramState state) throws DukeException {
        String body = getBody();
        if (body.isEmpty()) {
            throw new DukeException("The description of a todo cannot be empty.",
                    "Sorry, you need to tell me what you want to add. I can't add empty tasks.");
        }
        Task task = new Todo(body);
        list.addTask(task);
        String response = ("Added: " + task + "\nYou now have " + list.size() + " tasks in the list.");
        state.setNormal();
        return response;
    }
}
