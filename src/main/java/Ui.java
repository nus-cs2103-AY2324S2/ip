import java.util.Scanner;

public class Ui {

    public Ui() {
        
    }

    public void scan() {
        Scanner scanner = new Scanner(System.in);

        boolean exitScan = false;
        while (!exitScan) {
            String userInput = scanner.nextLine();
            try {
                exitScan = CommandHandler.executeCommand(userInput);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
        scanner.close();
    }
}
