package utilities;

import java.util.ArrayList;
import java.util.List;

import exceptions.WilliamException;
import tasks.Task;

/**
 * Contains the task list e.g. it has operations to add/delete tasks in the list
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Constructor if there is no existing file
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructor if there is an existing txt file with tasks
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Getter method for task
     * 
     * @return arraylist Arraylist of tasks
     */
    public List<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Returns a string representation of all the tasks in the list
     * 
     * @return A string listing all tasks or stating that the list is empty
     */
    public String printList() {
        StringBuilder sb = new StringBuilder();
        if (this.tasks.isEmpty()) {
            return "Your list is empty. Please add some tasks to the list first!\n";
        } else {
            sb.append("Here are the tasks in your list:\n");
            for (int i = 0; i < this.tasks.size(); i++) {
                sb.append(i + 1).append(". ").append(this.tasks.get(i).toString()).append("\n");
            }
        }
        return sb.toString();
    }


    /**
     * Adds task into the list of tasks
     * 
     * @param task Input that is the task (could be todo, deadline or event)
     */
    public void addTask(Task task) {
        this.tasks.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + this.tasks.size() + " tasks in the list.\n");
    }

    /**
     * Deletes the specified task from the list and returns a message of the task being deleted
     * 
     * @param input The ID of the task to delete
     * @return String that says whether the task has been deleted
     */
    public String deleteFromList(String input) {
        StringBuilder sb = new StringBuilder();
        if (this.tasks.isEmpty()) {
            return "There are no tasks to be deleted. Please add some tasks to the list first!\n";
        } else {
            try {
                int idOfItem = Integer.parseInt(input);
                int actualId = idOfItem - 1;
                Task removedTask = this.tasks.remove(actualId);
                sb.append("Noted. I've removed this task:\n").append(removedTask.toString())
                        .append("\nNow you have ").append(this.tasks.size())
                        .append(" tasks in the list.\n");
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                return "Invalid task ID. Please enter a valid number! + \n";
            }
        }
        return sb.toString();
    }


    /**
     * Unmarks/marks the task based on the ID
     * 
     * @param input The ID of the task
     */
    public void markAndUnmark(String input) {
        int idOfItem = Integer.parseInt(input);
        int actualId = idOfItem - 1;
        this.tasks.get(actualId).changeIsDone();
        System.out.println(this.tasks.get(actualId).toString() + "\n");
    }

    /**
     * Finds tasks based on whether their description contains the input
     * 
     * @param input Input from the user to find the task
     * @return A message with the matching tasks or a message that says no task is found
     * @throws WilliamException If no tasks match the provided input
     */
    public String findTasks(String input) throws WilliamException {
        StringBuilder sb = new StringBuilder();
        boolean isFound = false;
        int counter = 0;
        for (int i = 0; i < this.tasks.size(); i++) {
            String currTask = this.tasks.get(i).getName();
            if (currTask.contains(input)) {
                if (isFound == false) {
                    sb.append("Here are the matching tasks in your list:\n");
                    isFound = true;
                }
                counter++;
                sb.append(counter).append(". ").append(this.tasks.get(i).toString()).append("\n");
            }
        }
        if (isFound == false) {
            throw new WilliamException(
                    "No tasks match the provided input: " + input + ". Please try again!");
        }
        return sb.toString();
    }
}
