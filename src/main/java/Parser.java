public class Parser {
    private String[] validCommands;

    private TaskList taskList;
    Parser(String[] validCommands, TaskList taskList) {

        this.validCommands = validCommands;
        this.taskList = taskList;
    }

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

    protected String getCommand(String input) {
        String[] inputArr = input.split(" ");
        return inputArr[0];
    }

    protected void commandList(TaskList taskList) {
        taskList.list();
    }

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

    protected Task commandTodo(String input) throws LukeException {
        String[] inputArr = input.split(" ");
        if (inputArr.length == 1 || inputArr[1].trim().isEmpty()) {
            throw new LukeException(LukeException.ExceptionType.descriptionEmpty);
        }
        taskList.addTodo(inputArr[1].trim());
        return taskList.getMostRecentTask();
    }

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

    protected Task commandEvent(String input) throws LukeException {
        String[] eventSplitFrom = input.split("/from");

        if (eventSplitFrom.length != 2) {
            throw new LukeException(LukeException.ExceptionType.eventWrongFormat);
        }

        String eventDescription = eventSplitFrom[0].substring(6);
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
