import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;

public class ConvoBot {
    private static final String leftPadding = "    ";

    private static void printHorizontalLine(boolean newline) {
        System.out.println(leftPadding + "____________________________________________________________");
        if (newline) System.out.println();
    }

    private static void printWelcomeMsg() {
        printHorizontalLine(false);
        System.out.println(leftPadding + " Hello! I'm ConvoBot");
        System.out.println(leftPadding + " What can I do for you?");
        printHorizontalLine(true);
    }

    private static void printExitMsg() {
        printHorizontalLine(false);
        System.out.println(leftPadding + " Bye. Hope to see you again soon!");
        printHorizontalLine(true);
    }

    public static void main(String[] args) {
        printWelcomeMsg();

        ArrayList<Task> taskList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();

        while (!userInput.equals("bye")) {
            printHorizontalLine(false);
            ArrayList<String> inputList = new ArrayList<>(Arrays.asList(userInput.split(" ")));

            if (userInput.equals("list")) {
                System.out.println(leftPadding + " " + "Here are the tasks in your list:"); 
                for (int i = 0; i < taskList.size(); i++) {
                    int index = i+1;
                    System.out.println(leftPadding + " " + Integer.toString(index)
                        + "." + taskList.get(i).toString());
                }
            } else if (inputList.get(0).equals("mark") && inputList.size() == 2) {
                int i = Integer.parseInt(inputList.get(1)) - 1;
                taskList.get(i).markAsDone();
                System.out.println(leftPadding + " " + "Nice! I've marked this task as done:");
                System.out.println(leftPadding + " " + taskList.get(i).toString());
            } else if (inputList.get(0).equals("unmark") && inputList.size() == 2) {
                int i = Integer.parseInt(inputList.get(1)) - 1;
                taskList.get(i).markAsNotDone();
                System.out.println(leftPadding + " " + "OK, I've marked this task as not done yet:");
                System.out.println(leftPadding + " " + taskList.get(i).toString());
            } else {
                Task task;
                if (inputList.get(0).equals("todo")) {
                    task = new ToDo(userInput.substring(5));
                } else if (inputList.get(0).equals("deadline")) {
                    int j = inputList.indexOf("/by");
                    if (j == -1) {
                        // todo: handle error
                    }
                    String description = String.join(" ", inputList.subList(1, j));
                    String by = String.join(" ", inputList.subList(j+1, inputList.size()));
                    task = new Deadline(description, by);
                } else if (inputList.get(0).equals("event")) {
                    int j = inputList.indexOf("/from");
                    int k = inputList.indexOf("/to");
                    if (j == -1 || k == -1) {
                        // todo: handle error
                    }
                    String description = String.join(" ", inputList.subList(1, j));
                    String from = String.join(" ", inputList.subList(j+1, k));
                    String to = String.join(" ", inputList.subList(k+1, inputList.size()));
                    task = new Event(description, from, to);
                } else {
                    task = new Task(userInput);
                }
                taskList.add(task);
                System.out.println(leftPadding + " " + "Got it. I've added this task:");
                System.out.println(leftPadding + "   " + task.toString());
                System.out.println(leftPadding + " Now you have " + Integer.toString(taskList.size()) + " tasks in the list.");
            }
            printHorizontalLine(true);
            userInput = scanner.nextLine();
        }

        printExitMsg();
    }
}
