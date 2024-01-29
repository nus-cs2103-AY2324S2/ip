import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public boolean addTask(String input, Parser.CommandType commandType, Ui ui) throws TaskException {
        Task task;
        switch (commandType) {
            case TODO:
                task = createTodoTask(input);
                break;
            case DEADLINE:
                task = createDeadlineTask(input);
                break;
            case EVENT:
                task = createEventTask(input);
                break;
            default:
                return false;
        }
        addTask(task);
        ui.printAddedTask(task, tasks.size());
        return true;
    }

    public boolean markOrUnmarkTask(String input, Parser.CommandType commandType, Ui ui) throws TaskException {
        int taskNumber = parseTaskNumber(input);
        Task task = tasks.get(taskNumber - 1);

        if (commandType == Parser.CommandType.MARK) {
            task.check();
            ui.printMarkedTask(task);
        } else {
            task.uncheck();
            ui.printUnmarkedTask(task);
        }
        return true;
    }

    public boolean deleteTask(String input, Ui ui) throws TaskException {
        int taskNumber = parseTaskNumber(input);
        Task removedTask = tasks.remove(taskNumber - 1);
        ui.printDeletedTask(removedTask, tasks.size());
        return true;
    }

    private int parseTaskNumber(String input) throws TaskException {
        try {
            int taskNumber = Integer.parseInt(input.split(" ")[1]);
            if (taskNumber < 1 || taskNumber > tasks.size()) {
                throw new TaskException("Invalid task number. Please check and try again.");
            }
            return taskNumber;
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new TaskException("Invalid task number format. Please enter a valid number.");
        }
    }

    private ToDo createTodoTask(String input) throws TaskException {
        if (input.strip().length() <= 5) {
            throw TaskException.forEmptyTaskDescription();
        }
        return new ToDo(input.substring(5).trim());
    }

    private Deadline createDeadlineTask(String input) throws TaskException {
        if (input.strip().length() <= 9) {
            throw TaskException.forEmptyTaskDescription();
        }
        int byIndex = input.indexOf("/by");
        if (byIndex == -1) {
            throw TaskException.forInvalidTaskFormat("deadline");
        }
        try {
            String description = input.substring(9, byIndex).trim();
            String by = input.substring(byIndex + 4).trim();
            LocalDateTime byTime = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            return new Deadline(description, byTime);
        } catch (DateTimeParseException e) {
            throw new TaskException("Invalid date format. Please use YYYY-MM-DD HHMM, e.g., 2020-12-02 1800.");
        }
    }

    private Event createEventTask(String input) throws TaskException {
        if (input.strip().length() <= 6) {
            throw TaskException.forEmptyTaskDescription();
        }
        int fromIndex = input.indexOf("/from");
        int toIndex = input.indexOf("/to");
        if (fromIndex == -1 || toIndex == -1) {
            throw TaskException.forInvalidTaskFormat("event");
        }
        try {
            String description = input.substring(6, fromIndex).trim();
            String start = input.substring(fromIndex + 6, toIndex).trim();
            String end = input.substring(toIndex + 4).trim();
            LocalDateTime startTime = LocalDateTime.parse(start, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            LocalDateTime endTime = LocalDateTime.parse(end, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            return new Event(description, startTime, endTime);
        } catch (DateTimeParseException e) {
            throw new TaskException("Invalid date format. Please use YYYY-MM-DD HHMM, e.g., 2020-12-02 1800.");
        }
    }

    private void addTask(Task task) {
        tasks.add(task);
    }

    // Other utility methods as needed...
}
