package duke;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Takes in and processes user input according to the available bot command.
 */
public class Parser {
    private TaskList myTasks;

    public Parser(Scanner scanner, TaskList myTasks) {
        this.myTasks = myTasks;
    }

    /**
     * Processes a single command.
     *
     * @param command taken in by scanner
     * @return boolean that denotes whether to continue processing
     */
    public boolean processCmd(String command) {
        try {
            String cmd = command.split(" ")[0];
            String params = command.substring(cmd.length()).trim();
            switch (cmd) {
            case "bye":
                System.out.println("Goodbye!");
                return false;
                // Fallthrough
            case "list":
                parseList();
                break;
            case "find":
                parseFind(params);
                break;
            case "mark":
                parseMark(params);
                break;
            case "unmark":
                parseUnmark(params);
                break;
            case "delete":
                parseDelete(params);
            break;
            case "todo":
                parseTodo(params);
                break;
            case "deadline":
                parseDeadline(params);
                break;
            case "event":
                parseEvent(params);
                break;
            case "undo":
                parseUndo();
                break;
            default:
                throw new DukeException.UnknownCommandException();
            }
        } catch (DukeException e) {
            System.out.println("DukeException: " + e.getMessage());
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("An unexpected error occurred.");
        }
        return true;
    }

    private void parseList() {
        System.out.println("Here are the tasks in your list:");
        myTasks.printTasks();
    }

    private void parseUndo() {
        myTasks.undo();
    }

    private void parseFind(String params) throws DukeException {
        if (params.length() == 0) {
            throw new DukeException.FindParamsException();
        }
        String keyword = params.trim();
        myTasks.findTasks(keyword);
    }

    private void parseMark(String params) throws DukeException {
        if (params.length() == 0) {
            throw new DukeException.MarkParamsException();
        }
        int num = Integer.valueOf(params);
        myTasks.markTask(num);
        Task t = myTasks.getTask(num);

        System.out.println("Nice! I've marked this task as done:");
        System.out.println(t);
    }

    private void parseUnmark(String params) throws DukeException {
        if (params.length() == 0) {
            throw new DukeException.MarkParamsException();
        }
        int num = Integer.valueOf(params);
        myTasks.unmarkTask(num);
        Task t = myTasks.getTask(num);

        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(t);
    }

    private void parseDelete(String params) throws DukeException {
        if (params.length() == 0) {
            throw new DukeException.DeleteParamsException();
        }
        int num = Integer.valueOf(params);
        Task toDelete = myTasks.getTask(num);
        myTasks.deleteTask(num);
        System.out.println("Noted. I've removed this task:");
        System.out.println(toDelete);
    }
    private void parseTodo(String params) throws DukeException {
        Todo newTask;
        if (params.length() == 0) {
            throw new DukeException.TodoDescriptionMissingException();
        }

        String desc = params;

        newTask = new Todo(desc);
        myTasks.addTask(newTask);

        System.out.println("Got it. I've added this task:");
        System.out.println(newTask);
        System.out.println("Now you have " + myTasks.size() + " tasks in the list");
    }
    private void parseDeadline(String params) throws DukeException {
        Deadline newTask;
        if (!params.contains("/by")) {
            throw new DukeException.DeadlineDetailsMissingException();
        }

        String desc = params.split("/by")[0].trim();
        String by = params.split("/by")[1].trim();

        // Check if by is in valid date format
        if (Dates.isValidInputDate(by)) {
            LocalDateTime dateObj = Dates.inputStr2DateTime(by);
            newTask = new Deadline(desc, dateObj); // Create date object
        } else {
            newTask = new Deadline(desc, by);
        }
        myTasks.addTask(newTask);

        System.out.println("Got it. I've added this task:");
        System.out.println(newTask);
        System.out.println("Now you have " + myTasks.size() + " tasks in the list");

    }
    private void parseEvent(String params) throws DukeException {
        Event newTask;
        if (!params.contains("/from") || !params.contains("/to")) {
            throw new DukeException.EventDetailsMissingException();
        }

        String desc = params.split("/from")[0];
        String from = params.split("/from")[1].split("/to")[0].trim();
        String to = params.split("/to")[1].trim();

        if (Dates.isValidInputDate(from) && Dates.isValidInputDate(to)) {
            LocalDateTime dateObjFrom = Dates.inputStr2DateTime(from);
            LocalDateTime dateObjTo = Dates.inputStr2DateTime(to);
            newTask = new Event(desc, dateObjFrom, dateObjTo); // Create date object
        } else {
            newTask = new Event(desc, from, to);
        }
        myTasks.addTask(newTask);

        System.out.println("Got it. I've added this task:");
        System.out.println(newTask);
        System.out.println("Now you have " + myTasks.size() + " tasks in the list");
    }

}
