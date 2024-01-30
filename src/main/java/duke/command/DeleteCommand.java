package duke.command;

import duke.state.ProgramState;
import duke.DukeException;
import duke.task.Task;
import duke.task.TaskList;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(String body) throws DukeException {
        super(body);
        try {
            this.index = Integer.parseInt(body);
        } catch (NumberFormatException e) {
            if (body == null || body.equals("")) {
                throw new EmptyTaskDescriptionException("The index of a task cannot be empty.",
                        "Sorry, but I don't understand what you mean by task number \"" + body + "\".");
            }
            throw new InvalidTaskIndexException("The index of a task must be an integer.",
                    "Sorry, but I don't understand what you mean by task number \"" + body + "\".");
        }
    }

    public String execute(TaskList list, ProgramState state) throws DukeException {
        if (index < 1 || index > list.size()) {
            throw new InvalidTaskIndexException(
                    "The index of a task cannot be less than 1 or greater than the number of tasks.",
                    "Sorry, but task number " + index + " does not exist. You only have " + list.size() + " tasks.");
        }
        Task task = list.deleteTask(index);
        String response = ("Deleted: " + task + "\nYou now have " + list.size() + " tasks in the list.");
        state.setNormal();
        return response;
    }
}
