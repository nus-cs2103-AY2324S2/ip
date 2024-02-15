package duke;

import java.io.InputStream;
import java.util.Scanner;

/**
 * Represents the interface that the user interacts with.
 */
public class Ui {
    private Scanner sc;

    /**
     * Constructs an instance of UI.
     *
     * @param inputstream References the system input.
     */
    public Ui(InputStream inputstream) {
        this.sc = new Scanner(inputstream);
    }

    /**
     * Retreives the next instruction in the following line.
     *
     * @return The next instruction.
     */
    public String getInstr() {
        return this.sc.nextLine();
    }

    /**
     * Closes the scanner.
     */
    public void close() {
        this.sc.close();
    }

    /**
     * Takes care of the intialisation response.
     *
     * @return A string of output for the user as the chatbot boots up.
     */
    public String greet() {
        return "Hello! I'm YLEXI. \nWhat can I do for you?";
    }

    /**
     * Takes case of the response as the user exits.
     *
     * @return A string of output for the user as the chatbot ends.
     */
    public String exit() {
        return "Bye. Hope to see you again soon!";
    }
}
