import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class Duke {
    // constants
    static final String LINE = "\t――――――――――――――――――――――――――――――――――――――――――――――――――\n";
    static final String CHAT_BOT_NAME = "Uncle Bob";

    static ArrayList<Task> tasks = new ArrayList<>();

    static String file = "./data/duke.txt";

    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAppend);
        fw.close();
    }

    private static void loadFile(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        if (!f.isFile()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                System.out.println("An error occurred.");
            }
        }
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String[] inputs = s.nextLine().split("/");
            Task task;
            if ("[T]".equals(inputs[0])) {
                task = new Todo(inputs[2]);
            } else if ("[D]".equals(inputs[0])) {
                task = new Deadline(inputs[2], LocalDate.parse(inputs[3]));
            } else if ("[E]".equals(inputs[0])) {
                task = new Event(inputs[2], LocalDate.parse(inputs[3]), LocalDate.parse(inputs[4]));
            } else {
                task = new Task(inputs[0], inputs[2]);
            }

            if (inputs[1].equals("1")) {
                task.mark();
            }
            tasks.add(task);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            loadFile("data/duke.txt");
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        greet();
        boolean isExiting = false;
        while (!isExiting) {
            String[] inputs = br.readLine().split(" ");
            String command = inputs[0];
            String message = String.join(" ", Arrays.copyOfRange(inputs, 1, inputs.length));
            System.out.print(LINE);
            switch (command) {
                case "bye":
                    isExiting = true;
                    System.out.println("\t Bye! Hope to see you again soon!");
                    break;
                case "list":
                    handleList();
                    break;
                case "mark":
                    handleMark(message);
                    break;
                case "unmark":
                    handleUnmark(message);
                    break;
                case "todo":
                    try {
                        handleTodo(message);
                    } catch (TodoFormatException ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case "deadline":
                    try {
                        handleDeadline(message);
                    } catch (DeadlineFormatException ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case "event":
                    try {
                        handleEvent(message);
                    } catch (EventFormatException ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case "delete":
                    handleDelete(message);
                    break;
                default:
                    System.out.println("Sorry uncle don't understand what you want");
                    break;
            }
            System.out.print(LINE);
        }
    }

    // greet user
    public static void greet() {
        System.out.print(LINE + "\t Hello! I'm " + CHAT_BOT_NAME + "\n\t "
                + "What can uncle do for you?\n" + LINE);
    }

    // add task to tasks and echo
    public static void updateTasks(Task task) {
        tasks.add(task);
        System.out.println("\t Got it. Uncle added this task:\n\t\t " + task
                + "\n\t Now you have " + tasks.size() + " task(s) in the list.");
    }

    // list out all tasks
    public static void handleList() {
        if (tasks.isEmpty()) {
            System.out.println("\t Congrats, you have no more tasks! Uncle is proud of you!");
        }
        for (int i = 0; i < tasks.size(); i++){
            System.out.println("\t " + (i+1) + ". " + tasks.get(i));
        }
    }

    // mark task as done
    public static void handleMark(String message) {
        try {
            int index = Integer.parseInt(message);
            tasks.get(index - 1).mark();
            System.out.println("\t Nice! Uncle marked this task as done:\n\t\t" + tasks.get(index - 1));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\t Uncle think that you input wrong index.\n\t Use 'list' to view all tasks");
        }
    }

    // unmark task
    public static void handleUnmark(String message) {
        try {
            int index = Integer.parseInt(message);
            tasks.get(index - 1).unmark();
            System.out.println("\t Ok, Uncle marked this task as not done yet:\n\t\t"
                    + tasks.get(index - 1));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\t Uncle think that you input wrong index.\n\t Use 'list' to view all tasks");
        }
    }

    // create to-do task
    public static void handleTodo(String message) throws TodoFormatException {
        if (message.isEmpty()) {
            throw new TodoFormatException("\tUncle also need to know the message!\n" +
                    "\tCorrect Usage: todo <message>");
        } else {
            Task todo = new Todo(message);
            updateTasks(todo);
            try {
                appendToFile(file, todo.getSymbol() + "/" + todo.getStatus() + "/"
                        + todo.getDescription() + "\n");
            } catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
        }
    }

    // create deadline task
    public static void handleDeadline(String message) throws DeadlineFormatException {
        String[] args = message.split("/by");
        if (args.length == 1) {
            throw new DeadlineFormatException("\tUncle also need to know the deadline due date!\n" +
                    "\tCorrect Usage: deadline <message> /by <day/time>");
        } else if (args.length > 2) {
            throw new DeadlineFormatException("\tToo many dates for uncle to remember already!\n" +
                    "\tCorrect Usage: deadline <message> /by <day/time>");
        } else {
            String desc = args[0].trim();
            String by = args[1].trim();
            Task deadline = new Deadline(desc, LocalDate.parse(by));
            updateTasks(deadline);
            try {
                appendToFile(file, deadline.getSymbol() + "/" + deadline.getStatus() +
                        "/" + desc + "/" + by + "\n");
            } catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
        }
    }

    // create event task
    public static void handleEvent (String message) throws EventFormatException {
        String[] args = message.split("/from");
        if (args.length == 1) {
            throw new EventFormatException("\tUncle also need to know when the event is!\n" +
                    "\tCorrect Usage: event <message> /from <day/time> /to <day/time>");
        } else if (args.length > 2) {
            throw new EventFormatException("\tToo many dates for uncle to remember already!\n" +
                    "\tCorrect Usage: event <message> /from <day/time> /to <day/time>");
        } else {
            String desc = args[0].trim();
            String[] duration = args[1].split("/to");
            if (duration.length == 1) {
                throw new EventFormatException("\tUncle also need to know when the event ends!\n" +
                        "\tCorrect Usage: event <message> /from <day/time> /to <day/time>");
            } else if (duration.length > 2) {
                throw new EventFormatException("\tToo many dates for uncle to remember already!\n" +
                        "\tCorrect Usage: event <message> /from <day/time> /to <day/time>");
            } else {
                String start = duration[0].trim();
                String end = duration[1].trim();
                Task event = new Event(desc, LocalDate.parse(start), LocalDate.parse(end));
                updateTasks(event);
                try {
                    appendToFile(file, event.getSymbol() + "/" + event.getStatus() +
                            "/" + desc + "/" + start + "/" + end + "\n");
                } catch (IOException e) {
                    System.out.println("Something went wrong: " + e.getMessage());
                }
            }
        }
    }

    public static void handleDelete(String message) {
        try {
            int index = Integer.parseInt(message);
            Task removed = tasks.get(index - 1);
            tasks.remove(index - 1);
            System.out.println("\t Uncle deleted this item:\n\t\t" + removed
                    + "\n\t Now you have " + tasks.size() + " task(s) in the list.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\t Uncle think that you input wrong index.\n\t Use 'list' to view all tasks");
        }
    }
}