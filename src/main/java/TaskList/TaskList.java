package TaskList;

import SnomTask.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> task_lst;


    private TaskList() {
        this.task_lst = new ArrayList<>();
    }

    public static TaskList makeTaskList() {
        return new TaskList();
    }

    public Task getTask(int pos) {
        // anth that involves indices can throw index out of bounds exception
        return this.task_lst.get(pos);
    }

    public void markTask(int pos) {
        this.task_lst.get(pos).doTask();
    }

    public void unmarkTask(int pos) {
        this.task_lst.get(pos).undoTask();
    }

    public void deleteTask(int pos) {

        this.task_lst.remove(pos);
    }
}
