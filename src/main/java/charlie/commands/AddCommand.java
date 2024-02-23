package charlie.commands;

import charlie.exceptions.CharlieException;
import charlie.models.Deadline;
import charlie.models.Event;
import charlie.models.Task;
import charlie.models.Todo;
import charlie.storage.Storage;
import charlie.storage.TaskList;
import charlie.ui.Ui;

import java.util.ArrayList;

public class AddCommand extends Command {

    private String fullCommand;

    public AddCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws CharlieException {
        if (fullCommand.startsWith("todo")) {
            handleTodo(taskList.getTasks(), fullCommand);
        } else if (fullCommand.startsWith("event")) {
            handleEvent(taskList.getTasks(), fullCommand);
        } else if (fullCommand.startsWith("deadline")) {
            handleDeadline(taskList.getTasks(), fullCommand);
        }
        storage.saveTasks(taskList.getTasks());
        isExit = false;
    }

    private static void handleTodo(ArrayList<Task> taskList, String input) throws CharlieException {
        if (input.trim().equals("todo")) {
            throw new CharlieException("Sorry, the description of a todo cannot be empty.");
        }
        String description = input.substring(5);
        Todo todo = new Todo(description);
        taskList.add(todo);
        System.out.println("Got it. I've added this task:\n  " + todo);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    private static void handleDeadline(ArrayList<Task> taskList, String input) throws CharlieException {
        String[] parts = input.substring(9).split(" /by ");
        if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
            throw new CharlieException("Sorry, the description of a deadline cannot be empty and must include a /by time.");
        }
        Deadline deadline = new Deadline(parts[0], parts[1]);
        taskList.add(deadline);
        System.out.println("Got it. I've added this task:\n  " + deadline);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    private static void handleEvent(ArrayList<Task> taskList, String input) throws CharlieException {
        String[] parts = input.substring(6).split(" /from ");
        if (parts.length < 2 || parts[0].trim().isEmpty() || !parts[1].contains(" /to ")) {
            throw new CharlieException("Sorry, the description of an event cannot be empty and must include start /from and /to end times.");
        }
        String[] timeParts = parts[1].split(" /to ");
        Event event = new Event(parts[0], timeParts[0], timeParts[1]);
        taskList.add(event);
        System.out.println("Got it. I've added this task:\n  " + event);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

}