package duke;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Parses user input and executes corresponding actions in the Duke application.
 */
public class Parser {
    /**
     * The user input to be parsed and executed.
     */
    private String input;
    private Ui ui;
    /**
     * Constructs a Parser object with the given user input.
     *
     * @param input The user input to be parsed and executed.
     */
    public Parser(String input) {
        assert input != null : "Input cannot be null";
        this.input = input;
    }
    /**
     * Executes the corresponding action based on the parsed user input.
     *
     * @param tasks The task list to perform actions on.
     * @param ui    The user interface to interact with the user.
     */
    public void execute(TaskList tasks, Ui ui) {
        String line = "------------------------------";
        String[] elems = input.split(" ", 2);
        String action = elems[0];
        String[] dateFormats = {"dd/MM/yyyy", "MMM dd yyyy"};
        int taskCount = 0;
        while (tasks.getTask(taskCount) != null) {
            taskCount++;
        }

        switch (action) {
        case "list":
            ui.printList(tasks, taskCount);
            break;
        case "bye":
            ui.printMessage("Bye. Hope to see you again soon!");
            ui.printMessage(line);
            break;
        case "mark":
            handleMark(tasks, elems[1], action, elems);
            break;
        case "unmark":
            handleUnmark(tasks, elems[1], action, elems);
            break;
        case "todo":
            handleTodo(tasks, elems[1], ui, elems, line);
            taskCount++;
            break;
        case "deadline":
            handleDeadline(tasks, elems[1], ui, elems, dateFormats, line);
            taskCount++;
            break;

        case "event":
            handleEvent(tasks, elems[1], ui, dateFormats, elems, line);
            taskCount++;
            break;
        case "delete":
            handleDelete(tasks, elems[1], ui, taskCount, elems, line);
            taskCount--;
            break;
        case "find":
            handleFind(tasks, elems[1].toLowerCase(), ui, elems, line);
            break;
        default:
            ui.printMessage("Sorry what??, I did not get that!");
        }
    }



    private void handleMark(TaskList tasks, String description, String action, String[] elems) {
        assert tasks != null : "TaskList cannot be null";
        assert elems != null && elems.length >= 2 : "Invalid arguments for marking task";
        for (int k = 0; k < tasks.size(); k++) {
            if (tasks.getTask(k) != null) {
                Task t = tasks.getTask(k);
                String str = t.description;
                if (str.equals(elems[1])) {
                    t.markAsDone();
                }
            }
        }
        ui.printMessage("Nice! I've marked this task as done:");
        ui.printMessage("[X] " + elems[1]);
    }

    private void handleUnmark(TaskList tasks, String description, String action, String[] elems) {
        for (int k = 0; k < tasks.size(); k++) {
            if (tasks.getTask(k) != null) {
                Task t = tasks.getTask(k);
                String str = t.description;
                if (str.equals(elems[1])) {
                    t.unMark();
                }
            }
        }
        ui.printMessage("OK, I've marked this task as not done yet:");
        ui.printMessage("[ ] " + elems[1] );
    }

    private void handleTodo(TaskList tasks, String description, Ui ui, String[] elems, String line) {
        Task item = new ToDo(elems[1]);
        ui.printMessage("Got it. I've added this task:");
        ui.printMessage(item.toString());
        tasks.addTask(item);
        ui.printMessage("Now you have " + tasks.size() + " task(s) in your list!");
        ui.printMessage(line);
    }

    private void handleDeadline(TaskList tasks, String description, Ui ui, String[] elems,
                                String[] dateFormats, String line) {
        String[] by = elems[1].split("/by ", 2);
        String[] dateTime = by[1].split(" ", 2);
        LocalDate deadlineDate = null;
        for (String format : dateFormats) {
            try {
                deadlineDate = LocalDate.parse(by[1].trim(),
                        DateTimeFormatter.ofPattern(format));
                break;
            } catch (DateTimeParseException ignored) {
                ;
            }
        }
        if (deadlineDate != null) {
            Task dline = new Deadline(by[0], deadlineDate);
            ui.printMessage("Got it. I've added this task:");
            ui.printMessage(dline.toString());
            tasks.addTask(dline);
            ui.printMessage("Now you have " + tasks.size() + " task(s) in your list!");
        } else {
            System.out.println("Invalid date and time format -_-");
        }
        System.out.println(line);
    }

    private void handleEvent(TaskList tasks, String description, Ui ui, String[] dateFormats,
                             String[] elems, String line) {
        //String[] fromto = elems[1].split("/", 3);
        String[] fromto = elems[1].trim().split("/from", 2);
        String[] from = fromto[1].trim().split("\\s*/to\\s*", 2);

        //String[] to = from[1];
        LocalDate fromDate = null;
        LocalDate toDate = null;
        for (String format : dateFormats) {
            try {
                fromDate = LocalDate.parse(from[0], DateTimeFormatter.ofPattern(format));
                toDate = LocalDate.parse(from[1], DateTimeFormatter.ofPattern(format));
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Parsing error: " + e.getMessage());
            }
        }
        if (toDate != null && fromDate != null) {
            Task e = new Event(fromto[0], fromDate, toDate);
            ui.printMessage("Got it. I've added this task:");
            ui.printMessage(e.toString());
            tasks.addTask(e);
            ui.printMessage("Now you have " + tasks.size() + " task(s) in your list!");
            ui.printMessage(line);
        } else {
            ui.printMessage("Invalid date and time format!! -_-");
        }
    }

    private void handleDelete(TaskList tasks, String input, Ui ui, int taskCount, String[] elems, String line) {
        int toDelete = Integer.valueOf(input) - 1;
        if (toDelete >= 0 && toDelete < taskCount) {
            ui.printMessage("Noted. I've removed this task:");
            ui.printMessage(tasks.getTask(toDelete).toString());
            tasks.deleteTask(toDelete);
            ui.printMessage("Now you have " + tasks.size() + " task(s) in the list.");
        } else {
            ui.printMessage("That is an invalid task to delete sir??");
        }
        ui.printMessage(line);
    }

    private void handleFind(TaskList tasks, String key, Ui ui, String[] elems, String line) {
        //String key = elems[1].toLowerCase();
        ui.printMessage("Here are the matching tasks in your list:");
        int found = 0;
        for (int k = 0; k < tasks.size(); k++) {
            Task task = tasks.getTask(k);
            if (task != null && task.description.toLowerCase().contains(key)) {
                int nr = found + 1;
                System.out.println(nr + "." + task.toString() + ".");
                found++;
            }
        }
        if (found == 0) {
            ui.printMessage("No matching tasks found.");
        }
        ui.printMessage(line);
    }

}

