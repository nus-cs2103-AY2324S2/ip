package model.commands;
import io.Message;

@FunctionalInterface
public interface Command {
    /**
     *
     * @param args the arguments of the message (this excludes the first word of the command)
     * @return the message to be outputted
     */
    public Message run(String args);
}
