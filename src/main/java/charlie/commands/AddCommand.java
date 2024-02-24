package charlie.commands;

import java.util.ArrayList;

import charlie.exceptions.CharlieException;
import charlie.models.Deadline;
import charlie.models.Event;
import charlie.models.Task;
import charlie.models.Todo;
import charlie.storage.Storage;
import charlie.storage.TaskList;

public class AddCommand extends Command {

    private String fullCommand;
    private String response;

    /**
     * constructor for AddCommand
     * @param fullCommand the user command in its full string form
     */
    public AddCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * executes an add command, the method decides between tasks to-do, event and deadline, and
     * saves the tasks to storage
     * @param taskList - task list loaded at the start of the program.
     * @param storage  - class responsible for adding and loading tasks from and into the file
     * @throws CharlieException
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws CharlieException {
        if (fullCommand.startsWith("todo")) {
            this.response = handleTodo(taskList.getTasks(), fullCommand);
        } else if (fullCommand.startsWith("event")) {
            this.response = handleEvent(taskList.getTasks(), fullCommand);
        } else if (fullCommand.startsWith("deadline")) {
            this.response = handleDeadline(taskList.getTasks(), fullCommand);
        }
        storage.saveTasks(taskList.getTasks());
        isExit = false;
        return this.response;
    }

    /**
     * adds the to-do task to the current task list, and returns intended generated output
     * @param taskList task list loaded at the start of the program.
     * @param input to-do string which specifies the task saved
     * @throws CharlieException
     */
    private String handleTodo(ArrayList<Task> taskList, String input) throws CharlieException {
        if (input.trim().equals("todo")) {
            throw new CharlieException("Sorry, the description of a todo cannot be empty.");
        }
        String description = input.substring(5);
        Todo todo = new Todo(description);
        taskList.add(todo);
        String responseTodo =  "Got it. I've added this task:\n  "
                + todo + "Now you have " + taskList.size() + " tasks in the list.";
        return responseTodo;
    }

    /**
     * adds the deadline task to the current task list, and returns intended generated output
     * @param taskList task list loaded at the start of the program.
     * @param input to-do string which specifies the task saved
     * @throws CharlieException
     */
    private String handleDeadline(ArrayList<Task> taskList, String input) throws CharlieException {
        String[] parts = input.substring(9).split(" /by ");
        if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
            throw new CharlieException("Sorry, the description of a deadline "
                    + "cannot be empty and must include a /by time.");
        }
        Deadline deadline = new Deadline(parts[0], parts[1]);
        taskList.add(deadline);
        String responseDeadline = "Got it. I've added this task:\n  " + deadline
                + "Now you have " + taskList.size() + " tasks in the list.";
        return responseDeadline;
    }

    /**
     * adds the event task to the current task list, and returns intended generated output
     * @param taskList task list loaded at the start of the program.
     * @param input to-do string which specifies the task saved
     * @throws CharlieException
     */
    private String handleEvent(ArrayList<Task> taskList, String input) throws CharlieException {
        String[] parts = input.substring(6).split(" /from ");
        if (parts.length < 2 || parts[0].trim().isEmpty() || !parts[1].contains(" /to ")) {
            throw new CharlieException("Sorry, the description of an event cannot be empty "
                    + "and must include start /from and /to end times.");
        }
        String[] timeParts = parts[1].split(" /to ");
        Event event = new Event(parts[0], timeParts[0], timeParts[1]);
        taskList.add(event);
        String responseEvent = "Got it. I've added this task:\n  "
                + event + "Now you have " + taskList.size() + " tasks in the list.";
        return responseEvent;
    }

}
