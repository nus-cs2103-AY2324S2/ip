package duke;
import java.util.ArrayList;

import duke.tasks.Task;

public class TaskList {
    private static ArrayList<Task> taskList = new ArrayList<Task>();
    String indent = "    ";

    public void addTask(Task task)  {
        taskList.add(task);
    }

    public String showNewest() {
        int length = taskList.size();
        Task task = taskList.get(length - 1);
        return task.getName();
    }

    public int getLength() {
        return taskList.size();
    }

    public Task getTask(int idx) {
        return taskList.get(idx);
    }

    public String showList() {
        Task task;
        String status;
        String finalOutput = new String("");

        for (int i = 0; i < taskList.size(); i++) {
            task = taskList.get(i);
            status = task.checkStatus();
            finalOutput = finalOutput + indent + Integer.toString(i + 1) + "." + status + "\n";
        }
        return finalOutput;
    }

    public void markTask(int idx) {
        Task task = taskList.get(idx - 1);
        task.completeTask();
    }

    public void unmarkTask(int idx) {
        Task task = taskList.get(idx - 1);
        task.revertStatus();
    }

    public void removeTask(int idx) {
        taskList.remove(idx);
    }


}
