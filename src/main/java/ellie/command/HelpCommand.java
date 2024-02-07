package ellie.command;

import ellie.TaskList;

public class HelpCommand extends Command {

    /**
     * Constructs a HelpCommand object.
     */
    public HelpCommand() {
        super.isExit = false;
    }

    /**
     * Displays a list of supported commands.
     *
     * @param tasklist The TaskList to be operated on by the command.
     */
    public void run(TaskList tasklist) {
        System.out.println("Here's a list of supported commands so far:"
                + "\n help \n list \n mark/unmark [int] \n todo [task] \n "
                + "deadline [task] /by [date]  \n event [task] /from [date] /to [date] \n bye/exit \n");
    }
}

