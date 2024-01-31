public class Duke {
    private enum CommandType {
        BYE, LIST, DELETE, MARK, UNMARK, TODO, EVENT, DEADLINE
    }

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);

        try {
            this.tasks = new TaskList(this.storage.readTasksFile());
        } catch (DukeException e) {
            this.ui.error(e);

        }
    }

    private void run() {
        this.ui.greet();
        boolean isExit = false;

        while (!isExit) {
            try {
                String input = this.ui.readInput();
                Command cmd = Parser.parse(input);
                cmd.execute(this.tasks, this.ui, this.storage);
                isExit = cmd.isExit();

            } catch (DukeException e) {
                ui.error(e);
            }
        }
    }

    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }
}
