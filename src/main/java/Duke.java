import java.io.FileNotFoundException;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Duke {
    private static Storage store;
    private UI ui;

    private TaskList taskList;

    public Duke(String filePath) {
        ui = new UI(store);
        taskList = new TaskList();
        store = new Storage(filePath, taskList);
        try {
            store.loadFile();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
//    private static final String line = "      ________________________________________________________\n";
//    private static String logo = "     _______       ______     _______    _______    ___  ___\n"
//            + "    |   _  \"\\     /    \" \\   |   _  \"\\  |   _  \"\\  |\"  \\/\"  |\n"
//            + "    (. |_)  :)   // ____  \\  (. |_)  :) (. |_)  :)  \\   \\  /\n"
//            + "    |:     \\/   /  /    ) :) |:     \\/  |:     \\/    \\\\  \\/\n"
//            + "    (|  _  \\\\  (: (____/ //  (|  _  \\\\  (|  _  \\\\    /   /\n"
//            + "    |: |_)  :)  \\        /   |: |_)  :) |: |_)  :)  /   /\n"
//            + "    (_______/    \\\"_____/    (_______/  (_______/  |___/";
//    private static void intro() {
//        System.out.println("Hello! I'm\n" + Duke.logo + "\n\n What can I do for you today? :)\n");
//    }

//    private static void emptyDesc(String tasktype) throws DukeException {
//        if (tasktype.equals("todo")) {
//            throw new DukeException("todo task.");
//        } else if (tasktype.equals("deadline")) {
//            throw new DukeException("task and the deadline.");
//        } else if (tasktype.equals("event")) {
//            throw new DukeException("event and provide the start and end timing.");
//        }
//    }

    public void run() {
        ui.intro();
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();

        while(!userInput.equals("bye")) {
            try {
                if (userInput.startsWith("mark")) {
//                    String[] items = userInput.split(" ");
//                    if (items.length == 1) {
//                        throw new DukeException("Oops! Please state which task number you want to mark done!");
//                    }
                    taskList.mark(Parser.parseNum(userInput));
                } else if (userInput.startsWith("unmark")) {
                    taskList.unmark(Parser.parseNum(userInput));
                } else if (userInput.startsWith("delete")){
                    taskList.delete(Parser.parseNum(userInput));
                } else if (userInput.startsWith("todo")) {
                    taskList.addItem(new Todos(Parser.parseTodo(userInput)), true);
                } else if (userInput.startsWith("deadline")) {
                    String[] parts = Parser.parseDeadline(userInput);
                    String task = parts[0];
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
                    LocalDateTime deadline = LocalDateTime.parse(parts[1], formatter);
                    taskList.addItem(new Deadlines(task, deadline), true);
                } else if (userInput.startsWith("event")) {
                    String[] parts = Parser.parseEvent(userInput);
                    taskList.addItem(new Events(parts[0], parts[1], parts[2]), true);
                } else if (userInput.equals("list")) {
                    taskList.printList();
                } else {
                    throw new DukeException();
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }

            userInput = scanner.nextLine();
        }
        ui.bye();
    }
    public static void main(String[] args) {
        new Duke("./data/Duke.txt").run();
    }
}
