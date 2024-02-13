package duke.commands;

import duke.exceptions.DukeException;
import duke.exceptions.EmptyDescriptionException;
import duke.main.Duke;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.UI;

public class NameCommand extends Command {
    private String[] words;
    public NameCommand(String[] words) {
        super();
        this.words = words;
    }

    @Override
    public boolean execute(TaskList tasks, UI ui, Storage storage) {
        ui.setName(this.words[1]);
        System.out.println(ui.getName());
        return false;
    }

    @Override
    public String executeForString(TaskList tasks, UI ui, Storage storage) throws DukeException {
        if (words.length == 1) {
            throw new EmptyDescriptionException("name");
        }
        ui.setName(words[1]);
        return ui.nameMessage();
    }
}
