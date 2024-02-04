package remi.model.commands;
import remi.io.Message;
import remi.utils.RemiError;

/**
 * Interface for creating Commands, must override the run method.
 */
@FunctionalInterface
public interface Command {
    /**
     *
     * @param args the arguments of the message (this excludes the first word of the command)
     * @return the message to be outputted
     */
    Message run(String args) throws RemiError;
}
