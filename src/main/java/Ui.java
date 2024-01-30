import java.util.Scanner;

public class Ui {
    public void showLine() {
        System.out.println("--------------------------------------------------");
    }

    public void showWelcome() {
        showLine();
        System.out.println("Hello! I'm TheGiantPeach\nWhat can I do for you?");
        showLine();
    }

    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public void showError(String errMsg) {
        System.out.println(errMsg);
    }
}
