package jade.commands;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import jade.data.TaskList;
import jade.ui.Ui;
import jade.storage.Storage;

/**
 * The <code>ListCommand</code> object represents the command to list all tasks.
 * It is also optional for the user to specify a date for listing all tasks on that date.
 */
public class ListCommand extends Command {
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
    public ListCommand(LocalDate selectedDate) {
        this.selectedDate = selectedDate;
    }

    /**
     * @inheriDocs This implementation prints all tasks with an optional specified date.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.isEmpty()) {
            // Show user that there are no tasks now
            ui.printMessage("\tYou have no tasks now :-|");
            return;
        }
        int count = 0; // to track the number of tasks to be printed
        StringBuilder sb = new StringBuilder();
        String dateString = selectedDate == null ? "" : " on " + selectedDate;
        sb.append(String.format("\tHere are the task(s) in your list%s:\n", dateString));
        for (int i = 1; i <= tasks.size(); i++) {
            // case when user has specified a date for listing the tasks
            if (selectedDate != null) { // print tasks on a specific date
                if (tasks.get(i-1).isSameDate(selectedDate)) {
                    sb.append(String.format("\t%d. %s\n", i, tasks.get(i-1)));
                    count++;
                }
            } else {
                // print all tasks in list
                sb.append(String.format("\t%d. %s\n", i, tasks.get(i-1)));
                count++;
            }
        }
        if (selectedDate != null && count == 0) {
            ui.printMessage(String.format("\tThere are no tasks on %s", selectedDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))));
        } else {
            ui.printMessage((sb.toString()));
        }
    }

    /**
     * @inheriDocs The ListCommand does not indicate the exit of the program.
     */
    @Override
    public boolean shouldExit() {
        return false;
    }
}
