package jade.commands;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.IntStream;

import jade.data.TaskList;
import jade.storage.Storage;

/**
 * The <code>ListCommand</code> object represents the command to list all tasks.
 * It is also optional for the user to specify a date for listing all tasks on that date.
 */
public class ListCommand extends Command {
    private static final String EMPTY_RESULT_MSG = "So nice to see that you have no tasks to do for now.";
    private static final String EMPTY_RESULT_MSG_FORMATTED = "I am pleased to tell you that You have no tasks on %s.";
    private static final String RESULT_MSG_FORMATTED = "Here are all the task(s)%s:";
    private static final String LIST_INDEX_FORMATTED = "\n\t%d. %s";
    private final LocalDate selectedDate; // the user specified date for listing all tasks

    /**
     * Class constructor initializing the selected date as null.
     */
    public ListCommand() {
        this.selectedDate = null;
    }

    /**
     * Another Class constructor specifying the selected date.
     */
    public ListCommand(LocalDate selectedDateTime) {
        this.selectedDate = selectedDateTime;
    }

    /**
     * @inheritDoc This implementation prints all tasks with an option to specify a date.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        if (taskList.isEmpty()) {
            // Show user that there are no tasks now
            return EMPTY_RESULT_MSG;
        }
        if (selectedDate != null) {
            return handleListWithDate(taskList); // print tasks on a specific date
        } else {
            return handleListWithoutDate(taskList);
        }
    }

    /**
     * {@inheritDoc}
     * Prints all tasks with an option to specify a date.
     */
    private String handleListWithDate(TaskList taskList) {
        StringBuilder sb = new StringBuilder();
        String dateString = " on " + selectedDate;
        sb.append(String.format(RESULT_MSG_FORMATTED, dateString));
        int[] count = {0}; // to track the number of tasks to be printed
        IntStream.range(0, taskList.size())
                .filter(x -> taskList.get(x).isSameDate(selectedDate))
                .forEach(x -> {
                    count[0]++;
                    sb.append(String.format(LIST_INDEX_FORMATTED, x + 1, taskList.get(x)));
                });
        if (count[0] == 0) {
            return String.format(EMPTY_RESULT_MSG_FORMATTED, selectedDate
                    .format(DateTimeFormatter.ofPattern("MMM d uuuu")));
        }
        return sb.toString();
    }
    private String handleListWithoutDate(TaskList taskList) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(RESULT_MSG_FORMATTED, ""));
        IntStream.range(1, taskList.size() + 1).forEach(x ->
                sb.append(String.format(LIST_INDEX_FORMATTED, x, taskList.get(x - 1))));
        return sb.toString();
    }

    /**
     * {@inheritDoc}
     * Indicates that the program is not exiting.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
