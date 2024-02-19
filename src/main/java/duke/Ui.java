package duke;

import java.util.Scanner;

import duke.tasks.Task;

/**
 * Represent Ui class to interact with user.
 */
public class Ui {
    protected Scanner sc;

    /**
     * Constructor for the Ui class.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Gets input entered by user.
     *
     * @return String Input entered by user.
     */
    public String readCommand() {
        return this.sc.nextLine();
    }

    /**
     * Prints greeting message to the user.
     *
     * @return String Message for saying hello.
     */
    public String sayHello() {
        String greetMessage = String.format(
                "____________________________________________________________\n"
                        + " Hello! I'm Corgi!\n"
                        + " What can I do for you?\n"
                        + "____________________________________________________________\n");
        System.out.println(greetMessage);
        return greetMessage;
    }

    /**
     * Prints goodbye message to the user.
     *
     * @return String Message for goodbye.
     */
    public String sayGoodbye() {
        String exitMessage = "____________________________________________________________\n"
                + " Bye. Hope to see you again soon!\n"
                + "____________________________________________________________\n";
        System.out.println(exitMessage);
        return exitMessage;
    }

    /**
     * Prints add task message to the user.
     *
     * @param sizeOfTaskList Size of TaskList.
     * @param addedTask Task to be added to TaskList.
     * @return String Message for add task.
     */
    public String printAddMessage(int sizeOfTaskList, Task addedTask) {
        String message = String.format(
                "____________________________________________________________\n"
                        + " Got it. I've added this task:\n"
                        + "  [%s][%s] %s\n"
                        + " Now you have %d tasks in the list.\n"
                        + "____________________________________________________________\n",
                addedTask.getTaskType(), addedTask.getStatusIcon(),
                addedTask.toString(), sizeOfTaskList);
        System.out.println(message);
        return message;
    }

    /**
     * Prints delete task message to the user.
     *
     * @param sizeOfTaskList Size of TaskList.
     * @param deletedTask Task to be deleted.
     * @return String Message for delete task.
     */
    public String printDeleteMessage(int sizeOfTaskList, Task deletedTask) {
        String deleteMessage = String.format(
                "____________________________________________________________\n"
                        + " Noted. I've removed this task:\n"
                        + "  [%s][%s] %s\n"
                        + " Now you have %d tasks in the list.\n"
                        + "____________________________________________________________\n",
                deletedTask.getTaskType(), deletedTask.getStatusIcon(),
                deletedTask.toString(), sizeOfTaskList);
        System.out.println(deleteMessage);
        return deleteMessage;
    }

    /**
     * Prints print list message to the user.
     *
     * @param taskList TaskList to be printed.
     * @return String Message containing list of tasks.
     */
    public String printList(TaskList taskList) {
        String message = "____________________________________________________________\n"
                + " Here are the tasks in your list:\n";
        System.out.println("____________________________________________________________\n"
                + " Here are the tasks in your list:\n");
        for (int i = 0; i < taskList.size(); i++) {
            String currentTask = String.format("%d.[%s][%s] %s\n",
                    i + 1, taskList.getTask(i).getTaskType(),
                    taskList.getTask(i).getStatusIcon(), taskList.getTask(i).toString());
            System.out.println(currentTask);
            message += currentTask;
        }
        message += "____________________________________________________________\n";
        System.out.println("____________________________________________________________\n");
        return message;
    }

    /**
     * Prints mark task as done message to the user.
     *
     * @param taskToBeMarked Task to be marked.
     * @param taskNumber Index of the task to be marked.
     * @return String Message for marking task.
     */
    public String printMarkDoneMessage(Task taskToBeMarked, int taskNumber) {
        String message = String.format(
                "____________________________________________________________\n"
                        + " Nice! I've marked this task as done:\n"
                        + "  [%s][%s] %s\n"
                        + "____________________________________________________________\n",
                taskToBeMarked.getTaskType(), taskToBeMarked.getStatusIcon(),
                taskToBeMarked.getDescription());
        System.out.println(message);
        return message;
    }

    /**
     * Prints mark task as not done message to the user.
     *
     * @param taskToBeUnmarked Task to be unmarked.
     * @param taskNumber Index of the task to be unmarked.
     * @return String Message for unmarking task.
     */
    public String printMarkUndoneMessage(Task taskToBeUnmarked, int taskNumber) {
        String message = String.format(
                "____________________________________________________________\n"
                        + " OK, I've marked this task as not done yet:\n"
                        + "  [%s][%s] %s\n"
                        + "____________________________________________________________\n",
                taskToBeUnmarked.getTaskType(), taskToBeUnmarked.getStatusIcon(),
                taskToBeUnmarked.getDescription());
        System.out.println(message);
        return message;
    }

    /**
     * Prints error message to the user.
     *
     * @param message Error message for user.
     * @return String Message containing error.
     */
    public String printErrorMessage(String message) {
        String errorMessage = "____________________________________________________________\n"
                + message + "\n____________________________________________________________\n";
        System.out.println(errorMessage);
        return errorMessage;
    }

    /**
     * Prints date time error message to the user.
     *
     * @param message Error message for user.
     * @return String Message containing date error.
     */
    public String printDateErrorMessage(String message) {
        String errorMessage = "____________________________________________________________\n"
                + message + "\n____________________________________________________________\n";
        System.out.println(errorMessage);
        return errorMessage;
    }

    /**
     * Find tasks by a given string.
     *
     * @param taskList
     * @param keyWord
     * @return String Message containing found tasks.
     */
    public String printFoundTasks(TaskList taskList, String keyWord) {
        String message = "____________________________________________________________\n"
                + " Here are the matching tasks in your list:\n";
        System.out.println("____________________________________________________________\n"
                + " Here are the matching tasks in your list:\n");
        int count = 0;
        for (int i = 0; i < taskList.size(); i++) {
            if (!taskList.getTask(i).getDescription().contains(keyWord)) {
                continue;
            }
            String currentTask = String.format("%d.[%s][%s] %s\n",
                    count + 1, taskList.getTask(i).getTaskType(),
                    taskList.getTask(i).getStatusIcon(), taskList.getTask(i).toString());
            System.out.println(currentTask);
            message += currentTask;
            count++;
        }
        message += "____________________________________________________________\n";
        System.out.println("____________________________________________________________\n");
        return message;
    }

    /**
     * Prints reminder message to the user.
     *
     * @param taskList TaskList containing tasks.
     * @return String Message containing upcoming deadline.
     */
    public String printReminder(TaskList taskList) {
        String message = "____________________________________________________________\n"
                + " Please be reminded that you have upcoming deadlines:\n";
        System.out.println("____________________________________________________________\n"
                + " Please be reminded that you have upcoming deadlines:\n");
        int count = 0;
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.getTask(i).getTaskType() == 'D') {
                String currentTask = String.format("%d.[%s][%s] %s\n",
                        count + 1, taskList.getTask(i).getTaskType(),
                        taskList.getTask(i).getStatusIcon(), taskList.getTask(i).toString());
                System.out.println(currentTask);
                message += currentTask;
                count++;
            }
        }
        message += "____________________________________________________________\n";
        System.out.println("____________________________________________________________\n");
        return message;
    }

}

