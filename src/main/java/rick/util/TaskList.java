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
    private ArrayList<Task> tasks;

    /**
     * Creates a new instance of TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates a new instance of TaskList with an existing ArrayList.
     * @param list an ArrayList to be transformed into a TaskList.
     */
    public TaskList(ArrayList<Task> list) {
        this.tasks = list;
    }

    /**
     * Returns a string representation of the current list.
     * @return a user-friendly string representation of the current list.
     */
    public String list() {
        StringBuilder string = new StringBuilder();
        string.append("Fine, Morty, here's your list of tasks. Try not to mess it up:\n");
        for (int i = 0; i < tasks.size(); i++) {
            string.append((i + 1) + ". " + tasks.get(i) + "\n");
        }
        string.append("Don't make me regret this, Morty.");
        return string.toString();
    }

    /**
     * Adds a new to-do task into the task list based on user input.
     * @param name name of the to-do task.
     * @return the new task
     */
    public Task addToList(String name) throws RickException {
        try {
            ToDo newTodo = new ToDo(name, "[ ]");
            this.tasks.add(newTodo);
            return newTodo;
        } catch (RickException e) {
            throw e;
        }
    }

    /**
     * Adds a new deadline task into the task list based on user input.
     * @param name name of the deadline task.
     * @return the new task
     */
    public Task addToList(String name, String deadline) throws RickException {
        try {
            Deadline newDeadline = new Deadline(name, "[ ]", deadline);
            this.tasks.add(newDeadline);
            return newDeadline;
        } catch (RickException e) {
            throw e;
        }
    }

    /**
     * Adds a new event task into the task list based on user input.
     * @param name name of the event task.
     * @return the new task
     */
    public Task addToList(String name, String from, String to) throws RickException {
        try {
            Event newEvent = new Event(name, "[ ]", from, to);
            for (Task task : this.tasks) {
                if (task instanceof Event) {
                    assert (task instanceof Event);
                    Event.checkClash(newEvent, (Event) task);
                }
            }
            this.tasks.add(newEvent);
            return newEvent;
        } catch (RickException e) {
            throw e;
        }
    }

    /**
     * Marks a specified task as done.
     * @param index the index of task to be marked.
     * @return a string representation to tell user that the status has been updated successfully.
     * @throws RickException if the index is out of range.
     */
    public Task mark(int index) throws RickException {
        if (index >= this.tasks.size()) {
            throw new RickException("rick.tasks.Item not found QAQ");
        } else {
            Task task = this.tasks.get(index);
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
        if (index >= this.tasks.size()) {
            throw new RickException("rick.tasks.Item not found QAQ");
        } else {
            Task task = this.tasks.get(index);
            task.unmark();
            return task;
        }
    }

    /**
     * Deletes a specified task.
     * @param index the index of task to be deleted.
     * @return a string representation to tell user that the status has been updated successfully.
     * @throws RickException if the index is out of range.
     */
    public Task delete(int index) throws RickException {
        try {
            Task task = this.tasks.remove(index);
            return task;
        } catch (Exception e) {
            throw new RickException("Your index is wrong :(");
        }
    }

    /**
     * Finds items containing a specific substring.
     * @param arg the substring to look for.
     * @return list of items which contain the substring.
     */
    public String find(String arg) {
        StringBuilder results = new StringBuilder();
        boolean isFound = false;
        int counter = 0;
        for (int i = 0; i < this.tasks.size(); i++) {
            if (this.tasks.get(i).toString().contains(arg)) {
                counter++;
                results.append(counter + ". " + this.tasks.get(i) + "\n");
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
        return this.tasks.size();
    }
}
