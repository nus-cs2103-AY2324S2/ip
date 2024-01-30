package duke.command;

import duke.state.ProgramState;
import duke.task.Deadline;
import duke.DukeException;
import duke.task.Task;
import duke.task.TaskList;

public class DeadlineCommand extends Command {
    private String taskDescription;
    private String dueTime;

    public DeadlineCommand(String body) {
        super(body);
        String[] parts = body.split("/by", 2);
        this.taskDescription = parts[0].trim();
        this.dueTime = parts.length > 1 ? parts[1].trim() : "";
    }

    public String execute(TaskList list, ProgramState state) throws DukeException {
        if (taskDescription.isEmpty()) {
            throw new EmptyTaskDescriptionException("The description of a deadline cannot be empty.",
                    "The description of a deadline cannot be empty.");
        } else if (dueTime.isEmpty()) {
            throw new EmptyDateTimeException("The due time of a deadline cannot be empty.",
                    "The due time of a deadline cannot be empty.");
        }
        Task task = new Deadline(taskDescription, dueTime);
        list.addTask(task);
        String response = ("Added: " + task + "\nYou now have " + list.size() + " tasks in the list.");
        state.setNormal();
        return response;
    }
}
