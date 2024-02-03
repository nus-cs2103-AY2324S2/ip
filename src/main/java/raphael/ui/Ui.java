package raphael.ui;
import java.util.Scanner;
import raphael.task.TaskList;
import raphael.task.Task;
public class Ui {
    private static final String START_LINE = "----------------"
            + "------------[Rep"
            + "ort]------------"
            + "----------------";

    private static final String END_LINE = "----------------"
            + "------------[End"
            + "ing]------------"
            + "----------------";
    private static final String WARNING_LINE = "----------------"
            + "-----------<WARN"
            + "ing!>-----------"
            + "----------------";
    private static final String DIVIDER = "----------------"
            + "----------------"
            + "----------------"
            + "----------------";
    private static void printStartLine() {
        System.out.print(Ui.START_LINE);
    }

    private static void printEndLine() {
        System.out.println(Ui.END_LINE);
    }
    private static void printWarningLine() {
        System.out.print(Ui.WARNING_LINE);
    }
    public void showLine() {
        System.out.println(Ui.DIVIDER);
    }
    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }
    public void showLoadingError() {
        System.out.println("Failed to load task list!");
    }
    public void showWelcome() {
        System.out.println(raphael.Raphael.LOGO);
        System.out.printf("Hi! I am %s, how can I help you?\n", raphael.Raphael.BOT_NAME);
    }
    public void showAddOutput(TaskList tasks, Task task) {
        System.out.printf("Roger that! I have added the following task into your list:\n" +
                "\t%s\n", task);
        System.out.println(tasks.getSize());
    }
    public void showDeleteOutput(Task task) {
        System.out.printf("Alrigthy! I have deleted the following task for you:\n"
                + "\t%s\n", task);
    }
    public void showListOutput(String text) {
        System.out.println(text);
    }
    public void showMarkOutput(String text) {
        System.out.println(text);
    }
    public void showUnmarkOutput(String text) {
        System.out.println(text);
    }
    public static void end() {
        final String endings = "Bye. It is an honor to serve you.\n"
                + "Hope to see you again soon!";
        System.out.println(endings);
        Ui.printEndLine();
    }
    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
}
