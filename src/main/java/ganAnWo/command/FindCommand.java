package ganAnWo.command;

import ganAnWo.Storage;
import ganAnWo.TaskList;
import ganAnWo.Ui;
import ganAnWo.exception.CommandFormatException;

/**
 * Class for "find" command.
 *
 */
public class FindCommand extends Command {

    private String com;

    /**
     * Constructor for FindCommand.
     *
     * @param com the given command
     */
    public FindCommand(String com) {
        super(0);
        this.com = com;
    }

    @Override
    public void execute(TaskList tL, Ui ui, Storage st) {
        String[] inputs = com.split(" ");
        assert inputs.length > 0;
        try {
            if (!(inputs.length == 2)) {
                throw new CommandFormatException();
            }
            String key = inputs[1];
            String out = tL.findList(key);
            ui.showMessage(out);
            setOut(out);
        } catch (CommandFormatException e) {
            ui.showMessage("The command format for find is find 'key' (e.g.: find book)");
            setOut("The command format for find is find 'key' (e.g.: find book)");
        }
    }

    /**
     * returns the command initialised in
     * this instance of FindCommand.
     *
     * @return the command initialised.
     */
    public String getCom() {
        return com;
    }
}
