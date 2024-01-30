package duke;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }
    public void showWelcomeMessage() {
        System.out.println("Hello! I'm Duke. What can I do for you?");
    }

    public String getUserInput() {
        System.out.print("Enter your command: ");
        return scanner.next();
    }

    public String getUserInput3() {
        //System.out.print("Enter your command: ");
        return scanner.nextLine();
    }

    public void deleteMessage(String str) {
        System.out.println("I have deleted the below command: \n" + str);
    }
    public String getUserInput2() {
        //System.out.print("Enter your command: ");
        return scanner.next();
    }
    public int getUserInputInt() {
        return scanner.nextInt();
    }

    public boolean hasNextInt() {
        return scanner.hasNextInt();
    }

    public void showGoodbyeMessage() {
        System.out.println("Bye! Hope to see you again soon.");
    }

    public void showError(String errorMessage) {
        System.out.println("Error: " + errorMessage);
    }

    public void showLoadingError() {
        System.out.println("Error loading tasks from file. Starting with an empty task list.");
    }

    public void markedMessage(Task task) {
        System.out.println("\t" + "Nice! I've marked this task " +
                "as done:" + "\n" +
                "\t " + task);
    }

    public void showMessage(String str) {
        System.out.println(str);
    }
}

