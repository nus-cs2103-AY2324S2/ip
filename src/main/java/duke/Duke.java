package duke;

public class Duke {
    private static TaskList tasks;
    private Storage storage;
    private Command currentCommand;

    private Ui ui;

    Duke() {
        this.tasks = new TaskList();
        this.storage = new Storage(this.tasks);
        this.ui = new Ui();

    }

    public void run() throws DukeException{
        this.storage.createFile();
        this.storage.load(tasks);

        ui.hello();
        

        boolean exit = false;

        while (!exit) {
            String str = ui.getNextLine();
            Command command = ui.getCommand(str);

            try {
                command.execute(tasks, str);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }


            if (command == Command.BYE) {
                this.storage.write(tasks);
                exit = true;
            }

        }
    }


    public static void main(String[] args) throws DukeException {
        Duke duke = new Duke();
        duke.run();
    }


    public String getResponse(String input) {
        return input;
    }
}
