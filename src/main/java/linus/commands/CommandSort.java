package linus.commands;

import java.util.Comparator;

import linus.Ui;
import linus.exceptions.LinusCeption;
import linus.exceptions.UnknownCommandException;
import linus.tasks.Task;
import linus.tasks.TaskComparatorCreator;
import linus.tasks.TaskList;

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
                    throw new UnknownCommandException("Unknown sort by command");
            }
            taskList.sort(taskComparator);
            ui.printSortedMessage(sortBy);

        } catch(LinusCeption e) {
            ui.add(e.getMessage());
        }
    }
}
