import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String userInput;
        ArrayList<Task> taskList = new ArrayList<>();

        System.out.println("Hello! I'm TodoPal!");
        System.out.println("What can I do for you?");

        do {
            userInput = scanner.nextLine();
            if (userInput.equals("bye")) {
                break;
            } else if (userInput.equals("list")) {
                if (taskList.isEmpty()) {
                    System.out.println("Your task list is empty.");
                } else {
                    System.out.println(" Here are the tasks in your list:");
                    for (int i = 0; i < taskList.size(); i++) {
                        System.out.println((i + 1) + "." + taskList.get(i).toString());
                    }
                }
            } else if (userInput.startsWith("mark")) {
                markTask(userInput, taskList, true);
            } else if (userInput.startsWith("unmark")) {
                markTask(userInput, taskList, false);
            } else {
                Task currTask = new Task(userInput);
                taskList.add(currTask);
                System.out.println("added: " + userInput);
            }
        } while (true);

        System.out.println("Bye! Hope to see you again!");
        scanner.close();
    }

    private static void markTask(String userInput, ArrayList<Task> taskList, boolean isMarked) {
        try {
            int taskIndex = Integer.parseInt(userInput.split(" ")[1]) - 1;

            if (taskIndex >= 0 && taskIndex < taskList.size()) {
                taskList.get(taskIndex).setDone(isMarked);

                System.out.println(" Nice! I've marked this task as " + (isMarked ? "done:" : "not done yet:"));
                System.out.println(taskList.get(taskIndex).toString());
            } else {
                System.out.println(" Task not found. Please provide a valid task index.");
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println(" Invalid input. Please provide a valid task index.");
        }
    }
}
