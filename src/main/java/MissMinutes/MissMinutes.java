package MissMinutes;

public class MissMinutes {
    private Storage storage;
    private TaskList tasks;

    public MissMinutes(String filePath) {
        this.storage = new Storage(filePath);
        try {
            this.tasks = this.storage.loadTasks();
        } catch (MissMinutesException err) {
            Ui.sendMsg(err.getMessage());
            this.tasks = new TaskList();
        }
    }

    public void run() {
        Ui.sayHello();

        while (true) {
            String request = Ui.getInput();
            Parser.CommandType cmdType = Parser.parseCommand(request);
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
                        if (cmdType == Parser.CommandType.MARK) {
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
                        Task task = tasks.createTask(request);
                        tasks.addTask(task);
                        break;
                    case FIND:
                        tasks.findTask(request);
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