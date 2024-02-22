package command;

import duke.Storage;
import duke.Deadline;
import duke.DukeException;
import duke.Task;
import duke.Todo;
import duke.Event;
import duke.TaskList;


/**
 * Represents a command that adds a new task to the tasklist and inserts it in the storage file
 */
public class AddTaskCommand extends Command {
    private final String prompt;

    /**
     * Constructs a new command that creates a task.
     *
     * @param prompt Adjusted by the Parser
     */
    public AddTaskCommand(String prompt) {
        this.prompt = prompt;
    }

    private Task createTask() throws DukeException {
        String[] order = prompt.split(" ", 2);
        String taskType = order[0];
        switch (taskType) {
        case "todo":
            if (order.length <= 1) {
                throw new DukeException("Sorry. It seems this todo task has no content at all!");
            }
            return new Todo(order[1]);
        case "deadline":
            if (order.length <= 1) {
                throw new DukeException("Sorry. It seems this deadline task has no content at all!");
            }
            String[] deadline = order[1].split(" /by ", 2);
            if (deadline.length <= 1) {
                throw new DukeException("Sorry. The description of the deadline task is of incompatible format!");
            }
            return new Deadline(deadline[0], deadline[1]);
        case "event":
            if (order.length <= 1) {
                throw new DukeException("Sorry. It seems this event has no content at all!");
            }
            String[] start = order[1].split(" /from ", 2);
            if (start.length <= 1) {
                throw new DukeException("Sorry. The starting time of this event is not described in correct format");
            }
            String[] end = start[1].split(" /to ", 2);
            if (end.length <= 1) {
                throw new DukeException("Sorry. The ending time of this event is not described in correct format");
            }
            return new Event(start[0], end[0], end[1]);
        default:
            throw new DukeException("Sorry. I don't see what you mean by that...");
        }
    }

    /**
     * Judges if 2 commands are adding the same task.
     *
     * @param other Another command
     * @return True if 2 are of the same prompt
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof AddTaskCommand) {
            return ((AddTaskCommand) other).prompt.equals(prompt);
        }
        return false;
    }

    /**
     * Adds a new task to the taskList and write it into the file.
     *
     * @param storage Involved in file management
     * @param taskList Active during the execution of the program
     * @return A string that tells that the task-adding is successful
     * @throws DukeException
     */
    @Override
    public String execute(Storage storage, TaskList taskList) throws DukeException {
        Task task = createTask();
        storage.addTask(task);
        return taskList.add(task);
    }
}
