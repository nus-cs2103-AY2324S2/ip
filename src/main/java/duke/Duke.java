package duke;

import javafx.application.Application;

/**
 * The Duke program is a chatbot that helps users to keep track of their tasks.
 * It can store and retrieve tasks from a file, and perform operations such as adding, deleting,
 * and marking tasks as done.
 */
public class Duke {

    /**
     * Main method to run the Duke program.
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        Application.launch(DukeApp.class);
    }
}
