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
        int i = 0;
        while (tasks.getTask(i) != null) {
            i++;
        }

        switch (action) {
        case "list":
            ui.printList(tasks, i);
            break;
        case "bye":
            ui.printMessage("Bye. Hope to see you again soon!");
            ui.printMessage(line);
            break;
        case "mark":
            for (int k = 0; k < i; k++) {
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
            break;

        case "unmark":
            for (int k = 0; k < i; k++) {
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
            break;
        case "todo":
            Task item = new ToDo(elems[1]);
            ui.printMessage("Got it. I've added this task:");
            ui.printMessage(item.toString());
            i++;
            tasks.addTask(item);
            ui.printMessage("Now you have " + i + " task(s) in your list!");
            ui.printMessage(line);
            break;
        case "deadline":
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
                i++;
                tasks.addTask(dline);
                ui.printMessage("Now you have " + i + " task(s) in your list!");
            } else {
                System.out.println("Invalid date and time format -_-");
            }
            System.out.println(line);
            break;

        case "event":
            String[] fromto = elems[1].split("/", 3);
            LocalDate fromDate = null;
            LocalDate toDate = null;
            for (String format : dateFormats) {
                try {
                    fromDate = LocalDate.parse(fromto[1], DateTimeFormatter.ofPattern(format));
                    toDate = LocalDate.parse(fromto[2], DateTimeFormatter.ofPattern(format));
                    break;
                } catch (DateTimeParseException ignored) {
                    ;
                }
            }
            if (toDate != null & fromDate != null) {
                Task e = new Event(fromto[0], fromDate, toDate);
                ui.printMessage("Got it. I've added this task:");
                ui.printMessage(e.toString());
                i++;
                tasks.addTask(e);
                ui.printMessage("Now you have " + i + " task(s) in your list!");
                ui.printMessage(line);
                //storage.saveToFile(i, tasks.getTasks());

            } else {
                ui.printMessage("Invalid date and time format -_-");
            }
            break;
        case "delete":
            int toDelete = Integer.valueOf(elems[1]) - 1;
            if (toDelete >= 0 && toDelete < i) {
                tasks.deleteTask(toDelete);
                ui.printMessage("Noted. I've removed this task:");
                ui.printMessage(tasks.getTask(toDelete).toString());
                i--;
                ui.printMessage("Now you have " + i + " task(s) in the list.");
            } else {
                ui.printMessage("That is an invalid task to delete sir??");
            }
            ui.printMessage(line);
            break;
        case "find":
            String key = elems[1].toLowerCase();
            ui.printMessage("Here are the matching tasks in your list:");
            int found = 0;
            for (int k = 0; k < i; k++) {
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
            break;

        default:
            ui.printMessage("Sorry what??, I did not get that!");



        }
    }
}
