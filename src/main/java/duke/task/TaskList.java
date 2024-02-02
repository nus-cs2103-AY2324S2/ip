package duke.task;

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
    public void addTask(Task task) {
        tasks.add(task);
        System.out.println("Got it. I've added this task:\n"
                + task + "\n"
                + "Now you have " + getSize()
                + " tasks in the list.");
    }

    /**
     * Get size of the list.
     *
     * @return List size.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Lists down tasks in order when command is called.
     */
    public void listDownTask() {
        int size = getSize();
        System.out.println("Here are the tasks in your list:");
        for(int i = 1; i <= size; i++) {
            System.out.println(i +": " + tasks.get(i - 1));
        }
    }

    /**
     * Deletes the task at that index from the list.
     *
     * @param index
     */
    public void deleteTask(int index) {
        Task removed = getTask(index - 1);
        System.out.println("Noted. I've removed this task:\n" + removed);
        tasks.remove(index - 1);
        System.out.println("Now you have " + getSize() + " tasks in the list");
    }

    /**
     * Change the specific task at index as done.
     *
     * @param index
     */
    public void markTask(int index) {
        Task modTask = tasks.get(index - 1);
        modTask.setIsDone(true);
        System.out.println("Nice! I've marked this task as done:\n" + modTask);
    }

    /**
     * Change the specific task at index as not done.
     *
     * @param index
     */
    public void unmarkTask(int index) {
        Task modTask = tasks.get(index - 1);
        modTask.setIsDone(false);
        System.out.println("OK, I've marked this task as not done yet:\n" + modTask);
    }

    /**
     * Find the task with the keyword given and list it in order.
     * 
     * @param keyword
     */
    public void findTask(String keyword) {
        List<Task> temp = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                temp.add(task);
            }
        }
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < temp.size(); i++) {
            System.out.println((i + 1) + "." + temp.get(i));
        }
    }

    public Task getTask(int index) {
            return tasks.get(index);
    }
}
