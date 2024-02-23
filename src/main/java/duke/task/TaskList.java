package duke.task;

import duke.storage.Storage;
import duke.ui.Ui;

import java.util.ArrayList;
public class TaskList extends ArrayList<Task> {

    private static ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public static Task getTask(int taskNo) {
        return tasks.get(taskNo);
    }
    public static void addTask(Task t) {
        tasks.add(t);
    }

    public void deleteTask(int taskNo) {
        tasks.remove(taskNo);
    }

    public void markTask(int taskNo) {
        Task t = tasks.get(taskNo);
        t.done();
    }

    public static void unmarkTask(int taskNo) {
        Task t = tasks.get(taskNo);
        t.undone();
    }

    public TaskList filterTasksByKeyword(String keyword) {
        ArrayList<Task> list = new ArrayList<>();
        for (Task t: tasks) {
            if (t.getTaskName().contains(keyword)) {
                list.add(t);
            }
        }
        return new TaskList(list);
    }

    public static ArrayList<Task> findTaskByKeyword(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task t: tasks) {
            if (t.getTaskName().contains(keyword)) {
                matchingTasks.add(t);
            }
        }
        return matchingTasks;
    }


    @Override
    public String toString() {
        String taskList = "";
        if (tasks == null || tasks.isEmpty()) {
            taskList += "----You have no tasks yet.----";
        }
        else {
            for (int i = 0; i < tasks.size(); i++) {
                Task iTask = tasks.get(i);
                taskList += (i + 1) + ". " + iTask.toString() + "\n";
            }
        }
        return taskList;
    }

    public ArrayList<String> getTasksInStoreList() {
        ArrayList<String> taskContent = new ArrayList<>();
        for (Task t : tasks) {
            taskContent.add(t.toStore());
        }
        return taskContent;
    }
    public int getNoOfTasks() {
        return tasks.size();
    }

}