package duke;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Duke {
//    public static final String DBPATH = "../data/duke.txt"; // uncommment for runtest.sh
    public static final String DBPATH = "data/duke.txt";
    private Storage storage;
    private TaskList myTasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            myTasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            myTasks = new TaskList();
        }
    }

    /**
     * Saves the current tasklist to the hard drive
     */
    private void save() {
        List<String> lines = new ArrayList<>();
        for (Task t : this.myTasks.taskList) {
            String stringTask = TaskList.task2Db(t);
            lines.add(stringTask);
        }
        this.storage.writeLinesToFile(lines);
    }

    public void run() {
        String greeting = "____________________________________________________________\n" +
                " Hello! I'm steve\n" +
                " What can I do for you?\n" +
                "____________________________________________________________";

        String goodbye = " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________";

        System.out.println(greeting);

        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        String line = scanner.nextLine(); // Get first input
        Parser parser = new Parser(scanner, this.myTasks);

        while (parser.processCmd(line)) {
            line = scanner.nextLine();
        }
        System.out.println(goodbye);
        // save the tasks from myTasks to duke.Storage
        this.save();

    }

    public static void main(String[] args) {
        new Duke(DBPATH).run();
    }

}
