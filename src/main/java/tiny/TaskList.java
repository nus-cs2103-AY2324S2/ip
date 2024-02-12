package tiny;

import java.util.ArrayList;

import tiny.exceptions.TinyException;
import tiny.tasks.Deadline;
import tiny.tasks.Event;
import tiny.tasks.Task;
import tiny.tasks.Todo;

/**
 * Represents the tasklist to manage all the tasks.
 */
public class TaskList {
    protected ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Initializes TaskList when there is no tasks to load.
     */
    public TaskList() {
    }

    /**
     * Initializes TaskList with data to load.
     *
     * @param data The tasks to be added to the local tasks ArrayList.
     */
    public TaskList(ArrayList<String> datas) throws TinyException {
        // Parse the data here.
        for (int i = 0; i < datas.size(); i++) {
            String[] entry = datas.get(i).split(" \\| ");
            if (entry[0].equals("T")) {
                Todo todo = new Todo(entry[2], !entry[1].equals("0"));
                tasks.add(todo);
            } else if (entry[0].equals("D")) {
                Deadline deadline = new Deadline(entry[2], !entry[1].equals("0"), entry[3]);
                tasks.add(deadline);
            } else if (entry[0].equals("E")) {
                Event event = new Event(entry[2], !entry[1].equals("0"), entry[3], entry[4]);
                tasks.add(event);
            }
        }
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public void delete(Integer ind) {
        tasks.remove(tasks.get(ind));
    }

    public Task get(Integer ind) {
        return tasks.get(ind);
    }

    public Integer size() {
        return tasks.size();
    }

    /**
     * Finds all macthing tasks in the task list.
     *
     * @param keyword Keyword to search for.
     * @return String of all of the tasks.
     */
    public String find(String keyword) {
        int index = 1;
        String output = "Here are the matching tasks in your list:";
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).descriptionSearch(keyword)) {
                output += "\n";
                output += index + ". " + tasks.get(i);
                index++;
            }
        }

        if (output.equals("Here are the matching tasks in your list:")) {
            return "No matching results.";
        }

        return output;
    }

    /**
     * Lists out all the tasks in the task list.
     *
     * @return String of all of the tasks.
     */
    public String list() {
        if (tasks.size() == 0) {
            return "You don't have any tasks!";
        }
        String output = "";
        for (int i = 0; i < tasks.size(); i++) {
            output += (i + 1) + ". " + tasks.get(i);
            output += "\n";
        }
        return output;
    }

    /**
     * Formats all the tasks into the correct format to save.
     *
     * @return ArrayList of tasks in the correct format to save.
     */
    public ArrayList<String> toSave() {
        ArrayList<String> tasksToSave = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            tasksToSave.add(tasks.get(i).toSave());
        }
        return tasksToSave;
    }

}
