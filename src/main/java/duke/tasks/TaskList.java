package duke.tasks;

import java.util.ArrayList;

import duke.exceptions.DukeException;
import duke.exceptions.tasks.MissingTaskException;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public Task get(int index) throws DukeException {
        try {
            return this.tasks.get(index);
        } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            throw new MissingTaskException();
        }
    }

    public boolean add(Task task) {
        return this.tasks.add(task);
    }

    public Task remove(int index) throws DukeException {
        try {
            return this.tasks.remove(index);
        } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            throw new MissingTaskException();
        }
    }

    public int size() {
        return this.tasks.size();
    }
}
