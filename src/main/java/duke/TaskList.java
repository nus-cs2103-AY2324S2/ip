package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.File;
import java.util.Scanner;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    protected ArrayList<Task> tasks;
    protected Storage storage;

    private String reply;
    public TaskList(Storage storage) {
        this.tasks = new ArrayList<>();
        this.storage = storage;
    }

    public TaskList(File f, Storage storage) throws FileNotFoundException {
        this.tasks = new ArrayList<>();
        this.storage = storage;
        Scanner sc = new Scanner(f);
        while (sc.hasNext()) {
            String input = sc.nextLine();
            String[] words = input.split(" ");
            int isDone = Integer.parseInt(words[0]);
            String command = words[1];
            if (command.equals("todo")) {
                String[] parts = input.split("todo", 2);
                String description = parts[1].trim();
                tasks.add(new ToDo(description, isDone));
            } else if (command.equals("deadline")) {
                String[] parts = input.split("deadline", 2);
                String description = parts[1].trim();
                String[] deadlineParts = description.split("/by", 2);
                description = deadlineParts[0].trim();
                String deadline = deadlineParts[1].trim();
                tasks.add(new Deadline(description, isDone, deadline));
            } else {
                String[] parts = input.split("/from| /to");
                String description = parts[0].trim().substring("0 event".length()).trim();
                String start = parts[1].trim();
                String end = parts[2].trim();
                tasks.add(new Event(description, isDone, start, end));
            }
        }
    }

    /**
     * Finds tasks that contain the keyword.
     * @param keyword
     */
    public String find(String keyword) {
        int num = 1;
        reply = "Here are the matching tasks in your list:\n";
        Iterator<Task> it = tasks.iterator();
        while (it.hasNext()) {
            Task task = it.next();
            if (task.description.contains(keyword)) {
                reply += num + "." + task.toString() + "\n";
                num++;
            }
        }
        return reply;
    }

    /**
     * Prints the list of tasks.
     */
    public String list() {
        int num = 1;
        Iterator<Task> it = tasks.iterator();
        reply = "Here are the tasks in the list:\n";
        while (it.hasNext()) {
            reply += num + "." + it.next().toString() + "\n";
            num++;
        }
        return reply;
    }

    /**
     * Adds a todo task to the list.
     * @param description The description of the todo task.
     */
    public String addTodo(String description) {
        if (description == null) {
            reply = "Please add a description for todo";
        } else {
            tasks.add(new ToDo(description, 0));
            String task = tasks.get(tasks.size() - 1).toString();
            String numberOfTasks = "Now you have "
                    + String.valueOf(tasks.size())
                    + " task(s) left";
            reply = "Ok. I added this task:\n" + task + "\n" + numberOfTasks;
        }
        return reply;
    }

    /**
     * Adds a deadline task to the list.
     * @param description The description of the deadline task.
     * @param deadline The deadline of the deadline task.
     */
    public String addDeadline(String description, String deadline) {
        if (description == null || deadline == null) {
            reply = "Ensure that the format is: deadline [task] /by [deadline]";
        } else {
            tasks.add(new Deadline(description, 0, deadline));
            String task = tasks.get(tasks.size() - 1).toString();
            String numberOfTasks = "Now you have "
                    + String.valueOf(tasks.size())
                    + " task(s) left";
            reply = "Ok. I added this task:\n" + task + "\n" + numberOfTasks;
        }
        return reply;
    }

    /**
     * Adds an event task to the list.
     * @param description The description of the event task.
     * @param start The start time of the event task.
     * @param end The end time of the event task.
     */
    public String addEvent(String description, String start, String end) {
        if (description == null || start == null || end == null) {
            reply = "Ensure that the format is : event [task] /from [start] /to end";
        } else {
            tasks.add(new Event(description, 0, start, end));
            String task = tasks.get(tasks.size() - 1).toString();
            String numberOfTasks = "Now you have "
                    + String.valueOf(tasks.size())
                    + " task(s) left";
            reply = "Ok. I added this task:\n" + task + "\n" + numberOfTasks;
        }
        return reply;
    }

    /**
     * Deletes a task from the list.
     * @param index The index of the task to be deleted.
     */
    public String deleteTask(int index) {
        if (index <= 0 || index > tasks.size()) {
            reply = "Please enter index ranging from 1 to " + String.valueOf(tasks.size());
        } else {
            reply = "Ok. I'll be removing this task:\n "
                    + tasks.get(index - 1).toString()
                    + "\n"
                    + "Now you have " + String.valueOf(tasks.size() - 1) + " task(s) left";
            tasks.remove(index-1);
        }
        return reply;
    }

    /**
     * Marks a task as done.
     * @param index The index of the task to be marked as done.
     */
    public String markTask(int index) {
        if (index < 0 || index > tasks.size()) {
            reply = "Please enter index ranging from 1 to " + String.valueOf(tasks.size());
        } else {
            tasks.get(index - 1).markAsDone();
            reply = "This task is marked as done:\n"
                    + tasks.get(index - 1).toString();
        }
        return reply;
    }

    /**
     * Marks a task as undone.
     * @param index The index of the task to be marked as undone.
     */
    public String unmarkTask(int index) {
        if (index < 0 || index > tasks.size()) {
            reply = "Please enter index ranging from 1 to " + String.valueOf(tasks.size());
        } else {
            tasks.get(index - 1).markAsUndone();
            reply = "This task is marked as not done:\n"
                    + tasks.get(index - 1).toString();
        }
        return reply;
    }


}
