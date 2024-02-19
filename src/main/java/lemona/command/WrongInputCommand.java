package lemona.command;

import lemona.oop.TaskList;

public class WrongInputCommand extends Command{

    public WrongInputCommand() {}

    @Override
    public String execute(TaskList tasks) {
        return "I think you haven't had enough vitamin C." +
                "\nI can't understand what you want me to do!" +
                "\nI suggest you take some LEMONA.";
    }
}
