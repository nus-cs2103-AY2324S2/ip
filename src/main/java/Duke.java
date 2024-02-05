import java.util.Scanner;
import java.util.ArrayList;
public class Duke {

    // A class for elements in the taskList, with a name and an indicator of whether the task is marked
    private static class Task{
        private final String listItemName;
        private boolean isDone;

        // Constructor for the Task class, initialised with the name of the task
        public Task(String itemName) {
            this.listItemName = itemName;
            this.isDone = false;
        }

        // Allows the user to modify the status of the task
        public void modifyStatus(boolean status) {
            this.isDone = status;
        }

        // Returns the name of the task
        public String getName() {
            return this.listItemName;
        }

        // Returns the status of the task
        public boolean getStatus() {
            return this.isDone;
        }
    }

    // ArrayList for storing user-added tasks.
    private static final ArrayList<Task> taskList = new ArrayList<>();

    // Greets the user
    private static void greet() {
        System.out.println("\t____________________________________________________________");
        System.out.println("\t Greetings! I'm Barry.\n" + "\t How could I assist you?");
        System.out.println("\t____________________________________________________________\n");
    }

    // Exit the program
    private static void exit() {
        System.out.println("\t____________________________________________________________");
        System.out.println("\t Bye, see you next time! :)");
        System.out.println("\t____________________________________________________________\n");
    }

    // Echo mode, echos all input back to the user unless the input is "bye"
    private static void echo() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            // String input
            String inputText = scanner.nextLine().strip();
            if (inputText.equals("bye")) {
                break;
            } else {
                System.out.println("\t____________________________________________________________");
                System.out.println("\t " + inputText);
                System.out.println("\t____________________________________________________________\n");
            }
        }
    }

    // Task list mode, allows user to enter tasks into a list, and print all tasks in the list currently with "list", exits with "bye"
    private static void taskListMode() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            // String input
            String inputText = scanner.nextLine().strip();
            if (inputText.equals("bye")) {
                // Exit task list mode
                break;
            } else if (inputText.equals("list")) {
                // List all tasks in taskList currently
                int taskListSize = taskList.size();
                System.out.println("\t____________________________________________________________");
                System.out.println("\t Let's take a look at the tasks in your list:");
                for (int i = 0; i < taskListSize; i++) {
                    String checkMark;
                    // If-else to check the status of the task
                    if (taskList.get(i).getStatus()) {
                        checkMark = "X";
                    } else {
                        checkMark = " ";
                    }
                    System.out.println("\t " + (i + 1) + ".[" + checkMark + "] " + taskList.get(i).getName());
                }
                System.out.println("\t____________________________________________________________");
            } else if (inputText.startsWith("mark")) {
                // Mark indicated task
                int taskIndex = Integer.parseInt(inputText.substring(5)) - 1;
                taskList.get(taskIndex).modifyStatus(true);
                System.out.println("\t____________________________________________________________");
                System.out.println("\t Nice, i have marked this task as done. Great job! :)");
                System.out.println("\t\t[X] " + taskList.get(taskIndex).getName());
                System.out.println("\t____________________________________________________________\n");
            } else if (inputText.startsWith("unmark")){
                // Unmark indicated task
                int taskIndex = Integer.parseInt(inputText.substring(7)) - 1;
                taskList.get(taskIndex).modifyStatus(false);
                System.out.println("\t____________________________________________________________");
                System.out.println("\t Alright, i have marked this task as not done yet:");
                System.out.println("\t\t[ ] " + taskList.get(taskIndex).getName());
                System.out.println("\t____________________________________________________________\n");
            } else {
                // Add new task to taskList
                taskList.add(new Task(inputText));
                System.out.println("\t____________________________________________________________");
                System.out.println("\t added: " + inputText);
                System.out.println("\t____________________________________________________________\n");
            }
        }
    }

    public static void main(String[] args) {
        greet();
        taskListMode();
        exit();
    }
}