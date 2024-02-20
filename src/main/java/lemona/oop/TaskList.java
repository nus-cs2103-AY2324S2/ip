package lemona.oop;

import java.util.ArrayList;

import lemona.exceptions.DuplicateInstructionException;
import lemona.exceptions.OutOfIndexException;

import lemona.task.Task;



/**
 * Represents a list of tasks in the task manager application.
 * TaskList manages the tasks, such as adding, marking, unmarking, and deleting tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs a TaskList with the specified list of tasks.
     *
     * @param tasks The list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Gets the number of tasks in the task list.
     *
     * @return The number of tasks in the list.
     */
    public int size() {
        return tasks.size();
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Marks the task at the specified index as done.
     * If the index is out of range or the task is already marked as done, appropriate error messages are displayed.
     *
     * @param index The index of the task to mark as done.
     * @return The string message to be printed.
     */
    public String mark(int index) {
        String str;
        try {
            if (tasks.size() < index) {
                throw new OutOfIndexException();
            } else if (tasks.get(index - 1).getStatusIcon().equals("X")) {
                throw new DuplicateInstructionException();
            }
            tasks.get(index - 1).markAsDone();
            str = "Nice! I've marked this task as done:" + "\n\t" +
                    tasks.get(index - 1).print();
        } catch (OutOfIndexException e) {
            str = e.toString(tasks.size());
        } catch (DuplicateInstructionException e) {
            str = e.toString("mark");
        }
        return str;
    }

    /**
     * Marks the task at the specified index as not done yet (undone).
     * If the index is out of range or the task is already marked as not done, appropriate error messages are displayed.
     *
     * @param index The index of the task to mark as not done yet.
     * @return The string message to be printed.
     */
    public String unmark(int index) {
        String str;
        try {
            if (tasks.size() < index) {
                throw new OutOfIndexException();
            } else if (tasks.get(index - 1).getStatusIcon().equals(" ")) {
                throw new DuplicateInstructionException();
            }
            tasks.get(index - 1).unmarkAsDone();
            str = "OK, I've marked this task as not done yet:" + "\n\t" +
                    tasks.get(index - 1).print();
        } catch (OutOfIndexException e) {
            str = e.toString(tasks.size());
        } catch (DuplicateInstructionException e) {
            str = e.toString("unmark");
        }
        return str;
    }

    /**
     * Deletes the task at the specified index from the task list.
     * If the index is out of range, appropriate error message is displayed.
     *
     * @param index The index of the task to delete.
     * @return The string message to be printed.
     */
    public String delete(int index) {
        String str;
        try {
            if (tasks.size() < index || index < 1) {
                throw new OutOfIndexException();
            }
            str = "OK, I've removed this task:" + "\n\t" +
                    tasks.get(index - 1).print();
            tasks.remove(index - 1);
            str = str + "\n Now you have " + tasks.size() + " tasks in the list.";
        } catch (OutOfIndexException e) {
            str = e.toString(tasks.size());
        }
        return str;
    }

    /**
     * Adds the given task to the task list.
     * If the task description already exists in the list, appropriate error message is displayed. C-DetectDuplicates
     *
     * @param task The task to be added to the list.
     * @return The string message to be printed.
     */
    public String add(Task task) {
        String str;
        try {
            for (Task value : tasks) {
                if (value.getDescription().equals(task.getDescription())) {
                    throw new DuplicateInstructionException();
                }
            }
            tasks.add(task);
            str = "Got it. I've added this task:";
            str = str + "\n\t" + task.print() + "\nNow you have " + tasks.size() + " tasks in the list.";
        } catch (DuplicateInstructionException e) {
            str = e.toString("");
        }
        return str;
    }

    /**
     * Searches for tasks that contain the given keyword.
     *
     * @param keyword The keyword to search for in the task descriptions.
     * @return The string message to be printed.
     */
    public String find(String keyword) {
        StringBuilder matchingTasks = new StringBuilder();
        matchingTasks.append("These are the result of finding the keyword:");
        assert tasks.size() >= 1 : "There needs to be a task to search!";
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getDescription().contains(keyword)) {
                matchingTasks.append("\n" + (i + 1) + ". " + tasks.get(i).print());
            }
        }
        return matchingTasks.toString();
    }

    /**
     * Displays the list of tasks to the user.
     *
     * @return The string message to be printed.
     */
    public String list() {
        StringBuilder str = new StringBuilder();
        if (tasks.size() == 0) {
            str.append("I think you haven't had enough vitamin E."
                    + "\nYou do not have any tasks on the list yet!"
                    + "\nI suggest you take some LEMONA.");
        } else {
            str.append("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                str.append("\n\t" + (i + 1) + ". " + tasks.get(i).print());
            }
        }
        return str.toString();
    }
}
