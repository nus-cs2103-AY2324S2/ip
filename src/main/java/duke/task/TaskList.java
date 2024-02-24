package duke.task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A TaskList class that encapsulates the information and actions of a task list.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> list) {
        this.tasks = list;
    }

    public TaskList(List<Task> list) {
        this.tasks = new ArrayList<>(list);
    }

    public TaskList(TaskList other) {
        this.tasks = new ArrayList<>(other.tasks);
    }

    public void addTask(Task task) {
        assert task != null : "Task to be added cannot be null";
        this.tasks.add(task);
    }

    /**
     * Deletes a task from the TaskList.
     * @param  taskIndex Index of the task to be deleted.
     * @return           The deleted task.
     */
    public Task deleteTask(int taskIndex) {
        Task taskToDelete = tasks.get(taskIndex);
        this.tasks.remove(taskIndex);
        return taskToDelete;
    }

    public int getNoOfTasks() {
        return this.tasks.size();
    }

    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public void clearTasks() {
        this.tasks = new ArrayList<>();
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Task> getTasksByDate(LocalDate date) {
        return tasks.stream()
                .filter(task -> task instanceof Deadline && ((Deadline) task).getBy().toLocalDate().equals(date))
                .filter(task -> task instanceof Event && (((Event) task).getFrom().toLocalDate().equals(date)
                        || ((Event) task).getTo().toLocalDate().equals(date)))
                .collect(Collectors.toList());
    }



    /**
     * Finds tasks in the task list whose description contains a specified keyword.
     * @param keyword The keyword to search for in task descriptions.
     * @return A TaskList containing tasks whose descriptions contain the specified keyword.
     */
    public TaskList findTasksByKeyword(String keyword) {
        String lowerCaseKeyword = keyword.toLowerCase();
        List<Task> matchingTasks = tasks.stream()
                .filter(task -> task.getDescription().toLowerCase().contains(lowerCaseKeyword))
                .collect(Collectors.toList());
        return new TaskList(new ArrayList<>(matchingTasks));
    }



    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    @Override
    public String toString() {
        // Creating a string builder to append task index and details
        StringBuilder listContent = new StringBuilder();
        // Iterating through the task list
        for (int i = 0; i < this.getNoOfTasks(); i++) {
            // Appending task index and task details
            listContent.append(i + 1).append(".").append(this.getTask(i)).append("\n");
        }
        // Return the final string representation of the task list
        return String.valueOf(listContent);
    }

    public List<Task> getAllTasks() {
        return List.copyOf(this.tasks);
    }
}
