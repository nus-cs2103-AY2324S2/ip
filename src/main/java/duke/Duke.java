package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.time.format.DateTimeFormatter;

import java.nio.file.Path;
import java.nio.file.Paths;

import java.time.LocalDateTime;

public class Duke {

    public enum CommandType {
        LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE
    }

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private Parser parser;

    public Duke(Path filePath, String botName) {
        ui = new Ui(botName);
        storage = new Storage(filePath);
        parser = new Parser();
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {

        ui.showWelcome();
        String input = ui.readCommand();
        while (true) {
            try {
                if (input.equals("bye")) {
                    break;
                }
                Command cmd = parser.parse(input);
                switch (cmd.type) {
                    case LIST:
                        ui.showTaskList(tasks.getTaskStrings());
                        break;
                    case MARK:
                        int toMark = Integer.parseInt(cmd.args[0]) - 1;
                        tasks.markTaskAsDone(toMark);
                        ui.showTaskMarked(tasks.getTask(toMark));
                        break;
                    case UNMARK:
                        int toUnmark = Integer.parseInt(cmd.args[0]) - 1;
                        tasks.markTaskAsUndone(toUnmark);
                        ui.showTaskUnmarked(tasks.getTask(toUnmark));
                        break;
                    case TODO:
                        Todo newTodo = createTodo(cmd.args[0]);
                        tasks.addTask(newTodo);
                        ui.showTaskAdded(newTodo, tasks.getSize());
                        break;
                    case DEADLINE:
                        Deadline newDeadline = createDeadline(cmd.args[0], cmd.args[1]);
                        tasks.addTask(newDeadline);
                        ui.showTaskAdded(newDeadline, tasks.getSize());
                        break;
                    case EVENT:
                        Event newEvent = createEvent(cmd.args[0], cmd.args[1], cmd.args[2]);
                        tasks.addTask(newEvent);
                        ui.showTaskAdded(newEvent, tasks.getSize());
                        //numList(duke.tasks.getSize());
                        break;
                    case DELETE:
                        Task deletedTask = tasks.deleteTask(Integer.parseInt(cmd.args[0]) - 1);
                        ui.showTaskDeleted(deletedTask, tasks.getSize());
                        break;
                    default:
                        throw new DukeException("Unknown command");
                }
                storage.updateFile(tasks.getFileStrings());
            } catch (duke.DukeException e) {
                printLineBreak();
                System.out.println(e.getMessage());
            }
            printLineBreak();
            input = ui.readCommand();
        }
        ui.showGoodbye();
    }

    public static void printLineBreak() {
        System.out.println("____________________________________________________________\n");
    }

    public static LocalDateTime createDateTime(String input) throws DukeException {
        //turn possiblePatterns into two arrays
        //one for date, one for time
        //then combine them in a nested loop

        String[] possibleDates = {
                "d/M/yyyy",
                "d-M-yyyy",
                "d/M/yy",
                "d-M-yy",
                "dMMyyyy",
                "dMMyy",

                "dd/MM/yyyy",
                "dd-MM-yyyy",
                "yyyy-MM-dd",

                "dd/MM/yy",
                "dd-MM-yy",
                "ddMMyyyy",
                "ddMMyy",
        };

        String[] possibleTimes = {
                "HHmm",
                "HH:mm",
                "HH",
                "h:mma",
        };

        for (String datePattern : possibleDates) {
            for (String timePattern : possibleTimes) {
                //check that time pattern comes before date pattern
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(timePattern + " " + datePattern);
                    return LocalDateTime.parse(input, formatter);
                } catch (Exception e) {
                    //do nothing
                }
                //check that time pattern comes after date pattern
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePattern + " " + timePattern);
                    return LocalDateTime.parse(input, formatter);
                } catch (Exception e) {
                    //do nothing
                }
            }
        }
        return null;

    }

    public static Todo createTodo(String description) throws DukeException {
        Todo newTodo = new Todo(description);
        return newTodo;
    }

    public static Deadline createDeadline(String description, String dueDate) throws DukeException {

        LocalDateTime dueDateTime = createDateTime(dueDate);
        if (dueDateTime == null) {
            throw new DukeException("Unknown usage - due date of \"deadline\" is not in a valid date-time format.");
        }

        return new Deadline(description, dueDateTime);
    }

    public static Event createEvent(String description, String startDate, String endDate) throws DukeException {

        LocalDateTime startDateTime = createDateTime(startDate);
        if (startDateTime == null) {
            throw new DukeException("Unknown usage - start date of \"event\" is not in a valid date-time format.");
        }

        LocalDateTime endDateTime = createDateTime(endDate);
        if (endDateTime == null) {
            throw new DukeException("Unknown usage - end date of \"event\" is not in a valid date-time format.");
        }

        if (startDateTime.isAfter(endDateTime)) {
            throw new DukeException("Unknown usage - start date of \"event\" is after end date.");
        }

        return new Event(description, startDateTime, endDateTime);
    }

    public static void main(String[] args) {
        String botName = "KokBot";
        Path path = Paths.get("data", "duke.txt");
        new Duke(path, botName).run();
    }
}
