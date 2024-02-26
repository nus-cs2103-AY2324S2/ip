package commands;

import static destiny.Destiny.GOODBYE_MSG;

import destiny.DestinyException;
import destiny.TaskList;

/**
 * Command that simply returns the goodbye message
 */
public class GoodbyeCmd extends Command {

    @Override
    public String execute(TaskList tasks) throws DestinyException {
        return GOODBYE_MSG;
    }
}
