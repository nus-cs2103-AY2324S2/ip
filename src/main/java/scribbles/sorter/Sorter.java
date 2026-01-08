package scribbles.sorter;

import scribbles.tasklist.TaskList;

/**
 * This class deals with the sorting of the task list according to the given order.
 */
public class Sorter {

    /**
     * Constructs a new instance of a Sorter.
     */
    public Sorter(){
    }

    /**
     * Sorts the task list according to the given order.
     *
     * @param taskList The task list to sort.
     * @param order The order in which to sort by.
     * @throws IllegalArgumentException If the given order is not an accepted argument.
     */
    public void sortList(TaskList taskList, String order) throws IllegalArgumentException {
        switch(order) {
        case "description":
            taskList.sortByDescription();
            break;
        case "type":
            taskList.sortByType();
            break;
        case "completed first":
            taskList.sortByCompletedFirst();
            break;
        case "incompleted first":
            taskList.sortByIncompleteFirst();
            break;
        case "date":
            taskList.sortByDate();
            break;
        default:
            throw new IllegalArgumentException();
        }
    }
}
