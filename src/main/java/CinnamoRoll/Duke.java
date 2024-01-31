package CinnamoRoll;

import java.util.Scanner;
import java.io.IOException;

public class Duke {

    private Ui ui;
    private Storage storage;
    private TaskList tasklist;

    public Duke(String filepath) throws IOException {
        this.ui = new Ui();
        storage = new Storage(filepath);
        this.tasklist = new TaskList(storage.load_data(), filepath);
    }

    public void run() throws Exception {
        Scanner sc = new Scanner(System.in);
        this.ui.greet();
        while(true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                break;
            }
            ui.respond(this.tasklist, input);
        }
        ui.exit();
    }

    public static void main(String[] args) throws Exception {
        new Duke("src/main/Cinnamo.txt").run();
    }
}