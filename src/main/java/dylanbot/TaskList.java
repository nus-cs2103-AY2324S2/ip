package dylanbot;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

// contains the task list e.g., it has operations to add/delete tasks in the list
public class TaskList {
    private ArrayList<Task> tasks;
    private Ui ui;

    public TaskList(ArrayList<Task> tasks, Ui ui) {
        this.tasks = tasks;
        this.ui = ui;
    }

    public TaskList(Ui ui) {
        this.tasks = new ArrayList<>();
        this.ui = ui;
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public Task getTask(int idx) {
        return this.tasks.get(idx - 1);
    }

    public int getSize() {
        return this.tasks.size();
    }

    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    public void mark(int idx) throws DylanBotException {
        tasks.get(idx - 1).mark();
        Ui.print("Aight marked this task as done:\n\t"
                + tasks.get(idx - 1));
    }

    public void unmark(int idx) throws DylanBotException {
        tasks.get(idx - 1).unmark();
        Ui.print("Sian marked this task as undone:\n\t"
                + tasks.get(idx - 1));
    }

    public void createTodo(String desc) throws DylanBotException {
        Task curr = new TodoTask(desc);
        tasks.add(curr);
        Ui.print("Roger doger, added this task: \n\t" + curr.toString());
    }

    public void createDeadline(String desc, String deadlineStr) throws DylanBotException, DateTimeParseException {
        try {
            DeadlineTask curr = new DeadlineTask(desc, Storage.convertStringToDateTime(deadlineStr));
            tasks.add(curr);
            Ui.print("Roger doger, added this task: \n\t" + curr.toString());
        } catch (DateTimeParseException e) {
            Ui.print("Improper date format, TRY AGAIN!!!");
        }
    }

    public void createEvent(String desc, String fromStr, String toStr) throws DylanBotException {
        try {
            EventTask curr = new EventTask(desc, Storage.convertStringToDateTime(fromStr),
                    Storage.convertStringToDateTime(toStr));
            tasks.add(curr);
            Ui.print("Roger doger, added this task: \n\t" + curr.toString());
        } catch (DateTimeParseException e) {
            Ui.print("Improper date format, TRY AGAIN!!!");
        }
    }

    public void deleteTask(int idx) throws DylanBotException {
        Task toRemove = tasks.get(idx - 1);
        tasks.remove(idx - 1);
        Ui.print("Aight removed this task:\n\t" + toRemove.toString());
    }
}
