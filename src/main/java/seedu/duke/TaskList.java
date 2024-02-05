package seedu.duke;

import seedu.duke.task.Task;
import java.io.IOException;
import java.util.ArrayList;

/**
 * <h1> TaskList </h1>
 * This TaskList class is used to store all the Task class objects in an arraylist.
 * When Storage class loads the existing tasks from the file, the tasks are created and added to
 * TaskList.
 *
 * @author Yap Xuan Xuan
 * @version 0.1
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() { //constructor
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds the Task object to the arraylist and calls the function in Storage to store
     * the information of the newly added task in the file. Prints out the details of the
     * newly added task to be displayed to the user.
     * @param task Task object to be added to the arraylist.
     * @param isNew boolean that indicates whether the task is an existing one loaded from
     *              the file or a newly added one.
     */
    public void addItem(Task task, boolean isNew) { //to append items to tasks
        this.tasks.add(task);
        if (isNew) {
            System.out.print("      ________________________________________________________\n");
            System.out.print("      Got it. I've added this task:\n ");
            task.printFullDesc();
            System.out.printf("      Now you have %d %s in the tasks.\n", this.tasks.size(), (this.tasks.size() == 1 ? "task" : "tasks"));
            System.out.print("      ________________________________________________________\n");
            Storage.add(task);
        }
    }

    /**
     * Removes the Task object at the index of num from the arraylist and also calls the function in
     * Storage to delete the information of the task stored in the file.
     * @param num index of task to be deleted from the task list
     */
    public void delete(int num) {
        Task taskToDelete = this.tasks.get(num - 1);
        System.out.print("      ________________________________________________________\n");
        System.out.print("      Got it. I've removed this task:\n ");
        taskToDelete.printFullDesc();
        this.tasks.remove(num - 1);
        System.out.printf("      Now you have %d %s in the tasks.\n", this.tasks.size(), (this.tasks.size() == 1 ? "task" : "tasks"));
        System.out.print("      ________________________________________________________\n");
        Storage.delete(num - 1);
    }

    /**
     * Calls the mark function of the Task object and also calls the function in
     * Storage to update the information stored in the file.
     * @param num index of task to be marked done.
     */
    public void mark(int num) {
        Task toMark = this.tasks.get(num - 1);
        toMark.markDone(true);
        String toReplace = toMark.toStore();
        try {
            Storage.changeMarking(num - 1, toReplace);
        } catch (IOException e) {
            System.out.println("Oops something went wrong.\n" + e.getMessage());
        }
    }

    /**
     * Calls the unmark function of the Task object and also calls the function in
     * Storage to update the information stored in the file.
     * @param num index of task to be marked undone.
     */
    public void unmark(int num) {
        Task toUnmark = this.tasks.get(num - 1);
        String toDelete = toUnmark.toStore();
        toUnmark.unmark();
        String toReplace = toUnmark.toStore();
        try {
            Storage.changeMarking(num - 1, toReplace);
        } catch (IOException e) {
            System.out.println("Oops something went wrong.\n" + e.getMessage());
        }
    }

    /**
     * Returns the Task object at the index position of the task list.
     * @param index the index of the task in the task list.
     * @return Task object that is in the position of the index in the arraylist.
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Prints information of all the lists in the task list by calling their individual functions
     * from the Task class or its subclasses Deadlines and Events to print individual task descriptions.
     * Prints out that there is 0 tasks in the task list if the arraylist of tasks is empty.
     */
    public void printList() {
        if (this.tasks.size() == 0) {
            System.out.print("      ________________________________________________________\n");
            System.out.print("      Currently you have 0 tasks in your task list!\n");
            System.out.print("      ________________________________________________________\n");
        } else {
            for (int i = 0; i < this.tasks.size(); i++) {
                if (i < this.tasks.size() - 1 && this.tasks.size() != 1) { //not last element
                    this.tasks.get(i).printTaskDesc(i + 1, false);
                } else {
                    this.tasks.get(i).printTaskDesc(i + 1, true);
                }
            }
        }
    }
}
