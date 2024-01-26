public class MissMinutes {
    private Storage storage;
    private TaskList tasks;

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

    public MissMinutes(String filePath) {
        this.storage = new Storage(filePath);
        try {
            this.tasks = this.storage.loadTasks();
        } catch (MissMinutesException err) {
            Ui.sendMsg(err.getMessage());
            this.tasks = new TaskList();
        }
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

    public void run() {
        Ui.sayHello();

        while (true) {
            String request = Ui.getInput();
            CommandType cmdType = parseCommand(request);
            boolean tasksChanged = false;

            try {
                switch (cmdType) {
                    case BYE:
                        Ui.sayBye();
                        return;
                    case LIST:
                        Ui.printTasks(tasks);
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
                            tasks.markTask(idx);
                        } else {
                            tasks.unmarkTask(idx);
                        }
                        break;
                    case DELETE:
                        tasksChanged = true;
                        tasks.deleteTask(request);
                        break;
                    case TODO:
                    case DEADLINE:
                    case EVENT:
                        tasksChanged = true;
                        Task task = this.createTask(request);
                        tasks.addTask(task);
                        break;
                    case UNKNOWN:
                        throw new MissMinutesException("Oh, I'm sowwy, I didn't undewstand dat. (>_<) Can I hewp wif sumthin' else, pwease? UwU");
                }
            } catch (MissMinutesException err) {
                Ui.sendMsg(err.getMessage());
            }

            if (tasksChanged) {
                try {
                    storage.saveTasks(tasks);
                } catch (MissMinutesException err) {
                    Ui.sendMsg(err.getMessage());
                }
            }
        }
    }

    public static void main(String[] args) {
        MissMinutes mm = new MissMinutes("tasks.bin");
        mm.run();
    }
}