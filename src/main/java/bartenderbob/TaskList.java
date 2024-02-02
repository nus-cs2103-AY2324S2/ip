package bartenderbob;

import java.util.ArrayList;

public class TaskList {
    private final Storage STORAGE = new Storage("./data/tasks.txt");
    private final ArrayList<Task> TASKS;
    private Ui ui = new Ui();
    public TaskList(ArrayList<Task> tasks) {
        this.TASKS = tasks;
    }

    public void store(Task task) {
        TASKS.add(task);
        STORAGE.saveTask(task);
        int totalTasks = TASKS.size();
        ui.showStoreTasksMessage(task, totalTasks);
    }
    public void list() {
        ui.showListCommandHeader();
        for (int i = 0; i < TASKS.size(); i++) {
            int number = i + 1;
            ui.showListElements(number, TASKS, i);
        }
    }
    public void markDone(String index) throws BartenderBobException{
        try {
            int integerIndex = Integer.parseInt(index);
            Task task = TASKS.get(integerIndex - 1);
            task.mark();
            STORAGE.saveChanges(TASKS);
            ui.showMarkDone(task);
        } catch (IndexOutOfBoundsException e) {
            throw new BartenderBobException();
        }
    }
    public void unmarkDone(String index) throws BartenderBobException{
        try {
            int integerIndex = Integer.parseInt(index);
            Task task = TASKS.get(integerIndex - 1);
            task.unmark();
            STORAGE.saveChanges(TASKS);
            ui.showUnmarkDone(task);
        } catch (IndexOutOfBoundsException e) {
            throw new BartenderBobException();
        }

    }
    public void delete(String index) throws BartenderBobException{
        try {
            int integerIndex = Integer.parseInt(index);
            String display = TASKS.get(integerIndex - 1).show();
            TASKS.remove(integerIndex - 1);
            STORAGE.saveChanges(TASKS);
            int totalTasks = TASKS.size();
            ui.showDelete(display, totalTasks);
        } catch (IndexOutOfBoundsException e) {
            throw new BartenderBobException();
        }
    }

    public void find(String substring) {
        ArrayList<Task> result = new ArrayList<>();
        for (Task task : TASKS) {
            if (task.getDescription().contains(substring)) {
                result.add(task);
            }
        }
        ui.showFindCommandHeader();
        for (int i = 0; i < result.size(); i++) {
            int number = i + 1;
            ui.showListElements(number, result, i);
        }
    }
}
