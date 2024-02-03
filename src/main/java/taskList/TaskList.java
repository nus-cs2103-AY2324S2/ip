package taskList;

import excceptions.WeiException;
import tasks.Task;

import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public int getSize() {
        return tasks.size();
    }

    public Task getTask(int order) {
        return tasks.get(order);
    }

    public String list() {
        String wholeList = "";
        for (int i = 0; i < tasks.size(); i++) {
            int order = i + 1;
            Task task = tasks.get(i);
            String text = task.stringify();
            wholeList += order + ". " + text + "\n";
        }
        return wholeList;
    }
    public String add(Task task) {
        tasks.add(task);
        return task.stringify();
    }

    public String mark(int order) throws WeiException {
        Task task = tasks.get(order);
        task.setStatus(true);
        return task.stringify();
    }

    public String unmark(int order) throws WeiException {
        Task task = tasks.get(order);
        task.setStatus(false);
        return task.stringify();
    }

    public String delete(int order) {
        Task task = tasks.get(order);
        tasks.remove(order - 1);
        return task.stringify();
    }

    /**
     * Looks for tasks that matches the keyword.
     *
     * @param keyword Keyword.
     * @return List of tasks that matches the keyword.
     */
    public String find(String keyword) {
        String result = "";
        int order = 1;
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.search(keyword)) {
                String text = task.stringify();
                result += order + ". " + text + "\n";
                order++;
            }
        }
        return result;
    }
}
