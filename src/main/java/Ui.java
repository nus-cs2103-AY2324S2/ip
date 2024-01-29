import java.util.Scanner;

public class Ui {

    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    private static final String HORIZONTAL_LINE =
            "_______________________________________";
    public void showLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showLoadingError() {
        System.out.println("Error");
    }

    public void showWelcome() {
        System.out.println("Hello");
    }

    public void showFarewell() {
        this.scanner.close();
        System.out.println("bye");
    }

    public void showTaskList(TaskList taskList) {
        for (Task task : taskList) {
            System.out.println(task);
        }
    }

    public void showAddedTask(Task task) {
        System.out.println("added: " + task.toString());
    }
}
