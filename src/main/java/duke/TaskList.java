package duke;

import duke.Event;
import duke.Task;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    protected ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Finds tasks that contain the keyword.
     * @param keyword
     */
    public void find(String keyword) {
        int num = 1;
        Iterator<Task> it = tasks.iterator();
        System.out.println("Here are the matching tasks in your list:");
        while (it.hasNext()) {
            Task task = it.next();
            if (task.description.contains(keyword)) {
                System.out.println(num + "." + task.toString());
                num++;
            }
        }
    }

    /**
     * Prints the list of tasks.
     */
    public void list() {
        int num = 1;
        Iterator<Task> it = tasks.iterator();
        System.out.println("Here are the tasks in the list:");
        while (it.hasNext()) {
            System.out.println(num + "." + it.next().toString());
            num++;
        }
    }

    /**
     * Adds a todo task to the list.
     * @param description The description of the todo task.
     */
    public void addTodo(String description) {
        if (description == null) {
            System.out.println("Please add a description for todo");
        } else {
            tasks.add(new ToDo(description, 0));
            String task = tasks.get(tasks.size() - 1).toString();
            String numberOfTasks = "Now you have "
                    + String.valueOf(tasks.size())
                    + " task(s) left";
            System.out.println("Ok. I added this task:\n" + task + "\n" + numberOfTasks);
        }
    }

    /**
     * Adds a deadline task to the list.
     * @param description The description of the deadline task.
     * @param deadline The deadline of the deadline task.
     */
    public void addDeadline(String description, String deadline) {
        if (description == null || deadline == null) {
            System.out.println("Ensure that the format is: deadline [task] /by [deadline]");
        } else {
            tasks.add(new Deadline(description, 0, deadline));
            String task = tasks.get(tasks.size() - 1).toString();
            String numberOfTasks = "Now you have "
                    + String.valueOf(tasks.size())
                    + " task(s) left";
            System.out.println("Ok. I added this task:\n" + task + "\n" + numberOfTasks);
        }
    }

    /**
     * Adds an event task to the list.
     * @param description The description of the event task.
     * @param start The start time of the event task.
     * @param end The end time of the event task.
     */
    public void addEvent(String description, String start, String end) {
        if (description == null || start == null || end == null) {
            System.out.println("Ensure that the format is : event [task] /from [start] /to end");
        } else {
            tasks.add(new Event(description, 0, start, end));
            String task = tasks.get(tasks.size() - 1).toString();
            String numberOfTasks = "Now you have "
                    + String.valueOf(tasks.size())
                    + " task(s) left";
            System.out.println("Ok. I added this task:\n" + task + "\n" + numberOfTasks);
        }
    }

    /**
     * Deletes a task from the list.
     * @param index The index of the task to be deleted.
     */
    public void deleteTask(int index) {
        if (index <= 0 || index > tasks.size()) {
            System.out.println("Please enter index ranging from 1 to " + String.valueOf(tasks.size()));
        } else {
            System.out.println("Ok. I'll be removing this task:\n "
                    + tasks.get(index - 1).toString()
                    + "\n"
                    + "Now you have " + String.valueOf(tasks.size() - 1) + " task(s) left");
            tasks.remove(index-1);
        }
    }

    /**
     * Marks a task as done.
     * @param index The index of the task to be marked as done.
     */
    public void markTask(int index) {
        if (index < 0 || index > tasks.size()) {
            System.out.println("Please enter index ranging from 1 to " + String.valueOf(tasks.size()));
        } else {
            tasks.get(index - 1).markAsDone();
        }
    }

    /**
     * Marks a task as undone.
     * @param index The index of the task to be marked as undone.
     */
    public void unmarkTask(int index) {
        if (index < 0 || index > tasks.size()) {
            System.out.println("Please enter index ranging from 1 to " + String.valueOf(tasks.size()));
        } else {
            tasks.get(index - 1).markAsUndone();
        }
    }


}
