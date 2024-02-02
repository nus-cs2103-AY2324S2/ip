package jiayou.task;

import java.time.LocalDate;
import java.util.ArrayList;
import jiayou.*;

/**
 * Represents a task list to store all the tasks of the chatbot.
 */
public class TaskList {
    private Storage storage;
    private ArrayList<Task> tasks = new ArrayList<Task>();

    /**
     * Links the given storage to the task list.
     *
     * @param storage the storage to be linked.
     */
    public void linkStorage(Storage storage) {
        this.storage = storage;
    }

    /**
     * Adds a new task to the task list.
     *
     * @param task the task to be added.
     */
    public void addTask(Task task) {
         this.tasks.size();
         this.tasks.add(task);
         this.storage.save();
    }

    /**
     * Deletes a new task from the task list.
     *
     * @param input the task id of the task to be deleted.
     */
    public void deleteTask(String input) {
        try {
            int taskId = Integer.parseInt(input) - 1;
            if (taskId < 0 || taskId >= tasks.size()) {
                throw new JiayouException("OOPS!!! The task you wanna delete doesn't exist. Please input a valid number!");
            }

            Task removedTask = tasks.remove(taskId);
            System.out.println(">w<Noted. I've removed this task:");
            System.out.println("  " + removedTask);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            this.storage.save();
        } catch (NumberFormatException e) {
            System.out.println("OOPS!!! Please enter a valid task number!");
        } catch (JiayouException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Marks the certain task in the list as done.
     *
     * @param input the task id of the task to get marked done.
     */
    public void markTask(String input) {
        int taskId = Integer.parseInt(input);
        Task task = this.tasks.get(taskId - 1);
        task.setStatus(true);
        System.out.println(">w<Nice! I've marked this task as done:");
        System.out.println("  " + task);
        this.storage.save();
    }

    /**
     * Unmarks the certain task in the list as not done.
     *
     * @param input the task id of the task to get masked not done.
     */
    public void unmarkTask(String input) {
        int taskId = Integer.parseInt(input);
        Task task = tasks.get(taskId - 1);
        task.setStatus(false);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + task);
        this.storage.save();
    }

    /**
     * Searches for the tasks on a certain date and print them out.
     *
     * @param date the date to search.
     */
    public void searchByDate(LocalDate date) {
        System.out.println("Here are the tasks on " + date + " in your list:");
        for (int i = 0; i < this.tasks.size(); i++) {
            Task task = this.tasks.get(i);
            if (task instanceof Event) {
                if (date.equals(((Event) task).getFrom()) | date.equals((((Event) task).getTo())) | (date.isAfter((((Event) task).getFrom())) & date.isBefore(((Event) task).getTo()))) {
                    System.out.println((i + 1) + "." + task.toString());
                }
            } else if (task instanceof Deadline) {
                if (date.equals(((Deadline) task).getByTime())) {
                    System.out.println((i + 1) + "." + task.toString());
                }
            }
        }
    }

    /**
     * Searches for the tasks with a certain keyword and print them out.
     *
     * @param keyword the keyword to search.
     */
    public void searchByKeyword(String keyword) {
        System.out.println("Here are the tasks with the keyword " + keyword + " in your list:");
        for (int i = 0; i < this.tasks.size(); i++) {
            Task task = this.tasks.get(i);
            if (task.getDescription().contains(keyword)) {
                System.out.println((i + 1) + "." + task.toString());
            }
        }
    }

    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Prints all the tasks out.
     */
    public void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < this.tasks.size(); i++) {
            Task task = this.tasks.get(i);
            System.out.println((i + 1) + "." + task.toString());
        }
    }

    public ArrayList<Task> getList() {
        return this.tasks;
    }
}
