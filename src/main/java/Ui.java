import java.util.Scanner;
public class Ui {
    public void showWelcome() {
        System.out.println("Hello I'm NoisyChatter");
        System.out.println("What can I do for you?");
    }

    public String getUserInput() {
        System.out.print("Enter command: ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }


    public void showError(String errorMessage) {
        System.out.println("Error: " + errorMessage);
    }

    public void showLoadingError() {
        System.out.println("Error loading data from file.");
    }
}

