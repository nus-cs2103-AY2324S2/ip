package Aaron.UI;
import java.util.Scanner;

import Aaron.Exception.AaronBotException;
import Aaron.Task.TaskList;
public class UI {
    private static final Scanner scanner = new Scanner(System.in);

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

    public String readCommand() {
        String userInput = scanner.nextLine();
        return userInput;
    }

    public void taskListLoadedMessage(TaskList taskList) {
        if (taskList.getTasklistSize() > 0) {
            System.out.println("Tasklist successfully loaded.");
            taskList.showList();
        } else {
            System.out.println("Student, no previous tasklist found");
        }
    }

    public void showParseError(String userString) {
        System.out.println("Sorry student, we had an error parsing message: \n"
                + userString);
    }

    public void markMessage(int taskIndex, TaskList taskList) {
        System.out.println("Successfully marked task: \n"
                + taskList.printTask(taskIndex));
    }

    public void unmarkMessage(int taskIndex, TaskList taskList) {
        System.out.println("Successfully unmarked task: \n"
                + taskList.printTask(taskIndex));
    }

    public void taskAddedMessage(TaskList taskList) {
        System.out.println("Successfully added task: \n"
                + taskList.printTask(taskList.getTasklistSize()));
    }

    public void errorMessage(AaronBotException e) {
        System.out.println("Oh no, there seems to have been an error");
        System.out.println(e);
    }

    public void emptyListMessage() {
        System.out.println("STUDENT!!!! Tasklist is empty so there is nothing to show :)");
    }

    public void closeScanner() {
        scanner.close();
    }

    public void goodbyeMessage() {
        System.out.println("Goodbye student, HAND.");
    }
 
}
