import java.util.Scanner;

public class Duke {

    private static final String line = "      ________________________________________________________\n";
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
            if (userInput.startsWith("mark")) {
                store.mark(Integer.parseInt(userInput.split(" ")[1]));
            } else if (userInput.startsWith("unmark")) {
                store.unmark(Integer.parseInt(userInput.split(" ")[1]));
            } else if (userInput.startsWith("todo")) {
                store.addItem(new Todos(userInput.split(" ", 2)[1]));
            } else if (userInput.startsWith("deadline")) {
                String[] parts = userInput.split("/by ");
                String task = parts[0].replaceFirst("deadline ", "");
                String date = parts[1];
                store.addItem(new Deadlines(task, date));
            } else if (userInput.startsWith("event")) {
                String[] parts = userInput.split("/from ");
                String task = parts[0].replaceFirst("event ", "");
                String from = parts[1].split("/to")[0];
                String to = parts[1].split("/to")[1];
                store.addItem(new Events(task, from, to));
            } else if (userInput.equals("list")) {
                store.printList();
            } else {
                store.addItem(new Task(userInput));
            }
            userInput = scanner.nextLine();
        }
        System.out.print(Duke.line + "      Bye! Have a great day ahead :>\n" + Duke.line);

    }
}
