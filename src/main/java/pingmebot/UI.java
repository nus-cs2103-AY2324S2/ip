package pingmebot;

import pingmebot.task.Task;

import java.util.Scanner;

/**
 * Deals with the user interactivity of the application.
 */
public class UI {
    private final Scanner sc;

    /**
     * Creates a UI object with the Scanner object intialised to read user's inputs.
     */
    public UI() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Sends welcoming message to the user.
     */
    public void showWelcome() {
        String greetingMsg = "Hello! I'm PingMeBot\n" + "What can I do for you?";
        System.out.println(greetingMsg);
    }

    /**
     * Sends goodbye message to the user.
     */
    public void sayGoodbye() {
        String exitMsg = "Bye. Hope to see you again soon!";
        System.out.println("\n" + exitMsg);
    }

    /**
     * Sends to user an overview message of the tasks the user has.
     */
    public void listText() {
        System.out.println("Here are the tasks in your list:");
    }


    /**
     * Prints an overview text of matching tasks found in the tasklist.
     */
    public void listMatchingText() {
        System.out.println("Here are the matching tasks in your list:");
    }

    /**
     * Reads the user's inputs.
     *
     * @return User's input.
     */
    public String readCommand() {
        return this.sc.nextLine();
    }

    /**
     * Sends a message to the user when his/her desired task has been added.
     *
     * @param task The specified task the user wants to do.
     * @param allTasks The tasklist which contains all the user's tasks in which the user's new task is to be added.
     */
    public void additionToTasksText(Task task, TaskList allTasks) {
        String toUserUponAddition = "";
        toUserUponAddition += ("\n" + "Got it. I've added this task:");
        toUserUponAddition += "\n"  + "  " + task.toString();
        toUserUponAddition += "\n" + "Now you have " + allTasks.getTaskSize() + " tasks in the list.";
        System.out.println(toUserUponAddition);
    }

    /**
     * Sends a message to inform the user of the task that he/she wishes to delete.
     *
     * @param taskNumber The position of the task in the tasklist which the user wants to delete.
     * @param allTasks The list of all the tasks that the user has in which to delete the task from.
     */
    public void deletionToTasksText(int taskNumber, TaskList allTasks) {
        String toUserUponDeletion = "";
        toUserUponDeletion += "Noted. I've removed this task:";
        toUserUponDeletion += "\n"  + "  " + allTasks.taskToString(taskNumber);
        allTasks.removeTask(taskNumber);
        toUserUponDeletion += "\n" + "Now you have " + allTasks.getTaskSize() + " tasks in the list.";
        System.out.println(toUserUponDeletion);
    }

<<<<<<< HEAD
    /**
     * Sends a message to the user when the task that he/she wishes to mark as completed has been marked.
     *
     * @param taskNum The position of the task in the tasklist which the user wants to mark.
     * @param allTasks The list of all the tasks that the user has in which to mark the task from.
     * @throws myBotException If the user tries to mark a task which has already been completed.
     */
    public void markTaskText(int taskNum, TaskList allTasks) throws myBotException {
=======
    public void markTaskText(int taskNum, TaskList allTasks) throws PingMeException {
>>>>>>> branch-A-CodingStandard
        String toUserUponMarkingTask = "";
        if (allTasks.taskStatusIcon(taskNum).equals("X")) {
            throw new PingMeException("You cannot mark task again which has been completed!");
        }

        allTasks.taskMarkAsDone(taskNum);
        toUserUponMarkingTask += "Nice! I've marked this task as done:";
        toUserUponMarkingTask += "\n" + "  " + allTasks.taskToString(taskNum);
        System.out.println(toUserUponMarkingTask);
    }

<<<<<<< HEAD
    /**
     * Sends a message to the user when the task that he/she wishes to un-mark as not completed.
     *
     * @param taskNum The position of the task in the tasklist which the user wants to un-mark.
     * @param allTasks The list of all the tasks that the user has in which to un-mark the task from.
     * @throws myBotException If the user tries to un-mark a task which has not been completed.
     */
    public void unmarkTaskText(int taskNum, TaskList allTasks) throws myBotException {
=======
    public void unmarkTaskText(int taskNum, TaskList allTasks) throws PingMeException {
>>>>>>> branch-A-CodingStandard
        String toUserUponUnmarkingTask = "";
        if (allTasks.taskStatusIcon(taskNum).equals(" ")) {
            throw new PingMeException("You cannot un-mark task which has not been marked!");
        }

        allTasks.taskUncheckTask(taskNum);
        toUserUponUnmarkingTask += "OK, I've marked this task as not done yet:";
        toUserUponUnmarkingTask += "\n" + "  " + allTasks.taskToString(taskNum);
        System.out.println(toUserUponUnmarkingTask);
    }

    /**
     * Sends the specified error message to the user.
     *
     * @param errorMessage Specified error message.
     */
    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

}
