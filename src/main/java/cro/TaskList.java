package cro;

import tasks.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a TaskList in the chatbot. A TaskList object is responsible for
 * keeping track of the tasks currently in the system.
 */
public class TaskList {
    public List<Task> taskList = new ArrayList<>();
    static boolean isLoading = false;
    public TaskList(List<Task> loadedTasks) throws CroException {
        taskList = loadedTasks;
    }

    public TaskList() {
    }


    public String addTag(List<String> splitStr) throws CroException {
        String output = "";
        int taskNo;
        try {
            taskNo = Integer.parseInt(splitStr.get(0));
        } catch (Exception e) {
            throw new CroException("index must be a number");
        }
        if (taskNo > taskList.size()) {
            throw new CroException("Task not found!");
        } else {
            output += "-----------------------------------\n";
            output += String.format("%s to task %d\n",
                    taskList.get(taskNo - 1).addTag(splitStr.subList(1, splitStr.size())),
                    taskNo - 1);
            output += "-----------------------------------";
        }
        return output;

    }
    /**
     * Returns nothing. Adds a new task, newTask into the taskList. Displays a string output
     * upon successful addition of the task into taskList.
     * @param newTask
     */
    public String addToTaskList(Task newTask) {
        String output = "";
        taskList.add(newTask);
        if (!isLoading) {
            output += "-----------------------------------\n";
            output += "added: " + newTask + "\n";
            output += "-----------------------------------";
        }
        return output;
    }

    /**
     * Returns nothing. Adds a new ToDo into the taskList, as specified by the input of splitStr.
     * @param splitStr List that describes a new ToDo task.
     * @throws CroException If description of the task is empty.
     */
    public String addToDo(List<String> splitStr) throws CroException {
        ToDo newToDo = new ToDo(splitStr);
        return addToTaskList(newToDo);
    }

    /**
     * Returns nothing. Adds a new Deadline into the taskList, as specified by the input of splitStr.
     * @param splitStr List that describes a new Deadline task.
     * @throws CroException If any specifications of the deadline is missing.
     */
    public String addDeadline(List<String> splitStr) throws CroException {
        Deadline newDeadline = new Deadline(splitStr);
        return addToTaskList(newDeadline);
    }

    /**
     * Returns nothing. Adds a new Event into the taskList, as specified by the input of splitStr.
     * @param splitStr List that describes a new Event task.
     * @throws CroException If any specifications of the event is missing.
     */
    public String addEvent(List<String> splitStr) throws CroException {
        Event newEvent = new Event(splitStr);
        return addToTaskList(newEvent);
    }

    /**
     * Returns nothing. Displays all the current tasks in the tasklist.
     */
    public String displayTasks() {
        String output = "";
        for (int i = 0; i < taskList.size(); i++) {
            output += String.format("%d. %s\n", i+1, taskList.get(i));
        }
        output += String.format("Now you have %d tasks in the list%n", taskList.size());
        return output;
    }

    /**
     * Returns nothing. Displays all the current tasks specified in the taskList.
     * @param taskList A list of tasks that is to be displayed.
     */
    private String displayTasks(List<Task> taskList) {
        String output = "-----------------------------------\n";
        if (taskList.size() == 0) {
            output += "There are no matching tasks in your list.\n";
        } else {
            output += "Here are the matching tasks in your list:\n";
            for (int i = 0; i < taskList.size(); i++) {
                output += String.format("%d. %s\n", i + 1, taskList.get(i));
            }
        }
        output += "-----------------------------------";
        return output;
    }

    /**
     * Returns nothing. Marks the specified task as done as per the index in taskList.
     * @param splitStr Input list that specifies which task to mark as done.
     * @throws CroException If index out of range or index not an integer.
     */
    public String markTaskAsDone(List<String> splitStr) throws CroException {
        String output = "";
        int taskNo;
        try {
            taskNo = Integer.parseInt(splitStr.get(0));
        } catch (Exception e) {
            throw new CroException("index must be a number");
        }
        if (taskNo > taskList.size()) {
            throw new CroException("Task not found!");
        } else {
            taskList.get(taskNo - 1).setDone();
            output += "-----------------------------------\n";
            output += "Nice! I've marked this task as done!\n";
            output += taskList.get(taskNo - 1) + "\n";
            output += "-----------------------------------";
        }
        return output;
    }

    /**
     * Returns nothing. Marks the specified task as undone as per the index in taskList.
     * @param splitStr Input list that specifies which task to mark as undone.
     * @throws CroException If index out of range or index not an integer.
     */
    public String markTaskAsUndone(List<String> splitStr) throws CroException {
        String output = "";
        int taskNo;
        try {
            taskNo = Integer.parseInt(splitStr.get(0));
        } catch (Exception e) {
            throw new CroException("index must be a number");
        }
        if (taskNo > taskList.size()) {
            throw new CroException("Task not found!");
        } else {
            taskList.get(taskNo - 1).setUndone();
            output += "-----------------------------------\n";
            output += "OK, I've marked this task as not done yet.\n";
            output += taskList.get(taskNo - 1) + "\n";
            output += "-----------------------------------";
        }
        return output;
    }

    /**
     * Returns nothing. Deletes the specified task from the taskList.
     * @param splitStr Input list that specifies which task to delete.
     * @throws CroException If index out of range or index not an integer.
     */
    public String deleteEvent(List<String> splitStr) throws CroException {
        String output = "";
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
            output += "-----------------------------------\n";
            output += "OK, I've removed this task.\n";
            output += removedTask + "\n";
            output += String.format("Now you have %d tasks in the list.\n", taskList.size());
            output += "-----------------------------------";
        }
        return output;
    }

    /**
     * Returns nothing. Displays all tasks that contain the keyword specified.
     * @param splitStr A list specifying the keyword to search.
     */
    public String findKeyword(List<String> splitStr) {
        String searchString = String.join(" ", splitStr);
        List<Task> foundTasks = new ArrayList<>();
        for (Task task : taskList) {
            if (task.getDescription().contains(searchString)) {
                foundTasks.add(task);
            }
        }
        return displayTasks(foundTasks);
    }
}
