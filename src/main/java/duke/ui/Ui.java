package duke.ui;

import duke.exception.DukeException;

import duke.task.Task;
import duke.task.TaskList;
import duke.task.Deadline;
import duke.task.Event;


public class Ui {
<<<<<<< Updated upstream
        ArrayList<Task> arr;
        public static void showLoadingError() {
            System.out.println("Error loading tasks. Initializing with an empty task list."); //fix?
=======
    /**
     * Displays an error message indicating a problem with loading tasks.
     */
    public static void showLoadingError() {
        System.out.println("Error loading tasks. Initializing with an empty task list."); //fix?
    }

    /**
     * Displays a welcome message when the Duke application is launched.
     */
    public static void showWelcomeMessage() {
        System.out.println("-------------------------------");
        System.out.println("Hello! I'm Tango. \nWhat can I do for you today?");
        System.out.println("-------------------------------");
    }

    /**
     * Prints the number of tasks in the task list.
     *
     * @param tasks The task list.
     */
    public static void printNumberOfTasks(TaskList tasks) {
        System.out.println(tasks.size() == 1 ? " Now you have 1 task in the list." :
                "Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Prints a message about the deleted task.
     *
     * @param task The deleted task.
     */
    public static void printDeletedTaskMessage(Task task) {
        String taskDetails = "Noted. I've removed this task:\n" +
                "[" + task.getType() + "][" + task.getStatusIcon() + "] " + task.getDescription();
        if (task instanceof Deadline) {
            Deadline deadlineTask = (Deadline) task;
            taskDetails += " (by: " + deadlineTask.getBy() + ")";
        } else if (task instanceof Event) {
            Event eventTask = (Event) task;
            taskDetails += " (from: " + eventTask.getFrom() + " to: " + eventTask.getTo() + ")";
>>>>>>> Stashed changes
        }

        public static void showWelcomeMessage() {
            System.out.println("-------------------------------");
            System.out.println("Hello! I'm Tango. \nWhat can I do for you today?");
            System.out.println("-------------------------------");
        }

        public static void printNumberOfTasks(TaskList tasks) {
            System.out.println(tasks.size() == 1 ? " Now you have 1 task in the list." :
                    "Now you have " + tasks.size() + " tasks in the list.");
        }

        public static void printDeletedTaskMessage(Task task) {
            String taskDetails = "Noted. I've removed this task:\n" +
                    "[" + task.getType() + "][" + task.getStatusIcon() + "] " + task.getDescription();
            if (task instanceof Deadline) {
                Deadline deadlineTask = (Deadline) task;
                taskDetails += " (by: " + deadlineTask.getBy() + ")";
            } else if (task instanceof Event) {
                Event eventTask = (Event) task;
                taskDetails += " (from: " + eventTask.getFrom() + " to: " + eventTask.getTo() + ")";
            }

            System.out.println(taskDetails);
        }

        public static void showGoodbyeMessage() {
            System.out.println("Bye. Hope to see you again soon!");
        }

        public static void showError(String errorMessage) throws DukeException {
            System.out.println(errorMessage);
        }

        public static void showTaskList(TaskList tasks) {
            if (tasks.size() == 0) {
                System.out.println("You have no tasks in your list!");
            } else {
                System.out.println(tasks.size() == 1 ? "Here is the task in your list:" : "Here are the tasks in your list:");
                try {
                    TaskList.getList(tasks);
                } catch (DukeException e) {
                    System.out.println(e);
                }
            }
        }

        public static void markTask(Task task) {
            System.out.println("Nice! I've marked this as done: \n " + "[" + task.getType() + "][" + task.getStatusIcon() + "] " + task.getDescription());
        }

        public static void printDashes() {
            System.out.println("-------------------------------");
        }

        public static void unmarkTask(Task task) {
            System.out.println("OK, I've marked this task as not done yet: \n " + "[" + task.getType() + "][" + task.getStatusIcon() + "] " + task.getDescription());
        }

}
