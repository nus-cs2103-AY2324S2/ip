public class Ui {
    private Parser parser;
    private Storage loader;
    Reply greeting = new Greeting();

    public Ui(TaskList tasks, Storage loader) {
        this.parser = new Parser(tasks, loader);
        this.loader = loader;
    }

    public void printGreeting() {
        greeting.displayMessage();
    }

    public void run() {
        printGreeting();
        parser.parse();
    }

}
