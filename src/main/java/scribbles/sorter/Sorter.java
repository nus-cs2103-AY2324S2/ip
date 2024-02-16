package scribbles.sorter;

import scribbles.tasklist.TaskList;

public class Sorter {
    public Sorter(){
    }

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
