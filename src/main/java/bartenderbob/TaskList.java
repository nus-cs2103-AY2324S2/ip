package bartenderbob;

import java.util.ArrayList;

public class TaskList {
    private static final Storage STORAGE = new Storage("./data/tasks.txt");
    private final ArrayList<Task> tasks;
    private Ui ui = new Ui();
    public TaskList() {
        this.tasks = new ArrayList<>();
    }
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public String store(Task task) {
        assert task != null : "Task cannot be null";
        tasks.add(task);
        STORAGE.saveTask(task);
        int totalTasks = tasks.size();
        return ui.showStoreTasksMessage(task, totalTasks);
    }
    public String list() {
        assert tasks != null : "Tasks list cannot be null";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(ui.showListCommandHeader()).append("\n");
        for (int i = 0; i < tasks.size(); i++) {
            int number = i + 1;
            stringBuilder.append(ui.showListElements(number, tasks, i)).append("\n");
        }
        return stringBuilder.toString();
    }

    public String markDone(String index) throws BartenderBobException {
        assert tasks != null : "Tasks list cannot be null";
        try {
            int integerIndex = Integer.parseInt(index);
            Task task = tasks.get(integerIndex - 1);
            task.mark();
            STORAGE.saveChanges(tasks);
            return ui.showMarkDone(task);
        } catch (IndexOutOfBoundsException e) {
            throw new BartenderBobException();
        }
    }
    public String unmarkDone(String index) throws BartenderBobException {
        assert tasks != null : "Tasks list cannot be null";
        try {
            int integerIndex = Integer.parseInt(index);
            Task task = tasks.get(integerIndex - 1);
            task.unmark();
            STORAGE.saveChanges(tasks);
            return ui.showUnmarkDone(task);
        } catch (IndexOutOfBoundsException e) {
            throw new BartenderBobException();
        }

    }
    public String delete(String index) throws BartenderBobException {
        assert tasks != null : "Tasks list cannot be null";
        try {
            int integerIndex = Integer.parseInt(index);
            String display = tasks.get(integerIndex - 1).show();
            tasks.remove(integerIndex - 1);
            STORAGE.saveChanges(tasks);
            int totalTasks = tasks.size();
            return ui.showDelete(display, totalTasks);
        } catch (IndexOutOfBoundsException e) {
            throw new BartenderBobException();
        }
    }

    public String find(String substring) {
        assert tasks != null : "Tasks list cannot be null";
        ArrayList<Task> result = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(substring)) {
                result.add(task);
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(ui.showFindCommandHeader()).append("\n");
        for (int i = 0; i < result.size(); i++) {
            int number = i + 1;
            ui.showListElements(number, result, i);
            stringBuilder.append(ui.showListElements(number, result, i)).append("\n");
        }
        return stringBuilder.toString();
    }
}
