import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class ConvoBot {
    private static final String leftPadding = "    ";

    private static void printHorizontalLine(boolean newline) {
        System.out.println(leftPadding + "____________________________________________________________");
        if (newline) System.out.println();
    }

    private static void printWelcomeMsg() {
        System.out.println(leftPadding + " Hello! I'm ConvoBot");
        System.out.println(leftPadding + " What can I do for you?");
    }

    private static void printExitMsg() {
        System.out.println(leftPadding + " Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        printHorizontalLine(false);
        printWelcomeMsg();
        printHorizontalLine(true);

        ArrayList<Task> taskList = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();

        while (!userInput.equals("bye")) {
            printHorizontalLine(false);
            String[] inputList = userInput.split(" ");
            if (userInput.equals("list")) {
                System.out.println(leftPadding + " " + "Here are the tasks in your list:"); 
                for (int i = 0; i < taskList.size(); i++) {
                    int index = i+1;
                    System.out.println(leftPadding + " " + Integer.toString(index)
                        + "." + taskList.get(i).toString());
                }
            } else if (inputList[0].equals("mark") && inputList.length == 2) {
                int i = Integer.parseInt(inputList[1]) - 1;
                taskList.get(i).markAsDone();
                System.out.println(leftPadding + " " + "Nice! I've marked this task as done:");
                System.out.println(leftPadding + " " + taskList.get(i).toString());
            } else if (inputList[0].equals("unmark") && inputList.length == 2) {
                int i = Integer.parseInt(inputList[1]) - 1;
                taskList.get(i).markAsNotDone();
                System.out.println(leftPadding + " " + "OK, I've marked this task as not done yet:");
                System.out.println(leftPadding + " " + taskList.get(i).toString());
            } else {
                Task task = new Task(userInput); // defaults to not done
                taskList.add(task);
                System.out.println(leftPadding + " " + "added: " + userInput);
            }
            printHorizontalLine(true);
            userInput = scanner.nextLine();
        }

        printHorizontalLine(false);        
        printExitMsg();
        printHorizontalLine(true);
    }
}
