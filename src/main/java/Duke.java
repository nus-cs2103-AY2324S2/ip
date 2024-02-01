import java.io.File;
import java.io.IOException;
import java.util.*;
public class Duke {
    private Storage storage;
    private TaskList tasks;

    private static final String[] terminateKeywords = {"bye", "BYE", "Bye"};
    private static final List<String> exitProgramme = Arrays.asList(terminateKeywords);

    public Duke(String filePath) {
        try {
            this.storage = new Storage(filePath);
            this.tasks = new TaskList(storage.loadTasks());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            this.tasks = new TaskList();
        }
    }

    public void run() {
        // programme start
        Ui.greet();
        Scanner input = new Scanner(System.in);
        String[] currInput = input.nextLine().split(" ", 2);

        // programme
        while (!exitProgramme.contains(currInput[0])) {
            Parser.parse(this.tasks, currInput);
            currInput = input.nextLine().split(" ", 2);
        }

        // save and exit
        this.storage.writeTasks(this.tasks);
        Ui.goodbye();
    }

    public static void main(String[] args) {
        new Duke("./data/data.txt").run();
    }
}
