package duke.command;

import java.io.IOException;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.CommandFormatException;

/**
 * Class for "unmark" command.
 *
 */
public class UnmarkCommand extends Command {
    private String com;

    /**
     * Constructor for UnmarkCommand.
     *
     * @param i command in String format.
     */
    public UnmarkCommand(String i) {
        super(0);
        com = i;

    }

    @Override
    public void execute(TaskList tL, Ui ui, Storage st) {
        int noArr;
        String[] inputs = com.split(" ");
        assert inputs.length > 0;
        try {
            if (!(inputs.length == 2)) {
                throw new CommandFormatException();
            }
            noArr = Integer.parseInt(inputs[1]) - 1;
            String out = tL.unMark(noArr);
            ui.showMessage(out);
            st.write(tL.getList());
            setOut(out);
        } catch (IndexOutOfBoundsException e) {
            ui.showMessage("No task number " + inputs[1]);
            setOut("No task number " + inputs[1]);
        } catch (NumberFormatException e) {
            ui.showMessage("The task number given is not a number");
            setOut("The task number given is not a number");
        } catch (CommandFormatException e) {
            ui.showMessage("The command format for unmark is unmark number (e.g.: unmark 1)");
            setOut("The command format for unmark is unmark number (e.g.: unmark 1)");
        } catch (IOException e) {
            ui.showMessage("Save failed");
            setOut("Save failed");
        }
    }

    /**
     * Returns command in String format.
     *
     * @return command in String format.
     */
    public String getCom() {
        return com;
    }
}

