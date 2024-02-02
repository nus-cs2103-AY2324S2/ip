package duke.command;

import duke.exception.CommandFormatException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import java.io.IOException;

public class MarkCommand extends Command{
    private String com;

    /**
     * Constructor for MarkCommand.
     *
     * @param i command in String format.
     */
    public MarkCommand(String i) {
        super(0);
        com = i;

    }

    @Override
    public void execute(TaskList tL, Ui ui, Storage st) {
        int noArr;
        String[] inputs = com.split(" ");
        try {
            if (!(inputs.length == 2)) {
                throw new CommandFormatException();
            }
            noArr = Integer.parseInt(inputs[1])-1;
            String out = tL.mark(noArr);
            ui.showMessage(out);
            st.write(tL.getList());
        } catch (IndexOutOfBoundsException e) {
            ui.showMessage("No task number " + inputs[1]);
        } catch (NumberFormatException e) {
            ui.showMessage("The task number given is not a number");
        } catch (CommandFormatException e) {
            ui.showMessage("The command format for mark is mark number (e.g.: mark 1)");
        } catch (IOException e) {
            ui.showMessage("Save failed");
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
