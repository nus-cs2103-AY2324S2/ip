package duke.task;
import duke.storage.Storage;
import duke.ui.Ui;
import java.util.ArrayList;
public class TaskList extends ArrayList<Task> {

    private static ArrayList<Task> tasks;

    /**
     * A default constructor class to initialize TaskList object
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * A default constructor class to initialize TasKList object with ArrayList<Task>
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Gets task by taskNo
     * @param taskNo
     */
    public static Task getTask(int taskNo) {
        return tasks.get(taskNo);
    }

    /**
     * Adds task to tasks
     * @param t
     */
    public static void addTask(Task t) {
        tasks.add(t);
        assert tasks.get(0) != null;
    }

    /**
     * Deletes task by taskNo
     * @param taskNo
     */
    public void deleteTask(int taskNo) {
        tasks.remove(taskNo);
    }

    /**
     * Marks task by taskNo
     * @param taskNo
     */
    public void markTask(int taskNo) {
        Task t = tasks.get(taskNo);
        t.done();
        assert tasks.get(0) != null;
    }

    /**
     * Unmarks task by taskNo
     * @param taskNo
     */
    public static void unmarkTask(int taskNo) {
        Task t = tasks.get(taskNo);
        t.undone();
        assert tasks.get(0) != null;
    }

    /**
     * Filters tasks by keyword provided
     * @param keyword
     * @return TaskList
     */
    public TaskList filterTasksByKeyword(String keyword) {
        ArrayList<Task> list = new ArrayList<>();
        for (Task t: tasks) {
            if (t.getTaskName().contains(keyword)) {
                list.add(t);
            }
        }
        return new TaskList(list);
    }

    public static ArrayList<Task> findTaskByKeyword(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task t: tasks) {
            if (t.getTaskName().contains(keyword)) {
                matchingTasks.add(t);
            }
        }
        return matchingTasks;
    }


    /**
     * Returns a string of tasks by the following format:
     * 1. [<task_type>][<isDone>] <task_name> <other_details (if any)>
     * @return String
     */
    @Override
    public String toString() {
        String taskList = "";
        if (tasks == null || tasks.isEmpty()) {
            taskList += "----You have no tasks yet.----";
        }
        else {
            for (int i = 0; i < tasks.size(); i++) {
                Task iTask = tasks.get(i);
                taskList += (i + 1) + ". " + iTask.toString() + "\n";
            }
        }
        return taskList;
    }

    /**
     * Returns a string array of tasks by the following format:
     * 1. <task_type>,<isDone (0/1)>,<task_name>,<other_details (if any)>
     * @return ArrayList<String>
     */
    public ArrayList<String> getTasksInStoreList() {
        ArrayList<String> taskContent = new ArrayList<>();
        for (Task t : tasks) {
            taskContent.add(t.toStore());
        }
        return taskContent;
    }

    /**
     * Returns the number of tasks
     * @return int
     */
    public int getNoOfTasks() {
        return tasks.size();
    }
}