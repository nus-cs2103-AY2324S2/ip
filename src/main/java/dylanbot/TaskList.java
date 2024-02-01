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

    public String getTask(int idx) {
        return this.tasks.get(idx).toString();
    }

    public int getSize() {
        return this.tasks.size();
    }

    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    public void mark(int idx) throws DylanBotException {
        tasks.get(idx - 1).completed = true;
        ui.print("Aight marked this task as done:\n\t"
                + tasks.get(idx - 1));
    }

    public void unmark(int idx) throws DylanBotException {
        tasks.get(idx - 1).completed = false;
        ui.print("Sian marked this task as undone:\n\t"
                + tasks.get(idx - 1));
    }

    public void createTodo(String desc) throws DylanBotException {
        Task curr = new TodoTask("T", desc);
        tasks.add(curr);
        ui.print("Roger doger, added this task: \n\t" + curr.toString());
    }

    public void createDeadline(String desc, String deadlineStr) throws DylanBotException, DateTimeParseException {
        try {
            DeadlineTask curr = new DeadlineTask("D", desc, Storage.ConvertStringToDateTime(deadlineStr));
            tasks.add(curr);
            ui.print("Roger doger, added this task: \n\t" + curr.toString());
        } catch (DateTimeParseException e) {
            ui.print("Improper date format, TRY AGAIN!!!");
        }
    }

    public void createEvent(String desc, String fromStr, String toStr) throws DylanBotException {
        try {
            EventTask curr = new EventTask("E", desc, Storage.ConvertStringToDateTime(fromStr),
                    Storage.ConvertStringToDateTime(toStr));            
            tasks.add(curr);
            ui.print("Roger doger, added this task: \n\t" + curr.toString());
        } catch (DateTimeParseException e) {
            ui.print("Improper date format, TRY AGAIN!!!");
        }
    }

    public void deleteTask(int idx) throws DylanBotException {
        Task toRemove = tasks.get(idx - 1);
        tasks.remove(idx - 1);
        ui.print("Aight removed this task:\n\t" + toRemove.toString());
    }

}
