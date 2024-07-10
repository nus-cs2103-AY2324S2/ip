package kai.task;

import java.util.List;
import java.util.ArrayList;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds task to the list.
     *
     * @param task
     */
    public String addTask(Task task) {
        tasks.add(task);
        String result = "Got it. I've added this task:\n"
                + task + "\n"
                + "Now you have " + getSize()
                + " tasks in the list.";
        return result;
    }

    /**
     * Gets the size of the list.
     *
     * @return List size.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Lists down tasks in order when command is called.
     */
    public String listDownTask() {
        String result;
        int size = getSize();
        result = "Here are the tasks in your list:\n";
        for(int i = 1; i <= size; i++) {
            result += i +": " + tasks.get(i - 1) + "\n";
        }
        return result;
    }

    /**
     * Deletes the task at that index from the list.
     *
     * @param index
     */
    public String deleteTask(int index) {
        Task removed = getTask(index - 1);
        String result = "Noted. I've removed this task:\n" + removed + "\n";
        tasks.remove(index - 1);
        result += "Now you have " + getSize() + " tasks in the list";
        return result;
    }

    /**
     * Changes the specific task at index as done.
     *
     * @param index
     */
    public String markTask(int index) {
        Task modTask = tasks.get(index - 1);
        modTask.setIsDone(true);
        String result = "Nice! I've marked this task as done:\n" + modTask;
        return result;
    }

    /**
     * Changes the specific task at index as not done.
     *
     * @param index
     */
    public String unmarkTask(int index) {
        Task modTask = tasks.get(index - 1);
        modTask.setIsDone(false);
        String result = "OK, I've marked this task as not done yet:\n" + modTask;
        return result;
    }

    /**
     * Finds the task with the keyword given and list it in order.
     *
     * @param keyword
     */
    public String findTask(String keyword) {
        String result;
        List<Task> temp = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                temp.add(task);
            }
        }
        result = "Here are the matching tasks in your list:\n";
        for (int i = 0; i < temp.size(); i++) {
            result += (i + 1) + "." + temp.get(i) + "\n";
        }
        return result;
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }
}
