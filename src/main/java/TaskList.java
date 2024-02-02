import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TaskManager {

    // Return 0 to leave
    // Return 1 when task performed without changing list
    // Return 2 when task performed with changing list
    // Return 3 when task fail and exception is thrown
    public static int handleTask(String task, Storage storage) {

        String[] splitedTask = task.split(" ");
        Duke.TaskType taskType = Duke.TaskType.UNKNOWN;

        // Empty command handler
        try {
            taskType = Duke.TaskType.valueOf(splitedTask[0].toUpperCase());
        } catch (ArrayIndexOutOfBoundsException e) {
            Ui.print("Sorry, we are not sync enough to communicate through empty command.");
            return 3;
        } catch (IllegalArgumentException e) {
            Ui.print("Syntax error, unknown command.");
            return 3;
        }

        try {
            switch (taskType) {
                case BYE: {
                    return handleTaskBye(splitedTask);
                }
                case LIST: {
                    return handleTaskList(splitedTask, storage);
                }
                case MARK: {
                    return handleTaskMark(splitedTask, storage);
                }
                case UNMARK: {
                    return handleTaskUnmark(splitedTask, storage);
                }
                case TODO: {
                    return handleTaskTodo(task, splitedTask, storage);
                }
                case DEADLINE: {
                    return handleTaskDeadline(task, splitedTask, storage);
                }
                case EVENT: {
                    return handleTaskEvent(task, splitedTask, storage);
                }
                case DELETE: {
                    return handleTaskDelete(splitedTask, storage);
                }
                default: {
                    Ui.print("Syntax error, unknown command.");
                    return 3;
                }
            }
        } catch (DukeException e) {
            Ui.print(e.handleTaskError());
            return 3;
        }
    }

    public static int handleTaskBye(String[] splitedTask) throws DukeException {
        // Incorrect command syntax handler
        if (splitedTask.length > 1) {
            throw new DukeException("bye");
        }
        return 0;
    }

    public static int handleTaskList(String[] splitedTask, Storage storage) throws DukeException {
        // Incorrect command syntax handler
        if (splitedTask.length > 1) {
            throw new DukeException("list");
        }
        Ui.list(storage);
        return 1;
    }

    public static int handleTaskMark(String[] splitedTask, Storage storage) throws DukeException {

        // Incorrect command syntax handler
        if (splitedTask.length != 2) {
            throw new DukeException("mark");
        }

        int index = 0;
        try {
            index = Integer.parseInt(splitedTask[1]) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("mark");
        }

        // Index out of bound handler
        if (index >= storage.getItems().size()) {
            Ui.print("The index of task cannot be larger than number of task.");
            return 3;
        } else if (index < 0) {
            Ui.print("The index of task must be positive integer.");
            return 3;
        }

        storage.markDone(index);
        Ui.mark(storage.getItem(index));
        return 2;
    }

    public static int handleTaskUnmark(String[] splitedTask, Storage storage) throws DukeException {

        // Incorrect command syntax handler
        if (splitedTask.length != 2) {
            throw new DukeException("unmark");
        }
        int index = 0;
        try {
            index = Integer.parseInt(splitedTask[1]) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("unmark");
        }

        // Index out of bound handler
        if (index >= storage.getItems().size()) {
            Ui.print("The index of task cannot be larger than number of task.");
            return 3;
        } else if (index < 0) {
            Ui.print("The index of task must be positive integer.");
            return 3;
        }

        storage.unmarkDone(index);
        Ui.mark(storage.getItem(index));
        return 2;
    }

    public static int handleTaskTodo(String task, String[] splitedTask, Storage storage) throws DukeException {

        // Incorrect command syntax handler
        if (splitedTask.length == 1) {
            throw new DukeException("todo");
        }

        Task newTask = new Todo(task.substring(5));
        storage.add(newTask);
        Ui.add(newTask, storage);
        return 2;
    }

    public static int handleTaskDeadline(String task, String[] splitedTask, Storage storage) throws DukeException {

        // Incorrect syntax: No content, no deadline
        if (splitedTask.length == 1) {
            throw new DukeException("deadline");
        }

        // Remove the "deadline " keyword
        task = task.substring(9);

        // Split the string with /by
        String[] splitedBy = task.split(" /by ");

        // Incorrect syntax: The remaining string doesn't separate to content and deadline
        if (splitedBy.length != 2) {
            throw new DukeException("deadline");
        }

        // Get content and deadline
        String content = splitedBy[0].trim();
        String deadline = splitedBy[1].trim();

        // Verify content and deadline (cannot be blank)
        if (content.isBlank() || deadline.isBlank()) {
            throw new DukeException("deadline");
        }

        // Verify deadline (must be in format yyyy-MM-dd HH:mm or yyyy-mm-dd)
        String[] splitedDateTime = deadline.split(" ");
        if (splitedDateTime.length > 2) {
            throw new DukeException("deadline");
        }

        // Create deadline task
        try {
            if (splitedDateTime.length == 2) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime parsedDateTime = LocalDateTime.parse(deadline, formatter);
                Task newTask = new Deadline(content, parsedDateTime);
                storage.add(newTask);
                Ui.add(newTask, storage);
            } else {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate parsedDate = LocalDate.parse(deadline, formatter);
                Task newTask = new Deadline(content, parsedDate);
                storage.add(newTask);
                Ui.add(newTask, storage);
            }
        } catch (Exception e) {
            throw new DukeException("deadline");
        }

        return 2;
    }

    public static int handleTaskEvent(String task, String[] splitedTask, Storage storage) throws DukeException {

        // Incorrect syntax: No content, no from, no to
        if (splitedTask.length == 1) {
            throw new DukeException("event");
        }

        // Remove the "event " keyword
        task = task.substring(6);

        // Split the string with /from
        String[] splitedFrom = task.split(" /from ");

        // Incorrect syntax: The remaining string doesn't separate to content, from and to
        if (splitedFrom.length != 2) {
            throw new DukeException("event");
        }

        // Get content, from and to
        String content = splitedFrom[0].trim();
        String fromTo = splitedFrom[1].trim();

        // Split the string with /to
        String[] splitedTo = fromTo.split(" /to ");

        // Incorrect syntax: The remaining string doesn't separate to from and to
        if (splitedTo.length != 2) {
            throw new DukeException("event");
        }

        // Get from and to
        String from = splitedTo[0].trim();
        String to = splitedTo[1].trim();

        // Verify content, from, and to (cannot be blank)
        if (content.isBlank() || from.isBlank() || to.isBlank()) {
            throw new DukeException("event");
        }

        // Verify from (must be in format yyyy-MM-dd HH:mm or yyyy-mm-dd)
        String[] splitedFromDateTime = from.split(" ");
        if (splitedFromDateTime.length > 2) {
            throw new DukeException("deadline");
        }

        // Verify to (must be in format yyyy-MM-dd HH:mm or yyyy-mm-dd)
        String[] splitedToDateTime = to.split(" ");
        if (splitedToDateTime.length > 2) {
            throw new DukeException("deadline");
        }

        // Create event task
        try {
            if (splitedFromDateTime.length == 2 && splitedToDateTime.length == 2) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime parsedFromDateTime = LocalDateTime.parse(from, formatter);
                LocalDateTime parsedToDateTime = LocalDateTime.parse(to, formatter);
                Task newTask = new Event(content, parsedFromDateTime, parsedToDateTime);
                storage.add(newTask);
                Ui.add(newTask, storage);
            } else if (splitedFromDateTime.length == 2 && splitedToDateTime.length == 1) {
                DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDateTime parsedFromDateTime = LocalDateTime.parse(from, formatter1);
                LocalDate parsedToDate = LocalDate.parse(to, formatter2);
                Task newTask = new Event(content, parsedFromDateTime, parsedToDate);
                storage.add(newTask);
                Ui.add(newTask, storage);
            } else if (splitedFromDateTime.length == 1 && splitedToDateTime.length == 2) {
                DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate parsedFromDate = LocalDate.parse(from, formatter2);
                LocalDateTime parsedToDateTime = LocalDateTime.parse(to, formatter1);
                Task newTask = new Event(content, parsedFromDate, parsedToDateTime);
                storage.add(newTask);
                Ui.add(newTask, storage);
            } else {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate parsedFromDate = LocalDate.parse(from, formatter);
                LocalDate parsedToDate = LocalDate.parse(to, formatter);
                Task newTask = new Event(content, parsedFromDate, parsedToDate);
                storage.add(newTask);
                Ui.add(newTask, storage);
            }
        } catch (Exception e) {
            throw new DukeException("event");
        }

        return 2;
    }

    public static int handleTaskDelete(String[] splitedTask, Storage storage) throws DukeException {

        // Incorrect command syntax handler
        if (splitedTask.length != 2) {
            throw new DukeException("delete");
        }
        int index = 0;
        try {
            index = Integer.parseInt(splitedTask[1]) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("delete");
        }

        // Index out of bound handler
        if (index >= storage.getItems().size()) {
            Ui.print("The index of task cannot be larger than number of task.");
            return 3;
        } else if (index < 0) {
            Ui.print("The index of task must be positive integer.");
            return 3;
        }

        Ui.delete(storage.getItem(index), storage);
        storage.delete(index);
        return 2;
    }
}
