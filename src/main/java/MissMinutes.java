import java.util.Scanner;
import java.util.ArrayList;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class MissMinutes {
    private final Scanner stdin;
    private ArrayList<Task> tasks;
    private static final String separator = "-".repeat(60);
    private static final String logo =
            " __  __ _           __  __ _             _                  \n" +
            " |  \\/  (_)         |  \\/  (_)           | |              \n" +
            " | \\  / |_ ___ ___  | \\  / |_ _ __  _   _| |_ ___  ___    \n" +
            " | |\\/| | / __/ __| | |\\/| | | '_ \\| | | | __/ _ \\/ __| \n" +
            " | |  | | \\__ \\__ \\ | |  | | | | | | |_| | ||  __/\\__ \\\n" +
            " |_|  |_|_|___/___/ |_|  |_|_|_| |_|\\__,_|\\__\\___||___/  \n" +
            "                                                            \n";

    public enum CommandType {
        BYE, LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT, UNKNOWN
    }

    private static CommandType parseCommand(String command) {
        if (command.toLowerCase().startsWith("bye")) {
            return CommandType.BYE;
        } else if (command.toLowerCase().startsWith("list")) {
            return CommandType.LIST;
        } else if (command.toLowerCase().startsWith("mark")) {
            return CommandType.MARK;
        } else if (command.toLowerCase().startsWith("unmark")) {
            return CommandType.UNMARK;
        } else if (command.toLowerCase().startsWith("delete")) {
            return CommandType.DELETE;
        } else if (command.toLowerCase().startsWith("todo")) {
            return CommandType.TODO;
        } else if (command.toLowerCase().startsWith("deadline")) {
            return CommandType.DEADLINE;
        } else if (command.toLowerCase().startsWith("event")) {
            return CommandType.EVENT;
        } else {
            return CommandType.UNKNOWN;
        }
    }

    public MissMinutes() {
        this.stdin = new Scanner(System.in);
        this.tasks = new ArrayList<Task>(100);
    }

    public void sendMsg(String body) {
        System.out.println(separator);
        System.out.println(body);
        System.out.println(separator);
    }

    public Task createTask(String input) throws MissMinutesException {
        String[] split = input.split(" ", 2);
        String desc = split.length > 1 ? split[1] : "";

        try {
            if (split[0].equalsIgnoreCase("TODO")) {
                return Todo.fromStr(desc);
            } else if (split[0].equalsIgnoreCase("DEADLINE")) {
                return Deadline.fromStr(desc);
            } else if (split[0].equalsIgnoreCase("EVENT")) {
                return Event.fromStr(desc);
            } else {
                throw new MissMinutesException("Invalid command name, please try again!");
            }
        } catch (ArrayIndexOutOfBoundsException err) {
            throw new MissMinutesException("Incomplete command, please try again!");
        }
    }

    public void addTask(Task task) {
        this.tasks.add(task);
        String reply = "Got it. I've added this task: \n" +
                task + "\n" +
                "Now you have " + this.tasks.size() + " tasks in the list.";
        this.sendMsg(reply);
    }

    public void deleteTask(String input) throws MissMinutesException {
        String[] split = input.split(" ");
        int idx;
        try {
            idx = Integer.parseInt(split[1]) - 1; // 0 indexed
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException err) {
            throw new MissMinutesException("Please enter a valid index. For e.g, a correct usage is: delete 2");
        }
        try {
            Task curr = this.tasks.get(idx);
            this.tasks.remove(idx);
            String reply = "Noted. I've removed this task:\n"
                    + curr + "\n"
                    + "Now you have " + this.tasks.size() + " tasks in the list.";
            this.sendMsg(reply);
        } catch (IndexOutOfBoundsException err) {
            throw new MissMinutesException("This task doesn't exist!", err);
        }
    }

    public void greet() {
        this.sendMsg("Hello! I'm \n" + logo
                    + "What can I do for you");
    }

    public void exit() {
        this.sendMsg("Bye. Hope to see you again soon!");
        this.stdin.close();
    }

    public void printTasks() {
        if (this.tasks.isEmpty()) {
            this.sendMsg("There are no tasks in your list.");
            return;
        }
        StringBuilder reply = new StringBuilder("Here are the tasks in your list: ");
        for (int i = 0; i < this.tasks.size(); i++) {
            reply.append("\n")
                    .append((i + 1))
                    .append(". ")
                    .append(this.tasks.get(i));
        }
        this.sendMsg(reply.toString());
    }

    public void markTask(int idx) throws MissMinutesException {
        try {
            Task curr = this.tasks.get(idx);
            curr.markAsDone();
            String reply = "Nice! I've marked this task as done: \n" + curr;
            this.sendMsg(reply);
        } catch (IndexOutOfBoundsException err) {
            throw new MissMinutesException("This task doesn't exist!", err);
        }
    }

    public void unmarkTask(int idx) throws MissMinutesException {
        try {
            Task curr = this.tasks.get(idx);
            curr.unmark();
            String reply = "OK, I've marked this task as not done yet: \n" + curr;
            this.sendMsg(reply);
        } catch (IndexOutOfBoundsException err) {
            throw new MissMinutesException("This task doesn't exist!", err);
        }
    }

    public void run() {
        while (true) {
            String request = this.stdin.nextLine();
            CommandType cmdType = parseCommand(request);
            boolean tasksChanged = false;

            try {
                switch (cmdType) {
                    case BYE:
                        return;
                    case LIST:
                        printTasks();
                        break;
                    case MARK:
                    case UNMARK:
                        tasksChanged = true;
                        String[] split = request.split(" ");
                        int idx;
                        try {
                            idx = Integer.parseInt(split[1]) - 1; // 0 indexed
                        } catch (NumberFormatException | ArrayIndexOutOfBoundsException err) {
                            throw new MissMinutesException("Please enter a valid index. The correct usage is `mark <idx>`");
                        }
                        if (cmdType == CommandType.MARK) {
                            markTask(idx);
                        } else {
                            unmarkTask(idx);
                        }
                        break;
                    case DELETE:
                        tasksChanged = true;
                        deleteTask(request);
                        break;
                    case TODO:
                    case DEADLINE:
                    case EVENT:
                        tasksChanged = true;
                        Task task = this.createTask(request);
                        this.addTask(task);
                        break;
                    case UNKNOWN:
                        throw new MissMinutesException("Oh, I'm sowwy, I didn't undewstand dat. (>_<) Can I hewp wif sumthin' else, pwease? UwU");
                }
            } catch (MissMinutesException err) {
                this.sendMsg(err.getMessage());
            }

            if (tasksChanged) {
                try {
                    this.saveTasks();
                } catch (IOException err) {
                    this.sendMsg("Failed to save `tasks.bin`: " + err.getMessage());
                }
            }
        }
    }

    private void saveTasks() throws IOException {
        FileOutputStream fileOut = new FileOutputStream("tasks.bin");
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(tasks);
        out.close();
        fileOut.close();
    }

    private void loadTasks() throws IOException, ClassNotFoundException {
        FileInputStream fileIn = new FileInputStream("tasks.bin");
        ObjectInputStream out = new ObjectInputStream(fileIn);

        @SuppressWarnings("unchecked")
        ArrayList<Task> tasks = (ArrayList<Task>) out.readObject();

        this.tasks = tasks;

        out.close();
        fileIn.close();
    }

    public static void main(String[] args) {
        MissMinutes mm = new MissMinutes();

        try {
            mm.loadTasks();
        } catch (IOException err) {
            mm.sendMsg("No `tasks.bin` found, creating empty task list");
        } catch (ClassNotFoundException err) {
            mm.sendMsg(err.getMessage());
        }

        mm.greet();
        mm.run();
        mm.exit();
    }
}