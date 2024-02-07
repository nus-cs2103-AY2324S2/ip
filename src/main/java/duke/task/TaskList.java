package duke.task;

import duke.storage.Storage;
import duke.ui.Ui;

import java.util.ArrayList;
public class TaskList extends ArrayList<Task> {

    private static ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public static Task getTask(int taskNo) {
        try {
            return tasks.get(taskNo);
        } catch (IndexOutOfBoundsException e) {
            Ui.showErrorNotExist(taskNo);
            return null;
        }
    }
    public static void addTask(Task t) {
        tasks.add(t);
        Ui.printTaskAdded(t, tasks);
        Storage.updateFile(tasks);
    }

    public static void removeTask(int taskNo) {
        try {
            Task t = tasks.get(taskNo);
            tasks.remove(taskNo);
            Ui.printTaskRemoved(t, tasks);
        } catch (IndexOutOfBoundsException e) {
            Ui.showErrorNotExist(taskNo);
        }
    }

    public static void markTask(int taskNo) {
        try {
            Task t = tasks.get(taskNo);
            t.done();
            Ui.printTaskMarked(taskNo, t);
        } catch (IndexOutOfBoundsException e) {
            Ui.showErrorNotExist(taskNo);
        }
    }

    public static void unmarkTask(int taskNo) {
        try {
            Task t = tasks.get(taskNo);
            t.undone();
            Ui.printTaskUnmarked(taskNo, t);
        } catch (IndexOutOfBoundsException e) {
            Ui.showErrorNotExist(taskNo);
        }
    }

    public static void printTaskList() {
        Ui.printTasks(tasks);
    }
}
