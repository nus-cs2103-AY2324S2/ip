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

    public static void emptyDesc(String tasktype) throws DukeException {
        if (tasktype.equals("todo")) {
            throw new DukeException("todo task.");
        } else if (tasktype.equals("deadline")) {
            throw new DukeException("task and the deadline.");
        } else if (tasktype.equals("event")) {
            throw new DukeException("event and provide the start and end timing.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Storage store = new Storage();
        Duke.intro();
        String userInput = scanner.nextLine();

        while(!userInput.equals("bye")) {
            try {
                if (userInput.startsWith("mark")) {
//                    String[] items = userInput.split(" ");
//                    if (items.length == 1) {
//                        throw new DukeException("Oops! Please state which task number you want to mark done!");
//                    }
                    store.mark(Integer.parseInt(userInput.split(" ")[1]));
                } else if (userInput.startsWith("unmark")) {
                    store.unmark(Integer.parseInt(userInput.split(" ")[1]));
                } else if (userInput.startsWith("delete")){
                    store.delete(Integer.parseInt(userInput.split(" ")[1]));
                } else if (userInput.startsWith("todo")) {
                    String[] items = userInput.split(" ", 2);
                    if (items.length == 1) {
                        Duke.emptyDesc("todo");
                    }
                    store.addItem(new Todos(userInput.split(" ", 2)[1]));
                } else if (userInput.startsWith("deadline")) {
                    String[] items = userInput.split(" ", 2);
                    if (items.length == 1) {
                        Duke.emptyDesc("deadline");
                    }
                    String[] parts = userInput.split("/by ");
                    String task = parts[0].replaceFirst("deadline ", "");
                    String date = parts[1];
                    store.addItem(new Deadlines(task, date));
                } else if (userInput.startsWith("event")) {
                    String[] items = userInput.split(" ", 2);
                    if (items.length == 1) {
                        Duke.emptyDesc("event");
                    }
                    String[] parts = userInput.split("/from ");
                    String task = parts[0].replaceFirst("event ", "");
                    String from = parts[1].split("/to")[0];
                    String to = parts[1].split("/to")[1];
                    store.addItem(new Events(task, from, to));
                } else if (userInput.equals("list")) {
                    store.printList();
                } else {
                    throw new DukeException();
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }

            userInput = scanner.nextLine();
        }

        System.out.print(Duke.line + "      Bye! Have a great day ahead :>\n" + Duke.line);
    }
}
