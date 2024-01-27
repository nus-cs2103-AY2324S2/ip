import java.util.ArrayList;


public class Ui {
    public void showWelcome() {
        showLine();
        System.out.println("     Hello! I'm Artemis");
        System.out.println("     What can I do for you?");
        showLine();
    }

    public void showLoadingError() {
        showLine();
        System.out.println("     OOPS!!! Error loading tasks from the file.");
        showLine();
    }

    public void showTasks(ArrayList<Task> tasks) {
        showLine();
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); ++i) {
            System.out.println("     " + (i + 1) + "." + tasks.get(i));
        }
        showLine();
    }

    public void showMessage(String message) {
        showLine();
        System.out.println("     " + message);
        showLine();
    }

    public void showLine() {
        System.out.println("    ____________________________________________________________");
    }
}
