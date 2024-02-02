package jiayou;

import java.util.Scanner;
import jiayou.task.*;

/** Represents the user interface for the chatbot.
 * @author Liu Jiayao
 */
public class Ui {
    private static final String LINE = "____________________________________________________________";
    private Parser parser = new Parser();
    private TaskList tasks;

    /**
     * Returns a new Ui instance.
     *
     * @param tasks The task list linked to the chatbot interface.
     */
    public Ui(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Prints the greeting message.
     */
    public void greet() {
        System.out.println(Ui.LINE);
        System.out.println("Hello! I'm Jiayou. ");
        System.out.println("What can I do for you? > <");
        System.out.println(Ui.LINE);
        chat();
    }

    /**
     * Starts reading in the user command and continuing the chat.
     */
    public void chat() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            System.out.println(Ui.LINE);

            if (input.equals("bye")) { break;}
            this.parser.parse(this.tasks, input);

            System.out.println(Ui.LINE);
        }
        sc.close();
        bye();
    }

    /**
     * Prints the bye message.
     */
    public void bye() {
        System.out.println("Bye. Hope to see you again soon! > <");
        System.out.println(Ui.LINE);
    }
}
