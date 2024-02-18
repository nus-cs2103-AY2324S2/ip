package seedu.duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import seedu.duke.task.Deadline;
import seedu.duke.task.Event;
import seedu.duke.task.Task;
import seedu.duke.task.Todo;

/**
 * Represents <code>parsing</code> logic to interpret and execute
 * potential commands from user input e.g., <code>todo read book</code>
 */
public class Parser {

    /**
     * Parses input from user, specifically the <code>mark</code> command,
     * and interprets via the <code>TaskList</code> class.
     * Prints out the actions taken back to user via the <code>Ui</code> class.
     * @param input String written by user eg. <code>mark 2</code>
     * @param tasks <code>TaskList</code> object containing list of tasks
     * @param ui <code>Ui</code> object for printing actions
     * @return String output
     */
    public static String parseMark(String input, TaskList tasks, Ui ui) {
        String output = "";
        String[] splitInput = input.split(" ");
        try {
            if (splitInput.length < 2) {
                // for invalid entry: "mark"
                throw new DukeException("Here's the format I require: mark [valid index]");
            }
            int index = Integer.parseInt(splitInput[1]) - 1;
            if (index >= tasks.getSize()) {
                // for invalid entry: "mark [out of bounds]"
                throw new DukeException("Here's the format I require: mark [valid index]");
            } else if (index < 0) {
                // for invalid entry "mark [negative]"
                throw new DukeException("We don't have negative tasks!");
            }
            tasks.markTaskDone(Integer.parseInt(splitInput[1]) - 1);
            output = ui.showTaskDone(tasks.getTask(Integer.parseInt(splitInput[1]) - 1));
        } catch (DukeException d) {
            output = ui.printError(d);
        } catch (NumberFormatException n) {
            output = ui.printError(new DukeException("Here's the format I require: mark [valid index]"));
        }
        return output;
    }

    /**
     * Parses input from user, specifically the <code>unmark</code> command,
     * and interprets via the <code>TaskList</code> class.
     * Prints out the actions taken back to user via the <code>Ui</code> class.
     * @param input String written by user eg. <code>unmark 2</code>
     * @param tasks <code>TaskList</code> object containing list of tasks
     * @param ui <code>Ui</code> object for printing actions
     * @return String output
     */
    public static String parseUnmark(String input, TaskList tasks, Ui ui) {
        String output = "";
        String[] splitInput = input.split(" ");
        try {
            if (splitInput.length < 2) { // for invalid entry: "unmark"
                throw new DukeException("Here's the format I require: unmark [valid index]");
            }
            // for invalid entry "unmark [out of bounds]"
            int index = Integer.parseInt(splitInput[1]) - 1;
            if (index >= tasks.getSize()) {
                throw new DukeException("Here's the format I require: unmark [valid index]");
            } else if (index < 0) {
                // for invalid entry "mark [negative]"
                throw new DukeException("We don't have negative tasks!");
            }
            tasks.markTaskUndone(Integer.parseInt(splitInput[1]) - 1);
            output = ui.showTaskUndone(tasks.getTask(Integer.parseInt(splitInput[1]) - 1));
        } catch (DukeException d) {
            output = ui.printError(d);
        } catch (NumberFormatException n) {
            output = ui.printError(new DukeException("Here's the format I require: unmark [valid index]"));
        }
        return output;
    }

    /**
     * Parses input from user, specifically the <code>deadline</code> command,
     * and interprets via the <code>TaskList</code> class.
     * Prints out the actions taken back to user via the <code>Ui</code> class.
     * @param input String written by user eg. <code>deadline /by 2023-02-4</code>
     * @param tasks <code>TaskList</code> object containing list of tasks
     * @param ui <code>Ui</code> object for printing actions
     * @return String output
     */
    public static String parseDeadline(String input, TaskList tasks, Ui ui) {
        String output = "";
        String[] splitInput = input.split(" /by ");
        try {
            if (splitInput.length < 2) {
                throw new DukeException("Here's the format I require: deadline [description] /by [yyyy-mm-dd]");
            }
            String[] commandAndDescription = splitInput[0].split(" ", 2);
            String dateline = splitInput[1];
            LocalDate localDate = LocalDate.parse(dateline);
            Deadline deadline = new Deadline(commandAndDescription[1], localDate);
            tasks.addTask(deadline);
            output = ui.showTaskAdded(deadline, tasks.getSize());
        } catch (DukeException d) {
            output = ui.printError(d);
        } catch (DateTimeParseException dateTimeParseException) {
            output = ui.printError(new DukeException("Please enter a valid date in the format yyyy-mm-dd"));
        }
        return output;
    }

    /**
     * Parses input from user, specifically the <code>todo</code> command,
     * and interprets via the <code>TaskList</code> class.
     * Prints out the actions taken back to user via the <code>Ui</code> class.
     * @param input String written by user eg. <code>todo read book</code>
     * @param tasks <code>TaskList</code> object containing list of tasks
     * @param ui <code>Ui</code> object for printing actions
     * @return String output
     */
    public static String parseTodo(String input, TaskList tasks, Ui ui) {
        String output = "";
        String[] splitInput = input.split(" ", 2);
        try {
            if (splitInput.length < 2) {
                throw new DukeException("Here's the format I require: todo [name]");
            }
            String description = splitInput[1];
            Todo todo = new Todo(description);
            tasks.addTask(todo);
            output = ui.showTaskAdded(todo, tasks.getSize());
        } catch (DukeException d) {
            output = ui.printError(d);
        }
        return output;
    }

    /**
     * Parses input from user, specifically the <code>event</code> command,
     * and interprets via the <code>TaskList</code> class.
     * Prints out the actions taken back to user via the <code>Ui</code> class.
     * @param input String written by user
     *              eg. <code>event orientation /from 2023-08-02 /to 2024-08-09</code>
     * @param tasks <code>TaskList</code> object containing list of tasks
     * @param ui <code>Ui</code> object for printing actions
     * @return String output
     */
    public static String parseEvent(String input, TaskList tasks, Ui ui) {
        String output = "";
        String[] splitInput = input.split(" /from ");
        try {
            if (splitInput.length < 2) {
                throw new DukeException("Here's the format I require: "
                        + "event [description] /from [yyyy-mm-dd] /by [yyyy-mm-dd]");
            }
            String description = splitInput[0].split(" ", 2)[1];
            LocalDate fromDate = LocalDate.parse(splitInput[1].split(" /to ")[0]);
            LocalDate toDate = LocalDate.parse(splitInput[1].split(" /to ")[1]);
            Event event = new Event(description, fromDate, toDate);
            tasks.addTask(event);
            output = ui.showTaskAdded(event, tasks.getSize());
        } catch (DukeException d) {
            output = ui.printError(d);
        } catch (DateTimeParseException dateTimeParseException) {
        output = ui.printError(new DukeException("Please enter a valid date in the format yyyy-mm-dd"));
        }
        return output;
    }

    /**
     * Parses input from user, specifically the <code>delete</code> command,
     * and interprets via the <code>TaskList</code> class to get the task to delete.
     * Prints out the actions taken back to user via the <code>Ui</code> class.
     * @param input String written by user eg. <code>delete 2</code>
     * @param tasks <code>TaskList</code> object containing list of tasks
     * @param ui <code>Ui</code> object for printing actions
     * @return String output
     */
    public static String parseDelete(String input, TaskList tasks, Ui ui) {
        String output = "";
        String[] splitInput = input.split(" ");
        try {
            if (splitInput.length < 2) {
                throw new DukeException("Which task number do you want to delete?");
            }
            int numberToDelete = Integer.parseInt(splitInput[1]);
            if (numberToDelete > tasks.getSize()) {
                throw new DukeException("You don't have that many tasks!");
            } else if (numberToDelete < 1) {
                throw new DukeException("We don't have negative tasks!");
            }
            Task task = tasks.getTask(numberToDelete - 1);
            tasks.deleteTask(numberToDelete - 1);
            output = ui.showTaskDeleted(task, tasks.getSize());
        } catch (DukeException d) {
            output = ui.printError(d);
        } catch (NumberFormatException n) {
            output = ui.printError(new DukeException("Is that a valid task number?"));
        }
        return output;
    }

    /**
     * Parses input from user, specifically the <code>find</code> command,
     * and interprets via the <code>TaskList</code> class to search for tasks.
     * Prints out the actions taken back to user via the <code>Ui</code> class.
     * @param input String written by user eg. <code>find book</code>
     * @param tasks <code>TaskList</code> object containing list of tasks
     * @param ui <code>Ui</code> object for printing actions
     * @return String output
     */
    public static String parseFind(String input, TaskList tasks, Ui ui) {
        String output = "";
        String[] splitInput = input.split(" ");
        try {
            if (splitInput.length < 2) {
                throw new DukeException("Here's the format I require: find [search term]");
            }
            String searchTerm = splitInput[1];
            output = ui.searchThenPrintList(tasks.getList(), searchTerm);
        } catch (DukeException d) {
            output = ui.printError(d);
        }
        return output;
    }
}
