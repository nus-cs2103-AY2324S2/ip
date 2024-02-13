package paimon;


import paimon.task.Task;
import paimon.task.TaskList;

import java.util.Scanner;

public class UiHandler {
    private final Scanner scanner;

    public UiHandler() {
        this.scanner = new Scanner(System.in);
    }
    public String readCommand() {
        return scanner.nextLine();
    }

    private static void sendMessage(String mainMessage) {
        System.out.println("-------------------->");
        System.out.println(mainMessage);
        System.out.println("-------------------->");
    }

    private static void sendMessage(String mainMessage, String subMessage, String closingMessage) {
        System.out.println(mainMessage);
        System.out.println("-------------------->");
        System.out.println(subMessage);
        System.out.println("-------------------->");
        System.out.println(closingMessage);
    }

    private static void sendMessage(String mainMessage, String subMessage) {
        System.out.println(mainMessage);
        System.out.println("-------------------->");
        System.out.println(subMessage);
        System.out.println("-------------------->");
    }

    public void helpResponse() {
        sendMessage("You can perform the following actions, make sure to follow the syntax!",
                "list : Lists all your Tasks\n" +
                "todo <task>: Adds a Task without any deadline\n" +
                "deadline <task> /by <time>: Adds a task done before a time\n" +
                "event<Task> /from <time> /to <time>: Adds a task with a time window\n" +
                "mark/unmark <index>: Marks a Task as done. Index must be a number\n" +
                "unmark <index>: Mark a Task as not done. Index must be a number\n" +
                "delete <index>: Deletes a Task from your list!. Index must be a number\n" +
                "find <keyword>: Lists tasks that contain keyword\n" +
                "bye : Exits the program"
                );
    }
    public void addTaskResponse(String taskString, int tasksLeft) {
        sendMessage("Okay Traveller, I've added the following task!", taskString, "You have " + tasksLeft + " tasks in your list");
    }
    public void deleteTaskResponse(int tasksLeft) {
        sendMessage("Okay Traveller, I've deleted the task! You now have " + tasksLeft + " tasks remaining.");
    }
    public void getFoundResponse(String taskString) {
        sendMessage("Okay Traveller, here are the matching tasks", taskString);
    }
    public void markTaskResponse(String taskString, boolean isDone) {
        if (isDone) {
            sendMessage("Okay Traveller, I've marked the task as done", taskString);
        } else {
            sendMessage("Okay Traveller, I've unmarked this task", taskString);
        }
    }
    public void greetResponse() {
        sendMessage("Greetings Traveller! I'm Paimon, your friendly guide!\nType help to see what I can do!");
    }

    public void exitResponse() {
        sendMessage("See you later Traveller!");
    }

    public void listResponse(TaskList list) {
        if (list.getSize() == 0) {
            System.out.println("Your list is currently empty, add some tasks!");
        } else {
            System.out.println("Here is your list so far!");
            System.out.println("-------------------->");
            System.out.println(list);
            System.out.println("-------------------->");
        }

    }
    public void showError(ChatException e) {
        System.out.println(e.getMessage());
    }

}
