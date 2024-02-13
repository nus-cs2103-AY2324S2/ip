package duke.parser;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

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
     * @return True if the user input is "bye", false otherwise.
     */
    public boolean parseCommand(String input, Ui ui, Storage storage, Tasklist todolist) {
        String[] parts = input.split(" ", 2);
        String command = parts[0];
        String details = parts.length > 1 ? parts[1] : "";
        String output;
        int taskNumber;

        switch (command) {
        case "list":
            output = todolist.printTodolist();
            try {
                storage.saveData(todolist.getTodolist());
            } catch (IOException e) {
                System.out.println("An error occurred while writing to the file.");
                break;
            }
            break;
        case "mark":
            taskNumber = Integer.parseInt(details) - 1;
            todolist.markTaskAsDone(taskNumber, true);
            output = "Nice! I've marked this task as done:\n\t" + todolist.getTaskString(taskNumber);
            break;
        case "unmark":
            taskNumber = Integer.parseInt(details) - 1;
            todolist.markTaskAsDone(taskNumber, false);
            output = "OK, I've marked this task as not done yet:\n\t" + todolist.getTaskString(taskNumber);
            break;
        case "todo":
            try {
                if (details.isEmpty()) {
                    throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                }
                Task todo = new Todo(details, false);
                output = "added todo: " + todo;
                todolist.addItem(todo);
            } catch (DukeException e) {
                output = e.toString();
            }
            break;
        case "deadline":
            String deadlineName;
            LocalDate by;
            Task deadline;
            try {
                String[] d = details.split("/by");
                if (d.length <= 1) {
                    throw new DukeException("Invalid deadline task!\n\t"
                            + "Format: 'deadline [deadline name] /by yyyy-mm-dd'");
                }
                deadlineName = d[0].trim();
                by = LocalDate.parse(d[1].trim());
                deadline = new Deadline(deadlineName, by, false);
            } catch (DukeException e) {
                output = e.toString();
                break;
            } catch (DateTimeParseException e) {
                output = "Please specify the correct format date: yyyy-mm-dd";
                break;
            }
            todolist.addItem(deadline);
            output = "added deadline: " + deadline.toString();
            break;
        case "event":
            String eventName = details.split("/from ")[0].trim();
            LocalDate from = LocalDate.parse(details.split("/from ")[1].split(" /to")[0].trim());
            LocalDate to = LocalDate.parse(details.split("/to ")[1].trim());
            try {
                Task event = new Event(eventName, from, to, false);
                todolist.addItem(event);
                output = "added event: " + event.toString();
                break;
            } catch (DateTimeParseException e) {
                output = "Please specify the correct format date: yyyy-mm-dd";
                break;
            }
        case "delete":
            taskNumber = Integer.parseInt(details) - 1;
            Task t = todolist.removeItem(taskNumber);
            output = "Noted. I've removed this task:" + t.toString();
            break;
        case "bye":
            return true;
        default:
            DukeException e = new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            output = e.toString();
        }
        if (!output.isEmpty()) {
            ui.printMessage(output);
        }
        return false;
    }
}
