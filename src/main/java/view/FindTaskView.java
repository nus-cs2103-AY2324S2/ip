package view;

import model.TaskList;

/**
 * The {@link FindTaskView} class represents a view for displaying the list of filtered tasks.
 */
public class FindTaskView extends Ui {
    private final TaskList taskList;

    /**
     * Constructs a {@link FindTaskView} with the specified task list.
     *
     * @param taskList The {@link TaskList} containing the filtered tasks to be displayed.
     */
    public FindTaskView(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Displays the list of filtered tasks along with an introductory message and a formatted task list.
     */
    @Override
    public void display() {
        String list = "";
        for (int i = 0; i < taskList.size(); i++) {
            list += ("    " + (i + 1) + ". " + taskList.get(i).toString() + "\n");
        }
        System.out.println(
                "Here are the matching tasks in your list:\n"
                + "=: = = = = = = = = = = = = = = = = = = = = = = = = = = = = :=\n"
                + "::                        Task List                        ::\n"
                + "=: = = = = = = = = = = = = = = = = = = = = = = = = = = = = :=\n"
                + list
        );
    }
}
