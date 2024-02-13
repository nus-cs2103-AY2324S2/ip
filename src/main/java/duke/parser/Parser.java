package duke.parser;

import java.io.IOException;
import java.time.LocalDate;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Tasklist;
import duke.task.Todo;
import duke.ui.Ui;

/**
 * Represents a parser that parses user input and executes the corresponding commands.
 */
public class Parser {
    /**
     * Parses the user input and executes the corresponding commands.
     *
     * @param input The user input.
     * @param ui The user interface.
     * @param storage The storage for the tasklist.
     * @param todolist The task list to operate on.
     */
    public void parseCommand(String input, Ui ui,
                                Storage storage, Tasklist todolist) throws DukeException {
        String[] parts = input.trim().split(" ", 2);
        String command = parts[0];
        String details = parts.length > 1 ? parts[1].trim() : ""; // contains the details after command is issued
        switch (command) {
        case "list":
            handleList(ui, todolist, storage);
            break;
        case "mark":
        case "unmark":
            handleMarkUnmark(ui, details, command.equals("mark"), todolist);
            break;
        case "todo":
            handleAddTask(ui, new Todo(details, false), todolist);
            break;
        case "deadline":
            handleAddTask(ui, createDeadline(details), todolist);
            break;
        case "event":
            handleAddTask(ui, createEvent(details), todolist);
            break;
        case "delete":
            handleDelete(ui, details, todolist);
            break;
        case "find":
            findTask(ui, details, todolist);
            break;
        case "bye":
            break;
        default:
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
    public boolean commandIsBye(String input) {
        return input.trim().equals("bye");
    }

    private void handleList(Ui ui, Tasklist todolist, Storage storage) throws DukeException {
        try {
            storage.saveData(todolist.getTodolist());
            ui.printMessage(todolist.printTodolist());
        } catch (IOException e) {
            throw new DukeException("An error occurred while writing to the file.");
        }
    }

    private void handleMarkUnmark(Ui ui, String details, boolean isMark, Tasklist todolist) {
        int taskNumber = Integer.parseInt(details) - 1;
        todolist.markTaskAsDone(taskNumber, isMark);
        String statusMessage = isMark ? "Nice! I've marked this task as done:"
                : "OK, I've marked this task as not done yet:";
        ui.printMessage(statusMessage + "\n\t" + todolist.getTaskString(taskNumber));
    }

    private void handleAddTask(Ui ui, Task task, Tasklist todolist) {
        todolist.addItem(task);
        ui.printMessage("Added: " + task);
    }

    private Task createDeadline(String details) throws DukeException {
        String[] parts = details.split("/by", 2);
        if (parts.length < 2) {
            throw new DukeException("Invalid deadline format. Use 'deadline [name] /by yyyy-mm-dd'");
        }
        LocalDate by = LocalDate.parse(parts[1].trim());
        return new Deadline(parts[0].trim(), by, false);
    }

    private Task createEvent(String details) throws DukeException {
        String[] parts = details.split("/from | /to ", 3);
        if (parts.length < 3) {
            throw new DukeException("Invalid event format. Use 'event [name] /from yyyy-mm-dd /to yyyy-mm-dd'");
        }
        LocalDate from = LocalDate.parse(parts[1].trim());
        LocalDate to = LocalDate.parse(parts[2].trim());
        return new Event(parts[0].trim(), from, to, false);
    }

    private void handleDelete(Ui ui, String details, Tasklist todolist) {
        int taskNumber = Integer.parseInt(details) - 1;
        Task removed = todolist.removeItem(taskNumber);
        ui.printMessage("Noted. I've removed this task: " + removed);
    }

    private void findTask(Ui ui, String details, Tasklist todolist) throws DukeException {
        if (details.isEmpty()) {
            throw new DukeException("Please provide a keyword to search for.");
        }

        StringBuilder tasksWithKeyword = new StringBuilder();
        int i = 1;
        for (Task task : todolist.getTodolist()) {
            if (task.getDescription().contains(details)) {
                tasksWithKeyword.append(i).append(". ").append(task).append("\n\t");
                i++;
            }
        }

        if (tasksWithKeyword.length() == 0) {
            ui.printMessage("No matching tasks found.");
            return;
        }
        ui.printMessage(tasksWithKeyword.toString().trim());
    }
}
