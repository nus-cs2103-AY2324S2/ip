public class Parser {

    public enum TaskType {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, UNKNOWN
    }
    public static Command parse(String fullCommand) throws DukeException {
        String[] splitedTask = fullCommand.split(" ");
        Parser.TaskType taskType = Parser.TaskType.UNKNOWN;

        // Empty command handler
        try {
            taskType = Parser.TaskType.valueOf(splitedTask[0].toUpperCase());
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Sorry, we are not sync enough to communicate through empty command.");
        } catch (IllegalArgumentException e) {
            throw new DukeException("Syntax error, unknown command.");
        }

        switch (taskType) {
            case BYE: {
                return handleTaskBye(splitedTask);
            }
            case LIST: {
                return handleTaskList(splitedTask);
            }
            case MARK: {
                return handleTaskMark(splitedTask);
            }
            case UNMARK: {
                return handleTaskUnmark(splitedTask);
            }
            case TODO: {
                return handleTaskTodo(fullCommand, splitedTask);
            }
            case DEADLINE: {
                return handleTaskDeadline(fullCommand, splitedTask);
            }
            case EVENT: {
                return handleTaskEvent(fullCommand, splitedTask);
            }
            case DELETE: {
                return handleTaskDelete(splitedTask);
            }
            default: {
                throw new DukeException("Syntax error, unknown command.");
            }
        }
    }

    public static Command handleTaskBye(String[] splitedTask) throws DukeException {
        // Incorrect command syntax handler
        if (splitedTask.length > 1) {
            throw new DukeException("Syntax of bye: bye");
        }
        return new ExitCommand();
    }

    public static Command handleTaskList(String[] splitedTask) throws DukeException {
        // Incorrect command syntax handler
        if (splitedTask.length > 1) {
            throw new DukeException("Syntax of list: list");
        }
        return new ListCommand();
    }

    public static Command handleTaskMark(String[] splitedTask) throws DukeException {

        // Incorrect command syntax handler
        if (splitedTask.length != 2) {
            throw new DukeException("Syntax of mark: mark {index of task (integer)}\n"
                    + "E.g. mark 1");
        }

        int index = 0;
        try {
            index = Integer.parseInt(splitedTask[1]) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("Syntax of mark: mark {index of task (integer)}\n"
                    + "E.g. mark 1");
        }

        return new EditCommand(true, index);
    }

    public static Command handleTaskUnmark(String[] splitedTask) throws DukeException {

        // Incorrect command syntax handler
        if (splitedTask.length != 2) {
            throw new DukeException("Syntax of unmark: unmark {index of task (integer)}\n"
                    + "E.g. unmark 1");
        }
        int index = 0;
        try {
            index = Integer.parseInt(splitedTask[1]) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("Syntax of unmark: unmark {index of task (integer)}\n"
                    + "E.g. unmark 1");
        }

        return new EditCommand(false, index);
    }

    public static Command handleTaskTodo(String task, String[] splitedTask) throws DukeException {

        // Incorrect command syntax handler
        if (splitedTask.length == 1) {
            throw new DukeException("Syntax of todo: todo {task description}\n"
                    + "E.g. todo say hi to neighbour");
        }

        return new AddCommand(task);
    }

    public static Command handleTaskDeadline(String task, String[] splitedTask) throws DukeException {

        // Incorrect syntax: No content, no deadline
        if (splitedTask.length == 1) {
            throw new DukeException("Syntax of deadline: deadline {task description} /by ({yyyy-MM-dd HH:mm} or {yyyy-MM-dd})\n"
                    + "E.g. deadline breakfast /by 2022-12-31 15:00");
        }

        // Remove the "deadline " keyword
        task = task.substring(9);

        // Split the string with /by
        String[] splitedBy = task.split(" /by ");

        // Incorrect syntax: The remaining string doesn't separate to content and deadline
        if (splitedBy.length != 2) {
            throw new DukeException("Syntax of deadline: deadline {task description} /by ({yyyy-MM-dd HH:mm} or {yyyy-MM-dd})\n"
                    + "E.g. deadline breakfast /by 2022-12-31 15:00");
        }

        // Get content and deadline
        String content = splitedBy[0].trim();
        String deadline = splitedBy[1].trim();

        // Verify content and deadline (cannot be blank)
        if (content.isBlank() || deadline.isBlank()) {
            throw new DukeException("Syntax of deadline: deadline {task description} /by ({yyyy-MM-dd HH:mm} or {yyyy-MM-dd})\n"
                    + "E.g. deadline breakfast /by 2022-12-31 15:00");
        }

        // Verify deadline (must be in format yyyy-MM-dd HH:mm or yyyy-mm-dd)
        String[] splitedDateTime = deadline.split(" ");
        if (splitedDateTime.length > 2) {
            throw new DukeException("Syntax of deadline: deadline {task description} /by ({yyyy-MM-dd HH:mm} or {yyyy-MM-dd})\n"
                    + "E.g. deadline breakfast /by 2022-12-31 15:00");
        }

        return new AddCommand(splitedDateTime, deadline, content);
    }

    public static Command handleTaskEvent(String task, String[] splitedTask) throws DukeException {

        // Incorrect syntax: No content, no from, no to
        if (splitedTask.length == 1) {
            throw new DukeException("Syntax of event: event {task description} /from ({yyyy-MM-dd HH:mm} or {yyyy-MM-dd}) "
                    + "/to ({yyyy-MM-dd HH:mm} or {yyyy-MM-dd})\n"
                    + "E.g. event exam /from 2022-12-31 15:00 /to 17:00");
        }

        // Remove the "event " keyword
        task = task.substring(6);

        // Split the string with /from
        String[] splitedFrom = task.split(" /from ");

        // Incorrect syntax: The remaining string doesn't separate to content, from and to
        if (splitedFrom.length != 2) {
            throw new DukeException("Syntax of event: event {task description} /from ({yyyy-MM-dd HH:mm} or {yyyy-MM-dd}) "
                    + "/to ({yyyy-MM-dd HH:mm} or {yyyy-MM-dd})\n"
                    + "E.g. event exam /from 2022-12-31 15:00 /to 17:00");
        }

        // Get content, from and to
        String content = splitedFrom[0].trim();
        String fromTo = splitedFrom[1].trim();

        // Split the string with /to
        String[] splitedTo = fromTo.split(" /to ");

        // Incorrect syntax: The remaining string doesn't separate to from and to
        if (splitedTo.length != 2) {
            throw new DukeException("Syntax of event: event {task description} /from ({yyyy-MM-dd HH:mm} or {yyyy-MM-dd}) "
                    + "/to ({yyyy-MM-dd HH:mm} or {yyyy-MM-dd})\n"
                    + "E.g. event exam /from 2022-12-31 15:00 /to 17:00");
        }

        // Get from and to
        String from = splitedTo[0].trim();
        String to = splitedTo[1].trim();

        // Verify content, from, and to (cannot be blank)
        if (content.isBlank() || from.isBlank() || to.isBlank()) {
            throw new DukeException("Syntax of event: event {task description} /from ({yyyy-MM-dd HH:mm} or {yyyy-MM-dd}) "
                    + "/to ({yyyy-MM-dd HH:mm} or {yyyy-MM-dd})\n"
                    + "E.g. event exam /from 2022-12-31 15:00 /to 17:00");
        }

        // Verify from (must be in format yyyy-MM-dd HH:mm or yyyy-mm-dd)
        String[] splitedFromDateTime = from.split(" ");
        if (splitedFromDateTime.length > 2) {
            throw new DukeException("Syntax of event: event {task description} /from ({yyyy-MM-dd HH:mm} or {yyyy-MM-dd}) "
                    + "/to ({yyyy-MM-dd HH:mm} or {yyyy-MM-dd})\n"
                    + "E.g. event exam /from 2022-12-31 15:00 /to 17:00");
        }

        // Verify to (must be in format yyyy-MM-dd HH:mm or yyyy-mm-dd)
        String[] splitedToDateTime = to.split(" ");
        if (splitedToDateTime.length > 2) {
            throw new DukeException("Syntax of event: event {task description} /from ({yyyy-MM-dd HH:mm} or {yyyy-MM-dd}) "
                    + "/to ({yyyy-MM-dd HH:mm} or {yyyy-MM-dd})\n"
                    + "E.g. event exam /from 2022-12-31 15:00 /to 17:00");
        }

        return new AddCommand(splitedFromDateTime, splitedToDateTime, from, to, content);
    }

    public static Command handleTaskDelete(String[] splitedTask) throws DukeException {

        // Incorrect command syntax handler
        if (splitedTask.length != 2) {
            throw new DukeException("Syntax of delete: delete {index of task (integer)}\n"
                    + "E.g. delete 1");
        }
        int index = 0;
        try {
            index = Integer.parseInt(splitedTask[1]) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("Syntax of delete: delete {index of task (integer)}\n"
                    + "E.g. delete 1");
        }

        return new DeleteCommand(index);
    }
}
