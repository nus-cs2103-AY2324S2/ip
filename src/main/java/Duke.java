import java.util.Scanner;
import UI.UI;
import Parser.Parser;

public class Duke {
    public static void main(String[] args) {
        UI ui = new UI();
        UI.greeting();
        Scanner scanner = new Scanner(System.in);
        Parser parser = new Parser();
        while(true) {
            String input = scanner.nextLine().trim();
            parser.feed(input);
            String[] output = parser.parse();
            if (output[0].equals("bye")) {
                UI.goodbye();
                break;
            } else if (output[0].equals("list")) {
                ui.listItems();
            } else if (output[0].equals("unmark")) {
                ui.unMarkTask(Integer.parseInt(output[1])-1);
            } else if (output[0].equals("mark")) {
                ui.markTaskUI(Integer.parseInt(output[1])-1);
            } else {
                ui.addItem(input);
            }
        }
    }
}
