package zoe;
public class Zoe{
    private Parser parser;
    private TaskList tasks;
    private Ui ui;

    public Zoe() {
        ui = new Ui();
        Loader r = new Loader("./data/", "SavedTasks.txt");
        tasks =  new TaskList(r.loadTasks());
        parser = new Parser(tasks);
    }
    public static void main(String[] args) {
        new Zoe();
    }

    public String getResponse(String input) {

        if (input.equals("bye")) {
            return ui.saysBye();
        }

        return parser.process(input);
    }
}
