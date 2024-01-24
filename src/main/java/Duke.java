import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String logo = "     _______       ______     _______    _______    ___  ___  \n"
                + "    |   _  \"\\     /    \" \\   |   _  \"\\  |   _  \"\\  |\"  \\/\"  | \n"
                + "    (. |_)  :)   // ____  \\  (. |_)  :) (. |_)  :)  \\   \\  /  \n"
                + "    |:     \\/   /  /    ) :) |:     \\/  |:     \\/    \\\\  \\/ \n"
                + "    (|  _  \\\\  (: (____/ //  (|  _  \\\\  (|  _  \\\\    /   /  \n"
                + "    |: |_)  :)  \\        /   |: |_)  :) |: |_)  :)  /   / \n"
                + "    (_______/    \\\"_____/    (_______/  (_______/  |___/";
        System.out.println("Hello! I'm \n" + logo + "\n\n What can I do for you today? :) \n");
        String userInput = scanner.nextLine();
        while(!userInput.equals("bye")) {
            System.out.println("      ________________________________________________________");
            System.out.print("      " + userInput);
            System.out.println("\n      ________________________________________________________");
            userInput = scanner.nextLine();
        }
        System.out.println("\nBye! Have a great day ahead :>");
    }
}
