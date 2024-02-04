package dylanbot;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class TaskList { // contains the task list e.g., it has operations to add/delete tasks in the list
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

    /**
     * Determines if the list of tasks is empty
     *
     * @return whether the list of tasks is empty
     */
    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    /**
     * Marks the task at the specified 1-based index as completed
     *
     * @param idx 1-based index
     */
    public void mark(int idx) {
        tasks.get(idx - 1).mark();
        Ui.print("Aight marked this task as done:\n\t"
                + tasks.get(idx - 1));
    }

    /**
     * Marks the tasks at the specified 1-based index as not completed
     *
     * @param idx 1-based index
     */
    public void unmark(int idx) {
        tasks.get(idx - 1).unmark();
        Ui.print("Sian marked this task as undone:\n\t"
                + tasks.get(idx - 1));
    }

    /**
     * Creates a TodoTask and adds it to the list of tasks
     *
     * @param desc Description of the TodoTask
     */
    public void createTodo(String desc) {
        Task curr = new TodoTask(desc);
        tasks.add(curr);
        Ui.print("Roger doger, added this task: \n\t" + curr.toString());
    }

    /**
     * Creates a DeadlineTask and adds it to the list of tasks
     *
     * @param desc Description of the DeadlineTask
     * @param deadlineStr Deadline of the DeadlineTask
     * @throws DateTimeParseException If deadlineStr is of an invalid format
     */
    public void createDeadline(String desc, String deadlineStr) throws DateTimeParseException {
        try {
            DeadlineTask curr = new DeadlineTask(desc, Storage.convertStringToDateTime(deadlineStr));
            tasks.add(curr);
            Ui.print("Roger doger, added this task: \n\t" + curr.toString());
        } catch (DateTimeParseException e) {
            Ui.print("Improper date format, TRY AGAIN!!!");
        }
    }

    /**
     * Creates an EventTask and adds it to the list of tasks
     *
     * @param desc Description of the EventTask
     * @param fromStr Starting date of the EventTask
     * @param toStr Ending date of the EventTask
     */
    public void createEvent(String desc, String fromStr, String toStr) {
        try {
            EventTask curr = new EventTask(desc, Storage.convertStringToDateTime(fromStr),
                    Storage.convertStringToDateTime(toStr));
            tasks.add(curr);
            Ui.print("Roger doger, added this task: \n\t" + curr.toString());
        } catch (DateTimeParseException e) {
            Ui.print("Improper date format, TRY AGAIN!!!");
        }
    }

    /**
     * Deletes the task at the specified 1-based index from the list of tasks
     *
     * @param idx 1-based index
     */
    public void deleteTask(int idx) {
        Task toRemove = tasks.get(idx - 1);
        tasks.remove(idx - 1);
        Ui.print("Aight removed this task:\n\t" + toRemove.toString());
    }

    public void findTerm(String term) {
        ArrayList<Task> res = new ArrayList<>();
        for (Task t : tasks) {
            if (t.getDesc().contains(term)) {
                res.add(t);
            }
        }
        if (res.isEmpty()) {
            Ui.print("Shag no results found for your search for: '" + term + "'");
        } else {
            Ui.print("Here you go, results from your search for: '" + term + "'");
            ui.displayTasks(res);
        }
    }
}
