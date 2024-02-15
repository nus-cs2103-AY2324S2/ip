package duke;

import duke.tasks.TaskList;
import javafx.util.Pair;

/**
 * Handles interaction with the user by printing out the response generated from each command in the console log/
 */
public class Ui {
    public Ui() {

    }

    /**
     *
     * @param message command from user
     * @param parser parser created
     * @param taskList current taskList
     * @return returns a modified taskList for further modifications during the run function.
     */

    public TaskList printMessage(String message, Parser parser, TaskList taskList) {
        assert message != null : "No command received";
        Pair<TaskList, String> output =  parser.parse(taskList, message);
        System.out.println(output.getValue());
        return output.getKey();
    }

    /**
     * Greets the user upon running the chatbot.
     */

    public void greet() {
        this.printLines();
        System.out.println("Hello! I'm Toothless!\nWhat can I do for you?");
        this.printLines();
    }
    /**
     * Greets the user bye upon receiving a "bye" instruction.
     */
    public void bye() {
        this.printLines();
        System.out.println("Bye. Hope to see you again soon!");
        this.printLines();
    }

    /**
     * For formatting purposes
     */
    public void printLines() {
        System.out.println("____________________________________________________________");
    }

}
