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

        // Incorrect command syntax handler
        if (splitedTask.length == 1) {
            throw new DukeException("deadline");
        }

        // Split the string with /by
        String[] splitedBy = task.split(" /by ");

        // Incorrect command syntax handler
        if (splitedBy.length != 2 || splitedBy[0].length() <= 9
                || splitedBy[0].substring(9).isBlank()
                || splitedBy[1].isBlank()) {
            throw new DukeException("deadline");
        }

        // Create deadline task
        Task newTask = new Deadline(splitedBy[0].substring(9), splitedBy[1]);
        storage.add(newTask);
        Ui.add(newTask, storage);
        return 2;
    }

    public static int handleTaskEvent(String task, String[] splitedTask, Storage storage) throws DukeException {

        // Incorrect command syntax handler
        if (splitedTask.length == 1) {
            throw new DukeException("event");
        }

        // Split the string with /from
        String[] splitedFrom = task.split(" /from ");

        // Incorrect command syntax handler
        if (splitedFrom.length != 2 || splitedFrom[0].length() <= 6
                || splitedFrom[0].substring(6).isBlank()
                || splitedFrom[1].isBlank()) {
            throw new DukeException("deadline");
        }

        // Split the string with /to
        String[] splitedTo = splitedFrom[1].split(" /to ");

        // Incorrect command syntax handler
        if (splitedTo.length != 2 || splitedTo[0].isBlank() || splitedTo[1].isBlank()) {
            throw new DukeException("deadline");
        }

        // Create event task
        Task newTask = new Event(splitedFrom[0].substring(6), splitedTo[0], splitedTo[1]);
        storage.add(newTask);
        Ui.add(newTask, storage);
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
