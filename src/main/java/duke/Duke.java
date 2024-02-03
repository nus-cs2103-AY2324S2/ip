package duke;
/**
 * Main class of the duke application.
 *
 * @author Cedric
 */
public class Duke {
    private Parser parser = new Parser();
    /**
     * Runs the application and starts input and output
     */
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
