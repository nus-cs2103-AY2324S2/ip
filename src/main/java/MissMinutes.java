import java.util.ArrayList;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class MissMinutes {
    private Ui ui;
    private ArrayList<Task> tasks;

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
        this.ui = new Ui();
        this.tasks = new ArrayList<Task>(100);
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
        ui.sendMsg(reply);
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
            ui.sendMsg(reply);
        } catch (IndexOutOfBoundsException err) {
            throw new MissMinutesException("This task doesn't exist!", err);
        }
    }

    public void markTask(int idx) throws MissMinutesException {
        try {
            Task curr = this.tasks.get(idx);
            curr.markAsDone();
            String reply = "Nice! I've marked this task as done: \n" + curr;
            ui.sendMsg(reply);
        } catch (IndexOutOfBoundsException err) {
            throw new MissMinutesException("This task doesn't exist!", err);
        }
    }

    public void unmarkTask(int idx) throws MissMinutesException {
        try {
            Task curr = this.tasks.get(idx);
            curr.unmark();
            String reply = "OK, I've marked this task as not done yet: \n" + curr;
            ui.sendMsg(reply);
        } catch (IndexOutOfBoundsException err) {
            throw new MissMinutesException("This task doesn't exist!", err);
        }
    }

    public void run() {
        ui.greet();
        while (true) {
            String request = ui.getInput();
            CommandType cmdType = parseCommand(request);
            boolean tasksChanged = false;

            try {
                switch (cmdType) {
                    case BYE:
                        ui.exit();
                        return;
                    case LIST:
                        ui.printTasks(this.tasks);
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
                ui.sendMsg(err.getMessage());
            }

            if (tasksChanged) {
                try {
                    this.saveTasks();
                } catch (IOException err) {
                    ui.sendMsg("Failed to save `tasks.bin`: " + err.getMessage());
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
            mm.ui.sendMsg("No `tasks.bin` found, creating empty task list");
        } catch (ClassNotFoundException err) {
            mm.ui.sendMsg(err.getMessage());
        }

        mm.run();
    }
}