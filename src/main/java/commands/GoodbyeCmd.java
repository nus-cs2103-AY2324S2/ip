package commands;

import destiny.DestinyException;
import destiny.TaskList;

import static destiny.Destiny.GOODBYE_MSG;

/**
 * Command that simply returns the goodbye message
 */
public class GoodbyeCmd extends Command {

    @Override
    public String execute(TaskList tasks) throws DestinyException {
        return GOODBYE_MSG;
    }
}
