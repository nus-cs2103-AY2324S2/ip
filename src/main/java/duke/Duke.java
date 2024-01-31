package duke;

/**
 * This class represents a running instance of Duke, the task list!
 */
public class Duke {

    /**
     * Runs Duke
     * @param args
     */
    public static void main(String[] args) {
        Storage manager = new Storage("data");
        manager.createLog();
        Parser parser = new Parser();
        TaskList history = new TaskList(manager);
        UI ui = new UI(manager, parser, history);
        ui.run();
    }

}
