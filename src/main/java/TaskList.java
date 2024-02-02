import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    public TaskList() {
        tasks = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
    private String horizontalLine = "______________________________________________\n";

    public String tasksToString() {
        String taskString = "";

        if (tasks.isEmpty()) {
            return taskString;
        }

        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            int index = i + 1;
            taskString += index + ". " + task.printTask() + "\n";
        }

        return taskString;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void discreteAddTask(Task task) {
        tasks.add(task);
    }

    public Task markTask(int index) throws InvalidArgsException {
        if (index >= tasks.size()) {
            throw new InvalidArgsException("Sorry that item does not exist in your list!\n");
        }

        else if (index < 0) {
            throw new InvalidArgsException("Please input a positive task number!\n");
        }

        Task currTask = tasks.get(index);
        currTask.markTask();
        return currTask;
    }

    public Task unmarkTask(int index) throws InvalidArgsException {
        if (index >= tasks.size()) {
            throw new InvalidArgsException("Sorry that item does not exist in your list!\n");
        }

        else if (index < 0) {
            throw new InvalidArgsException("Please input a positive task number!\n");
        }

        Task currTask = tasks.get(index);
        currTask.unmarkTask();
        return currTask;
    }

    public Task deleteTask(int index) throws InvalidArgsException {
        if (index >= tasks.size()) {
            throw new InvalidArgsException("Sorry that item does not exist in your list!\n");
        }

        else if (index < 0) {
            throw new InvalidArgsException("Please input a positive task number!\n");
        }

        Task currTask = tasks.get(index);
        tasks.remove(index);
        return currTask;
    }

    public ArrayList<Task> getArrayList() {
        return tasks;
    }

    public int getSize() {
        return tasks.size();
    }
}
