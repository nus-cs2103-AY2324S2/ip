package lemona.command;

import lemona.oop.TaskList;

/**
 * A ByeCommand to handle bye command.
 */
public class ByeCommand extends Command{
    /**
     * Constructs ByeCommand object to handle bye command.
     */
    public ByeCommand(){}

    @Override
    public String execute(TaskList tasks) {
        String str = "Bye. Don't forget to take a LEMONA!";
        return str;
    }
}
