import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static final String NAME = "Arctic";
    private static final Integer BORDER_LEN = 60;
    private static final ArrayList<Task> TASKS = new ArrayList<>();

    private static String duplicateChar(Character c, Integer num) {
        return String.valueOf(c).repeat(num);
    }

    private static String getBorder() {
        return duplicateChar('_', BORDER_LEN);
    }

    private static void userGreeting () {
        System.out.println(getBorder());
        System.out.printf("Hello! I'm %s\n", NAME);
        System.out.println("What can I do for you?");
        System.out.println(getBorder());
    }

    private static void userFarewell() {
        System.out.println(getBorder());
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(getBorder());
    }

    private static void printCommandOutput(String output) {
        System.out.println(getBorder());
        System.out.printf(output);
        System.out.println(getBorder());
    }

    private static void handleCommands() {
        Scanner scnr = new Scanner(System.in);
        while (true) {
            String userInput = scnr.nextLine();
            String[] userInputSplit = userInput.split(" ");
            String command = userInputSplit[0];

            switch (command) {
                case "list":
                    StringBuilder output  = new StringBuilder();
                    for (int i = 0; i < TASKS.size(); i++) {
                        Task task = TASKS.get(i);
                        output.append(String.format("%d. %s\n", i + 1, task.toString()));
                    }
                    printCommandOutput(output.toString());

                    break;
                case "bye":
                    break;
                case "mark":
                case "unmark":
                    int taskNum = Integer.parseInt(userInputSplit[1]);
                    Task task = TASKS.get(taskNum - 1);

                    if (command.equals("mark")) {
                        task.mark();
                        printCommandOutput(String.format("Nice! I've marked this task as done:\n  %s\n", task.toString()));
                    } else {
                        task.unmark();
                        printCommandOutput(String.format("OK, I've marked this task as not done yet::\n  %s\n", task.toString()));
                    }

                    break;
                default:
                    TASKS.add(new Task(userInput));
                    printCommandOutput(String.format("added: %s\n", userInput));

                    break;
            }
        }
    }

    public static void main(String[] args) {
        userGreeting();

        handleCommands();

        userFarewell();
    }
}
