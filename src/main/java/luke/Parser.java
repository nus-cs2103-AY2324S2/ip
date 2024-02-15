package luke;

/**
 * Parses user input and executes corresponding commands.
 */
public class Parser {
    private String[] validCommands;

    private TaskList taskList;

    /**
     * Constructs a Parser object with the specified valid commands and task list.
     *
     * @param validCommands an array of valid commands
     * @param taskList the task list to be operated on
     */
    Parser(String[] validCommands, TaskList taskList) {

        this.validCommands = validCommands;
        this.taskList = taskList;
    }

    /**
     * Checks if the input command is valid.
     *
     * @param input the user input command
     * @throws LukeException if the command is invalid
     */
    protected void isInputValid(String input) throws LukeException {
        String[] inputArr = input.split(" ");
        String command = inputArr[0];
        for (String validCommand: validCommands) {
            if (command.equals(validCommand)) {
                return;
            }
        }
        throw new LukeException(LukeException.ExceptionType.commandInvalid);
    }

    /**
     * Retrieves the command from the input string.
     *
     * @param input the user input command
     * @return the command extracted from the input
     */
    protected String getCommand(String input) {
        String[] inputArr = input.split(" ");
        return inputArr[0];
    }

    /**
     * Lists all tasks in the task list.
     *
     * @param taskList the task list to be listed
     */
    protected void commandList(TaskList taskList) {
        taskList.list();
    }

    /**
     * Marks a task as done in the task list.
     *
     * @param input the user input command
     * @return the task that was marked as done
     * @throws LukeException if the task number is invalid
     */
    protected Task commandMark(String input) throws LukeException {
        String[] inputArr = input.split(" ");
        int taskNo = Integer.parseInt(inputArr[1]) - 1;
        int noTasks = taskList.getNoTasks();
        if (taskNo >= noTasks || taskNo < 0) {
            throw new LukeException(LukeException.ExceptionType.taskNumberInvalid);
        } else {
            taskList.markTask(taskNo);
            return taskList.getTask(taskNo);
        }
    }

    /**
     * Unmarks a task in the task list.
     *
     * @param input the user input command
     * @return the task that was unmarked
     * @throws LukeException if the task number is invalid
     */
    protected Task commandUnmark(String input) throws LukeException {
        String[] inputArr = input.split(" ");
        int taskNo = Integer.parseInt(inputArr[1]) - 1;
        int noTasks = taskList.getNoTasks();
        if (taskNo >= noTasks || taskNo < 0) {
            throw new LukeException(LukeException.ExceptionType.taskNumberInvalid);
        } else {
            taskList.unmarkTask(taskNo);
            return taskList.getMostRecentTask();
        }
    }

    /**
     * Deletes a task from the task list.
     *
     * @param input the user input command
     * @return the task that was deleted
     * @throws LukeException if the task number is invalid
     */
    protected Task commandDelete(String input) throws LukeException {
        String[] inputArr = input.split(" ");
        int taskNo = Integer.parseInt(inputArr[1]) - 1;
        int noTasks = taskList.getNoTasks();
        if (taskNo >= noTasks || taskNo < 0) {
            throw new LukeException(LukeException.ExceptionType.taskNumberInvalid);
        } else {
            Task deletedTask = taskList.deleteEvent(taskNo);
            return deletedTask;
        }
    }

    /**
     * Adds a todo task to the task list.
     *
     * @param input the user input command
     * @return the todo task that was added
     * @throws LukeException if the description is empty
     */
    protected Task commandTodo(String input) throws LukeException {
        String[] inputArr = input.split(" ");
        if (inputArr.length == 1 || inputArr[1].trim().isEmpty()) {
            throw new LukeException(LukeException.ExceptionType.descriptionEmpty);
        }
        taskList.addTodo(inputArr[1].trim());
        return taskList.getMostRecentTask();
    }

    /**
     * Adds a deadline task to the task list.
     *
     * @param input the user input command
     * @return the deadline task that was added
     * @throws LukeException if the description or deadline format is invalid
     */
    protected Task commandDeadline(String input) throws LukeException {
        String[] deadlineSplitBy = input.split("/by");
        String deadlineDescription = deadlineSplitBy[0].substring(9).trim();
        if (deadlineDescription.isEmpty()) {
            throw new LukeException(LukeException.ExceptionType.descriptionEmpty);
        }

        if (deadlineSplitBy.length != 2) {
            throw new LukeException(LukeException.ExceptionType.deadlineWrongFormat);
        }

        String by = deadlineSplitBy[1].trim();

        if (by.isEmpty()) {
            throw new LukeException(LukeException.ExceptionType.deadlineEmpty);
        }
        taskList.addDeadline(deadlineDescription, by);
        return taskList.getMostRecentTask();
    }

    /**
     * Adds an event task to the task list.
     *
     * @param input the user input command
     * @return the event task that was added
     * @throws LukeException if the description or event format is invalid
     */
    protected Task commandEvent(String input) throws LukeException {
        String[] eventSplitFrom = input.split("/from");

        if (eventSplitFrom.length != 2) {
            throw new LukeException(LukeException.ExceptionType.eventWrongFormat);
        }

        String eventDescription = eventSplitFrom[0].substring(6).trim();
        String[] eventSplitTo = eventSplitFrom[1].split("/to");

        if (eventSplitTo.length != 2) {
            throw new LukeException(LukeException.ExceptionType.eventWrongFormat);
        }

        String from = eventSplitTo[0].trim();
        String to = eventSplitTo[1].trim();

        if (eventDescription.isEmpty()) {
            throw new LukeException(LukeException.ExceptionType.descriptionEmpty);
        } else if (from.isEmpty()) {
            throw new LukeException(LukeException.ExceptionType.eventFromEmpty);
        } else if (to.isEmpty()) {
            throw new LukeException(LukeException.ExceptionType.eventToEmpty);
        }
        taskList.addEvent(eventDescription, from, to);
        return taskList.getMostRecentTask();
    }

}
