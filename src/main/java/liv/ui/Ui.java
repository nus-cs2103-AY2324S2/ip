package liv.ui;

import java.util.ArrayList;
import java.util.Scanner;

import liv.container.TaskList;
import liv.task.Task;
import liv.task.TodoTask;
import liv.task.Deadline;
import liv.task.Event;

/**
 * Handles the interaction between users and the chatbot.
 */
public class Ui {
    private static final Scanner SCANNER = new Scanner(System.in);

    /**
     * Displays the long bar between messages.
     */
    public void displayBar() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints the greeting message.
     */
    public static String getGreetingMessage() {
        String greetingMessage = String.join("\n",
                "Liv, under your instructions!", "What is your command?");
        //displayMessage(greetingMessage);
        return greetingMessage;
    }

    /**
     * Reads the input command from the users.
     * @return The line that user types.
     */
    public String readCommand() {
        return SCANNER.nextLine();
    }

    /**
     * Prints the goodbye message.
     */
    public static String getByeMessage() {
        String byeMessage = ("Farewell, see you next time!");
        displayMessage(byeMessage);
        return byeMessage;
    }

    /**
     * Prints the list of tasks to the user.
     */
    public static String getListMessage() {
        String listMessage = "Here are the missions you added:\n";

        for (int i = 0; i < TaskList.getListSize(); i++) {
            Task task = TaskList.getTask(i);
            int displayedIndex = i + 1;
            listMessage += displayedIndex + ". " + task.getDisplayedString() + "\n";
        }

        listMessage += "Total: " + TaskList.getListSize() + " mission(s)";
        displayMessage(listMessage);
        return listMessage;
    }

    /**
     * Prints the task that was marked by the command from the user.
     * @param task The task that was marked.
     */
    public static String getMarkMessage(ArrayList<Task> markedTasks) {
        String markMessage = "Mission completed:\n";
        for (Task task: markedTasks) {
            markMessage += task.getDisplayedString() + "\n";
        }
        displayMessage(markMessage);
        return markMessage;
    }

    /**
     * Prints the task that was unmarked by the command from the user.
     * @param task The task that was unmarked.
     */
    public static String getUnmarkMessage(Task task) {
        String unmarkMessage = String.join("\n", "Mission pending:",
                " " + task.getStatusIcon() + " " + task.getDescription());
        displayMessage(unmarkMessage);
        return unmarkMessage;
    }

    /**
     * Prints the task that was deleted from the list by the user.
     * @param removed The task removed from the list.
     */
    public static String getDeleteMessage(Task removed) {
        String deleteMessage = String.join("\n", "Mission deleted from list:",
                removed.getDisplayedString(), "You have " + TaskList.getListSize() + " mission(s) on the list");
        displayMessage(deleteMessage);
        return deleteMessage;
    }

    /**
     * Prints the task that was added by the user in the predefined format.
     * @param todo The task that user added to the list.
     */
    public static String getTodoMessage(TodoTask todo) {
        String todoMessage = String.join("\n", "Task added:", todo.getDisplayedString(),
                "You have " + TaskList.getListSize() + " mission(s) on the list");
        displayMessage(todoMessage);
        return todoMessage;
    }

    /**
     * Prints the deadline that was added by the user in the predefined format.
     * @param deadline The deadline that user added to the list.
     */
    public static String getDeadlineMessage(Deadline deadline) {
        String deadlineMessage = String.join("\n", "Deadline added:", deadline.getDisplayedString(),
                "You have " + TaskList.getListSize() + " mission(s) on the list");
        displayMessage(deadlineMessage);
        return deadlineMessage;
    }

    /**
     * Prints the deadline that was added by the user in the predefined format.
     * @param event The event that user added to the list.
     */
    public static String getEventMessage(Event event) {
        String eventMessage = String.join("\n", "Event added:", event.getDisplayedString(),
                "You have " + TaskList.getListSize() + " mission(s) on the list");
        displayMessage(eventMessage);
        return eventMessage;
    }

    /**
     * Displays the texts and results of the "find" command.
     * @param matchingTasks The list of tasks that is the result of the "find" command.
     */
    public static String getFindMessage(ArrayList<String> matchingTasks) {
        String findMessage = "Here are the mission(s) you requested me to find:\n";
        if (matchingTasks.size() == 0)  {
            displayErrorMessage("No mission found!");
        } else {
            for (int i = 0; i < matchingTasks.size(); i++) {
                findMessage += matchingTasks.get(i) + "\n";
            }
            findMessage += "Total: " + matchingTasks.size() + " mission(s)";
        }
        displayMessage(findMessage);
        return findMessage;
    }
    /** Prints the error message that was thrown by the Exception class.
     * @param errorMessage The exception message thrown.
     */
    public static void displayErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
    }

    public static void displayMessage(String message) {
        System.out.println(message);
    }
}
