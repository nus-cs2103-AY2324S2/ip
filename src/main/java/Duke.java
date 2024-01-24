import java.util.Scanner;
import java.util.ArrayList;
public class Duke {

    private static String logo = "     _______       ______     _______    _______    ___  ___  \n"
            + "    |   _  \"\\     /    \" \\   |   _  \"\\  |   _  \"\\  |\"  \\/\"  | \n"
            + "    (. |_)  :)   // ____  \\  (. |_)  :) (. |_)  :)  \\   \\  /  \n"
            + "    |:     \\/   /  /    ) :) |:     \\/  |:     \\/    \\\\  \\/ \n"
            + "    (|  _  \\\\  (: (____/ //  (|  _  \\\\  (|  _  \\\\    /   /  \n"
            + "    |: |_)  :)  \\        /   |: |_)  :) |: |_)  :)  /   / \n"
            + "    (_______/    \\\"_____/    (_______/  (_______/  |___/";
    public static void intro() {
        System.out.println("Hello! I'm \n" + Duke.logo + "\n\n What can I do for you today? :) \n");
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Storage store = new Storage();
        Duke.intro();
        String userInput = scanner.nextLine();
        while(!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                store.printList();
            } else {
                UI.printResponse("      added: " + userInput);
                store.addItem(userInput);
            }
            userInput = scanner.nextLine();
        }
        UI.printBye();

    }
}
