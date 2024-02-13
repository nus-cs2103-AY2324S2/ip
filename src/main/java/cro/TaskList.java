package cro;

import tasks.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a TaskList in the chat-bot. A TaskList object is responsible for keeping track of the tasks currently
 * in the system.
 */
public class TaskList {
    public List<Task> taskList = new ArrayList<>();
    static boolean isLoading = false;
    public TaskList(List<Task> loadedTasks) throws CroException {
        taskList = loadedTasks;
    }

    public TaskList() {
    }


    /**
     * Returns nothing. Adds a new task, newTask into the taskList. Displays a string output upon successful addition of
     * the task into taskList.
     * @param newTask
     */
    public void addToTaskList(Task newTask) {
        taskList.add(newTask);
        if (!isLoading) {
            System.out.println("-----------------------------------");
            System.out.println("added: " + newTask);
            System.out.println("-----------------------------------");
        }
    }

    /**
     * Returns nothing. Adds a new ToDo into the taskList, as specified by the input of splitStr.
     * @param splitStr List that describes a new ToDo task.
     * @throws CroException If description of the task is empty.
     */
    public void addToDo(List<String> splitStr) throws CroException {
        ToDo newToDo = new ToDo(splitStr);
        addToTaskList(newToDo);
    }

    /**
     * Returns nothing. Adds a new Deadline into the taskList, as specified by the input of splitStr.
     * @param splitStr List that describes a new Deadline task.
     * @throws CroException If any specifications of the deadline is missing.
     */
    public void addDeadline(List<String> splitStr) throws CroException {

        Deadline newDeadline = new Deadline(splitStr);
        addToTaskList(newDeadline);
    }

    /**
     * Returns nothing. Adds a new Event into the taskList, as specified by the input of splitStr.
     * @param splitStr List that describes a new Event task.
     * @throws CroException If any specifications of the event is missing.
     */
    public void addEvent(List<String> splitStr) throws CroException {
        Event newEvent = new Event(splitStr);
        addToTaskList(newEvent);
    }

    /**
     * Returns nothing. Displays all the current tasks in the tasklist.
     */
    public void displayTasks() {
        for (int i = 0; i < taskList.size(); i++) {
            String output = String.format("%d. %s", i+1, taskList.get(i));
            System.out.println(output);
        }
        System.out.printf("Now you have %d tasks in the list%n", taskList.size());
    }

    /**
     * Returns nothing. Marks the specified task as done as per the index in taskList.
     * @param splitStr Input list that specifies which task to mark as done.
     * @throws CroException If index out of range or index not an integer.
     */
    public void markTaskAsDone(List<String> splitStr) throws CroException {
        int taskNo;
        try {
            taskNo = Integer.parseInt(splitStr.get(0));
        } catch (Exception e) {
            throw new CroException("index must be a number");
        }
        if (taskNo > taskList.size()) {
            throw new CroException("Task not found!");
        } else {
            taskList.get(taskNo - 1).markAsDone();
            System.out.println("-----------------------------------");
            System.out.println("Nice! I've marked this task as done!");
            System.out.println(taskList.get(taskNo - 1));
            System.out.println("-----------------------------------");
        }
    }

    /**
     * Returns nothing. Marks the specified task as undone as per the index in taskList.
     * @param splitStr Input list that specifies which task to mark as undone.
     * @throws CroException If index out of range or index not an integer.
     */
    public void markTaskAsUndone(List<String> splitStr) throws CroException {
        int taskNo;
        try {
            taskNo = Integer.parseInt(splitStr.get(0));
        } catch (Exception e) {
            throw new CroException("index must be a number");
        }
        if (taskNo > taskList.size()) {
            throw new CroException("Task not found!");
        } else {
            taskList.get(taskNo - 1).markAsUndone();
            System.out.println("-----------------------------------");
            System.out.println("OK, I've marked this task as not done yet.");
            System.out.println(taskList.get(taskNo - 1));
            System.out.println("-----------------------------------");
        }
    }

    /**
     * Returns nothing. Deletes the specified task from the taskList.
     * @param splitStr Input list that specifies which task to delete.
     * @throws CroException If index out of range or index not an integer.
     */
    public void deleteEvent(List<String> splitStr) throws CroException {
        int taskNo;
        try {
            taskNo = Integer.parseInt(splitStr.get(0));
        } catch (Exception e) {
            throw new CroException("index must be a number");
        }
        if (taskNo > taskList.size()) {
            throw new CroException("Task not found!");
        } else {
            Task removedTask = taskList.remove(taskNo - 1);
            System.out.println("-----------------------------------");
            System.out.println("OK, I've removed this task.");
            System.out.println(removedTask);
            System.out.printf("Now you have %d tasks in the list%n", taskList.size());
            System.out.println("-----------------------------------");
        }
    }
}
