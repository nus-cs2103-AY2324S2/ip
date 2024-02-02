package duke.command;

import duke.exception.CommandFormatException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import java.io.IOException;

public class UnmarkCommand extends Command{
    private String com;
    public UnmarkCommand(String i) {
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
            String out = tL.unMark(noArr);
            ui.showMessage(out);
            st.write(tL.getList());
        } catch (IndexOutOfBoundsException e) {
            ui.showMessage("No task number " + inputs[1]);
        } catch (NumberFormatException e) {
            ui.showMessage("The task number given is not a number");
        } catch (CommandFormatException e) {
            ui.showMessage("The command format for unmark is unmark number (e.g.: unmark 1)");
        } catch (IOException e) {
            ui.showMessage("Save failed");
        }
    }

    public String getCom() {
        return com;
    }
}

