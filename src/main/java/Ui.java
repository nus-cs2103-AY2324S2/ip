import java.util.Scanner;

public class Ui {
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showLoadingError() {
        System.out.println("Error reading file");
    }

    public void showWelcome() {
        System.out.println("\t\tHello, my name is Xilef.\n\t\tHow may I help you today??\n");
    }

    public void showError(DukeException e) {
        System.out.println("\t\t" + e.getMessage());
    }

    public void showExit() {
        System.out.println("\t\tBye bye, see you next time!!!");
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showAdded(Task task) {
        System.out.println("\t\tAdded a new task to the list!");
        System.out.println("\t\t  " + task.toString());
    }

    public void showMarked(Task task) {
        System.out.println("\t\tGreat job, you have accomplished this task:\n\t\t  " + task.toString() + "\n");
    }

    public void showUnmarked(Task task) {
        System.out.println("\t\tReminder, you have not completed this task yet:\n\t\t  " + task.toString() + "\n");
    }

    public void showDeleted(Task task) {
        System.out.println("\t\tRemoved the following task:\n\t\t  " + task.toString());
    }

    public void showSize(TaskList tasks) {
        if (tasks.size() <= 1) {
            System.out.println("\t\tYou now have " + tasks.size() + " task remaining");
        } else {
            System.out.println("\t\tYou now have " + tasks.size() + " tasks remaining");
        }
    }

    public void showTasksStatus(TaskList tasks) {
        if (tasks.size() <= 1) {
            System.out.println("\t\tYou have " + tasks.size() + " too many task to do!!!" +
                    "\n\t\tQuickly start working on them!!!\n");
        } else {
            System.out.println("\t\tYou have " + tasks.size() + " too many tasks to do!!!" +
                    "\n\t\tQuickly start working on them!!!\n");
        }
    }
}
