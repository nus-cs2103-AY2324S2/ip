package duke.action;

<<<<<<< HEAD
import duke.task.Task;
import java.util.List;
=======

import duke.task.Task;

>>>>>>> A-CodingStandard

/**
 * Represents an action to display the list of tasks.
 */
public class MyList implements Action {

    /**
     * The task list to be displayed.
     */
    private final TaskList mylist;

    /**
     * Constructs a MyList action with the specified task list.
     *
     * @param mylist The task list to be displayed.
     */
    public MyList(TaskList mylist) {
        this.mylist = mylist;
    }

    /**
     * Gets the response message containing the list of tasks.
     *
     * @return A string representing the response message.
     */
    @Override
    public String response() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("  Here are the tasks in your list:\n");

        for (int i = 0; i < mylist.size(); i++) {
            Task task = mylist.get(i);
            stringBuilder.append("  ").append(i + 1).append(". ").append(task).append("\n");
        }

        return stringBuilder.toString();
    }
}

