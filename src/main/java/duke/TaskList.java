package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

/**
 * TaskList class to represent the collection of tasks being maintained.
 */
public class TaskList {
    private List<Task> list;

    private Storage storage;

    /**
     * Creates an empty instance of a TaskList from scratch
     * @param storage Storage object to help store the list.
     */
    public TaskList(Storage storage) {
        this.list = new ArrayList<>();
        this.storage = storage;
    }

    /**
     * Creates a TaskList based on data file
     * @param storage Storage object to help store the list
     * @param f File to load TaskList from
     * @throws FileNotFoundException If file is not found.
     */
    public TaskList(Storage storage, File f) throws FileNotFoundException {
        this.list = new ArrayList<>();
        this.storage = storage;

        Scanner scanner = new Scanner(f);
        while (scanner.hasNext()) {
            String curr = scanner.nextLine();
            String[] parts = curr.split("\\|");
            for (int i = 0; i < parts.length; ++i) {
                parts[i] = parts[i].trim();
            }

            String taskType = parts[0];
            String done = parts[1];
            String name = parts[2];
            String tag = parts[3];
            Task newTask;
            if (taskType.equals("T")) {
                newTask = new Todo(name);
            } else if (taskType.equals("D")) {
                newTask = new Deadline(name, parts[3]);
            } else {
                newTask = new Event(name, parts[3], parts[4]);
            }
            if (done.equals("1")) {
                newTask.mark();
            }
            if (!tag.equals("")) {
                newTask.tag(tag);
            }
            add(newTask);
        }
    }

    /**
     * Adds the specified task to the list.
     * @param task Task to be added.
     */
    public void add(Task task) {
        list.add(task);
        save();
    }

    /**
     * Deletes the specified task from the list.
     * @param index Index to delete from.
     * @return Returns the task deleted.
     */
    public Task delete(int index) {
        Task removed = list.remove(index - 1);
        save();
        return removed;
    }

    /**
     * Marks the specified task as either done / not done
     * @param index Index of task to be marked
     * @param mark If set to true, task is marked. If set to false, task is unmarked.
     */
    public void mark(int index, boolean mark) {
        if (mark) {
            list.get(index - 1).mark();
        } else {
            list.get(index - 1).unmark();
        }
        save();
    }

    /**
     * Tags the specified task
     * @param index Index of task to be tagged
     * @param tag Tag to be displayed
     */
    public void tag(int index, String tag) {
        list.get(index - 1).tag(tag);
    }

    /**
     * Returns the size of the task list.
     * @return Size of task list.
     */
    public int getSize() {
        return list.size();
    }

    /**
     * Returns the task at the specified index (input is 1-indexed)
     * @param index
     * @return The specified task at index
     */
    public Task getTaskByIndex(int index) {
        return list.get(index - 1);
    }

    /**
     * Saves the current state of task list to Storage object.
     */
    private void save() {
        String text = "";
        for (Task t : list) {
            text += t.fileString();
            text += "\n";
        }
        try {
            storage.writeToFile(text);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 1; i <= list.size(); ++i) {
            s += (i + ". " + list.get(i-1).toString() + '\n');
        }
        return s;
    }

}
