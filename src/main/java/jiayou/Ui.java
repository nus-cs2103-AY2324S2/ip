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
        System.out.println("Hello! I'm Jiayou.");
        System.out.println("What can I do for you? > <");
        System.out.println("(enter 'help' to know all my functionalities!)");
        System.out.println(Ui.LINE);
        chat();
    }

    /**
     * Prints all the command types and usages.
     */
    public void guide() {
        System.out.println("To get the whole list of tasks: list");
        System.out.println("To mark/unmark a certain task with the index x: mark/unmark x");
        System.out.println("To delete a certain task with the index x: delete x");
        System.out.println("To search tasks on a certain date: search_by_date YYYY-MM-DD");
        System.out.println("To search tasks with the keyword x: search_by_keyword x");
        System.out.println("To add a todo task: todo description");
        System.out.println("To add a deadline task: deadline description /by YYYY-MM-DD");
        System.out.println("To add an event task: event description /from YYYY-MM-DD /to YYYY-MM-DD");
        System.out.println(Ui.LINE);
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
            if (input.equals("help")) {
                guide();
                continue;
            }
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
