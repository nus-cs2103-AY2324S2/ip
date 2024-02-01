package duke;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.ArrayList;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Represents the tasklist and executes the tasks given
 * (i.e. list, write, addTask, mark, unmark, delete).
 * Contains an ArrayList<Task> tasklist and Storage s.
 */

public class TaskList {
    private ArrayList<Task> tasklist = new ArrayList<Task>();
    private Storage s;

    public TaskList(Storage s) {
        this.s = s;
    }

    /**
     * Writes current task to the file which is stored in Storage s.
     *
     * @throws IOException When File f cannot be found.
     */
    public void write() throws IOException {
        for (int i = 0; i < tasklist.size(); i++) {
            tasklist.get(i).writeToFile(s.getFile());
        }
    }

    /**
     * Lists all tasks from previous iterations of Duke.run() which are stored in
     * Storage s as well as current tasks in current iterations of Duke.run().
     */
    public void list() throws FileNotFoundException {
        try {
            System.out.println("All tasks:");
            s.getFileContent();
            System.out.println();
            System.out.println("Current tasks: ");
            for (int i = 0; i < tasklist.size(); i++) {
                int j = i + 1;
                System.out.println("    " + j + ". " + tasklist.get(i).getCat()
                        + tasklist.get(i).marked() + " "
                        + tasklist.get(i).getTask() + tasklist.get(i).getDetails());
            }
        } catch (FileNotFoundException e) {
            System.out.println("file not found! try again xx");
        }
    }

    /**
     * Adds Todo task to tasklist.
     * @param task Todo task to be added to tasklist.
     * @throws StringIndexOutOfBoundsException When user does not specify task and leaves blank (e.g. todo).
     */
    public void addTask(Todo task) throws StringIndexOutOfBoundsException {
        try {
            tasklist.add(task);
            System.out.println("Got it. I've added this task:");
            System.out.println(task.add());
            System.out.println("Now you have " + tasklist.size() + " tasks in the list.");

        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("ENTER INSTRUCTION");
        }

    }

    /**
     * Adds Deadline task to tasklist.
     * @param task Deadline task to be added to tasklist.
     * @throws StringIndexOutOfBoundsException When user does not specify task and leaves blank (e.g. deadline).
     */
    public void addTask(Deadline task) throws StringIndexOutOfBoundsException {
        try {
            tasklist.add(task);
            System.out.println("Got it. I've added this task:");
            System.out.println(task.add());
            System.out.println("Now you have " + tasklist.size() + " tasks in the list.");

        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("ENTER INSTRUCTION");
        }

    }

    /**
     * Adds Event task to tasklist.
     * @param task Event task to be added to tasklist.
     * @throws StringIndexOutOfBoundsException When user does not specify task and leaves blank (e.g. event).
     */

    public void addTask(Event task) throws StringIndexOutOfBoundsException {
        try {
            tasklist.add(task);
            System.out.println("Got it. I've added this task:");
            System.out.println(task.add());
            System.out.println("Now you have " + tasklist.size() + " tasks in the list.");

        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("ENTER INSTRUCTION");
        }

    }

    /**
     * Marks task in tasklist as done.
     * @param number Task number in current list to be marked.
     * @throws IndexOutOfBoundsException When user inputs task number that does not exist in the list.
     */
    public void mark(int number) throws IndexOutOfBoundsException {
        try {
            Task task = tasklist.get(number);
            task.setDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(task.mark(number));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("No such task to mark.");
        }
    }

    /**
     * Unmarks task in tasklist.
     * @param number Task number in current list to be unmarked.
     * @throws IndexOutOfBoundsException When user inputs task number that does not exist in the list.
     */
    public void unmark(int number) throws IndexOutOfBoundsException {
        try {
            Task task = tasklist.get(number);
            task.setNotDone();
            System.out.println("Ok, I've marked this task as not done yet:");
            System.out.println(task.unmark(number));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("No such task to unmark.");
        }

    }

    /**
     * Deletes task in tasklist.
     * @param number Task number in current list to be deleted.
     * @throws IndexOutOfBoundsException When user inputs task number that does not exist in the list.
     */
    public void delete(int number) throws IndexOutOfBoundsException {
        try {
            Task task = tasklist.get(number);
            tasklist.remove(number);
            System.out.println("Noted. I've removed this task: ");
            System.out.println(task.delete());
            System.out.println("Now you have " + tasklist.size() + " tasks in the current list.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("No such task to delete.");
        }
    }

    public void find(String str) throws StringIndexOutOfBoundsException {
        try {
            int j = 1;
            for (int i = 0; i < tasklist.size(); i++) {
                if (tasklist.get(i).isFound(str)) {
                    if (j == 1) {
                        System.out.println("Here are the matching tasks in your list:");
                    }
                    System.out.println("    " + j + ". " + tasklist.get(i).getCat()
                                        + tasklist.get(i).marked() + " "
                                        + tasklist.get(i).getTask()
                                        + tasklist.get(i).getDetails());
                    j++;
                }
            }
            if (j == 1) {
                System.out.println("No matching tasks!");
            }
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("ENTER INSTRUCTION");
        }
    }
}

