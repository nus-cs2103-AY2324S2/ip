package view;

import model.TaskList;

/**
 * The {@link TaskListView} class represents a view for displaying the list of tasks.
 */
public class TaskListView extends Ui {
    private final TaskList taskList;

    /**
     * Constructs a {@link TaskListView} with the specified task list.
     *
     * @param taskList The {@link TaskList} containing the tasks to be displayed.
     */
    public TaskListView(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Displays the list of tasks along with an introductory message and a formatted task list.
     */
    @Override
    public void display() {
        String list = "";
        for (int i = 0; i < taskList.size(); i++) {
            list += ("    " + (i + 1) + ". " + taskList.get(i).toString() + "\n");
        }
        System.out.println(
            "\n=: = = = = = = = = = = = = = = = = = = = = = = = = = = = = :=\n"
            + "::                        Task List                        ::\n"
            + "=: = = = = = = = = = = = = = = = = = = = = = = = = = = = = :=\n"
            + list
        );
    }
}
