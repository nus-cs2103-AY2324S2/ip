package duke.ui;

import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Represents a UI that interacts with users.
 */
public class Ui {

    /**
     * For greeting users at the start.
     * @param name Chatbot name
     */
    public static String greet(String name) {
        return String.format("Heyoo I'm %s.\nWhat can I do for you?", name);
    }

    /**
     * For ending the conversation with users.
     * @param tasklist Tasklist with user-specified tasks
     */
    public static String bye(TaskList tasklist) {
        Storage.saveTasks(tasklist);
        return "Bye lol see you again!";
    }

    /**
     * For listing out tasks entered by the users
     * @param tasklist Tasklist with user-specified tasks
     */
    public static String listTasks(TaskList tasklist) {
        String tasksListed = tasklist.listTask(); // Assumes this method exists and returns a formatted String
        return String.format("Here are the tasks in your list: \n%s", tasksListed);
    }

    /**
     * For adding tasks entered by the users
     * @param tasklist Tasklist with user-specified tasks
     * @param input Task input
     */
    public static String addTask(TaskList tasklist, String input) {
        boolean isAdded = tasklist.addTask(input);
        int tasksCount = TaskList.tasksCount;
        if (isAdded) {
            String taskDescription = tasklist.getTaskDescription(tasksCount - 1);
            return String.format("Gotcha. I've added this task: \n%s\nNow you have %d tasks in the list.",
                    taskDescription, tasksCount);
        } else {
            return "I couldn't add your task due to an error";
        }
    }

    /**
     * For updating the status the task from undone to done
     * @param tasklist Tasklist with user-specified tasks
     * @param input user input
     * @param status task status
     */
    public static String mark(TaskList tasklist, String input, boolean status) {
        int num = Integer.parseInt(input) - 1;
        tasklist.markTask(num, status);
        String taskDescription = tasklist.getTaskDescription(num);
        return String.format("%s\n%s",
                status ? "Good job on finishing this task: " : "Aw OK, I've marked this task as not done yet: ",
                taskDescription);
    }

    /**
     * For deleting a task from the tasklist.
     * @param tasklist Tasklist with user-specified tasks
     * @param input user input
     */
    public static String delete(TaskList tasklist, String input) {
        int num = Integer.parseInt(input) - 1;
        String deletedTask = tasklist.getTaskDescription(num);
        tasklist.deleteTask(num);
        return String.format("Sure, I've removed this task:\n%s\nNow you have %d tasks in the list.",
                deletedTask, TaskList.tasksCount);
    }

    /**
     * For finding task(s) with a given keyword from the tasklist.
     * @param tasklist Tasklist with user-specified tasks
     * @param keyword
     */
    public static String find(TaskList tasklist, String keyword) {
        String foundTasks = tasklist.findTasks(keyword); // Assumes this method exists and returns a formatted String
        return String.format("Here are the matching tasks in your list:\n%s", foundTasks);
    }
}



