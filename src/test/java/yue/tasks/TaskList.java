package yue.tasks;

import java.util.List;




public class TaskList {
    private List<Task> tasks;

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void delete(int index) {
        tasks.remove(index);

    }

    public List<Task> getAllTasks() {
        return tasks;
    }

    public Task get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= tasks.size()) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        return tasks.get(index);
    }


    public int size() {
        return tasks.size();
    }
}

