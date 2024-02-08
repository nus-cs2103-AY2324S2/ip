package seedu.duke;

import seedu.duke.task.Task;
import seedu.duke.task.Deadline;
import seedu.duke.task.Event;
import seedu.duke.task.Todo;

import java.time.LocalDate;

public class Parser {

    public static void parseMark(String input, TaskList tasks, Ui ui) {
        String[] split = input.split(" ");
        try {
            if (split.length < 2) { // for invalid entry: "mark"
                throw new DukeException("Here's the format I require: mark [valid index]");
            }
            // for invalid entry "mark [out of bounds]"
            int index = Integer.parseInt(split[1]) - 1;
            if (index >= tasks.getSize()) {
                throw new DukeException("Here's the format I require: mark [valid index]");
            }
            tasks.markTaskDone(Integer.parseInt(split[1]) - 1);
            ui.showTaskDone(tasks.getTask(Integer.parseInt(split[1]) - 1));
        } catch (DukeException d){
            ui.printError(d);
        }
    }

    public static void parseUnmark(String input, TaskList tasks, Ui ui) {
        String[] split = input.split(" ");

        try {
            if (split.length < 2) { // for invalid entry: "unmark"
                throw new DukeException("Here's the format I require: unmark [valid index]");
            }
            // for invalid entry "unmark [out of bounds]"
            int index = Integer.parseInt(split[1]) - 1;
            if (index >= tasks.getSize()) {
                throw new DukeException("Here's the format I require: mark [valid index]");
            }
            tasks.markTaskUndone(Integer.parseInt(split[1]) - 1);
            ui.showTaskUndone(tasks.getTask(Integer.parseInt(split[1]) - 1));
        } catch (DukeException d){
            ui.printError(d);
        }
    }

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

    public static void parseEvent(String input, TaskList tasks, Ui ui) {
        String[] split = input.split(" /from ");
        try {
            if (split.length < 2) {
                throw new DukeException("Here's the format I require: event [name] /from [yyyy-mm-dd] /by [yyyy-mm-dd]");
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

    public static void parseFind(String input, TaskList tasks, Ui ui) {
        //add
        String[] split = input.split(" ", 2);
        try {
            if (split.length < 2) {
                throw new DukeException("Here's the format I require: find [search term]");
            }
            String searchTerm = split[1];
            ui.searchThenPrintList(tasks.getList(), searchTerm);
        } catch (DukeException d){
            ui.printError(d);
        }
    }
}
