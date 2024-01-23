import java.util.Scanner;
import UI.UI;

public class Duke {
    public static void main(String[] args) {
        UI.greeting();
        Scanner scanner = new Scanner(System.in);
        while(true) {
            String input = scanner.nextLine().trim();
            if (input.equals("bye")) {
                UI.goodbye();
                break;
            }
            UI.repeat(input);
        }
    }
}
