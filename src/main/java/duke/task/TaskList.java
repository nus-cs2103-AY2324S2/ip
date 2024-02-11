package duke.task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
        List<Task> tasksByDate = new ArrayList<>();
        for (Task task : this.tasks) {
            if (task instanceof Deadline) {
                Deadline deadlineTask = (Deadline) task;
                if (deadlineTask.getBy().toLocalDate().equals(date)) {
                    tasksByDate.add(task);
                }
            } else if (task instanceof Event) {
                Event eventTask = (Event) task;
                if (eventTask.getFrom().toLocalDate().equals(date) || eventTask.getTo().toLocalDate().equals(date)) {
                    tasksByDate.add(task);
                }
            }
        }
        return tasksByDate;
    }


    /**
     * Finds tasks in the task list whose description contains a specified keyword.
     * @param keyword The keyword to search for in task descriptions.
     * @return A TaskList containing tasks whose descriptions contain the specified keyword.
     */
    public TaskList findTasksByKeyword(String keyword) {
        TaskList matchingTasks = new TaskList();
        String lowerCaseKeyword = keyword.toLowerCase(); // Convert keyword to lowercase for case insensitivity
        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(lowerCaseKeyword)) {
                matchingTasks.addTask(task);
            }
        }
        return matchingTasks;
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
}
