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
    /**
     * Running the main part of the code to start the Chatbot Cinnamo
     */
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

    /**
     * Setting the path directory to load data from the input and
     * the file to write and update the output
     */
    public static void main(String[] args) throws Exception {
        new Duke("src/main/Cinnamo.txt").run();
    }
}