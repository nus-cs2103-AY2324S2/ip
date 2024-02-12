package duke.task;

import duke.task.Task;

import java.util.List;
import java.util.ArrayList;
public class TaskList {

    private List<Task> todo;
    public TaskList(List<Task> todo) {
        this.todo = todo;
    }

    public TaskList() {
        this.todo = new ArrayList<Task>();
    }

    public void addTask(Task task) {

        this.todo.add(task);

    }

    public List<Task> getList() {
        return this.todo;
    }

    public Task deleteTask(int idx) {

        return this.todo.remove(idx - 1);
    }

    public Task unmark(int idx) {

        this.todo.get(idx - 1).unmark();
        return this.todo.get(idx - 1);
    }

    public Task mark(int idx) {
        this.todo.get(idx - 1).mark();
        return this.todo.get(idx - 1);
    }

    public void list() {
        for (int i = 0; i < todo.size(); i++) {
            System.out.printf("\t%d. %s", i + 1, todo.get(i));
        }
    }

    public void find(String keyword) {
        for (int i = 0; i < todo.size(); i++) {
            if (todo.get(i).getName().contains(keyword)) {
                System.out.printf("\t%d. %s", i + 1, todo.get(i));
            }
        }
    }

    public int size() {
        return todo.size();
    }
}
