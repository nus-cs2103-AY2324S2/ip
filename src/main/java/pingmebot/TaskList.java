package pingmebot;

import pingmebot.task.Task;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class TaskList {
    ArrayList<Task> tasks;
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }


    public int getTaskSize() {
        return this.tasks.size();
    }

    public void updateTaskToStorage(fileStorage fs) throws myBotException {
        try {
            fs.updateFile(this.tasks);
        } catch (myBotException e) {
            throw new myBotException(e.getMessage());
        }
    }
    public void addTask(Task task) {
        this.tasks.add(task);
    }
    public void removeTask(int taskNumber) {
        this.tasks.remove(taskNumber);
    }

    public String taskToString(int taskNumber) {
        return this.tasks.get(taskNumber).toString();
    }

    public String taskStatusIcon(int taskNumber) {
        return this.tasks.get(taskNumber).getStatusIcon();
    }

    public void taskMarkAsDone(int taskNumber) {
        this.tasks.get(taskNumber).markAsDone();
    }

    public void taskUncheckTask(int taskNumber) {
        this.tasks.get(taskNumber).uncheckingTask();
    }

    public void listTask() {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i) == null) {
                break;
            }
            int taskNumber = i + 1;
            System.out.println(taskNumber + "." + tasks.get(i).toString());
        }
    }

    /**
     * Returns a list of matching task according to the specified keyword.
     *
     * @param keyword Keyword specified by the user to find.
     * @return A list of matching task lists, can be empty as well.
     */
    public ArrayList<Task> findMatchingTask(String keyword) {
        return this.tasks.stream().filter(task
                -> task.getDescription().contains(keyword)).collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Prints out the list of matching task, if any, with a 1-based index.
     *
     * @param tasks The list of matching tasks, if any.
     */
    public void listMatchingTask(ArrayList<Task> tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i).toString());
        }
    }
}
