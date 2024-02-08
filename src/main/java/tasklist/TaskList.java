package tasklist;
import java.io.IOException;
import java.util.ArrayList;

import handler.DataHandler;
import msg.StdMsgs;
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
    private Ui ui;

    public TaskList() {}

    public int getTaskCount() {
        return taskCount;
    }

    /**
     * Adds an item to taskList and gives appropriate response msg
     *
     * @param item
     */
    public void add(Task item) throws IOException {
        this.taskList.add(item);
        taskCount += 1;
        // ack msg
        new Msg(
                "Got it. I've added this task:\n" +
                        item +
                        "\n" +
                        String.format("Now you have %d tasks in the list.", this.taskCount)
        ).print();
        DataHandler.overWriteItems(this.toDataFormat());
    }

    /**
     * Marks task at index i of taskList as done
     *
     * @param i
     */
    public void mark(int i) throws IOException {
        this.taskList.get(i - 1).markAsDone();
        StdMsgs.MARK.print();
        new Msg(this.taskList.get(i - 1).toString()).print();

        DataHandler.overWriteItems(this.toDataFormat());
    }

    /**
     * Unmarks task at index i of taskList
     *
     * @param i
     */
    public void unmark(int i) throws IOException {
        this.taskList.get(i - 1).unMarkAsDone();
        StdMsgs.UNMARK.print();
        new Msg(this.taskList.get(i - 1).toString()).print();
        DataHandler.overWriteItems(this.toDataFormat());
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
    public void delete(int i) throws IOException {
        // exception handling when taskList is empty or invalid index is required
        this.taskList.remove(i - 1);
        this.taskCount -= 1;
        DataHandler.overWriteItems(this.toDataFormat());
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
