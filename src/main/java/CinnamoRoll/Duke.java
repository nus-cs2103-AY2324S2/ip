package CinnamoRoll;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.util.Scanner;

public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList tasklist;

    public Duke() {
        this.ui = new Ui();
        storage = new Storage();
        this.tasklist = new TaskList();
    }
    /**
     * Running the main part of the code to start the Chatbot Cinnamo
     */
    public void run() throws Exception {
        this.tasklist = new TaskList(this.storage.loadData());
        Scanner sc = new Scanner(System.in);
        this.ui.greetUser();
        while(true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                break;
            }
            ui.respondUser(this.tasklist, input);
        }
        ui.exitChat();
    }

    public String getResponse(String input) {
        return "Cinnamo heard: " + input;
    }

    public static void main(String[] args) throws Exception {
        new Duke().run();
    }
}