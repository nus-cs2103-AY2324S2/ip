package jade.commands;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import jade.data.TaskList;
import jade.ui.Ui;
import jade.storage.Storage;

public class ListCommand extends Command {
    private LocalDate selectedDate;

    public ListCommand() { this.selectedDate = null; }

    public ListCommand(LocalDate selectedDate) {
        this.selectedDate = selectedDate;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.isEmpty()) {
            ui.printMessage("\tYou have no tasks now :-|");
            return;
        }
        int count = 0;
        StringBuilder sb = new StringBuilder();
        String dateString = selectedDate==null ? "" : " on " + selectedDate;
        sb.append(String.format("\tHere are the task(s) in your list%s:\n", dateString));
        for (int i = 1; i <= tasks.size(); i++) {
            if (selectedDate != null) { // print tasks on a specific date
                if (tasks.get(i-1).isSameDate(selectedDate)) {
                    sb.append(String.format("\t%d. %s\n", i, tasks.get(i-1)));
                    count++;
                }
            } else { // print all tasks in list
                sb.append(String.format("\t%d. %s\n", i, tasks.get(i-1)));
                count++;
            }
        }
        if (count == 0) {
            ui.printMessage(String.format("\tThere are no tasks on %s", selectedDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))));
        } else {
            ui.printMessage((sb.toString()));
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
