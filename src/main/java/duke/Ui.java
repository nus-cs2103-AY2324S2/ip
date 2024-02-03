package duke;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private Scanner sc;
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public void showLine() {
        System.out.println("    ____________________________________________________________");
    }
    public void printError(DukeException e) {
        System.out.printf("        %s\n", e.getMessage());
    }

    public void echoText(String text) {
        showLine();
        System.out.printf("      %s\n", text);
        showLine();
    }

    public String readCommand() {
        return sc.nextLine();
    }
    public void addTaskRespond(Task task, int size) {
        System.out.println("      Got it! I added:");
        System.out.printf("        %s\n", task.toString());
        System.out.printf("      You now have %d tasks in your list :D\n", size);
    }

    public void deleteTaskRespond(Task task, int size) {
        System.out.println("      Successfully removed task!");
        System.out.printf("        %s\n", task.toString());
        System.out.printf("      You have %d tasks left in the list :D\n", size);
    }

    public void markTaskRespond(Task task) {
        System.out.println("      Yippee! I have marked this task as done ;D");
        System.out.printf("        %s\n", task.toString());
    }

    public void unmarkTaskRespond(Task task) {
        System.out.println("      Awww...I have marked this task as not done yet :(");
        System.out.printf("        %s\n", task.toString());
    }

    public void endCommands() {
        this.sc.close();
        System.out.println("      Bye! Hope to see you again soon wooo!");
        showLine();
    }
}
