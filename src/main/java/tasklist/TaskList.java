package tasklist;
import storage.Storage;
import task.Task;
import ui.Ui;


/** This taskList interacts with UI and storage unlike barebones task list*/
public class TaskList extends BarebonesTaskList {
    /** Represents UI Sir Duke will use*/
    private Ui ui;
    /** Represents storage Sir Duke will access*/
    private Storage storage;

    /**
     * Constructs TaskList object
     * @param storage
     * @param ui
     */
    public TaskList(Storage storage, Ui ui) {
        this.storage = storage;
        this.ui = ui;
    }

    /**
     * Returns taskList object without storage set
     * @param ui
     */
    public  TaskList(Ui ui) {
        this.ui = ui;
    }
    public  TaskList() {
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    /**
     * Adds an item to taskList and gives appropriate response msg
     *
     * @param task
     */
    @Override
    public void add(Task task) {
        taskList.add(task);
        taskCount += 1;
        storage.save();
    }

    /**
     * Loads task from file. Difference between this and add is that there is no acknowledgement message
     *
     * @param task
     */
    @Override
    public void load(Task task) {
        taskList.add(task);
        taskCount += 1;
    }

    /**
     * Marks task at index i of taskList as done
     *
     * @param i
     */
    @Override
    public void mark(int i) {
        taskList.get(i).markAsDone();
        ui.markResponse(taskList.get(i).toString());
        storage.save();
    }

    /**
     * Unmarks task at index i of taskList
     *
     * @param i
     */
    @Override
    public void unmark(int i) {
        taskList.get(i).unMarkAsDone();
        ui.unmarkResponse(taskList.get(i).toString());
        storage.save();
    }

    /**
     * Deletes task at index i of taskList
     *
     * @param i
     */
    @Override
    public void delete(int i) {
        try {
            this.taskList.remove(i);
            this.taskCount -= 1;
            storage.save();
        } catch (ArrayIndexOutOfBoundsException e) {
            // exception handling when taskList is empty or invalid index is required
            ui.errorMsg(e.getMessage());
        }
    }

    @Override
    public void clear() {
        taskList.clear();
        taskCount = 0;
        storage.save();
    }

    public BarebonesTaskList find(String query) {
        BarebonesTaskList result = new BarebonesTaskList();
        Task curr;
        for (int i = 0; i < taskCount; i++) {
            curr = getTask(i);
            if (curr.getDescription().contains(query)) {
                result.add(curr);
            }
        }
        return result;
    }

    public String toDataFormat() {
        StringBuilder text = new StringBuilder();
        if (taskCount == 0) {
            return "Sorry Sir/Mdm, it looks as though you have yet to add any tasks";
        }
        for (int i = 0; i < taskCount; i++) {
            text.append(String.format("%s\n", taskList.get(i).toDataFormat()));
        }
        return text.toString();
    }
}
