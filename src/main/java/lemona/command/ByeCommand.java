package lemona.command;

import lemona.oop.TaskList;

public class ByeCommand extends Command{
    public ByeCommand(){}

    @Override
    public String execute(TaskList tasks) {
        String str = "Bye. Don't forget to take a LEMONA!";
        return str;
    }
}
