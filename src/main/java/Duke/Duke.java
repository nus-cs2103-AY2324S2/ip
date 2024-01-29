package Duke;

public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Parser parser;
    private Ui ui;


    public Duke() {
        this.ui = new Ui();
        this.parser = new Parser(this.ui);
        this.storage = new Storage(this.ui, this.parser);
        this.taskList = new TaskList(this.ui, this.parser, this.storage);
    }

    public void run() {
        this.taskList.listFunction();
    }


    public static void main(String[] args){
        new Duke().run();
    }
}

