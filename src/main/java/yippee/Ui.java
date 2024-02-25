package yippee;
import java.util.ArrayList;
import java.util.Scanner;

import yippee.commands.CreateTaskCommand;
import yippee.commands.DeleteCommand;
import yippee.commands.MarkCommand;
import yippee.exceptions.YippeeException;
import yippee.tasks.Deadline;
import yippee.tasks.Event;
import yippee.tasks.Task;
import yippee.tasks.ToDo;

/**
 * Represents the Ui that replies the user after a command.
 */
public class Ui {
    private Scanner sc;

    /**
     * Instantiates Ui instance.
     * Assigns sc to a new scanner.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public void showLine() {
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Prints any error encountered during execution.
     * @param e YippeeException encountered.
     */
    public String printError(YippeeException e) {
        return String.format("    %s\n", e.getMessage());
    }

    /**
     * Prints user input to the console.
     * @param text User input.
     */
    public void echoText(String text) {
        showLine();
        System.out.printf("      %s\n", text);
        showLine();
    }

    /**
     * Toggles to the next command user inputs.
     * @return The string representation of the user command.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Prints text for confirmation after user successfully adds task.
     * @param task Task user requested to add.
     * @param size Size of the resulting list of tasks after adding.
     */
    public String addTaskRespond(Task task, int size) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Got it! I added:\n");
        stringBuilder.append(String.format("    %s\n", task.toString()));
        stringBuilder.append(String.format("You now have %d tasks in your list :D\n", size));
        return stringBuilder.toString();
    }

    /**
     * Prints text for confirmation after user successfully deletes task.
     * @param task Task user requested to delete.
     * @param size Size of the resulting list of tasks after deleting.
     */
    public String deleteTaskRespond(Task task, int size) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Successfully removed task!\n");
        stringBuilder.append(String.format("    %s\n", task.toString()));
        stringBuilder.append(String.format("You have %d tasks left in the list :D\n", size));
        return stringBuilder.toString();
    }

    /**
     * Prints text for confirmation after user successfully marks task as complete.
     * @param task Task user requested to mark as complete.
     */
    public String markTaskRespond(Task task) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Yippee! I have marked this task as done ;D\n");
        stringBuilder.append(String.format("        %s\n", task.toString()));
        return stringBuilder.toString();
    }

    /**
     * Prints text for confirmation after user successfully marks task as incomplete.
     * @param task Task user requested to mark as incomplete.
     */
    public String unmarkTaskRespond(Task task) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Awww...I have marked this task as not done yet :(\n");
        stringBuilder.append(String.format("        %s\n", task.toString()));
        return stringBuilder.toString();
    }

    /**
     * Prints text for exiting the chatbot.
     */
    public String endCommands() {
        return "Bye! Hope to see you again soon wooo!";
    }

    /**
     * Prints content of list provided.
     * @param taskList List of Tasks to print.
     */
    public String printList(TaskList taskList) {
        ArrayList<Task> tasks = taskList.getList();
        int count = 1;
        String output = "";
        if (tasks.size() == 0) {
            return "Nothing added to list yet!";
        }
        for (Task task : tasks) {
            String entry = String.format("      %d. %s\n", count, task.toString());
            count++;
            output += entry;
        }
        assert count >= 1;
        return output;
    }

    public String printStats() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Here are some statistics of your current session so far!\n");
        stringBuilder.append(
                String.format("      Total created tasks: %d\n", CreateTaskCommand.getTotalCreated()));
        stringBuilder.append(
                String.format("      Total created todo tasks: %d\n", ToDo.getTodoCount()));
        stringBuilder.append(
                String.format("      Total created deadline tasks: %d\n", Deadline.getDeadlineCount()));
        stringBuilder.append(
                String.format("      Total created event tasks: %d\n\n", Event.getEventCount()));
        stringBuilder.append("You also performed these actions:\n");
        stringBuilder.append(
                String.format("      Marked complete %d times\n", MarkCommand.getMarkCompleteCount()));
        stringBuilder.append(
                String.format("      Marked incomplete %d times\n", MarkCommand.getMarkInCompleteCount()));
        stringBuilder.append(
                String.format("      Deleted tasks %d times\n", DeleteCommand.getDeleteCount()));

        return stringBuilder.toString();
    }
}
