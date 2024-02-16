package grizzly.utils;

import java.util.ArrayList;

import grizzly.contacts.Contact;
import grizzly.tasks.Task;

/**
 * This class implementions the functionality of a database with
 * variable length lists containg Task and Contact objects.
 *
 * @author delishad21
 */
public class Database {
    private ArrayList<Task> taskList;
    private ArrayList<Contact> contactList;

    /**
     * Creates a TaskList that contains an ArrayList.
     */
    public Database() {
        this.taskList = new ArrayList<>();
        this.contactList = new ArrayList<>();
    }

    /**
     * Prints out all items in the TaskList.
     *
     */
    @Override
    public String toString() {
        return tasksToString() + contactsToString();
    }

    /**
     * Prints out all stored tasks
     *
     */
    public String tasksToString() {
        String s = "";

        s += "----------\nTasks\n----------\n";

        for (int i = 1; i <= taskList.size(); i++) {
            s += i + "." + taskList.get(i - 1) + "\n";
        }

        return s;
    }

    /**
     * Prints out all stored contacts
     *
     */
    public String contactsToString() {
        String s = "";

        s += "----------\nContacts\n----------\n";

        for (int i = 1; i <= contactList.size(); i++) {
            s += i + "." + contactList.get(i - 1) + "\n";
        }

        return s;
    }

    /**
     * Adds task to list.
     *
     * @param t Task that is to be added to list.
     */
    public void addTask(Task t) {
        this.taskList.add(t);
    }

    /**
     * Adds contact to list
     *
     * @param c Contact to be added to list
     */
    public void addContact(Contact c) {
        this.contactList.add(c);
    }

    /**
     * Removes task from list based on task input.
     *
     * @param t Task that is to be removed from list.
     */
    public void removeTask(Task t) {
        this.taskList.remove(t);
    }

    /**
     * Removes Task from list based on index.
     *
     * @param i Index of task to be removed from list.
     */
    public void removeTask(int i) {
        this.taskList.remove(i - 1);
    }

    /**
     * Removes Contact from list based on Contact input.
     *
     * @param c Task that is to be removed from list.
     */
    public void removeContact(Contact c) {
        this.contactList.remove(c);
    }

    /**
     * Removes Contact from list based on index.
     *
     * @param i Index of contact to be removed from list.
     */
    public void removeContact(int i) {
        this.contactList.remove(i - 1);
    }

    /**
     * Gets items from TaskList using index.
     *
     * @param i Index of item to be retrieved.
     * @return Task retrieved from TaskList.
     */
    public Task getTask(int i) {
        return this.taskList.get(i - 1);
    }

    /**
     * Gets items from ContactList using index.
     *
     * @param i Index of item to be retrieved.
     * @return Task retrieved from TaskList.
     */
    public Contact getContact(int i) {
        return this.contactList.get(i - 1);
    }

    /**
     * Gets current size of TaskList.
     *
     * @return Size of TaskList as integer.
     */
    public int taskListSize() {
        return this.taskList.size();
    }

    /**
     * Gets current size of ContactList.
     *
     * @return Size of ContactList as integer.
     */
    public int contactListSize() {
        return this.contactList.size();
    }

}
