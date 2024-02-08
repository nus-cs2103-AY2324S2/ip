import duke.Duke;

/**
 * Class that handles a Duke instance
 */
public class Main {

    public static void main(String[] args) {
        new Duke("data", "tasks.txt").run();
    }
}
