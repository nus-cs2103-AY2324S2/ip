import java.time.LocalDate;
import java.util.ArrayList;

public class TaskList {
    public ArrayList<Task> tasks;
    private Ui ui;

    public TaskList() {
        tasks = new ArrayList<>();
        ui = new Ui();
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int i) {
        return tasks.get(i);
    }

    public void mark (int n) {
        if ((n > 0) && (n <= tasks.size())) {
            Task task = tasks.get(n - 1);
            task.setDone();
            ui.showMarkTask(task);
        } else {
            ui.showNoTaskFound();
        }
    }

    public void mark (int n, boolean show) {
        if ((n > 0) && (n <= tasks.size())) {
            Task task = tasks.get(n - 1);
            task.setDone();
            if (show) {
                ui.showMarkTask(task);
            }
        }
    }

    public void unmark(int n) {
        if ((n > 0) && (n <= tasks.size())) {
            Task task = tasks.get(n - 1);
            task.setUndone();
            ui.showUnmarkTask(task);
        } else {
            ui.showNoTaskFound();
        }
    }

    public void delete(int n) {
        if ((n > 0) && (n <= tasks.size())) {
            Task removedTask = tasks.remove(n - 1);
            ui.showDeleteTask(removedTask, tasks.size());
        } else {
            ui.showNoTaskFound();
        }
    }

    public void createToDo(String task) {
        ToDo todo = new ToDo(task);
        tasks.add(todo);
        ui.showCreateTask(todo, tasks.size());
    }

    public void createToDo(String task, boolean show) {
        ToDo todo = new ToDo(task);
        tasks.add(todo);
        if (show) {
            ui.showCreateTask(todo, tasks.size());
        }
    }

    public void createDeadline(String description, LocalDate by) {
        Deadline deadline = new Deadline(description, by);
        tasks.add(deadline);
        ui.showCreateTask(deadline, tasks.size());
    }

    public void createDeadline(String description, LocalDate by, boolean show) {
        Deadline deadline = new Deadline(description, by);
        tasks.add(deadline);
        if (show) {
            ui.showCreateTask(deadline, tasks.size());
        }
    }

    public void createEvent(String description, LocalDate from, LocalDate to) {
        Event event = new Event(description, from, to);
        tasks.add(event);
        ui.showCreateTask(event, tasks.size());
    }

    public void createEvent(String description, LocalDate from, LocalDate to, boolean show) {
        Event event = new Event(description, from, to);
        tasks.add(event);
        if (show) {
            ui.showCreateTask(event, tasks.size());
        }
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < size(); i++) {
            string.append(i + 1).append(". ").append(get(i)).append("\n");
        }
        return string.toString();
    }
}
