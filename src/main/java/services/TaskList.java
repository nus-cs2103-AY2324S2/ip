package services;

import exceptions.DukeException;
import tasks.Task;
import services.parser.Parser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The TaskList class manages a list of tasks, allowing for adding, deleting, marking, and unmarking tasks.
 */
public class TaskList {
    private static final int MAX_ITEMS = 100;
    private List<Task> tasks;
    private final String FILE_PATH = "./data/duke.txt";

    /**
     * Constructs a new TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Returns the list of tasks.
     *
     * @return List of tasks.
     */
    public List<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
        System.out.println("\tGot it. I've added this task: ");
        System.out.println("\t" + this.tasks.get(this.tasks.size() - 1));
        System.out.println(
                "\tNow you have " + this.tasks.size() + " task" +
                        (this.tasks.size() == 1 ? "" : "s") + " in the list");
    }

    /**
     * Deletes a task from the task list based on its index.
     *
     * @param index The index of the task to be deleted.
     * @throws DukeException If the index is out of range.
     */
    public void deleteTask(int index) throws DukeException {
        if (this.tasks.size() == 0) {
            throw new DukeException("Task index is out of range.");
        }
        if (index <= 0 || index > this.tasks.size()) {
            throw new DukeException("Index out of range");
        }
        Task deletedTask = this.tasks.remove(index - 1);
        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t" + deletedTask.toString());
        System.out.println(
                "\tNow you have " + this.tasks.size() + " task" +
                        (this.tasks.size() == 1 ? "" : "s") + " in the list");
    }

    /**
     * Marks a task as done based on its index.
     *
     * @param index The index of the task to be marked as done.
     * @throws DukeException If the index is out of range.
     */
    public void markTask(int index) throws DukeException {
        if (index <= 0 || index > this.tasks.size()) {
            throw new DukeException("Task index is out of range.");
        }
        Task currTask = this.tasks.get(index - 1);
        currTask.markAsDone();
        System.out.println("\tNice! I've marked this task as done:");
        currTask.toString();
    }

    /**
     * Unmarks a task (marks as not done) based on its index.
     *
     * @param index The index of the task to be unmarked.
     * @throws DukeException If the index is out of range.
     */
    public void unmarkTask(int index) throws DukeException {
        if (index <= 0 || index > this.tasks.size()) {
            throw new DukeException("Task index is out of range.");
        }
        Task currTask = this.tasks.get(index - 1);
        currTask.markAsUndone();
        System.out.println("\tOK, I've marked this task as not done yet:");
        currTask.toString();
    }

    /**
     * Lists all tasks in the task list.
     */
    public void listTasks() {
        if (this.tasks.size() == 0) {
            System.out.println("\tThe task list is empty.");
        } else {
            System.out.println("\tHere are the tasks in your list: ");
            for (int i = 0; i < this.tasks.size(); i++) {
                Task currTask = this.tasks.get(i);
                System.out.println("\t" + (i + 1) + "." + currTask.toString());
            }
        }
    }

    public List<Task> findTasks(String word) throws DukeException {
        Pattern pattern = Pattern.compile(Pattern.quote(word), Pattern.CASE_INSENSITIVE);
        List<Task> foundTasks = new ArrayList<>();
        for (Task task : this.tasks) {
            Matcher matcher = pattern.matcher(task.getName());
            if (matcher.find()) {
                foundTasks.add(task);
            }
<<<<<<< HEAD
=======
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String taskString = scanner.nextLine();
                Task currTask = Parser.parseTaskFromString(taskString);
                this.tasks.add(currTask);
            }
        } catch (IOException e) {
            System.out.println("Error occurred when writing to file");
        } catch (DukeException e) {
            System.out.println("Error occurred when parsing file");
>>>>>>> branch-A-CodingStandard
        }
        if (foundTasks.size() == 0) {
            throw new DukeException("No tasks with " + word + " found");
        }

        System.out.println("\tHere are the matching tasks in your list: ");
        for (int i = 0; i < foundTasks.size(); i++) {
            Task currTask = foundTasks.get(i);
            System.out.println("\t" + (i + 1) + "." + currTask.toString());
        }
        return foundTasks;
    }
}
