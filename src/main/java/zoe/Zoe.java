package zoe;
public class Zoe{
    private Parser parser;
    private TaskList tasks;
    private Ui ui;

    private Saver s;

    public Zoe() {
        ui = new Ui();
        Loader r = new Loader("./data/", "SavedTasks.txt");
        tasks =  new TaskList(r.loadTasks());
        parser = new Parser(tasks);
        s = new Saver(tasks.getTasks());
    }
    public static void main(String[] args) {
        new Zoe();
    }

    public String getResponse(String input) {
        if (input.equals("bye")) {
            s.saveTo("./data/");
            return ui.saysBye();
        }
        return parser.process(input);
    }
}
