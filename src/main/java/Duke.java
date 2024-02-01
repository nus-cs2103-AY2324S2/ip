
public class Duke {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    enum Command {
        BYE, LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT
    }

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.loadTasks());
        } catch (DukeException e){
            ui.displayError(e.getMessage());
            this.tasks = new TaskList();
        }
    }

    public void echo() {

        while (ui.next()) {
            String msg = ui.getInput();
            Parser parser = new Parser(msg);

            try {
                if (msg.equalsIgnoreCase(Command.BYE.name())) {

                    ui.exit();
                    break;

                } else if (msg.equalsIgnoreCase(Command.LIST.name())) {

                    ui.formatReply(tasks.toDoList());

                } else if (msg.toUpperCase().startsWith(Command.MARK.name())){

                    int taskIndex = parser.parseMarkToDo();
                    ui.formatReply(tasks.markToDo(taskIndex));
                    tasks.saveToStorage(storage);

                } else if (msg.toUpperCase().startsWith(Command.UNMARK.name())){

                    int taskIndex = parser.parseUnMarkToDo();
                    ui.formatReply(tasks.unMarkToDo(taskIndex));
                    tasks.saveToStorage(storage);

                } else if (msg.toUpperCase().startsWith(Command.DELETE.name())) {

                    int taskIndex = parser.parseDeleteToDo();
                    ui.formatReply(tasks.deleteToDo(taskIndex));
                    tasks.saveToStorage(storage);

                } else {

                    Task t = parser.parseAddToDo();
                    ui.formatReply(tasks.addToDo(t));
                    tasks.saveToStorage(storage);

                }
            } catch (DukeException e) {

                ui.displayError(e.getMessage());

            }
        }

        ui.closeScanner();

    }

    public void run() {
        ui.greetUser();
        this.echo();
    }

    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }
}
