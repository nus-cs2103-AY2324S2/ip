package duke.commands;

import java.util.Comparator;

import duke.Ui;
import duke.exceptions.DukeCeption;
import duke.tasks.TaskComparatorCreator;
import duke.tasks.TaskList;
import duke.tasks.Task;

public class CommandSort extends Command {
    
    public CommandSort(TaskList taskList, Ui ui) {
        super(taskList, ui);
    }

    @Override
    public void execute(String sortBy) {
        try {
            Comparator<Task> taskComparator;
            switch(sortBy.toUpperCase()) {
                case "DESCRIPTION":
                    taskComparator = TaskComparatorCreator.getDescriptionComparator();
                    break;
                case "TASK":
                    taskComparator = TaskComparatorCreator.getTaskComparator();
                    break;
                case "MARK":
                    taskComparator = TaskComparatorCreator.getMarkComparator();
                    break;
                case "DATE":
                    taskComparator = TaskComparatorCreator.getDateComparator();
                    break;
                default:
                    throw new DukeCeption("Unknown sort by command");
            }
            taskList.sort(taskComparator);
            ui.printSortedMessage(sortBy);

        } catch(DukeCeption e) {
            ui.add(e.getMessage());
        }
    }
}
