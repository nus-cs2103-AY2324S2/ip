package view;

import model.TaskList;

public class TaskListView extends Ui {
    private final TaskList taskList;
    public TaskListView(TaskList taskList) {
        this.taskList = taskList;
    }

    @Override
    public void display() {
        String list = "";
        for (int i = 0; i < taskList.size(); i++) {
            list += ("    " + (i+1) + ". " + taskList.get(i).toString() + "\n");
        }
        System.out.println(
        "\n=: = = = = = = = = = = = = = = = = = = = = = = = = = = = = :=\n" +
        "::                        Task List                        ::\n" +
        "=: = = = = = = = = = = = = = = = = = = = = = = = = = = = = :=\n" +
        list
        );
    }
}
