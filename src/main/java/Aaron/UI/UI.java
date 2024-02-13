package aaron.ui;

import java.util.Scanner;

import aaron.exception.AaronBotException;
import aaron.task.TaskList;

public class UI {
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Method to output initial greeting messages for user
     */
    public void greet() {
        System.out.println("Hello, I am AaronBot, please talk to me I love my students very much :)");
        System.out.println("To add a task to the list, type 'add ' followed by your task.");
        System.out.println("To add a Todo: add todo [task name]");
        System.out.println("To add a Deadline: add deadline [task name] /by [deadline]");
        System.out.println("To add an Event: add event [task name] /from [start] /to [end]");
        System.out.println("To show the list, type 'show list'.");
        System.out.println("To mark a task done: mark [index of task]");
        System.out.println("To unmark a task done: unmark [index of task]");
    }

    /**
     * Method that reads input from user
     * @return user input
     */
    public String readCommand() {
        String userInput = scanner.nextLine();
        return userInput;
    }

    /**
     * Message to indicate whether a previous tasklist was loaded
     * @param taskList tasklist that was loaded (empty if none found)
     */
    public void taskListLoadedMessage(TaskList taskList) {
        if (taskList.getTasklistSize() > 0) {
            System.out.println("Tasklist successfully loaded.");
            taskList.showList();
        } else {
            System.out.println("Student, no previous tasklist found");
        }
    }

    /**
     * Method to display parsing error to the user
     * @param userString user input that generated parsing error
     */
    public void showParseError(String userString) {
        System.out.println("Sorry student, we had an error parsing message: \n"
                + userString);
    }

    /**
     * Message outputted after successfully marking task
     * @param taskIndex index of tasklist marked
     * @param taskList tasklist modified
     */
    public void markMessage(int taskIndex, TaskList taskList) {
        System.out.println("Successfully marked task: \n"
                + taskList.printTask(taskIndex));
    }

    /**
     * Message outputted after successfully unmarking task
     * @param taskIndex index of tasklist unmarked
     * @param taskList tasklist modified
     */
    public void unmarkMessage(int taskIndex, TaskList taskList) {
        System.out.println("Successfully unmarked task: \n"
                + taskList.printTask(taskIndex));
    }

    /**
     * Message outputted after task is successfully added to tasklist
     * @param taskList tasklist modified
     */
    public void taskAddedMessage(TaskList taskList) {
        System.out.println("Successfully added task: \n"
                + taskList.printTask(taskList.getTasklistSize()));
    }

    /**
     * Method to display error to user
     * @param e error generated
     */
    public void errorMessage(AaronBotException e) {
        System.out.println("Oh no, there seems to have been an error");
        System.out.println(e);
    }

    /**
     * Message outputted when user tries to show empty list
     */
    public void emptyListMessage() {
        System.out.println("STUDENT!!!! Tasklist is empty so there is nothing to show :)");
    }

    /**
     * Method to close scanner (prevent memory leak)
     */
    public void closeScanner() {
        scanner.close();
    }

    /**
     * Message when exiting execution cycle
     */
    public void goodbyeMessage() {
        System.out.println("Goodbye student, HAND.");
    }

}
