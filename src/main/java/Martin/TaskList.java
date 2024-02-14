package Martin;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTodoList() {
        return this.tasks;
    }

    public String add(Task task) {
        tasks.add(task);
        return "task added: " + task.toString() + "\n";
    }

    public Task remove(int index) {
        return tasks.remove(index);
    }

    public String markAsDone(int index) {
        tasks.get(index).markAsDone();
        return tasks.get(index).toString();
    }

    public String unmarkAsDone(int index) {
        tasks.get(index).unmarkAsDone();
        return tasks.get(index).toString();
    }

    public String printList() {
        // 1-indexed todolist
        String list = "";
        for (int i = 1; i < tasks.size(); i++) {
            list += (i + "." + tasks.get(i) + "\n");
        }
        return list;
    }

    public ArrayList<Task> find(String keyword) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                foundTasks.add(task);
            }
        }
        return foundTasks;
    }
}
