package Duke.Commands;
import Duke.DukeException;
import Duke.Ui;
public abstract class Command {
    public abstract void execute() throws DukeException;
    public abstract boolean isExit();
    public static String getTaskName(String commandWord, String input) throws DukeException {
        if (commandWord.equalsIgnoreCase("todo")) {
            if (input.length() < 6) {
                throw new DukeException("Please enter a task name for Duke.Tasks.Todo!! >.<");
            }
            String taskName = input.substring(5);
            return taskName;
        } else if (commandWord.equalsIgnoreCase("deadline")) {
            int endIndex = input.indexOf("/by");
            if (input.length() < 10 || endIndex == -1) {
                throw new DukeException("Please enter a task name for Duke.Tasks.Deadline!! >.<");
            }
            String taskName = input.substring(9, endIndex);
            return taskName;
        } else if (commandWord.equalsIgnoreCase("event")) {
            int endIndex = input.indexOf("/from");
            if (input.length() < 7 || endIndex == -1) {
                throw new DukeException("Please enter a task name for Duke.Tasks.Event!! >.<");
            }
            String taskName = input.substring(6, endIndex);
            return taskName;
        } else {
            throw new DukeException("Please use one of the 3 tasks!! >.<");
        }
    }
    public static String getStartDate(String input) throws DukeException {
        int startIndex = input.indexOf("/from") + 6;
        int endIndex = input.indexOf("/to");

        if (startIndex == input.length() || startIndex == 5) {
            throw new DukeException("Please use the keyword /from for your event! >.<");
        } else if (startIndex > input.length()) {
            throw new DukeException("Please enter a start date for your event! >.<");
        } else if (endIndex == -1) {
            throw new DukeException("Please use the keyword /to for your event! >.<");
        } else if (startIndex > endIndex) {
            throw new DukeException("Please enter an end date for your event! >.<");
        }
        return input.substring(startIndex, endIndex);
    }
    public static String getEndDate(String task, String input) throws DukeException {
        String startWord = task.equalsIgnoreCase("deadline") ? "/by" : "/to";
        int startIndex = input.indexOf(startWord) + startWord.length() + 1;

        if (startIndex > input.length()) {
            throw new DukeException(String.format("Please enter an end date for your %s!", task));
        }
        return input.substring(startIndex);
    }
}
