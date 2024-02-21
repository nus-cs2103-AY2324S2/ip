package knight;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks in the Knight program.
 */
public class TaskList {
    private List<Task> tasks = new ArrayList<>();

    public TaskList() {
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Execute a command on the task list.
     *
     * @param commandType The type of command to execute.
     * @param message     The full command message.
     */
    void executeCommand(Command commandType, String message) {
        if (commandType == Command.LIST) {
            Ui.speak(executeListCommand());
        } else if (commandType == Command.SAVE) {
            Ui.speak(executeSaveCommand());
        } else if (commandType == Command.MARK) {
            Ui.speak(executeMarkCommand(message));
        } else if (commandType == Command.UNMARK) {
            Ui.speak(executeUnmarkCommand(message));
        } else if (commandType == Command.DELETE) {
            Ui.speak(executeDeleteCommand(message));
        } else if (commandType == Command.TODO) {
            Ui.speak(executeTodoCommand(message));
        } else if (commandType == Command.DEADLINE) {
            Ui.speak(executeDeadlineCommand(message));
        } else if (commandType == Command.EVENT) {
            Ui.speak(executeEventCommand(message));
        } else if (commandType == Command.FIND) {
            Ui.speak(executeFindCommand(message));
        } else if (commandType == Command.UPDATE) {
            Ui.speak(executeUpdateCommand(message));
        }
    }

    String executeCommandReturnString(Command commandType, String message) {
        if (commandType == Command.LIST) {
            return executeListCommand();
        } else if (commandType == Command.SAVE) {
            return executeSaveCommand();
        } else if (commandType == Command.MARK) {
            return executeMarkCommand(message);
        } else if (commandType == Command.UNMARK) {
            return executeUnmarkCommand(message);
        } else if (commandType == Command.DELETE) {
            return executeDeleteCommand(message);
        } else if (commandType == Command.TODO) {
            return executeTodoCommand(message);
        } else if (commandType == Command.DEADLINE) {
            return executeDeadlineCommand(message);
        } else if (commandType == Command.EVENT) {
            return executeEventCommand(message);
        } else if (commandType == Command.FIND) {
            return executeFindCommand(message);
        } else if (commandType == Command.UPDATE) {
            return executeUpdateCommand(message);
        }
        return "Thou hast reached a place previously deemed unreachable. How didst thou arrive here?";
    }

    /**
     * Execute a command on the task list without speaking.
     *
     * @param commandType The type of command to execute.
     * @param message     The full command message.
     */
    void executeCommandSilently(Command commandType, String message) {
        if (commandType == Command.MARK) {
            executeMarkCommand(message);
        } else if (commandType == Command.UNMARK) {
            executeUnmarkCommand(message);
        } else if (commandType == Command.TODO) {
            executeTodoCommand(message);
        } else if (commandType == Command.DEADLINE) {
            executeDeadlineCommand(message);
        } else if (commandType == Command.EVENT) {
            executeEventCommand(message);
        }
    }

    private String executeListCommand() {
        if (tasks.isEmpty()) {
            return "Your Excellency, thy list remaineth free of tasks at this present moment.";
        } else {
            return "Behold, the duties thou hast assigned:\n" + this.toString();
        }
    }

    private String executeSaveCommand() {
        Storage.writeToFile(this);
        return "Thy list hath been saved to thy scrolls of history.";
    }

    private String executeMarkCommand(String message) {
        int index = Integer.parseInt(message.substring(5));
        Task task;

        try {
            task = tasks.get(index - 1);
        } catch (IndexOutOfBoundsException e) {
            return "I regret to inform thee, Your Excellency, "
                    + "that thou lackest a task bearing this index in thy list.";
        }

        task.mark();
        return "Thy task hath been marked as done.\n" + task;
    }

    private String executeUnmarkCommand(String message) {
        int index = Integer.parseInt(message.substring(7));
        Task task;

        try {
            task = tasks.get(index - 1);
        } catch (IndexOutOfBoundsException e) {
            return "I regret to inform thee, Your Excellency, "
                    + "that thou lackest a task bearing this index in thy list.";
        }

        task.unmark();
        return "Thy task hath been unmarked.\n" + task;
    }

    private String executeDeleteCommand(String message) {
        int index = Integer.parseInt(message.substring(7));
        Task task;

        try {
            task = tasks.get(index - 1);
        } catch (IndexOutOfBoundsException e) {
            return "I regret to inform thee, Your Excellency,"
                    + "that thou lackest a task bearing this index in thy list.";
        }

        tasks.remove(index - 1);
        return "Thy task hath been removed from thy list.\n" + task;
    }

    private String executeTodoCommand(String message) {
        Task task = new ToDo(message.substring(5));
        tasks.add(task);
        return "Understood. This task hath been added to thy list:\n" + task;
    }

    private String executeDeadlineCommand(String message) {
        String[] params = message.split(" /");
        Task task;
        try {
            task = new Deadline(params[0].substring(9), params[1].substring(3));
        } catch (DateTimeParseException e) {
            return "Your Excellency, I struggle to understand thee. To specify a date, use format\n"
                    + "yyyy-mm-dd";
        }
        tasks.add(task);
        return "Understood. This task hath been added to thy list:\n" + task;
    }

    private String executeEventCommand(String message) {
        String[] params = message.split(" /");
        Task task;
        try {
            task = new Event(params[0].substring(6),
                    params[1].substring(5),
                    params[2].substring(3));
        } catch (DateTimeParseException e) {
            return "Your Excellency, I struggle to understand thee. To specify a date, use format\n"
                    + "yyyy-mm-dd";
        }
        tasks.add(task);
        return "Understood. This task hath been added to thy list:\n" + task;
    }

    private String executeFindCommand(String message) {
        String keyword = message.substring(5);
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).matches(keyword)) {
                output.append( + (i + 1) + ". " + tasks.get(i));
            }
        }
        String outputString = output.toString();
        if (outputString.equals("")) {
            return "I regret to inform thee, Your Excellency, "
                    + "that no tasks bearing this keyword exist in thy list.";
        } else {
            return "Behold, the tasks that match thy keyword:\n" + outputString;
        }
    }

    private String executeUpdateCommand(String message) {
        String[] params = message.split(" ");
        int index = Integer.parseInt(params[1]) - 1;
        Task task;
        try {
            task = tasks.get(index);
        } catch (IndexOutOfBoundsException e) {
            return "I regret to inform thee, Your Excellency, "
                    + "that thou lackest a task bearing this index in thy list.";
        }
        String oldTaskMessage = task.toString();
        String updateMessage = message.substring(8 + params[1].length());
        try {
            task.update(updateMessage);
        } catch (NonstandardCommandException e) {
            return e.getMessage();
        } catch (DateTimeParseException e) {
            return "Your Excellency, I struggle to understand thee. To specify a date, use format\n"
                    + "yyyy-mm-dd";
        }
        return "Thy task hath been updated.\n   " + oldTaskMessage + "\n-->" + task;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            if (i != tasks.size() - 1) {
                output.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
            } else {
                output.append(i + 1).append(". ").append(tasks.get(i));
            }
        }
        return output.toString();
    }

    /**
     * Get the commands representation of the task list.
     *
     * @return The commands representation of the task list.
     */
    String getCommands() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (i != tasks.size() - 1) {
                output.append(task.getCommand()).append("\n");
                if (task.isDone) {
                    output.append("mark ").append(i + 1).append("\n");
                }
            } else {
                output.append(task.getCommand());
                if (task.isDone) {
                    output.append("\nmark ").append(i + 1);
                }
            }
        }
        return output.toString();
    }
}
