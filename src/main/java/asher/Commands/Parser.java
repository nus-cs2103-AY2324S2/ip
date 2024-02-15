package asher.Commands;

import asher.BotException;
import asher.Tasks.TaskList;
import asher.Ui.Ui;

public class Parser {
    private final Ui ui;
    private final TaskList taskList;

    public Parser(Ui ui, TaskList taskList) {
        this.ui = ui;
        this.taskList = taskList;
    }

    public static Command parseCommand(String input) throws BotException {
        String[] word = input.split(" ");
        String inputType = word[0];

        switch (inputType) {
        case "bye":
            return new ExitCommand("bye");
        case "list":
            return new ListCommand("list");
        case "mark":
            return new MarkCommand(input);
        case "unmark":
            return new UnmarkCommand(input);
        case "todo":
            return new ToDoCommand(input);
        case "deadline":
            return new DeadlineCommand(input);
        case "event":
            return new EventCommand(input);
        case "delete":
            return new DeleteCommand(input);
        case "find":
            return new FindCommand(input);
        default:
            throw new BotException("Invalid Command!");
        }
    }

    /*public static Todo createToDoCommand(String input) throws BotException {
        String[] parts = input.split(" ", 2);
        if (parts.length < 2) {
            throw new BotException("Todo command is invalid!");
        }
        String description = parts[1].trim();

        if (description.isEmpty()) {
            throw new BotException("Todo description cannot be empty!");
        }
        return new Todo(description);
    }

    public void parseToDoCommand(String input) throws BotException {
        Todo todo = createToDoCommand(input);
        taskList.addTask(todo);
        ui.showAddTaskMessage(todo);
        int totalTask = taskList.getSize();
        ui.showNumberOfTaskInListMessage(totalTask);
    }

    public static Deadline createDeadlineCommand(String input) throws BotException {
        int split = input.indexOf("/by");
        if (split == -1) {
            throw new BotException("Due date not found!");
        }
        if (split + 4 >= input.length()) {
            throw new BotException("No such deadline!");
        }

        String[] parts = input.substring(9).split("/by", 2);
        String description = parts[0].trim();
        String deadline = parts[1].trim();

        String[] deadlineParts = deadline.split(" ", 2);
        String dueDateInString = deadlineParts[0].trim();
        String dueTimeInString = deadlineParts[1].trim();
        LocalDate dueDate = LocalDate.parse(dueDateInString);
        LocalTime dueTime = LocalTime.parse(dueTimeInString);

        if (description.isEmpty() || deadline.isEmpty()) {
            throw new BotException("Description and deadline cannot be empty!");
        }
        return new Deadline(description, dueDate, dueTime);
    }

    public void parseDeadlineCommand(String input) throws BotException {
        Deadline deadline = createDeadlineCommand(input);
        taskList.addTask(deadline);
        ui.showAddTaskMessage(deadline);
        int totalTask = taskList.getSize();
        ui.showNumberOfTaskInListMessage(totalTask);
    }

    public static Event createEventCommand(String input) throws BotException {
        int split1 = input.indexOf("/from");
        int split2 = input.indexOf("/to");
        if (split1 == -1 || split2 == -1) {
            throw new BotException("Start and End time not found!");
        }

        if (split2 + 4 >= input.length()) {
            throw new BotException("End time not found!");
        }

        String description = input.substring(6, split1).trim();

        String start = input.substring(split1 + 6, split2).trim();
        String[] startParts = start.split(" ", 2);
        String startDateInString = startParts[0].trim();
        LocalDate startDate = LocalDate.parse(startDateInString);
        String startTimeInString = startParts[1].trim();
        LocalTime startTime = LocalTime.parse(startTimeInString);

        String end = input.substring(split2 + 4).trim();
        String[] endParts = end.split(" ", 2);
        String endDateInString = endParts[0].trim();
        LocalDate endDate = LocalDate.parse(endDateInString);
        String endTimeInString = endParts[1].trim();
        LocalTime endTime = LocalTime.parse(endTimeInString);

        if ((endDate.isBefore(startDate)) || ((endDate.isEqual(startDate)) && (endTime.isBefore(startTime)))) {
            throw new BotException("Start Date/Time is after End Date/Time!");
        }

        if (description.isEmpty() || start.isEmpty() || end.isEmpty()) {
            throw new BotException("Description, StartTiming or EndTiming not found!");
        }

        return new Event(description, startDate, startTime, endDate, endTime);
    }

    public void parseEventCommand(String input) throws BotException {
        Event event = createEventCommand(input);
        taskList.addTask(event);
        ui.showAddTaskMessage(event);
        int totalTask = taskList.getSize();
        ui.showNumberOfTaskInListMessage(totalTask);
    }

    private static void parseMarkCommand(String command, TaskList taskList, Ui ui) throws BotException {
        int taskNumber = taskList.getTaskNumber(command);
        if (taskNumber != -1) {
            taskList.getTasks().get(taskNumber).markDone();
            ui.showMarkTaskMessage(taskList.getTasks().get(taskNumber));
        } else {
            throw new BotException("Invalid Task!");
        }
    }

    private static void parseUnmarkCommand(String command, TaskList taskList, Ui ui) throws BotException {
        int taskNumber = taskList.getTaskNumber(command);
        if (taskNumber != -1) {
            taskList.getTasks().get(taskNumber).markUndone();
            ui.showUnmarkTaskMessage(taskList.getTasks().get(taskNumber));
        } else {
            throw new BotException("Invalid Task!");
        }
    }

    private static void parseDeleteCommand(String input, TaskList taskList, Ui ui) throws BotException {
        try {
            String[] words = input.split(" ");
            if (words.length < 2) {
                throw new BotException("Invalid format of input!");
            }
            int taskId = Integer.parseInt(words[1]);
            int taskIndex = taskList.getTaskIndexById(taskId);
            if (taskIndex != -1) {
                Task removedTask = taskList.deleteTask(taskIndex);
                if (removedTask != null) {
                    ui.showRemoveTaskMessage(removedTask);
                    ui.showNumberOfTaskInListMessage(taskList.getTasks().size());
                } else {
                    System.out.println("Task not found!");
                }
                taskList.updateTaskIds();
            } else {
                System.out.println("Task not found!");
            }
        } catch (Exception e) {
            throw new BotException("Task not found!");
        }
    }

    private void parseFindCommand(String input) {
        String keyword = input.substring(5).trim();
        ArrayList<Task> matchingTasks = taskList.searchKeyword(keyword);
        ui.showMatchingTasks(matchingTasks);
    }*/
}
