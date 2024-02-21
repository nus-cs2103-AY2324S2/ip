package duke;

import duke.commands.CommandList;
import duke.commands.DukeCommandNotFoundException;
import duke.tasks.TaskList;
import duke.ui.Ui;
import javafx.application.Application;
import javafx.stage.Stage;


public class Duke extends Application {


    private Ui ui;
    private final CommandList commands = new CommandList();
    private TaskList tasks = new TaskList();
    private final Storage st = new Storage("data.txt");

    public Duke() {
    }

    public Storage getStorage() {
        return this.st;
    }

    public TaskList getTasks() {
        return this.tasks;
    }

    public Ui getUi() {
        return this.ui;
    }

    public void processInput(String input) {
        assert(input != null);
        assert(!input.contains("\n"));
        Parser parser = new Parser(input);
        try {
            this.commands.get(parser.next()).run(parser, this);
        } catch (DukeCommandNotFoundException e) {
            this.ui.print("no matching command...");
        } catch (DukeException e) {
            this.ui.print(String.format("OH NYO!!!!!!!!!!! %s", e.getMessage()));
        }
    }

    @Override
    public void start(Stage stage) {
        this.ui = new Ui(stage, this);
        try {
            this.tasks = this.st.loadTasks();
        } catch (DukeException e) {
            this.ui.print(String.format
                    ("Error loading task data: %s"
                            + "\n\nPlease delete 'data.txt' and try again. Bye bye...", e.getMessage()));
            System.exit(1);
        }
    }
}
