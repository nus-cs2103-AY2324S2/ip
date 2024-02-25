package pingmebot;

import java.util.ArrayList;
import java.util.Scanner;

import pingmebot.task.Task;


/**
 * Deals with the user interactivity of the application.
 */
public class UI {
    private final Scanner scanner;

    private ArrayList<String> response = new ArrayList<>();

    /**
     * Creates a UI object with the Scanner object intialised to read user's inputs.
     */
    public UI() {
        this.scanner = new Scanner(System.in);
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
        String exitMsg = "\n" + "Bye. Hope to see you again soon!";
        System.out.println(exitMsg);
        response.add(exitMsg);
    }

    /**
     * Sends to user an overview message of the tasks the user has.
     */
    public void listText(TaskList t) {
        String toUserUponListing = "Here are the tasks in your list:";
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < t.tasks.size(); i++) {
            if (t.tasks.get(i) == null) {
                break;
            }
            int taskNumber = i + 1;
            System.out.println("\n" + taskNumber + "." + t.tasks.get(i).toString());
            toUserUponListing += "\n" + taskNumber + "." + t.tasks.get(i).toString();
        }
        response.add(toUserUponListing);
    }


    /**
     * Prints an overview text of matching tasks found in the tasklist.
     */
    public void listMatchingText(ArrayList<Task> t) {
        String toUserUponMatching = "Here are the matching tasks in your list: " + "\n";
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < t.size(); i++) {
            System.out.println((i + 1) + "." + t.get(i).toString());
            toUserUponMatching += (i + 1) + "." + t.get(i).toString() + "\n";
        }
        response.add(toUserUponMatching);
    }

    /**
     * Reads the user's inputs.
     *
     * @return User's input.
     */
    public String readCommand() {
        return scanner.nextLine();
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
        toUserUponAddition += "\n" + "  " + task.toString();
        toUserUponAddition += "\n" + "Now you have " + allTasks.getTaskSize() + " tasks in the list.";
        System.out.println(toUserUponAddition);
        response.add(toUserUponAddition);
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
        toUserUponDeletion += "\n" + "  " + allTasks.taskToString(taskNumber);
        allTasks.removeTask(taskNumber);
        toUserUponDeletion += "\n" + "Now you have " + allTasks.getTaskSize() + " tasks in the list.";
        System.out.println(toUserUponDeletion);
        response.add(toUserUponDeletion);
    }

    /**
     * Sends a message to the user when the task that he/she wishes to mark as completed has been marked.
     *
     * @param taskNum The position of the task in the tasklist which the user wants to mark.
     * @param allTasks The list of all the tasks that the user has in which to mark the task from.
     * @throws PingMeException If the user tries to mark a task which has already been completed.
     */
    public void markTaskText(int taskNum, TaskList allTasks) throws PingMeException {
        String toUserUponMarkingTask = "";
        if (allTasks.taskStatusIcon(taskNum).equals("X")) {
            throw new PingMeException("You cannot mark task again which has been completed!");
        }

        allTasks.taskMarkAsDone(taskNum);
        toUserUponMarkingTask += "Nice! I've marked this task as done:";
        toUserUponMarkingTask += "\n" + "  " + allTasks.taskToString(taskNum);
        System.out.println(toUserUponMarkingTask);
        response.add(toUserUponMarkingTask);
    }

    /**
     * Sends a message to the user when the task that he/she wishes to un-mark as not completed.
     *
     * @param taskNum The position of the task in the tasklist which the user wants to un-mark.
     * @param allTasks The list of all the tasks that the user has in which to un-mark the task from.
     * @throws PingMeException If the user tries to un-mark a task which has not been completed.
     */
    public void unmarkTaskText(int taskNum, TaskList allTasks) throws PingMeException {
        String toUserUponUnmarkingTask = "";
        if (allTasks.taskStatusIcon(taskNum).equals(" ")) {
            throw new PingMeException("You cannot un-mark task which has not been marked!");
        }

        allTasks.taskUncheckTask(taskNum);
        toUserUponUnmarkingTask += "OK, I've marked this task as not done yet:";
        toUserUponUnmarkingTask += "\n" + "  " + allTasks.taskToString(taskNum);
        System.out.println(toUserUponUnmarkingTask);
        response.add(toUserUponUnmarkingTask);
    }

    /**
     * Sends the specified error message to the user.
     *
     * @param errorMessage Specified error message.
     */
    public void showError(String errorMessage) {
        System.out.println(errorMessage);
        response.add(errorMessage);
    }


    /**
     * Returns back the response stored in the response variable to be rendered on the GUI.
     *
     * @return The response to the user.
     */
    public String givesBackResponse() {
        String messageWithNewLine = String.join("\n", response);
        response.clear();
        return messageWithNewLine;
    }

    /**
     * Send a message to the user to inform the user of the task which he/she has postponed.
     *
     * @param taskNum The task number in the task list of the task he/she wants to postpone.
     * @param allTasks The list of all tasks to postpone from.
     */
    public void postponeTaskText(int taskNum, TaskList allTasks) {
        String toUserUponPostponing = "";
        toUserUponPostponing += ("\n" + "Got it. I've postponed this task to:");
        toUserUponPostponing += "\n" + "  " + allTasks.taskToString(taskNum);
        System.out.println(toUserUponPostponing);
        response.add(toUserUponPostponing);
    }
}
