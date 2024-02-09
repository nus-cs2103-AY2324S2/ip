package tasklist;
import java.util.ArrayList;
import storage.Storage;
import task.Task;
import msg.Msg;
import ui.Ui;


/**
 * The tasklist class is a representation of a list of tasks that is able to add, delete, mark and unmark tasks on that
 * list and return an appropriate response
 */
public class TaskList {
    /** tracks number of tasks created*/
    private int taskCount = 0;
    /** ArrayList<task> to store tasks */
    private ArrayList<Task> taskList = new ArrayList<Task>();
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

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public int getTaskCount() {
        return taskCount;
    }

    /**
     * Adds an item to taskList and gives appropriate response msg
     *
     * @param item
     */
    public void add(Task item) {
        this.taskList.add(item);
        taskCount += 1;
        storage.save();
    }

    /**
     * Loads task from file. Difference between this and add is that there is no acknowledgement message
     *
     * @param item
     */
    public void load(Task item) {
        this.taskList.add(item);
        taskCount += 1;
    }

    /**
     * Marks task at index i of taskList as done
     *
     * @param i
     */
    public void mark(int i) {
        taskList.get(i - 1).markAsDone();
        ui.markResponse(taskList.get(i - 1).toString());
        storage.save();
    }

    /**
     * Unmarks task at index i of taskList
     *
     * @param i
     */
    public void unmark(int i) {
        taskList.get(i - 1).unMarkAsDone();
        ui.unmarkResponse(taskList.get(i - 1).toString());
        storage.save();
    }

    /**
     * Retrieves task at index i
     *
     * @param i
     */
    public Task getTask(int i) {
        return taskList.get(i);
    }

    /**
     * Deletes task at index i of taskList
     *
     * @param i
     */
    public void delete(int i) {
        try {
            this.taskList.remove(i - 1);
            this.taskCount -= 1;
            storage.save();
        } catch (ArrayIndexOutOfBoundsException e) {
            // exception handling when taskList is empty or invalid index is required
            ui.errorMsg(e.getMessage());
        }
    }
    public Msg toMsg() {
        return new Msg(this.toString());
    }
    /**
     * Prints the list of tasks
     *
     */
    @Override
    public String toString() {
        StringBuilder text = new StringBuilder();
        if (taskCount == 0) {
            return "Sorry Sir/Mdm, it looks as though you have yet to add any tasks";
        }
        for (int i = 0;i < taskCount; i++) {
            text.append(String.format("%d. %s \n", i + 1, this.taskList.get(i)));
        }
        return text.toString();
    }

    public String toDataFormat() {
        StringBuilder text = new StringBuilder();
        if (taskCount == 0) {
            return "Sorry Sir/Mdm, it looks as though you have yet to add any tasks";
        }
        for (int i = 0; i < taskCount; i++) {
            text.append(String.format("%s\n", this.taskList.get(i).toDataFormat()));
        }
        return text.toString();
    }
}
