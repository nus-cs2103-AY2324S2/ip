import java.util.Scanner;
import UI.UI;
import Storage.Storage;

public class Duke {
    public static void main(String[] args) {
        UI ui = new UI();
        UI.greeting();
        Scanner scanner = new Scanner(System.in);
        while(true) {
            String input = scanner.nextLine().trim();
            if (input.equals("bye")) {
                UI.goodbye();
                break;
            } else if (input.equals("list")) {
                ui.listItems();
            } else {
                ui.addItem(input);
            }
        }
    }
}
