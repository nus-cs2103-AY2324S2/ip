package ChatbotRan;

import java.util.ArrayList;

/**
 * Displays chatbot responses to the CLI.
 */
public class RanUi {
    /**
     * Prints the startup message.
     */
    public void greet() {
        System.out.println("Hello. I am ");
        String art = "__________                \n" +
                "\\______   \\_____    ____  \n" +
                " |       _/\\__  \\  /    \\ \n" +
                " |    |   \\ / __ \\|   |  \\\n" +
                " |____|_  /(____  /___|  /\n" +
                "        \\/      \\/     \\/ ";
        System.out.println(art);
        System.out.println("What would you like to do today?");
    }

    /**
     * Prints a line to separate the next command.
     */
    public void line() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints all tasks in the TaskList.
     *
     * @param taskList list of tasks
     */
    public void printTasks(TaskList taskList) {
        int size = taskList.size();
        if (size == 0) {
            System.out.println("You haven't got any tasks.");
        } else {
            for (int i = 0; i < size; i++) {
                System.out.println("Task " + (i + 1) + ":" + taskList.get(i));
            }
        }
    }

    /**
     * Prints a generic error message.
     */
    public void unknown() {

        System.out.println("I didn't understand that.");
    }

    /**
     * Prints response after adding a Task
     *
     * @param task task
     */
    public void addTask(Task task) {
        System.out.println("I've added this task to the list: ");
        System.out.println(task);
    }

    void printNumber(int size) {
        if (size == 1) {
            System.out.println("There is now 1 task in the list");
        } else {
            System.out.println("There are now " + size + " tasks in the list");
        }
    }

    /**
     * Prints response after deleting a task.
     *
     * @param task task
     */
    public void delete(Task task) {
        System.out.println("I've deleted this task: ");
        System.out.println(task);
    }

    /**
     * Prints the shutdown message.
     */
    public void bye() {
        System.out.println("Goodbye, please return soon.");
    }

    /**
     * Prints response after attempting to unmark a task.
     *
     * @param completed whether the task was complete
     */
    public void unmark(boolean completed) {
        if (completed) {
            System.out.println("If that's the case, I'll set that task as incomplete: ");
        } else {
            System.out.println("That task is already incomplete: ");

        }
    }

    /**
     * Prints response after attempting to mark a task.
     *
     * @param completed whether the task was complete
     */
    public void mark(boolean completed) {

        if (!completed) {
            System.out.println("Alright. I have marked this task as complete: ");
        } else {
            System.out.println("That task is already complete: ");
        }
    }

    /**
     * Prints one task.
     *
     * @param task task
     */
    public void printTask(Task task) {
        System.out.println(task);
    }

    /**
     * Prints results of a parsing error.
     *
     * @param e error from parsing a task
     */
    public void error(TaskException e) {
        System.out.println(e.getMessage());
    }

    public void found(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks contain that string.");
        } else {
            System.out.println("Found " + tasks.size() + " match" + (tasks.size() == 1 ? "" : "es"));
            for (Task t: tasks) {
                printTask(t);
            };
        }
    }
}
