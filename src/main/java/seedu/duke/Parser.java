package seedu.duke;

import seedu.duke.task.Task;
import seedu.duke.task.Deadline;
import seedu.duke.task.Event;
import seedu.duke.task.Todo;

import java.time.LocalDate;

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
     */
    public static void parseMark(String input, TaskList tasks, Ui ui) {
        String[] split = input.split(" ");
        try {
            if (split.length < 2) {
                // for invalid entry: "mark"
                throw new DukeException("Here's the format I require: mark [valid index]");
            }
            int index = Integer.parseInt(split[1]) - 1;
            if (index >= tasks.getSize()) {
                // for invalid entry "mark [out of bounds]"
                throw new DukeException("Here's the format I require: mark [valid index]");
            }
            tasks.markTaskDone(Integer.parseInt(split[1]) - 1);
            ui.showTaskDone(tasks.getTask(Integer.parseInt(split[1]) - 1));
        } catch (DukeException d){
            ui.printError(d);
        }
    }

    /**
     * Parses input from user, specifically the <code>unmark</code> command,
     * and interprets via the <code>TaskList</code> class.
     * Prints out the actions taken back to user via the <code>Ui</code> class.
     * @param input String written by user eg. <code>unmark 2</code>
     * @param tasks <code>TaskList</code> object containing list of tasks
     * @param ui <code>Ui</code> object for printing actions
     */
    public static void parseUnmark(String input, TaskList tasks, Ui ui) {
        String[] split = input.split(" ");

        try {
            if (split.length < 2) { // for invalid entry: "unmark"
                throw new DukeException("Here's the format I require: unmark [valid index]");
            }
            // for invalid entry "unmark [out of bounds]"
            int index = Integer.parseInt(split[1]) - 1;
            if (index >= tasks.getSize()) {
                throw new DukeException("Here's the format I require: unmark [valid index]");
            }
            tasks.markTaskUndone(Integer.parseInt(split[1]) - 1);
            ui.showTaskUndone(tasks.getTask(Integer.parseInt(split[1]) - 1));
        } catch (DukeException d){
            ui.printError(d);
        }
    }

    /**
     * Parses input from user, specifically the <code>deadline</code> command,
     * and interprets via the <code>TaskList</code> class.
     * Prints out the actions taken back to user via the <code>Ui</code> class.
     * @param input String written by user eg. <code>deadline /by 2023-02-4</code>
     * @param tasks <code>TaskList</code> object containing list of tasks
     * @param ui <code>Ui</code> object for printing actions
     */
    public static void parseDeadline(String input, TaskList tasks, Ui ui) {
        String[] split = input.split(" /by ");
        try {
            if (split.length < 2) {
                throw new DukeException("Here's the format I require: deadline [name] /by [yyyy-mm-dd]");
            }
            String[] splitAgain = split[0].split(" ", 2);
            String dateline = split[1];
            LocalDate localDate = LocalDate.parse(dateline);
            Deadline deadline = new Deadline(splitAgain[1], localDate);
            tasks.addTask(deadline);
            ui.showTaskAdded(deadline, tasks.getSize());
        } catch (DukeException d) {
            ui.printError(d);
        }
    }

    /**
     * Parses input from user, specifically the <code>todo</code> command,
     * and interprets via the <code>TaskList</code> class.
     * Prints out the actions taken back to user via the <code>Ui</code> class.
     * @param input String written by user eg. <code>todo read book</code>
     * @param tasks <code>TaskList</code> object containing list of tasks
     * @param ui <code>Ui</code> object for printing actions
     */
    public static void parseTodo(String input, TaskList tasks, Ui ui) {
        String[] split = input.split(" ", 2);
        try {
            if (split.length < 2) {
                throw new DukeException("Here's the format I require: todo [name]");
            }
            String description = split[1];
            Todo todo = new Todo(description);
            tasks.addTask(todo);
            ui.showTaskAdded(todo, tasks.getSize());
        } catch (DukeException d){
            ui.printError(d);
        }
    }

    /**
     * Parses input from user, specifically the <code>event</code> command,
     * and interprets via the <code>TaskList</code> class.
     * Prints out the actions taken back to user via the <code>Ui</code> class.
     * @param input String written by user
     *              eg. <code>event orientation /from 2023-08-02 /to 2024-08-09</code>
     * @param tasks <code>TaskList</code> object containing list of tasks
     * @param ui <code>Ui</code> object for printing actions
     */
    public static void parseEvent(String input, TaskList tasks, Ui ui) {
        String[] split = input.split(" /from ");
        try {
            if (split.length < 2) {
                throw new DukeException("Here's the format I require: "
                        + "event [name] /from [yyyy-mm-dd] /by [yyyy-mm-dd]");
            }
            String description = split[0].split(" ", 2)[1];
            LocalDate from = LocalDate.parse(split[1].split(" /to ")[0]);
            LocalDate to = LocalDate.parse(split[1].split(" /to ")[1]);
            Event event = new Event(description, from, to);
            tasks.addTask(event);
            ui.showTaskAdded(event, tasks.getSize());
        } catch (DukeException d) {
            ui.printError(d);
        }
    }

    /**
     * Parses input from user, specifically the <code>delete</code> command,
     * and interprets via the <code>TaskList</code> class to get the task to delete.
     * Prints out the actions taken back to user via the <code>Ui</code> class.
     * @param input String written by user eg. <code>delete 2</code>
     * @param tasks <code>TaskList</code> object containing list of tasks
     * @param ui <code>Ui</code> object for printing actions
     */
    public static void parseDelete(String input, TaskList tasks, Ui ui) {
        String[] split = input.split(" ");
        try {
            if (split.length < 2) {
                throw new DukeException("Which task number do you want to delete?");
            }
            int number = Integer.parseInt(split[1]);
            Task task = tasks.getTask(number -  1);
            tasks.deleteTask(number - 1);
            ui.showTaskDeleted(task, tasks.getSize());
        } catch (DukeException d) {
            ui.printError(d);
        }
    }
}
