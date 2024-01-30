import java.util.ArrayList;
import java.util.List;
public class Ui {
        public void showWelcome() {

            System.out.println("Hello! I'm Aether!");
            System.out.println("What can I do for you?");

        }

        public void showLoadingError() {
            System.out.println("Error loading tasks from file.");
        }

    public void showSeparator() {
        System.out.println("_____________________________");
    }
        public void showErrorMessage(String errorMessage) {
            System.out.println("OOPS!!! " + errorMessage);
            System.out.println("_____________________________"); // Add a separator line
        }

    public void showTaskList(TaskList taskList) {
        System.out.println("Here are the tasks in your list:");
        List<Task> tasks = taskList.getTasks();
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
        System.out.println("_____________________________"); // Add a separator line
    }


    public void showTaskAdded(Task task, int taskCount) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        System.out.println("_____________________________"); // Add a separator line
    }

    public void showTaskDeleted(Task deletedTask, int remainingTasks) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + deletedTask);
        System.out.println("Now you have " + remainingTasks + " tasks in the list.");
        System.out.println("_____________________________"); // Add a separator line
    }

    public void showInvalidTaskIndex() {
        System.out.println("Invalid task index. Please provide a valid task index.");
    }

    public void showTaskMarked(Task markedTask) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + markedTask);
        System.out.println("_____________________________"); // Add a separator line
    }

    public void showTaskUnmarked(Task unmarkedTask) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + unmarkedTask);
        System.out.println("_____________________________"); // Add a separator line
    }
    public void showGoodbye() {
        System.out.println("Goodbye. Hope to see you again soon!");
        System.out.println("_____________________________"); // Add a separator line
    }

}
