import java.util.LinkedList;
import java.util.Scanner;

public class TaskList {
    private static LinkedList<Task> tasks = null;
    public int getNumOfTasks() {
        return tasks.size();
    }
    private static TaskList instance = null;
    private Ui ui = null;

    private TaskList() {
        //this.livInstance = Liv.getInstance();
    }
    public static TaskList getInstance() {
        if (instance == null) {
            instance = new TaskList();
        }
        return instance;
    }
    public void initTaskList() {
        tasks = new LinkedList<>();
        ui = Ui.getInstance();
    }
    public void addTask(Task task) {
        tasks.add(task);
    }

    public String setTaskDoneWithIndex(int index, String isDoneUpdateString, boolean isDone)
            throws TaskIndexOutOfBoundsException {
        try {
            tasks.get(index - 1).setIsDone(isDoneUpdateString, isDone);
            return tasks.get(index - 1).updateIsDoneMessage();
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            throw new TaskIndexOutOfBoundsException(index);
        }
    }

    public Task deleteTask(int index) throws TaskIndexOutOfBoundsException {
        try {
            //Task deletedTask =
            return tasks.remove(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskIndexOutOfBoundsException(index);
        }
    }

    public Task getTask(int i) {
        return tasks.get(i - 1);
    }
}
