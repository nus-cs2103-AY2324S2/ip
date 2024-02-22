package dune.task;

import dune.Parser;
import dune.Storage;
import dune.DuneException;

import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.ArrayList;

/**
 * Represents a list of tasks. A TaskList has a list of tasks.
 */
public class TaskList {
    private List<Task> tasks;

    private static String before = "Start date must be before end date";

    /**
     * Constructor for TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Finds tasks that contain the given string.
     *
     * @param string The string to search for.
     */
    public String find(String string) {
        List<Task> found = new ArrayList<>();
        for (Task t : this.tasks) {
            if (t.getDescription().contains(string)) {
                found.add(t);
            }
        }
        if (found.size() == 0) {
            return "No tasks found with that description\n";
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Here are the matching tasks in your list:\n");
            for (int i = 0; i < found.size(); i++) {
                sb.append(i + 1 + "." + found.get(i));
            }
            return sb.toString();
        }
    }

    /**
     * Adds task to list of tasks, provided there is no duplicate.
     *
     * @param task
     */
    public void addTask(Task task) {
        if (!this.tasks.contains(task)) {
            this.tasks.add(task);
        }
    }

    /**
     * Adds task to list of tasks given a string, provided there is no duplicate.
     *
     * @param command  Type of task.
     * @param text  Description of task.
     * @param storage  Storage object to save tasks.
     */
    public String addTask(Parser.Commands command, String text, Storage storage) {
        Task x = null;
        try {
            try {
                if (command == Parser.Commands.TODO) {
                    x = new ToDo(text.trim());
                } else if (command == Parser.Commands.DEADLINE) {
                    x = createDeadline(text);
                } else if (command == Parser.Commands.EVENT) {
                    x = createEvent(text);
                } else {
                    throw new DuneException("Unrecognized event type");
                }
            } catch (DateTimeParseException dt) {
                throw new DuneException("Enter date in the format yyyy-mm-ddTHH:MM");
            }
        } catch (DuneException d) {
            return d.toString();
        }

        if (this.tasks.contains(x)) {
            return "Task already exists in list\n";
        }

        this.tasks.add(x);
        storage.saveTasks(this);
        StringBuilder sb = new StringBuilder();
        sb.append("Got it. I've added this task:\n");
        sb.append(x + "\n");
        sb.append("Now you have " + this.tasks.size() + " tasks in your list.\n");
        return sb.toString();
    }

    /**
     * Creates a new Deadline with the given String.
     *
     * @param text
     * @return The new task created.
     * @throws DuneException
     * @throws DateTimeParseException
     */
    public Task createDeadline(String text) throws DuneException, DateTimeParseException {
        String[] parts = text.split("/by");
        if (parts.length < 2) {
            throw new DuneException("Deadlines need a /by (some date) ");
        } else if (parts.length > 2) {
            throw new DuneException("There can only be 1 instance of /by. String cannot be parsed...");
        }
        return new Deadline(parts[0].trim(), parts[1].trim());
    }


    /**
     * Creates a new Event with the given String.
     *
     * @param text
     * @return The new task created.
     * @throws DuneException
     * @throws DateTimeParseException
     */
    public Task createEvent(String text) throws DuneException, DateTimeParseException {
        String[] parts = text.split("/from");

        if (parts.length < 2) {
            throw new DuneException("Events need a /from and a /to in this order");
        }
        String[] dates = parts[1].split("/to");
        if (parts.length > 2 || dates.length > 2) {
            throw new DuneException("There can only be 1 instance of /from and /to\n" +
                    "String cannot be parsed...");
        } else if (dates.length < 2) {
            throw new DuneException("Events need a /from and a /to in this order");
        }
        return new Event(parts[0].trim(), dates[0].trim(), dates[1].trim());
    }

    /**
     * Deletes task from list of tasks at given position.
     *
     * @param index Position of task to be deleted.
     */
    private void deleteTask(int index) {
        this.tasks.remove(index);
    }

    /**
     * Deletes task from list of tasks at given string of position.
     *
     * @param indexStr Position of task to be deleted, as a string.
     * @param storage Storage object to save tasks.
     */
    public String deleteTask(String indexStr, Storage storage) {
        String response = "";
        try {
            if (indexStr.trim().equals("")) {
                throw new DuneException("Give an index to remove");
            }
            int index = Integer.parseInt(indexStr.trim());
            Task t = this.getTask(index - 1);
            this.deleteTask(index - 1);
            response += "Noted. I've removed this task:\n";
            response += t.toString() + "\n";
            response += "Now you have " + this.getSize() + " tasks in the list.\n";
            storage.saveTasks(this);

        } catch (NumberFormatException n) {
            response = "Index to be removed needs to be an integer\n";
        } catch (IndexOutOfBoundsException i) {
            response = "Give a valid index to remove\n";
        } catch (DuneException d) {
            response = d.toString() + "\n";
        } finally {
            return response;
        }
    }

    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    public int getSize() {
        return this.tasks.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n");
        for (int i = 0; i < this.tasks.size(); i++) {
            sb.append(i + 1).append(". ").append(this.tasks.get(i)).append("\n");
        }
        return sb.toString();
    }

}
