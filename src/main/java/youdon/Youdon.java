package youdon;

/**
 * The main class that starts the Youdon chatbot application.
 */
public class Youdon {

    private final Ui ui;
    private final TaskList tasks;

    public Youdon() {
        // initialise ui and storage (filepath = "./data/save.txt")
        this.ui = new Ui();
        Storage storage = new Storage("./data/save.txt");
        this.tasks = new TaskList(storage.loadData());
        Parser parser = new Parser(ui, tasks, storage);
    }

    /**
     * The main method of the application.
     *
     * @param args The command-line arguments passed to the application.
     */
    public static void main(String[] args) {
        // initialise ui and storage (filepath = "./data/save.txt")
        Ui ui = new Ui();
        Storage storage = new Storage("./data/save.txt");
        TaskList tasks = new TaskList(storage.loadData());
        Parser parser = new Parser(ui, tasks, storage);

        // chatbot welcome message
        ui.printWelcomeMsg();

        // parse input
        parser.parse();
    }

    protected String getResponse(String input) {
        // if input == "bye", print chatbot bye message
        if (input.equals("bye")) {
            return ui.getByeMsg();
        }

        // if input == "list", return tasklist
        if (input.equals("list")) {
            return ui.getTaskList(this.tasks);
        }

        if (input.contains(" ")) {
            // if input data has 2 parts, split into command & task number
            String[] parts = input.split(" ", 2);
            String command = parts[0];
            String task = parts[1];

            // if input == "find", find all tasks with the given word
            if (command.equals("find")) {
                TaskList foundList = new TaskList();
                for (int i = 0; i < tasks.size(); i++) {
                    Task currTask = tasks.get(i);
                    if (currTask.toString().contains(task)) {
                        foundList.add(currTask);
                    }
                }
                return ui.getTaskList(foundList);
            }

            // if input == "mark", mark the specified task as done
            if (command.equals("mark")) {
                // mark task as done in array
                int taskNumber = Integer.parseInt(task);
                // print out changes
                return ui.getMarkMsg(tasks, taskNumber);
            }

            // if input == "unmark", mark the specified task as undone
            if (command.equals("unmark")) {
                // mark task as undone in array
                int taskNumber = Integer.parseInt(task);
                // print out changes
                return ui.getUnmarkMsg(tasks, taskNumber);
            }

            // if input == "delete", delete the specified task
            if (command.equals("delete")) {
                int taskNumber = Integer.parseInt(task);
                // print out changes
                return ui.getDeleteMsg(tasks, taskNumber);
            }

            // differentiate between type of tasks and add to tasklist
            if (command.equals("todo")) {
                // print out task added
                return ui.getTaskAddedMsg(tasks);
            }

            if (command.equals("deadline")) {
                // print out task added
                return ui.getTaskAddedMsg(tasks);
            }

            if (command.equals("event")) {
                // print out task added
                return ui.getTaskAddedMsg(tasks);
            }
        }
        return "Error!";
    }
}
