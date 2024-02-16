package rick.util;

import java.util.ArrayList;

import rick.logic.RickException;
import rick.tasks.Deadline;
import rick.tasks.Event;
import rick.tasks.Task;
import rick.tasks.ToDo;


/**
 * List to store the items that the user creates.
 */
public class TaskList {
    private ArrayList<Task> items;

    /**
     * Creates a new instance of TaskList.
     */
    public TaskList() {
        this.items = new ArrayList<>();
    }

    /**
     * Creates a new instance of TaskList with an existing ArrayList.
     * @param list an ArrayList to be transformed into a TaskList.
     */
    public TaskList(ArrayList<Task> list) {
        this.items = list;
    }

    /**
     * Returns a string representation of the current list.
     * @return a user-friendly string representation of the current list.
     */
    public String list() {
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < items.size(); i++) {
            string.append((i + 1) + ". " + items.get(i) + "\n");
        }
        return string.toString();
    }

    /**
     * Adds an item into the list based on given input and update the local data.
     * @param arg the user input which specifies the type, name, date and time of the item.
     * @param storage the storage item to be used for storing the new item into local data.
     * @return a string that tells the user that the item has been added successfully.
     * @throws RickException when there is an error with the input command.
     */
    public String addToList(String arg, Storage storage) throws RickException {
        Task newTask;
        String[] splited = arg.split("\\s+");
        int last = splited.length - 1;
        if (splited.length == 1) {
            throw new RickException("What THING do you want to do...");
        }
        try {
            switch (splited[0]) {
            case "todo":
                newTask = new ToDo(arg.substring(5), "[ ]");
                this.items.add(newTask);
                break;
            case "deadline": {
                if (!arg.contains(" /by ") || splited[last].equals("/by")) {
                    throw new RickException("When is it due? You haven't told me!");
                }
                if (splited[1].equals("/by")) {
                    throw new RickException("What's the deadline about?");
                }
                int i = arg.indexOf("/by");
                String ddl = arg.substring(i + 4);
                String name = arg.substring(9, i - 1);
                newTask = new Deadline(name, "[ ]", ddl);
                this.items.add(newTask);
                break;
            }
            case "event": {
                if (!arg.contains(" /from ") || !arg.contains(" /to ")
                        || splited[last].equals("/to") || splited[last].equals("/from")) {
                    throw new RickException("WHEN is the event?");
                }
                if (splited[1].equals("/from") || splited[1].equals("/to")) {
                    throw new RickException("What event is this?");
                }
                int i = arg.indexOf("/from ");
                int j = arg.indexOf("/to ");
                String name = arg.substring(6, i - 1);

                String from = arg.substring(i + 6, j - 1);
                String to = arg.substring(j + 4);
                newTask = new Event(name, "[ ]", from, to);
                this.items.add(newTask);
                break;
            }
            default:
                throw new RickException("It seems that you are missing the space in your instruction. Homesick alien?");
            }
        } catch (RickException e) {
            throw e;
        } catch (Exception e1) {
            throw new RickException("ERROR: Congratulations! "
                    + "You have input a message that the developer did not expect. "
                    + "Report this issue here: https://forms.gle/hnnDTA7qYMnhJvQ46.");
        }
        storage.update();
        return "Got it. I've added this task:\n"
                + newTask
                + "\nNow you have " + items.size() + " tasks in the list.";
    }

    /**
     * Marks a specified task as done.
     * @param index the index of task to be marked.
     * @return a string representation to tell user that the status has been updated successfully.
     * @throws RickException if the index is out of range.
     */
    public Task mark(int index) throws RickException {
        if (index >= this.items.size()) {
            throw new RickException("rick.tasks.Item not found QAQ");
        } else {
            Task task = this.items.get(index);
            task.mark();
            return task;
        }
    }

    /**
     * Unmarks a specified task as not done.
     * @param index the index of task to be unmarked.
     * @return a string representation to tell user that the status has been updated successfully.
     * @throws RickException if the index is out of range.
     */
    public Task unmark(int index) throws RickException {
        if (index >= this.items.size()) {
            throw new RickException("rick.tasks.Item not found QAQ");
        } else {
            Task task = this.items.get(index);
            task.unmark();
            return task;
        }
    }

    /**
     * Delete a specified task.
     * @param index the index of task to be deleted.
     * @return a string representation to tell user that the status has been updated successfully.
     * @throws RickException if the index is out of range.
     */
    public Task delete(int index) throws RickException {
        try {
            Task task = this.items.remove(index);
            return task;
        } catch (Exception e) {
            throw new RickException("Your index is wrong :(");
        }
    }

    /**
     * Find items containing a specific substring.
     * @param arg the substring to look for.
     * @return list of items which contain the substring.
     */
    public String find(String arg) {
        StringBuilder results = new StringBuilder();
        boolean isFound = false;
        int counter = 0;
        for (int i = 0; i < this.items.size(); i++) {
            if (this.items.get(i).toString().contains(arg)) {
                counter++;
                results.append(counter + ". " + this.items.get(i) + "\n");
                isFound = true;
            }
        }
        if (isFound) {
            return "Here are the matching tasks in your list:\n" + results;
        } else {
            return "OOPS! There's no matching result in your list. ";
        }
    }
    public int getSize() {
        return this.items.size();
    }
}
