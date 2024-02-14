package runner;
import java.util.Scanner;

/**
 * Class for Duke.
 */
public class Duke {
    protected int exit;
    protected final Storage storage;
    protected final TaskList taskList;

    /**
     * Constructor for a Duke object.
     */
    public Duke() {
        this.exit = 0;
        this.taskList = new TaskList();
        this.storage = new Storage(taskList);
        storage.loadList();
    }

    public String getResponse(String input) {
        return new Parser(this).handle(input);
    }
}



//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);

