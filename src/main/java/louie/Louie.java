package louie;

import louie.commands.CommandList;
import louie.commands.LouieCommandNotFoundException;
import louie.tasks.TaskList;
import louie.ui.Ui;
import javafx.application.Application;
import javafx.stage.Stage;


public class Louie extends Application {


    private Ui ui;
    private final CommandList commands = new CommandList();
    private TaskList tasks = new TaskList();
    private final Storage st = new Storage("data.txt");

    public Louie() {
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
        } catch (LouieCommandNotFoundException e) {
            this.ui.print("no matching command...");
        } catch (LouieException e) {
            this.ui.print(String.format("OH NYO!!!!!!!!!!! %s", e.getMessage()));
        }
    }

    @Override
    public void start(Stage stage) {
        this.ui = new Ui(stage, this);
        try {
            this.tasks = this.st.loadTasks();
        } catch (LouieException e) {
            this.ui.print(String.format
                    ("Error loading task data: %s"
                            + "\n\nPlease delete 'data.txt' and try again. Bye bye...", e.getMessage()));
            System.exit(1);
        }
    }
}
