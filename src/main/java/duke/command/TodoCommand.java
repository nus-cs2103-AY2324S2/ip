package duke.command;

import duke.DukeException;
import duke.state.ProgramState;

import duke.task.TaskList;
import duke.task.Todo;
import duke.task.Task;

public class TodoCommand extends Command {
    public TodoCommand(String body) {
        super(body);
    }

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
