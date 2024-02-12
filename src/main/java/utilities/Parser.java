package utilities;

import java.time.format.DateTimeParseException;

import command.AddCommand;
import command.Command;
import command.DeleteCommand;
import command.ExitCommand;
import command.FindCommand;
import command.ListCommand;
import command.MarkCommand;
import exceptions.DukeException;
import task.Deadline;
import task.Event;
import task.Todo;

/**
 * Parser object that helps to interpret what the user inputs.
 */
public class Parser {
    /**
     * A method that helps to interpret what the user has keyed in the command line, and responds accordingly.
     * @param userInput The input that is entered by the user in the command line.
     * @return A command based on what the user has entered.
     * @throws DukeException The exception thrown when the user enters an invalid input.
     */
    public static Command parse(String userInput) throws DukeException {
        if (userInput.equals("bye")) {
            return new ExitCommand();
        } else if (userInput.equals("list")) {
            return new ListCommand();
        } else if (userInput.startsWith("mark")) {
            return new MarkCommand(userInput, true);
        } else if (userInput.startsWith("unmark")) {
            return new MarkCommand(userInput, false);
        } else if (userInput.startsWith("delete")) {
            return new DeleteCommand(userInput);
        } else if (userInput.startsWith("find")) {
            try {
                String keyword = userInput.split(" ", 2)[1];
                if (keyword.isEmpty()) {
                    throw new DukeException("Keyword cannot be empty. "
                            + "Please follow the given find format 'find <keyword>'.");
                }
                return new FindCommand(keyword);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("OOPS!!! Please follow the given find format 'find <keyword>'.");
            }
        } else {
            // Sort out type of task
            if (userInput.startsWith("todo")) {
                try {
                    String description = userInput.split(" ", 2)[1];
                    assert !description.equals("") : "Description should not be empty";
                    if (description.isEmpty()) {
                        throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                    } else {
                        Todo newTodo = new Todo(description);
                        return new AddCommand(newTodo);
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("OOPS!!! Please follow the given todo format 'todo <task description>'.");
                }
            } else if (userInput.startsWith("deadline")) {
                try {
                    String description = userInput.split(" /by ")[1];
                    assert !description.equals("") : "Description should not be empty";
                    if (description.isEmpty()) {
                        throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
                    } else {
                        String by = userInput.split(" /by ")[1];
                        Deadline newDeadline = new Deadline(description, by);
                        return new AddCommand(newDeadline);
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("OOPS!!! Please follow the given deadline format "
                            + "'deadline <task description> /by <deadline>'.");
                } catch (DateTimeParseException e) {
                    throw new DukeException("Invalid date time provided!");
                }
            } else if (userInput.startsWith("event")) {
                try {
                    String description = userInput.split(" /from ")[0].split(" ", 2)[1];
                    assert !description.equals("") : "Description should not be empty";
                    if (description.isEmpty()) {
                        throw new DukeException("OOPS!!! The description of an event cannot be empty.");
                    } else {
                        String from = userInput.split(" /from ")[1].split(" /to ")[0];
                        String to = userInput.split(" /to ")[1];
                        Event newEvent = new Event(description, from, to);
                        return new AddCommand(newEvent);
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("OOPS!!! Please follow the given event format "
                            + "'event <task description> /from <start> /to <end>'.");
                } catch (DateTimeParseException e) {
                    throw new DukeException("Invalid date time provided!");
                }
            } else {
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-( "
                        + "Please start with todo, deadline or event.");
            }
        }
    }
}
