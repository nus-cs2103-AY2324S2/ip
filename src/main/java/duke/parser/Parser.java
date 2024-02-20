package duke.parser;

import java.io.IOException;
import java.time.LocalDate;

import duke.exception.JosephException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Tasklist;
import duke.task.Todo;
import duke.ui.Ui;
import javafx.application.Platform;

/**
 * Represents a parser that parses user input and executes the corresponding commands.
 */
public class Parser {

    protected String outputMessage;

    /**
     * Parses the user input and executes the corresponding commands.
     *
     * @param input The user input.
     * @param ui The user interface.
     * @param storage The storage for the tasklist.
     * @param todolist The task list to operate on.
     */
    public String parseCommand(String input, Ui ui,
                                Storage storage, Tasklist todolist) throws JosephException {
        assert(input != null);
        String[] parts = input.trim().split(" ", 2);
        String command = parts[0];
        String details = parts.length > 1 ? parts[1].trim() : ""; // contains the details after command is issued
        switch (command) {
        case "list":
            return handleList(ui, todolist, storage);
        case "mark":
        case "unmark":
            return handleMarkUnmark(ui, details, command.equals("mark"), todolist);
        case "todo":
            return handleAddTask(ui, new Todo(details, false), todolist);
        case "deadline":
            return handleAddTask(ui, createDeadline(details), todolist);
        case "event":
            return handleAddTask(ui, createEvent(details), todolist);
        case "delete":
            return handleDelete(ui, details, todolist);
        case "find":
            return findTask(ui, details, todolist);
        case "sort":
            sortTodolist(todolist, storage);
            return "Sorted";
        case "bye":
            Platform.exit();
            return "Bye. Hope to see you again soon!";
        default:
            outputMessage = "OOPS!!! I'm sorry, but I don't know what that means :-(";
            return outputMessage;
        }
    }
    public boolean commandIsBye(String input) {
        return input.trim().equals("bye");
    }

    private String handleList(Ui ui, Tasklist todolist, Storage storage) throws JosephException {
        try {
            storage.saveData(todolist.getTodolist());
            outputMessage = todolist.printTodolist();
            ui.printMessage(outputMessage);
            return outputMessage;
        } catch (IOException e) {
            throw new JosephException("An error occurred while writing to the file.");
        }
    }

    private String handleMarkUnmark(Ui ui, String details,
                                  boolean isMark, Tasklist todolist) throws JosephException {
        try {
            int taskNumber = Integer.parseInt(details) - 1;
            todolist.markTaskAsDone(taskNumber, isMark);
            String statusMessage = isMark ? "Nice! I've marked this task as done:"
                    : "OK, I've marked this task as not done yet:";
            outputMessage = statusMessage + "\n\t" + todolist.getTaskString(taskNumber);
            ui.printMessage(outputMessage);
            return outputMessage;
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new JosephException("Please provide a valid task number to mark or unmark.");
        }
    }

    private String handleAddTask(Ui ui, Task task, Tasklist todolist) {
        todolist.addItem(task);
        outputMessage = "Added: " + task;
        ui.printMessage(outputMessage);
        return outputMessage;
    }

    private Task createDeadline(String details) throws JosephException {
        assert(details != null);
        String[] parts = details.split("/by", 2);
        if (parts.length < 2) {
            throw new JosephException("Invalid deadline format. Use 'deadline [name] /by yyyy-mm-dd'");
        }
        LocalDate by = LocalDate.parse(parts[1].trim());
        return new Deadline(parts[0].trim(), by, false);
    }

    private Task createEvent(String details) throws JosephException {
        assert(details != null);
        String[] parts = details.split("/from | /to ", 3);
        if (parts.length < 3) {
            throw new JosephException("Invalid event format. Use 'event [name] /from yyyy-mm-dd /to yyyy-mm-dd'");
        }
        LocalDate from = LocalDate.parse(parts[1].trim());
        LocalDate to = LocalDate.parse(parts[2].trim());
        return new Event(parts[0].trim(), from, to, false);
    }

    private String handleDelete(Ui ui, String details, Tasklist todolist) throws JosephException {
        try {
            int taskNumber = Integer.parseInt(details) - 1;
            Task removed = todolist.removeItem(taskNumber);
            outputMessage = "Noted. I've removed this task: " + removed;
            ui.printMessage(outputMessage);
            return outputMessage;
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new JosephException("Please provide a valid task number to delete.");
        }
    }

    private String findTask(Ui ui, String details, Tasklist todolist) throws JosephException {
        if (details.isEmpty()) {
            throw new JosephException("Please provide a keyword to search for.");
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
            outputMessage = "No matching tasks found.";
            ui.printMessage(outputMessage);
            return outputMessage;
        }
        outputMessage = tasksWithKeyword.toString().trim();
        ui.printMessage(outputMessage);
        return outputMessage;
    }

    /**
     * Sorts the task list by description.
     */
    public void sortTodolist(Tasklist todolist, Storage storage) throws JosephException {
        try {
            todolist.sortTodolist();
            storage.saveData(todolist.getTodolist());
        } catch (IOException e) {
            throw new JosephException("An error occurred while writing to the file.");
        }
    }
}
