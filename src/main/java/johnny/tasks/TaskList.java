package johnny.tasks;

import java.util.ArrayList;
import java.util.List;

import johnny.exceptions.JohnnyException;

public class TaskList {

    private List<Task> tasks;

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public Task get(int index) throws JohnnyException {
        try {
            return tasks.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new JohnnyException("This task does not exist bro.");
        }
    }

    public Task mark(int index) throws JohnnyException {
        Task task = get(index);
        task.mark();
        return task;
    }

    public Task unmark(int index) throws JohnnyException {
        Task task = get(index);
        task.unmark();
        return task;
    }

    public Task delete(int index) throws JohnnyException {
        try {
            Task task = tasks.remove(index);
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new JohnnyException("This task does not exist bro.");
        }
    }

    public int size() {
        return tasks.size();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Find all Tasks in TaskList that name contains keyword.
     *
     * @param keyword String to be matched to Task name.
     * @return TaskList of all Tasks that contains keyword.
     */
    public TaskList find(String keyword) {
        TaskList foundTasks = new TaskList();
        for (Task task: tasks) {
            if (task.contains(keyword)) {
                foundTasks.addTask(task);
            }
        }
        return foundTasks;
    }

    /**
     * Check if 2 TaskLists are equal by checking all Tasks in TaskList.
     *
     * @param o Object to be compared with.
     * @return True if the TaskLists are equal else false.
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (o instanceof TaskList) {
            TaskList t = (TaskList) o;
            if (this.size() != t.size()) {
                return false;
            }
            for (int i = 0; i < this.size(); i++) {
                if (this.tasks.get(i) != t.tasks.get(i)) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

}
