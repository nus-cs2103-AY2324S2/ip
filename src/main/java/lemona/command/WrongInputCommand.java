package lemona.command;

import lemona.oop.TaskList;

/**
 * A WrongInputCommand to handle wrong commands.
 */
public class WrongInputCommand extends Command{

    /**
     * Constructs WrongInputCommand object to handle wrong commands.
     */
    public WrongInputCommand() {}

    @Override
    public String execute(TaskList tasks) {
        return "I think you haven't had enough vitamin C." +
                "\nI can't understand what you want me to do!" +
                "\nI suggest you take some LEMONA.";
    }
}
