package remi.model.commands;

import javafx.application.Platform;
import remi.io.Message;
import remi.model.TaskList;
import remi.model.Ui;
import remi.utils.RemiError;

/**
 * Represents the exit command and its functionality.
 */
public class ExitCommand implements Command {

    /**
     * Exits the program. First closes javafx, then closes the System.
     *
     * @param args the arguments of the message (this excludes the first word of the command)
     * @param taskList the taskList object
     * @param chatbot the chatbot object
     * @return a Message that isn't displayed, since the program should exit
     * @throws RemiError
     */
    public Message run(String args, TaskList taskList, Ui chatbot) throws RemiError {
        Platform.exit();
        System.exit(0);
        return new Message("Bye. Please finish some of your tasks.");
    }
}
