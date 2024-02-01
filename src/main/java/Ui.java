import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Ui {
        private static final String LINE_SEPARATOR = "____________________________________________________________";
        private Scanner scanner;

        public Ui() {
            scanner = new Scanner(System.in);
        }

        public String getUserInput() {
            return scanner.nextLine();
        }

        public void showWelcomeMessage() {
            System.out.println(LINE_SEPARATOR);
            System.out.println(" Hello! I'm Duck");
            System.out.println(" What can I do for you?");
            System.out.println(LINE_SEPARATOR);
        }

        public void showGoodbyeMessage() {
            System.out.println(LINE_SEPARATOR);
            System.out.println(" Bye. Hope to see you again soon!");
            System.out.println(LINE_SEPARATOR);
        }

        public void showError(String errorMessage) {
            System.out.println(LINE_SEPARATOR);
            System.out.println(" " + errorMessage);
            System.out.println(LINE_SEPARATOR);
        }

        public void showTaskList(List<Task> tasks) {
            System.out.println(LINE_SEPARATOR);
            if (tasks.isEmpty()) {
                System.out.println(" There are no tasks in your list.");
            } else {
                System.out.println(" Here" + (tasks.size() == 1 ? " is the " : " are the ") + tasks.size() +
                        (tasks.size() == 1 ? " task " : " tasks ") + "in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(" " + (i + 1) + "." + tasks.get(i));
                }
            }
            System.out.println(LINE_SEPARATOR);
        }

        public void showConfirmationMessage(List<Task> tasks) {
            System.out.println(LINE_SEPARATOR);
            System.out.println(" Got it. I've added this task:");
            System.out.println("   " + tasks.get(tasks.size() - 1));
            System.out.println(" Now you have " + tasks.size() + (tasks.size() == 1 ? " task" : " tasks") + " in the list.");
            System.out.println(LINE_SEPARATOR);

        }

        public void showDeleteMessage(List<Task> tasks, Task removedTask) {

            Task removedTaskk = removedTask;
            System.out.println(LINE_SEPARATOR);
            System.out.println(" Noted. I've removed this task:");
            System.out.println("   " + removedTaskk);
            System.out.println(" Now you have " + tasks.size() + (tasks.size() == 1 ? " task" : " tasks") + " in the list.");
            System.out.println(LINE_SEPARATOR);


        }
        public void invalidTaskIndex() {
            System.out.println(LINE_SEPARATOR);
            System.out.println(" Invalid task index. Please enter a valid task index.");
            System.out.println(LINE_SEPARATOR);
        }


    // Mark a task as done
        public static void showMarkTaskAsDone(Task markedTask) {
            System.out.println(LINE_SEPARATOR);
            System.out.println(" Nice! I've marked this task as done:");
            System.out.println("   " + markedTask);
            System.out.println(LINE_SEPARATOR);

        }

    // Mark a task as not done
        public static void showUnmarkTaskAsDone(Task markedTask) {
            System.out.println(LINE_SEPARATOR);
            System.out.println(" OK, I've marked this task as not done yet:");
            System.out.println("   " + markedTask);
            System.out.println(LINE_SEPARATOR);
        }
        public static void findTasksByKeyword(String keyword, List<Task> tasks) {
            System.out.println(LINE_SEPARATOR);
            System.out.println(" Here are the matching tasks in your list:");

            // Filter tasks based on the keyword
            List<Task> matchingTasks = tasks.stream()
                    .filter(task -> task.getDescription().contains(keyword))
                    .collect(Collectors.toList());

            // Display the matching tasks
            for (int i = 0; i < matchingTasks.size(); i++) {
                System.out.println(" " + (i + 1) + "." + matchingTasks.get(i));
            }

            System.out.println(LINE_SEPARATOR);
        }

        public void showDukeExceptionMessage(DukeException e) {
            System.out.println(LINE_SEPARATOR);
            System.out.println(" " + e.getMessage());
            System.out.println(LINE_SEPARATOR);
        }

        public void showDukeDataCorruptionMessage(DukeDataCorruptedException e) {
            System.out.println("Error: The data file is corrupted. Please check the file format.");
        }

        public void showIOExceptionMessage() {
            System.out.println(LINE_SEPARATOR);
            System.out.println("Error saving or loading tasks. Please check the file.");
            System.out.println(LINE_SEPARATOR);
        }

        public void showNumberFormatExceptionMessage() {
            System.out.println(LINE_SEPARATOR);
            System.out.println("Please enter a valid task index.");
            System.out.println(LINE_SEPARATOR);
        }

        public void showFileNotFoundExceptionMessage() {
            System.out.println("Data file not found. Creating a new one.");
        }

        public void showLoadingError() {
            showError("Error loading tasks from file.");
        }
    }

