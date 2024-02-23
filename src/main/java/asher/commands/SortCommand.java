package asher.commands;

import asher.tasks.Task;
import asher.tasks.TaskList;
import asher.tasks.Deadline;
import asher.ui.Ui;

import java.util.ArrayList;
import java.util.Collections;

public class SortCommand extends Command {
    public SortCommand(String input) {
        super(input);
    }

    public ArrayList<Deadline> retrieveDeadlineTasks(TaskList taskList) {
        ArrayList<Deadline> deadlineTasks = new ArrayList<>();
        for (Task task : taskList.getTasks()) {
            if (task instanceof Deadline) {
                deadlineTasks.add((Deadline) task);
            }
        }
        return deadlineTasks;
    }

    public ArrayList<Deadline> sortByDeadline(TaskList taskList) {
        ArrayList<Deadline> deadlineTasks = retrieveDeadlineTasks(taskList);

        Collections.sort(deadlineTasks, (d1, d2) -> {
            int compareDates = d1.getDueDate().compareTo(d2.getDueDate());
            int compareTimes = d1.getDueTime().compareTo(d2.getDueTime());
            if (compareDates != 0) {
                return compareDates;
            } else {
                return compareTimes;
            }
        });
        return deadlineTasks;
    }

    @Override
    public String execute(Ui ui, TaskList taskList, Storage storage) {
        ArrayList<Deadline> sortedDeadlineTasks = sortByDeadline(taskList);
        return ui.showSortedDeadlineTasks(sortedDeadlineTasks);

    }
}
