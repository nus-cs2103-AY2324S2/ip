package oop;

import java.util.ArrayList;

import task.Task;

import exceptions.DuplicateInstructionException;
import exceptions.OutOfIndexException;


/**
 * Represents a list of tasks in the task manager application.
 * TaskList manages the tasks, such as adding, marking, unmarking, and deleting tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private static final String LINE = "\t______________________________________________________";

    /**
     * Constructs a TaskList with the specified list of tasks.
     *
     * @param tasks The list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Gets the number of tasks in the task list.
     *
     * @return The number of tasks in the list.
     */
    public int size() {
        return tasks.size();
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Marks the task at the specified index as done.
     * If the index is out of range or the task is already marked as done, appropriate error messages are displayed.
     *
     * @param index The index of the task to mark as done.
     */
    public void mark(int index) {
        try {
            if (tasks.size() < index) {
                throw new OutOfIndexException();
            } else if (tasks.get(index - 1).getStatusIcon().equals("X")) {
                throw new DuplicateInstructionException();
            }
            tasks.get(index - 1).markAsDone();
            System.out.println(LINE);
            System.out.println("\t Nice! I've marked this task as done:" + "\n\t\t" +
                    tasks.get(index - 1).print());
            System.out.println(LINE);
        } catch (OutOfIndexException e) {
            System.out.println(LINE);
            System.out.println(e.toString(tasks.size()));
            System.out.println(LINE);
        } catch (DuplicateInstructionException e) {
            System.out.println(LINE);
            System.out.println(e.toString("mark"));
            System.out.println(LINE);
        }
    }

    /**
     * Marks the task at the specified index as not done yet (undone).
     * If the index is out of range or the task is already marked as not done, appropriate error messages are displayed.
     *
     * @param index The index of the task to mark as not done yet.
     */
    public void unmark(int index) {
        try {
            if (tasks.size() < index) {
                throw new OutOfIndexException();
            } else if (tasks.get(index - 1).getStatusIcon().equals(" ")) {
                throw new DuplicateInstructionException();
            }
            tasks.get(index - 1).unmarkAsDone();
            System.out.println(LINE);
            System.out.println("\t OK, I've marked this task as not done yet:" + "\n\t\t" +
                    tasks.get(index - 1).print());
            System.out.println(LINE);
        } catch (OutOfIndexException e) {
            System.out.println(LINE);
            System.out.println(e.toString(tasks.size()));
            System.out.println(LINE);
        } catch (DuplicateInstructionException e) {
            System.out.println(LINE);
            System.out.println(e.toString("unmark"));
            System.out.println(LINE);
        }
    }

    /**
     * Deletes the task at the specified index from the task list.
     * If the index is out of range, appropriate error message is displayed.
     *
     * @param index The index of the task to delete.
     */
    public void delete(int index) {
        try {
            if (tasks.size() < index || index < 1) {
                throw new OutOfIndexException();
            }
            System.out.println(LINE);
            System.out.println("\t OK, I've removed this task:" + "\n\t\t" +
                    tasks.get(index - 1).print());
            tasks.remove(index - 1);
            System.out.println("\t Now you have " + tasks.size() + " tasks in the list.");
            System.out.println(LINE);
        } catch (OutOfIndexException e) {
            System.out.println(LINE);
            System.out.println(e.toString(tasks.size()));
            System.out.println(LINE);
        }
    }

    /**
     * Adds the given task to the task list.
     * If the task description already exists in the list, appropriate error message is displayed.
     *
     * @param task The task to be added to the list.
     */
    public void add(Task task){
        try {
            for (Task value : tasks) {
                if (value.getDescription().equals(task.getDescription())) {
                    throw new DuplicateInstructionException();
                }
            }
            tasks.add(task);
            System.out.println(LINE);
            System.out.println("\t Got it. I've added this task:");
            System.out.print("\t   " + task.print());
            System.out.println("\n\t Now you have " + tasks.size() + " tasks in the list.");
            System.out.println(LINE);
        } catch (DuplicateInstructionException e) {
            System.out.println(LINE);
            System.out.println(e.toString(""));
            System.out.println(LINE);
        }
    }
}
