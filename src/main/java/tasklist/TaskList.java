package tasklist;

import exceptions.InvalidStatusUpdateException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;
import ui.UI;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class TaskList {
    private List<Task> tasks;

    public TaskList(List<String> data) {
        this.tasks = new ArrayList<>();
        for (String line : data) {
            String[] words = line.split(",");
            try {
                if (words[0].equals("todo")) {
                    Todo todo = new Todo(words[1]);
                    if (words[2].equals("true")) {
                        todo.updateStatus(true);
                    }
                    this.tasks.add(todo);
                } else if (words[0].equals("deadline")) {
                    Deadline deadline = new Deadline(words[1], LocalDate.parse(words[2]));
                    if (words[3].equals("true")) {
                        deadline.updateStatus(true);
                    }
                    this.tasks.add(deadline);
                } else if (words[0].equals("event")) {
                    Event event = new Event(words[1], LocalDate.parse(words[2]), LocalDate.parse(words[3]));
                    if (words[4].equals("true")) {
                        event.updateStatus(true);
                    }
                    this.tasks.add(event);
                }
            } catch (InvalidStatusUpdateException e) {
                UI.print(e.getMessage());
            }
        }
    }

    public boolean addTask(Task task) {
        return this.tasks.add(task);
    }

    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    public Task deleteTask(int index) {
        return this.tasks.remove(index);
    }

    public int getSize() {
        return this.tasks.size();
    }

    public List<Task> fetchAll() {
        return this.tasks;
    }

}
