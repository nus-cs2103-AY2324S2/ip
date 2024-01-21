package view;

import model.Task;

import java.util.ArrayList;

public class TaskListView extends Ui {
    private final ArrayList<Task> taskList;
    public TaskListView(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    @Override
    public void display() {
        String list = "";
        for (int i = 0; i < taskList.size(); i++) {
            list += ("    " + (i+1) + ". " + taskList.get(i).isDoneString() + taskList.get(i).toString() + "\n");
        }
        System.out.println(
        "╔═══════════════════════════════════════════════════════════╗\n" +
        "║                         Task List                         ║\n" +
        "╚═══════════════════════════════════════════════════════════╝\n" +
        list
        );
    }
}
