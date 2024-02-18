package duke;

import duke.task.Task;
import duke.task.TaskList;

import java.util.Scanner;

public class Ui {
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showLoadingError() {
    }

    public static String showWelcome() {
        return "\t\tHello, my name is Xilef.\n\t\tHow may I help you today??\n";
    }

    public String showError(DukeException e) {
        return "\t\t" + e.getMessage();
    }

    public String showExit() {
        return "\t\tBye bye, see you next time!!!";
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public String showAdded(Task task) {
        String s = "\t\tAdded a new task to the list!\n\t\t  " + task.toString();
        return s;
    }

    public String showMarked(Task task) {
        return "\t\tGreat job, you have accomplished this task:\n\t\t  " + task.toString() + "\n";
    }

    public String showUnmarked(Task task) {
        return "\t\tReminder, you have not completed this task yet:\n\t\t  " + task.toString() + "\n";
    }

    public String showDeleted(Task task) {
        return "\t\tRemoved the following task:\n\t\t  " + task.toString();
    }

    public String showSize(TaskList tasks) {
        if (tasks.size() <= 1) {
            return "\t\tYou now have " + tasks.size() + " task remaining";
        } else {
            return "\t\tYou now have " + tasks.size() + " tasks remaining";
        }
    }

    public String showTasksStatus(TaskList tasks) {
        if (tasks.size() <= 1) {
            return "\t\tYou have " + tasks.size() + " too many task to do!!!" +
                    "\n\t\tQuickly start working on them!!!\n";
        } else {
            return "\t\tYou have " + tasks.size() + " too many tasks to do!!!" +
                    "\n\t\tQuickly start working on them!!!\n";
        }
    }
}
