import duke.Parser;
public class Duke {
    private Parser parser;
    public Duke() {
        this.parser = new Parser();
    }
    public void run() {
        parser.display("Hello! I'm Dukey.");
        parser.display("What can I do for you?");
        while (!parser.isEnded()) {
            parser.interpret();
        }
    }
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
