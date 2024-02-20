package aaron.ui;

import java.util.Scanner;

import aaron.task.TaskList;

public class UI {
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Method to output initial greeting messages for user
     */
    public String greet() {
        return "Hello student, I am AaronBot, please talk to me I love my students very much :)";
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
    public String taskListLoadedMessage(TaskList taskList, UI ui) {
        if (taskList.getTasklistSize() > 0) {
            return "STUDENT!!! Tasklist successfully loaded.\n" + taskList.showList();
        } else {
            return "Student, no previous tasklist found";
        }
    }

    /**
     * Method to display parsing error to the user
     * @param userString user input that generated parsing error
     */
    public String showParseError(String userString) {
        return "Sorry student, we had an error parsing message: \n"
                + userString;
    }

    /**
     * Message outputted after successfully marking task
     * @param taskIndex index of tasklist marked
     * @param taskList tasklist modified
     */
    public String markMessage(int taskIndex, TaskList taskList) {
        return "STUDENT I HAVE Successfully marked task: \n"
                + taskList.printTask(taskIndex);
    }

    /**
     * Message outputted after successfully unmarking task
     * @param taskIndex index of tasklist unmarked
     * @param taskList tasklist modified
     */
    public String unmarkMessage(int taskIndex, TaskList taskList) {
        return "STUDENT I HAVE Successfully unmarked task: \n"
                + taskList.printTask(taskIndex);
    }

    /**
     * Message outputted after task is successfully added to tasklist
     * @param taskList tasklist modified
     */
    public String taskAddedMessage(TaskList taskList) {
        return "STUDENT I HAVE Successfully added task: \n"
                + taskList.printTask(taskList.getTasklistSize());
    }

    /**
     * Method to display error to user
     * @param e error generated
     */
    public String errorMessage(Exception e) {
        return "STUDENT!!!!!!! there seems to have been an error \n" + e.getMessage();
    }

    /**
     * Message outputted when user tries to show empty list
     */
    public String emptyListMessage() {
        return "STUDENT!!!! Tasklist is empty so there is nothing to show :)";
    }

    /**
     * Method to close scanner (prevent memory leak)
     */
    public void closeScanner() {
        scanner.close();
    }

    /**
     * Method to display a message to the user
     * @param messageString message to be displayed
     */
    public String showMessage(String messageString) {
        return messageString;
    }

    /**
     * Method to display a message when task is successfully deleted
     * @param index
     * @return message
     */
    public String showTaskDeletedMsg(int index) {
        return ("STUDENT!!!! \nSuccessfully deleted task " + index);
    }

    /**
     * Method to display a message when task is successfully snoozed
     * @param index index of task snoozed
     * @return message
     */
    public String showTaskSnoozedMessage(int index) {
        return ("STUDENT!!!! Successfully snoozed task " + index);
    }
 
}
