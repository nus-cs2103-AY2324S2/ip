package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.FileNotFoundException;
import java.io.IOException;
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

    public TaskList(File f, Storage storage) throws FileNotFoundException, DukeException {
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
                assert command.equals("event") : "Command should be event";
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
     * @return A string that contains the tasks that contain the keyword.
     */
    public String find(String keyword) {
        int num = 1;
        reply = "Here are the matching tasks in your list:\n";
        Iterator<Task> it = tasks.iterator();
        while (it.hasNext()) {
            Task task = it.next();
            if (task.getDescription().contains(keyword)) {
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
     * @return A string that indicates the todo task has been added successfully.
     */
    public String addTodo(String description) {
        int arraySize = tasks.size();
        tasks.add(new ToDo(description, 0));
        assert(tasks.size() == arraySize + 1) : "Task should be added to the list";
        String task = tasks.get(tasks.size() - 1).toString();
        String numberOfTasks = "Now you have " + tasks.size() + " task(s) left";
        reply = "Ok. I added this task:\n" + task + "\n" + numberOfTasks;
        return reply;
    }

    /**
     * Adds a deadline task to the list.
     * @param description The description of the deadline task.
     * @param deadline The deadline of the deadline task.
     * @return A string that indicates the deadline task has been added successfully.
     */
    public String addDeadline(String description, String deadline) {
        int arraySize = tasks.size();
        tasks.add(new Deadline(description, 0, deadline));
        assert (tasks.size() == arraySize + 1) : "Task should be added to the list";
        String task = tasks.get(tasks.size() - 1).toString();
        String numberOfTasks = "Now you have " + tasks.size() + " task(s) left";
        reply = "Ok. I added this task:\n" + task + "\n" + numberOfTasks;
        return reply;
    }

    /**
     * Adds an event task to the list.
     * @param description The description of the event task.
     * @param start The start time of the event task.
     * @param end The end time of the event task.
     * @return A string that indicates the event task has been added.
     */
    public String addEvent(String description, String start, String end) {
        int arraySize = tasks.size();
        tasks.add(new Event(description, 0, start, end));
        assert(tasks.size() == arraySize + 1) : "Task should be added to the list";
        String task = tasks.get(tasks.size() - 1).toString();
        String numberOfTasks = "Now you have "
                    + tasks.size()
                    + " task(s) left";
        reply = "Ok. I added this task:\n" + task + "\n" + numberOfTasks;
        return reply;
    }

    /**
     * Deletes a task from the list.
     * @param index The index of the task to be deleted.
     * @return A string that indicates if the task has been deleted successfully.
     * @throws DukeException If the index is out of range.
     */
    public String deleteTask(int index) throws DukeException {
        try {
            int arraySize = tasks.size();
            reply = "Ok. I'll be removing this task:\n "
                    + tasks.get(index - 1).toString()
                    + "\n"
                    + "Now you have " + (tasks.size() - 1) + " task(s) left";
            tasks.remove(index - 1);
            assert(tasks.size() == arraySize - 1) : "Task should be removed from the list";
            storage.removeFromFile(index);
            return reply;
        } catch (IndexOutOfBoundsException | IOException e) {
            throw new DukeException("Please enter index ranging from 1 to " + tasks.size());
        }
    }

    /**
     * Marks a task as done.
     * @param index The index of the task to be marked as done.
     * @return A string that indicates if the task has been marked as done successfully.
     * @throws DukeException If the index is out of range.
     */
    public String markTask(int index) throws DukeException {
        try {
            tasks.get(index - 1).markAsDone();
            storage.editLineInFile(index, 1);
            reply = "This task is marked as done:\n"
                    + tasks.get(index - 1).toString();
            return reply;
        } catch (IndexOutOfBoundsException | IOException e) {
            throw new DukeException("Please enter index ranging from 1 to " + tasks.size());
        }
    }

    /**
     * Marks a task as undone.
     * @param index The index of the task to be marked as undone.
     * @return A string that indicates if the task has been marked as undone successfully.
     * @throws DukeException If the index is out of range.
     */
    public String unmarkTask(int index) throws DukeException {
        try {
            tasks.get(index - 1).markAsUndone();
            storage.editLineInFile(index, 0);
            reply = "This task is marked as not done:\n"
                    + tasks.get(index - 1).toString();
            return reply;
        } catch (IndexOutOfBoundsException | IOException e) {
            throw new DukeException("Please enter index ranging from 1 to " + tasks.size());
        }
    }


}
